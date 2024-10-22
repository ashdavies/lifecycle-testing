plugins {
  id("com.android.library")
  id("de.mannodermaus.android-junit5")

  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(29)

  defaultConfig {
    compileOptions {
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
    }

    minSdkVersion(14)
    targetSdkVersion(29)
  }

  sourceSets {
    mainSrcDirs("src/main/kotlin")
    testSrcDirs("src/test/kotlin")
  }
}

dependencies {
  implementation(project(":testing"))

  implementation("androidx.lifecycle:lifecycle-livedata-core:2.1.0")
  implementation("com.google.truth:truth:1.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")

  api("com.google.truth:truth:1.0")

  testImplementation(project(":jupiter"))

  testImplementation("com.google.truth:truth:1.0")
  testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}
