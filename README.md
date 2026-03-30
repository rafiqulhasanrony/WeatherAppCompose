# 🌤️ WeatherApp Compose

> A playground for testing architecture patterns, validating ideas, and making 2AM questionable decisions to understand why they were questionable.

---

## About

An ongoing Android application that fetches real-time weather data based on the user's current GPS location. The project is intentionally over-engineered — it serves as a hands-on sandbox to validate architectural decisions, explore Jetpack Compose patterns, and experiment with modularization strategies at scale.

---

## App Behavior

### Location & GPS Flow

| State | Behavior |
|---|---|
| Permission not granted | Shows a UI state with a title and **"Grant Permission"** button that navigates to device app settings |
| GPS disabled | Shows a GPS unavailable state with a **"Pick a City"** button that opens an offline city list |
| Location available | Fetches weather using current lat/lon coordinates |

> **Refresh always uses the current device location**, regardless of any previously selected city.

### Theme Support

Users can choose from four theme combinations:

- 🎨 Dynamic Light
- 🎨 Dynamic Dark
- ⚙️ Android System Light
- ⚙️ Android System Dark

Default follows the system theme.

### Measurement Units

- **Metric** (default)
- **Imperial**

Selectable from the Settings screen.

### Navigation

Settings and City Search are accessible via **toolbar action items**.

---

## Architecture

**MVVM + Clean Architecture + Multi-Module**
## Module Structure

```
WeatherAppCompose/
├── app/ # Application entry point, DI graph root
│
├── build-logic/ # Convention plugins + Kotlin DSL build management
│
├── core/
│ ├── common/ # Shared utilities, extensions, base classes
│ ├── datastore-preference/ # DataStore wrapper for preferences (theme, units)
│ ├── designsystem/ # Tokens, typography, theme, shared composables
│ ├── navigation/ # Navigation contract and shared nav utilities
│ ├── network/ # Retrofit setup, interceptors, response wrappers
│ ├── testing/ # Shared test utilities, fakes, coroutine rules
│ └── ui/ # Reusable UI components (loaders, states, etc.)
│
└── feature/
├── weatherinfo/
│ ├── impl/ # ViewModel, screens, repository, data sources
│ └── public-api/ # NavKey + UseCase/repository interfaces if needed (no impl leaked)
│
└── settings/
├── impl/ # ViewModel, settings screen, preference logic
└── public-api/ # NavKey + UseCase/repository interfaces if needed
```

Each feature is split into two modules:
- **`impl`** — Contains the full implementation (ViewModel, UI, repository)
- **`api`** — Exposes only navigation routes and use case interfaces consumed by other modules

This enforces strict separation of concerns and prevents accidental cross-feature coupling.

---

## Key Design Decisions

### Navigation (Nav 3 + DI)
Navigation entries are provided via Hilt as `EntryProviderInstaller`, eliminating manual copy-pasting of nav graph registrations across modules.

### ViewModel as UI/Data Boundary
ViewModels survive configuration changes and expose UI state as `StateFlow`. UI layer only consumes, never writes directly to data layer.

### Mapper Pattern
Dedicated mapper classes handle all data model → UI model conversions, keeping UI models clean and independent of API/database schemas.

### Build Logic
Convention plugins via Kotlin DSL replace repetitive Gradle boilerplate across modules. Benefits:
- Better readability
- IDE code navigation and auto-suggestions
- Single source of truth for dependency versions

### SOLID Principles
Code is written with SOLID in mind. Use case interfaces in `pblic-api` modules follow the Interface Segregation and Dependency Inversion principles by design.

### Testing
- **Unit tests** — JUnit5, AssertJ/Truth, MockK/MockitoKotlin, custom infix functions for readability
- **UI tests** — Espresso, with documented test scenarios explaining the intent of each test case

---

## Tech Stack

| Category | Libraries |
|---|---|
| UI | Jetpack Compose, Material 3 |
| Architecture | ViewModel, MVVM, Clean Architecture |
| Navigation | Navigation 3 |
| DI | Hilt |
| Async | Coroutines, Flow |
| Networking | Retrofit, Moshi / Gson |
| Image Loading | Coil |
| Build | Build Logic, Convention Plugins, Kotlin DSL |
| Unit Testing | JUnit5, AssertJ / Truth, MockitoKotlin, Kotlinx Coroutines Test |
| UI Testing | Espresso |

Full dependency list: [`libs.versions.toml`](gradle/libs.versions.toml)

---

## Status

🚧 **Work in progress** — features, tests, and documentation are actively being added.

- [ ] Enable theme change
- [ ] Location permission flow
- [ ] GPS fallback to city picker
- [ ] Weather info feature module
- [x] Settings (theme + units)
- [x] Build logic + convention plugins
- [x] Navigation setup
- [ ] Unit test coverage
- [ ] UI test coverage
- [ ] Full KDoc documentation
