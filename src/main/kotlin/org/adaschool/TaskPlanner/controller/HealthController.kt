package org.adaschool.TaskPlanner.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/v1/health")
class HealthController {

    @GetMapping
    fun serverHealth():String{

        return "Server is running Ok"

    }

}