# JuiceTracker-Android-OfflineFirst
Juice Tracker is a modern, offline-first Android application built to demonstrate best practices in local data persistence with Room, a reactive UI with Jetpack Compose, and a robust MVVM + Clean Architecture. The app allows users to track juices they've consumed, with all data saved locally for a seamless offline experience.

//image
<!--<img src="https://github.com/user-attachments/assets/02529146-0875-40e8-b357-d31ce2bf266c" alt="Home Screen" style="width:25%; height:auto;">-->

## ✨ Features
- Track Juices: View a list of all saved juices.

- Offline First: The app is fully functional without an internet connection, using a local Room database as the single source of truth.

- Add & Edit: Easily add new juices or edit existing ones through a Material 3 bottom sheet.

- Delete: Remove juices from the list with a simple tap.

- Dynamic UI: The UI is built entirely with Jetpack Compose and Material 3, featuring a dynamic color picker and a custom rating bar.

- Modern Navigation: Includes support for Android's predictive back gesture.

## 🛠 Tech Stack & Architecture
This project is built with a 100% Kotlin-first approach and showcases a modern, robust architecture.

### Tech Stack
- UI: Jetpack Compose & Material 3 for a declarative, modern UI.

- Architecture: MVVM + Clean Architecture (UI, Domain, Data layers).

- Asynchronous Programming: Kotlin Coroutines & Flow for managing background operations and reactive data streams.

- Local Persistence: Room for robust, local database storage.

- Dependency Injection: Manual DI container for a clear and foundational dependency setup.

- Testing: JUnit 4 & Turbine for unit testing the DAO and ViewModel layers.

### Architecture: Single Source of Truth
The application follows a strict offline-first architecture where the Room database is the Single Source of Truth (SSOT) for all application data.

- The UI Layer observes a `Flow` of data directly from the Repository (which gets it from Room).

- The Repository is responsible for all data operations. In a future version, it would handle fetching data from a network, saving it to the Room database, and then letting the database notify the UI of the changes. This makes the app resilient to network failures and provides a snappy user experience.

## 🚀 Setup & Running the Project
To run this project, you need Android Studio Giraffe or later.

1. Clone the repository:

```sh 
git clone https://github.com/iamspd/JuiceTracker-Android-OfflineFirst.git
```

2. Open in Android Studio:

- Open Android Studio.

- Select `File -> Open` and navigate to the cloned project directory.

3. Run the app:

- Let Gradle sync the dependencies.

- Select an emulator or a physical device.

- Click the "Run" button.

## ✅ Testing
This project includes a comprehensive suite of local unit tests to ensure the logic is correct and robust.

- DAO Tests (`JuiceDaoTest`): These tests run on an in-memory Room database to verify all CRUD (Create, Read, Update, Delete) operations work as expected.

- ViewModel Tests (`HomeViewModelTest`): These tests use a fake repository to verify the UI logic, state management, and event handling within the HomeViewModel.
