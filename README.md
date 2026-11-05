# Castle Clash: Kingdom Siege

An original, offline, landscape Android castle-battle prototype inspired by the broad castle-siege genre. No copyrighted Castle Busters assets are used.

## Gameplay
- Tap **Swordsman (50 gold)**, **Archer (75 gold)**, or **Knight (110 gold)** to deploy troops.
- Earn **8 gold/second**, plus **12 gold per enemy defeated**.
- Units automatically advance, attack opposing units, and damage the enemy castle.
- Destroy the enemy castle before yours falls. Tap after the result to restart.
- Enemy troop waves grow progressively more challenging.

## Open and build
1. Install Android Studio with Android SDK 35 and JDK 17.
2. Open the `CastleClashKingdomSiege` folder as a project.
3. Allow Gradle sync to download the Android Gradle Plugin.
4. Choose an emulator or connected Android phone, then press **Run**.
5. To generate an APK: **Build > Build Bundle(s) / APK(s) > Build APK(s)**.

The repository intentionally does not include a Gradle wrapper JAR. Android Studio can use a locally installed Gradle 8.9, or you can generate a wrapper using `gradle wrapper --gradle-version 8.9`.

## Technical details
- Java, native Android Canvas, no external graphics or runtime dependencies.
- Min Android 6.0 (API 23); target Android 15 (API 35).
- All art is drawn procedurally.

## Next improvements
Animations, sounds, menus, persistent upgrades, levels, abilities, balancing, accessibility, and app-store release preparation.
