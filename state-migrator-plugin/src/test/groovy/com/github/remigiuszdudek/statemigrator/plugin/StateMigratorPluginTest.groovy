package com.github.remigiuszdudek.statemigrator.plugin

import org.apache.commons.io.FileUtils
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class StateMigratorPluginTest extends Specification {
    private static final TEST_PROJECT_ORIGINAL_DIR = new File('src/test/groovy/com/github/remigiuszdudek/statemigrator/plugin/testproject')
    private static final API_PROJECT_DIR = new File('../state-migrator-api/src/main/groovy')
    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder(new File('build')) {
        @Override
        protected void after() {
        }
    }

    def setup() {
        testProjectDir.newFile('build.gradle') << '''\
            plugins {
              id 'groovy'
              id 'com.remigiuszdudek.statemigrator'
            }
            repositories {
              mavenCentral()
            }
            dependencies {
              implementation 'org.codehaus.groovy:groovy-all:3.0.7'
            }
        '''.stripIndent()

        testProjectDir.newFile('settings.gradle') << "rootProject.name = 'state-test'"
        File srcMainGroovy = testProjectDir.newFolder('src/main/groovy')
        File projectMainPackage = testProjectDir.newFolder('src/main/groovy/com/github/remigiuszdudek/statemigrator/plugin/testproject')
        FileUtils.copyDirectory(TEST_PROJECT_ORIGINAL_DIR, projectMainPackage)
        FileUtils.copyDirectory(API_PROJECT_DIR, srcMainGroovy)
    }

    def 'plugin successfully applied'() {
        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('generateStateSchema','--stacktrace')
                .withPluginClasspath()
                .forwardOutput()
                .withDebug(true)
                .build()

        then:
        result.output.contains('SimpleState')
    }
}
