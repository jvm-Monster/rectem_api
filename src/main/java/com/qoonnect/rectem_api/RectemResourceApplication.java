package com.qoonnect.rectem_api;

import com.qoonnect.rectem_api.cors.CorsFilter;
import com.qoonnect.rectem_api.resource.PdfResource;
import com.qoonnect.rectem_api.resource.UserResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RectemResourceApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(MultiPartFeature.class);
        resources.add(PdfResource.class);
        resources.add(UserResource.class);
        resources.add(CorsFilter.class);
        return resources;
    }
}
