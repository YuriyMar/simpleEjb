package com.ksoe.energynet.util;

import java.util.TimerTask;


import com.ksoe.energynet.util.tools.NetTools;

/**
 * 
 * Класс для заданий синхронизации между Программами EnergyNet и Буфет
 * 
 *  24.03.2020 Пока только для синхронизации между табельными номерами и кодами карт на скидку 
 * 
 * @author kreschishinma
 *
 */
public class BuffetSynchronization extends TimerTask {

	@Override
	public void run() {
        String ipAddres = NetTools.getInetAddress();
        if (!ipAddres.equals(Tools.ENERGY_NET_SERVER_IP)) return;
		System.out.println("Start buffet synchronization");
		ScheduledEventsManager manager = new ScheduledEventsManager();
		manager.synchronizeBuffetCards();
		System.out.println("Finish buffet synchronization");
	}

}
