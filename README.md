# 🧪 SauceDemo Test Automation Suite

## ⚠️ Disclaimer
- This project is **for educational and demonstration purposes only**.  
- I have made **no modifications** to the original [SauceDemo](https://www.saucedemo.com/) website.  
- I **do not own or claim any rights** to the website, its content, or its assets. All rights belong to [Sauce Labs](https://saucelabs.com/).  

> **Note:** This project showcases an example of automated smoke testing for a web application.  
> It was developed as a side project to demonstrate modern test automation practices using **Selenium WebDriver**, **TestNG**, and **Java**.

---

## 📌 Project Highlights
- Validates **login functionality** with multiple user scenarios  
- Verifies **product sorting** and **price accuracy**  
- Simulates **shopping cart operations** (add/remove items)  
- Built using a **Page Object Model (POM)** design for maintainability  
- Leverages **TestNG assertions** for verification and reporting  

---

## 🛠️ Tech Stack
- **Java**  
- **Selenium WebDriver**  
- **TestNG**  
- **WebDriverManager**  
- **Maven**

---

## 🚀 Running the Tests

1. Clone the repository **bash**:
   git clone https://github.com/yourusername/SeleniumTestExample.git

2. Navigate to the project folder:
   cd saucedemo-tests

3. Build the project and run tests:
  mvn clean test

Make sure you have Java and Maven installed. The tests are configured to run on Chrome via WebDriverManager.

## 📂 Test Structure

**framework** - Contains the logic, behind the test cases
- BaseTest.java – initializes browser and manages test lifecycle
- BrowserOptions.java - hanles Chrome security popup "change your password" and any future options configurations for the browswer's
- ConstantValues.java - has the accesibility ID's that are used across the tests
- InventoryPage.java - test logic for the inventory page
- LoginPage.java - test logic for the login page
- ShoppingCartList - test logic for the shopping cart page

**sauceDemoTests** - Contains all the UITests
- LoginTest.java – handles login page interactions and validations
- CheckoutTest.java – handles product listing and cart functionality
- ProductListTest.java – test cases for product sorting and cart behavior
- LoginTest.java – test cases for login scenarios

👤 Author
Bahtir Emini
Quality Assurance & Automation Engineer
📍 Based in Ottawa, Ontario – working remotely across Canada and Kosovo
🔗 LinkedIn Profile: https://www.linkedin.com/in/bahtir-emini/

##📜 License

**Copyright © 2025 Bahtir Emini**

This project is protected but made available for educational and non-commercial purposes.
You are free to:

Download, view, and run the code for learning or portfolio use.

Reference this repository in educational materials with proper attribution to the author.

**You may not:**

Redistribute, sell, or use the code in commercial projects.

Remove or alter the author attribution.

**All rights reserved.**
For collaboration or licensing inquiries, please contact Bahtir Emini via LinkedIn