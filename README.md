🪵 Kashta-Kala — Digital Design Catalog for Carpenters
📖 Table of Contents

Problem Statement
The Vision
Features
Tech Stack
Project Structure
Estimation Formula
Setup & Installation
Running the App
Screenshots
Impact Goals
VTU Success Criteria
Contributing
License


🔴 Problem Statement
Small-town carpenters and furniture makers are highly skilled but struggle to present modern furniture designs to their customers. They typically rely on:

Outdated printed catalogs
Verbal descriptions and hand sketches
The customer's imagination

This leads to miscommunication, dissatisfaction, and lost business. On top of that, manually calculating material requirements and costs is slow and error-prone.

💡 The Vision
Kashta-Kala is a "Digital Design Catalog for Carpenters" — a professional sales tool built for the local artisan.
The app comes pre-loaded with modern furniture designs and enables carpenters to:

Show high-quality design photos to customers on the spot
Instantly estimate the wood required and calculate a rough cost
Save customer quotes for future reference
Showcase their own finished work through a personal portfolio


Professionalizing the local carpentry business — one quote at a time.


✨ Features
ScreenDescription🏠 DashboardLive business stats — total designs, favourites, quotes given, revenue quoted🛋️ Design CatalogGrid of high-quality furniture photos (Sofa, Bed, Cabinet, Table) with category filters and ❤️ favourite toggle📐 Material EstimatorInput dimensions → app calculates square feet of wood needed, wood cost, labour cost, and total estimate📋 Price QuotesSave customer quotes to Room DB; view breakdown of wood + labour costs; delete old quotes🖼️ My PortfolioCarpenter adds photos of their own finished work to build credibility with customers

🛠 Tech Stack
LayerTechnologyLanguageJavaUIXML Layouts, ConstraintLayout, CardViewNavigationBottom Navigation View + Fragment ManagerImage LoadingGlide 4.16Local DatabaseRoom DB (SQLite abstraction)ArchitectureMVVM — ViewModel + LiveData + RepositoryListsRecyclerView with ListAdapter + DiffUtilBuild SystemGradle 8.2Min SDKAPI 24 (Android 7.0)Target SDKAPI 34 (Android 14)

📁 Project Structure
KashtaKala/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/kashta/kala/
│       │   ├── MainActivity.java             ← Bottom nav host
│       │   ├── KashtaViewModel.java          ← LiveData ViewModel
│       │   ├── KashtaRepository.java         ← Single source of truth
│       │   ├── AddQuoteActivity.java         ← New quote form
│       │   ├── AddPortfolioActivity.java     ← Add portfolio item
│       │   │
│       │   ├── database/
│       │   │   ├── KashtaDatabase.java       ← Room DB singleton + seeder
│       │   │   ├── entities/
│       │   │   │   ├── Design.java           ← Furniture design entity
│       │   │   │   ├── PriceQuote.java       ← Customer quote entity
│       │   │   │   └── PortfolioItem.java    ← Portfolio entity
│       │   │   └── dao/
│       │   │       ├── DesignDao.java        ← CRUD + filter queries
│       │   │       ├── PriceQuoteDao.java    ← Insert, delete, aggregate
│       │   │       └── PortfolioDao.java     ← Insert, delete
│       │   │
│       │   ├── fragments/
│       │   │   ├── DashboardFragment.java
│       │   │   ├── CatalogFragment.java      ← RecyclerView Grid + chips
│       │   │   ├── EstimatorFragment.java    ← Core math logic
│       │   │   ├── QuotesFragment.java
│       │   │   └── PortfolioFragment.java
│       │   │
│       │   ├── adapters/
│       │   │   ├── DesignAdapter.java        ← Glide image loading
│       │   │   ├── QuoteAdapter.java
│       │   │   └── PortfolioAdapter.java
│       │   │
│       │   └── utils/
│       │       └── WoodHelper.java           ← All estimation formulas
│       │
│       └── res/
│           ├── layout/                       ← 10 XML layout files
│           ├── values/                       ← colors.xml, strings.xml, themes.xml
│           ├── drawable/                     ← Custom shape drawables
│           ├── color/                        ← Nav color selector
│           └── menu/
│               └── bottom_nav_menu.xml
│
├── build.gradle
├── settings.gradle
└── gradle.properties

