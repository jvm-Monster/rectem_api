package com.qoonnect.rectem_api.configuration;

import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.model.User;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory;

    static{
        try{
            Configuration configuration = new Configuration();
            configuration.setProperties(HibernateProperty.getProperties());
            configuration.getProperties()
                    .put("hibernate.connection.datasource",HikarIcpDataSourceUtil.getDataSource());

            configuration.addAnnotatedClass(Pdf.class);
            configuration.addAnnotatedClass(User.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }catch (Exception e){
            throw new ExceptionInInitializerError(e);
        }
    }

    private HibernateUtil(){}
}
