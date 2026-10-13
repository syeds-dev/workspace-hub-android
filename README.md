# WorkspaceHub 🏢

[![Android CI](https://github.com/syeds-dev/workspace-hub-android/actions/workflows/android-ci.yml/badge.svg)](https://github.com/syeds-dev/workspace-hub-android/actions/workflows/android-ci.yml)
![Kotlin](https://img.shields.io/badge/Kotlin-1.7-7F52FF?logo=kotlin&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-19-3DDC84?logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%2B%20Clean-blue)

An Android workplace directory app: browse your **colleagues** and check **meeting room availability** from one place.

> 🆕 **Jetpack Compose version:** [workspace-hub-compose](https://github.com/syeds-dev/workspace-hub-compose) migrates this app's UI to Compose while keeping the same ViewModels, repositories and models.

<p align="center">
  <img src="screenshots/home.png" width="200" alt="Home screen"/>
  &nbsp;
  <img src="screenshots/contacts.png" width="200" alt="Contacts list"/>
  &nbsp;
  <img src="screenshots/contact_details.png" width="200" alt="Contact details"/>
  &nbsp;
  <img src="screenshots/rooms.png" width="200" alt="Rooms list"/>
</p>

## ✨ Features

**Contacts**
- Scrollable list of colleagues with profile photo and name
- Placeholder avatar when a contact has no photo
- Details screen with photo, name, email, job title and favourite colour

**Rooms**
- List of meeting rooms with maximum occupancy
- Availability status: **Available** or **Occupied** (occupied rooms highlighted in red)

**Navigation**
- Action bar shows the title of each screen, with a working back (Up) arrow on every screen except Home

**Accessibility**
- Content descriptions for buttons, lists, images and screens to support TalkBack

## 🛠 Tech stack

| Area | Libraries |
|---|---|
| Language | Kotlin |
| UI | XML layouts, ViewBinding, RecyclerView, Material Components, ConstraintLayout |
| Navigation | Jetpack Navigation Component |
| Architecture | MVVM + Clean Architecture, Repository pattern |
| Dependency injection | Hilt |
| Async | Kotlin Coroutines, StateFlow |
| Networking | Retrofit, Gson, OkHttp + logging interceptor |
| Image loading | Glide |
| Logging | Timber |
| Testing | JUnit, Coroutines Test, Espresso |

## 🏗 Architecture

```
UI (Fragments + ViewBinding)
        │  collects StateFlow
        ▼
ViewModel (Hilt-injected)
        │  calls
        ▼
Repository  ──►  Retrofit API
```

```
app/src/main/java/com/syedsubahani/workspacehub/
 ├── common/       # Shared utilities and helpers
 ├── data/         # API services, response models, repository implementations
 ├── di/           # Hilt modules
 ├── repository/   # Repository implementations
 └── ui/           # Home, Contacts, Contact details and Rooms screens
```

## 🧪 Testing

- **Unit tests** cover ViewModels and repositories using JUnit with fake repositories
- **Instrumentation tests** cover UI flows using Espresso

```bash
./gradlew testDebugUnitTest          # unit tests
./gradlew connectedDebugAndroidTest  # UI tests (emulator or device required)
```

Unit tests also run automatically on every push through GitHub Actions.

## 🚀 Getting started

1. Clone the repo
   ```bash
   git clone https://github.com/syeds-dev/workspace-hub-android.git
   ```
2. Open in Android Studio and let Gradle sync
3. Run on an emulator or device (Android 4.4+, minSdk 19)

## 🗺 Roadmap

- [x] Rebuild the UI with Jetpack Compose and Material 3 → [workspace-hub-compose](https://github.com/syeds-dev/workspace-hub-compose)
- [ ] Search and filter contacts
- [ ] Filter rooms by availability
- [ ] Dark theme

## 👤 Author

**Syed Subahani**, Senior Android Engineer
[LinkedIn](https://linkedin.com/in/subahani-syed-240bb0112) · [Portfolio](https://syeds.pages.dev) · syeds.androiddev@gmail.com
