buildscript {
  repositories {
    google()
    jcenter()
  }

  dependencies {
    //classpath("com.android.tools.build:gradle:3.6.0-alpha10")
    classpath(Dependencies.Android.Gradle)

    classpath("de.mannodermaus.gradle.plugins:android-junit5:1.5.1.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
  }
}

allprojects {
  apply(from = "${rootProject.projectDir}/repositories.gradle.kts")

  //apply(from = "${rootProject.projectDir}/gradle/git-release-notes.gradle")
  //apply(from = "${rootProject.projectDir}/gradle/gradle-mvn-push.gradle")
}
