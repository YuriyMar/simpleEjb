//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkKindDAOGen;
import com.ksoe.lla.security.UserProfile;

/**
 * DAO Object for ENPlanWorkKind;
 *
 */

public class ENPlanWorkKindDAO extends ENPlanWorkKindDAOGen {

    public ENPlanWorkKindDAO(UserProfile anUserProfile, Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkKindDAO(Connection aConnection, UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }

} // end of ENPlanWorkKindDAO

