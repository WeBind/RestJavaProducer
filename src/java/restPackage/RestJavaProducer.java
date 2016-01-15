package com.app;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.xml.ws.handler.MessageContext;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.xml.ws.WebServiceContext;

/**
 * REST Web Service
 * http://localhost:8080/RestProducer/webresources/rest pour appeler la methode doRestWork de ce WebService
 * @author Salah
 */
@Path("rest")
public class RestJavaProducer {

    @Context
    private UriInfo context;
    @Resource
    private WebServiceContext svcCtx;

    /** Creates a new instance of RestJavaProducer */
    public RestJavaProducer() {
    }

    /**
     * Retrieves representation of an instance of com.app.RestJavaProducer
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public byte[] doRestWork() throws InterruptedException {
        /*
        ServletContext ctx = retrieveSC();
        System.out.println("Doing stuff - delay:" + ctx.getAttribute(Config.CONFIG_DELAY));
        byte[] mess;
        long time2,
        time = System.currentTimeMillis();

        int delay = Integer.parseInt(
        ctx.getAttribute(Config.CONFIG_DELAY) != null ? ctx.getAttribute(Config.CONFIG_DELAY).toString() : "0"),
        messageSize = Integer.parseInt(
        ctx.getAttribute(Config.CONFIG_MSG_SIZE) != null ? ctx.getAttribute(Config.CONFIG_MSG_SIZE).toString() : "10");

        mess = generateMessage(messageSize);

        time2 = System.currentTimeMillis();

        Thread.sleep(delay - (time2 - time));
         */
        byte[] mess;
        mess = generateMessage(100);
        return mess;
    }

    /**
     * PUT method for updating or creating an instance of RestJavaProducer
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * Configures the WebService
     * @param messageSize   size of the answer message
     * @param delay         delay before the service responds
     */
    @WebMethod()
    public String configure(int messageSize, int delay) {
        ServletContext ctx = retrieveSC();
        ctx.setAttribute("delay", delay);
        ctx.setAttribute("messageSize", messageSize);

        return "OK";
    }

    private byte[] generateMessage(int messageSize) {
        byte[] mess = new byte[messageSize];
        for (int i = 0; i < messageSize; i++) {
            mess[i] = (byte) ('a' + (i % 26));
        }
        return mess;
    }

    private ServletContext retrieveSC() {
        MessageContext msgCtx = svcCtx.getMessageContext();
        return (ServletContext) msgCtx.get(MessageContext.SERVLET_CONTEXT);
    }
}
