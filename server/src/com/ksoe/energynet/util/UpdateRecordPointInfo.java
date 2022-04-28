package com.ksoe.energynet.util;

import java.util.TimerTask;

public class UpdateRecordPointInfo extends TimerTask {

	@Override
	public void run() {

        /* работаем только на указанном серваке */
        String ipAddres = Tools.getInetAddress();

        if (!ipAddres.equals(Tools.ENERGY_NET_SERVER_IP)) return;

		ScheduledEventsManager scheduledEventsManager = new ScheduledEventsManager();
		System.out.println("#####################  Start updateRecordpointBytInfo!!!");
		scheduledEventsManager.updateRecordpointBytInfo();
		System.out.println("#####################  updateRecordpointBytInfo is Done!!!");

		System.out.println("#####################  Start updateRecordpointPromInfo!!!");
		scheduledEventsManager.updateRecordpointPromInfo();
		System.out.println("#####################  updateRecordpointPromInfo is Done!!!");

    }


}