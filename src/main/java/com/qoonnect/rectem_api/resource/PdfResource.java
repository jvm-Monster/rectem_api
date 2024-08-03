package com.qoonnect.rectem_api.resource;

import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.service.PdfService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.InputStream;

@Path("/pdfs")
public class PdfResource {
    private final PdfService pdfService = new PdfService();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPdf(@FormDataParam("name") String bookName,
                              @FormDataParam("course") String course,
                              @FormDataParam("description") String description,
                              @FormDataParam("file") InputStream uploadedInputStream,
                              @FormDataParam("file") FormDataContentDisposition fileDetail) {

        if (fileDetail == null) {
            System.out.printf("its null");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return pdfService.savePdf(uploadedInputStream, fileDetail,bookName,course,description);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getPdf(@PathParam("id") Long id) {
       return pdfService.getPdf(id);
    }

    @GET
    @Produces("application/json")
    public Response getAllPdf(){
        return pdfService.getAllPdf();
    }
}
