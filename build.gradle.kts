plugins {
    id("com.android.application") version "8.12.1" apply false
    kotlin("android") version "2.0.20" apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}