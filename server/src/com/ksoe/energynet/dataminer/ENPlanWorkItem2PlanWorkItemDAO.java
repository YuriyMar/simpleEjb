
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2PlanWorkItemDAOGen;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2PlanWorkItemShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2PlanWorkItemShortList;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;

  /**
  *  DAO Object for ENPlanWorkItem2PlanWorkItem;
  *
  */

public class ENPlanWorkItem2PlanWorkItemDAO extends ENPlanWorkItem2PlanWorkItemDAOGen {



    public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItem aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
    {
        ENPlanWorkItem2PlanWorkItemShortList result = new ENPlanWorkItem2PlanWorkItemShortList();
        ENPlanWorkItem2PlanWorkItemShort anObject;
        result.list = new Vector();

        String     selectStr;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet  set = null;
        String     whereStr = "";
        String     condition = processCondition(anCondition);
        String     orderBy = processCondition(anOrderBy);

        if(orderBy.length() == 0)
        orderBy = "ENPLANWORKITM2PLNWRKTM.CODE";

        if(quantity < 0)
        quantity = Integer.MAX_VALUE/2;

        if(getUserProfile() == null)
        throw new PersistenceException("Internal Error (User Profile Is Undefined)");

        selectStr = "SELECT "+
        "ENPLANWORKITM2PLNWRKTM.CODE"+
        ",ENPLANWORKITM2PLNWRKTM.COUNTGEN"+

        ", ENPLANWRKTM2PLNWRKTMTP.CODE " +
        ", ENPLANWRKTM2PLNWRKTMTP.NAME " +
        ", ENPLANWORKITEM.CODE " +
        ", ENPLANWORKITEM.COUNTGEN " +
        ", ENPLANWORKITEM.TIMEGEN " +
        ", ENPLANWORKITEM.USERGEN " +
        ", ENPLANWORKITEM.DATEEDIT " +
        ", pi1.CODE " +
        ", pi1.COUNTGEN " +
        ", pi1.TIMEGEN " +
        ", pi1.USERGEN " +
        ", pi1.DATEEDIT " +
        " FROM ENPLANWORKITM2PLNWRKTM " +
        ", ENPLANWRKTM2PLNWRKTMTP " +
        ", ENPLANWORKITEM " +
        ", ENPLANWORKITEM pi1 " +
        //" WHERE "
        "";
        whereStr = " ENPLANWRKTM2PLNWRKTMTP.CODE = ENPLANWORKITM2PLNWRKTM.TYPEREFCODE" ; //+
        whereStr = whereStr +" AND ENPLANWORKITEM.CODE = ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE" ; //+
        whereStr = whereStr +" AND ENPLANWORKITEM.CODE = pi1.PLANITEMOUTREFCODE" ; //+
            //selectStr = selectStr + " ${s} ENPLANWORKITM2PLNWRKTM.CODE IN ( SELECT ENPLANWORKITM2PLNWRKTM.CODE FROM ENPLANWORKITM2PLNWRKTM ";

//     " ";
        if(aFilterObject != null)
        {
            if(aFilterObject.code != Integer.MIN_VALUE) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "  ENPLANWORKITM2PLNWRKTM.CODE = ?";
            }
            if(aFilterObject.countGen != null) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "  ENPLANWORKITM2PLNWRKTM.COUNTGEN = ?";
            }
            if(aFilterObject.modify_time != Long.MIN_VALUE) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "  ENPLANWORKITM2PLNWRKTM.MODIFY_TIME = ?";
            }
            if(aFilterObject.typeRef.code != Integer.MIN_VALUE) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "ENPLANWORKITM2PLNWRKTM.TYPEREFCODE = ? ";
            }
            if(aFilterObject.planItemInRef.code != Integer.MIN_VALUE) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE = ? ";
            }
            if(aFilterObject.planItemOutRef.code != Integer.MIN_VALUE) {
                if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
                whereStr = whereStr + "ENPLANWORKITM2PLNWRKTM.PLANITEMOUTREFCODE = ? ";
            }

        }



        if(condition.length() != 0)
        {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";

            whereStr = whereStr + " (" + condition + ")";
        }
