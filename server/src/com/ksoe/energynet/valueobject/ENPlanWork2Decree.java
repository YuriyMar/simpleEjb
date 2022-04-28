//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------

package com.ksoe.energynet.valueobject;

/**
 * Value Object for ENPlanWork2Decree;
 */

import java.io.Serializable;
import java.util.Date;

import com.ksoe.energynet.valueobject.references.ENPlanWorkRef;

public class ENPlanWork2Decree implements Serializable {

	public int code = Integer.MIN_VALUE;
	public int decreeCode = Integer.MIN_VALUE;
	public String userGen;
	public Date dateEdit;
	public ENPlanWorkRef planRef = new ENPlanWorkRef();
	public static final String tableName = "ENPLANWORK2DECREE";
	public static final String code_Attr = "code";
	public static final String code_Field = "CODE";
	public static final String code_QFielld = "ENPLANWORK2DECREE.CODE";
	public static final String decreeCode_Attr = "decreeCode";
	public static final String decreeCode_Field = "DECREECODE";
	public static final String decreeCode_QFielld = "ENPLANWORK2DECREE.DECREECODE";
	public static final String userGen_Attr = "userGen";
	public static final String userGen_Field = "USERGEN";
	public static final String userGen_QFielld = "ENPLANWORK2DECREE.USERGEN";
	public static final String dateEdit_Attr = "dateEdit";
	public static final String dateEdit_Field = "DATEEDIT";
	public static final String dateEdit_QFielld = "ENPLANWORK2DECREE.DATEEDIT";
	public static final String planRef_Attr = "planRef";
	public static final String planRef_Field = "PLANREFCODE";
	public static final String planRef_QFielld = "ENPLANWORK2DECREE.PLANREFCODE";

	public void setCode(int aValue) {
		code = aValue;
	}

	public int getCode() {
		return code;
	}

	public void setDecreeCode(int aValue) {
		decreeCode = aValue;
	}

	public int getDecreeCode() {
		return decreeCode;
	}

	public void setUserGen(String aValue) {
		userGen = aValue;
	}

	public String getUserGen() {
		return userGen;
	}

	public void setDateEdit(Date aValue) {
		dateEdit = aValue;
	}

	public Date getDateEdit() {
		return dateEdit;
	}

	public void setPlanRef(ENPlanWorkRef aValue) {
		planRef = aValue;
	}

	public ENPlanWorkRef getPlanRef() {
		return planRef;
	}

} // end of ENPlanWork2DecreeValueObject

