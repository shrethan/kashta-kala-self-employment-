# 🪵 Kashta-Kala — Android App
## MindMatrix VTU Internship — Project Title 62

A native Android app (Java + Room DB + RecyclerView) for carpenters to browse furniture designs,
estimate material costs, save price quotes, and showcase their portfolio.

---

## 📁 Project Structure

```
KashtaKala/
├── app/
│   ├── build.gradle                          ← App dependencies
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/kashta/kala/
│       │   ├── MainActivity.java             ← Bottom nav host
│       │   ├── KashtaViewModel.java          ← ViewModel (LiveData)
│       │   ├── KashtaRepository.java         ← Data layer
│       │   ├── AddQuoteActivity.java
│       │   ├── AddPortfolioActivity.java
│       │   ├── database/
│       │   │   ├── KashtaDatabase.java       ← Room DB singleton
│       │   │   ├── entities/
│       │   │   │   ├── Design.java
│       │   │   │   ├── PriceQuote.java
│       │   │   │   └── PortfolioItem.java
│       │   │   └── dao/
│       │   │       ├── DesignDao.java
│       │   │       ├── PriceQuoteDao.java
│       │   │       └── PortfolioDao.java
│       │   ├── fragments/
│       │   │   ├── DashboardFragment.java
│       │   │   ├── CatalogFragment.java      ← RecyclerView Grid
│       │   │   ├── EstimatorFragment.java    ← Math logic
│       │   │   ├── QuotesFragment.java
│       │   │   └── PortfolioFragment.java
│       │   ├── adapters/
│       │   │   ├── DesignAdapter.java        ← Glide + ListAdapter
│       │   │   ├── QuoteAdapter.java
│       │   │   └── PortfolioAdapter.java
│       │   └── utils/
│       │       └── WoodHelper.java           ← Estimation formulas
│       └── res/
│           ├── layout/                       ← All XML layouts
│           ├── values/                       ← colors, strings, themes
│           ├── drawable/                     ← Custom backgrounds
│           └── menu/bottom_nav_menu.xml
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## 🚀 STEP-BY-STEP: Open & Run in Android Studio

### Step 1 — Prerequisites
- Install **Android Studio Hedgehog (2023.1.1)** or newer
  → https://developer.android.com/studio
- Ensure you have **JDK 17** (bundled with Android Studio)
- Internet connection (for Gradle sync + Glide image loading)

---

### Step 2 — Open the Project
1. Unzip `KashtaKala.zip` to a folder (e.g. `C:\Projects\KashtaKala`)
2. Open **Android Studio**
3. Click **"Open"** (not "New Project")
4. Navigate to and select the **`KashtaKala`** folder (the one with `settings.gradle`)
5. Click **OK**

---

### Step 3 — Gradle Sync
Android Studio will auto-detect the project and start syncing.
- Wait for **"Gradle sync finished"** in the bottom status bar
- If it asks to upgrade AGP (Android Gradle Plugin), click **"Don't remind me"** or upgrade

**If sync fails:**
```
File → Invalidate Caches → Invalidate and Restart
```
Then sync again via: **File → Sync Project with Gradle Files**

---

### Step 4 — Run on Emulator

**Option A: Use existing emulator**
1. Click **Tools → Device Manager**
2. If no emulator exists, click **"Create Device"**
3. Choose **Pixel 6** → Next → Select **API 33 (Android 13)** → Download if needed → Finish
4. Click ▶ (Run) button or press **Shift+F10**
5. Select your emulator → OK

**Option B: Run on your physical Android phone**
1. On your phone: **Settings → About Phone → tap "Build Number" 7 times** (enables Developer Mode)
2. **Settings → Developer Options → USB Debugging → Enable**
3. Connect phone to PC via USB
4. In Android Studio, select your device from the dropdown
5. Click ▶ Run

---

### Step 5 — First Launch
On first launch:
- The Room DB auto-creates with **8 pre-seeded furniture designs**
- Images load from the internet via Glide (needs WiFi/data)
- All other features (Estimator, Quotes, Portfolio) work fully offline

---

## 📱 App Features

| Screen | What It Does |
|--------|-------------|
| 🏠 Dashboard | Stats: total designs, quotes, portfolio count, revenue |
| 🛋️ Catalog | Grid of furniture photos, category filter chips, ❤️ favourite toggle |
| 📐 Estimator | Enter dimensions → auto-calculates sqft, wood cost, labour cost |
| 📋 Quotes | Save customer quotes to Room DB, delete, view summary |
| 🖼️ Portfolio | Add/remove your finished work photos |

---

## 🪵 Estimation Formula (WoodHelper.java)

```
Square Feet  = Length × Width
Cubic Feet   = Length × Width × Height  (if height given)
Wood Cost    = Square Feet × Wood Price per sqft
Labour Cost  = Square Feet × Labour Rate (default ₹200/sqft)
Total Cost   = Wood Cost + Labour Cost
```

| Wood Type | ₹/sqft | Durability |
|-----------|--------|-----------|
| Teak      | 850    | Excellent |
| Sheesham  | 600    | Very Good |
| Sal       | 500    | Very Good |
| Mango     | 400    | Good      |
| Pine      | 300    | Fair      |
| Bamboo    | 250    | Good      |

---

## 🔧 Common Issues & Fixes

| Problem | Fix |
|---------|-----|
| Gradle sync fails | File → Invalidate Caches → Restart |
| `minSdk` error | Ensure emulator is API 24+ |
| Images not loading | Check internet connection; Glide needs INTERNET permission (already in Manifest) |
| Build error "cannot find symbol" | Build → Clean Project → Rebuild Project |
| Room DB error | Uninstall app from emulator and reinstall (DB schema changed) |
| `ViewBinding not found` | Ensure `buildFeatures { viewBinding true }` in app/build.gradle |

---

## 📦 Build APK for Submission

1. **Build → Generate Signed Bundle / APK**
2. Choose **APK**
3. Create a new keystore (fill in details, remember password)
4. Select **release** build variant
5. Click **Finish**
6. APK will be at: `app/release/app-release.apk`

---

## ✅ VTU Success Criteria Checklist

- [x] Material Estimator with accurate formulas (sqft, cubic ft, wood + labour cost)
- [x] Multiple wood types handled (Teak, Sheesham, Mango, Pine, Sal, Bamboo)
- [x] Design gallery with Favouriting support
- [x] Visual-heavy UI with large images (RecyclerView + Glide)
- [x] Price Quotes saved in Room DB
- [x] Portfolio section for carpenter's own photos
