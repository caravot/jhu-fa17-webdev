package com.cravott1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld2")
public class MyResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        System.out.println("Hello");
        return "SUCCESSFUL OUTPUT FROM SERVICE!!";
    }
}
