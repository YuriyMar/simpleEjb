package com.ksoe.energynet.util;

import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ksoe.lla.persistence.exception.SystemException;

public class BillingEjbCache {
    private static Hashtable homes = new Hashtable();
    Context ctx;

    public BillingEjbCache() throws NamingException {
    }


	public static synchronized EJBHome getHome(String JNDI, String homeClass,
			String billingServerIp, String billingServerJnpPort) throws NamingException {
		return getHome(JNDI, homeClass, billingServerIp, billingServerJnpPort, false);
	}

    public static synchronized EJBHome getHome(String JNDI, String homeClass,
            String billingServerIp, String billingServerJnpPort, boolean silent) throws NamingException {

        /** работаем только на указанном серваке */
        String ipAddres = Tools.getInetAddress();
        if (!ipAddres.equals(Tools.ENERGY_NET_SERVER_IP)) {
            throw new SystemException("\n \n"
                    + " Внимание биллинг!!!\n"
                    + " Используется только для промышленной эксплуатации!!!");
       }

        EJBHome home = (EJBHome) homes.get(homeClass);
        Context ctx = getInitialContext(billingServerIp, billingServerJnpPort, silent);
        try {
            home = (EJBHome) PortableRemoteObject.narrow(ctx.lookup(JNDI),
                    Class.forName(homeClass));

        } catch (ClassCastException e) {
        	 throw new SystemException("\n \n" + "Нет связи с биллингом...");
        } catch (ClassNotFoundException e) {
        	 throw new SystemException("\n \n" + "Нет связи с биллингом...");
        }

        homes.put(homeClass, home);
        return home;
    }


	private static Context getInitialContext(String billingServerIp, String billingServerJnpPort,
			boolean silent) throws NamingException {
        Properties props = new Properties();

        // DEBUG !!!!!!!!!!!!
        // billingServerIp = "10.77.11.224";

        props.put(Context.PROVIDER_URL, "jnp://" + billingServerIp + ":" + billingServerJnpPort);

        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.security.jndi.JndiLoginInitialContextFactory");
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");

        if (silent) {
        	/** статическая авторизация */
            props.put(Context.SECURITY_PRINCIPAL, "energynet");
            props.put(Context.SECURITY_CREDENTIALS, "1");
        } else {
            /** авторизация по пользователю */
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            props.put(Context.SECURITY_AUTHENTICATION, "simple");
        }

        return new InitialContext(props);
    }


}