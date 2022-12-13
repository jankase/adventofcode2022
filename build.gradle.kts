plugins {
    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.kotlin.jvm)
    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.kotlin.lint)
    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.kotlin.lint.idea)
    @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
    alias(libs.plugins.kotlin.serialization)
}

repositories {
    mavenCentral()
}

kotlin {
    sourceSets {
        val main by getting {
            dependencies {
                implementation(kotlin("reflect"))
                implementation(libs.mordant)
                implementation(libs.reflections)
                implementation(libs.kotlin.serialization.json)
            }
        }
        val test by getting {
            dependsOn(main)
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN_GROUP_BY_FILE)
    }
}
