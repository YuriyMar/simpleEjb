
//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork2FKOrder;  	
  */

import java.io.Serializable;

import com.ksoe.energynet.valueobject.references.ENPlanWorkRef;
import com.ksoe.rqorder.valueobject.references.RQFKOrderRef;

public class ENPlanWork2FKOrder implements Serializable {

    public int  code = Integer.MIN_VALUE; 
    public long  modify_time = Long.MIN_VALUE;
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public RQFKOrderRef fkOrderRef = new RQFKOrderRef();
    public static final String tableName = "ENPLANWORK2FKORDER";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2FKORDER.CODE";
    public static final String modify_time_Attr = "modify_time";
    public static final String modify_time_Field = "MODIFY_TIME";
    public static final String modify_time_QFielld = "ENPLANWORK2FKORDER.MODIFY_TIME";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2FKORDER.PLANREFCODE";
    public static final String fkOrderRef_Attr = "fkOrderRef";
    public static final String fkOrderRef_Field = "FKORDERREFCODE";
    public static final String  fkOrderRef_QFielld = "ENPLANWORK2FKORDER.FKORDERREFCODE";


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

    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }
    public void setFkOrderRef(RQFKOrderRef aValue){
       fkOrderRef = aValue;
    }

    public RQFKOrderRef getFkOrderRef(){
       return fkOrderRef;
    }

} // end of ENPlanWork2FKOrderValueObject

