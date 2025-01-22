# Fetch Android Challenge

## Overview
An Android application that retrieves data from a REST API endpoint and displays it in an organized list format. The app demonstrates modern Android development practices including MVVM architecture, data binding, and clean code principles.

## Features
* Fetches data from https://fetch-hiring.s3.amazonaws.com/hiring.json
* Displays items grouped by listId
* Sorts items by listId and name
* Filters out items with blank or null names
* Material Design UI components
* Error handling
* Loading state management

## Technical Specifications
* Language: Kotlin
* Architecture: MVVM (Model-View-ViewModel)

## Libraries Used
* AndroidX Libraries
* Material Design Components
* Retrofit for network calls
* Data Binding for UI
* Kotlin Coroutines for asynchronous operations
* ViewModel and LiveData for lifecycle-aware data handling

## Setup Instructions
1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app

## Key Implementation Details
* Uses data binding with observable fields for UI updates
* Manages UI state (loading, error, content) systematically
* Uses Material Design components for consistent UI

## Architecture Overview
* **ViewModel**: Manages UI data and states
* **Repository**: Single source of truth for data operations
* **Data Binding**: Connects UI elements with data sources
* **LiveData**: Provides lifecycle-aware data observations
* **Coroutines**: Handles asynchronous operations

## Future Improvements
* Add unit tests
* Implement data caching
* Add pull-to-refresh functionality
* Implement filtering functionality
