package com.github.remigiuszdudek.statemigrator.plugin

import com.github.remigiuszdudek.statemigrator.plugin.tasks.GenerateStateSchemaTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class StateMigratorPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register('generateStateSchema', GenerateStateSchemaTask) {
            group = 'other'
            dependsOn('compileJava', 'compileGroovy')
        }
    }
}
