# Swiftbot Robot Project

## Project Overview

This project is part of a Java group project for controlling and programming the Swiftbot robot. The Swiftbot is an educational robotics platform designed for learning programming concepts and robotics fundamentals.

## Features

- **Robot Control**: Complete control over Swiftbot movement and sensors
- **Sensor Integration**: Read and process data from various sensors
- **Autonomous Navigation**: Implement autonomous movement patterns
- **Interactive Commands**: Real-time command processing and execution

## Requirements

### Software Requirements

- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE**: Eclipse IDE for Java Developers
- **Build System**: Maven or Gradle (if applicable)
- **Testing Framework**: JUnit (for unit testing)

### Hardware Requirements

- **Swiftbot Robot**: Compatible Swiftbot model
- **USB Connection**: For robot communication
- **Power Supply**: Adequate power for robot operation

## Installation

### Prerequisites

1. Install JDK 8 or higher
2. Install Eclipse IDE for Java Developers
3. Ensure Swiftbot drivers are installed

### Setup Instructions

1. Clone or download this project
2. Import the project into Eclipse IDE
3. Configure the build path and dependencies
4. Connect the Swiftbot robot via USB
5. Run the main application

## Project Structure

When you upload the project to the Pi, it should look like this:

```
Swiftbot/
├── SimonGame.java         # Main game source code
├── swiftbot_API/          # Library folder
│   └── SwiftBot-API-6.0.0.jar
└── Readme.md             # This file
```

## Usage

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 11 or higher is installed on your Raspberry Pi.
- **SwiftBot Library**: The `SwiftBot-API-6.0.0.jar` file must be present in the `swiftbot_API` folder.
- **Hardware**: A SwiftBot robot with a Raspberry Pi.

### Running the Application

1.  **Transfer the files** to your Raspberry Pi.
2.  **Open a terminal** on the Pi and navigate to the project folder.
3.  **Compile** the code:
    ```bash
    javac -cp swiftbot_API/SwiftBot-API-6.0.0.jar SimonGame.java
    ```
4.  **Run** the game:
    ```bash
    java -cp ".:swiftbot_API/SwiftBot-API-6.0.0.jar" SimonGame
    ```

## Game Instructions

1.  The SwiftBot will show a sequence of colours using its underlights and button lights.
2.  Repeat the sequence by pressing the corresponding buttons:
    *   **A** = Red
    *   **B** = Green
    *   **X** = Blue
    *   **Y** = Yellow
3.  If you get it right, the sequence gets longer!
4.  If you get it wrong, the game ends and shows your score.

## Configuration

### Robot Settings

Configure robot parameters in the configuration file:

- **Speed Settings**: Default movement speeds
- **Sensor Calibration**: Sensor sensitivity and thresholds
- **Communication Port**: USB port for robot connection
- **Debug Mode**: Enable/disable debug output


### Integration Tests

Test robot connectivity and basic operations:

1. Robot connection test
2. Sensor functionality test
3. Movement accuracy test
4. LED and sound test

## Troubleshooting

1.  **"Could not find or load main class SimonGame"**: Make sure you compiled the code first and are running the `java` command from the correct folder.
2.  **"package swiftbot does not exist"**: Ensure the `swiftbot_API` folder contains the JAR file and you are using the correct `-cp` flag.
3.  **"This program must run on a Raspberry Pi"**: This code requires the SwiftBot hardware and cannot run on a regular computer (Mac/PC).
