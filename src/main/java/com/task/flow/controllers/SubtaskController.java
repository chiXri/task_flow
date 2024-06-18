package com.task.flow.controllers;

import com.task.flow.models.Subtask;
import com.task.flow.services.SubtaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subtasks")
public class SubtaskController {

    @Autowired
    private SubtaskService subtaskService;

    @PostMapping
    public Subtask createSubtask(@RequestBody Subtask subtask) {
        return subtaskService.saveSubtask(subtask);
    }

    @GetMapping
    public List<Subtask> getAllSubtasks() {
        return subtaskService.getAllSubtasks();
    }

    @GetMapping("/{id}")
    public Optional<Subtask> getSubtaskById(@PathVariable Long id) {
        return subtaskService.getSubtaskById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubtask(@PathVariable Long id) {
        subtaskService.deleteSubtask(id);
    }
}
