
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer.generated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.valueobject.DataAccessType;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2PlanWorkItemShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2PlanWorkItemFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2PlanWorkItemShortList;
import com.ksoe.lla.persistence.GenericDataMiner;
import com.ksoe.lla.persistence.SequenceKey;
import com.ksoe.lla.persistence.SequenceValue;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.lla.segregation.SegregationInfo;
import com.ksoe.lla.segregation.SegregationProcessor;


/**
 * DAO Object for ENPlanWorkItem2PlanWorkItem;
 *
 */

public class ENPlanWorkItem2PlanWorkItemDAOGen extends GenericDataMiner {

  public ENPlanWorkItem2PlanWorkItemDAOGen(UserProfile anUserProfile,Connection aConnection) {super(anUserProfile,aConnection);}
  public ENPlanWorkItem2PlanWorkItemDAOGen(Connection aConnection,UserProfile anUserProfile) {super(aConnection,anUserProfile);}

   private boolean isEqual(ENPlanWorkItem2PlanWorkItem inObject) throws PersistenceException
   {
      ENPlanWorkItem2PlanWorkItem obj = new ENPlanWorkItem2PlanWorkItem();
      obj.code = inObject.code;
      loadObject(obj);

     if ( ! inObject.countGen.equals(obj.countGen)){
       return false;
     }
     if (inObject.typeRef.code != obj.typeRef.code){
        return false;
     }
     if (inObject.planItemInRef.code != obj.planItemInRef.code){
        return false;
     }
     if (inObject.planItemOutRef.code != obj.planItemOutRef.code){
        return false;
     }
      return true;
   }

   public int add(ENPlanWorkItem2PlanWorkItem anObject) throws PersistenceException
   {
    return add(anObject,true);
   }

   public int add(ENPlanWorkItem2PlanWorkItem anObject, boolean aUseSequential) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;

    anObject.modify_time = System.currentTimeMillis();

    if(aUseSequential)
     _collectAutoIncrementFields(anObject,connection);


    selectStr = "INSERT INTO ENPLANWORKITM2PLNWRKTM (CODE,COUNTGEN,MODIFY_TIME,TYPEREFCODE,PLANITEMINREFCODE,PLANITEMOUTREFCODE) VALUES (?,?,?,?,?,?)";

