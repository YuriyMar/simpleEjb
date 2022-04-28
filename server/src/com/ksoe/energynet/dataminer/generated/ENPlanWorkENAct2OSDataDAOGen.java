
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
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

import com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkENAct2OSDataFilter;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkENAct2OSDataShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkENAct2OSDataShortList;


/**
 * DAO Object for ENPlanWorkENAct2OSData;
 *
 */

public class ENPlanWorkENAct2OSDataDAOGen extends GenericDataMiner {

   public ENPlanWorkENAct2OSDataDAOGen(UserProfile anUserProfile, Connection aConnection) {
        super(anUserProfile,aConnection);
   }

   public ENPlanWorkENAct2OSDataDAOGen(Connection aConnection, UserProfile anUserProfile) {
        super(aConnection,anUserProfile);
   }

   protected boolean isEqual(ENPlanWorkENAct2OSData inObject) throws PersistenceException
   {
      ENPlanWorkENAct2OSData obj = new ENPlanWorkENAct2OSData();
      obj.code = inObject.code;
      loadObject(obj);

     if (inObject.num_un != obj.num_un){
				return false;
			}

     if (inObject.num_unWriteOff != obj.num_unWriteOff){
				return false;
			}

	if(inObject.kod_inv == null && obj.kod_inv == null){}
	else
		if(inObject.kod_inv == null || obj.kod_inv == null) return false;
		else
			if ( ! inObject.kod_inv.equals(obj.kod_inv)){
				return false;
			}

	if(inObject.kod_vid == null && obj.kod_vid == null){}
	else
		if(inObject.kod_vid == null || obj.kod_vid == null) return false;
		else
			if ( ! inObject.kod_vid.equals(obj.kod_vid)){
				return false;
			}

	if(inObject.kod_subsch_b == null && obj.kod_subsch_b == null){}
	else
		if(inObject.kod_subsch_b == null || obj.kod_subsch_b == null) return false;
		else
			if ( ! inObject.kod_subsch_b.equals(obj.kod_subsch_b)){
				return false;
			}

	if(inObject.name_inv == null && obj.name_inv == null){}
	else
		if(inObject.name_inv == null || obj.name_inv == null) return false;
		else
			if ( ! inObject.name_inv.equals(obj.name_inv)){
				return false;
			}

	if(inObject.kod_ist == null && obj.kod_ist == null){}
	else
		if(inObject.kod_ist == null || obj.kod_ist == null) return false;
		else
			if ( ! inObject.kod_ist.equals(obj.kod_ist)){
				return false;
			}

	if(inObject.name_ist == null && obj.name_ist == null){}
	else
		if(inObject.name_ist == null || obj.name_ist == null) return false;
		else
			if ( ! inObject.name_ist.equals(obj.name_ist)){
				return false;
			}

	if(inObject.sumBuhWriteOZ == null && obj.sumBuhWriteOZ == null){}
	else
		if(inObject.sumBuhWriteOZ == null || obj.sumBuhWriteOZ == null) return false;
		else
			if ( ! inObject.sumBuhWriteOZ.equals(obj.sumBuhWriteOZ)){
				return false;
			}

	if(inObject.sumStCurrentN == null && obj.sumStCurrentN == null){}
	else
		if(inObject.sumStCurrentN == null || obj.sumStCurrentN == null) return false;
		else
			if ( ! inObject.sumStCurrentN.equals(obj.sumStCurrentN)){
				return false;
			}

	if(inObject.sumIznCurrentB == null && obj.sumIznCurrentB == null){}
	else
		if(inObject.sumIznCurrentB == null || obj.sumIznCurrentB == null) return false;
		else
			if ( ! inObject.sumIznCurrentB.equals(obj.sumIznCurrentB)){
				return false;
			}

	if(inObject.sumIznCurrentN == null && obj.sumIznCurrentN == null){}
	else
		if(inObject.sumIznCurrentN == null || obj.sumIznCurrentN == null) return false;
		else
			if ( ! inObject.sumIznCurrentN.equals(obj.sumIznCurrentN)){
				return false;
			}

     if (inObject.typeWriteOff != obj.typeWriteOff){
				return false;
			}

	if(inObject.userGen == null && obj.userGen == null){}
	else
		if(inObject.userGen == null || obj.userGen == null) return false;
		else
			if ( ! inObject.userGen.equals(obj.userGen)){
				return false;
			}

	if(inObject.dateEdit == null && obj.dateEdit == null){}
	else
		if(inObject.dateEdit == null || obj.dateEdit == null) return false;
		else
			if ( ! inObject.dateEdit.equals(obj.dateEdit)){
				return false;
			}
     if (inObject.planWorkRef.code != obj.planWorkRef.code){
        return false;
     }
     if (inObject.actRef.code != obj.actRef.code){
        return false;
     }
      return true;
   }

   public int add(ENPlanWorkENAct2OSData anObject) throws PersistenceException
   {
    return add(anObject,true);
   }

   public int add(ENPlanWorkENAct2OSData anObject, boolean aUseSequential) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;

  anObject.modify_time = System.currentTimeMillis();

    if(aUseSequential)
     _collectAutoIncrementFields(anObject,connection);


