package com.task.flow.services;

import com.task.flow.models.Subtask;
import com.task.flow.repositories.SubtaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubtaskService {

    @Autowired
    private SubtaskRepository subtaskRepository;

    public Subtask saveSubtask(Subtask subtask) {
        return subtaskRepository.save(subtask);
    }

    public List<Subtask> getAllSubtasks() {
        return subtaskRepository.findAll();
    }

    public Optional<Subtask> getSubtaskById(Long id) {
        return subtaskRepository.findById(id);
    }

    public void deleteSubtask(Long id) {
        subtaskRepository.deleteById(id);
    }
}
