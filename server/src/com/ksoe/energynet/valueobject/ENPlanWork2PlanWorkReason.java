
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork2PlanWorkReason;  	
  */

import java.io.Serializable;

import com.ksoe.energynet.valueobject.references.ENPlanWorkReasonRef;
import com.ksoe.energynet.valueobject.references.ENPlanWorkRef;

public class ENPlanWork2PlanWorkReason implements Serializable {

    public int  code = Integer.MIN_VALUE; 
    public long  modify_time = Long.MIN_VALUE;
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public ENPlanWorkReasonRef planWorkReasonRef = new ENPlanWorkReasonRef();
    public static final String tableName = "ENPLANWORK2PLANWORKRSN";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2PLANWORKRSN.CODE";
    public static final String modify_time_Attr = "modify_time";
    public static final String modify_time_Field = "MODIFY_TIME";
    public static final String modify_time_QFielld = "ENPLANWORK2PLANWORKRSN.MODIFY_TIME";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2PLANWORKRSN.PLANREFCODE";
    public static final String planWorkReasonRef_Attr = "planWorkReasonRef";
    public static final String planWorkReasonRef_Field = "PLANWORKREASONREFCODE";
    public static final String  planWorkReasonRef_QFielld = "ENPLANWORK2PLANWORKRSN.PLANWORKREASONREFCODE";


    public void setCode(int aValue){
       code = aValue;
    }

    public int getCode(){
       return code;
    }

    public void setModify_time(long aValue){
       modify_time = aValue;
    }

    public long getModify_time(){
       return modify_time;
    }

;
    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }
    public void setPlanWorkReasonRef(ENPlanWorkReasonRef aValue){
       planWorkReasonRef = aValue;
    }

    public ENPlanWorkReasonRef getPlanWorkReasonRef(){
       return planWorkReasonRef;
    }

} // end of ENPlanWork2PlanWorkReasonValueObject

