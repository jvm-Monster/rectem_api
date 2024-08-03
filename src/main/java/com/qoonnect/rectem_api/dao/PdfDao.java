package com.qoonnect.rectem_api.dao;

import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.util.TransactionUtil;

import java.util.List;
import java.util.stream.Collectors;

public class PdfDao {
    // Code to handle database operations (CRUD)
    public void save(Pdf pdf) {
        // Code to save PDF to the database
        TransactionUtil.executeTransaction(session -> {
            session.persist(pdf);
        });
    }

    public Pdf get(Long id) {
        // Code to retrieve PDF from the database
        return TransactionUtil.executeTransaction(session -> {
          return session.get(Pdf.class, id);
        });
    }

    //Retrieves all pdf info without the pdf content
    public List<Pdf> getAll() {
        List<Object[]> results = TransactionUtil.executeTransaction(session -> {
            return session.createQuery("SELECT p.id, p.name, p.description, p.course FROM Pdf p", Object[].class).list();
        });

        return results.stream().map(result -> new Pdf((Long) result[0], (String) result[1],(String) result[2],null,null)).collect(Collectors.toList());
    }
}
