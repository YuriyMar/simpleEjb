
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb;

import com.ksoe.energynet.valueobject.ENPlanWorkForm;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFormFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFormShortList;

public interface ENPlanWorkFormController extends javax.ejb.EJBObject
{
  public static final String JNDI_NAME = "ksoe/energynet/ENPlanWorkFormController";


  /*ENPlanWorkForm. Добавить*/
  public int add(ENPlanWorkForm aENPlanWorkForm) throws java.rmi.RemoteException;

  /*ENPlanWorkForm. Удалить*/
  public void remove(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. Изменить*/
  public void save(ENPlanWorkForm aENPlanWorkForm) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. Получить объект*/
  public ENPlanWorkForm getObject(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. Получить полный список*/
  public ENPlanWorkFormShortList getList() throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. Получить список по фильтру*/
  public ENPlanWorkFormShortList getFilteredList(ENPlanWorkFormFilter aENPlanWorkFormFilter) throws  java.rmi.RemoteException;  
  
  /*ENPlanWorkForm. Получить список для просмотра*/
  public ENPlanWorkFormShortList getScrollableList(int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;  
  
  /*ENPlanWorkForm. Получить список для просмотра по фильтру*/
  public ENPlanWorkFormShortList getScrollableFilteredList(ENPlanWorkFormFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException; 

  /*ENPlanWorkForm. Получить список для просмотра по условию*/
  public ENPlanWorkFormShortList getScrollableListByCondition(String aCondition,int aFromPosition,int aQuantity) throws java.rmi.RemoteException;

  
  }