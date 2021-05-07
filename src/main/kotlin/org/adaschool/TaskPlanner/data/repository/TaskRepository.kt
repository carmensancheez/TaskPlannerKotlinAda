package org.adaschool.TaskPlanner.data.repository

import org.adaschool.TaskPlanner.data.document.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<Task, String> {

    fun findTaskByUserId(userId: String): List<Task>?

}