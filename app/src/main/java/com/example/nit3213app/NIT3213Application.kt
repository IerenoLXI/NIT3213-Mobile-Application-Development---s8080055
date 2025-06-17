package com.example.nit3213app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for Hilt dependency injection
 * 
 * @HiltAndroidApp annotation triggers Hilt's code generation including a base class
 * for your application that serves as the application-level dependency container.
 */
@HiltAndroidApp
class NIT3213Application : Application() 