//     + " WHERE" +  сделано выше ????
        if(whereStr.length() != 0)
            selectStr = selectStr + " WHERE " + whereStr;

        // selectStr = selectStr + ") ";

        selectStr = selectStr + " ORDER BY " + orderBy;

        try
        {
        statement = connection.prepareStatement(selectStr);
        int number = 0;
        if(aFilterObject != null){
            if(aFilterObject.code != Integer.MIN_VALUE){
                number++;
                statement.setInt(number,aFilterObject.code);
            }
            if(aFilterObject.countGen != null){
                number++;
                aFilterObject.countGen = aFilterObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(number,aFilterObject.countGen);
            }
            if(aFilterObject.modify_time != Long.MIN_VALUE){
                number++;
                if(aFilterObject.modify_time == Long.MIN_VALUE)
                    statement.setBigDecimal(number,null);
                else
                    statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
            }
        if(aFilterObject.typeRef.code != Integer.MIN_VALUE) {
            number++;
            statement.setInt(number,aFilterObject.typeRef.code);
        }
        if(aFilterObject.planItemInRef.code != Integer.MIN_VALUE) {
            number++;
            statement.setInt(number,aFilterObject.planItemInRef.code);
        }
        if(aFilterObject.planItemOutRef.code != Integer.MIN_VALUE) {
            number++;
            statement.setInt(number,aFilterObject.planItemOutRef.code);
        }
        }

        if(condition.length() > 0 && aBindObjects != null)
        _bindObjectsToPreparedStatement(statement,aBindObjects,number);

        set = statement.executeQuery();
        int i;
        for(i = 0;set.next();i++)
        {
            if(i < fromPosition)
            continue;
            else if(i >= fromPosition + quantity)
            {
            i++;
            break;
            }

            anObject = new ENPlanWorkItem2PlanWorkItemShort();

            anObject.code = set.getInt(1);
            if ( set.wasNull() )
                anObject.code = Integer.MIN_VALUE;
            anObject.countGen = set.getBigDecimal(2);
            if(anObject.countGen != null)
                anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);

            anObject.typeRefCode = set.getInt(3);
            if(set.wasNull())
            anObject.typeRefCode = Integer.MIN_VALUE;
            anObject.typeRefName = set.getString(4);
            anObject.planItemInRefCode = set.getInt(5);
            if(set.wasNull())
            anObject.planItemInRefCode = Integer.MIN_VALUE;
            anObject.planItemInRefCountGen = set.getBigDecimal(6);
            if(anObject.planItemInRefCountGen != null)
            anObject.planItemInRefCountGen = anObject.planItemInRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
            anObject.planItemInRefTimeGen = set.getBigDecimal(7);
            if(anObject.planItemInRefTimeGen != null)
            anObject.planItemInRefTimeGen = anObject.planItemInRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
            anObject.planItemInRefUserGen = set.getString(8);
            anObject.planItemInRefDateEdit = set.getDate(9);
            anObject.planItemOutRefCode = set.getInt(10);
            if(set.wasNull())
            anObject.planItemOutRefCode = Integer.MIN_VALUE;
            anObject.planItemOutRefCountGen = set.getBigDecimal(11);
            if(anObject.planItemOutRefCountGen != null)
            anObject.planItemOutRefCountGen = anObject.planItemOutRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
            anObject.planItemOutRefTimeGen = set.getBigDecimal(12);
            if(anObject.planItemOutRefTimeGen != null)
            anObject.planItemOutRefTimeGen = anObject.planItemOutRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
            anObject.planItemOutRefUserGen = set.getString(13);
            anObject.planItemOutRefDateEdit = set.getDate(14);

            result.list.add(anObject);
        }

        result.setTotalCount(i);
        return result;
        }
        catch(SQLException e)
        {
        System.out.println(e.getMessage()+"\nstatement - "+selectStr);
        EnergyproPersistenceExceptionAnalyzer.analyser.throwPersistenceException(e);
        return null;
        }
        finally
        {
        try {if (set != null) set.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
        if(connection != super.getConnection())
        {
            try{connection.close();} catch(SQLException e){}
        }
        }
    }



  public ENPlanWorkItem2PlanWorkItemDAO(UserProfile anUserProfile,Connection aConnection) {super(anUserProfile,aConnection);}
  public ENPlanWorkItem2PlanWorkItemDAO(Connection aConnection,UserProfile anUserProfile) {super(aConnection,anUserProfile);}


} // end of ENPlanWorkItem2PlanWorkItemDAO

