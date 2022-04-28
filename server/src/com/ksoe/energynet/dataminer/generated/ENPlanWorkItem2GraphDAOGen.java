
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2018 SIT
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
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2GraphFilter;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2GraphShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2GraphShortList;


/**
 * DAO Object for ENPlanWorkItem2Graph;
 *
 */

public class ENPlanWorkItem2GraphDAOGen extends GenericDataMiner {

	public ENPlanWorkItem2GraphDAOGen(UserProfile anUserProfile, Connection aConnection) {
		super(anUserProfile,aConnection);
	}

	public ENPlanWorkItem2GraphDAOGen(Connection aConnection, UserProfile anUserProfile) {
		super(aConnection,anUserProfile);
	}

	public boolean isEqual(ENPlanWorkItem2Graph inObject) throws PersistenceException {
		ENPlanWorkItem2Graph obj = new ENPlanWorkItem2Graph();
		obj.code = inObject.code;
		loadObject(obj);

		if(inObject.dayWork == null && obj.dayWork == null){} else 
			if(inObject.dayWork == null || obj.dayWork == null) return false;
			else
				if (inObject.dayWork.compareTo(obj.dayWork) != 0){
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

		if(inObject.countgen == null && obj.countgen == null){}
		else
			if(inObject.countgen == null || obj.countgen == null) return false;
			else
				if ( ! inObject.countgen.equals(obj.countgen)){
					return false;
				}
		if (inObject.planWorkItemRef.code != obj.planWorkItemRef.code){
			return false;
		}
		if (inObject.planWorkRef.code != obj.planWorkRef.code){
			return false;
		}
		return true;
	}

	public int add(ENPlanWorkItem2Graph anObject) throws PersistenceException {
		return add(anObject,true);
	}

	public int add(ENPlanWorkItem2Graph anObject, boolean aUseSequential) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;

		anObject.modify_time = System.currentTimeMillis();
		if(aUseSequential) {
			_collectAutoIncrementFields(anObject,connection);
		}


		selectStr = "INSERT INTO ENPLANWORKITEM2GRAPH (CODE,DAYWORK,MODIFY_TIME,USERGEN,DATEEDIT,COUNTGEN,PLANWORKITEMREFCODE,PLANWORKREFCODE) VALUES (?,?,?,?,?,?,?,?)";

		try {
			statement = connection.prepareStatement(selectStr);
			if (anObject.code != Integer.MIN_VALUE ) {
				statement.setInt(1,anObject.code);
			} else {
				statement.setNull(1,java.sql.Types.INTEGER);
			}
			if (anObject.dayWork == null) {
				statement.setTimestamp(2,null);
			} else {
				statement.setTimestamp(2,new java.sql.Timestamp(anObject.dayWork.getTime()));
			}
			if (anObject.modify_time == Long.MIN_VALUE) {
				statement.setBigDecimal(3,null);
			} else {
				statement.setBigDecimal(3,new java.math.BigDecimal(anObject.modify_time));
			}
			statement.setString(4,anObject.userGen);
			if (anObject.dateEdit == null) {
				statement.setTimestamp(5,null);
			} else {
				statement.setTimestamp(5,new java.sql.Timestamp(anObject.dateEdit.getTime()));
			}
			if (anObject.countgen != null) {
				anObject.countgen = anObject.countgen.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
			}
			statement.setBigDecimal(6,anObject.countgen);
			if (anObject.planWorkItemRef.code != Integer.MIN_VALUE){
				if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).exists(anObject.planWorkItemRef.code)) {
					throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph.planWorkItemRef.code%} = {%"+anObject.planWorkItemRef.code+"%}");
				}
				statement.setInt(7,anObject.planWorkItemRef.code);
			} else {
				statement.setNull(7,java.sql.Types.INTEGER);
			}
			if (anObject.planWorkRef.code != Integer.MIN_VALUE){
				if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkDAOGen(connection,getUserProfile()).exists(anObject.planWorkRef.code)) {
					throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkItem2Graph.planWorkRef.code%} = {%"+anObject.planWorkRef.code+"%}");
				}
				statement.setInt(8,anObject.planWorkRef.code);
			} else {
				statement.setNull(8,java.sql.Types.INTEGER);
			}

			statement.execute();
			return anObject.code;
		} catch(SQLException e) {
			_sequenceTable.clear();
			throw new SystemException(e.getMessage(), e);
		} catch(Exception e) {
			_sequenceTable.clear();
			throw new PersistenceException("Error in method {%ENPlanWorkItem2GraphDAOGen.add%}",e);
		} finally {
			try {if (statement != null) statement.close();} catch (SQLException e) {}
			if(connection != super.getConnection()) {
				try{connection.close();} catch(SQLException e){}
			}
		}
	}

	public void save(ENPlanWorkItem2Graph anObject) throws PersistenceException {
		save(anObject,null);
	}

	public void save(ENPlanWorkItem2Graph anObject,String[] anAttributes) throws PersistenceException {
		if(anAttributes != null && anAttributes.length == 0) {
			return;
		}
		Connection connection = getConnection();
		try {
			PreparedStatement statement = null;

			ENPlanWorkItem2Graph oldObject = new ENPlanWorkItem2Graph();
			oldObject.code = anObject.code;
			String oldObjectSelectStr = "SELECT "+ENPlanWorkItem2Graph.modify_time_Field+" FROM  ENPLANWORKITEM2GRAPH WHERE CODE = ?";
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
					if(fieldNameStr.compareTo("DAYWORK") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("MODIFY_TIME") == 0) {
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
					if(fieldNameStr.compareTo("COUNTGEN") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PLANWORKITEMREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
					fieldNameStr = processCondition(anAttributes[attrIndex]);
					if(fieldNameStr.compareTo("PLANWORKREF") == 0) {
						fields.add(fieldNameStr);
						continue;
					}
				}
				if(fields.size() == 0) {
					return;
				}
			}
			if(fields == null) {
				selectStr = "UPDATE ENPLANWORKITEM2GRAPH SET  DAYWORK = ? , MODIFY_TIME = ? , USERGEN = ? , DATEEDIT = ? , COUNTGEN = ? , PLANWORKITEMREFCODE = ? , PLANWORKREFCODE = ?  "
					+" WHERE CODE = ?";
			} else {
				selectStr = "UPDATE ENPLANWORKITEM2GRAPH SET ";
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
					if (anObject.dayWork == null) {
						statement.setTimestamp(1,null);
					} else {
						statement.setTimestamp(1,new java.sql.Timestamp(anObject.dayWork.getTime()));
					}
					if (anObject.modify_time == Long.MIN_VALUE) {
						statement.setBigDecimal(2,null);
					} else {
						statement.setBigDecimal(2,new java.math.BigDecimal(anObject.modify_time));
					}
					statement.setString(3,anObject.userGen);
					if (anObject.dateEdit == null) {
						statement.setTimestamp(4,null);
					} else {
						statement.setTimestamp(4,new java.sql.Timestamp(anObject.dateEdit.getTime()));
					}
					if (anObject.countgen != null) {
						anObject.countgen = anObject.countgen.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
					}
					statement.setBigDecimal(5,anObject.countgen);
					if (anObject.planWorkItemRef.code != Integer.MIN_VALUE) {
						statement.setInt(6,anObject.planWorkItemRef.code);
					} else {
						statement.setNull(6,java.sql.Types.INTEGER);
					}
					if (anObject.planWorkRef.code != Integer.MIN_VALUE) {
						statement.setInt(7,anObject.planWorkRef.code);
					} else {
						statement.setNull(7,java.sql.Types.INTEGER);
					}
					statement.setInt(8,anObject.code);
				} else {
					for(int i = 0;i < fields.size();i++) {
						if("DAYWORK".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dayWork == null) {
								statement.setTimestamp(i+1,null);
							} else {
								statement.setTimestamp(i+1,new java.sql.Timestamp(anObject.dayWork.getTime()));
							}
							continue;
						}
						if("MODIFY_TIME".compareTo((String)fields.get(i)) == 0) {
							if (anObject.modify_time == Long.MIN_VALUE) {
								statement.setBigDecimal(i+1,null);
							} else {
								statement.setBigDecimal(i+1,new java.math.BigDecimal(anObject.modify_time));
							}
							continue;
						}
						if("USERGEN".compareTo((String)fields.get(i)) == 0) {
							statement.setString(i+1,anObject.userGen);
							continue;
						}
						if("DATEEDIT".compareTo((String)fields.get(i)) == 0) {
							if (anObject.dateEdit == null) {
								statement.setTimestamp(i+1,null);
							} else {
								statement.setTimestamp(i+1,new java.sql.Timestamp(anObject.dateEdit.getTime()));
							}
							continue;
						}
						if("COUNTGEN".compareTo((String)fields.get(i)) == 0) {
							if (anObject.countgen != null) {
								anObject.countgen = anObject.countgen.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
							}
							statement.setBigDecimal(i+1,anObject.countgen);
							continue;
						}
						if("PLANWORKITEMREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.planWorkItemRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1,anObject.planWorkItemRef.code);
							} else {
								statement.setNull(i+1,java.sql.Types.INTEGER);
							}
							continue;
						}
						if("PLANWORKREF".compareTo((String)fields.get(i)) == 0) {
							if (anObject.planWorkRef.code != Integer.MIN_VALUE) {
								statement.setInt(i+1,anObject.planWorkRef.code);
							} else {
								statement.setNull(i+1,java.sql.Types.INTEGER);
							}
							continue;
						}
					}
					statement.setInt(fields.size(),anObject.code);
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

	} // end of save(ENPlanWorkItem2Graph anObject,String[] anAttributes)


	public ENPlanWorkItem2GraphShort getShortObject(int anObjectCode) throws PersistenceException {
		ENPlanWorkItem2Graph filterObject = new ENPlanWorkItem2Graph();
		Vector<ENPlanWorkItem2GraphShort> list;
		filterObject.code = anObjectCode;
		list = getFilteredList(filterObject).list;
		if(list.size() > 0) {
			return (ENPlanWorkItem2GraphShort)list.get(0);
		}
		return null;
	}
	
	public int setParameters(ENPlanWorkItem2Graph filter, PreparedStatement statement) throws SQLException {
		int index = 0;
		if(filter != null) {
			index++;
			index = BaseDAOUtils.setIntParameter(filter.code, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dayWork, index, statement);
			index = BaseDAOUtils.setLongParameter(filter.modify_time, index, statement);
			index = BaseDAOUtils.setStringParameter(filter.userGen, index, statement);
			index = BaseDAOUtils.setDateParameter(filter.dateEdit, index, statement);
			index = BaseDAOUtils.setDecimalParameter(filter.countgen, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.planWorkItemRef.code, index, statement);
			index = BaseDAOUtils.setIntParameter(filter.planWorkRef.code, index, statement);
			index--;
		}
		return index;
	}
	
	public String buildCondition(ENPlanWorkItem2GraphFilter filter) {
		String out = buildCondition((ENPlanWorkItem2Graph)filter);
		if(filter != null) {
			out = BaseDAOUtils.addToCondition(filter.conditionSQL, out);
		}
		return out;
	}
	public String buildCondition(ENPlanWorkItem2Graph filter) {
		String out = "";
		if(filter != null) {
			out = BaseDAOUtils.addIntToCondition(filter.code, ENPlanWorkItem2Graph.code_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dayWork, ENPlanWorkItem2Graph.dayWork_QFielld, out);
			out = BaseDAOUtils.addLongToCondition(filter.modify_time, ENPlanWorkItem2Graph.modify_time_QFielld, out);
			out = BaseDAOUtils.addStringToCondition(filter.userGen, ENPlanWorkItem2Graph.userGen_QFielld, out);
			out = BaseDAOUtils.addDateToCondition(filter.dateEdit, ENPlanWorkItem2Graph.dateEdit_QFielld, out);
			out = BaseDAOUtils.addDecimalToCondition(filter.countgen, ENPlanWorkItem2Graph.countgen_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.planWorkItemRef.code, ENPlanWorkItem2Graph.planWorkItemRef_QFielld, out);
			out = BaseDAOUtils.addIntToCondition(filter.planWorkRef.code, ENPlanWorkItem2Graph.planWorkRef_QFielld, out);
		}
		return out;
	}

	public ENPlanWorkItem2GraphShortList getList(String aCondition) throws PersistenceException {
		return getScrollableFilteredList(null, aCondition, 0, -1);
	}

	public ENPlanWorkItem2GraphShortList getList() throws PersistenceException {
		return getScrollableFilteredList(null, null, 0, -1);
	}

	public ENPlanWorkItem2GraphShortList getScrollableList(int fromPosition, int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, null, fromPosition, quantity);
	}

	public ENPlanWorkItem2GraphShortList getFilteredList(ENPlanWorkItem2Graph filterObject) throws PersistenceException {
		return getScrollableFilteredList(filterObject, 0, -1);
	}

	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(ENPlanWorkItem2Graph aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, null, fromPosition, quantity);
	}

	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(ENPlanWorkItem2Graph aFilterObject, String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject, anCondition, null, fromPosition, quantity, null);
	}

	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(String anCondition,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(null, anCondition, fromPosition, quantity);
	}

	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(ENPlanWorkItem2GraphFilter aFilterObject,int fromPosition,int quantity) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
	}
	
	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(ENPlanWorkItem2GraphFilter aFilterObject,int fromPosition,int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}
	
	public ENPlanWorkItem2GraphShortList getScrollableFilteredList(ENPlanWorkItem2Graph aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		ENPlanWorkItem2GraphShortList result = new ENPlanWorkItem2GraphShortList();
		ENPlanWorkItem2GraphShort anObject;
		result.list = new Vector<ENPlanWorkItem2GraphShort>();

		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet  set = null;
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORKITEM2GRAPH.CODE";
		}

		if(quantity < 0) {
			quantity = Integer.MAX_VALUE/2;
		}

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		selectStr = "SELECT "+
			"ENPLANWORKITEM2GRAPH.CODE"+
			",ENPLANWORKITEM2GRAPH.DAYWORK"+
			",ENPLANWORKITEM2GRAPH.USERGEN"+
			",ENPLANWORKITEM2GRAPH.DATEEDIT"+
			",ENPLANWORKITEM2GRAPH.COUNTGEN"+
			", ENPLANWORKITEM.CODE " +
			", ENPLANWORKITEM.COUNTGEN " +
			", ENPLANWORKITEM.TIMEGEN " +
			", ENPLANWORKITEM.COSTGEN " +
			", ENPLANWORKITEM.USERGEN " +
			", ENPLANWORKITEM.DATEEDIT " +
			", ENPLANWORK.CODE " +
			", ENPLANWORK.DATEGEN " +
			", ENPLANWORK.DATESTART " +
			", ENPLANWORK.DATEFINAL " +
			", ENPLANWORK.YEARGEN " +
			", ENPLANWORK.MONTHGEN " +
			", ENPLANWORK.YEARORIGINAL " +
			", ENPLANWORK.MONTHORIGINAL " +
			", ENPLANWORK.USERGEN " +
			", ENPLANWORK.DATEEDIT " +
			", ENPLANWORK.WORKORDERNUMBER " +
			", ENPLANWORK.DATEWORKORDER " +
			", ENPLANWORK.PRICONNECTIONNUMBER " +
			", ENPLANWORK.DATEENDPRICONNECTION " +
			", ENPLANWORK.INVESTWORKSDESCRIPTION " +
			", ENPLANWORK.SERVICESFSIDEFINID " +
			", ENPLANWORK.SERVICESFSIDECNNUM " +
			", ENPLANWORK.TOTALTIMEHOURS " +
			", ENPLANWORK.TOTALTIMEDAYS " +
			", ENPLANWORK.INVESTITEMNUMBER " +
		" FROM ENPLANWORKITEM2GRAPH " +
			", ENPLANWORKITEM " +
			", ENPLANWORK " +
		"";
		whereStr = " ENPLANWORKITEM.CODE = ENPLANWORKITEM2GRAPH.PLANWORKITEMREFCODE" ; //+
		whereStr += " AND ENPLANWORK.CODE = ENPLANWORKITEM2GRAPH.PLANWORKREFCODE" ; //+

	
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
				anObject = new ENPlanWorkItem2GraphShort();
				anObject.code = set.getInt(1);
				if ( set.wasNull() ) {
					anObject.code = Integer.MIN_VALUE;
				}
				anObject.dayWork = set.getTimestamp(2);
				anObject.userGen = set.getString(3);
				anObject.dateEdit = set.getTimestamp(4);
				anObject.countgen = set.getBigDecimal(5);
				if(anObject.countgen != null) {
					anObject.countgen = anObject.countgen.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}

				anObject.planWorkItemRefCode = set.getInt(6);
				if(set.wasNull()) {
					anObject.planWorkItemRefCode = Integer.MIN_VALUE;
				}
				anObject.planWorkItemRefCountGen = set.getBigDecimal(7);
				if(anObject.planWorkItemRefCountGen != null) {
					anObject.planWorkItemRefCountGen = anObject.planWorkItemRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkItemRefTimeGen = set.getBigDecimal(8);
				if(anObject.planWorkItemRefTimeGen != null) {
					anObject.planWorkItemRefTimeGen = anObject.planWorkItemRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkItemRefCostGen = set.getBigDecimal(9);
				if(anObject.planWorkItemRefCostGen != null) {
					anObject.planWorkItemRefCostGen = anObject.planWorkItemRefCostGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkItemRefUserGen = set.getString(10);
				anObject.planWorkItemRefDateEdit = set.getDate(11);
				anObject.planWorkRefCode = set.getInt(12);
				if(set.wasNull()) {
					anObject.planWorkRefCode = Integer.MIN_VALUE;
				}
				anObject.planWorkRefDateGen = set.getTimestamp(13);
				anObject.planWorkRefDateStart = set.getDate(14);
				anObject.planWorkRefDateFinal = set.getDate(15);
				anObject.planWorkRefYearGen = set.getInt(16);
				if(set.wasNull()) {
					anObject.planWorkRefYearGen = Integer.MIN_VALUE;
				}
				anObject.planWorkRefMonthGen = set.getInt(17);
				if(set.wasNull()) {
					anObject.planWorkRefMonthGen = Integer.MIN_VALUE;
				}
				anObject.planWorkRefYearOriginal = set.getInt(18);
				if(set.wasNull()) {
					anObject.planWorkRefYearOriginal = Integer.MIN_VALUE;
				}
				anObject.planWorkRefMonthOriginal = set.getInt(19);
				if(set.wasNull()) {
					anObject.planWorkRefMonthOriginal = Integer.MIN_VALUE;
				}
				anObject.planWorkRefUserGen = set.getString(20);
				anObject.planWorkRefDateEdit = set.getDate(21);
				anObject.planWorkRefWorkOrderNumber = set.getString(22);
				anObject.planWorkRefDateWorkOrder = set.getDate(23);
				anObject.planWorkRefPriConnectionNumber = set.getString(24);
				anObject.planWorkRefDateEndPriConnection = set.getDate(25);
				anObject.planWorkRefInvestWorksDescription = set.getString(26);
				anObject.planWorkRefServicesFSideFinId = set.getInt(27);
				if(set.wasNull()) {
					anObject.planWorkRefServicesFSideFinId = Integer.MIN_VALUE;
				}
				anObject.planWorkRefServicesFSideCnNum = set.getString(28);
				anObject.planWorkRefTotalTimeHours = set.getBigDecimal(29);
				if(anObject.planWorkRefTotalTimeHours != null) {
					anObject.planWorkRefTotalTimeHours = anObject.planWorkRefTotalTimeHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkRefTotalTimeDays = set.getBigDecimal(30);
				if(anObject.planWorkRefTotalTimeDays != null) {
					anObject.planWorkRefTotalTimeDays = anObject.planWorkRefTotalTimeDays.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkRefInvestItemNumber = set.getString(31);

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
	
	public int[] getFilteredCodeArray(ENPlanWorkItem2GraphFilter aFilterObject, int fromPosition, int quantity) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject, fromPosition, quantity, null);
	}

	public int[] getFilteredCodeArray(ENPlanWorkItem2GraphFilter aFilterObject, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		return getFilteredCodeArray(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
	}


	public int[] getFilteredCodeArray(ENPlanWorkItem2Graph aFilterObject, String anCondition, String anOrderBy, int fromPosition, int quantity, Vector<? extends Object> aBindObjects) throws PersistenceException {
		Vector<Integer> result = new Vector<Integer>();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = "SELECT ENPLANWORKITEM2GRAPH.CODE FROM ENPLANWORKITEM2GRAPH";
		String whereStr = "";
		String condition = processCondition(anCondition);
		String orderBy = processCondition(anOrderBy);

		if(orderBy.length() == 0) {
			orderBy = "ENPLANWORKITEM2GRAPH.CODE";
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
				if(i < fromPosition) {
					continue;
				} else if(i >= fromPosition + quantity) {
					i++;
					break;
				}
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

	public ENPlanWorkItem2Graph getObject(int uid) throws PersistenceException {
		ENPlanWorkItem2Graph result = new ENPlanWorkItem2Graph();
		result.code = uid;
		loadObject(result);
		if(result.code == Integer.MIN_VALUE) {
			return null;
		}
		return result;
	}

	public void loadObject(ENPlanWorkItem2Graph anObject) throws PersistenceException {
		String selectStr;
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;



		selectStr = "SELECT  ENPLANWORKITEM2GRAPH.CODE, ENPLANWORKITEM2GRAPH.DAYWORK, ENPLANWORKITEM2GRAPH.MODIFY_TIME, ENPLANWORKITEM2GRAPH.USERGEN, ENPLANWORKITEM2GRAPH.DATEEDIT, ENPLANWORKITEM2GRAPH.COUNTGEN, ENPLANWORKITEM2GRAPH.PLANWORKITEMREFCODE, ENPLANWORKITEM2GRAPH.PLANWORKREFCODE "
			+" FROM ENPLANWORKITEM2GRAPH WHERE ENPLANWORKITEM2GRAPH.CODE = ?";

		try {
			statement = connection.prepareStatement(selectStr);
			statement.setInt(1,anObject.code);
			set = statement.executeQuery();
			if(!set.next()) {
				anObject.code = Integer.MIN_VALUE;
			} else {
				anObject.code = set.getInt(1);
			
				anObject.dayWork = set.getTimestamp(2);
				anObject.modify_time = set.getLong(3);
				if(set.wasNull()) {
					anObject.modify_time = Long.MIN_VALUE;
				}
				anObject.userGen = set.getString(4);
				anObject.dateEdit = set.getTimestamp(5);
				anObject.countgen = set.getBigDecimal(6);
				if(anObject.countgen != null) {
					anObject.countgen = anObject.countgen.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.planWorkItemRef.code = set.getInt(7);
				if (set.wasNull()) {
					anObject.planWorkItemRef.code = Integer.MIN_VALUE;
				}
				anObject.planWorkRef.code = set.getInt(8);
				if (set.wasNull()) {
					anObject.planWorkRef.code = Integer.MIN_VALUE;
				}
				if(anObject.planWorkItemRef.code != Integer.MIN_VALUE) {
					anObject.setPlanWorkItemRef(
						new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).getRef(anObject.planWorkItemRef.code));
				}
				if(anObject.planWorkRef.code != Integer.MIN_VALUE) {
					anObject.setPlanWorkRef(
						new com.ksoe.energynet.dataminer.generated.ENPlanWorkDAOGen(connection,getUserProfile()).getRef(anObject.planWorkRef.code));
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


	public com.ksoe.energynet.valueobject.references.ENPlanWorkItem2GraphRef getRef(int anObjectCode) throws PersistenceException {
		com.ksoe.energynet.valueobject.references.ENPlanWorkItem2GraphRef ref = new com.ksoe.energynet.valueobject.references.ENPlanWorkItem2GraphRef();
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

		selectStr = "DELETE FROM  ENPLANWORKITEM2GRAPH WHERE CODE = ?";

		if(getUserProfile() == null) {
			throw new PersistenceException("Internal Error (User Profile Is Undefined)");
		}

		ENPlanWorkItem2Graph object = getObject(uid);

		if(object == null) {
			throw new PersistenceException("{%ENPlanWorkItem2Graph.getObject%} access denied");
		}

		if(new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkItem2Graph.class.getName(),DataAccessType.DELETE,getUserProfile().getDomainInfo().getDomain()).isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWorkItem2Graph.remove%} access denied");
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
	
	public long count(ENPlanWorkItem2GraphFilter filter) throws PersistenceException {
		return count(filter, null);
	}
	public long count(ENPlanWorkItem2GraphFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		return (Long)getAggregateValue("count", "code", filter, bindObjects);
	}
	
	@SuppressWarnings("unchecked")
	public <E> E getAggregateValue(String aggFunction, String column, ENPlanWorkItem2GraphFilter filter, Vector<? extends Object> bindObjects) throws PersistenceException {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet set = null;
		String selectStr = String.format("SELECT %s(%s) FROM ENPLANWORKITEM2GRAPH", aggFunction, column);
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
	public <E> List<E> getListOfPropertyValues(String propertyName, ENPlanWorkItem2GraphFilter filter, int fromPosition,int quantity, Vector<Object> bindObjects) throws PersistenceException {
		Connection connection = this.getConnection();
		String sql = String.format("SELECT %s FROM %s ", propertyName, "ENPLANWORKITEM2GRAPH");
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
		
		try {
			statement = connection.prepareStatement(sql);
			int number = setParameters(filter, statement);

			if(bindObjects != null) {
				_bindObjectsToPreparedStatement(statement,bindObjects,number);
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

		SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkItem2Graph.class.getName(),DataAccessType.READ,getUserProfile().getDomainInfo().getDomain());
		if(segregationInfo.isAccessDenied()) {
			throw new PersistenceException("{%ENPlanWorkItem2Graph.getObject%} access denied");
		}

		selectStr =
			"SELECT  ENPLANWORKITEM2GRAPH.CODE FROM  ENPLANWORKITEM2GRAPH WHERE  ENPLANWORKITEM2GRAPH.CODE = ?";
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
		_checkConditionToken(condition,"code","ENPLANWORKITEM2GRAPH.CODE");
		_checkConditionToken(condition,"daywork","ENPLANWORKITEM2GRAPH.DAYWORK");
		_checkConditionToken(condition,"modify_time","ENPLANWORKITEM2GRAPH.MODIFY_TIME");
		_checkConditionToken(condition,"usergen","ENPLANWORKITEM2GRAPH.USERGEN");
		_checkConditionToken(condition,"dateedit","ENPLANWORKITEM2GRAPH.DATEEDIT");
		_checkConditionToken(condition,"countgen","ENPLANWORKITEM2GRAPH.COUNTGEN");
		// relationship conditions
		_checkConditionToken(condition,"planworkitemref","PLANWORKITEMREFCODE");
		_checkConditionToken(condition,"planworkitemref.code","PLANWORKITEMREFCODE");
		_checkConditionToken(condition,"planworkref","PLANWORKREFCODE");
		_checkConditionToken(condition,"planworkref.code","PLANWORKREFCODE");
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
	
	private void _collectAutoIncrementFields(ENPlanWorkItem2Graph anObject, Connection connection) throws PersistenceException {

		SequenceKey hashKey = new SequenceKey("ENPLANWORKITEM2GRAPH", "CODE");
		Integer nextSeqValue = null;
		SequenceValue sequenceValue;
		synchronized (_sequenceTable) {
			sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
			if (sequenceValue == null) {
				sequenceValue = getNewSequenceValue("ENPLANWORKITEM2GRAPH", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
			if (!sequenceValue.isNextValueAvailable()) {
				sequenceValue = getNewSequenceValue("ENPLANWORKITEM2GRAPH", "CODE");
				_sequenceTable.put(hashKey, sequenceValue);
			}
		}

		nextSeqValue = sequenceValue.getNextValue();
		if (nextSeqValue == null) {
			throw new PersistenceException(
				"Can't obtain auto increment value from: ENPLANWORKITEM2GRAPH");
		} else {
			anObject.code = nextSeqValue.intValue();
			return;
		}
	}

} // end of ENPlanWorkItem2GraphDAO