    selectStr = "INSERT INTO ENPLANWORKENACT2OSDATA (CODE,NUM_UN,NUM_UNWRITEOFF,KOD_INV,KOD_VID,KOD_SUBSCH_B,NAME_INV,KOD_IST,NAME_IST,SUMBUHWRITEOZ,SUMSTCURRENTN,SUMIZNCURRENTB,SUMIZNCURRENTN,TYPEWRITEOFF,USERGEN,DATEEDIT,MODIFY_TIME,PLANWORKREFCODE,ACTREFCODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    try
     {
      statement = connection.prepareStatement(selectStr);
      if (anObject.code != Integer.MIN_VALUE )
         statement.setInt(1,anObject.code);
      else
         statement.setNull(1,java.sql.Types.INTEGER);
      if (anObject.num_un != Integer.MIN_VALUE )
         statement.setInt(2,anObject.num_un);
      else
         statement.setNull(2,java.sql.Types.INTEGER);
      if (anObject.num_unWriteOff != Integer.MIN_VALUE )
         statement.setInt(3,anObject.num_unWriteOff);
      else
         statement.setNull(3,java.sql.Types.INTEGER);
      statement.setString(4,anObject.kod_inv);
      statement.setString(5,anObject.kod_vid);
      statement.setString(6,anObject.kod_subsch_b);
      statement.setString(7,anObject.name_inv);
      statement.setString(8,anObject.kod_ist);
      statement.setString(9,anObject.name_ist);
      if (anObject.sumBuhWriteOZ != null)
        anObject.sumBuhWriteOZ = anObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(10,anObject.sumBuhWriteOZ);
      if (anObject.sumStCurrentN != null)
        anObject.sumStCurrentN = anObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(11,anObject.sumStCurrentN);
      if (anObject.sumIznCurrentB != null)
        anObject.sumIznCurrentB = anObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(12,anObject.sumIznCurrentB);
      if (anObject.sumIznCurrentN != null)
        anObject.sumIznCurrentN = anObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(13,anObject.sumIznCurrentN);
      if (anObject.typeWriteOff != Integer.MIN_VALUE )
         statement.setInt(14,anObject.typeWriteOff);
      else
         statement.setNull(14,java.sql.Types.INTEGER);
      statement.setString(15,anObject.userGen);
      if (anObject.dateEdit == null)
        statement.setTimestamp(16,null);
      else
        statement.setTimestamp(16,new java.sql.Timestamp(anObject.dateEdit.getTime()));
      if (anObject.modify_time == Long.MIN_VALUE)
        statement.setBigDecimal(17,null);
      else
        statement.setBigDecimal(17,new java.math.BigDecimal(anObject.modify_time));
      if (anObject.planWorkRef.code != Integer.MIN_VALUE){
        if( ! new com.ksoe.energynet.dataminer.generated.ENPlanWorkDAOGen(connection,getUserProfile()).exists(anObject.planWorkRef.code))
           throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData.planWorkRef.code%} = {%"+anObject.planWorkRef.code+"%}");
        statement.setInt(18,anObject.planWorkRef.code);
      }
      else
        statement.setNull(18,java.sql.Types.INTEGER);
      if (anObject.actRef.code != Integer.MIN_VALUE){
        if( ! new com.ksoe.energynet.dataminer.generated.ENActDAOGen(connection,getUserProfile()).exists(anObject.actRef.code))
           throw new PersistenceException("Invalid attribute value {%com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData.actRef.code%} = {%"+anObject.actRef.code+"%}");
        statement.setInt(19,anObject.actRef.code);
      }
      else
        statement.setNull(19,java.sql.Types.INTEGER);

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
      throw new PersistenceException("Error in method {%ENPlanWorkENAct2OSDataDAOGen.add%}",e);
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

   public void save(ENPlanWorkENAct2OSData anObject) throws PersistenceException
   {
    save(anObject,null);
   }

