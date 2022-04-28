
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer.generated;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.authorization.valueobject.DataAccessType;
import com.ksoe.lla.persistence.GenericDataMiner;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.lla.persistence.SequenceKey;
import com.ksoe.lla.persistence.SequenceValue;
import com.ksoe.lla.persistence.exception.SystemException;


import java.util.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.dataminer.BaseDAOUtils;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Humen;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2HumenFilter;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2HumenShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2HumenShortList;


/**
 * DAO Object for ENPlanWorkItem2Humen;
 *
 */

public class ENPlanWorkItem2HumenDAOGen extends GenericDataMiner {

	public ENPlanWorkItem2HumenDAOGen(UserProfile anUserProfile, Connection aConnection) {
		super(anUserProfile,aConnection);
	}

	public ENPlanWorkItem2HumenDAOGen(Connection aConnection, UserProfile anUserProfile) {
		super(aConnection,anUserProfile);
	}

	public boolean isEqual(ENPlanWorkItem2Humen inObject) throws PersistenceException {
		ENPlanWorkItem2Humen obj = new ENPlanWorkItem2Humen();
		obj.code = inObject.code;
		loadObject(obj);

		if (inObject.orederNum != obj.orederNum){
					return false;
				}

		if(inObject.tabNumber == null && obj.tabNumber == null){}
		else
			if(inObject.tabNumber == null || obj.tabNumber == null) return false;
			else
				if ( ! inObject.tabNumber.equals(obj.tabNumber)){
					return false;
				}

		if(inObject.fio == null && obj.fio == null){}
		else
			if(inObject.fio == null || obj.fio == null) return false;
			else
				if ( ! inObject.fio.equals(obj.fio)){
					return false;
				}

		if(inObject.salary == null && obj.salary == null){}
		else
			if(inObject.salary == null || obj.salary == null) return false;
			else
				if ( ! inObject.salary.equals(obj.salary)){
					return false;
				}

		if(inObject.timeMonth == null && obj.timeMonth == null){}
		else
			if(inObject.timeMonth == null || obj.timeMonth == null) return false;
			else
				if ( ! inObject.timeMonth.equals(obj.timeMonth)){
					return false;
				}

		if(inObject.daysMonth == null && obj.daysMonth == null){}
		else
			if(inObject.daysMonth == null || obj.daysMonth == null) return false;
			else
				if ( ! inObject.daysMonth.equals(obj.daysMonth)){
					return false;
				}

		if(inObject.salaryHours == null && obj.salaryHours == null){}
		else
			if(inObject.salaryHours == null || obj.salaryHours == null) return false;
			else
				if ( ! inObject.salaryHours.equals(obj.salaryHours)){
					return false;
				}

		if(inObject.timeWork == null && obj.timeWork == null){}
		else
			if(inObject.timeWork == null || obj.timeWork == null) return false;
			else
				if ( ! inObject.timeWork.equals(obj.timeWork)){
					return false;
				}

		if(inObject.timeObjectWork == null && obj.timeObjectWork == null){}
		else
			if(inObject.timeObjectWork == null || obj.timeObjectWork == null) return false;
			else
				if ( ! inObject.timeObjectWork.equals(obj.timeObjectWork)){
					return false;
				}

		if(inObject.timeWorkFact == null && obj.timeWorkFact == null){}
		else
			if(inObject.timeWorkFact == null || obj.timeWorkFact == null) return false;
			else
				if ( ! inObject.timeWorkFact.equals(obj.timeWorkFact)){
					return false;
				}

		if(inObject.timeDelivery == null && obj.timeDelivery == null){}
		else
			if(inObject.timeDelivery == null || obj.timeDelivery == null) return false;
			else
				if ( ! inObject.timeDelivery.equals(obj.timeDelivery)){
					return false;
				}

		if(inObject.paysWork == null && obj.paysWork == null){}
		else
			if(inObject.paysWork == null || obj.paysWork == null) return false;
			else
				if ( ! inObject.paysWork.equals(obj.paysWork)){
					return false;
				}

		if(inObject.bonus == null && obj.bonus == null){}
		else
			if(inObject.bonus == null || obj.bonus == null) return false;
			else
				if ( ! inObject.bonus.equals(obj.bonus)){
					return false;
				}

		if(inObject.salaryHoursBonus == null && obj.salaryHoursBonus == null){}
		else
			if(inObject.salaryHoursBonus == null || obj.salaryHoursBonus == null) return false;
			else
				if ( ! inObject.salaryHoursBonus.equals(obj.salaryHoursBonus)){
					return false;
				}

		if(inObject.paysWorkBonus == null && obj.paysWorkBonus == null){}
		else
			if(inObject.paysWorkBonus == null || obj.paysWorkBonus == null) return false;
			else
				if ( ! inObject.paysWorkBonus.equals(obj.paysWorkBonus)){
					return false;
				}

		if(inObject.chargePercent == null && obj.chargePercent == null){}
		else
			if(inObject.chargePercent == null || obj.chargePercent == null) return false;
			else
				if ( ! inObject.chargePercent.equals(obj.chargePercent)){
					return false;
				}

		if(inObject.chargeSum == null && obj.chargeSum == null){}
		else
			if(inObject.chargeSum == null || obj.chargeSum == null) return false;
			else
				if ( ! inObject.chargeSum.equals(obj.chargeSum)){
					return false;
				}

		if(inObject.balans == null && obj.balans == null){}
		else
			if(inObject.balans == null || obj.balans == null) return false;
			else
				if ( ! inObject.balans.equals(obj.balans)){
					return false;
				}

		if(inObject.podrcod == null && obj.podrcod == null){}
		else
			if(inObject.podrcod == null || obj.podrcod == null) return false;
			else
				if ( ! inObject.podrcod.equals(obj.podrcod)){
					return false;
				}

		if(inObject.costDelivery == null && obj.costDelivery == null){}
		else
			if(inObject.costDelivery == null || obj.costDelivery == null) return false;
			else
				if ( ! inObject.costDelivery.equals(obj.costDelivery)){
					return false;
				}

		if(inObject.generalExpenses == null && obj.generalExpenses == null){}
		else
			if(inObject.generalExpenses == null || obj.generalExpenses == null) return false;
			else
				if ( ! inObject.generalExpenses.equals(obj.generalExpenses)){
					return false;
				}

		if(inObject.administrativeExpenses == null && obj.administrativeExpenses == null){}
		else
			if(inObject.administrativeExpenses == null || obj.administrativeExpenses == null) return false;
			else
				if ( ! inObject.administrativeExpenses.equals(obj.administrativeExpenses)){
					return false;
				}

		if(inObject.cehId == null && obj.cehId == null){}
		else
			if(inObject.cehId == null || obj.cehId == null) return false;
			else
				if ( ! inObject.cehId.equals(obj.cehId)){
					return false;
				}

		if(inObject.percentSurcharge == null && obj.percentSurcharge == null){}
		else
			if(inObject.percentSurcharge == null || obj.percentSurcharge == null) return false;
			else
				if ( ! inObject.percentSurcharge.equals(obj.percentSurcharge)){
					return false;
				}

		if(inObject.salaryHoursSurcharge == null && obj.salaryHoursSurcharge == null){}
		else
			if(inObject.salaryHoursSurcharge == null || obj.salaryHoursSurcharge == null) return false;
			else
				if ( ! inObject.salaryHoursSurcharge.equals(obj.salaryHoursSurcharge)){
					return false;
				}

		if(inObject.paysWorkSurcharge == null && obj.paysWorkSurcharge == null){}
		else
			if(inObject.paysWorkSurcharge == null || obj.paysWorkSurcharge == null) return false;
			else
				if ( ! inObject.paysWorkSurcharge.equals(obj.paysWorkSurcharge)){
					return false;
				}

		if(inObject.percentPremium == null && obj.percentPremium == null){}
		else
			if(inObject.percentPremium == null || obj.percentPremium == null) return false;
			else
				if ( ! inObject.percentPremium.equals(obj.percentPremium)){
					return false;
				}

		if(inObject.salaryHoursPremium == null && obj.salaryHoursPremium == null){}
		else
			if(inObject.salaryHoursPremium == null || obj.salaryHoursPremium == null) return false;
			else
				if ( ! inObject.salaryHoursPremium.equals(obj.salaryHoursPremium)){
					return false;
				}

		if(inObject.paysWorkPremium == null && obj.paysWorkPremium == null){}
		else
			if(inObject.paysWorkPremium == null || obj.paysWorkPremium == null) return false;
			else
				if ( ! inObject.paysWorkPremium.equals(obj.paysWorkPremium)){
					return false;
				}

		if(inObject.percentAdditional == null && obj.percentAdditional == null){}
		else
			if(inObject.percentAdditional == null || obj.percentAdditional == null) return false;
			else
				if ( ! inObject.percentAdditional.equals(obj.percentAdditional)){
					return false;
				}

		if(inObject.salaryHoursAdditional == null && obj.salaryHoursAdditional == null){}
		else
			if(inObject.salaryHoursAdditional == null || obj.salaryHoursAdditional == null) return false;
			else
				if ( ! inObject.salaryHoursAdditional.equals(obj.salaryHoursAdditional)){
					return false;
				}

		if(inObject.paysWorkAdditional == null && obj.paysWorkAdditional == null){}
		else
			if(inObject.paysWorkAdditional == null || obj.paysWorkAdditional == null) return false;
			else
				if ( ! inObject.paysWorkAdditional.equals(obj.paysWorkAdditional)){
					return false;
				}

		if(inObject.paysWorkWithAllSurcharge == null && obj.paysWorkWithAllSurcharge == null){}
		else
			if(inObject.paysWorkWithAllSurcharge == null || obj.paysWorkWithAllSurcharge == null) return false;
			else
				if ( ! inObject.paysWorkWithAllSurcharge.equals(obj.paysWorkWithAllSurcharge)){
					return false;
				}

		if(inObject.paysWorkWithAllSurchargeWithoutDeliv == null && obj.paysWorkWithAllSurchargeWithoutDeliv == null){}
		else
			if(inObject.paysWorkWithAllSurchargeWithoutDeliv == null || obj.paysWorkWithAllSurchargeWithoutDeliv == null) return false;
			else
				if ( ! inObject.paysWorkWithAllSurchargeWithoutDeliv.equals(obj.paysWorkWithAllSurchargeWithoutDeliv)){
					return false;
				}

		if(inObject.paysWorkBonusWithoutDeliv == null && obj.paysWorkBonusWithoutDeliv == null){}
		else
			if(inObject.paysWorkBonusWithoutDeliv == null || obj.paysWorkBonusWithoutDeliv == null) return false;
			else
				if ( ! inObject.paysWorkBonusWithoutDeliv.equals(obj.paysWorkBonusWithoutDeliv)){
					return false;
				}

		if(inObject.paysWorkSurchargeWithoutDeliv == null && obj.paysWorkSurchargeWithoutDeliv == null){}
		else
			if(inObject.paysWorkSurchargeWithoutDeliv == null || obj.paysWorkSurchargeWithoutDeliv == null) return false;
			else
				if ( ! inObject.paysWorkSurchargeWithoutDeliv.equals(obj.paysWorkSurchargeWithoutDeliv)){
					return false;
				}

		if(inObject.paysWorkPremiumWithoutDeliv == null && obj.paysWorkPremiumWithoutDeliv == null){}
		else
			if(inObject.paysWorkPremiumWithoutDeliv == null || obj.paysWorkPremiumWithoutDeliv == null) return false;
			else
				if ( ! inObject.paysWorkPremiumWithoutDeliv.equals(obj.paysWorkPremiumWithoutDeliv)){
					return false;
				}

		if(inObject.paysWorkAdditionalWithoutDeliv == null && obj.paysWorkAdditionalWithoutDeliv == null){}
		else
			if(inObject.paysWorkAdditionalWithoutDeliv == null || obj.paysWorkAdditionalWithoutDeliv == null) return false;
			else
				if ( ! inObject.paysWorkAdditionalWithoutDeliv.equals(obj.paysWorkAdditionalWithoutDeliv)){
					return false;
				}

		if(inObject.chargeSumWithoutDeliv == null && obj.chargeSumWithoutDeliv == null){}
		else
			if(inObject.chargeSumWithoutDeliv == null || obj.chargeSumWithoutDeliv == null) return false;
			else
				if ( ! inObject.chargeSumWithoutDeliv.equals(obj.chargeSumWithoutDeliv)){
					return false;
				}
		if (inObject.humenKindRef.code != obj.humenKindRef.code){
			return false;
		}
		if (inObject.plaItemRef.code != obj.plaItemRef.code){
			return false;
		}
		if (inObject.classificationTypeRef.code != obj.classificationTypeRef.code){
			return false;
		}
		if (inObject.chargeRef.code != obj.chargeRef.code){
			return false;
		}
		if (inObject.positionRef.code != obj.positionRef.code){
			return false;
		}
		if (inObject.transport.code != obj.transport.code){
			return false;
		}
		return true;
	}

