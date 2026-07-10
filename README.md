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

- ## 📸 Screenshots
<p align="center">
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/e4ba6020-76ee-4678-b297-da0915a1ecd0" />&nbsp;&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/0c54c905-2fcd-49ba-8325-2d98f6ebdcc5" />&nbsp;&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/b01b5844-30b1-4e23-bef1-a4da17acacd3" />&nbsp;&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/2a9bc18f-1200-471c-9fff-7558e1a02fda" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/bee5a479-f311-49c9-850e-d4332c7e902f" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/c22ddbbc-37b7-427b-9fb7-a6d5168e128f" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/9d992e30-8cdd-47f3-9505-91b5ebec835e" />&nbsp;&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/23bab299-7135-4acd-8ec6-c6f5b87bdce5" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/3f553ded-3c0d-421b-b5cc-b1e01ca69263" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/1d23503f-cf63-4b85-8748-3070edbfda61" />&nbsp;&nbsp;&nbsp;
<img width="208" height="560" alt="image" src="https://github.com/user-attachments/assets/aac1d701-9920-47b9-a62c-ade7d6f0321e" />&nbsp;&nbsp;&nbsp;
</p>

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
4. Sync the project with Gradle files.
5. Run the app on an emulator or a physical device.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.







