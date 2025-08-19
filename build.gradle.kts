plugins {
    id("com.android.application") version "8.12.1" apply false
    id("org.jetbrains.kotlin.android") version "2.2.10" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10" apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}