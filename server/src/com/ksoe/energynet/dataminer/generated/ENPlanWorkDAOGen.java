
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2021 SIT
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
import com.ksoe.lla.persistence.sql.SegregationQueryBuilder;
import com.ksoe.lla.segregation.SegregationInfo;
import com.ksoe.lla.segregation.SegregationProcessor;


import java.util.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.dataminer.BaseDAOUtils;
import com.ksoe.energynet.valueobject.ENPlanWork;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkShortList;


/**
 * DAO Object for ENPlanWork;
 *
 */

public class ENPlanWorkDAOGen extends GenericDataMiner {

	public ENPlanWorkDAOGen(UserProfile anUserProfile, Connection aConnection) {
		super(anUserProfile,aConnection);
	}

	public ENPlanWorkDAOGen(Connection aConnection, UserProfile anUserProfile) {
		super(aConnection,anUserProfile);
	}

	public boolean isEqual(ENPlanWork inObject) throws PersistenceException {
		ENPlanWork obj = new ENPlanWork();
		obj.code = inObject.code;
		loadObject(obj);

		if(inObject.dateGen == null && obj.dateGen == null){} else 
			if(inObject.dateGen == null || obj.dateGen == null) return false;
			else
				if (inObject.dateGen.compareTo(obj.dateGen) != 0){
					return false;
				}

		if(inObject.dateStart == null && obj.dateStart == null){} else 
			if(inObject.dateStart == null || obj.dateStart == null) return false;
			else
				if (inObject.dateStart.compareTo(obj.dateStart) != 0){
					return false;
				}

		if(inObject.dateFinal == null && obj.dateFinal == null){} else 
			if(inObject.dateFinal == null || obj.dateFinal == null) return false;
			else
				if (inObject.dateFinal.compareTo(obj.dateFinal) != 0){
					return false;
				}

		if (inObject.yearGen != obj.yearGen){
					return false;
				}

		if (inObject.monthGen != obj.monthGen){
					return false;
				}

		if (inObject.yearOriginal != obj.yearOriginal){
					return false;
				}

		if (inObject.monthOriginal != obj.monthOriginal){
					return false;
				}

		if(inObject.commentGen == null && obj.commentGen == null){}
		else
			if(inObject.commentGen == null || obj.commentGen == null) return false;
			else
				if ( ! inObject.commentGen.equals(obj.commentGen)){
					return false;
				}

		if(inObject.userGen == null && obj.userGen == null){}
		else
			if(inObject.userGen == null || obj.userGen == null) return false;
			else
				if ( ! inObject.userGen.equals(obj.userGen)){
					return false;
				}

		if(inObject.dateEdit == null && obj.dateEdit == null){} else 
			if(inObject.dateEdit == null || obj.dateEdit == null) return false;
			else
				if (inObject.dateEdit.compareTo(obj.dateEdit) != 0){
					return false;
				}

		if(inObject.distanceAlong == null && obj.distanceAlong == null){}
		else
			if(inObject.distanceAlong == null || obj.distanceAlong == null) return false;
			else
				if ( ! inObject.distanceAlong.equals(obj.distanceAlong)){
					return false;
				}

		if(inObject.distanceTo == null && obj.distanceTo == null){}
		else
			if(inObject.distanceTo == null || obj.distanceTo == null) return false;
			else
				if ( ! inObject.distanceTo.equals(obj.distanceTo)){
					return false;
				}

		if(inObject.workOrderNumber == null && obj.workOrderNumber == null){}
		else
			if(inObject.workOrderNumber == null || obj.workOrderNumber == null) return false;
			else
				if ( ! inObject.workOrderNumber.equals(obj.workOrderNumber)){
					return false;
				}

		if(inObject.dateWorkOrder == null && obj.dateWorkOrder == null){} else 
			if(inObject.dateWorkOrder == null || obj.dateWorkOrder == null) return false;
			else
				if (inObject.dateWorkOrder.compareTo(obj.dateWorkOrder) != 0){
					return false;
				}

		if(inObject.priConnectionNumber == null && obj.priConnectionNumber == null){}
		else
			if(inObject.priConnectionNumber == null || obj.priConnectionNumber == null) return false;
			else
				if ( ! inObject.priConnectionNumber.equals(obj.priConnectionNumber)){
					return false;
				}

		if(inObject.dateEndPriConnection == null && obj.dateEndPriConnection == null){} else 
			if(inObject.dateEndPriConnection == null || obj.dateEndPriConnection == null) return false;
			else
				if (inObject.dateEndPriConnection.compareTo(obj.dateEndPriConnection) != 0){
					return false;
				}

		if(inObject.investWorksAmount == null && obj.investWorksAmount == null){}
		else
			if(inObject.investWorksAmount == null || obj.investWorksAmount == null) return false;
			else
				if ( ! inObject.investWorksAmount.equals(obj.investWorksAmount)){
					return false;
				}

		if(inObject.investWorksDescription == null && obj.investWorksDescription == null){}
		else
			if(inObject.investWorksDescription == null || obj.investWorksDescription == null) return false;
			else
				if ( ! inObject.investWorksDescription.equals(obj.investWorksDescription)){
					return false;
				}

		if(inObject.investDateStartWork == null && obj.investDateStartWork == null){} else 
			if(inObject.investDateStartWork == null || obj.investDateStartWork == null) return false;
			else
				if (inObject.investDateStartWork.compareTo(obj.investDateStartWork) != 0){
					return false;
				}

		if (inObject.investWorkMeasCode != obj.investWorkMeasCode){
					return false;
				}

		if (inObject.servicesFSideFinId != obj.servicesFSideFinId){
					return false;
				}

		if(inObject.servicesFSideCnNum == null && obj.servicesFSideCnNum == null){}
		else
			if(inObject.servicesFSideCnNum == null || obj.servicesFSideCnNum == null) return false;
			else
				if ( ! inObject.servicesFSideCnNum.equals(obj.servicesFSideCnNum)){
					return false;
				}

		if(inObject.totalTimeHours == null && obj.totalTimeHours == null){}
		else
			if(inObject.totalTimeHours == null || obj.totalTimeHours == null) return false;
			else
				if ( ! inObject.totalTimeHours.equals(obj.totalTimeHours)){
					return false;
				}

		if(inObject.totalTimeDays == null && obj.totalTimeDays == null){}
		else
			if(inObject.totalTimeDays == null || obj.totalTimeDays == null) return false;
			else
				if ( ! inObject.totalTimeDays.equals(obj.totalTimeDays)){
					return false;
				}

		if(inObject.investItemNumber == null && obj.investItemNumber == null){}
		else
			if(inObject.investItemNumber == null || obj.investItemNumber == null) return false;
			else
				if ( ! inObject.investItemNumber.equals(obj.investItemNumber)){
					return false;
				}

		if (inObject.causeDisconnection != obj.causeDisconnection){
					return false;
				}
		if (inObject.status.code != obj.status.code){
			return false;
		}
		if (inObject.elementRef.code != obj.elementRef.code){
			return false;
		}
		if (inObject.typeRef.code != obj.typeRef.code){
			return false;
		}
		if (inObject.kind.code != obj.kind.code){
			return false;
		}
		if (inObject.renRef.code != obj.renRef.code){
			return false;
		}
		if (inObject.finExecutor.code != obj.finExecutor.code){
			return false;
		}
		if (inObject.stateRef.code != obj.stateRef.code){
			return false;
		}
		if (inObject.formRef.code != obj.formRef.code){
			return false;
		}
		if (inObject.sourceRef.code != obj.sourceRef.code){
			return false;
		}
		if (inObject.ddsCodes.code != obj.ddsCodes.code){
			return false;
		}
		if (inObject.budgetRef.code != obj.budgetRef.code){
			return false;
		}
		if (inObject.responsibilityRef.code != obj.responsibilityRef.code){
			return false;
		}
		if (inObject.departmentRef.code != obj.departmentRef.code){
			return false;
		}
		if (inObject.invgroupRef.code != obj.invgroupRef.code){
			return false;
		}
		if (inObject.ipImplementTypeRef.code != obj.ipImplementTypeRef.code){
			return false;
		}
		return true;
	}

	public int add(ENPlanWork anObject) throws PersistenceException {
		return add(anObject,true);
	}

	public int add(ENPlanWork anObject, boolean aUseSequential) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		anObject.modify_time = System.currentTimeMillis();
		if(anObject.getDomain_info() == null) {
			anObject.setDomain_info(getUserProfile().getDomainInfo().getDomain());
		}
		if(aUseSequential) {
			_collectAutoIncrementFields(anObject,connection);
		}


