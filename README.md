# Movies App

## Technologies and Libraries Used

- Android Jetpack Compose: Modern UI toolkit for building native Android UI.
- Kotlin Coroutines: For asynchronous and concurrent programming.
- ViewModel: Part of the Android Architecture Components, used to manage UI-related data in a lifecycle-aware manner.
- Retrofit: HTTP client library for making network requests.
- Paging: HTTP client library for making network requests.
- Gson: JSON serialization/deserialization library for parsing API responses.
- Dagger Hilt: Dependency injection framework for Android.
- Coil: Image loading library for displaying movie posters and images.
- Kotlin DSL: Gradle build scripts written in Kotlin.

## Architecture

The app follows the principles of Clean Architecture, The architecture consists of the following layers:

- **Presentation**: Contains the Jetpack Compose UI components, ViewModels, and UI-related logic.
- **Domain**: Contains the business logic and defines the use cases of the application.
- **Data**: Handles data operations, including fetching data from the network.
- **Dependency Injection**: Uses Dagger Hilt for dependency injection, enabling modular and testable code.

