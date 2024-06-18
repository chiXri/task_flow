package com.task.flow.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String subcategory; // Actividad específica
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private Task task;
}