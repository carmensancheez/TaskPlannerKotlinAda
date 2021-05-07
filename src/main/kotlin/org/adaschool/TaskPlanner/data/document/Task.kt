package org.adaschool.TaskPlanner.data.document

import org.adaschool.TaskPlanner.controller.dto.TaskDto
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Task(
    @Id
    val id: String?,
    val description: String,
    val personResponsible:String,
    val dueDate: String,
    val status: String,
    val userId: String
) {
    constructor(taskDto: TaskDto):this(
        null,
        taskDto.description,
        taskDto.personResponsible,
        taskDto.dueDate,
        taskDto.status,
        taskDto.userId
    )
}