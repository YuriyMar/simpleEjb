//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;









import com.ksoe.authorization.valueobject.DataAccessType;
import com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2TKKoefDAOGen;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2TKKoefShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2TKKoefShortList;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.persistence.sql.SegregationQueryBuilder;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.lla.segregation.SegregationInfo;
import com.ksoe.lla.segregation.SegregationProcessor;

/**
 * DAO Object for ENPlanWorkItem2TKKoef;
 *
 */

public class ENPlanWorkItem2TKKoefDAO extends ENPlanWorkItem2TKKoefDAOGen {

    public ENPlanWorkItem2TKKoefDAO(UserProfile anUserProfile,
            Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkItem2TKKoefDAO(Connection aConnection,
            UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }
    
    
    public ENPlanWorkItem2TKKoefShortList getScrollableFilteredList(ENPlanWorkItem2TKKoef aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
    {
     ENPlanWorkItem2TKKoefShortList result = new ENPlanWorkItem2TKKoefShortList();
     ENPlanWorkItem2TKKoefShort anObject;
     result.list = new Vector();

     String     selectStr;
     Connection connection = getConnection();
     PreparedStatement statement = null;
     ResultSet  set = null;
     String     whereStr = "";
     String     condition = processCondition(anCondition);
     String     orderBy = processCondition(anOrderBy);

     if(orderBy.length() == 0)
      orderBy = "ENPLANWORKITEM2TKKOEF.CODE";

     if(quantity < 0)
      quantity = Integer.MAX_VALUE/2;

     if(getUserProfile() == null)
      throw new PersistenceException("Internal Error (User Profile Is Undefined)");

     selectStr = "SELECT "+
      "ENPLANWORKITEM2TKKOEF.CODE"+

       ", TKTECHCARDSOURCEKOEF.CODE " +
       ", TKTECHCARDSOURCEKOEF.NUM " +
       ", TKTECHCARDSOURCEKOEF.NAME " +
       ", TKTECHCARDSOURCEKOEF.KOEF " +
       ", ENPLANWORKITEM.CODE " +
       ", ENPLANWORKITEM.COUNTGEN " +
       ", ENPLANWORKITEM.TIMEGEN " +
       ", ENPLANWORKITEM.COSTGEN " +
       ", ENPLANWORKITEM.USERGEN " +
       ", ENPLANWORKITEM.DATEEDIT " +
       ", dep.name  as budjname " +
       ", plst.name  as enplanworkstatename " +
      " FROM ENPLANWORKITEM2TKKOEF   " +  
      ", TKTECHCARDSOURCEKOEF left join endepartment dep on (TKTECHCARDSOURCEKOEF.budgetrefcode=dep.code) " + 
	  "					      left join enplanworkstate plst on (TKTECHCARDSOURCEKOEF.workstaterefcode=plst.code) " + 
      ", ENPLANWORKITEM " +
      //" WHERE "
     "";
      whereStr = " TKTECHCARDSOURCEKOEF.CODE = ENPLANWORKITEM2TKKOEF.SOURCEKOEFCODE" ; //+
       whereStr = whereStr +" AND ENPLANWORKITEM.CODE = ENPLANWORKITEM2TKKOEF.PLANWORKITEMREFCODE" ; //+
         //selectStr = selectStr + " ${s} ENPLANWORKITEM2TKKOEF.CODE IN ( SELECT ENPLANWORKITEM2TKKOEF.CODE FROM ENPLANWORKITEM2TKKOEF ";

 // " ";
       if(aFilterObject != null)
       {
         if(aFilterObject.code != Integer.MIN_VALUE) {
             if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
             whereStr = whereStr + "  ENPLANWORKITEM2TKKOEF.CODE = ?";
         }
          if (aFilterObject.commentGen != null) {
              if(whereStr.length() != 0)
                  whereStr = whereStr + " AND ";
              if (aFilterObject.commentGen.indexOf('*',0) < 0 && aFilterObject.commentGen.indexOf('?',0) < 0)
                  whereStr = whereStr + "  UPPER(ENPLANWORKITEM2TKKOEF.COMMENTGEN) = UPPER(?)";
              else
                  whereStr = whereStr + " UPPER(ENPLANWORKITEM2TKKOEF.COMMENTGEN) LIKE UPPER(?)";
          }
          if (aFilterObject.domain_info != null) {
              if(whereStr.length() != 0)
                  whereStr = whereStr + " AND ";
              if (aFilterObject.domain_info.indexOf('*',0) < 0 && aFilterObject.domain_info.indexOf('?',0) < 0)
                  whereStr = whereStr + "  UPPER(ENPLANWORKITEM2TKKOEF.DOMAIN_INFO) = UPPER(?)";
              else
                  whereStr = whereStr + " UPPER(ENPLANWORKITEM2TKKOEF.DOMAIN_INFO) LIKE UPPER(?)";
          }
         if(aFilterObject.modify_time != Long.MIN_VALUE) {
             if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
             whereStr = whereStr + "  ENPLANWORKITEM2TKKOEF.MODIFY_TIME = ?";
         }
         if(aFilterObject.sourceKoef.code != Integer.MIN_VALUE) {
             if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
             whereStr = whereStr + "ENPLANWORKITEM2TKKOEF.SOURCEKOEFCODE = ? ";
         }
         if(aFilterObject.planWorkItemRef.code != Integer.MIN_VALUE) {
             if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
             whereStr = whereStr + "ENPLANWORKITEM2TKKOEF.PLANWORKITEMREFCODE = ? ";
         }

       }


     SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkItem2TKKoef.class.getName(),DataAccessType.READ_LIST,getUserProfile().getDomainInfo().getDomain());
     if(segregationInfo.isAccessDenied())
       throw new PersistenceException("{%ENPlanWorkItem2TKKoef.getList%} access denied");

     String domainWhereStr = SegregationQueryBuilder.addWhere("ENPLANWORKITEM2TKKOEF",segregationInfo,getUserProfile().getDomainInfo());

     if (domainWhereStr.length() != 0){
     // domainWhereStr = "  AND ENPLANWORKITEM2TKKOEF.DOMAIN_INFO IS NOT NULL";
     //else
     if (whereStr.length() == 0)
         whereStr = domainWhereStr;
     else
         whereStr = " "+whereStr + " AND " +domainWhereStr;
     }


       if(condition.length() != 0)
       {
          if(whereStr.length() != 0)
             whereStr = whereStr + " AND ";

          whereStr = whereStr + " (" + condition + ")";
       }
 // + " WHERE" +  сделано выше ????
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

            if(aFilterObject.commentGen != null){
                number++;
                StringBuffer likeStr = new StringBuffer();
                likeStr.append(aFilterObject.commentGen);
                for(int i = 0;i < likeStr.length();i++){
                     if(likeStr.charAt(i) == '*')
                          likeStr.setCharAt(i,'%');
                     if(likeStr.charAt(i) == '?')
                          likeStr.setCharAt(i,'_');
                }
                statement.setString(number,likeStr.toString());
            }

            if(aFilterObject.domain_info != null){
                number++;
                StringBuffer likeStr = new StringBuffer();
                likeStr.append(aFilterObject.domain_info);
                for(int i = 0;i < likeStr.length();i++){
                     if(likeStr.charAt(i) == '*')
                          likeStr.setCharAt(i,'%');
                     if(likeStr.charAt(i) == '?')
                          likeStr.setCharAt(i,'_');
                }
                statement.setString(number,likeStr.toString());
            }
         if(aFilterObject.modify_time != Long.MIN_VALUE){
             number++;
             if(aFilterObject.modify_time == Long.MIN_VALUE)
                 statement.setBigDecimal(number,null);
             else
                 statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
         }
        if(aFilterObject.sourceKoef.code != Integer.MIN_VALUE) {
            number++;
            statement.setInt(number,aFilterObject.sourceKoef.code);
        }
        if(aFilterObject.planWorkItemRef.code != Integer.MIN_VALUE) {
            number++;
            statement.setInt(number,aFilterObject.planWorkItemRef.code);
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

         anObject = new ENPlanWorkItem2TKKoefShort();

         anObject.code = set.getInt(1);
         if ( set.wasNull() )
             anObject.code = Integer.MIN_VALUE;

         anObject.sourceKoefCode = set.getInt(2);
         if(set.wasNull())
         anObject.sourceKoefCode = Integer.MIN_VALUE;
         anObject.sourceKoefNum = set.getString(3);
         anObject.sourceKoefName = set.getString(4);
         anObject.sourceKoefKoef = set.getBigDecimal(5);
         if(anObject.sourceKoefKoef != null)
           anObject.sourceKoefKoef = anObject.sourceKoefKoef.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);
         anObject.planWorkItemRefCode = set.getInt(6);
         if(set.wasNull())
         anObject.planWorkItemRefCode = Integer.MIN_VALUE;
         anObject.planWorkItemRefCountGen = set.getBigDecimal(7);
         if(anObject.planWorkItemRefCountGen != null)
           anObject.planWorkItemRefCountGen = anObject.planWorkItemRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
         anObject.planWorkItemRefTimeGen = set.getBigDecimal(8);
         if(anObject.planWorkItemRefTimeGen != null)
           anObject.planWorkItemRefTimeGen = anObject.planWorkItemRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

         anObject.planWorkItemRefUserGen = set.getString(10);
         anObject.planWorkItemRefDateEdit = set.getDate(11);
         
         anObject.budjname = set.getString(12);
         anObject.enplanworkstatename = set.getString(13);

          result.list.add(anObject);
        }

       result.setTotalCount(i);
       return result;
      }
     catch(SQLException e)
      {
       System.out.println(e.getMessage()+"\nstatement - "+selectStr);
       throw new SystemException(e.getMessage(), e);
       //return null;
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

} // end of ENPlanWorkItem2TKKoefDAO

