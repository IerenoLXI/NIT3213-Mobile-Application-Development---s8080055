# NIT3213 Android Application

## Project Overview

A **generic and dynamic Android application** developed for the NIT3213 Final Assignment. This app demonstrates proficiency in API integration, user interface design, and Android development best practices. The application is designed to be completely dynamic, retrieving all data from the API without hardcoded content.

## 🎯 Objective

Develop an Android application that demonstrates:
- API integration and network communication
- Modern user interface design
- Android development best practices
- Dynamic content handling from API responses

## 📱 Application Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture with:
- **ViewBinding** for view references
- **ViewModel** for business logic
- **LiveData** for reactive UI updates
- **Hilt** for dependency injection
- **Retrofit** for API communication
- **Kotlin Coroutines** for asynchronous operations

## 🚀 Features

### Three Main Screens:

1. **Login Screen**
   - User authentication with username and password fields
   - Dynamic error handling and user feedback
   - Clean, modern UI design

2. **Dashboard Screen**
   - RecyclerView displaying entities from API
   - Dynamic content based on API response
   - Click functionality for detailed view navigation
   - Pull-to-refresh functionality

3. **Details Screen**
   - Complete entity information display
   - Dynamic property rendering
   - Clean, card-based layout

## 🌐 API Integration

**Base URL:** `https://nit3213api.onrender.com/`

### Authentication Endpoint
```
POST /footscray/auth
POST /sydney/auth  
POST /ort/auth
```

**Request Body:**
```json
{
  "username": "YourFirstName",
  "password": "sYourStudentID"
}
```

**Success Response:**
```json
{
  "keypass": "topicName"
}
```

### Dashboard Endpoint
```
GET /dashboard/{keypass}
```

**Success Response:**
```json
{
  "entities": [
    {
      "property1": "value1",
      "property2": "value2", 
      "description": "Detailed description"
    }
  ],
  "entityTotal": 7
}
```

## 🏗️ Technical Implementation

### Dynamic Data Handling
- **Generic Entity Model**: Adapts to any JSON structure using key-value pairs
- **Dynamic UI Rendering**: Properties displayed based on API response structure
- **Flexible API Integration**: Supports different authentication endpoints based on location

### Key Components

#### Entity Model
```kotlin
data class Entity(private val data: Map<String, Any>)
```
- Stores data as key-value pairs for maximum flexibility
- Adapts to any JSON structure from the API
- Provides utility methods for data access

#### Authentication Flow
1. **Login Screen**: Enter username/password → Authenticate → Get keypass
2. **Dashboard Screen**: Use keypass → Fetch entities → Display in RecyclerView
3. **Detail Screen**: Show selected entity with all properties

#### Network Layer
- **Retrofit** for HTTP communication
- **Gson** for JSON parsing
- **OkHttp** for logging and interceptors
- **Coroutines** for asynchronous operations

## 📋 Requirements Implementation

### ✅ Login Screen
- [x] Username and password input fields
- [x] Student name as username format
- [x] Student ID (s12345678) as password format
- [x] POST request to appropriate auth endpoint
- [x] Error handling and user feedback
- [x] Navigation to Dashboard on success

### ✅ Dashboard Screen
- [x] RecyclerView for entity list display
- [x] Keypass-based API authentication
- [x] Entity summary display (excluding description)
- [x] Click navigation to Details screen
- [x] Loading states and error handling

### ✅ Details Screen
- [x] Complete entity information display
- [x] Including description field
- [x] User-friendly layout design
- [x] Clean information presentation

## 🎨 UI/UX Features

- **Material Design 3** principles
- **Clean and modern interface**
- **Responsive layouts**
- **Loading states and progress indicators**
- **Error handling with user-friendly messages**
- **Consistent color scheme and typography**

## 🛠️ Build and Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 24 or higher
- Kotlin 1.8.0 or later

### Dependencies
```gradle
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx'
implementation 'androidx.lifecycle:lifecycle-livedata-ktx'
implementation 'com.google.dagger:hilt-android'
implementation 'com.squareup.retrofit2:retrofit'
implementation 'com.squareup.retrofit2:converter-gson'
implementation 'androidx.recyclerview:recyclerview'
implementation 'com.google.android.material:material'
```

### Building the Project
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Build and run on device/emulator

## 🔧 Configuration

The app dynamically configures itself based on:
- **API responses** for content structure
- **Authentication endpoint** selection
- **Entity property** mapping
- **UI component** rendering

## 🌟 Key Highlights

- **100% Dynamic**: No hardcoded content, all data from API
- **Generic Design**: Adapts to any API response structure  
- **Modern Architecture**: MVVM with dependency injection
- **Error Resilient**: Comprehensive error handling
- **User Friendly**: Clean UI with proper feedback
- **Scalable**: Easy to extend and modify

## 📝 Development Notes

This application demonstrates:
- Professional Android development practices
- Clean architecture implementation
- Modern UI/UX design principles
- Robust error handling
- Efficient network communication
- Dynamic content management

---

## 👨‍💻 Developer Information

**Developer**: Aakrosh Rai  
**Student ID**: s8080055  
**Course**: NIT3213 - Android Application Development  
**Assignment**: Final Project - Dynamic Android Application 