		selectStr = "INSERT INTO ENPLANWORK (CODE,DATEGEN,DATESTART,DATEFINAL,YEARGEN,MONTHGEN,YEARORIGINAL,MONTHORIGINAL,COMMENTGEN,USERGEN,DATEEDIT,DISTANCEALONG,DISTANCETO,WORKORDERNUMBER,DATEWORKORDER,PRICONNECTIONNUMBER,DATEENDPRICONNECTION,INVESTWORKSAMOUNT,INVESTWORKSDESCRIPTION,INVESTDATESTARTWORK,INVESTWORKMEASCODE,SERVICESFSIDEFINID,SERVICESFSIDECNNUM,TOTALTIMEHOURS,TOTALTIMEDAYS,INVESTITEMNUMBER,CAUSEDISCONNECTION,DOMAIN_INFO,MODIFY_TIME,STATUSCODE,ELEMENTREFCODE,TYPEREFCODE,KINDCODE,RENREFCODE,FINEXECUTORCODE,STATEREFCODE,FORMREFCODE,SOURCEREFCODE,DDSCODESCODE,BUDGETREFCODE,RESPONSIBILITYREFCODE,DEPARTMENTREFCODE,INVGROUPREFCODE,IPIMPLEMENTTYPEREFCODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			statement = connection.prepareStatement(selectStr);
			if (anObject.code != Integer.MIN_VALUE ) {
				statement.setInt(1, anObject.code);
			} else {
				statement.setNull(1, java.sql.Types.INTEGER);
			}
			if (anObject.dateGen == null) {
				statement.setTimestamp(2, null);
			} else {
				statement.setTimestamp(2, new java.sql.Timestamp(anObject.dateGen.getTime()));
			}
			if (anObject.dateStart == null) {
				statement.setDate(3, null);
			} else {
				statement.setDate(3, new java.sql.Date(anObject.dateStart.getTime()));
			}
			if (anObject.dateFinal == null) {
				statement.setDate(4, null);
			} else {
				statement.setDate(4, new java.sql.Date(anObject.dateFinal.getTime()));
			}
			if (anObject.yearGen != Integer.MIN_VALUE ) {
				statement.setInt(5, anObject.yearGen);
			} else {
				statement.setNull(5, java.sql.Types.INTEGER);
			}
			if (anObject.monthGen != Integer.MIN_VALUE ) {
				statement.setInt(6, anObject.monthGen);
			} else {
				statement.setNull(6, java.sql.Types.INTEGER);
			}
			if (anObject.yearOriginal != Integer.MIN_VALUE ) {
				statement.setInt(7, anObject.yearOriginal);
			} else {
				statement.setNull(7, java.sql.Types.INTEGER);
			}
			if (anObject.monthOriginal != Integer.MIN_VALUE ) {
				statement.setInt(8, anObject.monthOriginal);
			} else {
				statement.setNull(8, java.sql.Types.INTEGER);
			}
			statement.setString(9, anObject.commentGen);
			statement.setString(10, anObject.userGen);
			if (anObject.dateEdit == null) {
				statement.setDate(11, null);
			} else {
				statement.setDate(11, new java.sql.Date(anObject.dateEdit.getTime()));
			}
			if (anObject.distanceAlong != null) {
				anObject.distanceAlong = anObject.distanceAlong.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(12, anObject.distanceAlong);
			if (anObject.distanceTo != null) {
				anObject.distanceTo = anObject.distanceTo.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(13, anObject.distanceTo);
			statement.setString(14, anObject.workOrderNumber);
			if (anObject.dateWorkOrder == null) {
				statement.setDate(15, null);
			} else {
				statement.setDate(15, new java.sql.Date(anObject.dateWorkOrder.getTime()));
			}
			statement.setString(16, anObject.priConnectionNumber);
			if (anObject.dateEndPriConnection == null) {
				statement.setDate(17, null);
			} else {
				statement.setDate(17, new java.sql.Date(anObject.dateEndPriConnection.getTime()));
			}
			if (anObject.investWorksAmount != null) {
				anObject.investWorksAmount = anObject.investWorksAmount.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(18, anObject.investWorksAmount);
			statement.setString(19, anObject.investWorksDescription);
			if (anObject.investDateStartWork == null) {
				statement.setTimestamp(20, null);
			} else {
				statement.setTimestamp(20, new java.sql.Timestamp(anObject.investDateStartWork.getTime()));
			}
			if (anObject.investWorkMeasCode != Integer.MIN_VALUE ) {
				statement.setInt(21, anObject.investWorkMeasCode);
			} else {
				statement.setNull(21, java.sql.Types.INTEGER);
			}
			if (anObject.servicesFSideFinId != Integer.MIN_VALUE ) {
				statement.setInt(22, anObject.servicesFSideFinId);
			} else {
				statement.setNull(22, java.sql.Types.INTEGER);
			}
			statement.setString(23, anObject.servicesFSideCnNum);
			if (anObject.totalTimeHours != null) {
				anObject.totalTimeHours = anObject.totalTimeHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(24, anObject.totalTimeHours);
			if (anObject.totalTimeDays != null) {
				anObject.totalTimeDays = anObject.totalTimeDays.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(25, anObject.totalTimeDays);
			statement.setString(26, anObject.investItemNumber);
			if (anObject.causeDisconnection != Integer.MIN_VALUE ) {
				statement.setInt(27, anObject.causeDisconnection);
			} else {
				statement.setNull(27, java.sql.Types.INTEGER);
			}
			statement.setString(28, anObject.domain_info);
			if (anObject.modify_time == Long.MIN_VALUE) {
				statement.setBigDecimal(29, null);
			} else {
				statement.setBigDecimal(29, new java.math.BigDecimal(anObject.modify_time));
			}
			if (anObject.status.code != Integer.MIN_VALUE){
				statement.setInt(30,anObject.status.code);
			} else {
				statement.setNull(30,java.sql.Types.INTEGER);
			}
			if (anObject.elementRef.code != Integer.MIN_VALUE){
				statement.setInt(31,anObject.elementRef.code);
			} else {
				statement.setNull(31,java.sql.Types.INTEGER);
			}
			if (anObject.typeRef.code != Integer.MIN_VALUE){
				statement.setInt(32,anObject.typeRef.code);
			} else {
				statement.setNull(32,java.sql.Types.INTEGER);
			}
			if (anObject.kind.code != Integer.MIN_VALUE){
				statement.setInt(33,anObject.kind.code);
			} else {
				statement.setNull(33,java.sql.Types.INTEGER);
			}
			if (anObject.renRef.code != Integer.MIN_VALUE){
				statement.setInt(34,anObject.renRef.code);
			} else {
				statement.setNull(34,java.sql.Types.INTEGER);
			}
			if (anObject.finExecutor.code != Integer.MIN_VALUE){
				statement.setInt(35,anObject.finExecutor.code);
			} else {
				statement.setNull(35,java.sql.Types.INTEGER);
			}
			if (anObject.stateRef.code != Integer.MIN_VALUE){
				statement.setInt(36,anObject.stateRef.code);
			} else {
				statement.setNull(36,java.sql.Types.INTEGER);
			}
			if (anObject.formRef.code != Integer.MIN_VALUE){
				statement.setInt(37,anObject.formRef.code);
			} else {
				statement.setNull(37,java.sql.Types.INTEGER);
			}
			if (anObject.sourceRef.code != Integer.MIN_VALUE){
				statement.setInt(38,anObject.sourceRef.code);
			} else {
				statement.setNull(38,java.sql.Types.INTEGER);
			}
			if (anObject.ddsCodes.code != Integer.MIN_VALUE){
				statement.setInt(39,anObject.ddsCodes.code);
			} else {
				statement.setNull(39,java.sql.Types.INTEGER);
			}
			if (anObject.budgetRef.code != Integer.MIN_VALUE){
				statement.setInt(40,anObject.budgetRef.code);
			} else {
				statement.setNull(40,java.sql.Types.INTEGER);
			}
			if (anObject.responsibilityRef.code != Integer.MIN_VALUE){
				statement.setInt(41,anObject.responsibilityRef.code);
			} else {
				statement.setNull(41,java.sql.Types.INTEGER);
			}
			if (anObject.departmentRef.code != Integer.MIN_VALUE){
				statement.setInt(42,anObject.departmentRef.code);
			} else {
				statement.setNull(42,java.sql.Types.INTEGER);
			}
			if (anObject.invgroupRef.code != Integer.MIN_VALUE){
				statement.setInt(43,anObject.invgroupRef.code);
			} else {
				statement.setNull(43,java.sql.Types.INTEGER);
			}
			if (anObject.ipImplementTypeRef.code != Integer.MIN_VALUE){
				statement.setInt(44,anObject.ipImplementTypeRef.code);
			} else {
				statement.setNull(44,java.sql.Types.INTEGER);
			}

			statement.execute();


			return anObject.code;

		} catch(SQLException e) {
			_sequenceTable.clear();
			throw new SystemException(e.getMessage(), e);
		} catch(Exception e) {
			_sequenceTable.clear();
			throw new PersistenceException("Error in method {%ENPlanWorkDAOGen.add%}",e);
		} finally {
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}

	public void save(ENPlanWork anObject) throws PersistenceException {
		save(anObject,null);
	}

	public void save(ENPlanWork anObject,String[] anAttributes) throws PersistenceException {
		if(anAttributes != null && anAttributes.length == 0) {
			return;
		}
		Connection connection = getConnection();
		try {
			PreparedStatement statement = null;

			ENPlanWork oldObject = new ENPlanWork();
			oldObject.code = anObject.code;
			String oldObjectSelectStr = "SELECT "+ENPlanWork.modify_time_Field + "," + ENPlanWork.domain_info_Field+" FROM  ENPLANWORK WHERE CODE = ?";
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
				oldObject.domain_info = set.getString(2);
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

			if(anObject.getDomain_info() == null) {
				anObject.setDomain_info(getUserProfile().getDomainInfo().getDomain());
			}
			String selectStr;

			Vector<String> fields = null;
			if(anAttributes != null) {
				String fieldNameStr;
				fields = new Vector<String>();
				for(int attrIndex = 0;attrIndex < anAttributes.length;attrIndex++) {
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATEGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATESTART") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATEFINAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("YEARGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("MONTHGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("YEARORIGINAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("MONTHORIGINAL") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("COMMENTGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("USERGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATEEDIT") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DISTANCEALONG") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DISTANCETO") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("WORKORDERNUMBER") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATEWORKORDER") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PRICONNECTIONNUMBER") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DATEENDPRICONNECTION") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVESTWORKSAMOUNT") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVESTWORKSDESCRIPTION") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVESTDATESTARTWORK") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVESTWORKMEASCODE") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SERVICESFSIDEFINID") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SERVICESFSIDECNNUM") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TOTALTIMEHOURS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TOTALTIMEDAYS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVESTITEMNUMBER") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("CAUSEDISCONNECTION") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DOMAIN_INFO") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("MODIFY_TIME") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("STATUS") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("ELEMENTREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("TYPEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("KIND") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("RENREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("FINEXECUTOR") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("STATEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("FORMREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("SOURCEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DDSCODES") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("BUDGETREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("RESPONSIBILITYREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("DEPARTMENTREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("INVGROUPREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("IPIMPLEMENTTYPEREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
				}
				if(fields.size() == 0) {
					return;
				}
			}
			if(fields == null) {
				selectStr = "UPDATE ENPLANWORK SET  DATEGEN = ? , DATESTART = ? , DATEFINAL = ? , YEARGEN = ? , MONTHGEN = ? , YEARORIGINAL = ? , MONTHORIGINAL = ? , COMMENTGEN = ? , USERGEN = ? , DATEEDIT = ? , DISTANCEALONG = ? , DISTANCETO = ? , WORKORDERNUMBER = ? , DATEWORKORDER = ? , PRICONNECTIONNUMBER = ? , DATEENDPRICONNECTION = ? , INVESTWORKSAMOUNT = ? , INVESTWORKSDESCRIPTION = ? , INVESTDATESTARTWORK = ? , INVESTWORKMEASCODE = ? , SERVICESFSIDEFINID = ? , SERVICESFSIDECNNUM = ? , TOTALTIMEHOURS = ? , TOTALTIMEDAYS = ? , INVESTITEMNUMBER = ? , CAUSEDISCONNECTION = ? , DOMAIN_INFO = ? , MODIFY_TIME = ? , STATUSCODE = ? , ELEMENTREFCODE = ? , TYPEREFCODE = ? , KINDCODE = ? , RENREFCODE = ? , FINEXECUTORCODE = ? , STATEREFCODE = ? , FORMREFCODE = ? , SOURCEREFCODE = ? , DDSCODESCODE = ? , BUDGETREFCODE = ? , RESPONSIBILITYREFCODE = ? , DEPARTMENTREFCODE = ? , INVGROUPREFCODE = ? , IPIMPLEMENTTYPEREFCODE = ?  "
					+" WHERE CODE = ?";
			} else {
				selectStr = "UPDATE ENPLANWORK SET ";
				for(int fldIndex = 0;fldIndex < fields.size();fldIndex++) {
					if(fldIndex > 0) {
						selectStr+=", ";
					}
					selectStr+=(String)fields.get(fldIndex);
					selectStr+=" = ?";
				}
				selectStr += " WHERE CODE = ?";
			}

			statement = null;
			try {
				statement = connection.prepareStatement(selectStr);
				if(fields == null) {
					if (anObject.dateGen == null) {
						statement.setTimestamp(1, null);
					} else {
						statement.setTimestamp(1, new java.sql.Timestamp(anObject.dateGen.getTime()));
					}
					if (anObject.dateStart == null) {
						statement.setDate(2, null);
					} else {
						statement.setDate(2, new java.sql.Date(anObject.dateStart.getTime()));
					}
					if (anObject.dateFinal == null) {
						statement.setDate(3, null);
					} else {
						statement.setDate(3, new java.sql.Date(anObject.dateFinal.getTime()));
					}
					if (anObject.yearGen != Integer.MIN_VALUE) {
						statement.setInt(4, anObject.yearGen);
					} else {
						statement.setNull(4, java.sql.Types.INTEGER);
					}
					if (anObject.monthGen != Integer.MIN_VALUE) {
						statement.setInt(5, anObject.monthGen);
					} else {
						statement.setNull(5, java.sql.Types.INTEGER);
					}
					if (anObject.yearOriginal != Integer.MIN_VALUE) {
						statement.setInt(6, anObject.yearOriginal);
					} else {
						statement.setNull(6, java.sql.Types.INTEGER);
					}
					if (anObject.monthOriginal != Integer.MIN_VALUE) {
						statement.setInt(7, anObject.monthOriginal);
					} else {
						statement.setNull(7, java.sql.Types.INTEGER);
					}
					statement.setString(8, anObject.commentGen);
					statement.setString(9, anObject.userGen);
					if (anObject.dateEdit == null) {
						statement.setDate(10, null);
					} else {
						statement.setDate(10, new java.sql.Date(anObject.dateEdit.getTime()));
					}
					if (anObject.distanceAlong != null) {
						anObject.distanceAlong = anObject.distanceAlong.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(11, anObject.distanceAlong);
					if (anObject.distanceTo != null) {
						anObject.distanceTo = anObject.distanceTo.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(12, anObject.distanceTo);
					statement.setString(13, anObject.workOrderNumber);
					if (anObject.dateWorkOrder == null) {
						statement.setDate(14, null);
					} else {
						statement.setDate(14, new java.sql.Date(anObject.dateWorkOrder.getTime()));
					}
					statement.setString(15, anObject.priConnectionNumber);
					if (anObject.dateEndPriConnection == null) {
						statement.setDate(16, null);
					} else {
						statement.setDate(16, new java.sql.Date(anObject.dateEndPriConnection.getTime()));
					}
					if (anObject.investWorksAmount != null) {
						anObject.investWorksAmount = anObject.investWorksAmount.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(17, anObject.investWorksAmount);
					statement.setString(18, anObject.investWorksDescription);
					if (anObject.investDateStartWork == null) {
						statement.setTimestamp(19, null);
					} else {
						statement.setTimestamp(19, new java.sql.Timestamp(anObject.investDateStartWork.getTime()));
					}
					if (anObject.investWorkMeasCode != Integer.MIN_VALUE) {
						statement.setInt(20, anObject.investWorkMeasCode);
					} else {
						statement.setNull(20, java.sql.Types.INTEGER);
					}
					if (anObject.servicesFSideFinId != Integer.MIN_VALUE) {
						statement.setInt(21, anObject.servicesFSideFinId);
					} else {
						statement.setNull(21, java.sql.Types.INTEGER);
					}
					statement.setString(22, anObject.servicesFSideCnNum);
					if (anObject.totalTimeHours != null) {
						anObject.totalTimeHours = anObject.totalTimeHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(23, anObject.totalTimeHours);
					if (anObject.totalTimeDays != null) {
						anObject.totalTimeDays = anObject.totalTimeDays.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(24, anObject.totalTimeDays);
					statement.setString(25, anObject.investItemNumber);
					if (anObject.causeDisconnection != Integer.MIN_VALUE) {
						statement.setInt(26, anObject.causeDisconnection);
					} else {
						statement.setNull(26, java.sql.Types.INTEGER);
					}
					statement.setString(27, anObject.domain_info);
					if (anObject.modify_time == Long.MIN_VALUE) {
						statement.setBigDecimal(28, null);
					} else {
						statement.setBigDecimal(28, new java.math.BigDecimal(anObject.modify_time));
					}
					if (anObject.status.code != Integer.MIN_VALUE) {
						statement.setInt(29, anObject.status.code);
					} else {
						statement.setNull(29, java.sql.Types.INTEGER);
					}
					if (anObject.elementRef.code != Integer.MIN_VALUE) {
						statement.setInt(30, anObject.elementRef.code);
					} else {
						statement.setNull(30, java.sql.Types.INTEGER);
					}
					if (anObject.typeRef.code != Integer.MIN_VALUE) {
						statement.setInt(31, anObject.typeRef.code);
					} else {
						statement.setNull(31, java.sql.Types.INTEGER);
					}
					if (anObject.kind.code != Integer.MIN_VALUE) {
						statement.setInt(32, anObject.kind.code);
					} else {
						statement.setNull(32, java.sql.Types.INTEGER);
					}
					if (anObject.renRef.code != Integer.MIN_VALUE) {
						statement.setInt(33, anObject.renRef.code);
					} else {
						statement.setNull(33, java.sql.Types.INTEGER);
					}
					if (anObject.finExecutor.code != Integer.MIN_VALUE) {
						statement.setInt(34, anObject.finExecutor.code);
					} else {
						statement.setNull(34, java.sql.Types.INTEGER);
					}
					if (anObject.stateRef.code != Integer.MIN_VALUE) {
						statement.setInt(35, anObject.stateRef.code);
					} else {
						statement.setNull(35, java.sql.Types.INTEGER);
					}
					if (anObject.formRef.code != Integer.MIN_VALUE) {
						statement.setInt(36, anObject.formRef.code);
					} else {
						statement.setNull(36, java.sql.Types.INTEGER);
					}
					if (anObject.sourceRef.code != Integer.MIN_VALUE) {
						statement.setInt(37, anObject.sourceRef.code);
					} else {
						statement.setNull(37, java.sql.Types.INTEGER);
					}
					if (anObject.ddsCodes.code != Integer.MIN_VALUE) {
						statement.setInt(38, anObject.ddsCodes.code);
					} else {
						statement.setNull(38, java.sql.Types.INTEGER);
					}
					if (anObject.budgetRef.code != Integer.MIN_VALUE) {
						statement.setInt(39, anObject.budgetRef.code);
					} else {
						statement.setNull(39, java.sql.Types.INTEGER);
					}
					if (anObject.responsibilityRef.code != Integer.MIN_VALUE) {
						statement.setInt(40, anObject.responsibilityRef.code);
					} else {
						statement.setNull(40, java.sql.Types.INTEGER);
					}
					if (anObject.departmentRef.code != Integer.MIN_VALUE) {
						statement.setInt(41, anObject.departmentRef.code);
					} else {
						statement.setNull(41, java.sql.Types.INTEGER);
					}
					if (anObject.invgroupRef.code != Integer.MIN_VALUE) {
						statement.setInt(42, anObject.invgroupRef.code);
					} else {
						statement.setNull(42, java.sql.Types.INTEGER);
					}
					if (anObject.ipImplementTypeRef.code != Integer.MIN_VALUE) {
						statement.setInt(43, anObject.ipImplementTypeRef.code);
					} else {
						statement.setNull(43, java.sql.Types.INTEGER);
					}
					statement.setInt(44, anObject.code);
				} else {
					for(int i = 0;i < fields.size();i++) {
						if("DATEGEN".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateGen == null) {
								statement.setTimestamp(i+1,null);
							} else {
								statement.setTimestamp(i+1, new java.sql.Timestamp(anObject.dateGen.getTime()));
							}
							continue;
						}
						if("DATESTART".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateStart == null) {
								statement.setDate(i+1,null);
							} else {
								statement.setDate(i+1, new java.sql.Date(anObject.dateStart.getTime()));
							}
							continue;
						}
						if("DATEFINAL".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateFinal == null) {
								statement.setDate(i+1,null);
							} else {
								statement.setDate(i+1, new java.sql.Date(anObject.dateFinal.getTime()));
							}
							continue;
						}
						if("YEARGEN".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.yearGen);
							continue;
						}
						if("MONTHGEN".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.monthGen);
							continue;
						}
						if("YEARORIGINAL".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.yearOriginal);
							continue;
						}
						if("MONTHORIGINAL".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.monthOriginal);
							continue;
						}
						if("COMMENTGEN".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.commentGen);
							continue;
						}
						if("USERGEN".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.userGen);
							continue;
						}
						if("DATEEDIT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateEdit == null) {
								statement.setDate(i+1,null);
							} else {
								statement.setDate(i+1, new java.sql.Date(anObject.dateEdit.getTime()));
							}
							continue;
						}
						if("DISTANCEALONG".compareTo((String)fields.get(i)) == 0) {
							if (anObject.distanceAlong != null) {
								anObject.distanceAlong = anObject.distanceAlong.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.distanceAlong);
							continue;
						}
						if("DISTANCETO".compareTo((String)fields.get(i)) == 0) {
							if (anObject.distanceTo != null) {
								anObject.distanceTo = anObject.distanceTo.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.distanceTo);
							continue;
						}
						if("WORKORDERNUMBER".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.workOrderNumber);
							continue;
						}
						if("DATEWORKORDER".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateWorkOrder == null) {
								statement.setDate(i+1,null);
							} else {
								statement.setDate(i+1, new java.sql.Date(anObject.dateWorkOrder.getTime()));
							}
							continue;
						}
						if("PRICONNECTIONNUMBER".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.priConnectionNumber);
							continue;
						}
						if("DATEENDPRICONNECTION".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateEndPriConnection == null) {
								statement.setDate(i+1,null);
							} else {
								statement.setDate(i+1, new java.sql.Date(anObject.dateEndPriConnection.getTime()));
							}
							continue;
						}
						if("INVESTWORKSAMOUNT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.investWorksAmount != null) {
								anObject.investWorksAmount = anObject.investWorksAmount.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.investWorksAmount);
							continue;
						}
						if("INVESTWORKSDESCRIPTION".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.investWorksDescription);
							continue;
						}
						if("INVESTDATESTARTWORK".compareTo((String)fields.get(i)) == 0) {
							if (anObject.investDateStartWork == null) {
								statement.setTimestamp(i+1,null);
							} else {
								statement.setTimestamp(i+1, new java.sql.Timestamp(anObject.investDateStartWork.getTime()));
							}
							continue;
						}
						if("INVESTWORKMEASCODE".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.investWorkMeasCode);
							continue;
						}
						if("SERVICESFSIDEFINID".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.servicesFSideFinId);
							continue;
						}
						if("SERVICESFSIDECNNUM".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.servicesFSideCnNum);
							continue;
						}
						if("TOTALTIMEHOURS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.totalTimeHours != null) {
								anObject.totalTimeHours = anObject.totalTimeHours.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.totalTimeHours);
							continue;
						}
						if("TOTALTIMEDAYS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.totalTimeDays != null) {
								anObject.totalTimeDays = anObject.totalTimeDays.setScale(3, java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1, anObject.totalTimeDays);
							continue;
						}
						if("INVESTITEMNUMBER".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.investItemNumber);
							continue;
						}
						if("CAUSEDISCONNECTION".compareTo((String)fields.get(i)) == 0) {
							statement.setInt(i+1, anObject.causeDisconnection);
							continue;
						}
						if("DOMAIN_INFO".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1, anObject.domain_info);
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
						if("STATUS".compareTo((String)fields.get(i)) == 0) {
							if (anObject.status.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.status.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("ELEMENTREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.elementRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.elementRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("TYPEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.typeRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.typeRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("KIND".compareTo((String)fields.get(i)) == 0) {
							if (anObject.kind.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.kind.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("RENREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.renRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.renRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("FINEXECUTOR".compareTo((String)fields.get(i)) == 0) {
							if (anObject.finExecutor.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.finExecutor.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("STATEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.stateRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.stateRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("FORMREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.formRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.formRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("SOURCEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.sourceRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.sourceRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("DDSCODES".compareTo((String)fields.get(i)) == 0) {
							if (anObject.ddsCodes.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.ddsCodes.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("BUDGETREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.budgetRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.budgetRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("RESPONSIBILITYREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.responsibilityRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.responsibilityRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("DEPARTMENTREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.departmentRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.departmentRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("INVGROUPREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.invgroupRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.invgroupRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
						if("IPIMPLEMENTTYPEREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.ipImplementTypeRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1, anObject.ipImplementTypeRef.code);
							} else {
								statement.setNull(i+1, java.sql.Types.INTEGER);
							}
							continue;
						}
					}
					statement.setInt(fields.size()+1, anObject.code);
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

	} // end of save(ENPlanWork anObject,String[] anAttributes)


	public ENPlanWorkShort getShortObject(int anObjectCode) throws PersistenceException {
		ENPlanWork filterObject = new ENPlanWork();
		Vector<ENPlanWorkShort> list;
		filterObject.code = anObjectCode;
		list = getFilteredList(filterObject).list;
		if(list.size() > 0) {
			return (ENPlanWorkShort)list.get(0);
		}
		return null;
	}
	
	public int setParameters(ENPlanWork filter, PreparedStatement statement) throws SQLException {
		int index = 0;
		if(filter != null) {
			index++;
			index = BaseDAOUtils.setIntParameter(filter.code, index, statement);
			index = BaseDAOUtils.setDateTimeParameter(filter.dateGen, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateStart, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateFinal, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.yearGen, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.monthGen, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.yearOriginal, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.monthOriginal, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.commentGen, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.userGen, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateEdit, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.distanceAlong, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.distanceTo, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.workOrderNumber, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateWorkOrder, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.priConnectionNumber, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateEndPriConnection, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.investWorksAmount, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.investWorksDescription, index, statement);
			index = BaseDAOUtils.setDateTimeParameter(filter.investDateStartWork, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.investWorkMeasCode, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.servicesFSideFinId, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.servicesFSideCnNum, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.totalTimeHours, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.totalTimeDays, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.investItemNumber, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.causeDisconnection, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.domain_info, index, statement);
			index = BaseDAOUtils.setLongParameter(filter.modify_time, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.status.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.elementRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.typeRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.kind.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.renRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.finExecutor.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.stateRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.formRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.sourceRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.ddsCodes.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.budgetRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.responsibilityRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.departmentRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.invgroupRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.ipImplementTypeRef.code, index, statement);
			index--;
		}
		return index;
	}
	
	public String buildCondition(ENPlanWorkFilter filter) {
		String out = buildCondition((ENPlanWork)filter);
		if(filter != null) {
			out = BaseDAOUtils.addToCondition(filter.conditionSQL, out);
		}
		return out;
	}
	public String buildCondition(ENPlanWork filter) {
		String out = "";
		if(filter != null) {
			out = BaseDAOUtils.addIntToCondition(filter.code, ENPlanWork.code_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateGen, ENPlanWork.dateGen_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateStart, ENPlanWork.dateStart_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateFinal, ENPlanWork.dateFinal_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.yearGen, ENPlanWork.yearGen_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.monthGen, ENPlanWork.monthGen_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.yearOriginal, ENPlanWork.yearOriginal_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.monthOriginal, ENPlanWork.monthOriginal_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.commentGen, ENPlanWork.commentGen_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.userGen, ENPlanWork.userGen_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateEdit, ENPlanWork.dateEdit_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.distanceAlong, ENPlanWork.distanceAlong_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.distanceTo, ENPlanWork.distanceTo_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.workOrderNumber, ENPlanWork.workOrderNumber_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateWorkOrder, ENPlanWork.dateWorkOrder_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.priConnectionNumber, ENPlanWork.priConnectionNumber_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateEndPriConnection, ENPlanWork.dateEndPriConnection_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.investWorksAmount, ENPlanWork.investWorksAmount_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.investWorksDescription, ENPlanWork.investWorksDescription_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.investDateStartWork, ENPlanWork.investDateStartWork_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.investWorkMeasCode, ENPlanWork.investWorkMeasCode_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.servicesFSideFinId, ENPlanWork.servicesFSideFinId_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.servicesFSideCnNum, ENPlanWork.servicesFSideCnNum_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.totalTimeHours, ENPlanWork.totalTimeHours_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.totalTimeDays, ENPlanWork.totalTimeDays_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.investItemNumber, ENPlanWork.investItemNumber_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.causeDisconnection, ENPlanWork.causeDisconnection_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.domain_info, ENPlanWork.domain_info_QFielld, out);
			out = BaseDAOUtils.addLongToCondition(filter.modify_time, ENPlanWork.modify_time_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.status.code, ENPlanWork.status_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.elementRef.code, ENPlanWork.elementRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.typeRef.code, ENPlanWork.typeRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.kind.code, ENPlanWork.kind_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.renRef.code, ENPlanWork.renRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.finExecutor.code, ENPlanWork.finExecutor_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.stateRef.code, ENPlanWork.stateRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.formRef.code, ENPlanWork.formRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.sourceRef.code, ENPlanWork.sourceRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.ddsCodes.code, ENPlanWork.ddsCodes_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.budgetRef.code, ENPlanWork.budgetRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.responsibilityRef.code, ENPlanWork.responsibilityRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.departmentRef.code, ENPlanWork.departmentRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.invgroupRef.code, ENPlanWork.invgroupRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.ipImplementTypeRef.code, ENPlanWork.ipImplementTypeRef_QFielld, out);
		}
		return out;
	}

	public ENPlanWorkShortList getList(String aCondition) throws PersistenceException {
		return getScrollableFilteredList(null, aCondition, 0, -1);
	}

	public ENPlanWorkShortList getList() throws PersistenceException {
		return getScrollableFilteredList(null, null, 0, -1);
	}

	public ENPlanWorkShortList getScrollableList(int fromPosition, int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, null, fromPosition, quantity);
	}

	public ENPlanWorkShortList getFilteredList(ENPlanWork filterObject) throws PersistenceException {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	public ENPlanWorkShortList getScrollableFilteredList(ENPlanWork aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, null, fromPosition, quantity);
	}

	public ENPlanWorkShortList getScrollableFilteredList(ENPlanWork aFilterObject, String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, anCondition, null, fromPosition, quantity, null);
	}

	public ENPlanWorkShortList getScrollableFilteredList(String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, anCondition, fromPosition, quantity);
	}

	public ENPlanWorkShortList getScrollableFilteredList(ENPlanWorkFilter aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
	}
	
	public ENPlanWorkShortList getScrollableFilteredList(ENPlanWorkFilter aFilterObject,int fromPosition,int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}
	
	public ENPlanWorkShortList getScrollableFilteredList(ENPlanWork aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		ENPlanWorkShortList result = new ENPlanWorkShortList();
		ENPlanWorkShort anObject;
		result.list = new Vector<ENPlanWorkShort>();

		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet  set = null;
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORK.CODE";
		}

		if(quantity < 0) {
			quantity = Integer.MAX_VALUE/2;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		selectStr = "SELECT "+
			"ENPLANWORK.CODE"+
			",ENPLANWORK.DATEGEN"+
			",ENPLANWORK.DATESTART"+
			",ENPLANWORK.DATEFINAL"+
			",ENPLANWORK.YEARGEN"+
			",ENPLANWORK.MONTHGEN"+
			",ENPLANWORK.YEARORIGINAL"+
			",ENPLANWORK.MONTHORIGINAL"+
			",ENPLANWORK.USERGEN"+
			",ENPLANWORK.DATEEDIT"+
			",ENPLANWORK.WORKORDERNUMBER"+
			",ENPLANWORK.DATEWORKORDER"+
			",ENPLANWORK.PRICONNECTIONNUMBER"+
			",ENPLANWORK.DATEENDPRICONNECTION"+
			",ENPLANWORK.INVESTWORKSDESCRIPTION"+
			",ENPLANWORK.INVESTDATESTARTWORK"+
			",ENPLANWORK.INVESTWORKMEASCODE"+
			",ENPLANWORK.SERVICESFSIDEFINID"+
			",ENPLANWORK.SERVICESFSIDECNNUM"+
			",ENPLANWORK.TOTALTIMEHOURS"+
			",ENPLANWORK.TOTALTIMEDAYS"+
			",ENPLANWORK.INVESTITEMNUMBER"+
			", ENPLANWORKSTATUS.CODE " +
			", ENPLANWORKSTATUS.NAME " +
			", ENELEMENT.CODE " +
			", ENPLANWORKTYPE.CODE " +
			", ENPLANWORKTYPE.NAME " +
			", ENPLANWORKTYPE.SHORTNAME " +
			", ENPLANWORKKIND.CODE " +
			", ENPLANWORKKIND.NAME " +
			", EPREN.CODE " +
			", EPREN.NAME " +
			", FINEXECUTOR.CODE " +
			", FINEXECUTOR.NAME " +
			", FINEXECUTOR.FINCODE " +
			", FINEXECUTOR.FINTYPENAME " +
			", FINEXECUTOR.FINTYPECODE " +
			", FINEXECUTOR.FINKINDNAME " +
			", FINEXECUTOR.FINKINDCODE " +
			", FINEXECUTOR.FINCEHNAME " +
			", FINEXECUTOR.FINCEHCODE " +
			", FINEXECUTOR.AXORGID " +
			", FINEXECUTOR.AXPARENTORGID " +
			", FINEXECUTOR.AXPARENTORGNAME " +
			", FINEXECUTOR.AXORGTYPEID " +
			", FINEXECUTOR.AXORGTYPENAME " +
			", ENPLANWORKSTATE.CODE " +
			", ENPLANWORKSTATE.NAME " +
			", ENPLANWORKSTATE.SHORTNAME " +
			", ENPLANWORKFORM.CODE " +
			", ENPLANWORKFORM.NAME " +
			", ENPLANWORKSOURCE.CODE " +
			", ENPLANWORKSOURCE.NAME " +
			", RQDDSCODES.CODE " +
			", ENDEPARTMENT.CODE " +
			", ENDEPARTMENT.SHORTNAME " +
			", ENDEPARTMENT.DATESTART " +
			", ENDEPARTMENT.DATEFINAL " +
			", ENDEPARTMENT.RENCODE " +
			", ENDEPARTMENT.SHPZBALANS " +
			", ENDEPARTMENT.KAU_TABLE_ID_1884 " +
			", ENDEPARTMENT.KAU_1884 " +
			", ENDEPARTMENT.NAME_1884 " +
			", ENDEPARTMENT.HRMORGANIZATIONID " +
			", ENDEPARTMENT.CODE " +
			", ENDEPARTMENT.SHORTNAME " +
			", ENDEPARTMENT.DATESTART " +
			", ENDEPARTMENT.DATEFINAL " +
			", ENDEPARTMENT.RENCODE " +
			", ENDEPARTMENT.SHPZBALANS " +
			", ENDEPARTMENT.KAU_TABLE_ID_1884 " +
			", ENDEPARTMENT.KAU_1884 " +
			", ENDEPARTMENT.NAME_1884 " +
			", ENDEPARTMENT.HRMORGANIZATIONID " +
			", ENDEPARTMENT.CODE " +
			", ENDEPARTMENT.SHORTNAME " +
			", ENDEPARTMENT.DATESTART " +
			", ENDEPARTMENT.DATEFINAL " +
			", ENDEPARTMENT.RENCODE " +
			", ENDEPARTMENT.SHPZBALANS " +
			", ENDEPARTMENT.KAU_TABLE_ID_1884 " +
			", ENDEPARTMENT.KAU_1884 " +
			", ENDEPARTMENT.NAME_1884 " +
			", ENDEPARTMENT.HRMORGANIZATIONID " +
			", ENINVESTPROGRAMGROUPS.CODE " +
			", ENINVESTPROGRAMGROUPS.NAME " +
			", ENINVESTPROGRAMGROUPS.COMMENTGEN " +
			", ENIPIMPLEMENTATIONTYPE.CODE " +
			", ENIPIMPLEMENTATIONTYPE.NAME " +
		" FROM ENPLANWORK " +
			" LEFT JOIN ENPLANWORKSTATUS on ENPLANWORKSTATUS.CODE = ENPLANWORK.STATUSCODE "+
			" LEFT JOIN ENELEMENT on ENELEMENT.CODE = ENPLANWORK.ELEMENTREFCODE "+
			" LEFT JOIN ENPLANWORKTYPE on ENPLANWORKTYPE.CODE = ENPLANWORK.TYPEREFCODE "+
			" LEFT JOIN ENPLANWORKKIND on ENPLANWORKKIND.CODE = ENPLANWORK.KINDCODE "+
			" LEFT JOIN EPREN on EPREN.CODE = ENPLANWORK.RENREFCODE "+
			" LEFT JOIN FINEXECUTOR on FINEXECUTOR.CODE = ENPLANWORK.FINEXECUTORCODE "+
			" LEFT JOIN ENPLANWORKSTATE on ENPLANWORKSTATE.CODE = ENPLANWORK.STATEREFCODE "+
			" LEFT JOIN ENPLANWORKFORM on ENPLANWORKFORM.CODE = ENPLANWORK.FORMREFCODE "+
			" LEFT JOIN ENPLANWORKSOURCE on ENPLANWORKSOURCE.CODE = ENPLANWORK.SOURCEREFCODE "+
			" LEFT JOIN RQDDSCODES on RQDDSCODES.CODE = ENPLANWORK.DDSCODESCODE "+
			" LEFT JOIN ENDEPARTMENT on ENDEPARTMENT.CODE = ENPLANWORK.BUDGETREFCODE "+
			" LEFT JOIN ENDEPARTMENT on ENDEPARTMENT.CODE = ENPLANWORK.RESPONSIBILITYREFCODE "+
			" LEFT JOIN ENDEPARTMENT on ENDEPARTMENT.CODE = ENPLANWORK.DEPARTMENTREFCODE "+
			" LEFT JOIN ENINVESTPROGRAMGROUPS on ENINVESTPROGRAMGROUPS.CODE = ENPLANWORK.INVGROUPREFCODE "+
			" LEFT JOIN ENIPIMPLEMENTATIONTYPE on ENIPIMPLEMENTATIONTYPE.CODE = ENPLANWORK.IPIMPLEMENTTYPEREFCODE "+
		"";

	
		whereStr = BaseDAOUtils.addToCondition(buildCondition(aFilterObject), whereStr);

		SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ_LIST,getUserProfile().getDomainInfo().getDomain());
		if(segregationInfo.isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWork.getList%} access denied");
		}
		String domainWhereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());
		if (domainWhereStr.length() != 0){
			if (whereStr.length() == 0) {
				whereStr = domainWhereStr;
			} else {
				whereStr = " "+whereStr + " AND " +domainWhereStr;
			}
		}

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
				anObject = new ENPlanWorkShort();
				anObject.code = set.getInt(1);
				if ( set.wasNull() ) {
					anObject.code = Integer.MIN_VALUE;
				}
				anObject.dateGen = set.getTimestamp(2);
				anObject.dateStart = set.getDate(3);
				anObject.dateFinal = set.getDate(4);
				anObject.yearGen = set.getInt(5);
				if ( set.wasNull() ) {
					anObject.yearGen = Integer.MIN_VALUE;
				}
				anObject.monthGen = set.getInt(6);
				if ( set.wasNull() ) {
					anObject.monthGen = Integer.MIN_VALUE;
				}
				anObject.yearOriginal = set.getInt(7);
				if ( set.wasNull() ) {
					anObject.yearOriginal = Integer.MIN_VALUE;
				}
				anObject.monthOriginal = set.getInt(8);
				if ( set.wasNull() ) {
					anObject.monthOriginal = Integer.MIN_VALUE;
				}
				anObject.userGen = set.getString(9);
				anObject.dateEdit = set.getDate(10);
				anObject.workOrderNumber = set.getString(11);
				anObject.dateWorkOrder = set.getDate(12);
				anObject.priConnectionNumber = set.getString(13);
				anObject.dateEndPriConnection = set.getDate(14);
				anObject.investWorksDescription = set.getString(15);
				anObject.investDateStartWork = set.getTimestamp(16);
				anObject.investWorkMeasCode = set.getInt(17);
				if ( set.wasNull() ) {
					anObject.investWorkMeasCode = Integer.MIN_VALUE;
				}
				anObject.servicesFSideFinId = set.getInt(18);
				if ( set.wasNull() ) {
					anObject.servicesFSideFinId = Integer.MIN_VALUE;
				}
				anObject.servicesFSideCnNum = set.getString(19);
				anObject.totalTimeHours = set.getBigDecimal(20);
				if(anObject.totalTimeHours != null) {
					anObject.totalTimeHours = anObject.totalTimeHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.totalTimeDays = set.getBigDecimal(21);
				if(anObject.totalTimeDays != null) {
					anObject.totalTimeDays = anObject.totalTimeDays.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.investItemNumber = set.getString(22);

				anObject.statusCode = set.getInt(23);
				if(set.wasNull()) {
					anObject.statusCode = Integer.MIN_VALUE;
				}
				anObject.statusName = set.getString(24);
				anObject.elementRefCode = set.getInt(25);
				if(set.wasNull()) {
					anObject.elementRefCode = Integer.MIN_VALUE;
				}
				anObject.typeRefCode = set.getInt(26);
				if(set.wasNull()) {
					anObject.typeRefCode = Integer.MIN_VALUE;
				}
				anObject.typeRefName = set.getString(27);
				anObject.typeRefShortName = set.getString(28);
				anObject.kindCode = set.getInt(29);
				if(set.wasNull()) {
					anObject.kindCode = Integer.MIN_VALUE;
				}
				anObject.kindName = set.getString(30);
				anObject.renRefCode = set.getInt(31);
				if(set.wasNull()) {
					anObject.renRefCode = Integer.MIN_VALUE;
				}
				anObject.renRefName = set.getString(32);
				anObject.finExecutorCode = set.getInt(33);
				if(set.wasNull()) {
					anObject.finExecutorCode = Integer.MIN_VALUE;
				}
				anObject.finExecutorName = set.getString(34);
				anObject.finExecutorFinCode = set.getInt(35);
				if(set.wasNull()) {
					anObject.finExecutorFinCode = Integer.MIN_VALUE;
				}
				anObject.finExecutorFinTypeName = set.getString(36);
				anObject.finExecutorFinTypeCode = set.getInt(37);
				if(set.wasNull()) {
					anObject.finExecutorFinTypeCode = Integer.MIN_VALUE;
				}
				anObject.finExecutorFinKindName = set.getString(38);
				anObject.finExecutorFinKindCode = set.getInt(39);
				if(set.wasNull()) {
					anObject.finExecutorFinKindCode = Integer.MIN_VALUE;
				}
				anObject.finExecutorFinCehName = set.getString(40);
				anObject.finExecutorFinCehCode = set.getInt(41);
				if(set.wasNull()) {
					anObject.finExecutorFinCehCode = Integer.MIN_VALUE;
				}

				anObject.stateRefCode = set.getInt(47);
				if(set.wasNull()) {
					anObject.stateRefCode = Integer.MIN_VALUE;
				}
				anObject.stateRefName = set.getString(48);
				anObject.stateRefShortName = set.getString(49);
				anObject.formRefCode = set.getInt(50);
				if(set.wasNull()) {
					anObject.formRefCode = Integer.MIN_VALUE;
				}
				anObject.formRefName = set.getString(51);
				anObject.sourceRefCode = set.getInt(52);
				if(set.wasNull()) {
					anObject.sourceRefCode = Integer.MIN_VALUE;
				}
				anObject.sourceRefName = set.getString(53);
				anObject.ddsCodesCode = set.getInt(54);
				if(set.wasNull()) {
					anObject.ddsCodesCode = Integer.MIN_VALUE;
				}
				anObject.budgetRefCode = set.getInt(55);
				if(set.wasNull()) {
					anObject.budgetRefCode = Integer.MIN_VALUE;
				}
				anObject.budgetRefShortName = set.getString(56);
				anObject.budgetRefDateStart = set.getDate(57);
				anObject.budgetRefDateFinal = set.getDate(58);
				anObject.budgetRefRenCode = set.getInt(59);
				if(set.wasNull()) {
					anObject.budgetRefRenCode = Integer.MIN_VALUE;
				}
				anObject.budgetRefShpzBalans = set.getString(60);
				anObject.budgetRefKau_table_id_1884 = set.getInt(61);
				if(set.wasNull()) {
					anObject.budgetRefKau_table_id_1884 = Integer.MIN_VALUE;
				}
				anObject.budgetRefKau_1884 = set.getString(62);
				anObject.budgetRefName_1884 = set.getString(63);

				anObject.responsibilityRefCode = set.getInt(65);
				if(set.wasNull()) {
					anObject.responsibilityRefCode = Integer.MIN_VALUE;
				}
				anObject.responsibilityRefShortName = set.getString(66);
				anObject.responsibilityRefDateStart = set.getDate(67);
				anObject.responsibilityRefDateFinal = set.getDate(68);
				anObject.responsibilityRefRenCode = set.getInt(69);
				if(set.wasNull()) {
					anObject.responsibilityRefRenCode = Integer.MIN_VALUE;
				}
				anObject.responsibilityRefShpzBalans = set.getString(70);
				anObject.responsibilityRefKau_table_id_1884 = set.getInt(71);
				if(set.wasNull()) {
					anObject.responsibilityRefKau_table_id_1884 = Integer.MIN_VALUE;
				}
				anObject.responsibilityRefKau_1884 = set.getString(72);
				anObject.responsibilityRefName_1884 = set.getString(73);

				anObject.departmentRefCode = set.getInt(75);
				if(set.wasNull()) {
					anObject.departmentRefCode = Integer.MIN_VALUE;
				}
				anObject.departmentRefShortName = set.getString(76);
				anObject.departmentRefDateStart = set.getDate(77);
				anObject.departmentRefDateFinal = set.getDate(78);
				anObject.departmentRefRenCode = set.getInt(79);
				if(set.wasNull()) {
					anObject.departmentRefRenCode = Integer.MIN_VALUE;
				}
				anObject.departmentRefShpzBalans = set.getString(80);
				anObject.departmentRefKau_table_id_1884 = set.getInt(81);
				if(set.wasNull()) {
					anObject.departmentRefKau_table_id_1884 = Integer.MIN_VALUE;
				}
				anObject.departmentRefKau_1884 = set.getString(82);
				anObject.departmentRefName_1884 = set.getString(83);
				anObject.invgroupRefCode = set.getInt(85);
				if(set.wasNull()) {
					anObject.invgroupRefCode = Integer.MIN_VALUE;
				}
				anObject.invgroupRefName = set.getString(86);
				anObject.invgroupRefCommentgen = set.getString(87);
				anObject.ipImplementTypeRefCode = set.getInt(88);
				if(set.wasNull()) {
					anObject.ipImplementTypeRefCode = Integer.MIN_VALUE;
				}
				anObject.ipImplementTypeRefName = set.getString(89);

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
	
	public int[] getFilteredCodeArray(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject, fromPosition, quantity, null);
	}

	public int[] getFilteredCodeArray(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}


	public int[] getFilteredCodeArray(ENPlanWork aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		Vector<Integer> result = new Vector<Integer>();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = "SELECT ENPLANWORK.CODE FROM ENPLANWORK";
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORK.CODE";
		}
		if(quantity < 0) {
			quantity = Integer.MAX_VALUE/2;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ_LIST,getUserProfile().getDomainInfo().getDomain());
		if(segregationInfo.isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWork.getList%} access denied");
		}

		whereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());

		if(whereStr.length() == 0) {
			whereStr = " (ENPLANWORK.DOMAIN_INFO IS NOT NULL) ";
		} else {
			whereStr = " "+whereStr;
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


	public ENPlanWork getObject(int uid) throws PersistenceException {
		ENPlanWork result = new ENPlanWork();
		result.code = uid;
		loadObject(result);
		if(result.code == Integer.MIN_VALUE) {
			return null;
		}
		return result;
	}

    public void loadObject(ENPlanWork anObject) throws PersistenceException {
        loadObject(anObject, false);
    }


    public void loadObject(ENPlanWork anObject, boolean noSegregation) throws PersistenceException {
        loadObject(anObject, noSegregation, false);
    }

	public void loadObject(ENPlanWork anObject, boolean noSegregation, boolean noReferences) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

        SegregationInfo segregationInfo = null;
        String segregationWhereStr = null;
        
        if (!noSegregation) {
            segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ,getUserProfile().getDomainInfo().getDomain());
            if (segregationInfo.isAccessDenied()) {
                throw new PersistenceException("{%ENPlanWork.getObject%} access denied");
            }
        }

		selectStr = "SELECT  ENPLANWORK.CODE, ENPLANWORK.DATEGEN, ENPLANWORK.DATESTART, ENPLANWORK.DATEFINAL, ENPLANWORK.YEARGEN, ENPLANWORK.MONTHGEN, ENPLANWORK.YEARORIGINAL, ENPLANWORK.MONTHORIGINAL, ENPLANWORK.COMMENTGEN, ENPLANWORK.USERGEN, ENPLANWORK.DATEEDIT, ENPLANWORK.DISTANCEALONG, ENPLANWORK.DISTANCETO, ENPLANWORK.WORKORDERNUMBER, ENPLANWORK.DATEWORKORDER, ENPLANWORK.PRICONNECTIONNUMBER, ENPLANWORK.DATEENDPRICONNECTION, ENPLANWORK.INVESTWORKSAMOUNT, ENPLANWORK.INVESTWORKSDESCRIPTION, ENPLANWORK.INVESTDATESTARTWORK, ENPLANWORK.INVESTWORKMEASCODE, ENPLANWORK.SERVICESFSIDEFINID, ENPLANWORK.SERVICESFSIDECNNUM, ENPLANWORK.TOTALTIMEHOURS, ENPLANWORK.TOTALTIMEDAYS, ENPLANWORK.INVESTITEMNUMBER, ENPLANWORK.CAUSEDISCONNECTION, ENPLANWORK.DOMAIN_INFO, ENPLANWORK.MODIFY_TIME, ENPLANWORK.STATUSCODE, ENPLANWORK.ELEMENTREFCODE, ENPLANWORK.TYPEREFCODE, ENPLANWORK.KINDCODE, ENPLANWORK.RENREFCODE, ENPLANWORK.FINEXECUTORCODE, ENPLANWORK.STATEREFCODE, ENPLANWORK.FORMREFCODE, ENPLANWORK.SOURCEREFCODE, ENPLANWORK.DDSCODESCODE, ENPLANWORK.BUDGETREFCODE, ENPLANWORK.RESPONSIBILITYREFCODE, ENPLANWORK.DEPARTMENTREFCODE, ENPLANWORK.INVGROUPREFCODE, ENPLANWORK.IPIMPLEMENTTYPEREFCODE "
			+" FROM ENPLANWORK WHERE ENPLANWORK.CODE = ?";

        if (!noSegregation) {
            segregationWhereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());
            if (segregationWhereStr.length() > 0) {
                selectStr = selectStr + " AND " + segregationWhereStr;
            }
        }

		try {
			statement = connection.prepareStatement(selectStr);
			statement.setInt(1,anObject.code);
			set = statement.executeQuery();
			if(!set.next()) {
				anObject.code = Integer.MIN_VALUE;
			} else {
				anObject.code = set.getInt(1);
			
				anObject.dateGen = set.getTimestamp(2);
				anObject.dateStart = set.getDate(3);
				anObject.dateFinal = set.getDate(4);
				anObject.yearGen = set.getInt(5);
				if (set.wasNull()) {
					anObject.yearGen = Integer.MIN_VALUE;
				}
				anObject.monthGen = set.getInt(6);
				if (set.wasNull()) {
					anObject.monthGen = Integer.MIN_VALUE;
				}
				anObject.yearOriginal = set.getInt(7);
				if (set.wasNull()) {
					anObject.yearOriginal = Integer.MIN_VALUE;
				}
				anObject.monthOriginal = set.getInt(8);
				if (set.wasNull()) {
					anObject.monthOriginal = Integer.MIN_VALUE;
				}
				anObject.commentGen = set.getString(9);
				anObject.userGen = set.getString(10);
				anObject.dateEdit = set.getDate(11);
				anObject.distanceAlong = set.getBigDecimal(12);
				if(anObject.distanceAlong != null) {
					anObject.distanceAlong = anObject.distanceAlong.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.distanceTo = set.getBigDecimal(13);
				if(anObject.distanceTo != null) {
					anObject.distanceTo = anObject.distanceTo.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.workOrderNumber = set.getString(14);
				anObject.dateWorkOrder = set.getDate(15);
				anObject.priConnectionNumber = set.getString(16);
				anObject.dateEndPriConnection = set.getDate(17);
				anObject.investWorksAmount = set.getBigDecimal(18);
				if(anObject.investWorksAmount != null) {
					anObject.investWorksAmount = anObject.investWorksAmount.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.investWorksDescription = set.getString(19);
				anObject.investDateStartWork = set.getTimestamp(20);
				anObject.investWorkMeasCode = set.getInt(21);
				if (set.wasNull()) {
					anObject.investWorkMeasCode = Integer.MIN_VALUE;
				}
				anObject.servicesFSideFinId = set.getInt(22);
				if (set.wasNull()) {
					anObject.servicesFSideFinId = Integer.MIN_VALUE;
				}
				anObject.servicesFSideCnNum = set.getString(23);
				anObject.totalTimeHours = set.getBigDecimal(24);
				if(anObject.totalTimeHours != null) {
					anObject.totalTimeHours = anObject.totalTimeHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.totalTimeDays = set.getBigDecimal(25);
				if(anObject.totalTimeDays != null) {
					anObject.totalTimeDays = anObject.totalTimeDays.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.investItemNumber = set.getString(26);
				anObject.causeDisconnection = set.getInt(27);
				if (set.wasNull()) {
					anObject.causeDisconnection = Integer.MIN_VALUE;
				}
				anObject.domain_info = set.getString(28);
				anObject.modify_time = set.getLong(29);
				if(set.wasNull()) {
					anObject.modify_time = Long.MIN_VALUE;
				}
				anObject.status.code = set.getInt(30);
				if (set.wasNull()) {
					anObject.status.code = Integer.MIN_VALUE;
				}
				anObject.elementRef.code = set.getInt(31);
				if (set.wasNull()) {
					anObject.elementRef.code = Integer.MIN_VALUE;
				}
				anObject.typeRef.code = set.getInt(32);
				if (set.wasNull()) {
					anObject.typeRef.code = Integer.MIN_VALUE;
				}
				anObject.kind.code = set.getInt(33);
				if (set.wasNull()) {
					anObject.kind.code = Integer.MIN_VALUE;
				}
				anObject.renRef.code = set.getInt(34);
				if (set.wasNull()) {
					anObject.renRef.code = Integer.MIN_VALUE;
				}
				anObject.finExecutor.code = set.getInt(35);
				if (set.wasNull()) {
					anObject.finExecutor.code = Integer.MIN_VALUE;
				}
				anObject.stateRef.code = set.getInt(36);
				if (set.wasNull()) {
					anObject.stateRef.code = Integer.MIN_VALUE;
				}
				anObject.formRef.code = set.getInt(37);
				if (set.wasNull()) {
					anObject.formRef.code = Integer.MIN_VALUE;
				}
				anObject.sourceRef.code = set.getInt(38);
				if (set.wasNull()) {
					anObject.sourceRef.code = Integer.MIN_VALUE;
				}
				anObject.ddsCodes.code = set.getInt(39);
				if (set.wasNull()) {
					anObject.ddsCodes.code = Integer.MIN_VALUE;
				}
				anObject.budgetRef.code = set.getInt(40);
				if (set.wasNull()) {
					anObject.budgetRef.code = Integer.MIN_VALUE;
				}
				anObject.responsibilityRef.code = set.getInt(41);
				if (set.wasNull()) {
					anObject.responsibilityRef.code = Integer.MIN_VALUE;
				}
				anObject.departmentRef.code = set.getInt(42);
				if (set.wasNull()) {
					anObject.departmentRef.code = Integer.MIN_VALUE;
				}
				anObject.invgroupRef.code = set.getInt(43);
				if (set.wasNull()) {
					anObject.invgroupRef.code = Integer.MIN_VALUE;
				}
				anObject.ipImplementTypeRef.code = set.getInt(44);
				if (set.wasNull()) {
					anObject.ipImplementTypeRef.code = Integer.MIN_VALUE;
				}
				if(anObject.status.code != Integer.MIN_VALUE) {
					anObject.setStatus(
						new com.ksoe.energynet.dataminer.generated.ENPlanWorkStatusDAOGen(connection,getUserProfile()).getObject(anObject.status.code));
				}
				if(anObject.kind.code != Integer.MIN_VALUE) {
					anObject.setKind(
						new com.ksoe.energynet.dataminer.generated.ENPlanWorkKindDAOGen(connection,getUserProfile()).getObject(anObject.kind.code));
				}
				if(anObject.finExecutor.code != Integer.MIN_VALUE) {
					anObject.setFinExecutor(
						new com.ksoe.energynet.dataminer.generated.FINExecutorDAOGen(connection,getUserProfile()).getObject(anObject.finExecutor.code));
				}
				if(anObject.ddsCodes.code != Integer.MIN_VALUE) {
					anObject.setDdsCodes(
						new com.ksoe.rqorder.dataminer.generated.RQDDSCodesDAOGen(connection,getUserProfile()).getObject(anObject.ddsCodes.code));
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


	public com.ksoe.energynet.valueobject.references.ENPlanWorkRef getRef(int anObjectCode) throws PersistenceException {
		com.ksoe.energynet.valueobject.references.ENPlanWorkRef ref = new com.ksoe.energynet.valueobject.references.ENPlanWorkRef();
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

		selectStr = "DELETE FROM  ENPLANWORK WHERE CODE = ?";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		ENPlanWork object = getObject(uid);

		if(object == null) {
			throw new PersistenceException("{%ENPlanWork.getObject%} access denied");
		}
		
		if(new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.DELETE,getUserProfile().getDomainInfo().getDomain()).isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWork.remove%} access denied");
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
	
	public long count(ENPlanWorkFilter filter) throws PersistenceException {
		return count(filter, null);
	}
	public long count(ENPlanWorkFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		return (Long)getAggregateValue("count", "code", filter, bindObjects);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getAggregateValue(String aggFunction, String column, ENPlanWorkFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = String.format("SELECT %s(%s) FROM ENPLANWORK", aggFunction, column);
		String whereStr = "";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		
		SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ_LIST,getUserProfile().getDomainInfo().getDomain());
		if(segregationInfo.isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWork.getList%} access denied");
		}

		whereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());

		if(whereStr.length() == 0) {
			whereStr = " (ENPLANWORK.DOMAIN_INFO IS NOT NULL) ";
		} else {
			whereStr = " "+whereStr;
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
		String sql = String.format("SELECT %s FROM ENPLANWORK WHERE code = ?", propertyName);
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
	
	public <E> List<E> getListOfPropertyValues(String propertyName, ENPlanWorkFilter filter, int fromPosition,int quantity, Collection<? extends Object> bindObjects) throws PersistenceException {
		return this.getListOfPropertyValues(propertyName, filter, fromPosition, quantity, bindObjects, true);
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> getListOfPropertyValues(String propertyName, ENPlanWorkFilter filter, int fromPosition,int quantity, Collection<? extends Object> bindObjects, boolean isSegregation) throws PersistenceException {
		Connection connection = this.getConnection();
		String sql = String.format("SELECT %s FROM %s ", propertyName, "ENPLANWORK");
		String whereStr = "";
		PreparedStatement statement = null;
		ResultSet set = null;
		
		ArrayList<E> out = new ArrayList<E>();
		
		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}
		
		if(isSegregation) {
			SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ_LIST,getUserProfile().getDomainInfo().getDomain());
			if(segregationInfo.isAccessDenied()) {
				throw new PersistenceException("{%ENPlanWork.getList%} access denied");
			}

			whereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());

			if(whereStr.length() == 0) {
				whereStr = " (ENPLANWORK.DOMAIN_INFO IS NOT NULL) ";
			} else {
				whereStr = " "+whereStr;
			}		
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
		
		SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWork.class.getName(),DataAccessType.READ,getUserProfile().getDomainInfo().getDomain());
		if(segregationInfo.isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWork.getObject%} access denied");
		}
		selectStr =
			"SELECT  ENPLANWORK.CODE FROM  ENPLANWORK WHERE  ENPLANWORK.CODE = ?";
		String segregationWhereStr = SegregationQueryBuilder.addWhere("ENPLANWORK",segregationInfo,getUserProfile().getDomainInfo());
		if(segregationWhereStr.length() > 0) {
			selectStr = selectStr +
				" AND " + segregationWhereStr;
		}
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
		_checkConditionToken(condition,"code","ENPLANWORK.CODE");
		_checkConditionToken(condition,"dategen","ENPLANWORK.DATEGEN");
		_checkConditionToken(condition,"datestart","ENPLANWORK.DATESTART");
		_checkConditionToken(condition,"datefinal","ENPLANWORK.DATEFINAL");
		_checkConditionToken(condition,"yeargen","ENPLANWORK.YEARGEN");
		_checkConditionToken(condition,"monthgen","ENPLANWORK.MONTHGEN");
		_checkConditionToken(condition,"yearoriginal","ENPLANWORK.YEARORIGINAL");
		_checkConditionToken(condition,"monthoriginal","ENPLANWORK.MONTHORIGINAL");
		_checkConditionToken(condition,"commentgen","ENPLANWORK.COMMENTGEN");
		_checkConditionToken(condition,"usergen","ENPLANWORK.USERGEN");
		_checkConditionToken(condition,"dateedit","ENPLANWORK.DATEEDIT");
		_checkConditionToken(condition,"distancealong","ENPLANWORK.DISTANCEALONG");
		_checkConditionToken(condition,"distanceto","ENPLANWORK.DISTANCETO");
		_checkConditionToken(condition,"workordernumber","ENPLANWORK.WORKORDERNUMBER");
		_checkConditionToken(condition,"dateworkorder","ENPLANWORK.DATEWORKORDER");
		_checkConditionToken(condition,"priconnectionnumber","ENPLANWORK.PRICONNECTIONNUMBER");
		_checkConditionToken(condition,"dateendpriconnection","ENPLANWORK.DATEENDPRICONNECTION");
		_checkConditionToken(condition,"investworksamount","ENPLANWORK.INVESTWORKSAMOUNT");
		_checkConditionToken(condition,"investworksdescription","ENPLANWORK.INVESTWORKSDESCRIPTION");
		_checkConditionToken(condition,"investdatestartwork","ENPLANWORK.INVESTDATESTARTWORK");
		_checkConditionToken(condition,"investworkmeascode","ENPLANWORK.INVESTWORKMEASCODE");
		_checkConditionToken(condition,"servicesfsidefinid","ENPLANWORK.SERVICESFSIDEFINID");
		_checkConditionToken(condition,"servicesfsidecnnum","ENPLANWORK.SERVICESFSIDECNNUM");
		_checkConditionToken(condition,"totaltimehours","ENPLANWORK.TOTALTIMEHOURS");
		_checkConditionToken(condition,"totaltimedays","ENPLANWORK.TOTALTIMEDAYS");
		_checkConditionToken(condition,"investitemnumber","ENPLANWORK.INVESTITEMNUMBER");
		_checkConditionToken(condition,"causedisconnection","ENPLANWORK.CAUSEDISCONNECTION");
		_checkConditionToken(condition,"domain_info","ENPLANWORK.DOMAIN_INFO");
		_checkConditionToken(condition,"modify_time","ENPLANWORK.MODIFY_TIME");
		// relationship conditions
		_checkConditionToken(condition,"status","STATUSCODE");
		_checkConditionToken(condition,"status.code","STATUSCODE");
		_checkConditionToken(condition,"elementref","ELEMENTREFCODE");
		_checkConditionToken(condition,"elementref.code","ELEMENTREFCODE");
		_checkConditionToken(condition,"typeref","TYPEREFCODE");
		_checkConditionToken(condition,"typeref.code","TYPEREFCODE");
		_checkConditionToken(condition,"kind","KINDCODE");
		_checkConditionToken(condition,"kind.code","KINDCODE");
		_checkConditionToken(condition,"renref","RENREFCODE");
		_checkConditionToken(condition,"renref.code","RENREFCODE");
		_checkConditionToken(condition,"finexecutor","FINEXECUTORCODE");
		_checkConditionToken(condition,"finexecutor.code","FINEXECUTORCODE");
		_checkConditionToken(condition,"stateref","STATEREFCODE");
		_checkConditionToken(condition,"stateref.code","STATEREFCODE");
		_checkConditionToken(condition,"formref","FORMREFCODE");
		_checkConditionToken(condition,"formref.code","FORMREFCODE");
		_checkConditionToken(condition,"sourceref","SOURCEREFCODE");
		_checkConditionToken(condition,"sourceref.code","SOURCEREFCODE");
		_checkConditionToken(condition,"ddscodes","DDSCODESCODE");
		_checkConditionToken(condition,"ddscodes.code","DDSCODESCODE");
		_checkConditionToken(condition,"budgetref","BUDGETREFCODE");
		_checkConditionToken(condition,"budgetref.code","BUDGETREFCODE");
		_checkConditionToken(condition,"responsibilityref","RESPONSIBILITYREFCODE");
		_checkConditionToken(condition,"responsibilityref.code","RESPONSIBILITYREFCODE");
		_checkConditionToken(condition,"departmentref","DEPARTMENTREFCODE");
		_checkConditionToken(condition,"departmentref.code","DEPARTMENTREFCODE");
		_checkConditionToken(condition,"invgroupref","INVGROUPREFCODE");
		_checkConditionToken(condition,"invgroupref.code","INVGROUPREFCODE");
		_checkConditionToken(condition,"ipimplementtyperef","IPIMPLEMENTTYPEREFCODE");
		_checkConditionToken(condition,"ipimplementtyperef.code","IPIMPLEMENTTYPEREFCODE");
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
	
	private void _collectAutoIncrementFields(ENPlanWork anObject, Connection connection) throws PersistenceException {

		SequenceKey hashKey = new SequenceKey("ENPLANWORK", "CODE");
		Integer nextSeqValue = null;
		SequenceValue sequenceValue;
		synchronized (_sequenceTable) {
			sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
			if (sequenceValue == null) {
				sequenceValue = getNewSequenceValue("ENPLANWORK", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
			if (!sequenceValue.isNextValueAvailable()) {
				sequenceValue = getNewSequenceValue("ENPLANWORK", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
		}

		nextSeqValue = sequenceValue.getNextValue();
		if (nextSeqValue == null) {
			throw new PersistenceException(
				"Can't obtain auto increment value from: ENPLANWORK");
		} else {
			anObject.code = nextSeqValue.intValue();
			return;
		}
	}

} // end of ENPlanWorkDAO
