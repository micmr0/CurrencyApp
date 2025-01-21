plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.micmr0.currencyapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.micmr0.currencyapp"
        minSdk = 27
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)

    implementation (libs.lifecycle)
    implementation (libs.retrofit2)
    implementation (libs.retrofit2Gson)

    implementation (libs.dagger)
    annotationProcessor (libs.daggerCompiler)

    implementation(libs.worker)

    implementation (libs.ormlite)
    implementation (libs.ormliteCore)

    testImplementation(libs.junit)
    testImplementation(libs.arch)
    implementation (libs.mockito)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}