package com.task.flow.controllers;

import com.task.flow.models.Task;
import com.task.flow.models.User;
import com.task.flow.services.TaskService;
import com.task.flow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Task> getTasks() {
        User user = getCurrentUser();
        return taskService.getTasksByUser(user);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setUser(getCurrentUser());
        return taskService.createTask(task);
    }

    private User getCurrentUser() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userService.findByUsername(username);
    }
}
