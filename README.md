# Cafeteria Management System - Documentation
## Overview
The Cafeteria Management System is a JavaFX-based application designed to streamline the cafeteria ordering process. The application is built using IntelliJ IDEA Community Edition and uses MySQL for data storage. The system supports three types of users: Employee, Admin, and Customer, each with their own set of functionalities.

## Features
### 1. Employee Features:
- Registration and Login: Employees can register and log in to access the system.
- View Orders: Once logged in, the employee can see the list of customer orders.
- Serve Orders: Employees can serve customer orders. After serving, the order will be removed from the table.
- Search Orders: Employees can search for orders dynamically using the search field to locate specific orders.
### 2. Customer Features:
- Registration and Login: Customers can register and log in to the system.
- Place Orders: Customers can place orders by selecting a food item from the menu and specifying the quantity.
- View Order List: Once an order is placed, it will appear in the customer's order list.
- Payment and Change Calculation: Customers can specify the amount they are paying. If the payment exceeds the order total, the system will calculate the change.
- Remove Items from Order List: Customers can remove items from the order list.
- Prevent Duplicate Orders: The system ensures that customers cannot order the same food item more than once.
- Reset Order: The reset button clears the total, amount, and change fields, resetting the order details.
### 3. Admin Features:
- Manage Food Items: Admins can add, delete, modify, or update food items in the menu.
- Manage Customers and Employees: Admins can update customer and employee information (fields auto-fill upon selection) and remove them from the system.
- Admin Credentials:
  - Username: abasse
  - Password: 123

## Setup and Configuration
### 1. Database Connection:
- Download the MySQL Connector and place it in the lib folder of the project.
- The application will use this connector to establish a connection with the MySQL database.

### 2. Running the Application:
- Open the project in IntelliJ IDEA Community Edition.
- Ensure that all dependencies are correctly added.
- Build and run the project to start the application.

### 3. Database Configuration:
- Set up the MySQL database to store user information, food items, and orders.
- The admin can manage the database through the admin interface.

### 4. Login Credentials:
- Employees can log in using their credentials.
- Admin uses the credentials mentioned above to manage the system.

## Project Structure
- Employee Interface: Where employees can log in, view orders, and serve them.
- Customer Interface: Where customers can place orders, view the order list, and process payments.
- Admin Interface: Admin manages users (customers and employees) and food items.

## Technologies Used
- JavaFX: The GUI framework for building the application's user interface.
- MySQL: Used for storing customer orders, user information, and food data.
- IntelliJ IDEA: The Integrated Development Environment (IDE) used to develop the application.

## How to Use
### 1. For Employees:
- Log in using your credentials.
- View the orders from customers.
- Serve the orders by removing them from the order list.

![alt text](https://github.com/mounbagna/CANTEEN-MANAGEMENT-SYSTEM/blob/master/pics/ehp.png)

### 2. For Customers:
- Register and log in to place orders.
- Choose food items, specify quantities, and place the order.
- View your order list and make payments.
- Remove items from the order list if needed.

![alt text](https://github.com/mounbagna/CANTEEN-MANAGEMENT-SYSTEM/blob/master/pics/chp.png)

### 3. For Admins:
- Log in with the credentials: abasse (admin) and 123 (password).
- Add, update, or remove food items.
- Update customer or employee information.
- Remove customers or employees from the database.

  ![alt text](https://github.com/mounbagna/CANTEEN-MANAGEMENT-SYSTEM/blob/master/pics/ahp.png)
  
### Connect
- [LinkedIn](https://www.linkedin.com/in/mounbagna-abdella-abasse-875958314/) – Connect with me on LinkedIn!


## HOW THE APP LOOKS LIKE

![alt text](https://github.com/mounbagna/CANTEEN-MANAGEMENT-SYSTEM/blob/master/pics/hp.png)