	public int add(ENPlanWorkItem2Humen anObject) throws PersistenceException {
		return add(anObject,true);
	}

	public int add(ENPlanWorkItem2Humen anObject, boolean aUseSequential) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		anObject.modify_time = System.currentTimeMillis();
		if(aUseSequential) {
			_collectAutoIncrementFields(anObject,connection);
		}


		selectStr = "INSERT INTO ENPLANWORKITEM2HUMEN (CODE,OREDERNUM,TABNUMBER,FIO,SALARY,TIMEMONTH,DAYSMONTH,SALARYHOURS,TIMEWORK,TIMEOBJECTWORK,TIMEWORKFACT,TIMEDELIVERY,PAYSWORK,BONUS,SALARYHOURSBONUS,PAYSWORKBONUS,CHARGEPERCENT,CHARGESUM,BALANS,PODRCOD,COSTDELIVERY,GENERALEXPENSES,ADMINISTRATIVEEXPENSES,CEHID,PERCENTSURCHARGE,SALARYHOURSSURCHARGE,PAYSWORKSURCHARGE,PERCENTPREMIUM,SALARYHOURSPREMIUM,PAYSWORKPREMIUM,PERCENTADDITIONAL,SALARYHOURSADDITIONAL,PAYSWORKADDITIONAL,PAYSWORKWITHALLSURCHRG,PSWRKWTHLLSRCHRGWTHTDL,PAYSWORKBONUSWITHOTDLV,PAYSWORKSURCHRGWTHTDLV,PAYSWORKPREMIUMWTHTDLV,PAYSWORKADDITNLWTHTDLV,CHARGESUMWITHOUTDELIV,MODIFY_TIME,HUMENKINDREFCODE,PLAITEMREFCODE,CLASSIFICATIONTYPERFCD,CHARGEREFCODE,POSITIONREFCODE,TRANSPORTCODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			statement = connection.prepareStatement(selectStr);
			if (anObject.code != Integer.MIN_VALUE ) {
				statement.setInt(1, anObject.code);
			} else {
				statement.setNull(1, java.sql.Types.INTEGER);
			}
			if (anObject.orederNum != Integer.MIN_VALUE ) {
				statement.setInt(2, anObject.orederNum);
			} else {
				statement.setNull(2, java.sql.Types.INTEGER);
			}
			statement.setString(3, anObject.tabNumber);
			statement.setString(4, anObject.fio);
			if (anObject.salary != null) {
				anObject.salary = anObject.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(5, anObject.salary);
			if (anObject.timeMonth != null) {
				anObject.timeMonth = anObject.timeMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(6, anObject.timeMonth);
			if (anObject.daysMonth != null) {
				anObject.daysMonth = anObject.daysMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(7, anObject.daysMonth);
			if (anObject.salaryHours != null) {
				anObject.salaryHours = anObject.salaryHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(8, anObject.salaryHours);
			if (anObject.timeWork != null) {
				anObject.timeWork = anObject.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(9, anObject.timeWork);
			if (anObject.timeObjectWork != null) {
				anObject.timeObjectWork = anObject.timeObjectWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(10, anObject.timeObjectWork);
			if (anObject.timeWorkFact != null) {
				anObject.timeWorkFact = anObject.timeWorkFact.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(11, anObject.timeWorkFact);
			if (anObject.timeDelivery != null) {
				anObject.timeDelivery = anObject.timeDelivery.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(12, anObject.timeDelivery);
			if (anObject.paysWork != null) {
				anObject.paysWork = anObject.paysWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(13, anObject.paysWork);
			if (anObject.bonus != null) {
				anObject.bonus = anObject.bonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(14, anObject.bonus);
			if (anObject.salaryHoursBonus != null) {
				anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(15, anObject.salaryHoursBonus);
			if (anObject.paysWorkBonus != null) {
				anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(16, anObject.paysWorkBonus);
			if (anObject.chargePercent != null) {
				anObject.chargePercent = anObject.chargePercent.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(17, anObject.chargePercent);
			if (anObject.chargeSum != null) {
				anObject.chargeSum = anObject.chargeSum.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(18, anObject.chargeSum);
			statement.setString(19, anObject.balans);
			statement.setString(20, anObject.podrcod);
			if (anObject.costDelivery != null) {
				anObject.costDelivery = anObject.costDelivery.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(21, anObject.costDelivery);
			if (anObject.generalExpenses != null) {
				anObject.generalExpenses = anObject.generalExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(22, anObject.generalExpenses);
			if (anObject.administrativeExpenses != null) {
				anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(23, anObject.administrativeExpenses);
			statement.setString(24, anObject.cehId);
			if (anObject.percentSurcharge != null) {
				anObject.percentSurcharge = anObject.percentSurcharge.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(25, anObject.percentSurcharge);
			if (anObject.salaryHoursSurcharge != null) {
				anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(26, anObject.salaryHoursSurcharge);
			if (anObject.paysWorkSurcharge != null) {
				anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(27, anObject.paysWorkSurcharge);
			if (anObject.percentPremium != null) {
				anObject.percentPremium = anObject.percentPremium.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(28, anObject.percentPremium);
			if (anObject.salaryHoursPremium != null) {
				anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(29, anObject.salaryHoursPremium);
			if (anObject.paysWorkPremium != null) {
				anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(30, anObject.paysWorkPremium);
			if (anObject.percentAdditional != null) {
				anObject.percentAdditional = anObject.percentAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(31, anObject.percentAdditional);
			if (anObject.salaryHoursAdditional != null) {
				anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(32, anObject.salaryHoursAdditional);
			if (anObject.paysWorkAdditional != null) {
				anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(33, anObject.paysWorkAdditional);
			if (anObject.paysWorkWithAllSurcharge != null) {
				anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(34, anObject.paysWorkWithAllSurcharge);
			if (anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
				anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(35, anObject.paysWorkWithAllSurchargeWithoutDeliv);
			if (anObject.paysWorkBonusWithoutDeliv != null) {
				anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(36, anObject.paysWorkBonusWithoutDeliv);
			if (anObject.paysWorkSurchargeWithoutDeliv != null) {
				anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(37, anObject.paysWorkSurchargeWithoutDeliv);
			if (anObject.paysWorkPremiumWithoutDeliv != null) {
				anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(38, anObject.paysWorkPremiumWithoutDeliv);
			if (anObject.paysWorkAdditionalWithoutDeliv != null) {
				anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(39, anObject.paysWorkAdditionalWithoutDeliv);
			if (anObject.chargeSumWithoutDeliv != null) {
				anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(40, anObject.chargeSumWithoutDeliv);
			if (anObject.modify_time == Long.MIN_VALUE) {
				statement.setBigDecimal(41, null);
			} else {
				statement.setBigDecimal(41, new java.math.BigDecimal(anObject.modify_time));
			}
			if (anObject.humenKindRef.code != Integer.MIN_VALUE){
				statement.setInt(42,anObject.humenKindRef.code);
			} else {
				statement.setNull(42,java.sql.Types.INTEGER);
			}
			if (anObject.plaItemRef.code != Integer.MIN_VALUE){
				statement.setInt(43,anObject.plaItemRef.code);
			} else {
				statement.setNull(43,java.sql.Types.INTEGER);
			}
			if (anObject.classificationTypeRef.code != Integer.MIN_VALUE){
				statement.setInt(44,anObject.classificationTypeRef.code);
			} else {
				statement.setNull(44,java.sql.Types.INTEGER);
			}
			if (anObject.chargeRef.code != Integer.MIN_VALUE){
				statement.setInt(45,anObject.chargeRef.code);
			} else {
				statement.setNull(45,java.sql.Types.INTEGER);
			}
			if (anObject.positionRef.code != Integer.MIN_VALUE){
				statement.setInt(46,anObject.positionRef.code);
			} else {
				statement.setNull(46,java.sql.Types.INTEGER);
			}
			if (anObject.transport.code != Integer.MIN_VALUE){
				statement.setInt(47,anObject.transport.code);
			} else {
				statement.setNull(47,java.sql.Types.INTEGER);
			}

			statement.execute();


			return anObject.code;

		} catch(SQLException e) {
			_sequenceTable.clear();
			throw new SystemException(e.getMessage(), e);
		} catch(Exception e) {
			_sequenceTable.clear();
			throw new PersistenceException("Error in method {%ENPlanWorkItem2HumenDAOGen.add%}",e);
		} finally {
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}

	public void save(ENPlanWorkItem2Humen anObject) throws PersistenceException {
		save(anObject,null);
	}

	public void save(ENPlanWorkItem2Humen anObject,String[] anAttributes) throws PersistenceException {
		if(anAttributes != null && anAttributes.length == 0) {
			return;
		}
		Connection connection = getConnection();
		try {
			PreparedStatement statement = null;

			ENPlanWorkItem2Humen oldObject = new ENPlanWorkItem2Humen();
			oldObject.code = anObject.code;
			String oldObjectSelectStr = "SELECT "+ENPlanWorkItem2Humen.modify_time_Field+" FROM  ENPLANWORKITEM2HUMEN WHERE CODE = ?";
			ResultSet set = null;
			try {
				statement = connection.prepareStatement(oldObjectSelectStr);
				statement.setInt(1,oldObject.code);
				set = statement.executeQuery();
				if(!set.next()) {
					throw new PersistenceException("Can't get old object.");
				}
				oldObject.modify_time = set.getLong(1);
				if(set.wasNull()) {
					oldObject.modify_time = Long.MIN_VALUE;
				}
			} catch(SQLException e) {
				System.out.println(e.getMessage()+"\nstatement - "+oldObjectSelectStr);
				throw new SystemException(e.getMessage(), e);
			} finally {
				try {if (set != null) set.close();} catch (SQLException e) {}
				try {if (statement != null) statement.close();} catch (SQLException e) {}
				statement = null;
			}
			if(oldObject.modify_time != anObject.modify_time) {
				throw new PersistenceException("Can't update object (optimistic locking).");
			}
			anObject.modify_time = System.currentTimeMillis();

			String selectStr;

			Vector<String> fields = null;
			if(anAttributes != null) {
				String fieldNameStr;
				fields = new Vector<String>();
				for(int attrIndex = 0;attrIndex < anAttributes.length;attrIndex++) {
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("OREDERNUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TABNUMBER") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("FIO") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARY") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TIMEMONTH") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DAYSMONTH") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARYHOURS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TIMEWORK") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TIMEOBJECTWORK") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TIMEWORKFACT") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TIMEDELIVERY") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORK") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("BONUS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARYHOURSBONUS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKBONUS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CHARGEPERCENT") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CHARGESUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("BALANS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PODRCOD") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("COSTDELIVERY") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("GENERALEXPENSES") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("ADMINISTRATIVEEXPENSES") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CEHID") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PERCENTSURCHARGE") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARYHOURSSURCHARGE") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKSURCHARGE") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PERCENTPREMIUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARYHOURSPREMIUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKPREMIUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PERCENTADDITIONAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SALARYHOURSADDITIONAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKADDITIONAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKWITHALLSURCHARGE") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKWITHALLSURCHARGEWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKBONUSWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKSURCHARGEWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKPREMIUMWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PAYSWORKADDITIONALWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CHARGESUMWITHOUTDELIV") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("MODIFY_TIME") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("HUMENKINDREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PLAITEMREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CLASSIFICATIONTYPEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CHARGEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("POSITIONREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TRANSPORT") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
				}
				if(fields.size() == 0) {
					return;
				}
			}
			if(fields == null) {
				selectStr = "UPDATE ENPLANWORKITEM2HUMEN SET  OREDERNUM = ? , TABNUMBER = ? , FIO = ? , SALARY = ? , TIMEMONTH = ? , DAYSMONTH = ? , SALARYHOURS = ? , TIMEWORK = ? , TIMEOBJECTWORK = ? , TIMEWORKFACT = ? , TIMEDELIVERY = ? , PAYSWORK = ? , BONUS = ? , SALARYHOURSBONUS = ? , PAYSWORKBONUS = ? , CHARGEPERCENT = ? , CHARGESUM = ? , BALANS = ? , PODRCOD = ? , COSTDELIVERY = ? , GENERALEXPENSES = ? , ADMINISTRATIVEEXPENSES = ? , CEHID = ? , PERCENTSURCHARGE = ? , SALARYHOURSSURCHARGE = ? , PAYSWORKSURCHARGE = ? , PERCENTPREMIUM = ? , SALARYHOURSPREMIUM = ? , PAYSWORKPREMIUM = ? , PERCENTADDITIONAL = ? , SALARYHOURSADDITIONAL = ? , PAYSWORKADDITIONAL = ? , PAYSWORKWITHALLSURCHRG = ? , PSWRKWTHLLSRCHRGWTHTDL = ? , PAYSWORKBONUSWITHOTDLV = ? , PAYSWORKSURCHRGWTHTDLV = ? , PAYSWORKPREMIUMWTHTDLV = ? , PAYSWORKADDITNLWTHTDLV = ? , CHARGESUMWITHOUTDELIV = ? , MODIFY_TIME = ? , HUMENKINDREFCODE = ? , PLAITEMREFCODE = ? , CLASSIFICATIONTYPERFCD = ? , CHARGEREFCODE = ? , POSITIONREFCODE = ? , TRANSPORTCODE = ?  "
					+" WHERE CODE = ?";
			} else {
				selectStr = "UPDATE ENPLANWORKITEM2HUMEN SET ";
				for(int fldIndex = 0;fldIndex < fields.size();fldIndex++) {
					selectStr+=(String)fields.get(fldIndex);
					if(fldIndex > 0) {
						selectStr+=",";
					}
				}
				selectStr += " WHERE CODE = ?";
			}

			statement = null;
			try {
				statement = connection.prepareStatement(selectStr);
				if(fields == null) {
					if (anObject.orederNum != Integer.MIN_VALUE) {
						statement.setInt(1, anObject.orederNum);
					} else {
						statement.setNull(1, java.sql.Types.INTEGER);
					}
					statement.setString(2, anObject.tabNumber);
					statement.setString(3, anObject.fio);
					if (anObject.salary != null) {
						anObject.salary = anObject.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(4, anObject.salary);
					if (anObject.timeMonth != null) {
						anObject.timeMonth = anObject.timeMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(5, anObject.timeMonth);
					if (anObject.daysMonth != null) {
						anObject.daysMonth = anObject.daysMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(6, anObject.daysMonth);
					if (anObject.salaryHours != null) {
						anObject.salaryHours = anObject.salaryHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(7, anObject.salaryHours);
					if (anObject.timeWork != null) {
						anObject.timeWork = anObject.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(8, anObject.timeWork);
					if (anObject.timeObjectWork != null) {
						anObject.timeObjectWork = anObject.timeObjectWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(9, anObject.timeObjectWork);
					if (anObject.timeWorkFact != null) {
						anObject.timeWorkFact = anObject.timeWorkFact.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(10, anObject.timeWorkFact);
					if (anObject.timeDelivery != null) {
						anObject.timeDelivery = anObject.timeDelivery.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(11, anObject.timeDelivery);
					if (anObject.paysWork != null) {
						anObject.paysWork = anObject.paysWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(12, anObject.paysWork);
					if (anObject.bonus != null) {
						anObject.bonus = anObject.bonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(13, anObject.bonus);
					if (anObject.salaryHoursBonus != null) {
						anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(14, anObject.salaryHoursBonus);
					if (anObject.paysWorkBonus != null) {
						anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(15, anObject.paysWorkBonus);
					if (anObject.chargePercent != null) {
						anObject.chargePercent = anObject.chargePercent.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(16, anObject.chargePercent);
					if (anObject.chargeSum != null) {
						anObject.chargeSum = anObject.chargeSum.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(17, anObject.chargeSum);
					statement.setString(18, anObject.balans);
					statement.setString(19, anObject.podrcod);
					if (anObject.costDelivery != null) {
						anObject.costDelivery = anObject.costDelivery.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(20, anObject.costDelivery);
					if (anObject.generalExpenses != null) {
						anObject.generalExpenses = anObject.generalExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(21, anObject.generalExpenses);
					if (anObject.administrativeExpenses != null) {
						anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(22, anObject.administrativeExpenses);
					statement.setString(23, anObject.cehId);
					if (anObject.percentSurcharge != null) {
						anObject.percentSurcharge = anObject.percentSurcharge.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(24, anObject.percentSurcharge);
					if (anObject.salaryHoursSurcharge != null) {
						anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(25, anObject.salaryHoursSurcharge);
					if (anObject.paysWorkSurcharge != null) {
						anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(26, anObject.paysWorkSurcharge);
					if (anObject.percentPremium != null) {
						anObject.percentPremium = anObject.percentPremium.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(27, anObject.percentPremium);
					if (anObject.salaryHoursPremium != null) {
						anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(28, anObject.salaryHoursPremium);
					if (anObject.paysWorkPremium != null) {
						anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(29, anObject.paysWorkPremium);
					if (anObject.percentAdditional != null) {
						anObject.percentAdditional = anObject.percentAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(30, anObject.percentAdditional);
					if (anObject.salaryHoursAdditional != null) {
						anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(31, anObject.salaryHoursAdditional);
					if (anObject.paysWorkAdditional != null) {
						anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(32, anObject.paysWorkAdditional);
					if (anObject.paysWorkWithAllSurcharge != null) {
						anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(33, anObject.paysWorkWithAllSurcharge);
					if (anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
						anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(34, anObject.paysWorkWithAllSurchargeWithoutDeliv);
					if (anObject.paysWorkBonusWithoutDeliv != null) {
						anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(35, anObject.paysWorkBonusWithoutDeliv);
					if (anObject.paysWorkSurchargeWithoutDeliv != null) {
						anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(36, anObject.paysWorkSurchargeWithoutDeliv);
					if (anObject.paysWorkPremiumWithoutDeliv != null) {
						anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(37, anObject.paysWorkPremiumWithoutDeliv);
					if (anObject.paysWorkAdditionalWithoutDeliv != null) {
						anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(38, anObject.paysWorkAdditionalWithoutDeliv);
					if (anObject.chargeSumWithoutDeliv != null) {
						anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(39, anObject.chargeSumWithoutDeliv);
					if (anObject.modify_time == Long.MIN_VALUE) {
						statement.setBigDecimal(40, null);
					} else {
						statement.setBigDecimal(40, new java.math.BigDecimal(anObject.modify_time));
					}
					if (anObject.humenKindRef.code != Integer.MIN_VALUE) {
						statement.setInt(41, anObject.humenKindRef.code);
					} else {
						statement.setNull(41, java.sql.Types.INTEGER);
					}
					if (anObject.plaItemRef.code != Integer.MIN_VALUE) {
						statement.setInt(42, anObject.plaItemRef.code);
					} else {
						statement.setNull(42, java.sql.Types.INTEGER);
					}
					if (anObject.classificationTypeRef.code != Integer.MIN_VALUE) {
						statement.setInt(43, anObject.classificationTypeRef.code);
					} else {
						statement.setNull(43, java.sql.Types.INTEGER);
					}
					if (anObject.chargeRef.code != Integer.MIN_VALUE) {
						statement.setInt(44, anObject.chargeRef.code);
					} else {
						statement.setNull(44, java.sql.Types.INTEGER);
					}
					if (anObject.positionRef.code != Integer.MIN_VALUE) {
						statement.setInt(45, anObject.positionRef.code);
					} else {
						statement.setNull(45, java.sql.Types.INTEGER);
					}
					if (anObject.transport.code != Integer.MIN_VALUE) {
						statement.setInt(46, anObject.transport.code);
					} else {
						statement.setNull(46, java.sql.Types.INTEGER);
					}
					statement.setInt(47, anObject.code);
				} else {
					for(int i = 0;i < fields.size();i++) {
						if("OREDERNUM".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.orederNum);
							continue;
						}
						if("TABNUMBER".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.tabNumber);
							continue;
						}
						if("FIO".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.fio);
							continue;
						}
						if("SALARY".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salary != null) {
								anObject.salary = anObject.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salary);
							continue;
						}
						if("TIMEMONTH".compareTo((String)fields.get(i)) == 0) {
							if (anObject.timeMonth != null) {
								anObject.timeMonth = anObject.timeMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.timeMonth);
							continue;
						}
						if("DAYSMONTH".compareTo((String)fields.get(i)) == 0) {
							if (anObject.daysMonth != null) {
								anObject.daysMonth = anObject.daysMonth.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.daysMonth);
							continue;
						}
						if("SALARYHOURS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salaryHours != null) {
								anObject.salaryHours = anObject.salaryHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salaryHours);
							continue;
						}
						if("TIMEWORK".compareTo((String)fields.get(i)) == 0) {
							if (anObject.timeWork != null) {
								anObject.timeWork = anObject.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.timeWork);
							continue;
						}
						if("TIMEOBJECTWORK".compareTo((String)fields.get(i)) == 0) {
							if (anObject.timeObjectWork != null) {
								anObject.timeObjectWork = anObject.timeObjectWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.timeObjectWork);
							continue;
						}
						if("TIMEWORKFACT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.timeWorkFact != null) {
								anObject.timeWorkFact = anObject.timeWorkFact.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.timeWorkFact);
							continue;
						}
						if("TIMEDELIVERY".compareTo((String)fields.get(i)) == 0) {
							if (anObject.timeDelivery != null) {
								anObject.timeDelivery = anObject.timeDelivery.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.timeDelivery);
							continue;
						}
						if("PAYSWORK".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWork != null) {
								anObject.paysWork = anObject.paysWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWork);
							continue;
						}
						if("BONUS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.bonus != null) {
								anObject.bonus = anObject.bonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.bonus);
							continue;
						}
						if("SALARYHOURSBONUS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salaryHoursBonus != null) {
								anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salaryHoursBonus);
							continue;
						}
						if("PAYSWORKBONUS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkBonus != null) {
								anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkBonus);
							continue;
						}
						if("CHARGEPERCENT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.chargePercent != null) {
								anObject.chargePercent = anObject.chargePercent.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.chargePercent);
							continue;
						}
						if("CHARGESUM".compareTo((String)fields.get(i)) == 0) {
							if (anObject.chargeSum != null) {
								anObject.chargeSum = anObject.chargeSum.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.chargeSum);
							continue;
						}
						if("BALANS".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.balans);
							continue;
						}
						if("PODRCOD".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.podrcod);
							continue;
						}
						if("COSTDELIVERY".compareTo((String)fields.get(i)) == 0) {
							if (anObject.costDelivery != null) {
								anObject.costDelivery = anObject.costDelivery.setScale(6, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.costDelivery);
							continue;
						}
						if("GENERALEXPENSES".compareTo((String)fields.get(i)) == 0) {
							if (anObject.generalExpenses != null) {
								anObject.generalExpenses = anObject.generalExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.generalExpenses);
							continue;
						}
						if("ADMINISTRATIVEEXPENSES".compareTo((String)fields.get(i)) == 0) {
							if (anObject.administrativeExpenses != null) {
								anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(4, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.administrativeExpenses);
							continue;
						}
						if("CEHID".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.cehId);
							continue;
						}
						if("PERCENTSURCHARGE".compareTo((String)fields.get(i)) == 0) {
							if (anObject.percentSurcharge != null) {
								anObject.percentSurcharge = anObject.percentSurcharge.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.percentSurcharge);
							continue;
						}
						if("SALARYHOURSSURCHARGE".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salaryHoursSurcharge != null) {
								anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salaryHoursSurcharge);
							continue;
						}
						if("PAYSWORKSURCHARGE".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkSurcharge != null) {
								anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkSurcharge);
							continue;
						}
						if("PERCENTPREMIUM".compareTo((String)fields.get(i)) == 0) {
							if (anObject.percentPremium != null) {
								anObject.percentPremium = anObject.percentPremium.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.percentPremium);
							continue;
						}
						if("SALARYHOURSPREMIUM".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salaryHoursPremium != null) {
								anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salaryHoursPremium);
							continue;
						}
						if("PAYSWORKPREMIUM".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkPremium != null) {
								anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkPremium);
							continue;
						}
						if("PERCENTADDITIONAL".compareTo((String)fields.get(i)) == 0) {
							if (anObject.percentAdditional != null) {
								anObject.percentAdditional = anObject.percentAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.percentAdditional);
							continue;
						}
						if("SALARYHOURSADDITIONAL".compareTo((String)fields.get(i)) == 0) {
							if (anObject.salaryHoursAdditional != null) {
								anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.salaryHoursAdditional);
							continue;
						}
						if("PAYSWORKADDITIONAL".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkAdditional != null) {
								anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkAdditional);
							continue;
						}
						if("PAYSWORKWITHALLSURCHARGE".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkWithAllSurcharge != null) {
								anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkWithAllSurcharge);
							continue;
						}
						if("PAYSWORKWITHALLSURCHARGEWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
								anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkWithAllSurchargeWithoutDeliv);
							continue;
						}
						if("PAYSWORKBONUSWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkBonusWithoutDeliv != null) {
								anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkBonusWithoutDeliv);
							continue;
						}
						if("PAYSWORKSURCHARGEWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkSurchargeWithoutDeliv != null) {
								anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkSurchargeWithoutDeliv);
							continue;
						}
						if("PAYSWORKPREMIUMWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkPremiumWithoutDeliv != null) {
								anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkPremiumWithoutDeliv);
							continue;
						}
						if("PAYSWORKADDITIONALWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.paysWorkAdditionalWithoutDeliv != null) {
								anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.paysWorkAdditionalWithoutDeliv);
							continue;
						}
						if("CHARGESUMWITHOUTDELIV".compareTo((String)fields.get(i)) == 0) {
							if (anObject.chargeSumWithoutDeliv != null) {
								anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.chargeSumWithoutDeliv);
							continue;
						}
						if("MODIFY_TIME".compareTo((String)fields.get(i)) == 0) {
							if (anObject.modify_time == Long.MIN_VALUE) {
								statement.setBigDecimal(i+1, null);
							} else {
								statement.setBigDecimal(i+1, new java.math.BigDecimal(anObject.modify_time));
							}
							continue;
						}
						if("HUMENKINDREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.humenKindRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.humenKindRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("PLAITEMREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.plaItemRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.plaItemRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("CLASSIFICATIONTYPEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.classificationTypeRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.classificationTypeRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("CHARGEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.chargeRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.chargeRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("POSITIONREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.positionRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.positionRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("TRANSPORT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.transport.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.transport.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
					}
					statement.setInt(fields.size(), anObject.code);
				}

				statement.execute();


			} catch(SQLException e) {
				System.out.println(e.getMessage()+"\nstatement - "+selectStr);
				throw new SystemException(e.getMessage(), e);
			} finally {
				try {if (statement != null) statement.close();} catch (SQLException e) {}
			}
		} finally {
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}

	} // end of save(ENPlanWorkItem2Humen anObject,String[] anAttributes)


	public ENPlanWorkItem2HumenShort getShortObject(int anObjectCode) throws PersistenceException {
		ENPlanWorkItem2Humen filterObject = new ENPlanWorkItem2Humen();
		Vector<ENPlanWorkItem2HumenShort> list;
		filterObject.code = anObjectCode;
		list = getFilteredList(filterObject).list;
		if(list.size() > 0) {
			return (ENPlanWorkItem2HumenShort)list.get(0);
		}
		return null;
	}
	
	public int setParameters(ENPlanWorkItem2Humen filter, PreparedStatement statement) throws SQLException {
		int index = 0;
		if(filter != null) {
			index++;
			index = BaseDAOUtils.setIntParameter(filter.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.orederNum, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.tabNumber, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.fio, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salary, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.timeMonth, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.daysMonth, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salaryHours, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.timeWork, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.timeObjectWork, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.timeWorkFact, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.timeDelivery, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWork, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.bonus, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salaryHoursBonus, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkBonus, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.chargePercent, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.chargeSum, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.balans, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.podrcod, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.costDelivery, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.generalExpenses, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.administrativeExpenses, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.cehId, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.percentSurcharge, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salaryHoursSurcharge, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkSurcharge, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.percentPremium, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salaryHoursPremium, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkPremium, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.percentAdditional, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.salaryHoursAdditional, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkAdditional, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkWithAllSurcharge, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkWithAllSurchargeWithoutDeliv, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkBonusWithoutDeliv, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkSurchargeWithoutDeliv, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkPremiumWithoutDeliv, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.paysWorkAdditionalWithoutDeliv, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.chargeSumWithoutDeliv, index, statement);
			index = BaseDAOUtils.setLongParameter(filter.modify_time, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.humenKindRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.plaItemRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.classificationTypeRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.chargeRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.positionRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.transport.code, index, statement);
			index--;
		}
		return index;
	}
	
	public String buildCondition(ENPlanWorkItem2HumenFilter filter) {
		String out = buildCondition((ENPlanWorkItem2Humen)filter);
		if(filter != null) {
			out = BaseDAOUtils.addToCondition(filter.conditionSQL, out);
		}
		return out;
	}
	public String buildCondition(ENPlanWorkItem2Humen filter) {
		String out = "";
		if(filter != null) {
			out = BaseDAOUtils.addIntToCondition(filter.code, ENPlanWorkItem2Humen.code_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.orederNum, ENPlanWorkItem2Humen.orederNum_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.tabNumber, ENPlanWorkItem2Humen.tabNumber_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.fio, ENPlanWorkItem2Humen.fio_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salary, ENPlanWorkItem2Humen.salary_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.timeMonth, ENPlanWorkItem2Humen.timeMonth_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.daysMonth, ENPlanWorkItem2Humen.daysMonth_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salaryHours, ENPlanWorkItem2Humen.salaryHours_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.timeWork, ENPlanWorkItem2Humen.timeWork_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.timeObjectWork, ENPlanWorkItem2Humen.timeObjectWork_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.timeWorkFact, ENPlanWorkItem2Humen.timeWorkFact_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.timeDelivery, ENPlanWorkItem2Humen.timeDelivery_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWork, ENPlanWorkItem2Humen.paysWork_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.bonus, ENPlanWorkItem2Humen.bonus_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salaryHoursBonus, ENPlanWorkItem2Humen.salaryHoursBonus_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkBonus, ENPlanWorkItem2Humen.paysWorkBonus_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.chargePercent, ENPlanWorkItem2Humen.chargePercent_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.chargeSum, ENPlanWorkItem2Humen.chargeSum_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.balans, ENPlanWorkItem2Humen.balans_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.podrcod, ENPlanWorkItem2Humen.podrcod_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.costDelivery, ENPlanWorkItem2Humen.costDelivery_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.generalExpenses, ENPlanWorkItem2Humen.generalExpenses_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.administrativeExpenses, ENPlanWorkItem2Humen.administrativeExpenses_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.cehId, ENPlanWorkItem2Humen.cehId_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.percentSurcharge, ENPlanWorkItem2Humen.percentSurcharge_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salaryHoursSurcharge, ENPlanWorkItem2Humen.salaryHoursSurcharge_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkSurcharge, ENPlanWorkItem2Humen.paysWorkSurcharge_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.percentPremium, ENPlanWorkItem2Humen.percentPremium_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salaryHoursPremium, ENPlanWorkItem2Humen.salaryHoursPremium_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkPremium, ENPlanWorkItem2Humen.paysWorkPremium_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.percentAdditional, ENPlanWorkItem2Humen.percentAdditional_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.salaryHoursAdditional, ENPlanWorkItem2Humen.salaryHoursAdditional_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkAdditional, ENPlanWorkItem2Humen.paysWorkAdditional_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkWithAllSurcharge, ENPlanWorkItem2Humen.paysWorkWithAllSurcharge_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkWithAllSurchargeWithoutDeliv, ENPlanWorkItem2Humen.paysWorkWithAllSurchargeWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkBonusWithoutDeliv, ENPlanWorkItem2Humen.paysWorkBonusWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkSurchargeWithoutDeliv, ENPlanWorkItem2Humen.paysWorkSurchargeWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkPremiumWithoutDeliv, ENPlanWorkItem2Humen.paysWorkPremiumWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.paysWorkAdditionalWithoutDeliv, ENPlanWorkItem2Humen.paysWorkAdditionalWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.chargeSumWithoutDeliv, ENPlanWorkItem2Humen.chargeSumWithoutDeliv_QFielld, out);
			out = BaseDAOUtils.addLongToCondition(filter.modify_time, ENPlanWorkItem2Humen.modify_time_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.humenKindRef.code, ENPlanWorkItem2Humen.humenKindRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.plaItemRef.code, ENPlanWorkItem2Humen.plaItemRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.classificationTypeRef.code, ENPlanWorkItem2Humen.classificationTypeRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.chargeRef.code, ENPlanWorkItem2Humen.chargeRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.positionRef.code, ENPlanWorkItem2Humen.positionRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.transport.code, ENPlanWorkItem2Humen.transport_QFielld, out);
		}
		return out;
	}

	public ENPlanWorkItem2HumenShortList getList(String aCondition) throws PersistenceException {
		return getScrollableFilteredList(null, aCondition, 0, -1);
	}

	public ENPlanWorkItem2HumenShortList getList() throws PersistenceException {
		return getScrollableFilteredList(null, null, 0, -1);
	}

	public ENPlanWorkItem2HumenShortList getScrollableList(int fromPosition, int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, null, fromPosition, quantity);
	}

	public ENPlanWorkItem2HumenShortList getFilteredList(ENPlanWorkItem2Humen filterObject) throws PersistenceException {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2Humen aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, null, fromPosition, quantity);
	}

	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2Humen aFilterObject, String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, anCondition, null, fromPosition, quantity, null);
	}

	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, anCondition, fromPosition, quantity);
	}

	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2HumenFilter aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
	}
	
	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2HumenFilter aFilterObject,int fromPosition,int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}
	
	public ENPlanWorkItem2HumenShortList getScrollableFilteredList(ENPlanWorkItem2Humen aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		ENPlanWorkItem2HumenShortList result = new ENPlanWorkItem2HumenShortList();
		ENPlanWorkItem2HumenShort anObject;
		result.list = new Vector<ENPlanWorkItem2HumenShort>();

		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet  set = null;
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORKITEM2HUMEN.CODE";
		}

		if(quantity < 0) {
			quantity = Integer.MAX_VALUE/2;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		selectStr = "SELECT "+
			"ENPLANWORKITEM2HUMEN.CODE"+
			",ENPLANWORKITEM2HUMEN.OREDERNUM"+
			",ENPLANWORKITEM2HUMEN.TABNUMBER"+
			",ENPLANWORKITEM2HUMEN.FIO"+
			",ENPLANWORKITEM2HUMEN.SALARY"+
			",ENPLANWORKITEM2HUMEN.TIMEMONTH"+
			",ENPLANWORKITEM2HUMEN.DAYSMONTH"+
			",ENPLANWORKITEM2HUMEN.SALARYHOURS"+
			",ENPLANWORKITEM2HUMEN.TIMEWORK"+
			",ENPLANWORKITEM2HUMEN.TIMEOBJECTWORK"+
			",ENPLANWORKITEM2HUMEN.TIMEWORKFACT"+
			",ENPLANWORKITEM2HUMEN.TIMEDELIVERY"+
			",ENPLANWORKITEM2HUMEN.PAYSWORK"+
			",ENPLANWORKITEM2HUMEN.BONUS"+
			",ENPLANWORKITEM2HUMEN.SALARYHOURSBONUS"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKBONUS"+
			",ENPLANWORKITEM2HUMEN.CHARGEPERCENT"+
			",ENPLANWORKITEM2HUMEN.CHARGESUM"+
			",ENPLANWORKITEM2HUMEN.BALANS"+
			",ENPLANWORKITEM2HUMEN.PODRCOD"+
			",ENPLANWORKITEM2HUMEN.COSTDELIVERY"+
			",ENPLANWORKITEM2HUMEN.GENERALEXPENSES"+
			",ENPLANWORKITEM2HUMEN.ADMINISTRATIVEEXPENSES"+
			",ENPLANWORKITEM2HUMEN.CEHID"+
			",ENPLANWORKITEM2HUMEN.PERCENTSURCHARGE"+
			",ENPLANWORKITEM2HUMEN.SALARYHOURSSURCHARGE"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKSURCHARGE"+
			",ENPLANWORKITEM2HUMEN.PERCENTPREMIUM"+
			",ENPLANWORKITEM2HUMEN.SALARYHOURSPREMIUM"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUM"+
			",ENPLANWORKITEM2HUMEN.PERCENTADDITIONAL"+
			",ENPLANWORKITEM2HUMEN.SALARYHOURSADDITIONAL"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKADDITIONAL"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKWITHALLSURCHRG"+
			",ENPLANWORKITEM2HUMEN.PSWRKWTHLLSRCHRGWTHTDL"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKBONUSWITHOTDLV"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKSURCHRGWTHTDLV"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUMWTHTDLV"+
			",ENPLANWORKITEM2HUMEN.PAYSWORKADDITNLWTHTDLV"+
			",ENPLANWORKITEM2HUMEN.CHARGESUMWITHOUTDELIV"+
			", ENHUMENITEMKIND.CODE " +
			", ENHUMENITEMKIND.NAME " +
			", ENPLANWORKITEM.CODE " +
			", ENPLANWORKITEM.COUNTGEN " +
			", ENPLANWORKITEM.TIMEGEN " +
			", ENPLANWORKITEM.COSTGEN " +
			", ENPLANWORKITEM.USERGEN " +
			", ENPLANWORKITEM.DATEEDIT " +
			", TKCLASSIFICATIONTYPE.CODE " +
			", TKCLASSIFICATIONTYPE.NAME " +
			", TKCLASSIFICATIONTYPE.KOD " +
			", FINCHARGETYPE.CODE " +
			", FINCHARGETYPE.NAME " +
			", TKPOSITION.CODE " +
			", TKPOSITION.NAME " +
			", TKPOSITION.SAFETYGROUP " +
			", TKPOSITION.RANK " +
			", TKPOSITION.SHORTNAME " +
			", TKTRANSPORT.CODE " +
			", TKTRANSPORT.NAME " +
		" FROM ENPLANWORKITEM2HUMEN " +
			" LEFT JOIN ENHUMENITEMKIND on ENHUMENITEMKIND.CODE = ENPLANWORKITEM2HUMEN.HUMENKINDREFCODE "+
			" LEFT JOIN ENPLANWORKITEM on ENPLANWORKITEM.CODE = ENPLANWORKITEM2HUMEN.PLAITEMREFCODE "+
			" LEFT JOIN TKCLASSIFICATIONTYPE on TKCLASSIFICATIONTYPE.CODE = ENPLANWORKITEM2HUMEN.CLASSIFICATIONTYPERFCD "+
			" LEFT JOIN FINCHARGETYPE on FINCHARGETYPE.CODE = ENPLANWORKITEM2HUMEN.CHARGEREFCODE "+
			" LEFT JOIN TKPOSITION on TKPOSITION.CODE = ENPLANWORKITEM2HUMEN.POSITIONREFCODE "+
			" LEFT JOIN TKTRANSPORT on TKTRANSPORT.CODE = ENPLANWORKITEM2HUMEN.TRANSPORTCODE "+
		"";

	
		whereStr = BaseDAOUtils.addToCondition(buildCondition(aFilterObject), whereStr);

		whereStr = BaseDAOUtils.addToCondition(condition, whereStr, true);
		if(whereStr.length() != 0) {
			selectStr += " WHERE " + whereStr;
		}
		selectStr = selectStr + " ORDER BY " + orderBy;

		selectStr = selectStr + " OFFSET " + fromPosition;
		if (quantity > -1) selectStr = selectStr + " LIMIT " + quantity;
		int number = 0;
		try {
			statement = connection.prepareStatement(selectStr);
			number = setParameters(aFilterObject, statement);
			
			if(condition.length() > 0 && aBindObjects != null) {
				_bindObjectsToPreparedStatement(statement, aBindObjects, number);
			}

			set = statement.executeQuery();
			int i;
			for (i = 0; set.next(); i++) {
				anObject = new ENPlanWorkItem2HumenShort();
				anObject.code = set.getInt(1);
				if ( set.wasNull() ) {
					anObject.code = Integer.MIN_VALUE;
				}
				anObject.orederNum = set.getInt(2);
				if ( set.wasNull() ) {
					anObject.orederNum = Integer.MIN_VALUE;
				}
				anObject.tabNumber = set.getString(3);
				anObject.fio = set.getString(4);
				anObject.salary = set.getBigDecimal(5);
				if(anObject.salary != null) {
					anObject.salary = anObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeMonth = set.getBigDecimal(6);
				if(anObject.timeMonth != null) {
					anObject.timeMonth = anObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.daysMonth = set.getBigDecimal(7);
				if(anObject.daysMonth != null) {
					anObject.daysMonth = anObject.daysMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHours = set.getBigDecimal(8);
				if(anObject.salaryHours != null) {
					anObject.salaryHours = anObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeWork = set.getBigDecimal(9);
				if(anObject.timeWork != null) {
					anObject.timeWork = anObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeObjectWork = set.getBigDecimal(10);
				if(anObject.timeObjectWork != null) {
					anObject.timeObjectWork = anObject.timeObjectWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeWorkFact = set.getBigDecimal(11);
				if(anObject.timeWorkFact != null) {
					anObject.timeWorkFact = anObject.timeWorkFact.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeDelivery = set.getBigDecimal(12);
				if(anObject.timeDelivery != null) {
					anObject.timeDelivery = anObject.timeDelivery.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWork = set.getBigDecimal(13);
				if(anObject.paysWork != null) {
					anObject.paysWork = anObject.paysWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.bonus = set.getBigDecimal(14);
				if(anObject.bonus != null) {
					anObject.bonus = anObject.bonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursBonus = set.getBigDecimal(15);
				if(anObject.salaryHoursBonus != null) {
					anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkBonus = set.getBigDecimal(16);
				if(anObject.paysWorkBonus != null) {
					anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargePercent = set.getBigDecimal(17);
				if(anObject.chargePercent != null) {
					anObject.chargePercent = anObject.chargePercent.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargeSum = set.getBigDecimal(18);
				if(anObject.chargeSum != null) {
					anObject.chargeSum = anObject.chargeSum.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.balans = set.getString(19);
				anObject.podrcod = set.getString(20);
				anObject.costDelivery = set.getBigDecimal(21);
				if(anObject.costDelivery != null) {
					anObject.costDelivery = anObject.costDelivery.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.generalExpenses = set.getBigDecimal(22);
				if(anObject.generalExpenses != null) {
					anObject.generalExpenses = anObject.generalExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.administrativeExpenses = set.getBigDecimal(23);
				if(anObject.administrativeExpenses != null) {
					anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.cehId = set.getString(24);
				anObject.percentSurcharge = set.getBigDecimal(25);
				if(anObject.percentSurcharge != null) {
					anObject.percentSurcharge = anObject.percentSurcharge.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursSurcharge = set.getBigDecimal(26);
				if(anObject.salaryHoursSurcharge != null) {
					anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkSurcharge = set.getBigDecimal(27);
				if(anObject.paysWorkSurcharge != null) {
					anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.percentPremium = set.getBigDecimal(28);
				if(anObject.percentPremium != null) {
					anObject.percentPremium = anObject.percentPremium.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursPremium = set.getBigDecimal(29);
				if(anObject.salaryHoursPremium != null) {
					anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkPremium = set.getBigDecimal(30);
				if(anObject.paysWorkPremium != null) {
					anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.percentAdditional = set.getBigDecimal(31);
				if(anObject.percentAdditional != null) {
					anObject.percentAdditional = anObject.percentAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursAdditional = set.getBigDecimal(32);
				if(anObject.salaryHoursAdditional != null) {
					anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkAdditional = set.getBigDecimal(33);
				if(anObject.paysWorkAdditional != null) {
					anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkWithAllSurcharge = set.getBigDecimal(34);
				if(anObject.paysWorkWithAllSurcharge != null) {
					anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkWithAllSurchargeWithoutDeliv = set.getBigDecimal(35);
				if(anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
					anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkBonusWithoutDeliv = set.getBigDecimal(36);
				if(anObject.paysWorkBonusWithoutDeliv != null) {
					anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkSurchargeWithoutDeliv = set.getBigDecimal(37);
				if(anObject.paysWorkSurchargeWithoutDeliv != null) {
					anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkPremiumWithoutDeliv = set.getBigDecimal(38);
				if(anObject.paysWorkPremiumWithoutDeliv != null) {
					anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkAdditionalWithoutDeliv = set.getBigDecimal(39);
				if(anObject.paysWorkAdditionalWithoutDeliv != null) {
					anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargeSumWithoutDeliv = set.getBigDecimal(40);
				if(anObject.chargeSumWithoutDeliv != null) {
					anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}

				anObject.humenKindRefCode = set.getInt(41);
				if(set.wasNull()) {
					anObject.humenKindRefCode = Integer.MIN_VALUE;
				}
				anObject.humenKindRefName = set.getString(42);
				anObject.plaItemRefCode = set.getInt(43);
				if(set.wasNull()) {
					anObject.plaItemRefCode = Integer.MIN_VALUE;
				}
				anObject.plaItemRefCountGen = set.getBigDecimal(44);
				if(anObject.plaItemRefCountGen != null) {
					anObject.plaItemRefCountGen = anObject.plaItemRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.plaItemRefTimeGen = set.getBigDecimal(45);
				if(anObject.plaItemRefTimeGen != null) {
					anObject.plaItemRefTimeGen = anObject.plaItemRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.plaItemRefCostGen = set.getBigDecimal(46);
				if(anObject.plaItemRefCostGen != null) {
					anObject.plaItemRefCostGen = anObject.plaItemRefCostGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.plaItemRefUserGen = set.getString(47);
				anObject.plaItemRefDateEdit = set.getDate(48);
				anObject.classificationTypeRefCode = set.getInt(49);
				if(set.wasNull()) {
					anObject.classificationTypeRefCode = Integer.MIN_VALUE;
				}
				anObject.classificationTypeRefName = set.getString(50);
				anObject.classificationTypeRefKod = set.getString(51);
				anObject.chargeRefCode = set.getInt(52);
				if(set.wasNull()) {
					anObject.chargeRefCode = Integer.MIN_VALUE;
				}
				anObject.chargeRefName = set.getString(53);
				anObject.positionRefCode = set.getInt(54);
				if(set.wasNull()) {
					anObject.positionRefCode = Integer.MIN_VALUE;
				}
				anObject.positionRefName = set.getString(55);
				anObject.positionRefSafetyGroup = set.getString(56);
				anObject.positionRefRank = set.getString(57);
				anObject.positionRefShortName = set.getString(58);
				anObject.transportCode = set.getInt(59);
				if(set.wasNull()) {
					anObject.transportCode = Integer.MIN_VALUE;
				}
				anObject.transportName = set.getString(60);

				result.list.add(anObject);
			}

			result.setTotalCount(i);
			return result;
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();}             catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}
	
	public int[] getFilteredCodeArray(ENPlanWorkItem2HumenFilter aFilterObject, int fromPosition, int quantity) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject, fromPosition, quantity, null);
	}

	public int[] getFilteredCodeArray(ENPlanWorkItem2HumenFilter aFilterObject, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}


	public int[] getFilteredCodeArray(ENPlanWorkItem2Humen aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		Vector<Integer> result = new Vector<Integer>();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = "SELECT ENPLANWORKITEM2HUMEN.CODE FROM ENPLANWORKITEM2HUMEN";
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORKITEM2HUMEN.CODE";
		}
		if(quantity < 0) {
			quantity = Integer.MAX_VALUE/2;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		
		whereStr = BaseDAOUtils.addToCondition(buildCondition(aFilterObject), whereStr);
		
		whereStr = BaseDAOUtils.addToCondition(condition, whereStr, true);

		if(whereStr.length() != 0) {
			selectStr = selectStr + " WHERE " + whereStr;
		}

		selectStr = selectStr + " ORDER BY " + orderBy;
		selectStr = selectStr + " OFFSET " + fromPosition;
		if (quantity > -1) selectStr = selectStr + " LIMIT " + quantity;

		try {
			statement = connection.prepareStatement(selectStr);
			int number = setParameters(aFilterObject, statement);

			if(condition.length() > 0 && aBindObjects != null) {
				_bindObjectsToPreparedStatement(statement,aBindObjects,number);
			}

			set = statement.executeQuery();
			int i;
			for(i = 0;set.next();i++) {
				/*if(i < fromPosition) {
					continue;
				} else if(i >= fromPosition + quantity) {
					i++;
					break;
				}*/
				result.add(set.getInt(1));
			}

			int[] array = new int[result.size()];
			for(int j = 0;j < result.size();j++) {
				array[j] = result.get(j);
			}
			return array;
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();} catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	} // end of getFilteredCodeArray


	public ENPlanWorkItem2Humen getObject(int uid) throws PersistenceException {
		ENPlanWorkItem2Humen result = new ENPlanWorkItem2Humen();
		result.code = uid;
		loadObject(result);
		if(result.code == Integer.MIN_VALUE) {
			return null;
		}
		return result;
	}


	public void loadObject(ENPlanWorkItem2Humen anObject) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;


		selectStr = "SELECT  ENPLANWORKITEM2HUMEN.CODE, ENPLANWORKITEM2HUMEN.OREDERNUM, ENPLANWORKITEM2HUMEN.TABNUMBER, ENPLANWORKITEM2HUMEN.FIO, ENPLANWORKITEM2HUMEN.SALARY, ENPLANWORKITEM2HUMEN.TIMEMONTH, ENPLANWORKITEM2HUMEN.DAYSMONTH, ENPLANWORKITEM2HUMEN.SALARYHOURS, ENPLANWORKITEM2HUMEN.TIMEWORK, ENPLANWORKITEM2HUMEN.TIMEOBJECTWORK, ENPLANWORKITEM2HUMEN.TIMEWORKFACT, ENPLANWORKITEM2HUMEN.TIMEDELIVERY, ENPLANWORKITEM2HUMEN.PAYSWORK, ENPLANWORKITEM2HUMEN.BONUS, ENPLANWORKITEM2HUMEN.SALARYHOURSBONUS, ENPLANWORKITEM2HUMEN.PAYSWORKBONUS, ENPLANWORKITEM2HUMEN.CHARGEPERCENT, ENPLANWORKITEM2HUMEN.CHARGESUM, ENPLANWORKITEM2HUMEN.BALANS, ENPLANWORKITEM2HUMEN.PODRCOD, ENPLANWORKITEM2HUMEN.COSTDELIVERY, ENPLANWORKITEM2HUMEN.GENERALEXPENSES, ENPLANWORKITEM2HUMEN.ADMINISTRATIVEEXPENSES, ENPLANWORKITEM2HUMEN.CEHID, ENPLANWORKITEM2HUMEN.PERCENTSURCHARGE, ENPLANWORKITEM2HUMEN.SALARYHOURSSURCHARGE, ENPLANWORKITEM2HUMEN.PAYSWORKSURCHARGE, ENPLANWORKITEM2HUMEN.PERCENTPREMIUM, ENPLANWORKITEM2HUMEN.SALARYHOURSPREMIUM, ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUM, ENPLANWORKITEM2HUMEN.PERCENTADDITIONAL, ENPLANWORKITEM2HUMEN.SALARYHOURSADDITIONAL, ENPLANWORKITEM2HUMEN.PAYSWORKADDITIONAL, ENPLANWORKITEM2HUMEN.PAYSWORKWITHALLSURCHRG, ENPLANWORKITEM2HUMEN.PSWRKWTHLLSRCHRGWTHTDL, ENPLANWORKITEM2HUMEN.PAYSWORKBONUSWITHOTDLV, ENPLANWORKITEM2HUMEN.PAYSWORKSURCHRGWTHTDLV, ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUMWTHTDLV, ENPLANWORKITEM2HUMEN.PAYSWORKADDITNLWTHTDLV, ENPLANWORKITEM2HUMEN.CHARGESUMWITHOUTDELIV, ENPLANWORKITEM2HUMEN.MODIFY_TIME, ENPLANWORKITEM2HUMEN.HUMENKINDREFCODE, ENPLANWORKITEM2HUMEN.PLAITEMREFCODE, ENPLANWORKITEM2HUMEN.CLASSIFICATIONTYPERFCD, ENPLANWORKITEM2HUMEN.CHARGEREFCODE, ENPLANWORKITEM2HUMEN.POSITIONREFCODE, ENPLANWORKITEM2HUMEN.TRANSPORTCODE "
			+" FROM ENPLANWORKITEM2HUMEN WHERE ENPLANWORKITEM2HUMEN.CODE = ?";


		try {
			statement = connection.prepareStatement(selectStr);
			statement.setInt(1,anObject.code);
			set = statement.executeQuery();
			if(!set.next()) {
				anObject.code = Integer.MIN_VALUE;
			} else {
				anObject.code = set.getInt(1);
			
				anObject.orederNum = set.getInt(2);
				if (set.wasNull()) {
					anObject.orederNum = Integer.MIN_VALUE;
				}
				anObject.tabNumber = set.getString(3);
				anObject.fio = set.getString(4);
				anObject.salary = set.getBigDecimal(5);
				if(anObject.salary != null) {
					anObject.salary = anObject.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeMonth = set.getBigDecimal(6);
				if(anObject.timeMonth != null) {
					anObject.timeMonth = anObject.timeMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.daysMonth = set.getBigDecimal(7);
				if(anObject.daysMonth != null) {
					anObject.daysMonth = anObject.daysMonth.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHours = set.getBigDecimal(8);
				if(anObject.salaryHours != null) {
					anObject.salaryHours = anObject.salaryHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeWork = set.getBigDecimal(9);
				if(anObject.timeWork != null) {
					anObject.timeWork = anObject.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeObjectWork = set.getBigDecimal(10);
				if(anObject.timeObjectWork != null) {
					anObject.timeObjectWork = anObject.timeObjectWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeWorkFact = set.getBigDecimal(11);
				if(anObject.timeWorkFact != null) {
					anObject.timeWorkFact = anObject.timeWorkFact.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.timeDelivery = set.getBigDecimal(12);
				if(anObject.timeDelivery != null) {
					anObject.timeDelivery = anObject.timeDelivery.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWork = set.getBigDecimal(13);
				if(anObject.paysWork != null) {
					anObject.paysWork = anObject.paysWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.bonus = set.getBigDecimal(14);
				if(anObject.bonus != null) {
					anObject.bonus = anObject.bonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursBonus = set.getBigDecimal(15);
				if(anObject.salaryHoursBonus != null) {
					anObject.salaryHoursBonus = anObject.salaryHoursBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkBonus = set.getBigDecimal(16);
				if(anObject.paysWorkBonus != null) {
					anObject.paysWorkBonus = anObject.paysWorkBonus.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargePercent = set.getBigDecimal(17);
				if(anObject.chargePercent != null) {
					anObject.chargePercent = anObject.chargePercent.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargeSum = set.getBigDecimal(18);
				if(anObject.chargeSum != null) {
					anObject.chargeSum = anObject.chargeSum.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.balans = set.getString(19);
				anObject.podrcod = set.getString(20);
				anObject.costDelivery = set.getBigDecimal(21);
				if(anObject.costDelivery != null) {
					anObject.costDelivery = anObject.costDelivery.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.generalExpenses = set.getBigDecimal(22);
				if(anObject.generalExpenses != null) {
					anObject.generalExpenses = anObject.generalExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.administrativeExpenses = set.getBigDecimal(23);
				if(anObject.administrativeExpenses != null) {
					anObject.administrativeExpenses = anObject.administrativeExpenses.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.cehId = set.getString(24);
				anObject.percentSurcharge = set.getBigDecimal(25);
				if(anObject.percentSurcharge != null) {
					anObject.percentSurcharge = anObject.percentSurcharge.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursSurcharge = set.getBigDecimal(26);
				if(anObject.salaryHoursSurcharge != null) {
					anObject.salaryHoursSurcharge = anObject.salaryHoursSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkSurcharge = set.getBigDecimal(27);
				if(anObject.paysWorkSurcharge != null) {
					anObject.paysWorkSurcharge = anObject.paysWorkSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.percentPremium = set.getBigDecimal(28);
				if(anObject.percentPremium != null) {
					anObject.percentPremium = anObject.percentPremium.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursPremium = set.getBigDecimal(29);
				if(anObject.salaryHoursPremium != null) {
					anObject.salaryHoursPremium = anObject.salaryHoursPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkPremium = set.getBigDecimal(30);
				if(anObject.paysWorkPremium != null) {
					anObject.paysWorkPremium = anObject.paysWorkPremium.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.percentAdditional = set.getBigDecimal(31);
				if(anObject.percentAdditional != null) {
					anObject.percentAdditional = anObject.percentAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.salaryHoursAdditional = set.getBigDecimal(32);
				if(anObject.salaryHoursAdditional != null) {
					anObject.salaryHoursAdditional = anObject.salaryHoursAdditional.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkAdditional = set.getBigDecimal(33);
				if(anObject.paysWorkAdditional != null) {
					anObject.paysWorkAdditional = anObject.paysWorkAdditional.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkWithAllSurcharge = set.getBigDecimal(34);
				if(anObject.paysWorkWithAllSurcharge != null) {
					anObject.paysWorkWithAllSurcharge = anObject.paysWorkWithAllSurcharge.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkWithAllSurchargeWithoutDeliv = set.getBigDecimal(35);
				if(anObject.paysWorkWithAllSurchargeWithoutDeliv != null) {
					anObject.paysWorkWithAllSurchargeWithoutDeliv = anObject.paysWorkWithAllSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkBonusWithoutDeliv = set.getBigDecimal(36);
				if(anObject.paysWorkBonusWithoutDeliv != null) {
					anObject.paysWorkBonusWithoutDeliv = anObject.paysWorkBonusWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkSurchargeWithoutDeliv = set.getBigDecimal(37);
				if(anObject.paysWorkSurchargeWithoutDeliv != null) {
					anObject.paysWorkSurchargeWithoutDeliv = anObject.paysWorkSurchargeWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkPremiumWithoutDeliv = set.getBigDecimal(38);
				if(anObject.paysWorkPremiumWithoutDeliv != null) {
					anObject.paysWorkPremiumWithoutDeliv = anObject.paysWorkPremiumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.paysWorkAdditionalWithoutDeliv = set.getBigDecimal(39);
				if(anObject.paysWorkAdditionalWithoutDeliv != null) {
					anObject.paysWorkAdditionalWithoutDeliv = anObject.paysWorkAdditionalWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.chargeSumWithoutDeliv = set.getBigDecimal(40);
				if(anObject.chargeSumWithoutDeliv != null) {
					anObject.chargeSumWithoutDeliv = anObject.chargeSumWithoutDeliv.setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.modify_time = set.getLong(41);
				if(set.wasNull()) {
					anObject.modify_time = Long.MIN_VALUE;
				}
				anObject.humenKindRef.code = set.getInt(42);
				if (set.wasNull()) {
					anObject.humenKindRef.code = Integer.MIN_VALUE;
				}
				anObject.plaItemRef.code = set.getInt(43);
				if (set.wasNull()) {
					anObject.plaItemRef.code = Integer.MIN_VALUE;
				}
				anObject.classificationTypeRef.code = set.getInt(44);
				if (set.wasNull()) {
					anObject.classificationTypeRef.code = Integer.MIN_VALUE;
				}
				anObject.chargeRef.code = set.getInt(45);
				if (set.wasNull()) {
					anObject.chargeRef.code = Integer.MIN_VALUE;
				}
				anObject.positionRef.code = set.getInt(46);
				if (set.wasNull()) {
					anObject.positionRef.code = Integer.MIN_VALUE;
				}
				anObject.transport.code = set.getInt(47);
				if (set.wasNull()) {
					anObject.transport.code = Integer.MIN_VALUE;
				}
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if(set != null) set.close(); if (statement != null) statement.close();}
			catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}


	public com.ksoe.energynet.valueobject.references.ENPlanWorkItem2HumenRef getRef(int anObjectCode) throws PersistenceException {
		com.ksoe.energynet.valueobject.references.ENPlanWorkItem2HumenRef ref = new com.ksoe.energynet.valueobject.references.ENPlanWorkItem2HumenRef();
		if(exists(anObjectCode)) {
			ref.code = anObjectCode;
		} else {
			ref.code = Integer.MIN_VALUE;
		}
		return ref;
	}

	public void remove(int uid) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();

		selectStr = "DELETE FROM  ENPLANWORKITEM2HUMEN WHERE CODE = ?";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		ENPlanWorkItem2Humen object = getObject(uid);

		if(object == null) {
			throw new PersistenceException("{%ENPlanWorkItem2Humen.getObject%} access denied");
		}
		
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(selectStr);
			statement.setInt(1,uid);
			statement.execute();
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}
	
	public long count(ENPlanWorkItem2HumenFilter filter) throws PersistenceException {
		return count(filter, null);
	}
	public long count(ENPlanWorkItem2HumenFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		return (Long)getAggregateValue("count", "code", filter, bindObjects);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getAggregateValue(String aggFunction, String column, ENPlanWorkItem2HumenFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = String.format("SELECT %s(%s) FROM ENPLANWORKITEM2HUMEN", aggFunction, column);
		String whereStr = "";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		

		whereStr = BaseDAOUtils.addToCondition(buildCondition(filter), whereStr);

		if(whereStr.length() != 0) {
			selectStr = selectStr + " WHERE " + whereStr;
		}
		
		try {
			statement = connection.prepareStatement(selectStr);
			int number = setParameters(filter, statement);

			if(bindObjects != null) {
				_bindObjectsToPreparedStatement(statement,bindObjects,number);
			}

			set = statement.executeQuery();
			if(set.next()) {
				return (E)set.getObject(1);
			} else {
				return null;
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();} catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getProperty(String propertyName, int code, E defaultValue) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		String sql = String.format("SELECT %s FROM ENPLANWORKITEM2HUMEN WHERE code = ?", propertyName);
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, code);
			set = statement.executeQuery();
			if(set.next()) {
				return (E)set.getObject(1);
			} else {
				return defaultValue;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - " + sql);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();} catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			try{if(connection != null && !connection.isClosed())connection.close();} catch(SQLException e){}
		}
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> getListOfPropertyValues(String propertyName, ENPlanWorkItem2HumenFilter filter, int fromPosition,int quantity, Collection<? extends Object> bindObjects) throws PersistenceException {
		Connection connection = this.getConnection();
		String sql = String.format("SELECT %s FROM %s ", propertyName, "ENPLANWORKITEM2HUMEN");
		String whereStr = "";
		PreparedStatement statement = null;
		ResultSet set = null;
		
		ArrayList<E> out = new ArrayList<E>();
		
		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		whereStr += buildCondition(filter);

		if(whereStr.length() != 0) {
			sql += " WHERE " + whereStr;
		}
		
		if(filter.getOrderBySQL() != null && filter.getOrderBySQL().trim().length() > 0) {
			sql += " ORDER BY " + filter.getOrderBySQL();
		}
		
		sql += " OFFSET " + fromPosition;
		
		if(quantity > -1) {
			sql += " LIMIT " + quantity;
		}
		
		try {
			statement = connection.prepareStatement(sql);
			int number = setParameters(filter, statement);

			if(bindObjects != null) {
				_bindObjectsToPreparedStatement(statement,new Vector<Object>(bindObjects),number);
			}

			set = statement.executeQuery();
			while(set.next()) {
				out.add((E)set.getObject(1));
			}
			return out;
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+sql);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();} catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
		}		
	}

	public boolean exists(int anObjectCode) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;

		if(anObjectCode == Integer.MIN_VALUE) {
			return false;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		
		selectStr =
			"SELECT  ENPLANWORKITEM2HUMEN.CODE FROM  ENPLANWORKITEM2HUMEN WHERE  ENPLANWORKITEM2HUMEN.CODE = ?";
		try {
			statement = connection.prepareStatement(selectStr);
			statement.setInt(1,anObjectCode);
			set = statement.executeQuery();
			if(set.next()) {
				return true;
			}
			return false;
		} catch(SQLException e) {
			System.out.println(e.getMessage()+"\nstatement - "+selectStr);
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {if (set != null) set.close();}             catch (SQLException e) {}
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}

	public static String processCondition(String aCondition) {
		if(aCondition == null || aCondition.length() == 0) {
			return "";
		}

		StringBuffer condition = new StringBuffer(aCondition);
		_checkConditionToken(condition,";"," ");
		_checkConditionToken(condition,"--"," ");
		_checkConditionToken(condition,"\r"," ");
		_checkConditionToken(condition,"\n"," ");
		_checkConditionToken(condition,"||"," OR ");
		_checkConditionToken(condition,"&&"," AND ");
		_checkConditionToken(condition,"==","=");
		_checkConditionToken(condition,"!=","<>");
		_checkConditionToken(condition,"code","ENPLANWORKITEM2HUMEN.CODE");
		_checkConditionToken(condition,"oredernum","ENPLANWORKITEM2HUMEN.OREDERNUM");
		_checkConditionToken(condition,"tabnumber","ENPLANWORKITEM2HUMEN.TABNUMBER");
		_checkConditionToken(condition,"fio","ENPLANWORKITEM2HUMEN.FIO");
		_checkConditionToken(condition,"salary","ENPLANWORKITEM2HUMEN.SALARY");
		_checkConditionToken(condition,"timemonth","ENPLANWORKITEM2HUMEN.TIMEMONTH");
		_checkConditionToken(condition,"daysmonth","ENPLANWORKITEM2HUMEN.DAYSMONTH");
		_checkConditionToken(condition,"salaryhours","ENPLANWORKITEM2HUMEN.SALARYHOURS");
		_checkConditionToken(condition,"timework","ENPLANWORKITEM2HUMEN.TIMEWORK");
		_checkConditionToken(condition,"timeobjectwork","ENPLANWORKITEM2HUMEN.TIMEOBJECTWORK");
		_checkConditionToken(condition,"timeworkfact","ENPLANWORKITEM2HUMEN.TIMEWORKFACT");
		_checkConditionToken(condition,"timedelivery","ENPLANWORKITEM2HUMEN.TIMEDELIVERY");
		_checkConditionToken(condition,"payswork","ENPLANWORKITEM2HUMEN.PAYSWORK");
		_checkConditionToken(condition,"bonus","ENPLANWORKITEM2HUMEN.BONUS");
		_checkConditionToken(condition,"salaryhoursbonus","ENPLANWORKITEM2HUMEN.SALARYHOURSBONUS");
		_checkConditionToken(condition,"paysworkbonus","ENPLANWORKITEM2HUMEN.PAYSWORKBONUS");
		_checkConditionToken(condition,"chargepercent","ENPLANWORKITEM2HUMEN.CHARGEPERCENT");
		_checkConditionToken(condition,"chargesum","ENPLANWORKITEM2HUMEN.CHARGESUM");
		_checkConditionToken(condition,"balans","ENPLANWORKITEM2HUMEN.BALANS");
		_checkConditionToken(condition,"podrcod","ENPLANWORKITEM2HUMEN.PODRCOD");
		_checkConditionToken(condition,"costdelivery","ENPLANWORKITEM2HUMEN.COSTDELIVERY");
		_checkConditionToken(condition,"generalexpenses","ENPLANWORKITEM2HUMEN.GENERALEXPENSES");
		_checkConditionToken(condition,"administrativeexpenses","ENPLANWORKITEM2HUMEN.ADMINISTRATIVEEXPENSES");
		_checkConditionToken(condition,"cehid","ENPLANWORKITEM2HUMEN.CEHID");
		_checkConditionToken(condition,"percentsurcharge","ENPLANWORKITEM2HUMEN.PERCENTSURCHARGE");
		_checkConditionToken(condition,"salaryhourssurcharge","ENPLANWORKITEM2HUMEN.SALARYHOURSSURCHARGE");
		_checkConditionToken(condition,"paysworksurcharge","ENPLANWORKITEM2HUMEN.PAYSWORKSURCHARGE");
		_checkConditionToken(condition,"percentpremium","ENPLANWORKITEM2HUMEN.PERCENTPREMIUM");
		_checkConditionToken(condition,"salaryhourspremium","ENPLANWORKITEM2HUMEN.SALARYHOURSPREMIUM");
		_checkConditionToken(condition,"paysworkpremium","ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUM");
		_checkConditionToken(condition,"percentadditional","ENPLANWORKITEM2HUMEN.PERCENTADDITIONAL");
		_checkConditionToken(condition,"salaryhoursadditional","ENPLANWORKITEM2HUMEN.SALARYHOURSADDITIONAL");
		_checkConditionToken(condition,"paysworkadditional","ENPLANWORKITEM2HUMEN.PAYSWORKADDITIONAL");
		_checkConditionToken(condition,"paysworkwithallsurcharge","ENPLANWORKITEM2HUMEN.PAYSWORKWITHALLSURCHRG");
		_checkConditionToken(condition,"paysworkwithallsurchargewithoutdeliv","ENPLANWORKITEM2HUMEN.PSWRKWTHLLSRCHRGWTHTDL");
		_checkConditionToken(condition,"paysworkbonuswithoutdeliv","ENPLANWORKITEM2HUMEN.PAYSWORKBONUSWITHOTDLV");
		_checkConditionToken(condition,"paysworksurchargewithoutdeliv","ENPLANWORKITEM2HUMEN.PAYSWORKSURCHRGWTHTDLV");
		_checkConditionToken(condition,"paysworkpremiumwithoutdeliv","ENPLANWORKITEM2HUMEN.PAYSWORKPREMIUMWTHTDLV");
		_checkConditionToken(condition,"paysworkadditionalwithoutdeliv","ENPLANWORKITEM2HUMEN.PAYSWORKADDITNLWTHTDLV");
		_checkConditionToken(condition,"chargesumwithoutdeliv","ENPLANWORKITEM2HUMEN.CHARGESUMWITHOUTDELIV");
		_checkConditionToken(condition,"modify_time","ENPLANWORKITEM2HUMEN.MODIFY_TIME");
		// relationship conditions
		_checkConditionToken(condition,"humenkindref","HUMENKINDREFCODE");
		_checkConditionToken(condition,"humenkindref.code","HUMENKINDREFCODE");
		_checkConditionToken(condition,"plaitemref","PLAITEMREFCODE");
		_checkConditionToken(condition,"plaitemref.code","PLAITEMREFCODE");
		_checkConditionToken(condition,"classificationtyperef","CLASSIFICATIONTYPERFCD");
		_checkConditionToken(condition,"classificationtyperef.code","CLASSIFICATIONTYPERFCD");
		_checkConditionToken(condition,"chargeref","CHARGEREFCODE");
		_checkConditionToken(condition,"chargeref.code","CHARGEREFCODE");
		_checkConditionToken(condition,"positionref","POSITIONREFCODE");
		_checkConditionToken(condition,"positionref.code","POSITIONREFCODE");
		_checkConditionToken(condition,"transport","TRANSPORTCODE");
		_checkConditionToken(condition,"transport.code","TRANSPORTCODE");
		return condition.toString();
	}

	public Connection getConnection() {
		try {
			if(super.getConnection() != null && !super.getConnection().isClosed())
			return super.getConnection();

			InitialContext initialContext = new InitialContext();
			DataSource dataSource = (DataSource)initialContext.lookup(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
			return dataSource.getConnection();
		}
		catch (NamingException e) {throw new SystemException("Can't get connection. JNDI = {%"+AuthorizationJNDINames.ENERGYNET_DATASOURCE+"%}",e);}
		catch (SQLException e)    {throw new SystemException("Can't get connection. JNDI = {%"+AuthorizationJNDINames.ENERGYNET_DATASOURCE+"%}",e);}
	}

	///////////// PRIVATE SECTION ///////////////
	protected static Hashtable<SequenceKey, SequenceValue> _sequenceTable = new Hashtable<SequenceKey, SequenceValue>();
	
	private void _collectAutoIncrementFields(ENPlanWorkItem2Humen anObject, Connection connection) throws PersistenceException {

		SequenceKey hashKey = new SequenceKey("ENPLANWORKITEM2HUMEN", "CODE");
		Integer nextSeqValue = null;
		SequenceValue sequenceValue;
		synchronized (_sequenceTable) {
			sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
			if (sequenceValue == null) {
				sequenceValue = getNewSequenceValue("ENPLANWORKITEM2HUMEN", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
			if (!sequenceValue.isNextValueAvailable()) {
				sequenceValue = getNewSequenceValue("ENPLANWORKITEM2HUMEN", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
		}

		nextSeqValue = sequenceValue.getNextValue();
		if (nextSeqValue == null) {
			throw new PersistenceException(
				"Can't obtain auto increment value from: ENPLANWORKITEM2HUMEN");
		} else {
			anObject.code = nextSeqValue.intValue();
			return;
		}
	}

} // end of ENPlanWorkItem2HumenDAO
