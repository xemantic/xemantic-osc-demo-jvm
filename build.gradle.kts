plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.gradle.versions.plugin)
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(libs.xemantic.osc.udp)
  implementation(libs.kotlin.logging)
  runtimeOnly(libs.log4j.slf4j2)
  runtimeOnly(libs.log4j.core)
  runtimeOnly(libs.jackson.databind)
  runtimeOnly(libs.jackson.json)
}

tasks {

  dependencyUpdates {
    gradleReleaseChannel = "current"
    rejectVersionIf {
      isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
  }

}

private val notStableKeywords = listOf("alpha", "beta", "rc")

fun isNonStable(
  version: String
): Boolean = notStableKeywords.any {
  version.lowercase().contains(it)
}
