
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2020 SIT
//---------------------------------------------------------

package  com.ksoe.energynet.valueobject;



  /**
  * Value Object for ENPlanWork2CCDamageLog;  	
  */

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

    import  com.ksoe.energynet.valueobject.references.ENPlanWorkRef;
    import  com.ksoe.energynet.valueobject.references.ENPlan2CCDamageLogTypeRef;

public class ENPlanWork2CCDamageLog implements Serializable {

    public int  code = Integer.MIN_VALUE;
    public long  modify_time = Long.MIN_VALUE;
    public String  userAdd; 
    public Date dateAdd ;
    public String  userGen; 
    public Date dateEdit ;
    public int  ccDamageLogCode = Integer.MIN_VALUE;
    public String  newsPaperName; 
    public String  newsPaperNumber; 
    public Date datePubl ;
    public Date dateBegin ;
    public Date dateFinish ;
    public int  needsApprovalByCustomer = Integer.MIN_VALUE;
    public ENPlanWorkRef planRef = new ENPlanWorkRef();
    public ENPlan2CCDamageLogTypeRef typeRef = new ENPlan2CCDamageLogTypeRef();
    public static final String tableName = "ENPLANWORK2CCDAMAGELOG";
    public static final String code_Attr = "code";
    public static final String code_Field = "CODE";
    public static final String code_QFielld = "ENPLANWORK2CCDAMAGELOG.CODE";
    public static final String modify_time_Attr = "modify_time";
    public static final String modify_time_Field = "MODIFY_TIME";
    public static final String modify_time_QFielld = "ENPLANWORK2CCDAMAGELOG.MODIFY_TIME";
    public static final String userAdd_Attr = "userAdd";
    public static final String userAdd_Field = "USERADD";
    public static final String userAdd_QFielld = "ENPLANWORK2CCDAMAGELOG.USERADD";
    public static final String dateAdd_Attr = "dateAdd";
    public static final String dateAdd_Field = "DATEADD";
    public static final String dateAdd_QFielld = "ENPLANWORK2CCDAMAGELOG.DATEADD";
    public static final String userGen_Attr = "userGen";
    public static final String userGen_Field = "USERGEN";
    public static final String userGen_QFielld = "ENPLANWORK2CCDAMAGELOG.USERGEN";
    public static final String dateEdit_Attr = "dateEdit";
    public static final String dateEdit_Field = "DATEEDIT";
    public static final String dateEdit_QFielld = "ENPLANWORK2CCDAMAGELOG.DATEEDIT";
    public static final String ccDamageLogCode_Attr = "ccDamageLogCode";
    public static final String ccDamageLogCode_Field = "CCDAMAGELOGCODE";
    public static final String ccDamageLogCode_QFielld = "ENPLANWORK2CCDAMAGELOG.CCDAMAGELOGCODE";
    public static final String newsPaperName_Attr = "newsPaperName";
    public static final String newsPaperName_Field = "NEWSPAPERNAME";
    public static final String newsPaperName_QFielld = "ENPLANWORK2CCDAMAGELOG.NEWSPAPERNAME";
    public static final String newsPaperNumber_Attr = "newsPaperNumber";
    public static final String newsPaperNumber_Field = "NEWSPAPERNUMBER";
    public static final String newsPaperNumber_QFielld = "ENPLANWORK2CCDAMAGELOG.NEWSPAPERNUMBER";
    public static final String datePubl_Attr = "datePubl";
    public static final String datePubl_Field = "DATEPUBL";
    public static final String datePubl_QFielld = "ENPLANWORK2CCDAMAGELOG.DATEPUBL";
    public static final String dateBegin_Attr = "dateBegin";
    public static final String dateBegin_Field = "DATEBEGIN";
    public static final String dateBegin_QFielld = "ENPLANWORK2CCDAMAGELOG.DATEBEGIN";
    public static final String dateFinish_Attr = "dateFinish";
    public static final String dateFinish_Field = "DATEFINISH";
    public static final String dateFinish_QFielld = "ENPLANWORK2CCDAMAGELOG.DATEFINISH";
    public static final String needsApprovalByCustomer_Attr = "needsApprovalByCustomer";
    public static final String needsApprovalByCustomer_Field = "NEEDSAPPROVALBYCUSTOMER";
    public static final String needsApprovalByCustomer_QFielld = "ENPLANWORK2CCDAMAGELOG.NEEDSAPPROVALBYCUSTOMR";
    public static final String planRef_Attr = "planRef";
    public static final String planRef_Field = "PLANREFCODE";
    public static final String  planRef_QFielld = "ENPLANWORK2CCDAMAGELOG.PLANREFCODE";
    public static final String typeRef_Attr = "typeRef";
    public static final String typeRef_Field = "TYPEREFCODE";
    public static final String  typeRef_QFielld = "ENPLANWORK2CCDAMAGELOG.TYPEREFCODE";



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


    public void setUserAdd(String aValue){
       userAdd = aValue;
    }

    public String getUserAdd(){
       return userAdd;
    }


    public void setDateAdd(Date aValue){
       dateAdd = aValue;
    }

    public Date getDateAdd(){
       return dateAdd;
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


    public void setCcDamageLogCode(int aValue){
       ccDamageLogCode = aValue;
    }

    public int getCcDamageLogCode(){
       return ccDamageLogCode;
    }


    public void setNewsPaperName(String aValue){
       newsPaperName = aValue;
    }

    public String getNewsPaperName(){
       return newsPaperName;
    }


    public void setNewsPaperNumber(String aValue){
       newsPaperNumber = aValue;
    }

    public String getNewsPaperNumber(){
       return newsPaperNumber;
    }


    public void setDatePubl(Date aValue){
       datePubl = aValue;
    }

    public Date getDatePubl(){
       return datePubl;
    }


    public void setDateBegin(Date aValue){
       dateBegin = aValue;
    }

    public Date getDateBegin(){
       return dateBegin;
    }


    public void setDateFinish(Date aValue){
       dateFinish = aValue;
    }

    public Date getDateFinish(){
       return dateFinish;
    }


    public void setNeedsApprovalByCustomer(int aValue){
       needsApprovalByCustomer = aValue;
    }

    public int getNeedsApprovalByCustomer(){
       return needsApprovalByCustomer;
    }

    public void setPlanRef(ENPlanWorkRef aValue){
       planRef = aValue;
    }

    public ENPlanWorkRef getPlanRef(){
       return planRef;
    }
    public void setTypeRef(ENPlan2CCDamageLogTypeRef aValue){
       typeRef = aValue;
    }

    public ENPlan2CCDamageLogTypeRef getTypeRef(){
       return typeRef;
    }

} // end of ENPlanWork2CCDamageLogValueObject