   public void save(ENPlanWorkENAct2OSData anObject,String[] anAttributes) throws PersistenceException
   {
    if(anAttributes != null && anAttributes.length == 0)
       return;

    Connection connection = getConnection();
    try
     {
      PreparedStatement statement = null;

      ENPlanWorkENAct2OSData oldObject = new ENPlanWorkENAct2OSData();
      oldObject.code = anObject.code;

      String oldObjectSelectStr =
       "SELECT "+ENPlanWorkENAct2OSData.modify_time_Field+" FROM  ENPLANWORKENACT2OSDATA WHERE CODE = ?";

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
          if(fieldNameStr.compareTo("NUM_UN") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("NUM_UNWRITEOFF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("KOD_INV") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("KOD_VID") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("KOD_SUBSCH_B") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("NAME_INV") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("KOD_IST") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("NAME_IST") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("SUMBUHWRITEOZ") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("SUMSTCURRENTN") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("SUMIZNCURRENTB") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("SUMIZNCURRENTN") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("TYPEWRITEOFF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("USERGEN") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("DATEEDIT") == 0)
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
          if(fieldNameStr.compareTo("PLANWORKREF") == 0)
           {
            fields.add(fieldNameStr);
            continue;
           }
          fieldNameStr = processCondition(anAttributes[attrIndex]);
          if(fieldNameStr.compareTo("ACTREF") == 0)
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
        "UPDATE ENPLANWORKENACT2OSDATA SET  NUM_UN = ? , NUM_UNWRITEOFF = ? , KOD_INV = ? , KOD_VID = ? , KOD_SUBSCH_B = ? , NAME_INV = ? , KOD_IST = ? , NAME_IST = ? , SUMBUHWRITEOZ = ? , SUMSTCURRENTN = ? , SUMIZNCURRENTB = ? , SUMIZNCURRENTN = ? , TYPEWRITEOFF = ? , USERGEN = ? , DATEEDIT = ? , MODIFY_TIME = ? , PLANWORKREFCODE = ? , ACTREFCODE = ? "
        +" WHERE CODE = ?";
       }
      else
       {
        selectStr =
        "UPDATE ENPLANWORKENACT2OSDATA SET ";
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
      if (anObject.num_un != Integer.MIN_VALUE )
         statement.setInt(1,anObject.num_un);
      else
         statement.setNull(1,java.sql.Types.INTEGER);
      if (anObject.num_unWriteOff != Integer.MIN_VALUE )
         statement.setInt(2,anObject.num_unWriteOff);
      else
         statement.setNull(2,java.sql.Types.INTEGER);
      statement.setString(3,anObject.kod_inv);
      statement.setString(4,anObject.kod_vid);
      statement.setString(5,anObject.kod_subsch_b);
      statement.setString(6,anObject.name_inv);
      statement.setString(7,anObject.kod_ist);
      statement.setString(8,anObject.name_ist);
      if (anObject.sumBuhWriteOZ != null)
        anObject.sumBuhWriteOZ = anObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(9,anObject.sumBuhWriteOZ);
      if (anObject.sumStCurrentN != null)
        anObject.sumStCurrentN = anObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(10,anObject.sumStCurrentN);
      if (anObject.sumIznCurrentB != null)
        anObject.sumIznCurrentB = anObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(11,anObject.sumIznCurrentB);
      if (anObject.sumIznCurrentN != null)
        anObject.sumIznCurrentN = anObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
      statement.setBigDecimal(12,anObject.sumIznCurrentN);
      if (anObject.typeWriteOff != Integer.MIN_VALUE )
         statement.setInt(13,anObject.typeWriteOff);
      else
         statement.setNull(13,java.sql.Types.INTEGER);
      statement.setString(14,anObject.userGen);
      if (anObject.dateEdit == null)
        statement.setTimestamp(15,null);
      else
        statement.setTimestamp(15,new java.sql.Timestamp(anObject.dateEdit.getTime()));
      if (anObject.modify_time == Long.MIN_VALUE)
        statement.setBigDecimal(16,null);
      else
        statement.setBigDecimal(16,new java.math.BigDecimal(anObject.modify_time));
      if (anObject.planWorkRef.code != Integer.MIN_VALUE)
        statement.setInt(17,anObject.planWorkRef.code);
      else
        statement.setNull(17,java.sql.Types.INTEGER);
      if (anObject.actRef.code != Integer.MIN_VALUE)
        statement.setInt(18,anObject.actRef.code);
      else
        statement.setNull(18,java.sql.Types.INTEGER);
          statement.setInt(19,anObject.code);
         }
        else
         {

          for(int i = 0;i < fields.size();i++)
           {
            if("NUM_UN".compareTo((String)fields.get(i)) == 0)
             {
                statement.setInt(i+1,anObject.num_un);
                continue;
             }
            if("NUM_UNWRITEOFF".compareTo((String)fields.get(i)) == 0)
             {
                statement.setInt(i+1,anObject.num_unWriteOff);
                continue;
             }
            if("KOD_INV".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.kod_inv);
                continue;
             }
            if("KOD_VID".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.kod_vid);
                continue;
             }
            if("KOD_SUBSCH_B".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.kod_subsch_b);
                continue;
             }
            if("NAME_INV".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.name_inv);
                continue;
             }
            if("KOD_IST".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.kod_ist);
                continue;
             }
            if("NAME_IST".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.name_ist);
                continue;
             }
            if("SUMBUHWRITEOZ".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.sumBuhWriteOZ != null)
                    anObject.sumBuhWriteOZ = anObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(i+1,anObject.sumBuhWriteOZ);
                continue;
             }
            if("SUMSTCURRENTN".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.sumStCurrentN != null)
                    anObject.sumStCurrentN = anObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(i+1,anObject.sumStCurrentN);
                continue;
             }
            if("SUMIZNCURRENTB".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.sumIznCurrentB != null)
                    anObject.sumIznCurrentB = anObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(i+1,anObject.sumIznCurrentB);
                continue;
             }
            if("SUMIZNCURRENTN".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.sumIznCurrentN != null)
                    anObject.sumIznCurrentN = anObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
                statement.setBigDecimal(i+1,anObject.sumIznCurrentN);
                continue;
             }
            if("TYPEWRITEOFF".compareTo((String)fields.get(i)) == 0)
             {
                statement.setInt(i+1,anObject.typeWriteOff);
                continue;
             }
            if("USERGEN".compareTo((String)fields.get(i)) == 0)
             {
                statement.setString(i+1,anObject.userGen);
                continue;
             }
            if("DATEEDIT".compareTo((String)fields.get(i)) == 0)
             {
      if (anObject.dateEdit == null)
                statement.setTimestamp(i+1,null);
      else
                statement.setTimestamp(i+1,new java.sql.Timestamp(anObject.dateEdit.getTime()));
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
            if("PLANWORKREF".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.planWorkRef.code != Integer.MIN_VALUE)
                     statement.setInt(i+1,anObject.planWorkRef.code);
                else
                     statement.setNull(i+1,java.sql.Types.INTEGER);
                continue;
             }
            if("ACTREF".compareTo((String)fields.get(i)) == 0)
             {
                if (anObject.actRef.code != Integer.MIN_VALUE)
                     statement.setInt(i+1,anObject.actRef.code);
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

   } // end of save(ENPlanWorkENAct2OSData anObject,String[] anAttributes)


 public ENPlanWorkENAct2OSDataShort getShortObject(int anObjectCode) throws PersistenceException
  {
   ENPlanWorkENAct2OSData filterObject = new ENPlanWorkENAct2OSData();
   Vector list;

   filterObject.code = anObjectCode;
   list = getFilteredList(filterObject).list;
   if(list.size() > 0)
    return (ENPlanWorkENAct2OSDataShort)list.get(0);
   return null;
  }

  public ENPlanWorkENAct2OSDataShortList getList(String aCondition) throws PersistenceException
   {
    return getScrollableFilteredList(null,aCondition,0,-1);
   }

  public ENPlanWorkENAct2OSDataShortList getList() throws PersistenceException
   {
    return getScrollableFilteredList(null,null,0,-1);
   }

  public ENPlanWorkENAct2OSDataShortList getScrollableList(int fromPosition, int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(null,null,fromPosition,quantity);
   }

  public ENPlanWorkENAct2OSDataShortList getFilteredList(ENPlanWorkENAct2OSData filterObject) throws PersistenceException
   {
    return getScrollableFilteredList(filterObject,0,-1);
   }

  public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSData aFilterObject,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,null,fromPosition,quantity);
   }

   public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSData aFilterObject, String anCondition,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,anCondition,null,fromPosition,quantity,null);
   }

  public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(String anCondition,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(null,anCondition,fromPosition,quantity);
   }

  public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSDataFilter aFilterObject,int fromPosition,int quantity) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
   }

  public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSDataFilter aFilterObject,int fromPosition,int quantity, Vector aBindObjects) throws PersistenceException
   {
    return getScrollableFilteredList(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, aBindObjects);
   }

