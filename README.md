# CineVerse

CineVerse is a modern Android application designed for movie enthusiasts to explore, discover, and manage their favorite films. Built with the latest Android development tools and best practices, it provides a seamless and engaging user experience.

## 🚀 Features

- **Home Feed:** Explore trending, top-rated, and upcoming movies with beautiful carousels and categorized sections.
- **Movie Details:** Get in-depth information about any movie, including overview, ratings, and reviews.
- **Explore:** Search for your favorite movies and discover new ones.
- **Personalized Experience:** Manage your profile and enjoy features tailored to your "vibe."
- **Smooth Navigation:** Intuitive navigation between screens using Compose Navigation.
- **Offline Support:** Powered by Room database for reliable data caching.
- **Pagination:** Smoothly scroll through large lists of movies with Paging 3.

## 🛠 Tech Stack

- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) for building a modern, declarative UI.
- **Networking:** [Ktor](https://ktor.io/) for efficient and flexible HTTP requests.
- **Dependency Injection:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for clean and scalable architecture.
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/) for fast and lightweight image loading.
- **Pagination:** [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for efficient data loading in lists.
- **Animations:** [Lottie](https://github.com/airbnb/lottie-android) for high-quality animations.
- **Data Persistence:** [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) for storing simple preferences.
- **API:** Powered by [The Movie Database (TMDB) API](https://www.themoviedb.org/documentation/api).

## 🏗 Architecture

The project follows a **Clean Architecture** approach combined with **MVVM (Model-View-ViewModel)** pattern to ensure a separation of concerns, testability, and maintainability:

- **Data Layer:** Handles API calls and local database operations.
- **Domain Layer:** Contains business logic and use cases.
- **Presentation Layer:** Manages UI states and user interactions using Jetpack Compose and ViewModels.


### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AliAlashwall/CineVerse
   ```
2. Open the project in Android Studio.
3. Obtain an API key from [TMDB](https://www.themoviedb.org/documentation/api).
4. (Optional) Set up your API key in the project (usually in a `Constants` or `BuildConfig` file).
5. Sync project with Gradle files.
6. Run the app on an emulator or a physical device.

## 📸 Screenshots