    try
     {
      statement = connection.prepareStatement(selectStr);
      if (anObject.code != Integer.MIN_VALUE )
         statement.setInt(1,anObject.code);
      else
         statement.setNull(1,java.sql.Types.INTEGER);
      if (anObject.countGen != null)
        anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(2,anObject.countGen);
      if (anObject.modify_time == Long.MIN_VALUE)
        statement.setBigDecimal(3,null);
      else
        statement.setBigDecimal(3,new java.math.BigDecimal(anObject.modify_time));
      if (anObject.typeRef.code != Integer.MIN_VALUE){
        if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2PlanWorkItemTypeDAOGen(connection,getUserProfile()).exists(anObject.typeRef.code))
           throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem.typeRef.code%} = {%"+anObject.typeRef.code+"%}");
        statement.setInt(4,anObject.typeRef.code);
      }
      else
        statement.setNull(4,java.sql.Types.INTEGER);
      if (anObject.planItemInRef.code != Integer.MIN_VALUE){
        if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).exists(anObject.planItemInRef.code))
           throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem.planItemInRef.code%} = {%"+anObject.planItemInRef.code+"%}");
        statement.setInt(5,anObject.planItemInRef.code);
      }
      else
        statement.setNull(5,java.sql.Types.INTEGER);
      if (anObject.planItemOutRef.code != Integer.MIN_VALUE){
        if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).exists(anObject.planItemOutRef.code))
           throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkItem2PlanWorkItem.planItemOutRef.code%} = {%"+anObject.planItemOutRef.code+"%}");
        statement.setInt(6,anObject.planItemOutRef.code);
      }
      else
        statement.setNull(6,java.sql.Types.INTEGER);

      statement.execute();
      return anObject.code;
     }
    catch(SQLException e)
     {
           _sequenceTable.clear();
           throw new SystemException(e.getMessage(), e);
      //return Integer.MIN_VALUE;
     }
        catch(Exception e)
     {
      _sequenceTable.clear();
      throw new PersistenceException("Error in method {%ENPlanWorkItem2PlanWorkItemDAOGen.add%}",e);
     }
        finally
     {
      try {if (statement != null) statement.close();} catch (SQLException e) {}
      if(connection != super.getConnection())
       {
        try{connection.close();} catch(SQLException e){}
       }
     }
   }

   public void save(ENPlanWorkItem2PlanWorkItem anObject) throws PersistenceException
   {
    save(anObject,null);
   }

   public void save(ENPlanWorkItem2PlanWorkItem anObject,String[] anAttributes) throws PersistenceException
   {
    if(anAttributes != null && anAttributes.length == 0)
       return;

    Connection connection = getConnection();
    try
     {
      PreparedStatement statement = null;

      ENPlanWorkItem2PlanWorkItem oldObject = new ENPlanWorkItem2PlanWorkItem();
      oldObject.code = anObject.code;

      String oldObjectSelectStr =
       "SELECT "+ENPlanWorkItem2PlanWorkItem.modify_time_Field+" FROM  ENPLANWORKITM2PLNWRKTM WHERE CODE = ?";

      ResultSet set = null;
      try
       {
        statement = connection.prepareStatement(oldObjectSelectStr);
        statement.setInt(1,oldObject.code);
        set = statement.executeQuery();
        if(!set.next())
           throw new PersistenceException("Can't get old object.");
       oldObject.modify_time = set.getLong(1);
        if(set.wasNull())
         oldObject.modify_time = Long.MIN_VALUE;
       }
      catch(SQLException e)
       {
        System.out.println(e.getMessage()+"\nstatement - "+oldObjectSelectStr);
        throw new SystemException(e.getMessage(), e);
       }
      finally
       {
        try {if (set != null) set.close();}             catch (SQLException e) {}
        try {if (statement != null) statement.close();} catch (SQLException e) {}
        statement = null;
       }

      if(oldObject.modify_time != anObject.modify_time)
       throw new PersistenceException("Can't update object (optimistic locking).");

      anObject.modify_time = System.currentTimeMillis();
      String selectStr;

      Vector fields = null;
      if(anAttributes != null)
       {
        String fieldNameStr;
        fields = new Vector();
        for(int attrIndex = 0;attrIndex < anAttributes.length;attrIndex++)
         {
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("COUNTGEN") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("MODIFY_TIME") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("TYPEREF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("PLANITEMINREF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("PLANITEMOUTREF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
         }
       if(fields.size() == 0)
         return;
       }
      if(fields == null)
       {
        selectStr =
        "UPDATE ENPLANWORKITM2PLNWRKTM SET  COUNTGEN = ? , MODIFY_TIME = ? , TYPEREFCODE = ? , PLANITEMINREFCODE = ? , PLANITEMOUTREFCODE = ? "
        +" WHERE CODE = ?";
       }
      else
       {
        selectStr =
        "UPDATE ENPLANWORKITEM2PLANWORKITEM SET ";
        for(int fldIndex = 0;fldIndex < fields.size();fldIndex++)
         {
          selectStr+=(String)fields.get(fldIndex);
          if(fldIndex > 0)
           selectStr+=",";
         }
        selectStr += " WHERE CODE = ?";
       }

      statement = null;

      try
       {
        statement = connection.prepareStatement(selectStr);
        if(fields == null)
         {
      if (anObject.countGen != null)
        anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(1,anObject.countGen);
      if (anObject.modify_time == Long.MIN_VALUE)
        statement.setBigDecimal(2,null);
      else
        statement.setBigDecimal(2,new java.math.BigDecimal(anObject.modify_time));
      if (anObject.typeRef.code != Integer.MIN_VALUE)
        statement.setInt(3,anObject.typeRef.code);
      else
        statement.setNull(3,java.sql.Types.INTEGER);
      if (anObject.planItemInRef.code != Integer.MIN_VALUE)
        statement.setInt(4,anObject.planItemInRef.code);
      else
        statement.setNull(4,java.sql.Types.INTEGER);
      if (anObject.planItemOutRef.code != Integer.MIN_VALUE)
        statement.setInt(5,anObject.planItemOutRef.code);
      else
        statement.setNull(5,java.sql.Types.INTEGER);
          statement.setInt(6,anObject.code);
         }
        else
         {

          for(int i = 0;i < fields.size();i++)
           {
            if("COUNTGEN".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.countGen != null)
                    anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(i+1,anObject.countGen);
                continue;
             }
            if("MODIFY_TIME".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.modify_time == Long.MIN_VALUE)
                    statement.setBigDecimal(i+1,null);
                else
                     statement.setBigDecimal(i+1,new java.math.BigDecimal(anObject.modify_time));
                continue;
             }
            if("TYPEREF".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.typeRef.code != Integer.MIN_VALUE)
                     statement.setInt(i+1,anObject.typeRef.code);
                else
                     statement.setNull(i+1,java.sql.Types.INTEGER);
                continue;
             }
            if("PLANITEMINREF".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.planItemInRef.code != Integer.MIN_VALUE)
                     statement.setInt(i+1,anObject.planItemInRef.code);
                else
                     statement.setNull(i+1,java.sql.Types.INTEGER);
                continue;
             }
            if("PLANITEMOUTREF".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.planItemOutRef.code != Integer.MIN_VALUE)
                     statement.setInt(i+1,anObject.planItemOutRef.code);
                else
                     statement.setNull(i+1,java.sql.Types.INTEGER);
                continue;
             }
            }
         statement.setInt(fields.size(),anObject.code);
         }

        statement.execute();
       }
      catch(SQLException e)
       {
        System.out.println(e.getMessage()+"\nstatement - "+selectStr);
        throw new SystemException(e.getMessage(), e);
       }
      finally
       {
        try {if (statement != null) statement.close();} catch (SQLException e) {}
       }
     }
    finally
     {
      if(connection != super.getConnection())
       {
        try{connection.close();} catch(SQLException e){}
       }
     }

   } // end of save(ENPlanWorkItem2PlanWorkItem anObject,String[] anAttributes)


 public ENPlanWorkItem2PlanWorkItemShort getShortObject(int anObjectCode) throws PersistenceException
  {
   ENPlanWorkItem2PlanWorkItem filterObject = new ENPlanWorkItem2PlanWorkItem();
   Vector list;

   filterObject.code = anObjectCode;
   list = getFilteredList(filterObject).list;
   if(list.size() > 0)
    return (ENPlanWorkItem2PlanWorkItemShort)list.get(0);
   return null;
  }

  public ENPlanWorkItem2PlanWorkItemShortList getList(String aCondition) throws PersistenceException
   {
    return getScrollableFilteredList(null,aCondition,0,-1);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getList() throws PersistenceException
   {
    return getScrollableFilteredList(null,null,0,-1);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getScrollableList(int fromPosition, int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(null,null,fromPosition,quantity);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getFilteredList(ENPlanWorkItem2PlanWorkItem filterObject) throws PersistenceException
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItem aFilterObject,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,null,fromPosition,quantity);
   }

   public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItem aFilterObject, String anCondition,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,anCondition,null,fromPosition,quantity,null);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(String anCondition,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(null,anCondition,fromPosition,quantity);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItemFilter aFilterObject,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
   }

  public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItemFilter aFilterObject,int fromPosition,int quantity, Vector aBindObjects) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
   }

