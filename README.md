# SauceDemo Test Automation Suite

This repository contains a collection of automated UI tests for the [SauceDemo](https://www.saucedemo.com/) web application, created using Java, Selenium, and TestNG.

## ⚠️ Disclaimer

- This project is **for testing and educational purposes only**.
- I have made **no modifications** to the original [SauceDemo](https://www.saucedemo.com/) website.
- I **do not own or claim any rights** to the website, its content, or its assets. All rights belong to [Sauce Labs](https://saucelabs.com/).

## 📌 Project Features

- Tests login functionality with various user scenarios
- Validates product list sorting and price display
- Simulates cart operations (add/remove items)
- Utilizes reusable Page Object Model (POM) structure
- Includes simple assertion-driven test logic using TestNG

## 🛠️ Tech Stack

- Java
- Selenium WebDriver
- TestNG
- WebDriverManager
- Maven (or Gradle if applicable)

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/SeleniumTestExample.git

2. Navigate to the project folder:
   cd saucedemo-tests

3. Build the project and run tests:
  mvn clean test

Make sure you have Java and Maven installed. The tests are configured to run on Chrome via WebDriverManager.

📂 Test Structure
-  BaseTest.java – initializes browser and manages test lifecycle
- LoginPage.java – handles login page interactions
- InventoryPage.java – handles product listing and cart functionality
- ProductListTest.java – test cases for product sorting and cart behavior
- LoginTest.java – test cases for login scenarios

📃 License
This repository is open source, but the tested website belongs to Sauce Labs. No modifications have been made to their system, and all tests are external-only.

