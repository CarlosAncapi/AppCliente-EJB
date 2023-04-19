package org.cancapi.appcliente.ejb;

import org.cancapi.webapp.ejb.models.Producto;
import org.cancapi.webapp.ejb.service.ServiceEJBRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        ServiceEJBRemote service = null;
        ServiceEJBRemote service2 = null;

            //la configuracion de las conexiones quedaron en la carpeta resources, en el archivo "jndi.properties"

        //final Properties env = new Properties();
        //env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        //env.put("jboss.naming.client.ejb.context", true);
        try {
            InitialContext ctx = new InitialContext();
            service = (ServiceEJBRemote) ctx.lookup("ejb:/appejb-remote/ServiceEJB!org.cancapi.webapp.ejb.service.ServiceEJBRemote?stateful");
            service2 = (ServiceEJBRemote) ctx.lookup("ejb:/appejb-remote/ServiceEJB!org.cancapi.webapp.ejb.service.ServiceEJBRemote?stateful");

            String saludo = service.saludar("Andres");
            String saludo2 = service2.saludar("John");
            System.out.println(saludo);
            System.out.println(saludo2);

            Producto p = service.crear(new Producto("Sandia"));
            System.out.println("nuevo producto: "+ p );

            service.listar().forEach(System.out::println);


        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}