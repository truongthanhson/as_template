package com.github.truongthanhson.astemplate.services

import com.github.truongthanhson.astemplate.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
