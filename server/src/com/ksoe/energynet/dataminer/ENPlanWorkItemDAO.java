
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Sat Sep 26 14:38:30 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkItemDAOGen;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENEstimateItemStatus;
import com.ksoe.energynet.valueobject.ENPlanWorkItem;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItemShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItemShortList;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.techcard.valueobject.TKTechCardSource;

  /**
  *  DAO Object for ENPlanWorkItem;
  *
  */

public class ENPlanWorkItemDAO extends ENPlanWorkItemDAOGen {

  //public ENPlanWorkItemDAO() {super();}
  //public ENPlanWorkItemDAO(Connection aConnection) {super(aConnection);}
  //public ENPlanWorkItemDAO(UserProfile anUserProfile) {super(anUserProfile);}
  public ENPlanWorkItemDAO(UserProfile anUserProfile,Connection aConnection) {super(anUserProfile,aConnection);}
  public ENPlanWorkItemDAO(Connection aConnection,UserProfile anUserProfile) {super(aConnection,anUserProfile);}


  @Override
public ENPlanWorkItemShortList getScrollableFilteredList(ENPlanWorkItem aFilterObject,String anCondition,String anOrderBy,int fromPosition,int quantity,Vector aBindObjects) throws PersistenceException
  {
   ENPlanWorkItemShortList result = new ENPlanWorkItemShortList();
   ENPlanWorkItemShort anObject;
   result.list = new Vector();

   String     selectStr;
   Connection connection = getConnection();
   PreparedStatement statement = null;
   ResultSet  set = null;
   String     whereStr = "";
   String     condition = processCondition(anCondition);
   String     orderBy = processCondition(anOrderBy);

   if(orderBy.length() == 0)
    orderBy = "ENPLANWORKITEM.CODE";

   if(quantity < 0)
    quantity = Integer.MAX_VALUE/2;

   // System.out.println(" ##### planCode = " + aFilterObject.planRef.code);
   // System.out.println(" ##### planItem = " + aFilterObject.code);
   // System.out.println(" ##### anCondition = " + anCondition);
   // System.out.println(" ##### user = " + getUserProfile().userName);

   if ((anCondition == null)
        && (aFilterObject == null
                && aFilterObject.planRef.code == Integer.MIN_VALUE
                && aFilterObject.code == Integer.MIN_VALUE)) {

    throw new PersistenceException("пустой фильтр");
   }

   if(getUserProfile() == null)
    throw new PersistenceException("Internal Error (User Profile Is Undefined)");

   selectStr = "SELECT "+
/*
 *
   "ENPLANWORKITEM.CODE"+
   ",ENPLANWORKITEM.COUNTGEN"+
   ",ENPLANWORKITEM.USERGEN"+
   ",ENPLANWORKITEM.DATEEDIT"+

    ", ENPLANWORK.CODE " +
    ", ENPLANWORK.DATEGEN " +


    ", ENPLANWORK.USERGEN " +
    ", ENPLANWORK.DATEEDIT " +
    ", KARTI.ID " +
    ", KARTI.OPISANIE " +
*/
   "ENPLANWORKITEM.CODE"+
   ",ENPLANWORKITEM.COUNTGEN"+
   ",ENPLANWORKITEM.USERGEN"+
   ",ENPLANWORKITEM.DATEEDIT"+

    ", ENPLANWORK.CODE " +
    ", ENPLANWORK.DATEGEN " +
    ", ENPLANWORK.YEARGEN " +
    ", ENPLANWORK.MONTHGEN " +
    ", ENPLANWORK.USERGEN " +
    ", ENPLANWORK.DATEEDIT " +


    /*
     * tehkart
     *
     */
  //  "  ,(select text from repimport.tmesure_unit ei where ei.id = kk.code_meas_instr_edizm )" +
    ",TKMEASUREMENT.NAME" +
    //", kk.code_meas_instr " +
   // ",(select name from tehkart.spr_meas_instr where code = kk.code_meas_instr)" +

    ",TKTECHCARD.METER" +
    ", TKTECHCARD.code " +
    ", TKTECHCARD.name " +
    ", TKTECHCARD.techkartnumber " +
    ", TKTECHCARD.NORMOFTIME " +
    ", TKTECHCARDSOURCE.name " +

    ", ENPLANWORKITEM.TIMEGEN " +

    ", (select coalesce(e.cost,0) from enestimateitem e" +
    "    where e.planitemrefcode = enplanworkitem.code " +
    "      and tktechcardsource.code = " + TKTechCardSource.SERVICES_FROM_SIDE + ") " +   // 19

    ", ENPLANWORKITEM.COMMENTGEN " +

    ", coalesce((select k.koef " +
    "   from enplanworkitem2tkkoef ik, tktechcardsourcekoef k " +
    "    where ik.sourcekoefcode = k.code and ik.planworkitemrefcode = ENPLANWORKITEM.code " +
    "    limit 1), 1) as koef " +


    ", (select case when (s.code = " + ENEstimateItemStatus.PLANNED + ") then 'Запланована' " +
    "    when (s.code = " + ENEstimateItemStatus.ORDERED + ") then 'Замовлена' " +
    "      when (s.code = " + ENEstimateItemStatus.PRESENT + ") then 'Виконано' else s.name end " +
    " from enestimateitem e, enestimateitemstatus s " +
    " where e.statusrefcode = s.code " +
    " and e.kindrefcode = " + ENEstimateItemKind.SERVICES + " " +
    " and e.planitemrefcode = enplanworkitem.code) as estatus " +       //  22


   " FROM ENPLANWORKITEM " +
   ", ENPLANWORK " +
  // ", TEHKART.KARTI kk " +
   ", TKTECHCARD  " +
   ", TKMEASUREMENT  " +
   ", TKTECHCARDSOURCE " +

   //" WHERE "
    "";


   whereStr = " ENPLANWORK.CODE = ENPLANWORKITEM.PLANREFCODE" ; //+
   whereStr = whereStr + " AND TKTECHCARD.MEASUREMENTCODE = TKMEASUREMENT.CODE" ; //+

    whereStr = whereStr +" AND TKTECHCARD.CODE = ENPLANWORKITEM.KARTAREFCODE" ; //+
    whereStr = whereStr +" AND TKTECHCARD.TECHCARDSOURCECODE = TKTECHCARDSOURCE.CODE" ;



        //selectStr = selectStr + " ${s} ENPLANWORKITEM.CODE IN ( SELECT ENPLANWORKITEM.CODE FROM ENPLANWORKITEM ";

//" ";
     if(aFilterObject != null)
     {
       if(aFilterObject.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM.CODE = ?";
       }
       if(aFilterObject.countGen != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM.COUNTGEN = ?";
       }
        if (aFilterObject.userGen != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND ";
            if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                whereStr = whereStr + "  ENPLANWORKITEM.USERGEN = ?";
            else
                whereStr = whereStr + "  ENPLANWORKITEM.USERGEN LIKE ?";
        }
       if(aFilterObject.dateEdit != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM.DATEEDIT = ?";
       }
       if(aFilterObject.planRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM.PLANREFCODE = ? ";
       }
       if(aFilterObject.kartaRef.code != Integer.MIN_VALUE) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "ENPLANWORKITEM.KARTAREFCODE = ? ";
       }

       if(aFilterObject.timeGen != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM.TIMEGEN = ?";
       }

       if(aFilterObject.costGen != null) {
           if(whereStr.length() != 0)
              whereStr = whereStr + " AND ";
           whereStr = whereStr + "  ENPLANWORKITEM.COSTGEN = ?";
       }
     }

