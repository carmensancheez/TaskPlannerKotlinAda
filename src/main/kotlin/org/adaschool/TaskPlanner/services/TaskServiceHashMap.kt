package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.TaskDto
import org.adaschool.TaskPlanner.data.document.Task
import java.util.concurrent.atomic.AtomicLong

class TaskServiceHashMap:TaskService {

    private val tasks = HashMap<String,Task>()

    private val nextOid = AtomicLong()

    override fun save(taskDto: TaskDto): Task {
        val task = Task(taskDto)
        tasks[taskDto.id] = task
        return task

    }

    override fun update(taskId: String, taskDto: TaskDto): Task {
        if (tasks.containsKey(taskId)){
            tasks[taskId] = Task(taskDto)
        }
        return tasks[taskId]!!

    }

    override fun findTaskById(taskId: String): Task? {
        return if (tasks.containsKey(taskId))
            tasks[taskId]
        else
            null

    }

    override fun all(): List<Task> {
        return tasks.values.toList()

    }

    override fun delete(taskId: String) {
        tasks.remove(taskId) != null
    }

    override fun findTaskByUserId(userId: String): List<Task>? {
        TODO("Not yet implemented")
    }

}