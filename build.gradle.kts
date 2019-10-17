import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:3.6.0-beta01")
    classpath("de.mannodermaus.gradle.plugins:android-junit5:1.5.2.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
  }
}

allprojects {
  apply(from = "${rootProject.projectDir}/repositories.gradle.kts")
}

subprojects {
  apply(from = "${rootProject.projectDir}/gradle/git-release-notes.gradle")
  apply(from = "${rootProject.projectDir}/gradle/gradle-mvn-push.gradle")

  tasks
      .withType<Javadoc>()
      .all { enabled = false }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion
          .VERSION_1_8
          .toString()
    }
  }
}
