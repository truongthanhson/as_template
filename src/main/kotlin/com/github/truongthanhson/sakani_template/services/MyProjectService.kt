package com.github.truongthanhson.sakani_template.services

import com.github.truongthanhson.sakani_template.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