/*
     public ENPlanWorkItem2PlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem2PlanWorkItem aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
 return null;
 }
**********************************/

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
      ", ENPLANWORKITEM.COSTGEN " +
      ", ENPLANWORKITEM.USERGEN " +
      ", ENPLANWORKITEM.DATEEDIT " +
      ", ENPLANWORKITEM.CODE " +
      ", ENPLANWORKITEM.COUNTGEN " +
      ", ENPLANWORKITEM.TIMEGEN " +
      ", ENPLANWORKITEM.COSTGEN " +
      ", ENPLANWORKITEM.USERGEN " +
      ", ENPLANWORKITEM.DATEEDIT " +
     " FROM ENPLANWORKITM2PLNWRKTM " +
     ", ENPLANWRKTM2PLNWRKTMTP " +
     ", ENPLANWORKITEM " +
     ", ENPLANWORKITEM " +
     //" WHERE "
    "";
     whereStr = " ENPLANWRKTM2PLNWRKTMTP.CODE = ENPLANWORKITM2PLNWRKTM.TYPEREFCODE" ; //+
      whereStr = whereStr +" AND ENPLANWORKITEM.CODE = ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE" ; //+
      whereStr = whereStr +" AND ENPLANWORKITEM.CODE = ENPLANWORKITM2PLNWRKTM.PLANITEMOUTREFCODE" ; //+
        //selectStr = selectStr + " ${s} ENPLANWORKITM2PLNWRKTM.CODE IN ( SELECT ENPLANWORKITM2PLNWRKTM.CODE FROM ENPLANWORKITM2PLNWRKTM ";

