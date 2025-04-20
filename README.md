# ⚽ Football Tournament Desktop App (JavaFX)

This is a JavaFX-based desktop application that allows users to manage and simulate a football tournament. It provides a user-friendly interface to **CRUD teams**, **view match analytics**, and **simulate entire tournaments** with randomized results. All data is stored in a **MySQL** database.

---

## 🚀 Features

- ✅ **CRUD Teams**: Add, update, delete, and view football teams.
- 🎮 **Play Tournament**: Choose tournament name and type (32, 16, 8, 4, or 2 teams).
- 🎲 **Random Match Simulation**: Matches are played randomly and progress through knockout stages until a winner is found.
- 📊 **Match Analytics**: View history and results of all played matches.
- 💾 **MySQL Database Integration**: All data is persisted in a MySQL database.

---

## 📦 Installation & Setup

### 1. **Clone the Repository**

```bash
git clone https://github.com/your-username/football-tournament-app.git
cd football-tournament-app
```

### 2. Java & JavaFX Requirements

- Java 11 or higher

- JavaFX SDK

- MySQL Connector/J

Both JavaFX SDK and MySQL Connector are already included in the /lib folder.

### 3. Database Setup

1. Open the Database.java class.

2. Set your local MySQL credentials:

```bash

String databaseUser = "root";
String databasePassword = "your_password";
```

3. Import and run the following SQL files from /resources/database:

   - create_schema_and_tables.sql – Creates the database schema and tables.

   - seed_the_database.sql – Seeds the database with an initial list of football teams.

---

## 🧠 How It Works

1. You select a tournament name and size (32, 16, 8, etc.).

2. The app randomly pairs teams and simulates matches.

3. Winners automatically advance to the next round until a final winner is crowned.

4. All results are saved and viewable later via the Analytics page.

---

## Screenshots

![Screenshot](/public/image1.png)
![Screenshot](/public/image2.png)
![Screenshot](/public/image3.png)
![Screenshot](/public/image4.png)
![Screenshot](/public/image5.png)
![Screenshot](/public/image6.png)
![Screenshot](/public/image7.png)
![Screenshot](/public/image8.png)
![Screenshot](/public/image9.png)
![Screenshot](/public/image10.png)
