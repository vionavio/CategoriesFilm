@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelable)
    alias(libs.plugins.secretsGradlePlugin)
    kotlin("kapt") version "1.6.10"
}

android {
    namespace = "com.viona.categoriesfilm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.viona.categoriesfilm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.gson)
    implementation(libs.logging)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.kotlinCoroutinesAndroid)
    implementation(libs.lifecycle)
    implementation(libs.lifecycleLiveData)
    implementation(libs.dagger)
    implementation(libs.kotlinCoroutines)
    kapt(libs.daggerCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.archCoreTest)
    testImplementation(libs.kotlinCoroutinesTest)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.testRules)
    androidTestImplementation(libs.testRunner)
}