// " ";
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

        anObject.planItemInRefUserGen = set.getString(9);
        anObject.planItemInRefDateEdit = set.getDate(10);
        anObject.planItemOutRefCode = set.getInt(11);
        if(set.wasNull())
        anObject.planItemOutRefCode = Integer.MIN_VALUE;
        anObject.planItemOutRefCountGen = set.getBigDecimal(12);
        if(anObject.planItemOutRefCountGen != null)
          anObject.planItemOutRefCountGen = anObject.planItemOutRefCountGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.planItemOutRefTimeGen = set.getBigDecimal(13);
        if(anObject.planItemOutRefTimeGen != null)
          anObject.planItemOutRefTimeGen = anObject.planItemOutRefTimeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

        anObject.planItemOutRefUserGen = set.getString(15);
        anObject.planItemOutRefDateEdit = set.getDate(16);

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

  public int[] getFilteredCodeArrayOLD(ENPlanWorkItem2PlanWorkItem aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
    Vector result = new Vector();

    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;
    String     selectStr = "SELECT ENPLANWORKITM2PLNWRKTM.CODE FROM ENPLANWORKITM2PLNWRKTM";
    String     whereStr = "";
    String     condition = processCondition(anCondition);
    String     orderBy = processCondition(anOrderBy);

    if(orderBy.length() == 0)
     orderBy = "ENPLANWORKITM2PLNWRKTM.CODE";

    if(quantity < 0)
     quantity = Integer.MAX_VALUE/2;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

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
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.TYPEREFCODE = ? ";
        }
        if(aFilterObject.planItemInRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE = ? ";
        }
        if(aFilterObject.planItemOutRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.PLANITEMOUTREFCODE = ? ";
        }

      }

      if(condition.length() != 0)
      {
         if(whereStr.length() != 0)
            whereStr = whereStr + " AND ";
         whereStr = whereStr + " (" + condition + ")";
      }

     if(whereStr.length() != 0)
         selectStr = selectStr + " WHERE " + whereStr;

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

        result.add(new Integer(set.getInt(1)));
       }

      int[] array;

      array = new int[result.size()];
      for(int j = 0;j < result.size();j++)
       array[j] = ((Integer)result.get(j)).intValue();

      return array;
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


    } // end of getFilteredCodeArray

