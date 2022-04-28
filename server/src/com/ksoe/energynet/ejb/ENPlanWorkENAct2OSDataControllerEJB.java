
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------


package com.ksoe.energynet.ejb;

/**
 * EJB Controller for ENPlanWorkENAct2OSData;
 *
 */

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENActDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkENAct2OSDataDAO;
import com.ksoe.energynet.dataminer.ENWorkOrder2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENWorkOrderDAO;
import com.ksoe.energynet.dataminer.FINMolDataDAO;
import com.ksoe.energynet.ejb.generated.ENPlanWorkENAct2OSDataControllerEJBGen;
import com.ksoe.energynet.valueobject.ENAct;
import com.ksoe.energynet.valueobject.ENAct2ENPlanWork;
import com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData;
import com.ksoe.energynet.valueobject.ENWorkOrder;
import com.ksoe.energynet.valueobject.FINMolType;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkENAct2OSDataFilter;
import com.ksoe.energynet.valueobject.filter.ENWorkOrder2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.FINMolDataFilter;
import com.ksoe.energynet.valueobject.lists.ENWorkOrder2ENPlanWorkShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.fin.logic.FKOSLogic;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.rqorder.logic.RQConsts;
import com.ksoe.techcard.dataminer.OSTableDAO;
import com.ksoe.techcard.valueobject.filter.OSTableFilter;

