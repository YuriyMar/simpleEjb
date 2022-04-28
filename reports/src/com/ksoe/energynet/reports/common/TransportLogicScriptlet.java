package com.ksoe.energynet.reports.common;

//import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Date;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillParameter;

//import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.security.UserProfile;

//import com.ksoe.energypro.logic.PwrMeasurementLogic;
//import com.ksoe.energypro.valueobject.EPPwrMeasurement;

import com.ksoe.energynet.logic.TransportLogic;
import com.ksoe.authorization.util.AuthorizationJNDINames;

public class TransportLogicScriptlet extends JRDefaultScriptlet {

	
	//private Connection connection = null;

	//private static UserProfile userProfile = null;
        
    //private TransportLogic logic = null;
    //private EPPwrMeasurement pwrMeasurement = new EPPwrMeasurement();
    
	public void beforeReportInit() throws JRScriptletException {
		//JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
	    //connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
	    //userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
	    //logic = new TransportLogic(connection, userProfile);
	}
	

	public BigDecimal getTimeByPlan(int planCode) throws PersistenceException{
		JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
		
		return new TransportLogic(connection, userProfile).calculateTimeForPlanByDistance(planCode, 0);
	}
	
	public BigDecimal getTimeByPlanItem(int planItemCode) throws PersistenceException{
		JRFillParameter jrParameterMap = (JRFillParameter) this.parametersMap.get("REPORT_PARAMETERS_MAP");
		Connection connection = (Connection) ((JRFillParameter) this.parametersMap.get("REPORT_CONNECTION")).getValue();
		UserProfile userProfile = (UserProfile) ((HashMap) jrParameterMap.getValue()).get("userProfile");
		
		return new TransportLogic(connection, userProfile).calculateTimeForPlanItemByDistance(planItemCode);//calculateTimeForPlanByDistance(planItemCode, 1);

	}
	
/*	
	public BigDecimal getKZU(
	){

		return logic.calculateCoef(pwr).coefLoadMorning;	
	}

	public BigDecimal getKZV(	){
	
		return logic.calculateCoef(pwr).coefLoadEvening;	
	}
*/
}
	

