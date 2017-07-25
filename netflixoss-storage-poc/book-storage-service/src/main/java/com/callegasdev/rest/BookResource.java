package com.callegasdev.rest;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Singleton
@Path("/book")
public class BookResource {

    private static final Logger logger = LoggerFactory.getLogger(BookResource.class);

    private BookService service;

    @Inject
    public BookResource(BookService service) {
        this.service = service;
    }

    @GET
    @Path("add/{bookName}/{bookAuthor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response set(@PathParam("bookName") String bookName, @PathParam("bookAuthor") String bookAuthor) {
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
