package com.example.Logs.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Document(collection = "Logs")
public class Log {

    @Id
    private String id;

    private LocalDate fecha;
    private String descripcion;

}