/*********************************/

  public int[] getFilteredCodeArray(ENPlanWorkItem2PlanWorkItemFilter aFilterObject, int fromPosition, int quantity) throws PersistenceException
    {
    return getFilteredCodeArray(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
    }


  public int[] getFilteredCodeArray(ENPlanWorkItem2PlanWorkItem aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
    Vector result = new Vector();

    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;
    String     selectStr = "SELECT ENPLANWORKITM2PLNWRKTM.CODE FROM ENPLANWORKITM2PLNWRKTM";
    String     whereStr = "";
    String     condition = processCondition(anCondition);
    String     orderBy = processCondition(anOrderBy);

    if(orderBy.length() == 0)
     orderBy = "ENPLANWORKITM2PLNWRKTM.CODE";

    if(quantity < 0)
     quantity = Integer.MAX_VALUE/2;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

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
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.TYPEREFCODE = ? ";
        }
        if(aFilterObject.planItemInRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE = ? ";
        }
        if(aFilterObject.planItemOutRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKITM2PLNWRKTM.PLANITEMOUTREFCODE = ? ";
        }

      }

      if(condition.length() != 0)
      {
         if(whereStr.length() != 0)
            whereStr = whereStr + " AND ";
         whereStr = whereStr + " (" + condition + ")";
      }

     if(whereStr.length() != 0)
         selectStr = selectStr + " WHERE " + whereStr;

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

        result.add(new Integer(set.getInt(1)));
       }

      int[] array;

      array = new int[result.size()];
      for(int j = 0;j < result.size();j++)
       array[j] = ((Integer)result.get(j)).intValue();

      return array;
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


    } // end of getFilteredCodeArray


   public ENPlanWorkItem2PlanWorkItem getObject(int uid) throws PersistenceException
   {
    ENPlanWorkItem2PlanWorkItem result = new ENPlanWorkItem2PlanWorkItem();
    result.code = uid;
    loadObject(result);
    if(result.code == Integer.MIN_VALUE)
     return null;
    return result;
   }


  public void loadObject(ENPlanWorkItem2PlanWorkItem anObject) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet set = null;



    selectStr =
    "SELECT  ENPLANWORKITM2PLNWRKTM.CODE, ENPLANWORKITM2PLNWRKTM.COUNTGEN, ENPLANWORKITM2PLNWRKTM.MODIFY_TIME, ENPLANWORKITM2PLNWRKTM.TYPEREFCODE, ENPLANWORKITM2PLNWRKTM.PLANITEMINREFCODE, ENPLANWORKITM2PLNWRKTM.PLANITEMOUTREFCODE "
    +" FROM ENPLANWORKITM2PLNWRKTM WHERE ENPLANWORKITM2PLNWRKTM.CODE = ?";

    try
     {
      statement = connection.prepareStatement(selectStr);
      statement.setInt(1,anObject.code);
      set = statement.executeQuery();
      if(!set.next())
       anObject.code = Integer.MIN_VALUE;
      else
       {
        anObject.code = set.getInt(1);
        anObject.countGen = set.getBigDecimal(2);
        if(anObject.countGen != null)
            anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.modify_time = set.getLong(3);
        if(set.wasNull())
         anObject.modify_time = Long.MIN_VALUE;
        anObject.typeRef.code = set.getInt(4);
        if ( set.wasNull() )
            anObject.typeRef.code = Integer.MIN_VALUE;
        anObject.planItemInRef.code = set.getInt(5);
        if ( set.wasNull() )
            anObject.planItemInRef.code = Integer.MIN_VALUE;
        anObject.planItemOutRef.code = set.getInt(6);
        if ( set.wasNull() )
            anObject.planItemOutRef.code = Integer.MIN_VALUE;
        if(anObject.typeRef.code != Integer.MIN_VALUE)
        {
           anObject.setTypeRef(
        new com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2PlanWorkItemTypeDAOGen(connection,getUserProfile()).getRef(anObject.typeRef.code));
    }
        if(anObject.planItemInRef.code != Integer.MIN_VALUE)
        {
           anObject.setPlanItemInRef(
        new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).getRef(anObject.planItemInRef.code));
    }
        if(anObject.planItemOutRef.code != Integer.MIN_VALUE)
        {
           anObject.setPlanItemOutRef(
        new com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen(connection,getUserProfile()).getRef(anObject.planItemOutRef.code));
    }
      }
    }
    catch(SQLException e)
     {
      System.out.println(e.getMessage()+"\nstatement - "+selectStr);
      throw new SystemException(e.getMessage(), e);
     }
    finally
     {
      try {if(set != null) set.close(); if (statement != null) statement.close();}
      catch (SQLException e) {}
      if(connection != super.getConnection())
       {
        try{connection.close();} catch(SQLException e){}
       }
     }
   }


  public com.ksoe.energynet.valueobject.references.ENPlanWorkItem2PlanWorkItemRef getRef(int anObjectCode) throws PersistenceException
   {
    com.ksoe.energynet.valueobject.references.ENPlanWorkItem2PlanWorkItemRef ref = new com.ksoe.energynet.valueobject.references.ENPlanWorkItem2PlanWorkItemRef();
    if(exists(anObjectCode))
     ref.code = anObjectCode;
    else
     ref.code = Integer.MIN_VALUE;
    return ref;
   }


   public void remove(int uid) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();

    selectStr = "DELETE FROM  ENPLANWORKITM2PLNWRKTM WHERE CODE = ?";

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

    ENPlanWorkItem2PlanWorkItem object = getObject(uid);

    if(object == null)
     throw new PersistenceException("{%ENPlanWorkItem2PlanWorkItem.getObject%} access denied");

    if(new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkItem2PlanWorkItem.class.getName(),DataAccessType.DELETE,getUserProfile().getDomainInfo().getDomain()).isAccessDenied())
     throw new PersistenceException("{%ENPlanWorkItem2PlanWorkItem.remove%} access denied");

    PreparedStatement statement = null;
    try
     {
      statement = connection.prepareStatement(selectStr);
      statement.setInt(1,uid);
      statement.execute();
     }
    catch(SQLException e)
     {
      System.out.println(e.getMessage()+"\nstatement - "+selectStr);
      throw new SystemException(e.getMessage(), e);
     }
    finally
     {
      try {if (statement != null) statement.close();} catch (SQLException e) {}
      if(connection != super.getConnection())
       {
        try{connection.close();} catch(SQLException e){}
       }
     }
   }

   public boolean exists(int anObjectCode) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;

    if(anObjectCode == Integer.MIN_VALUE)
     return false;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

    SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkItem2PlanWorkItem.class.getName(),DataAccessType.READ,getUserProfile().getDomainInfo().getDomain());
    if(segregationInfo.isAccessDenied())
      throw new PersistenceException("{%ENPlanWorkItem2PlanWorkItem.getObject%} access denied");

    selectStr =

    "SELECT  ENPLANWORKITM2PLNWRKTM.CODE FROM  ENPLANWORKITM2PLNWRKTM WHERE  ENPLANWORKITM2PLNWRKTM.CODE = ?";
    try
     {
      statement = connection.prepareStatement(selectStr);
      statement.setInt(1,anObjectCode);
      set = statement.executeQuery();
      if(set.next())
       return true;
      return false;
     }
    catch(SQLException e)
     {
      System.out.println(e.getMessage()+"\nstatement - "+selectStr);
      throw new SystemException(e.getMessage(), e);
      //return false;
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

  public static String processCondition(String aCondition)
   {
    if(aCondition == null || aCondition.length() == 0)
     return "";

    StringBuffer condition = new StringBuffer(aCondition);
    _checkConditionToken(condition,";"," ");
    _checkConditionToken(condition,"--"," ");
    _checkConditionToken(condition,"\r"," ");
    _checkConditionToken(condition,"\n"," ");
    _checkConditionToken(condition,"||"," OR ");
    _checkConditionToken(condition,"&&"," AND ");
    _checkConditionToken(condition,"==","=");
    _checkConditionToken(condition,"!=","<>");
    _checkConditionToken(condition,"code","ENPLANWORKITM2PLNWRKTM.CODE");
    _checkConditionToken(condition,"countgen","ENPLANWORKITM2PLNWRKTM.COUNTGEN");
    _checkConditionToken(condition,"modify_time","ENPLANWORKITM2PLNWRKTM.MODIFY_TIME");
      // relationship conditions
    _checkConditionToken(condition,"typeref","TYPEREFCODE");
    _checkConditionToken(condition,"typeref.code","TYPEREFCODE");
    _checkConditionToken(condition,"planiteminref","PLANITEMINREFCODE");
    _checkConditionToken(condition,"planiteminref.code","PLANITEMINREFCODE");
    _checkConditionToken(condition,"planitemoutref","PLANITEMOUTREFCODE");
    _checkConditionToken(condition,"planitemoutref.code","PLANITEMOUTREFCODE");
    return condition.toString();
   }

   public Connection getConnection()
   {
    try
     {
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
    protected static Hashtable _sequenceTable = new Hashtable();

    private void _collectAutoIncrementFields(ENPlanWorkItem2PlanWorkItem anObject,
            Connection connection) throws PersistenceException {

        SequenceKey hashKey = new SequenceKey("ENPLANWORKITM2PLNWRKTM", "CODE");
        Integer nextSeqValue = null;
        SequenceValue sequenceValue;
        synchronized (_sequenceTable) { sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
            if (sequenceValue == null) {
                sequenceValue = getNewSequenceValue("ENPLANWORKITM2PLNWRKTM", "CODE");
                _sequenceTable.put(hashKey, sequenceValue);
            }
            if (!sequenceValue.isNextValueAvailable()) {
                sequenceValue = getNewSequenceValue("ENPLANWORKITM2PLNWRKTM", "CODE");
                _sequenceTable.put(hashKey, sequenceValue);
            }
        }

        nextSeqValue = sequenceValue.getNextValue();
        if (nextSeqValue == null) {
            throw new PersistenceException(
                    "Can't obtain auto increment value from: ENPLANWORKITM2PLNWRKTM");
        } else {
            anObject.code = nextSeqValue.intValue();
            return;
        }
    }

} // end of ENPlanWorkItem2PlanWorkItemDAO
