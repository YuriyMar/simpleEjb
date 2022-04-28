//---------------------------------------------------------
// Application: EnergyNET2
// Author     : Kherson
//
// Copyright © 2010 SIT
//---------------------------------------------------------

package com.ksoe.energynet.dataminer;

import java.sql.Connection;

import com.ksoe.energynet.dataminer.generated.ENPlanWorkFormDAOGen;
import com.ksoe.lla.security.UserProfile;

/**
 * DAO Object for ENPlanWorkForm;
 *
 */

public class ENPlanWorkFormDAO extends ENPlanWorkFormDAOGen {

    public ENPlanWorkFormDAO(UserProfile anUserProfile, Connection aConnection) {
        super(anUserProfile, aConnection);
    }

    public ENPlanWorkFormDAO(Connection aConnection, UserProfile anUserProfile) {
        super(aConnection, anUserProfile);
    }

} // end of ENPlanWorkFormDAO

