import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
}

val keystoreProps by lazy { loadProps("keystore.properties") }
val admobProps by lazy { loadProps("admob.properties") }

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)

        applicationId = "appinventor.ai_pavitragolchha.VR"
        versionCode = 16
        versionName = "8.1"
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")

            manifestPlaceholders = mapOf("admobAppId" to admobProps.getProperty("appId"))
            resValue("string", "banner_ad_unit_id", admobProps.getProperty("bannerAdUnitId"))
        }
        getByName("debug") {
            applicationIdSuffix = ".test"
            versionNameSuffix = "-test"

            manifestPlaceholders = mapOf("admobAppId" to "ca-app-pub-3940256099942544~3347511713")
            resValue("string", "banner_ad_unit_id", "ca-app-pub-3940256099942544/6300978111")
        }
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.2.0-alpha03")
    implementation("com.google.android.gms:play-services-ads-lite:19.1.0")
}

fun loadProps(filename: String) = Properties().apply {
    load(rootProject.file(filename).inputStream())
}