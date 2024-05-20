package org.acme;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path("/tenther")
public class TentherResource {
    @GET
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    public String multiply(@PathParam("number") Integer number) {
        return String.valueOf(number * 10) + "\n";
    }
}