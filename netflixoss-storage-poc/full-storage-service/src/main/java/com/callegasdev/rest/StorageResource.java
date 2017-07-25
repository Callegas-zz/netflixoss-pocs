package com.callegasdev.rest;

import com.callegasdev.hystrix.SimpleCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/storage")
public class StorageResource {

	private static final Logger logger = LoggerFactory.getLogger(StorageResource.class);

	private StorageService service;

	@Inject
	public StorageResource(StorageService service) {
		this.service = service;
	}
	
	@GET
	@Path("view/{product}/")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAll(@PathParam("product") String product){
		try {
			return Response.ok( service.show(product) + ""  ).
							header("CREATED_BY", ArchaiusPropsManager.getInstance().getCreator()).
							build();
		} catch (Exception e) {
			logger.error("Error creating json response.", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("products")
	@Produces(MediaType.TEXT_PLAIN)
	public Response products() {
		try {
			
			String result = new SimpleCommand("book, vinyl").observe().toBlocking().first();
			
			return Response.ok( result ).
							header("CREATED_BY", ArchaiusPropsManager.getInstance().getCreator()).
							build();
		} catch (Exception e) {
			logger.error("Error creating json response.", e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
