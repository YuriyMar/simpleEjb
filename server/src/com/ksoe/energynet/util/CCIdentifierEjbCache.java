package com.ksoe.energynet.util;

import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class CCIdentifierEjbCache {

    public CCIdentifierEjbCache() throws NamingException {
    }

    public static synchronized EJBHome getHome(String JNDI, String homeClass)
            throws NamingException {

        // айпи  ил÷ентра
        String identifierServerIp = "10.77.11.180";

        EJBHome home = (EJBHome) homes.get(homeClass);
        Context ctx = getInitialContext(identifierServerIp);
        try {
            home = (EJBHome) PortableRemoteObject.narrow(ctx.lookup(JNDI),
                    Class.forName(homeClass));
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        homes.put(homeClass, home);
        return home;
    }

    private static Context getInitialContext(String identifierServerIp)
            throws NamingException {
        Properties props = new Properties();
        props.put(
                "java.naming.provider.url",
                (new StringBuilder()).append("jnp://")
                        .append(identifierServerIp).append(":1099").toString());

        props.put("java.naming.factory.initial",
                "org.jboss.security.jndi.JndiLoginInitialContextFactory");
        props.put("java.naming.factory.url.pkgs",
                "org.jboss.naming:org.jnp.interfaces");
        props.put("java.naming.factory.initial",
                "org.jnp.interfaces.NamingContextFactory");
        props.put("java.naming.security.authentication", "simple");
        return new InitialContext(props);
    }

    private static Hashtable homes = new Hashtable();
    Context ctx;

}