package com.qoonnect.rectem_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String course;

    private String description;

    @Lob
    private byte[] pdfData;

    public void setName(String name) {
        if (name != null && name.toLowerCase().endsWith(".pdf")) {
            this.name = name.substring(0, name.length() - 4); // Remove the last 4 characters (.pdf)
        } else {
            this.name = name; // Handle other cases if necessary
        }
    }
}
