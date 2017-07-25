package com.callegasdev;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by callegas on 13/07/17.
 */

@Path("/")
public interface LibraryBookApi {

    @GET
    @Path("/")
    List<BookRestModel> all();

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    void add(BookRestModel book);

    @GET
    @Path("/{id}")
    BookRestModel getById(@PathParam("id") Long id);

}
