plugins {
    id("com.android.application") version "8.0.2" apply false
    kotlin("android") version "1.8.21" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}