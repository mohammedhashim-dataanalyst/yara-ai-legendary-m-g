# Yara AI Legendary (Ultimate Production Edition)

A high-performance Android AI companion built with custom personas, offline memory engines, and multi-API failover capabilities.

## Features
- **3 Dynamic Modes:** 20yo Girlfriend Persona (Makima Style), Data Analyst & Python Tutor, Personal Assistant.
- **Persistent Memory Brain:** Stores user preferences, name, and context across app launches.
- **Multi-API Failover Pool:** Supports 4 API key slots with automatic rate-limit detection and fallback to the offline local core.
- **WhatsApp Dark UI:** Intuitive interface with dynamic emotion color coding and quick mode switching.

## How to Build & Install
1. **GitHub Import:** Extract this zip file, push all contents to a new GitHub repository (`git init`, `git add .`, `git commit`, `git push`).
2. **GitHub Actions Build:** The included GitHub workflow (`.github/workflows/build_apk.yml`) automatically compiles and uploads your Android APK under the repository Actions tab.
3. **Local Build:** Open the root directory in Android Studio (Android Studio Hedgehog / Iguana / Jellyfish) and click **Run** or **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