🪵 Estimation Formula
All material calculations live in WoodHelper.java:
javaSquare Feet  = Length (ft) × Width (ft)
Cubic Feet   = Length × Width × Height   // only if height is provided
Wood Cost    = Square Feet × Wood Price per sqft
Labour Cost  = Square Feet × Labour Rate  // default ₹200/sqft
Total Cost   = Wood Cost + Labour Cost
Wood Type Pricing Table
Wood Type₹ / sqftDensityDurabilityTeak₹850HighExcellentSheesham₹600Medium-HighVery GoodSal₹500HighVery GoodMango₹400MediumGoodPine₹300Low-MediumFairBamboo₹250MediumGood

⚙️ Setup & Installation
Prerequisites

Android Studio Hedgehog (2023.1.1) or newer
JDK 17 (bundled with Android Studio)
Internet connection for Gradle sync and Glide image loading

Clone the Repository
bashgit clone https://github.com/shrethan/kashta-kala-self-employment-.git
cd kashta-kala-self-employment-
Open in Android Studio

Launch Android Studio
Click File → Open
Select the cloned kashta-kala-self-employment- folder (the one containing settings.gradle)
Click OK and wait for Gradle sync to complete (~2–5 minutes on first run)


Sync failed? Go to File → Invalidate Caches → Invalidate and Restart, then File → Sync Project with Gradle Files


▶️ Running the App
On an Emulator

Go to Tools → Device Manager
Click Create Device → Choose Pixel 6 → API 33 (Android 13)
Download the system image if prompted, then click Finish
Press Shift + F10 or click the ▶ Run button

On a Physical Android Device

Enable Developer Options: Settings → About Phone → tap Build Number 7 times
Enable USB Debugging: Settings → Developer Options → USB Debugging
Connect your phone via USB
Select your device from the Android Studio device dropdown
Press Shift + F10

First Launch Behaviour

Room DB auto-creates and seeds 8 furniture designs on first install
Images load via Glide over the internet (WiFi recommended)
Estimator, Quotes, and Portfolio work fully offline


📸 Screenshots

Add your screenshots here after running the app

DashboardDesign CatalogEstimatorPrice QuotesPortfolio(Add screenshot)(Add screenshot)(Add screenshot)(Add screenshot)(Add screenshot)

🎯 Impact Goals
GoalDescription💼 Micro-Business TechGives local artisans tools to compete with large furniture brands♻️ Resource EfficiencyAccurate estimation reduces wood wastage🤝 Customer TrustProfessional quotes and design options build better business relationships

✅ VTU Success Criteria Checklist

 Material Estimator is accurate and handles multiple wood types
 Design gallery supports Favouriting designs
 UI is visual-heavy with clean, large images via RecyclerView + Glide
 Price Quotes are saved in Room DB for future reference
 Portfolio section allows carpenter to add and remove finished work photos
 MVVM architecture with LiveData for reactive UI updates
 Math logic correctly computes square feet and cubic feet


🔧 Troubleshooting
ProblemSolutionGradle sync failsFile → Invalidate Caches → RestartminSdk version errorEnsure your emulator/device is API 24+Images not loadingCheck internet connection — Glide requires the INTERNET permission (already declared in AndroidManifest.xml)cannot find symbol build errorBuild → Clean Project → Rebuild ProjectRoom DB migration errorUninstall the app from the emulator/device and reinstallViewBinding not resolvingConfirm buildFeatures { viewBinding true } is in app/build.gradle

📦 Building a Release APK

Go to Build → Generate Signed Bundle / APK
Select APK → Next
Create a new keystore (note your alias and password)
Select release build variant → Finish
APK location: app/release/app-release.apk


🤝 Contributing

Fork the repository
Create your feature branch: git checkout -b feature/your-feature-name
Commit your changes: git commit -m "Add: your feature description"
Push to the branch: git push origin feature/your-feature-name
Open a Pull Request


👨‍💻 Author
Shrethan
GitHub: @shrethan
