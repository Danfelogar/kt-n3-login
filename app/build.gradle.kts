plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    //   custom plugins
    // super core for kotlin serialization
    alias(libs.plugins.ksp)

    alias(libs.plugins.jetbrains.kotlin.serialization)
    //hilt
    alias(libs.plugins.hilt)
    //protobuf for datastore proto
    alias(libs.plugins.protobuf)

}

android {
    namespace = "app.vercel.danfelogarporfolios.kt_test_post_in_login_screen"
    compileSdk = 36

    defaultConfig {
        applicationId = "app.vercel.danfelogarporfolios.kt_test_post_in_login_screen"
        minSdk = 24
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Custom dependencies
    //navigation-3
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)
    //dagger-hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.navigation)
    // coil(async img)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    //datastore preferences
    implementation(libs.data.store.preferences)
    //datastore proto
    implementation(libs.data.store.proto)
    implementation(libs.datastore.core)
    implementation(libs.protobuf.compiler)
    implementation(libs.protobuf.java)
    implementation(libs.protobuf.kotlin)
    //extended icons
    implementation(libs.material.icons.extended)
    //    retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.converter.gson)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${libs.versions.protobuf.get()}"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