public class ENPlanWorkENAct2OSDataControllerEJB extends
		ENPlanWorkENAct2OSDataControllerEJBGen {

	public ENPlanWorkENAct2OSDataControllerEJB() {
	}

	/* ENPlanWorkENAct2OSData. Добавить */
	@Override
	public int add(ENPlanWorkENAct2OSData object) {

		Connection finConn = null;
		Connection enConn = null;

		try {

			finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
		    enConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(getUserProfile(),enConn);

			ENWorkOrder2ENPlanWorkDAO wo2plDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),enConn);
			ENWorkOrderDAO woDAO = new ENWorkOrderDAO(getUserProfile(),enConn);
			FINMolDataDAO fmdDAO = new FINMolDataDAO(getUserProfile(),enConn);

			FKOSLogic OSLogic = new FKOSLogic(finConn, getUserProfile());

			if (object.num_un == Integer.MIN_VALUE ){
				throw new EnergyproSystemException(" \n Потрібно обрати основний засіб");
			}

			if (object.sumBuhWriteOZ == null ){
				throw new EnergyproSystemException(" \n Не вказана сума списання ОЗ !!!");
			}

		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();


	        ENWorkOrder2ENPlanWorkFilter wo2plFilter = new ENWorkOrder2ENPlanWorkFilter();
	        wo2plFilter.plan.code = object.planWorkRef.code;

	        ENWorkOrder2ENPlanWorkShortList wo2plList = wo2plDAO.getScrollableFilteredList(wo2plFilter, wo2plFilter.conditionSQL,
	        		"", 0, -1, null);

	        if (wo2plList.totalCount == 0 ){
	        	throw new EnergyproSystemException(" \n На плані не заведено Наряд-завдання !!!");
	        }


	        ENWorkOrder woObject = woDAO.getObjectNOSEGR(wo2plList.get(0).workOrderCode);

	        FINMolDataFilter fmdFilter = new FINMolDataFilter();
	        fmdFilter.workOrder.code = woObject.code;
	        fmdFilter.molTypeRef.code = FINMolType.MASTER;

	        int[] fmdArr = fmdDAO.getFilteredCodeArray(fmdFilter, 0, -1);

	        if (fmdArr.length == 0 ){
	        	throw new EnergyproSystemException(" \n Додайте МОЛ на  Наряд-завдання в плані !!!");
	        }

	        // проверка на разделенность основного
	        OSLogic.checkIsSeparateOS(object.kod_inv);

	        // при добавлении основного в план залочим основное для списания (реквизиты наряд задания плана )
	        OSLogic.lockOS(object.num_un, RQConsts.TYPE_RESERVE_OS_WRITINGS, woObject.workOrderNumber, woObject.dateGen);

	        ///-------------------------
	        // посчитаем суммы для списываемого ОС   //бух ст Первоначальная    //износ на кон мес бух     //нал ст Первонач   //износ на кон мес на

	        // по инвентарному вытянем суммы "Первоначальная стоиость бух/у", "Первоначальная стоиость нал/у","Износ бух/у", "Износ нал/у"
            BigDecimal[] costPervIznBuhNal = OSLogic.getCostPervIznBuhNal(object.kod_inv , " and 1 = 1 ");

            BigDecimal st_current_b = costPervIznBuhNal[0]; //   Первоначальная стоиость бух/у
            BigDecimal st_current = costPervIznBuhNal[1];   //   Первоначальная стоиость нал/у
            BigDecimal izn_current_b = costPervIznBuhNal[2]; //  Износ бух/у
            BigDecimal izn_current = costPervIznBuhNal[3];    // Износ нал/у

            // проверим что бы правильно ставили тип списания (0-частичная 1- полная )

            if (object.typeWriteOff == 0 && ( st_current_b.doubleValue() == object.sumBuhWriteOZ.doubleValue()   )  ){
            	throw new EnergyproSystemException(" Тип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   дорівнює первісній вартості по бух. обліку (" + st_current_b.doubleValue() + ") " );
            }

            if (object.typeWriteOff == 1 && ( st_current_b.doubleValue() != object.sumBuhWriteOZ.doubleValue()   )  ){
            	throw new EnergyproSystemException(" Тип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   не співпадає з первісною вартістю по бух. обліку (" + st_current_b.doubleValue() + ") " );
            }

            if ( st_current_b.doubleValue() < object.sumBuhWriteOZ.doubleValue()  ){
            	throw new EnergyproSystemException(" Сума для списання ОЗ(" + object.sumBuhWriteOZ + ") перевищує його первістну вартість з карточки ОЗ ("+ st_current_b.doubleValue() +") " );
            }

            BigDecimal st_current_b_div_for_writeoff = new BigDecimal(0);
            BigDecimal st_current_div_for_writeoff = new BigDecimal(0);
            BigDecimal izn_current_b_div_for_writeoff = new BigDecimal(0);
            BigDecimal izn_current_div_for_writeoff = new BigDecimal(0);

            if(object.typeWriteOff == 0){

            	// коефициент на который будем умешать суммы для разделения
                // BigDecimal sKoef = pw2actOSObj.sumBuhWriteOZ.divide(st_current_b,16, BigDecimal.ROUND_HALF_UP);

            	double sKoef = object.sumBuhWriteOZ.doubleValue()/st_current_b.doubleValue();

            	st_current_b_div_for_writeoff = st_current_b.multiply( new BigDecimal(sKoef) ).setScale(2, BigDecimal.ROUND_HALF_UP); // pw2actOSObj.sumBuhWriteOZ; //   Первоначальная стоиость бух/у для основного который разделим и спишем
                st_current_div_for_writeoff = st_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);   //  Первоначальная стоиость нал/у для основного который разделим и спишем
                izn_current_b_div_for_writeoff = izn_current_b.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP); //  Износ бух/у для основного который разделим и спишем
                izn_current_div_for_writeoff = izn_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);    // Износ нал/у для основного который разделим и спишем

                if (st_current_b_div_for_writeoff.doubleValue() !=  object.sumBuhWriteOZ.doubleValue() ){
                	throw new EnergyproSystemException( " Помилка розрахунку суми зносу ОЗ !!! " );
                }

                object.sumStCurrentN = st_current_div_for_writeoff;
                object.sumIznCurrentB = izn_current_b_div_for_writeoff;
                object.sumIznCurrentN = izn_current_div_for_writeoff;

            } else
            	if (object.typeWriteOff == 1){

            		object.sumStCurrentN = st_current.setScale(2, BigDecimal.ROUND_HALF_UP);
                    object.sumIznCurrentB = izn_current_b.setScale(2, BigDecimal.ROUND_HALF_UP);
                    object.sumIznCurrentN = izn_current.setScale(2, BigDecimal.ROUND_HALF_UP);

            	}

            ///-------------------------\\\

		    return objectDAO.add(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	/* ENPlanWorkENAct2OSData. Удалить */
	@Override
	public void remove(int code) {
		try {
			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(
					getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			objectDAO.remove(code);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't remove {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

   	/* ENPlanWorkENAct2OSData. Изменить */
	@Override
	public void save(ENPlanWorkENAct2OSData object) {
		Connection finConn = null;
		Connection enConn = null;

		try {
			finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
		    enConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

			ENPlanWorkENAct2OSDataDAO objectDAO = new ENPlanWorkENAct2OSDataDAO(getUserProfile(),enConn);
			ENWorkOrder2ENPlanWorkDAO wo2plDAO = new ENWorkOrder2ENPlanWorkDAO(getUserProfile(),enConn);
			ENWorkOrderDAO woDAO = new ENWorkOrderDAO(getUserProfile(),enConn);
			FINMolDataDAO fmdDAO = new FINMolDataDAO(getUserProfile(),enConn);
			ENActDAO aDAO = new ENActDAO(getUserProfile(),enConn);

			FKOSLogic OSLogic = new FKOSLogic(finConn, getUserProfile());


			ENPlanWorkENAct2OSData objectOld = objectDAO.getObject(object.code);

			if (objectOld.actRef != null ){
				if (objectOld.actRef.code != Integer.MIN_VALUE ){
					ENAct aObj = aDAO.getObject(objectOld.actRef.code);
					throw new EnergyproSystemException(" \n Редагування неможливе. План на списання включено до акту на списання ОЗ № " + aObj.numberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(aObj.dateAct)
							+ "\n Видаляйте Наряд-завдання з акту!!! " ) ;
				}
			}


			if (object.num_un == Integer.MIN_VALUE ){
				throw new EnergyproSystemException(" \n Потрібно обрати основний засіб");
			}

			if (object.sumBuhWriteOZ == null ){
				throw new EnergyproSystemException(" \n Не вказана сума списання ОЗ !!!");
			}


			    ENWorkOrder2ENPlanWorkFilter wo2plFilter = new ENWorkOrder2ENPlanWorkFilter();
		        wo2plFilter.plan.code = object.planWorkRef.code;

		        ENWorkOrder2ENPlanWorkShortList wo2plList = wo2plDAO.getScrollableFilteredList(wo2plFilter, wo2plFilter.conditionSQL,
		        		"", 0, -1, null);

		        if (wo2plList.totalCount == 0 ){
		        	throw new EnergyproSystemException(" \n На плані не заведено Наряд-завдання !!!");
		        }


		        ENWorkOrder woObject = woDAO.getObjectNOSEGR(wo2plList.get(0).workOrderCode);

		        FINMolDataFilter fmdFilter = new FINMolDataFilter();
		        fmdFilter.workOrder.code = woObject.code;
		        fmdFilter.molTypeRef.code = FINMolType.MASTER;

		        int[] fmdArr = fmdDAO.getFilteredCodeArray(fmdFilter, 0, -1);

		        if (fmdArr.length == 0 ){
		        	throw new EnergyproSystemException(" \n Додайте МОЛ на  Наряд-завдання в плані !!!");
		        }

		        //
		        OSTableDAO ostDAO = new OSTableDAO(finConn, getUserProfile());
		        OSTableFilter ostFilter = new OSTableFilter();
		        ostFilter.num_un = object.num_un;

		        int[] ostArr = ostDAO.getFilteredCodeArray(ostFilter, 0, -1);

		        if (ostArr.length == 0 ){
		        	throw new EnergyproSystemException(" \n \n NET-4383, num_un " + object.num_un + " Не знайдено у модулі \" Основні средства \"  ");
		        }

		        // сменился инвентарный тогда снимем блокировку на том который был и установим блокировку на новом основном
		        if (object.num_un != objectOld.num_un){

		        	OSLogic.unlockOS(objectOld.num_un);
		        	OSLogic.lockOS(object.num_un, RQConsts.TYPE_RESERVE_OS_WRITINGS, woObject.workOrderNumber, woObject.dateGen);

		        	// проверка на разделенность основного
		        	OSLogic.checkIsSeparateOS(object.kod_inv);

		        	 ///-------------------------
			        // посчитаем суммы для списываемого ОС   //бух ст Первоначальная    //износ на кон мес бух     //нал ст Первонач   //износ на кон мес на

			        // по инвентарному вытянем суммы "Первоначальная стоиость бух/у", "Первоначальная стоиость нал/у","Износ бух/у", "Износ нал/у"
		            BigDecimal[] costPervIznBuhNal = OSLogic.getCostPervIznBuhNal(object.kod_inv , " and  1 = 1 ");

		            BigDecimal st_current_b = costPervIznBuhNal[0]; //   Первоначальная стоиость бух/у
		            BigDecimal st_current = costPervIznBuhNal[1];   //   Первоначальная стоиость нал/у
		            BigDecimal izn_current_b = costPervIznBuhNal[2]; //  Износ бух/у
		            BigDecimal izn_current = costPervIznBuhNal[3];    // Износ нал/у

		            // проверим что бы правильно ставили тип списания (0-частичная 1- полная )

		            if (object.typeWriteOff == 0 && ( st_current_b.doubleValue() == object.sumBuhWriteOZ.doubleValue()   )  ){
		            	throw new EnergyproSystemException(" \n Тип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   дорівнює первісній вартості по бух. обліку (" + st_current_b.doubleValue() + ") " );
		            }

		            if (object.typeWriteOff == 1 && ( st_current_b.doubleValue() != object.sumBuhWriteOZ.doubleValue()   )  ){
		            	throw new EnergyproSystemException(" \nТип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   не співпадає з первісною вартістю по бух. обліку (" + st_current_b.doubleValue() + ") " );
		            }

		            if ( st_current_b.doubleValue() < object.sumBuhWriteOZ.doubleValue()  ){
		            	throw new EnergyproSystemException(" Сума для списання ОЗ(" + object.sumBuhWriteOZ + ") перевищує його первістну вартість з карточки ОЗ ("+ st_current_b.doubleValue() +") " );
		            }

		            BigDecimal st_current_b_div_for_writeoff = new BigDecimal(0);
		            BigDecimal st_current_div_for_writeoff = new BigDecimal(0);
		            BigDecimal izn_current_b_div_for_writeoff = new BigDecimal(0);
		            BigDecimal izn_current_div_for_writeoff = new BigDecimal(0);

		            if(object.typeWriteOff == 0){

		            	// коефициент на который будем умешать суммы для разделения
		                // BigDecimal sKoef = pw2actOSObj.sumBuhWriteOZ.divide(st_current_b,16, BigDecimal.ROUND_HALF_UP);

		            	double sKoef = object.sumBuhWriteOZ.doubleValue()/st_current_b.doubleValue();

		            	st_current_b_div_for_writeoff = st_current_b.multiply( new BigDecimal(sKoef) ).setScale(2, BigDecimal.ROUND_HALF_UP); // pw2actOSObj.sumBuhWriteOZ; //   Первоначальная стоиость бух/у для основного который разделим и спишем
		                st_current_div_for_writeoff = st_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);   //  Первоначальная стоиость нал/у для основного который разделим и спишем
		                izn_current_b_div_for_writeoff = izn_current_b.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP); //  Износ бух/у для основного который разделим и спишем
		                izn_current_div_for_writeoff = izn_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);    // Износ нал/у для основного который разделим и спишем

		                if (st_current_b_div_for_writeoff.doubleValue() !=  object.sumBuhWriteOZ.doubleValue() ){
		                	throw new EnergyproSystemException( " Помилка розрахунку суми зносу ОЗ !!! " );
		                }

		                object.sumStCurrentN = st_current_div_for_writeoff;
		                object.sumIznCurrentB = izn_current_b_div_for_writeoff;
		                object.sumIznCurrentN = izn_current_div_for_writeoff;

		            } else
		            	if (object.typeWriteOff == 1){

		            		object.sumStCurrentN = st_current.setScale(2, BigDecimal.ROUND_HALF_UP);
		                    object.sumIznCurrentB = izn_current_b.setScale(2, BigDecimal.ROUND_HALF_UP);
		                    object.sumIznCurrentN = izn_current.setScale(2, BigDecimal.ROUND_HALF_UP);

		            	}

		            ///-------------------------\\\

		        }
		        else {

		        	// по инвентарному вытянем суммы "Первоначальная стоиость бух/у", "Первоначальная стоиость нал/у","Износ бух/у", "Износ нал/у"
		        	 BigDecimal[] costPervIznBuhNal = OSLogic.getCostPervIznBuhNal(object.kod_inv , " and  1 = 1 ");

                     BigDecimal st_current_b = costPervIznBuhNal[0]; //   Первоначальная стоиость бух/у
			         BigDecimal st_current = costPervIznBuhNal[1];   //   Первоначальная стоиость нал/у
			         BigDecimal izn_current_b = costPervIznBuhNal[2]; //  Износ бух/у
			         BigDecimal izn_current = costPervIznBuhNal[3];    // Износ нал/у


		            // проверим что бы правильно ставили тип списания (0-частичная 1- полная )

		            if (object.typeWriteOff == 0 && ( st_current_b.doubleValue() == object.sumBuhWriteOZ.doubleValue()   )  ){
		            	throw new EnergyproSystemException(" \n Тип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   дорівнює первісній вартості по бух. обліку (" + st_current_b.doubleValue() + ") " );
		            }

		            if (object.typeWriteOff == 1 && ( st_current_b.doubleValue() != object.sumBuhWriteOZ.doubleValue()   )  ){
		            	throw new EnergyproSystemException(" \nТип списання на плані для списання ОЗ невірний. Сума Списання ОЗ на плані ( " + object.sumBuhWriteOZ.toString() + " )   не співпадає з первісною вартістю по бух. обліку (" + st_current_b.doubleValue() + ") " );
		            }

		            if ( st_current_b.doubleValue() < object.sumBuhWriteOZ.doubleValue()  ){
		            	throw new EnergyproSystemException(" Сума для списання ОЗ(" + object.sumBuhWriteOZ + ") перевищує його первістну вартість з карточки ОЗ ("+ st_current_b.doubleValue() +") " );
		            }

		            BigDecimal st_current_b_div_for_writeoff = new BigDecimal(0);
		            BigDecimal st_current_div_for_writeoff = new BigDecimal(0);
		            BigDecimal izn_current_b_div_for_writeoff = new BigDecimal(0);
		            BigDecimal izn_current_div_for_writeoff = new BigDecimal(0);

		            if(object.typeWriteOff == 0){

		            	// коефициент на который будем умешать суммы для разделения
		                // BigDecimal sKoef = pw2actOSObj.sumBuhWriteOZ.divide(st_current_b,16, BigDecimal.ROUND_HALF_UP);

		            	double sKoef = object.sumBuhWriteOZ.doubleValue()/st_current_b.doubleValue();

		            	st_current_b_div_for_writeoff = st_current_b.multiply( new BigDecimal(sKoef) ).setScale(2, BigDecimal.ROUND_HALF_UP); // pw2actOSObj.sumBuhWriteOZ; //   Первоначальная стоиость бух/у для основного который разделим и спишем
		                st_current_div_for_writeoff = st_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);   //  Первоначальная стоиость нал/у для основного который разделим и спишем
		                izn_current_b_div_for_writeoff = izn_current_b.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP); //  Износ бух/у для основного который разделим и спишем
		                izn_current_div_for_writeoff = izn_current.multiply(new BigDecimal(sKoef)).setScale(2, BigDecimal.ROUND_HALF_UP);    // Износ нал/у для основного который разделим и спишем

		                if (st_current_b_div_for_writeoff.doubleValue() !=  object.sumBuhWriteOZ.doubleValue() ){
		                	throw new EnergyproSystemException( " Помилка розрахунку суми зносу ОЗ !!! " );
		                }

		                object.sumStCurrentN = st_current_div_for_writeoff;
		                object.sumIznCurrentB = izn_current_b_div_for_writeoff;
		                object.sumIznCurrentN = izn_current_div_for_writeoff;

		            } else
		            	if (object.typeWriteOff == 1){

		            		object.sumStCurrentN = st_current.setScale(2, BigDecimal.ROUND_HALF_UP);
		                    object.sumIznCurrentB = izn_current_b.setScale(2, BigDecimal.ROUND_HALF_UP);
		                    object.sumIznCurrentN = izn_current.setScale(2, BigDecimal.ROUND_HALF_UP);

		            	}
		             }



		    object.userGen = getUserProfile().userName;
	        object.dateEdit = new Date();

		    objectDAO.save(object);
		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/* ENPlanWorkENAct2OSData. Изменить источник для списания и код акта */
	public void saveIstOS(ENPlanWorkENAct2OSData object) {
		Connection enConn = null;
		Connection finConn = null;

		try {
			enConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
			finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

			ENPlanWorkENAct2OSDataDAO pw2actOSDAO = new ENPlanWorkENAct2OSDataDAO(getUserProfile(),enConn);
			ENAct2ENPlanWorkDAO a2pDAO = new ENAct2ENPlanWorkDAO(getUserProfile(),enConn);
			ENActDAO aDAO = new ENActDAO(getUserProfile(),enConn);


			FKOSLogic OSLogic = new FKOSLogic(finConn, getUserProfile());

			if (object.actRef == null){
				throw new EnergyproSystemException(" \n NET-4383 Помилка при збереженні джерела списання. Не визначений код акта!!!");
			}

			if (object.actRef.code == Integer.MIN_VALUE){
				throw new EnergyproSystemException(" \n NET-4383 Помилка при збереженні джерела списання. Не визначений код акта!!!");
			}

			ENAct aObj = aDAO.getObject(object.actRef.code);

			ENAct2ENPlanWorkFilter a2pFilter = new ENAct2ENPlanWorkFilter();
			a2pFilter.actRef.code = object.actRef.code;

			int[] a2pArr = a2pDAO.getFilteredCodeArray(a2pFilter,0, -1);

			ENAct2ENPlanWork a2pObj = a2pDAO.getObject(a2pArr[0]);

			if (a2pArr.length == 0){
				throw new EnergyproSystemException(" \n NET-4383 Помилка при збереженні джерела списання!!!");
			}





			ENPlanWorkENAct2OSDataFilter pw2actOSFilter = new ENPlanWorkENAct2OSDataFilter();
			pw2actOSFilter.planWorkRef.code = a2pObj.plan.code;

			int[] pw2actOSArr = pw2actOSDAO.getFilteredCodeArray(pw2actOSFilter, 0, -1);


			if (pw2actOSArr.length > 0 ){
				ENPlanWorkENAct2OSData objectOsData = pw2actOSDAO.getObject(pw2actOSArr[0]);

				objectOsData.actRef.code = object.actRef.code;
				objectOsData.kod_ist = object.kod_ist;
				objectOsData.name_ist = object.name_ist;

				object.userGen = getUserProfile().userName;
		        object.dateEdit = new Date();

		        // поменям документ и дату документа блокировки осного с наряд задания на акт в момент выбора источника
		        OSLogic.lockOS(objectOsData.num_un, RQConsts.TYPE_RESERVE_OS_WRITINGS, aObj.numberGen, aObj.dateAct);

		        pw2actOSDAO.save(objectOsData);



			} else {
				throw new EnergyproSystemException(" \n NET-4383 Помилка при збереженні джерела списання. Не визначена інформація для списання ОС !!!");
			}



		} catch (DatasourceConnectException e) {
			throw new SystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData%} object.",
					e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


} // end of EJB Controller for ENPlanWorkENAct2OSData