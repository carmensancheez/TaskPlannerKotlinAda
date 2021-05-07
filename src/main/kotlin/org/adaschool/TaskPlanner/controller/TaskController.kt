package org.adaschool.TaskPlanner.controller

import org.adaschool.TaskPlanner.controller.dto.TaskDto
import org.adaschool.TaskPlanner.data.document.Task
import org.adaschool.TaskPlanner.services.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/tasks")
class TaskController(@Autowired val taskService: TaskService){

    @PostMapping
    fun create(@RequestBody taskDto: TaskDto): Task {
        return taskService.save(taskDto)
    }

    @GetMapping
    fun getTasks():List<Task>?{
        return taskService.all()
    }

    @GetMapping("/{id}")
    fun findTaskById(@PathVariable id:String): Task?{
        return taskService.findTaskById(id)?:throw Exception()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody taskDto: TaskDto): Task?{
        return taskService.update(id,taskDto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) {
        taskService.delete(id)
    }

    @GetMapping("/user/{id}")
        fun findTaskByUserId(@PathVariable userId: String) {
        taskService.findTaskByUserId(userId)
    }
}