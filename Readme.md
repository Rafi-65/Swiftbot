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

```
Swiftbot/
├── src/                    # Source code directory
│   ├── main/              # Main application code
│   │   ├── java/          # Java source files
│   │   └── resources/     # Configuration files
│   └── test/              # Test code
├── docs/                  # Documentation
├── lib/                   # External libraries
└── README.md              # This file
```

## Usage

### Basic Operations

1. **Robot Movement**: Control forward, backward, left, and right movement
2. **Sensor Reading**: Access data from proximity, light, and other sensors
3. **LED Control**: Manage LED indicators and displays
4. **Sound Output**: Generate audio feedback and alerts

### Running the Application

```bash
# Compile the project
javac -cp .:lib/* src/main/java/*.java

# Run the main application
java -cp .:lib/* com.swiftbot.Main
```

## API Documentation

### Core Classes

- **SwiftbotController**: Main controller for robot operations
- **SensorManager**: Handles all sensor interactions
- **MovementController**: Manages robot movement and navigation
- **CommunicationHandler**: Handles USB communication with the robot

### Key Methods

```java
// Movement control
swiftbot.moveForward(speed);
swiftbot.turnLeft(degrees);
swiftbot.stop();

// Sensor reading
int proximity = sensorManager.getProximityReading();
double lightLevel = sensorManager.getLightLevel();

// LED control
ledController.setColor(255, 0, 0); // Red
ledController.blink(1000); // Blink every 1 second
```

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

### Common Issues

1. **Connection Failed**: Check USB cable and port
2. **Sensor Not Responding**: Verify sensor connections and calibration
3. **Movement Issues**: Check motor connections and power supply
4. **Compilation Errors**: Verify JDK version and dependencies

### Debug Mode

Enable debug mode to get detailed logging:

```java
// Enable debug logging
System.setProperty("swiftbot.debug", "true");
```
