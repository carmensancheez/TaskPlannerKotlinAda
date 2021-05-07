package org.adaschool.TaskPlanner.services

import org.adaschool.TaskPlanner.controller.dto.TaskDto
import org.adaschool.TaskPlanner.data.document.Task

interface TaskService {

    fun save(taskDto: TaskDto):Task

    fun update(taskId: String,taskDto: TaskDto):Task

    fun findTaskById(taskId: String):Task?

    fun all():List<Task>

    fun delete(taskId: String)

    fun findTaskByUserId(userId: String):List<Task>?

}