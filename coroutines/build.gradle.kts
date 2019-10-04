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
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.50")
  implementation("org.junit.jupiter:junit-jupiter-api:5.5.1")

  testImplementation("com.google.truth:truth:1.0")
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
  testImplementation("org.junit.jupiter:junit-jupiter:5.5.1")
}
