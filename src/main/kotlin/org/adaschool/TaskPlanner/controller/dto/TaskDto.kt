package org.adaschool.TaskPlanner.controller.dto

import java.util.*

data class TaskDto(
    val id: String,
    val description: String,
    val personResponsible:String,
    val startDate: Date,
    val endDate: Date,
    val status: String

)