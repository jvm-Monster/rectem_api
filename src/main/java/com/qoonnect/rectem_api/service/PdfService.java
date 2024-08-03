package com.qoonnect.rectem_api.service;

import com.qoonnect.rectem_api.dao.PdfDao;
import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.util.FileIOUtil;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PdfService {
    private final PdfDao pdfDao;
    private final FileIOUtil fileIOUtil;

    public PdfService (){
        fileIOUtil=new FileIOUtil();
        pdfDao = new PdfDao();
    }

    public Response savePdf(InputStream inputStream, FormDataContentDisposition fileDetail,String bookName,String course,String description) {


        try{
            Pdf pdf = new Pdf();
            pdf.setName(bookName);
            pdf.setDescription(description);
            pdf.setCourse(course);
            pdf.setPdfData(fileIOUtil.getUploadedFile(inputStream));
            pdfDao.save(pdf);

            return Response.ok().build();
        } catch (Exception e){
            Response.ResponseBuilder response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage());
            return response.build();
        }
    }


    public Response getPdf(Long id) {
        Pdf pdf = pdfDao.get(id);
        if (pdf == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        // Send the PDF as inline content instead of attachment
        StreamingOutput stream = outputStream -> {
            try {
                outputStream.write(pdf.getPdfData());
            } catch (IOException e) {
                throw new RuntimeException("Error streaming PDF", e);
            }
        };

        return Response.ok(stream)
                .header("Content-Disposition", "inline; filename=\"" + pdf.getName() + "\"")
                .type("application/pdf")  // Set the media type to application/pdf
                .build();
    }
    public Response getAllPdf(){
        List<Pdf> pdfList =  pdfDao.getAll();
        if(pdfList==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pdfList).build();
    }


}
