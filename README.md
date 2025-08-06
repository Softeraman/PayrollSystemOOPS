# 🧾 Java Payroll Management System



This Java project demonstrates a simple Payroll System that effectively implements core **Object-Oriented Programming (OOP)** principles.

---

## 🔧 OOP Concepts Implemented

### 1️⃣ Abstraction
- The abstract class `Employee` defines common structure (`name`, `id`, and `calculateSalary()` method) for all employee types.
- Hides implementation details and enforces subclasses to define salary logic.

### 2️⃣ Inheritance
- `FullTimeEmployee` and `PartTimeEmployee` **extend** the abstract class `Employee`.
- Inherits common fields like name and ID.

### 3️⃣ Polymorphism
- Each employee type overrides `calculateSalary()` differently:
  - `FullTimeEmployee`: returns monthly salary.
  - `PartTimeEmployee`: returns hourlyRate × hoursWorked.
- Enables dynamic method execution at runtime.

### 4️⃣ Encapsulation
- All fields (`name`, `id`, etc.) are `private`.
- Accessed using public `getName()` and `getId()` methods.
- Ensures data safety and object-level control.

---


## 📸 Screenshots

### 🔹 Form for ADD Employee section (Add Employee Form):
![Employee List](screenshot_employee_list.png)
### 🔹 Input Validation (Missing Name/ID Alert):
![Validation Alert](screenshot_name_id.png)

### 🔹 Displaying Employee List (After Adding):
![Form Layout](screenshot_gui_layout.png)

---
# Learning 

🧠 Learning from This Project
Through building this Java Payroll System, I strengthened my understanding and practical application of:

✅ Core OOP Principles (Abstraction, Inheritance, Polymorphism, Encapsulation)

🧱 Designing a modular class structure using abstract and concrete classes

👨‍💻 Implementing method overriding for dynamic behavior in subclasses

🔐 Applying data hiding with proper use of private fields and public getters



🛠 Structuring code for real-world-like payroll logic (full-time vs part-time)

🧪 Handling input validation and interactive feedback in GUI ( Mostly Used AI ) 

🎯 This project helped bridge the gap between theoretical OOP concepts and practical implementation, making me more confident in building scalable Java applications.


## 👨‍💻 Author

**Aman Sharma**  

📅 Last Updated: August 06, 2025


