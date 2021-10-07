import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion by extra { "1.0.1" }

val keystoreProps by lazy { loadProps("keystore.properties") }

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        applicationId = "appinventor.ai_pavitragolchha.VR"
        versionCode = 17
        versionName = "9.0"

        resourceConfigurations += setOf("en")
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProps.getProperty("keyAlias")
            keyPassword = keystoreProps.getProperty("keyPassword")
            storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
            storePassword = keystoreProps.getProperty("storePassword")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        compose = true
        // Turn off unnecessary things
        aidl = false
        buildConfig = false
        resValues = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    lint {
        isCheckReleaseBuilds = false
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.19.0")
    implementation("com.google.accompanist:accompanist-insets:0.19.0")
}

fun loadProps(filename: String) = Properties().apply {
    load(rootProject.file(filename).inputStream())
}