/*
     public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSData aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
 return null;
 }
**********************************/

  public ENPlanWorkENAct2OSDataShortList getScrollableFilteredList(ENPlanWorkENAct2OSData aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
    ENPlanWorkENAct2OSDataShortList result = new ENPlanWorkENAct2OSDataShortList();
    ENPlanWorkENAct2OSDataShort anObject;
    result.list = new Vector();

    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;
    String     whereStr = "";
    String     condition = processCondition(anCondition);
    String     orderBy = processCondition(anOrderBy);

    if(orderBy.length() == 0)
     orderBy = "ENPLANWORKENACT2OSDATA.CODE";

    if(quantity < 0)
     quantity = Integer.MAX_VALUE/2;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

    selectStr = "SELECT "+
     "ENPLANWORKENACT2OSDATA.CODE"+
     ",ENPLANWORKENACT2OSDATA.NUM_UN"+
     ",ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF"+
     ",ENPLANWORKENACT2OSDATA.KOD_INV"+
     ",ENPLANWORKENACT2OSDATA.KOD_VID"+
     ",ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B"+
     ",ENPLANWORKENACT2OSDATA.NAME_INV"+
     ",ENPLANWORKENACT2OSDATA.KOD_IST"+
     ",ENPLANWORKENACT2OSDATA.NAME_IST"+
     ",ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ"+
     ",ENPLANWORKENACT2OSDATA.SUMSTCURRENTN"+
     ",ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB"+
     ",ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN"+
     ",ENPLANWORKENACT2OSDATA.TYPEWRITEOFF"+
     ",ENPLANWORKENACT2OSDATA.USERGEN"+
     ",ENPLANWORKENACT2OSDATA.DATEEDIT"+

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
      ", ENACT.CODE " +
      ", ENACT.NUMBERGEN " +
      ", ENACT.DATEGEN " +
      ", ENACT.FINDOCCODE " +
      ", ENACT.FINDOCMECHANICCODE " +
      ", ENACT.FINMOLNAME " +
      ", ENACT.FINMECHANICNAME " +
      ", ENACT.INVNUMBER " +
      ", ENACT.USERGEN " +
      ", ENACT.DATEEDIT " +
      ", ENACT.DATEACT " +
     " FROM ENPLANWORKENACT2OSDATA " +
     ", ENPLANWORK " +
     ", ENACT " +
     //" WHERE "
  "";
     whereStr = " ENPLANWORK.CODE = ENPLANWORKENACT2OSDATA.PLANWORKREFCODE" ; //+
      whereStr = whereStr +" AND ENACT.CODE = ENPLANWORKENACT2OSDATA.ACTREFCODE" ; //+
    //selectStr = selectStr + " ${s} ENPLANWORKENACT2OSDATA.CODE IN ( SELECT ENPLANWORKENACT2OSDATA.CODE FROM ENPLANWORKENACT2OSDATA ";

// " ";
      if(aFilterObject != null)
      {
        if(aFilterObject.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.CODE = ?";
        }
        if(aFilterObject.num_un != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UN = ?";
        }
        if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF = ?";
        }
         if (aFilterObject.kod_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_inv.indexOf('*',0) < 0 && aFilterObject.kod_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.KOD_INV) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.KOD_INV) LIKE UPPER(?)";
         }
         if (aFilterObject.kod_vid != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_vid.indexOf('*',0) < 0 && aFilterObject.kod_vid.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.KOD_VID) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.KOD_VID) LIKE UPPER(?)";
         }
         if (aFilterObject.kod_subsch_b != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_subsch_b.indexOf('*',0) < 0 && aFilterObject.kod_subsch_b.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B) LIKE UPPER(?)";
         }
         if (aFilterObject.name_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_inv.indexOf('*',0) < 0 && aFilterObject.name_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.NAME_INV) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.NAME_INV) LIKE UPPER(?)";
         }
         if (aFilterObject.kod_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_ist.indexOf('*',0) < 0 && aFilterObject.kod_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.KOD_IST) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.KOD_IST) LIKE UPPER(?)";
         }
         if (aFilterObject.name_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_ist.indexOf('*',0) < 0 && aFilterObject.name_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.NAME_IST) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.NAME_IST) LIKE UPPER(?)";
         }
        if(aFilterObject.sumBuhWriteOZ != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ = ?";
        }
        if(aFilterObject.sumStCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMSTCURRENTN = ?";
        }
        if(aFilterObject.sumIznCurrentB != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB = ?";
        }
        if(aFilterObject.sumIznCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN = ?";
        }
        if(aFilterObject.typeWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.TYPEWRITEOFF = ?";
        }
         if (aFilterObject.userGen != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                 whereStr = whereStr + "  UPPER(ENPLANWORKENACT2OSDATA.USERGEN) = UPPER(?)";
             else
                 whereStr = whereStr + " UPPER(ENPLANWORKENACT2OSDATA.USERGEN) LIKE UPPER(?)";
         }
        if(aFilterObject.dateEdit != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.DATEEDIT = ?";
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.MODIFY_TIME = ?";
        }
        if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "ENPLANWORKENACT2OSDATA.PLANWORKREFCODE = ? ";
        }
        if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "ENPLANWORKENACT2OSDATA.ACTREFCODE = ? ";
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

    selectStr = selectStr + " OFFSET " + fromPosition;
    if (quantity>-1) selectStr = selectStr + " LIMIT " + quantity;

    try
     {
      statement = connection.prepareStatement(selectStr);
      int number = 0;
      if(aFilterObject != null){
         if(aFilterObject.code != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.code);
         }
         if(aFilterObject.num_un != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_un);
         }
         if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_unWriteOff);
         }

           if(aFilterObject.kod_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }

           if(aFilterObject.kod_vid != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_vid);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }

           if(aFilterObject.kod_subsch_b != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_subsch_b);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }

           if(aFilterObject.name_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }

           if(aFilterObject.kod_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }

           if(aFilterObject.name_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
        if(aFilterObject.sumBuhWriteOZ != null){
            number++;
            aFilterObject.sumBuhWriteOZ = aFilterObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumBuhWriteOZ);
        }
        if(aFilterObject.sumStCurrentN != null){
            number++;
            aFilterObject.sumStCurrentN = aFilterObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumStCurrentN);
        }
        if(aFilterObject.sumIznCurrentB != null){
            number++;
            aFilterObject.sumIznCurrentB = aFilterObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentB);
        }
        if(aFilterObject.sumIznCurrentN != null){
            number++;
            aFilterObject.sumIznCurrentN = aFilterObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentN);
        }
         if(aFilterObject.typeWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.typeWriteOff);
         }

           if(aFilterObject.userGen != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.userGen);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
        if(aFilterObject.dateEdit != null){
            number++;
            statement.setTimestamp(number,new java.sql.Timestamp(aFilterObject.dateEdit.getTime()));
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE){
            number++;
            if(aFilterObject.modify_time == Long.MIN_VALUE)
                statement.setBigDecimal(number,null);
            else
                statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
        }
       if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.planWorkRef.code);
       }
       if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.actRef.code);
       }
      }

      if(condition.length() > 0 && aBindObjects != null)
       _bindObjectsToPreparedStatement(statement,aBindObjects,number);

      set = statement.executeQuery();
      int i;
      for (i = 0; set.next(); i++) {
        /*
        if (i < fromPosition)
          continue;
        else if (i >= fromPosition + quantity) {
          i++;
          break;
        } */

        anObject = new ENPlanWorkENAct2OSDataShort();

        anObject.code = set.getInt(1);
        if ( set.wasNull() )
            anObject.code = Integer.MIN_VALUE;
        anObject.num_un = set.getInt(2);
        if ( set.wasNull() )
            anObject.num_un = Integer.MIN_VALUE;
        anObject.num_unWriteOff = set.getInt(3);
        if ( set.wasNull() )
            anObject.num_unWriteOff = Integer.MIN_VALUE;
        anObject.kod_inv = set.getString(4);
        anObject.kod_vid = set.getString(5);
        anObject.kod_subsch_b = set.getString(6);
        anObject.name_inv = set.getString(7);
        anObject.kod_ist = set.getString(8);
        anObject.name_ist = set.getString(9);
        anObject.sumBuhWriteOZ = set.getBigDecimal(10);
        if(anObject.sumBuhWriteOZ != null)
            anObject.sumBuhWriteOZ = anObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumStCurrentN = set.getBigDecimal(11);
        if(anObject.sumStCurrentN != null)
            anObject.sumStCurrentN = anObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumIznCurrentB = set.getBigDecimal(12);
        if(anObject.sumIznCurrentB != null)
            anObject.sumIznCurrentB = anObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumIznCurrentN = set.getBigDecimal(13);
        if(anObject.sumIznCurrentN != null)
            anObject.sumIznCurrentN = anObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.typeWriteOff = set.getInt(14);
        if ( set.wasNull() )
            anObject.typeWriteOff = Integer.MIN_VALUE;
        anObject.userGen = set.getString(15);
        anObject.dateEdit = set.getTimestamp(16);

        anObject.planWorkRefCode = set.getInt(17);
    if(set.wasNull())
      anObject.planWorkRefCode = Integer.MIN_VALUE;
        anObject.planWorkRefDateGen = set.getTimestamp(18);
        anObject.planWorkRefDateStart = set.getDate(19);
        anObject.planWorkRefDateFinal = set.getDate(20);
        anObject.planWorkRefYearGen = set.getInt(21);
    if(set.wasNull())
      anObject.planWorkRefYearGen = Integer.MIN_VALUE;
        anObject.planWorkRefMonthGen = set.getInt(22);
    if(set.wasNull())
      anObject.planWorkRefMonthGen = Integer.MIN_VALUE;
        anObject.planWorkRefYearOriginal = set.getInt(23);
    if(set.wasNull())
      anObject.planWorkRefYearOriginal = Integer.MIN_VALUE;
        anObject.planWorkRefMonthOriginal = set.getInt(24);
    if(set.wasNull())
      anObject.planWorkRefMonthOriginal = Integer.MIN_VALUE;
        anObject.planWorkRefUserGen = set.getString(25);
        anObject.planWorkRefDateEdit = set.getDate(26);
        anObject.planWorkRefWorkOrderNumber = set.getString(27);
        anObject.planWorkRefDateWorkOrder = set.getDate(28);
        anObject.planWorkRefPriConnectionNumber = set.getString(29);
        anObject.planWorkRefDateEndPriConnection = set.getDate(30);
        anObject.planWorkRefInvestWorksDescription = set.getString(31);
        anObject.planWorkRefServicesFSideFinId = set.getInt(32);
    if(set.wasNull())
      anObject.planWorkRefServicesFSideFinId = Integer.MIN_VALUE;
        anObject.planWorkRefServicesFSideCnNum = set.getString(33);
        anObject.planWorkRefTotalTimeHours = set.getBigDecimal(34);
        if(anObject.planWorkRefTotalTimeHours != null)
          anObject.planWorkRefTotalTimeHours = anObject.planWorkRefTotalTimeHours.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.planWorkRefTotalTimeDays = set.getBigDecimal(35);
        if(anObject.planWorkRefTotalTimeDays != null)
          anObject.planWorkRefTotalTimeDays = anObject.planWorkRefTotalTimeDays.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.planWorkRefInvestItemNumber = set.getString(36);
        anObject.actRefCode = set.getInt(37);
    if(set.wasNull())
      anObject.actRefCode = Integer.MIN_VALUE;
        anObject.actRefNumberGen = set.getString(38);
        anObject.actRefDateGen = set.getDate(39);
        anObject.actRefFinDocCode = set.getInt(40);
    if(set.wasNull())
      anObject.actRefFinDocCode = Integer.MIN_VALUE;
        anObject.actRefFinDocMechanicCode = set.getInt(41);
    if(set.wasNull())
      anObject.actRefFinDocMechanicCode = Integer.MIN_VALUE;
        anObject.actRefFinMolName = set.getString(42);
        anObject.actRefFinMechanicName = set.getString(43);
        anObject.actRefInvNumber = set.getString(44);
        anObject.actRefUserGen = set.getString(45);
        anObject.actRefDateEdit = set.getDate(46);
        anObject.actRefDateAct = set.getDate(47);

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

  public int[] getFilteredCodeArrayOLD(ENPlanWorkENAct2OSData aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
    Vector result = new Vector();

    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;
    String     selectStr = "SELECT ENPLANWORKENACT2OSDATA.CODE FROM ENPLANWORKENACT2OSDATA";
    String     whereStr = "";
    String     condition = processCondition(anCondition);
    String     orderBy = processCondition(anOrderBy);

    if(orderBy.length() == 0)
     orderBy = "ENPLANWORKENACT2OSDATA.CODE";

    if(quantity < 0)
     quantity = Integer.MAX_VALUE/2;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

      if(aFilterObject != null)
      {
        if(aFilterObject.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.CODE = ?";
        }
        if(aFilterObject.num_un != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UN = ?";
        }
        if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF = ?";
        }
         if (aFilterObject.kod_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_inv.indexOf('*',0) < 0 && aFilterObject.kod_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_INV = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_INV LIKE ?";
         }
         if (aFilterObject.kod_vid != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_vid.indexOf('*',0) < 0 && aFilterObject.kod_vid.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_VID = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_VID LIKE ?";
         }
         if (aFilterObject.kod_subsch_b != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_subsch_b.indexOf('*',0) < 0 && aFilterObject.kod_subsch_b.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B LIKE ?";
         }
         if (aFilterObject.name_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_inv.indexOf('*',0) < 0 && aFilterObject.name_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_INV = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_INV LIKE ?";
         }
         if (aFilterObject.kod_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_ist.indexOf('*',0) < 0 && aFilterObject.kod_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_IST = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_IST LIKE ?";
         }
         if (aFilterObject.name_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_ist.indexOf('*',0) < 0 && aFilterObject.name_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_IST = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_IST LIKE ?";
         }
        if(aFilterObject.sumBuhWriteOZ != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ = ?";
        }
        if(aFilterObject.sumStCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMSTCURRENTN = ?";
        }
        if(aFilterObject.sumIznCurrentB != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB = ?";
        }
        if(aFilterObject.sumIznCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN = ?";
        }
        if(aFilterObject.typeWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.TYPEWRITEOFF = ?";
        }
         if (aFilterObject.userGen != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.USERGEN = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.USERGEN LIKE ?";
         }
        if(aFilterObject.dateEdit != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.DATEEDIT = ?";
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.MODIFY_TIME = ?";
        }
        if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKENACT2OSDATA.PLANWORKREFCODE = ? ";
        }
        if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKENACT2OSDATA.ACTREFCODE = ? ";
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
         if(aFilterObject.num_un != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_un);
         }
         if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_unWriteOff);
         }
         if (aFilterObject.kod_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_inv.indexOf('*',0) < 0 && aFilterObject.kod_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_INV = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_INV LIKE ?";

           if(aFilterObject.kod_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_vid != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_vid.indexOf('*',0) < 0 && aFilterObject.kod_vid.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_VID = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_VID LIKE ?";

           if(aFilterObject.kod_vid != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_vid);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_subsch_b != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_subsch_b.indexOf('*',0) < 0 && aFilterObject.kod_subsch_b.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B LIKE ?";

           if(aFilterObject.kod_subsch_b != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_subsch_b);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.name_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.name_inv.indexOf('*',0) < 0 && aFilterObject.name_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_INV = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_INV LIKE ?";

           if(aFilterObject.name_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_ist.indexOf('*',0) < 0 && aFilterObject.kod_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_IST = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_IST LIKE ?";

           if(aFilterObject.kod_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.name_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.name_ist.indexOf('*',0) < 0 && aFilterObject.name_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_IST = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_IST LIKE ?";

           if(aFilterObject.name_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
        if(aFilterObject.sumBuhWriteOZ != null){
            number++;
            aFilterObject.sumBuhWriteOZ = aFilterObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumBuhWriteOZ);
        }
        if(aFilterObject.sumStCurrentN != null){
            number++;
            aFilterObject.sumStCurrentN = aFilterObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumStCurrentN);
        }
        if(aFilterObject.sumIznCurrentB != null){
            number++;
            aFilterObject.sumIznCurrentB = aFilterObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentB);
        }
        if(aFilterObject.sumIznCurrentN != null){
            number++;
            aFilterObject.sumIznCurrentN = aFilterObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentN);
        }
         if(aFilterObject.typeWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.typeWriteOff);
         }
         if (aFilterObject.userGen != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.USERGEN = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.USERGEN LIKE ?";

           if(aFilterObject.userGen != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.userGen);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
        if(aFilterObject.dateEdit != null){
            number++;
            statement.setTimestamp(number,new java.sql.Timestamp(aFilterObject.dateEdit.getTime()));
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE){
            number++;
            if(aFilterObject.modify_time == Long.MIN_VALUE)
                statement.setBigDecimal(number,null);
            else
                statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
        }
       if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.planWorkRef.code);
       }
       if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.actRef.code);
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

  public int[] getFilteredCodeArray(ENPlanWorkENAct2OSDataFilter aFilterObject, int fromPosition, int quantity) throws PersistenceException
    {
  return getFilteredCodeArray(aFilterObject,(aFilterObject == null) ? (null) : (aFilterObject.conditionSQL), (aFilterObject == null ) ? (null) : (aFilterObject.orderBySQL),fromPosition,quantity, null);
    }


  public int[] getFilteredCodeArray(ENPlanWorkENAct2OSData aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
   {
    Vector result = new Vector();

    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet  set = null;
    String     selectStr = "SELECT ENPLANWORKENACT2OSDATA.CODE FROM ENPLANWORKENACT2OSDATA";
    String     whereStr = "";
    String     condition = processCondition(anCondition);
    String     orderBy = processCondition(anOrderBy);

    if(orderBy.length() == 0)
     orderBy = "ENPLANWORKENACT2OSDATA.CODE";

    if(quantity < 0)
     quantity = Integer.MAX_VALUE/2;

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

      if(aFilterObject != null)
      {
        if(aFilterObject.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.CODE = ?";
        }
        if(aFilterObject.num_un != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UN = ?";
        }
        if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF = ?";
        }
         if (aFilterObject.kod_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_inv.indexOf('*',0) < 0 && aFilterObject.kod_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_INV = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_INV LIKE ?";
         }
         if (aFilterObject.kod_vid != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_vid.indexOf('*',0) < 0 && aFilterObject.kod_vid.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_VID = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_VID LIKE ?";
         }
         if (aFilterObject.kod_subsch_b != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_subsch_b.indexOf('*',0) < 0 && aFilterObject.kod_subsch_b.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B LIKE ?";
         }
         if (aFilterObject.name_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_inv.indexOf('*',0) < 0 && aFilterObject.name_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_INV = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_INV LIKE ?";
         }
         if (aFilterObject.kod_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.kod_ist.indexOf('*',0) < 0 && aFilterObject.kod_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_IST = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.KOD_IST LIKE ?";
         }
         if (aFilterObject.name_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.name_ist.indexOf('*',0) < 0 && aFilterObject.name_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_IST = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.NAME_IST LIKE ?";
         }
        if(aFilterObject.sumBuhWriteOZ != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ = ?";
        }
        if(aFilterObject.sumStCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMSTCURRENTN = ?";
        }
        if(aFilterObject.sumIznCurrentB != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB = ?";
        }
        if(aFilterObject.sumIznCurrentN != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN = ?";
        }
        if(aFilterObject.typeWriteOff != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.TYPEWRITEOFF = ?";
        }
         if (aFilterObject.userGen != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND ";
             if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.USERGEN = ?";
             else
                 whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.USERGEN LIKE ?";
         }
        if(aFilterObject.dateEdit != null) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.DATEEDIT = ?";
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + "  ENPLANWORKENACT2OSDATA.MODIFY_TIME = ?";
        }
        if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKENACT2OSDATA.PLANWORKREFCODE = ? ";
        }
        if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
            if(whereStr.length() != 0)
               whereStr = whereStr + " AND ";
            whereStr = whereStr + " ENPLANWORKENACT2OSDATA.ACTREFCODE = ? ";
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
         if(aFilterObject.num_un != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_un);
         }
         if(aFilterObject.num_unWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.num_unWriteOff);
         }
         if (aFilterObject.kod_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_inv.indexOf('*',0) < 0 && aFilterObject.kod_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_INV = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_INV LIKE ?";

           if(aFilterObject.kod_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_vid != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_vid.indexOf('*',0) < 0 && aFilterObject.kod_vid.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_VID = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_VID LIKE ?";

           if(aFilterObject.kod_vid != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_vid);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_subsch_b != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_subsch_b.indexOf('*',0) < 0 && aFilterObject.kod_subsch_b.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B LIKE ?";

           if(aFilterObject.kod_subsch_b != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_subsch_b);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.name_inv != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.name_inv.indexOf('*',0) < 0 && aFilterObject.name_inv.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_INV = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_INV LIKE ?";

           if(aFilterObject.name_inv != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_inv);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.kod_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.kod_ist.indexOf('*',0) < 0 && aFilterObject.kod_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_IST = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.KOD_IST LIKE ?";

           if(aFilterObject.kod_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.kod_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
         if (aFilterObject.name_ist != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.name_ist.indexOf('*',0) < 0 && aFilterObject.name_ist.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_IST = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.NAME_IST LIKE ?";

           if(aFilterObject.name_ist != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.name_ist);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
        if(aFilterObject.sumBuhWriteOZ != null){
            number++;
            aFilterObject.sumBuhWriteOZ = aFilterObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumBuhWriteOZ);
        }
        if(aFilterObject.sumStCurrentN != null){
            number++;
            aFilterObject.sumStCurrentN = aFilterObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumStCurrentN);
        }
        if(aFilterObject.sumIznCurrentB != null){
            number++;
            aFilterObject.sumIznCurrentB = aFilterObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentB);
        }
        if(aFilterObject.sumIznCurrentN != null){
            number++;
            aFilterObject.sumIznCurrentN = aFilterObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
            statement.setBigDecimal(number,aFilterObject.sumIznCurrentN);
        }
         if(aFilterObject.typeWriteOff != Integer.MIN_VALUE){
             number++;
             statement.setInt(number,aFilterObject.typeWriteOff);
         }
         if (aFilterObject.userGen != null) {
             if(whereStr.length() != 0)
                 whereStr = whereStr + " AND";
             if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.USERGEN = ?";
             else
                 whereStr = whereStr + " ENPLANWORKENACT2OSDATA.USERGEN LIKE ?";

           if(aFilterObject.userGen != null){
               number++;
               StringBuffer likeStr = new StringBuffer();
               likeStr.append(aFilterObject.userGen);
               for(int i = 0;i < likeStr.length();i++){
                    if(likeStr.charAt(i) == '*')
                         likeStr.setCharAt(i,'%');
                    if(likeStr.charAt(i) == '?')
                         likeStr.setCharAt(i,'_');
               }
               statement.setString(number,likeStr.toString());
           }
         }
        if(aFilterObject.dateEdit != null){
            number++;
            statement.setTimestamp(number,new java.sql.Timestamp(aFilterObject.dateEdit.getTime()));
        }
        if(aFilterObject.modify_time != Long.MIN_VALUE){
            number++;
            if(aFilterObject.modify_time == Long.MIN_VALUE)
                statement.setBigDecimal(number,null);
            else
                statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
        }
       if(aFilterObject.planWorkRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.planWorkRef.code);
       }
       if(aFilterObject.actRef.code != Integer.MIN_VALUE) {
           number++;
           statement.setInt(number,aFilterObject.actRef.code);
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


   public ENPlanWorkENAct2OSData getObject(int uid) throws PersistenceException
   {
    ENPlanWorkENAct2OSData result = new ENPlanWorkENAct2OSData();
    result.code = uid;
    loadObject(result);
    if(result.code == Integer.MIN_VALUE)
     return null;
    return result;
   }


  public void loadObject(ENPlanWorkENAct2OSData anObject) throws PersistenceException
   {
    String     selectStr;
    Connection connection = getConnection();
    PreparedStatement statement = null;
    ResultSet set = null;



    selectStr =
    "SELECT  ENPLANWORKENACT2OSDATA.CODE, ENPLANWORKENACT2OSDATA.NUM_UN, ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF, ENPLANWORKENACT2OSDATA.KOD_INV, ENPLANWORKENACT2OSDATA.KOD_VID, ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B, ENPLANWORKENACT2OSDATA.NAME_INV, ENPLANWORKENACT2OSDATA.KOD_IST, ENPLANWORKENACT2OSDATA.NAME_IST, ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ, ENPLANWORKENACT2OSDATA.SUMSTCURRENTN, ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB, ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN, ENPLANWORKENACT2OSDATA.TYPEWRITEOFF, ENPLANWORKENACT2OSDATA.USERGEN, ENPLANWORKENACT2OSDATA.DATEEDIT, ENPLANWORKENACT2OSDATA.MODIFY_TIME, ENPLANWORKENACT2OSDATA.PLANWORKREFCODE, ENPLANWORKENACT2OSDATA.ACTREFCODE "
    +" FROM ENPLANWORKENACT2OSDATA WHERE ENPLANWORKENACT2OSDATA.CODE = ?";

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
        anObject.num_un = set.getInt(2);
        if ( set.wasNull() )
           anObject.num_un = Integer.MIN_VALUE;
        anObject.num_unWriteOff = set.getInt(3);
        if ( set.wasNull() )
           anObject.num_unWriteOff = Integer.MIN_VALUE;
        anObject.kod_inv = set.getString(4);
        anObject.kod_vid = set.getString(5);
        anObject.kod_subsch_b = set.getString(6);
        anObject.name_inv = set.getString(7);
        anObject.kod_ist = set.getString(8);
        anObject.name_ist = set.getString(9);
        anObject.sumBuhWriteOZ = set.getBigDecimal(10);
        if(anObject.sumBuhWriteOZ != null)
            anObject.sumBuhWriteOZ = anObject.sumBuhWriteOZ.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumStCurrentN = set.getBigDecimal(11);
        if(anObject.sumStCurrentN != null)
            anObject.sumStCurrentN = anObject.sumStCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumIznCurrentB = set.getBigDecimal(12);
        if(anObject.sumIznCurrentB != null)
            anObject.sumIznCurrentB = anObject.sumIznCurrentB.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.sumIznCurrentN = set.getBigDecimal(13);
        if(anObject.sumIznCurrentN != null)
            anObject.sumIznCurrentN = anObject.sumIznCurrentN.setScale(6,java.math.BigDecimal.ROUND_HALF_UP);
        anObject.typeWriteOff = set.getInt(14);
        if ( set.wasNull() )
           anObject.typeWriteOff = Integer.MIN_VALUE;
        anObject.userGen = set.getString(15);
        anObject.dateEdit = set.getTimestamp(16);
        anObject.modify_time = set.getLong(17);
        if(set.wasNull())
         anObject.modify_time = Long.MIN_VALUE;
        anObject.planWorkRef.code = set.getInt(18);
        if ( set.wasNull() )
            anObject.planWorkRef.code = Integer.MIN_VALUE;
        anObject.actRef.code = set.getInt(19);
        if ( set.wasNull() )
            anObject.actRef.code = Integer.MIN_VALUE;
        if(anObject.planWorkRef.code != Integer.MIN_VALUE)
        {
           anObject.setPlanWorkRef(
      new com.ksoe.energynet.dataminer.generated.ENPlanWorkDAOGen(connection,getUserProfile()).getRef(anObject.planWorkRef.code));
    }
        if(anObject.actRef.code != Integer.MIN_VALUE)
        {
           anObject.setActRef(
      new com.ksoe.energynet.dataminer.generated.ENActDAOGen(connection,getUserProfile()).getRef(anObject.actRef.code));
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


  public com.ksoe.energynet.valueobject.references.ENPlanWorkENAct2OSDataRef getRef(int anObjectCode) throws PersistenceException
   {
    com.ksoe.energynet.valueobject.references.ENPlanWorkENAct2OSDataRef ref = new com.ksoe.energynet.valueobject.references.ENPlanWorkENAct2OSDataRef();
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

    selectStr = "DELETE FROM  ENPLANWORKENACT2OSDATA WHERE CODE = ?";

    if(getUserProfile() == null)
     throw new PersistenceException("Internal Error (User Profile Is Undefined)");

    ENPlanWorkENAct2OSData object = getObject(uid);

    if(object == null)
     throw new PersistenceException("{%ENPlanWorkENAct2OSData.getObject%} access denied");

    if(new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkENAct2OSData.class.getName(),DataAccessType.DELETE,getUserProfile().getDomainInfo().getDomain()).isAccessDenied())
     throw new PersistenceException("{%ENPlanWorkENAct2OSData.remove%} access denied");

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

    SegregationInfo segregationInfo = new SegregationProcessor().getSegregationInfoForDataAccess(ENPlanWorkENAct2OSData.class.getName(),DataAccessType.READ,getUserProfile().getDomainInfo().getDomain());
    if(segregationInfo.isAccessDenied())
      throw new PersistenceException("{%ENPlanWorkENAct2OSData.getObject%} access denied");

    selectStr =

    "SELECT  ENPLANWORKENACT2OSDATA.CODE FROM  ENPLANWORKENACT2OSDATA WHERE  ENPLANWORKENACT2OSDATA.CODE = ?";
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
    _checkConditionToken(condition,"code","ENPLANWORKENACT2OSDATA.CODE");
    _checkConditionToken(condition,"num_un","ENPLANWORKENACT2OSDATA.NUM_UN");
    _checkConditionToken(condition,"num_unwriteoff","ENPLANWORKENACT2OSDATA.NUM_UNWRITEOFF");
    _checkConditionToken(condition,"kod_inv","ENPLANWORKENACT2OSDATA.KOD_INV");
    _checkConditionToken(condition,"kod_vid","ENPLANWORKENACT2OSDATA.KOD_VID");
    _checkConditionToken(condition,"kod_subsch_b","ENPLANWORKENACT2OSDATA.KOD_SUBSCH_B");
    _checkConditionToken(condition,"name_inv","ENPLANWORKENACT2OSDATA.NAME_INV");
    _checkConditionToken(condition,"kod_ist","ENPLANWORKENACT2OSDATA.KOD_IST");
    _checkConditionToken(condition,"name_ist","ENPLANWORKENACT2OSDATA.NAME_IST");
    _checkConditionToken(condition,"sumbuhwriteoz","ENPLANWORKENACT2OSDATA.SUMBUHWRITEOZ");
    _checkConditionToken(condition,"sumstcurrentn","ENPLANWORKENACT2OSDATA.SUMSTCURRENTN");
    _checkConditionToken(condition,"sumizncurrentb","ENPLANWORKENACT2OSDATA.SUMIZNCURRENTB");
    _checkConditionToken(condition,"sumizncurrentn","ENPLANWORKENACT2OSDATA.SUMIZNCURRENTN");
    _checkConditionToken(condition,"typewriteoff","ENPLANWORKENACT2OSDATA.TYPEWRITEOFF");
    _checkConditionToken(condition,"usergen","ENPLANWORKENACT2OSDATA.USERGEN");
    _checkConditionToken(condition,"dateedit","ENPLANWORKENACT2OSDATA.DATEEDIT");
    _checkConditionToken(condition,"modify_time","ENPLANWORKENACT2OSDATA.MODIFY_TIME");
      // relationship conditions
    _checkConditionToken(condition,"planworkref","PLANWORKREFCODE");
    _checkConditionToken(condition,"planworkref.code","PLANWORKREFCODE");
    _checkConditionToken(condition,"actref","ACTREFCODE");
    _checkConditionToken(condition,"actref.code","ACTREFCODE");
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

  private void _collectAutoIncrementFields(ENPlanWorkENAct2OSData anObject,
      Connection connection) throws PersistenceException {

    SequenceKey hashKey = new SequenceKey("ENPLANWORKENACT2OSDATA", "CODE");
    Integer nextSeqValue = null;
    SequenceValue sequenceValue;
    synchronized (_sequenceTable) { sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
      if (sequenceValue == null) {
        sequenceValue = getNewSequenceValue("ENPLANWORKENACT2OSDATA", "CODE");
        _sequenceTable.put(hashKey, sequenceValue);
      }
      if (!sequenceValue.isNextValueAvailable()) {
        sequenceValue = getNewSequenceValue("ENPLANWORKENACT2OSDATA", "CODE");
        _sequenceTable.put(hashKey, sequenceValue);
      }
    }

    nextSeqValue = sequenceValue.getNextValue();
    if (nextSeqValue == null) {
      throw new PersistenceException(
          "Can't obtain auto increment value from: ENPLANWORKENACT2OSDATA");
    } else {
      anObject.code = nextSeqValue.intValue();
      return;
    }
  }

} // end of ENPlanWorkENAct2OSDataDAO
