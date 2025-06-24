plugins {
    id("com.android.application"); id("org.jetbrains.kotlin.android"); id("com.google.gms.google-services")
}
android {
    namespace="com.example.weatherapp"; compileSdk=34
    defaultConfig {
        applicationId="com.example.weatherapp"; minSdk=24; targetSdk=34
        versionCode=1; versionName="1.0"
        buildConfigField("String","OPENWEATHER_API_KEY","\"${project.findProperty("OPENWEATHER_API_KEY")}\"")
        testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures { compose=true }
    composeOptions { kotlinCompilerExtensionVersion="1.5.10" }
    kotlinOptions { jvmTarget="1.8" }
    buildTypes { release { isMinifyEnabled=false } }
}
dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:1.6.7")
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
}