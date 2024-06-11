package com.task.flow.repositories;


import com.task.flow.models.Task;
import com.task.flow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}