     if(condition.length() != 0)
     {
        if(whereStr.length() != 0)
           whereStr = whereStr + " AND ";

        whereStr = whereStr + " (" + condition + ")";
     }
//+ " WHERE" +  сделано выше ????
    if(whereStr.length() != 0)
        selectStr = selectStr + " WHERE" + whereStr;

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
        if (aFilterObject.commentGen != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND";
            if (aFilterObject.commentGen.indexOf('*',0) < 0 && aFilterObject.commentGen.indexOf('?',0) < 0)
                whereStr = whereStr + " ENPLANWORKITEM.COMMENTGEN = ?";
            else
                whereStr = whereStr + " ENPLANWORKITEM.COMMENTGEN LIKE ?";

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
        }
        if (aFilterObject.userGen != null) {
            if(whereStr.length() != 0)
                whereStr = whereStr + " AND";
            if (aFilterObject.userGen.indexOf('*',0) < 0 && aFilterObject.userGen.indexOf('?',0) < 0)
                whereStr = whereStr + " ENPLANWORKITEM.USERGEN = ?";
            else
                whereStr = whereStr + " ENPLANWORKITEM.USERGEN LIKE ?";

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
           statement.setDate(number,new java.sql.Date(aFilterObject.dateEdit.getTime()));
       }

