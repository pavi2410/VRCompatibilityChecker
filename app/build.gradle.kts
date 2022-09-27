import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val keystoreProps by lazy { loadProps("keystore.properties") }

android {
    namespace = "me.pavi2410.vrcc"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
        applicationId = "appinventor.ai_pavitragolchha.VR"
        versionCode = 18
        versionName = "10.0"

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
        kotlinCompilerExtensionVersion = "1.3.1"
    }

    packagingOptions {
        resources {
            excludes += setOf("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    lint {
        checkReleaseBuilds = false
    }
}

val composeVersion = "1.2.1"

dependencies {
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.19.0")
}

fun loadProps(filename: String) = Properties().apply {
    load(rootProject.file(filename).inputStream())
}