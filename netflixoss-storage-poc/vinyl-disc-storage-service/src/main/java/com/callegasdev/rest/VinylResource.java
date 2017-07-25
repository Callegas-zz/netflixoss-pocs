package com.callegasdev.rest;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/vinyl")
public class VinylResource {

    private static final Logger logger = LoggerFactory.getLogger(VinylResource.class);

    private VinylService service;

    @Inject
    public VinylResource(VinylService service) {
        this.service = service;
    }

    @GET
    @Path("add/{vinylName}/{vinylAuthor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response set(@PathParam("vinylName") String bookName, @PathParam("vinylAuthor") String bookAuthor) {
        try {
            return Response.ok( service.addBook(bookName, bookAuthor) + "" ).build();
        } catch (Exception e) {
            logger.error("Error creating json response.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        try {
            return Response.ok( service.getAll() + "" ).build();
        } catch (Exception e) {
            logger.error("Error creating json response.", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
