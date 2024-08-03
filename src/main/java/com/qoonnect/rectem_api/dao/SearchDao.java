package com.qoonnect.rectem_api.dao;

import com.qoonnect.rectem_api.model.Pdf;
import com.qoonnect.rectem_api.util.TransactionUtil;
import lombok.var;

import java.util.List;
import java.util.stream.Collectors;

public class SearchDao {

    // Retrieves all pdf info without the pdf content based on search criteria
    public List<Pdf> search(String name, String description, String course) {
        List<Object[]> results = TransactionUtil.executeTransaction(session -> {
            String hql = "SELECT p.id, p.name, p.description, p.course FROM Pdf p WHERE 1=1";
            if (name != null && !name.isEmpty()) {
                hql += " AND p.name LIKE :name";
            }
            if (description != null && !description.isEmpty()) {
                hql += " AND p.description LIKE :description";
            }
            if (course != null && !course.isEmpty()) {
                hql += " AND p.course LIKE :course";
            }
            var query = session.createQuery(hql, Object[].class);
            if (name != null && !name.isEmpty()) {
                query.setParameter("name", "%" + name + "%");
            }
            if (description != null && !description.isEmpty()) {
                query.setParameter("description", "%" + description + "%");
            }
            if (course != null && !course.isEmpty()) {
                query.setParameter("course", "%" + course + "%");
            }
            return query.list();
        });

        return results.stream().map(result -> new Pdf((Long) result[0], (String) result[1], (String) result[2], null, null)).collect(Collectors.toList());
    }
}
