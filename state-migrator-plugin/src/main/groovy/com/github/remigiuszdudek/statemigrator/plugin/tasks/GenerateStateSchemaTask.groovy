package com.github.remigiuszdudek.statemigrator.plugin.tasks

import com.github.remigiuszdudek.statemigrator.api.PersistentState
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.scanners.TypeAnnotationsScanner
import org.reflections.util.ConfigurationBuilder

class GenerateStateSchemaTask extends DefaultTask {
    @TaskAction
    void generateStateSchema() {
        Collection<URL> projectClasspath = project.sourceSets.main.runtimeClasspath.collect { File it ->
            it.path.contains('main') && it.exists() ? it.toURI().toURL() : null
        } - null
        def reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(projectClasspath)
                        .addClassLoader(new URLClassLoader((URL[]) projectClasspath.toArray()))
                        .setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner()))

        Set<Class<?>> stateClazzes = reflections.getTypesAnnotatedWith(PersistentState)
        println "state classes: ${stateClazzes}"
    }
}
