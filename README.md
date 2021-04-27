# WeatherForecastApp
A mobile app which allows user to retrieve and display the “5 day weather  forecast”, using the OpenWeatherMap 5 day weather forecast API. 

#Follow steps to install and run this App

 Install Android Studio and SDK.

Download code from Github account or you can clone from URL  "https://github.com/snehasagar/WeatherForecastApp.git"

#Using in your projects

# Gradle
Add dependencies (you can also add other modules that you need):

dependencies

{

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0'
    
    implementation "com.squareup.retrofit2:retrofit: $version_retrofit"
    
    implementation "com.squareup.retrofit2:converter-moshi:$version_retrofit"
    
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.0'
   
    implementation 'io.reactivex.rxjava2:rxjava:2.2.5'
   
    implementation 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
    
}

#And make sure that you use the latest Kotlin version and Retrofit version:

buildscript {

    ext.kotlin_version = '1.5.0'
    
    ext.version_retrofit = "2.5.0"
    
}


Platform-specific artifacts will be resolved automatically via Gradle.

Platform-specific dependencies are recommended to be used only for non-multiplatform projects that are compiled only for target platform.

Additional Implemention to be done:

caches the data locally in the database and show to the user .

Cover your code with Unit testing.

More user friendly user interface.

