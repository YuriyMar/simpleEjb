
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2014 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork2ActFailure;  	
  */

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

    import  com.ksoe.energynet.valueobject.references.ENPlanWorkRef;
    import  com.ksoe.energynet.valueobject.references.ENActFailureRef;

public class ENPlanWork2ActFailure implements Serializable {

    public int  code = Integer.MIN_VALUE; 
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public ENActFailureRef actFailureRef = new ENActFailureRef();
    public static final String tableName = "ENPLANWORK2ACTFAILURE";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2ACTFAILURE.CODE";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2ACTFAILURE.PLANREFCODE";
    public static final String actFailureRef_Attr = "actFailureRef";
    public static final String actFailureRef_Field = "ACTFAILUREREFCODE";
    public static final String  actFailureRef_QFielld = "ENPLANWORK2ACTFAILURE.ACTFAILUREREFCODE";


    public void setCode(int aValue){
       code = aValue;
    }

    public int getCode(){
       return code;
    }

    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }
    public void setActFailureRef(ENActFailureRef aValue){
       actFailureRef = aValue;
    }

    public ENActFailureRef getActFailureRef(){
       return actFailureRef;
    }

} // end of ENPlanWork2ActFailureValueObject

