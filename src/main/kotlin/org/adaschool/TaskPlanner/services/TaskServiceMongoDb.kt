package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.TaskDto
import org.adaschool.TaskPlanner.data.repository.TaskRepository
import org.adaschool.TaskPlanner.data.document.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskServiceMongoDb(@Autowired val taskRepository: TaskRepository): TaskService {
    override fun save(taskDto: TaskDto): Task {
        return taskRepository.save(Task(taskDto))
    }

    override fun update(taskId: String, taskDto: TaskDto): Task {
        val task = taskRepository.findById(taskId).get()
        return taskRepository.save(task)
    }

    override fun findTaskById(taskId: String): Task? {
        return taskRepository.findById(taskId).orElse(null)
    }

    override fun all(): List<Task> {
        return taskRepository.findAll()
    }

    override fun delete(taskId: String) {
        return taskRepository.deleteById(taskId)
    }

    override fun findTaskByUserId(userId: String): List<Task>? {
        return taskRepository.findTaskByUserId(userId)
    }
}