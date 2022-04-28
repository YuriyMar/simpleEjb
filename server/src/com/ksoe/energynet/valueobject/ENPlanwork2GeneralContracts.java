
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2019 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanwork2GeneralContracts;  	
  */

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

    import  com.ksoe.energynet.valueobject.references.ENGeneralContractsRef;
    import  com.ksoe.energynet.valueobject.references.ENPlanWorkRef;

public class ENPlanwork2GeneralContracts implements Serializable {

    public int  code = Integer.MIN_VALUE;
    public String  userGen; 
    public Date dateEdit ;
    public ENGeneralContractsRef generalContractRef = new ENGeneralContractsRef();
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public static final String tableName = "ENPLANWORK2GNRLCNTRCTS";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2GNRLCNTRCTS.CODE";
    public static final String userGen_Attr = "userGen";
    public static final String userGen_Field = "USERGEN";
    public static final String userGen_QFielld = "ENPLANWORK2GNRLCNTRCTS.USERGEN";
    public static final String dateEdit_Attr = "dateEdit";
    public static final String dateEdit_Field = "DATEEDIT";
    public static final String dateEdit_QFielld = "ENPLANWORK2GNRLCNTRCTS.DATEEDIT";
    public static final String generalContractRef_Attr = "generalContractRef";
    public static final String generalContractRef_Field = "GENERALCONTRACTREFCODE";
    public static final String  generalContractRef_QFielld = "ENPLANWORK2GNRLCNTRCTS.GENERALCONTRACTREFCODE";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2GNRLCNTRCTS.PLANREFCODE";



    public void setCode(int aValue){
       code = aValue;
    }

    public int getCode(){
       return code;
    }


    public void setUserGen(String aValue){
       userGen = aValue;
    }

    public String getUserGen(){
       return userGen;
    }


    public void setDateEdit(Date aValue){
       dateEdit = aValue;
    }

    public Date getDateEdit(){
       return dateEdit;
    }

    public void setGeneralContractRef(ENGeneralContractsRef aValue){
       generalContractRef = aValue;
    }

    public ENGeneralContractsRef getGeneralContractRef(){
       return generalContractRef;
    }
    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }

} // end of ENPlanwork2GeneralContractsValueObject

