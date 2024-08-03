package com.qoonnect.rectem_api.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileIOUtil {


    //covert to byte array
    public byte[] getUploadedFile(InputStream uploadedFile ) throws IOException {
        return IOUtils.toByteArray(uploadedFile);// Convert input stream to byte array
    }

}
