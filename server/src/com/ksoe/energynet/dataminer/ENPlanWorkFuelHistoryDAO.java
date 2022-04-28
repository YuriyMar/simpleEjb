
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2015 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkFuelHistoryDAOGen;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENPlanWorkFuelHistory;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkFuelHistoryShort;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkFuelHistoryShortList;
import com.ksoe.energypro.util.EnergyproPersistenceExceptionAnalyzer;
import com.ksoe.lla.persistence.SequenceValue;
import com.ksoe.lla.persistence.VersionKey;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;

/**
 * DAO Object for ENPlanWorkFuelHistory;
 *
 */

public class ENPlanWorkFuelHistoryDAO extends ENPlanWorkFuelHistoryDAOGen {

    public ENPlanWorkFuelHistoryDAO(UserProfile anUserProfile,
            Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkFuelHistoryDAO(Connection aConnection,
            UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }
    
    public int add(ENPlanWorkFuelHistory anObject) throws PersistenceException
    {
        anObject.userAdd = getUserProfile().userName;
        anObject.dateAdd = new Date();
        anObject.userGen = getUserProfile().userName;
        anObject.dateEdit = new Date();

        return super.add(anObject);
    }

    public void save(ENPlanWorkFuelHistory anObject) throws PersistenceException
    {
        anObject.userGen = getUserProfile().userName;
        anObject.dateEdit = new Date();

        super.save(anObject);
    }    

    public int _collectAutoIncrementVersion(int planCode)
            throws PersistenceException {

        //SequenceKey hashKey = new SequenceKey("ENPLANWORKFUELHISTORY", "VERSION");
    	VersionKey hashKey = new VersionKey("ENPLANWORKFUELHISTORY", "VERSION", planCode);
    	
        Integer nextSeqValue = null;
        SequenceValue sequenceValue;
        synchronized (_sequenceTable) { 
        	
        	/*
        	sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
            
        	if (sequenceValue == null) {
                sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
                _sequenceTable.put(hashKey, sequenceValue);
            }

            if (!sequenceValue.isNextValueAvailable()) {
            	sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
                _sequenceTable.put(hashKey, sequenceValue);
            }
            */
        	
        	//sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
        	
        	sequenceValue = (SequenceValue) _sequenceTable.get(hashKey);
            
        	if (sequenceValue == null) {
                sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
                _sequenceTable.put(hashKey, sequenceValue);
            }

            if (!sequenceValue.isNextValueAvailable()) {
            	sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
                _sequenceTable.put(hashKey, sequenceValue);
            }        	
        }

        nextSeqValue = sequenceValue.getNextValue();
        if (nextSeqValue == null) {
            throw new PersistenceException(
                    "Can't obtain auto increment value from: ENPLANWORKFUELHISTORY");
        } else {
            return nextSeqValue.intValue();
        }
    }
    
    public void _resetAutoIncrementVersion(int planCode) throws PersistenceException 
    {
    	VersionKey hashKey = new VersionKey("ENPLANWORKFUELHISTORY", "VERSION", planCode);

        synchronized (_sequenceTable) 
        { 
        	SequenceValue sequenceValue = getNewVersion("ENPLANWORKFUELHISTORY", "PLANREFCODE", "" + planCode);
            _sequenceTable.put(hashKey, sequenceValue);
        }
    }
    
    public ENPlanWorkFuelHistoryShortList getFuelListForPlan(int planCode) throws PersistenceException
    {
    	if (planCode == Integer.MIN_VALUE)
    	{
    		throw new SystemException("\n\nNET-4440 Не заданий код плану!");
    	}
    	
    	ENPlanWorkFuelHistoryShortList result = new ENPlanWorkFuelHistoryShortList();
    	ENPlanWorkFuelHistoryShort anObject;
    	result.list = new Vector();

    	String     selectStr;
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	ResultSet  set = null;
   

    	selectStr =
		  " select ft.code, sum(ei.countfact) " +
		  " from enplanwork p, enestimateitem ei, tkmaterials m, tkfueltype ft " +
		  " where ei.planrefcode = p.code " +
		  "   and ei.materialrefcode = m.code " +
		  "   and ft.materialrefcode = m.code " +
		  ///// 19.03.15 Надо брать не только топливо, которое в ГСМ, но и то, которое в материалах 
		  //"   and ei.kindrefcode = " + ENEstimateItemKind.GSM +
		  "   and ei.kindrefcode in (" + ENEstimateItemKind.MATERIALS + ", " + ENEstimateItemKind.GSM + ") " + 
		  /////
		  "   and p.code = " + planCode +
		  " group by ft.code ";
    		  
    	try
    	{
    		statement = connection.prepareStatement(selectStr);
    		set = statement.executeQuery();
    		int i;
    		for(i = 0; set.next(); i++)
    		{
	  	        anObject = new ENPlanWorkFuelHistoryShort();
	  	
	  	        /*
	  	        anObject.code = set.getInt(1);
	  	        if ( set.wasNull() )
	  	            anObject.code = Integer.MIN_VALUE;
	  	        */
	  	        anObject.code = Integer.MIN_VALUE;
	  	      
	  	        anObject.fuelTypeRefCode = set.getInt(1);
	  	        if ( set.wasNull() )
	  	            anObject.fuelTypeRefCode = Integer.MIN_VALUE;  	        
	  	        
	  	        anObject.countGen = set.getBigDecimal(2);
	  	        if (anObject.countGen != null)
	  	        {
	  	            anObject.countGen = anObject.countGen.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
	  	        }
	  	
	  	        anObject.planRefCode = planCode;
	
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
    
} // end of ENPlanWorkFuelHistoryDAO
