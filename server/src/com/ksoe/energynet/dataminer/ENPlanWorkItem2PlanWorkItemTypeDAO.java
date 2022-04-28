//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2011 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkItem2PlanWorkItemTypeDAOGen;
import com.ksoe.lla.security.UserProfile;

/**
 * DAO Object for ENPlanWorkItem2PlanWorkItemType;
 *
 */

public class ENPlanWorkItem2PlanWorkItemTypeDAO extends
        ENPlanWorkItem2PlanWorkItemTypeDAOGen {

    public ENPlanWorkItem2PlanWorkItemTypeDAO(UserProfile anUserProfile,
            Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkItem2PlanWorkItemTypeDAO(Connection aConnection,
            UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }

} // end of ENPlanWorkItem2PlanWorkItemTypeDAO

