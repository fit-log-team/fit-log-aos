plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "com.example.data.CustomRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //_ hilt
    implementation(libs.hilt) //_ hilt
    kapt(libs.hilt.compiler) //_ kapt - hilt


    //_ retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.gson)

    //_ okhttp3
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)

    //_ timber
    implementation(libs.timber)

    //_ -------------test------------------------

    //_ hilt
    implementation(libs.hilt.test)
    kapt(libs.hilt.compiler)
    testImplementation(libs.hilt)
    kaptAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.test)

    //_ coroutine
    androidTestImplementation(libs.coroutine.test)
    androidTestImplementation(libs.test.runner)

    implementation(libs.retrofit.converter.serialization)
    implementation(libs.kotlinx.serialization.json)



}