package com.qoonnect.rectem_api.resource;

import com.qoonnect.rectem_api.dao.SearchDao;
import com.qoonnect.rectem_api.model.Pdf;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/search")
public class SearchResource {

    private SearchDao searchDao = new SearchDao();

    @GET
    public Response search(@QueryParam("name") String name,
                           @QueryParam("description") String description,
                           @QueryParam("course") String course) {
        // Use the searchDao to perform the search with the provided parameters
        List<Pdf> results = searchDao.search(name, description, course);
        return Response.ok(results, MediaType.APPLICATION_JSON).build();
    }
}
