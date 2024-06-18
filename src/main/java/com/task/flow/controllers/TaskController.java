package com.task.flow.controllers;

import com.task.flow.models.Task;
import com.task.flow.models.User;
import com.task.flow.services.TaskService;
import com.task.flow.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task", description = "Task management APIs")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks for the current user")
    public List<Task> getTasks() {
        User user = getCurrentUser();
        return taskService.getTasksByUser(user);
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "Create a new task for the current user")
    public Task createTask(@RequestBody Task task) {
        task.setUser(getCurrentUser());
        return taskService.createTask(task);
    }

    private User getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userService.findByUsername(username);
    }
}
