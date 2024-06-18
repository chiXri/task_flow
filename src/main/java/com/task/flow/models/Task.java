package com.task.flow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Task {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String description;
        private String category; // Productividad, Ocio, Bienestar, Tareas
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        @ManyToOne
        private User user;

        @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
        private List<Subtask> subtasks;
    }

