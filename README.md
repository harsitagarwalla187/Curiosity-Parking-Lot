# 🧠 Curiosity Parking Lot

A minimalist, friction-free Android app designed to let readers quickly capture unknown terms, concepts, or questions encountered while reading—without breaking their flow—so they can review and learn them later during free time.

---

## 🎯 The Problem & Philosophy

When reading technical books, documentation, or research papers, encountering unfamiliar terms (e.g., *"LSM-Tree"*, *"Monad"*, *"Idempotency"*) presents a dilemma:
1. **Stop reading to look it up:** Interrupts reading flow and context switching destroys focus.
2. **Skip it:** Risks forgetting the concept entirely.

**Curiosity Parking Lot** solves this with a **ruthlessly simple, local-first "parking lot"**:
- **Capture in under 3 seconds:** Type a concept name into the top bar and press Enter or the Add button.
- **Optional Context & Source:** Expand extra fields to save a quote snippet or book/URL source if desired.
- **Review at your own pace:** Organize items into `PARKED`, `IN_PROGRESS`, or `LEARNED`.
- **100% Offline & Private:** Stored locally on device using Room SQLite. No backend bloat, accounts, or telemetry.

---

## ✨ Features

- ⚡ **Instant Quick-Capture:** Top input bar pinned for ultra-fast text entry.
- 📝 **Context & Source Support:** Expandable fields to store quote snippets (`"A B-Tree keeps data sorted..."`) and source tags (`"Designing Data-Intensive Applications"`).
- 🔄 **3-Stage Learning Workflow:**
  - `PARKED` 📌: Newly captured items awaiting review.
  - `IN_PROGRESS` 📖: Concepts currently being researched or read about.
  - `LEARNED` ✅: Concepts mastered and archived.
- 🗂️ **Filter Chips:** One-tap filtering between `All`, `Parked`, `In Progress`, and `Learned`.
- 🎨 **Modern Material 3 UI:** Built entirely with Jetpack Compose, smooth transitions, and high-contrast typography.
- 📴 **Zero Latency Offline Storage:** Built on Room Database with reactive Kotlin `Flow` queries.

---

## 🏗️ Architecture & Tech Stack

This project follows **Clean Architecture** principles with **MVVM (Model-View-ViewModel)** and **Unidirectional Data Flow (UDF)**.

```
┌────────────────────────────────────────────────────────┐
│                   CuriosityScreen                      │
│                  (Jetpack Compose)                     │
└───────────────────────────┬────────────────────────────┘
                            │ User Actions / Intents
                            ▼
┌────────────────────────────────────────────────────────┐
│                  CuriosityViewModel                    │
│             (Exposes StateFlow<CuriosityUiState>)      │
└───────────────────────────┬────────────────────────────┘
                            │ Dispatchers.IO Coroutines
                            ▼
┌────────────────────────────────────────────────────────┐
│                 CuriosityRepository                    │
│             (CuriosityRepositoryImpl)                  │
└───────────────────────────┬────────────────────────────┘
                            │ Reactive Flow Streams
                            ▼
┌────────────────────────────────────────────────────────┐
│               CuriosityDao & AppDatabase               │
│                    (Room SQLite)                       │
└────────────────────────────────────────────────────────┘
```

### Tech Stack

- **Language:** Kotlin 2.2+
- **UI Framework:** Jetpack Compose (Material 3)
- **Local Storage:** Room Database 2.8.5
- **Annotation Processing:** KSP (Kotlin Symbol Processing)
- **Async & Reactive:** Kotlin Coroutines & `StateFlow` / `Flow`
- **Build System:** Gradle Kotlin DSL with Version Catalog (`gradle/libs.versions.toml`)

---

## 📂 Directory Structure

```
app/src/main/java/com/example/vibepractice/
├── MainActivity.kt                      # App entry point & ViewModel instantiation
├── data/
│   ├── model/
│   │   ├── CuriosityItem.kt             # Room Entity table definition
│   │   └── CuriosityStatus.kt          # Status Enum (PARKED, IN_PROGRESS, LEARNED)
│   ├── local/
│   │   ├── CuriosityDao.kt              # Room DAO with reactive Flow queries
│   │   ├── Converters.kt                # TypeConverters for Enum persistence
│   │   └── AppDatabase.kt               # Room Database singleton builder
│   └── repository/
│       └── CuriosityRepository.kt       # Repository interface & implementation with IO dispatchers
└── ui/
    ├── CuriosityUiState.kt              # Immutable UI state data class
    ├── CuriosityViewModel.kt            # ViewModel managing UI state and user intents
    ├── CuriosityScreen.kt               # Compose UI (Quick Capture, Filter Chips, Item Cards)
    └── theme/
        ├── Color.kt                     # Material 3 color palette
        ├── Theme.kt                     # VIbePracticeTheme setup
        └── Type.kt                      # Material 3 typography
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio:** Ladybug / 2024.2.1 or newer
- **JDK:** Java 17 or Java 21
- **Android SDK:** Target SDK 35/37 (Min SDK 24)

### Building & Running

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/CuriosityParkingLot.git
   cd CuriosityParkingLot
   ```

2. **Open in Android Studio** and let Gradle sync.

3. **Build the Debug APK via Gradle:**
   ```bash
   ./gradlew app:assembleDebug
   ```

4. **Run on Emulator or Physical Device:**
   Select `app` run configuration in Android Studio and press `Shift + F10`.

---

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.
