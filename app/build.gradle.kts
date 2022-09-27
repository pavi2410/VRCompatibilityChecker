import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val composeVersion by extra { "1.0.5" }

val keystoreProps by lazy { loadProps("keystore.properties") }

android {
    namespace = "me.pavi2410.vrcc"
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
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
        kotlinCompilerExtensionVersion = composeVersion
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

dependencies {
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.19.0")
    implementation("com.google.accompanist:accompanist-insets:0.19.0")
}

fun loadProps(filename: String) = Properties().apply {
    load(rootProject.file(filename).inputStream())
}