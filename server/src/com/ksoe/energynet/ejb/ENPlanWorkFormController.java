
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright � 2010 SIT
//---------------------------------------------------------
package com.ksoe.energynet.ejb;

import com.ksoe.energynet.valueobject.ENPlanWorkForm;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFormFilter;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFormShortList;

public interface ENPlanWorkFormController extends javax.ejb.EJBObject
{
  public static final String JNDI_NAME = "ksoe/energynet/ENPlanWorkFormController";


  /*ENPlanWorkForm. ��������*/
  public int add(ENPlanWorkForm aENPlanWorkForm) throws java.rmi.RemoteException;

  /*ENPlanWorkForm. �������*/
  public void remove(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. ��������*/
  public void save(ENPlanWorkForm aENPlanWorkForm) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. �������� ������*/
  public ENPlanWorkForm getObject(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. �������� ������ ������*/
  public ENPlanWorkFormShortList getList() throws  java.rmi.RemoteException;

  /*ENPlanWorkForm. �������� ������ �� �������*/
  public ENPlanWorkFormShortList getFilteredList(ENPlanWorkFormFilter aENPlanWorkFormFilter) throws  java.rmi.RemoteException;  
  
  /*ENPlanWorkForm. �������� ������ ��� ���������*/
  public ENPlanWorkFormShortList getScrollableList(int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;  
  
  /*ENPlanWorkForm. �������� ������ ��� ��������� �� �������*/
  public ENPlanWorkFormShortList getScrollableFilteredList(ENPlanWorkFormFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException; 

  /*ENPlanWorkForm. �������� ������ ��� ��������� �� �������*/
  public ENPlanWorkFormShortList getScrollableListByCondition(String aCondition,int aFromPosition,int aQuantity) throws java.rmi.RemoteException;

  
  }