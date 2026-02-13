# Recursive Grid

An interactive Android application featuring a 3x3 grid with dynamic, cascading numerical rules. Built with **Kotlin** and **Jetpack Compose**.

## 🎮 Game Rules & Mechanics

The grid consists of 9 cells (3x3 layout), all starting at **0**.

### 1. Interaction
- **Tap a Cell**: The cell's value increments by **1**.

### 2. Cascading Logic
Updates can trigger changes in neighboring cells:
- **Divisible by 3 Rule**: If a cell's new value is divisible by **3**, its **Right Neighbor** decreases by **1**.
- **Divisible by 5 Rule**: If a cell's new value is divisible by **5**, its **Bottom Neighbor** increases by **2**.

### 3. Locking State 🔒
- **Threshold**: Any cell that reaches a value of **15** or higher becomes **LOCKED**.
- **Effect**: Locked cells turn **Red** and stop accepting updates (clicks or neighbor cascades).

### 4. Reset
- Use the **Reset** button at the bottom of the screen to unlock all cells and return their values to **0**.

## 🎨 Visual Guide

The interface updates dynamically based on the state of each cell:

| State | Background Color | Text Color |
| :--- | :--- | :--- |
| **Even Number** | Light Grey (`#E0E0E0`) | Black |
| **Odd Number** | Dark Blue (`#1A237E`) | White |
| **Locked (≥15)** | Red (`Color.Red`) | White |

*Note: All cells feature a distinct shadow effect for depth.*

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture**: Logic encapsulated in `GridInteractor` with reactive state management (`StateList`).

## 🚀 Setup & Installation

### Prerequisites
- **Android Studio** (Latest Stable Version recommended)
- **JDK 17** or higher (usually bundled with Android Studio)

### Steps
1.  **Clone the Repository**
    ```bash
    git clone <repository-url>
    ```
2.  **Open in Android Studio**
    - Launch Android Studio.
    - Select **Open** or **Import Project**.
    - Navigate to the cloned folder and select it.
3.  **Sync Gradle**
    - Android Studio will automatically start syncing Gradle.
    - Wait for the process to complete (this may take a few minutes for the first time).
4.  **Run the App**
    - Connect an Android device via USB (ensure USB Debugging is on) or create an Android Virtual Device (AVD).
    - Click the green **Run** (▶) button in the toolbar.

---
*Developed as part of the Recursive Grid project.*