       if(aFilterObject.modify_time != Long.MIN_VALUE){
           number++;
           if(aFilterObject.modify_time == Long.MIN_VALUE)
               statement.setBigDecimal(number,null);
           else
               statement.setBigDecimal(number,new java.math.BigDecimal(aFilterObject.modify_time));
       }
      if(aFilterObject.planRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.planRef.code);
      }
      if(aFilterObject.kartaRef.code != Integer.MIN_VALUE) {
          number++;
          statement.setInt(number,aFilterObject.kartaRef.code);
      }

      if(aFilterObject.timeGen != null){
          number++;
          aFilterObject.timeGen = aFilterObject.timeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
          statement.setBigDecimal(number,aFilterObject.timeGen);
      }

      if(aFilterObject.costGen != null){
          number++;
          aFilterObject.costGen = aFilterObject.costGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
          statement.setBigDecimal(number,aFilterObject.costGen);
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

      anObject = new ENPlanWorkItemShort();
/*
      anObject.code = set.getInt(1);
      if ( set.wasNull() )
          anObject.code = Integer.MIN_VALUE;
      anObject.countGen = set.getBigDecimal(2);
      if(anObject.countGen != null)
          anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
      anObject.userGen = set.getString(3);
      anObject.dateEdit = set.getDate(4);


      anObject.planRefCode = set.getInt(5);

      anObject.planRefDateGen = set.getDate(6);

      anObject.planRefYearGen = set.getInt(7);

      anObject.planRefMonthGen = set.getInt(8);

      anObject.planRefUserGen = set.getString(9);

      anObject.planRefDateEdit = set.getDate(10);

      anObject.kartaRefCode = set.getInt(11);

      anObject.kartaRefName = set.getString(12);
*/
      anObject.code = set.getInt(1);
      if ( set.wasNull() )
          anObject.code = Integer.MIN_VALUE;
      anObject.countGen = set.getBigDecimal(2);
      if(anObject.countGen != null)
          anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
      anObject.userGen = set.getString(3);
      anObject.dateEdit = set.getDate(4);


      anObject.planRefCode = set.getInt(5);

      anObject.planRefDateGen = set.getDate(6);

      anObject.planRefYearGen = set.getInt(7);

      anObject.planRefMonthGen = set.getInt(8);

      anObject.planRefUserGen = set.getString(9);

      anObject.planRefDateEdit = set.getDate(10);

      anObject.measurementUnit = set.getString(11);
      anObject.meter = set.getString(12);

      anObject.kartaRefCode = set.getInt(13);
      anObject.kartaRefName = set.getString(14);
      anObject.kartaNum = set.getString(15);

      anObject.normOfTime = set.getBigDecimal(16);
      if (anObject.normOfTime != null)
        anObject.normOfTime = anObject.normOfTime.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

      anObject.sourceName = set.getString(17);

      anObject.timeGen = set.getBigDecimal(18);
      if ( anObject.timeGen != null)
        anObject.timeGen = anObject.timeGen.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

      anObject.costServicesFS = set.getBigDecimal(19);
      if (anObject.costServicesFS != null)
        anObject.costServicesFS = anObject.costServicesFS.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

      anObject.commentGen = set.getString(20);

      anObject.koef = set.getBigDecimal(21);
      if (anObject.koef != null)
        anObject.koef = anObject.koef.setScale(4,java.math.BigDecimal.ROUND_HALF_UP);

      anObject.statusName = set.getString(22);

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


  public ENPlanWorkItemShortList getMaxNormHumenCount(int planCode) throws PersistenceException
  {
   ENPlanWorkItemShortList result = new ENPlanWorkItemShortList();
   ENPlanWorkItemShort anObject;
   result.list = new Vector();

   String     selectStr;
   Connection connection = getConnection();
   PreparedStatement statement = null;
   ResultSet  set = null;


// NET-2264 Не учитываем тех хуменов которые весят на техкартах с признаком "Не расчитывать доставку"
   selectStr =   "  select max(kolvo) from (  " +
   "  Select  sum(tk2tech.kolvo) as kolvo  ,  " +
   "           tkd.code " +
   "     From enplanworkitem enpi , tktechcard tkd , tkelement tkel , " +
   "                       tkposition tkpos  ,   tkelement2techcard tk2tech   " +
   "     Where enpi.kartarefcode = tkd.code " +
   "       And tkel.elementtypecode = 240000001 " +
   "       And tk2tech.elementcode = tkel.code " +
   "       And tk2tech.techkartcode = tkd.code " +
   "       And tkpos.elementcode = tkel.code  " +
   "       And enpi.planrefcode =  ? " +
   "       And enpi.countgen <> 0 " +
   "       And coalesce(tkd.isnotcalculdelivery,0) <> 1  " +
   "       group by tkd.code " +
   "       ) sel " ;

   // selectStr =   "  select max(kolvo) from (  " +
   // "  Select  sum(tk2tech.kolvo) as kolvo  ,  " +
   // "           tkd.code " +
   // "     From enplanworkitem enpi , tktechcard tkd , tkelement tkel , " +
   // "                       tkposition tkpos  ,   tkelement2techcard tk2tech   " +
// "     Where enpi.kartarefcode = tkd.code " +
// "       And tkel.elementtypecode = 240000001 " +
// "       And tk2tech.elementcode = tkel.code " +
// "       And tk2tech.techkartcode = tkd.code " +
// "       And tkpos.elementcode = tkel.code  " +
// "       And enpi.planrefcode =  ? " +
// "       And enpi.countgen <> 0 " +
// "       group by tkd.code " +
// "       ) sel " ;



/*
   selectStr = " select picode, kol from ( " +
   " select  " +
   " pi.code as picode, count(hi.code) as kol " +
   " from enplanworkitem pi, enhumenitem hi " +
   " where pi.planrefcode = ? " +
   " and pi.code = hi.planitemrefcode " +
   " and pi.countgen<>0 and hi.countgen<>0 " +
   " group by pi.code " +
   " ) q " +
   " order by kol desc " ;
*/

   try
    {
     statement = connection.prepareStatement(selectStr);

     statement.setInt(1,planCode);

     set = statement.executeQuery();
     int i;
     for(i = 0;set.next();i++)
     {


      anObject = new ENPlanWorkItemShort();
      //anObject.code = set.getInt(1);
     // if ( set.wasNull() )
     //     anObject.code = Integer.MIN_VALUE;
      anObject.countGen = set.getBigDecimal(1);
      if(anObject.countGen != null)
          anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
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

  public ENPlanWorkItemShortList getRealHumenCount(int planCode) throws PersistenceException
  {
   ENPlanWorkItemShortList result = new ENPlanWorkItemShortList();
   ENPlanWorkItemShort anObject;
   result.list = new Vector();

   String     selectStr;
   Connection connection = getConnection();
   PreparedStatement statement = null;
   ResultSet  set = null;

   // NET-2264 Не учитываем тех хуменов которые весят на техкартах с признаком "Не расчитывать доставку"
   selectStr = " select pcode, count(kol) from ( " +
   " select " +
   " distinct  " +
   " pi.planrefcode as pcode,  " +
   " f.tabnumber as kol " +
   " from enplanworkitem pi, enhumenitem hi, finworker f  , tktechcard tk  " + // YM
   " where pi.planrefcode = ? " +
   " and pi.code = hi.planitemrefcode " +
   " and pi.countgen<>0 and hi.countfact<>0 " +
   " and hi.finworkercode = f.code " +
   " and tk.code = pi.kartarefcode " + // YM
   " and coalesce(tk.isnotcalculdelivery,0) <> 1  " +
   "  " +
   " ) q " +
   " group by pcode " ;

   /*selectStr = " select pcode, count(kol) from ( " +
   " select " +
   " distinct  " +
   " pi.planrefcode as pcode,  " +
   " f.tabnumber as kol " +
   " from enplanworkitem pi, enhumenitem hi, finworker f " +
   " where pi.planrefcode = ? " +
   " and pi.code = hi.planitemrefcode " +
   " and pi.countgen<>0 and hi.countfact<>0 " +
   " and hi.finworkercode = f.code " +
   "  " +
   " ) q " +
   " group by pcode " ;*/


   try
    {
     statement = connection.prepareStatement(selectStr);

     statement.setInt(1,planCode);

     set = statement.executeQuery();
     int i;
     for(i = 0;set.next();i++)
     {


      anObject = new ENPlanWorkItemShort();
      anObject.code = set.getInt(1);
      if ( set.wasNull() )
          anObject.code = Integer.MIN_VALUE;
      anObject.countGen = set.getBigDecimal(2);
      if(anObject.countGen != null)
          anObject.countGen = anObject.countGen.setScale(3,java.math.BigDecimal.ROUND_HALF_UP);
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

} // end of ENPlanWorkItemDAO

