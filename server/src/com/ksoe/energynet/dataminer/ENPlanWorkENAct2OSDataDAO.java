
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.energynet.dataminer.generated.ENPlanWorkENAct2OSDataDAOGen;
import com.ksoe.energynet.valueobject.ENPlanWorkENAct2OSData;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkENAct2OSDataShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkENAct2OSDataShortList;

/**
 * DAO Object for ENPlanWorkENAct2OSData;
 *
 */

public class ENPlanWorkENAct2OSDataDAO extends ENPlanWorkENAct2OSDataDAOGen {

    public ENPlanWorkENAct2OSDataDAO(UserProfile anUserProfile,
            Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkENAct2OSDataDAO(Connection aConnection,
            UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }
    
    
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
     " FROM ENPLANWORKENACT2OSDATA LEFT JOIN ENACT ON ENACT.CODE = ENPLANWORKENACT2OSDATA.ACTREFCODE " +
     ", ENPLANWORK " +
     // ", ENACT " +
     //" WHERE "
  "";
     whereStr = " ENPLANWORK.CODE = ENPLANWORKENACT2OSDATA.PLANWORKREFCODE" ; //+
      // whereStr = whereStr +" AND ENACT.CODE = ENPLANWORKENACT2OSDATA.ACTREFCODE" ; //+
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




} // end of ENPlanWorkENAct2OSDataDAO
