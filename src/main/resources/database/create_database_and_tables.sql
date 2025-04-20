-- Step 1: Create the Database
CREATE DATABASE IF NOT EXISTS FootballCupManager;

-- Step 2: Use the newly created database
USE FootballCupManager;

-- Step 3: Create the TEAM table
CREATE TABLE TEAM (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100) NOT NULL,
    team_country VARCHAR(100) NOT NULL
);

-- Step 4: Create the TOURNAMENT table
CREATE TABLE TOURNAMENT (
    tournament_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type ENUM('8', '16', '32') NOT NULL
);

-- Step 5: Create the TOURNAMENT_TEAM table (junction table)
CREATE TABLE TOURNAMENT_TEAM (
    tournament_team_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT,
    team_id INT,
    FOREIGN KEY (tournament_id) REFERENCES TOURNAMENT(tournament_id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES TEAM(team_id) ON DELETE CASCADE
);

-- Step 6: Create the PLAYED_MATCH table
CREATE TABLE PLAYED_MATCH (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT,
    team1_id INT,
    team2_id INT,
    result VARCHAR(50),  -- Store results in a simple format, e.g., '2-1'
    winner_id INT,
    FOREIGN KEY (tournament_id) REFERENCES TOURNAMENT(tournament_id) ON DELETE CASCADE,
    FOREIGN KEY (team1_id) REFERENCES TEAM(team_id),
    FOREIGN KEY (team2_id) REFERENCES TEAM(team_id),
    FOREIGN KEY (winner_id) REFERENCES TEAM(team_id)
);

-- Step 7: Create the ANALYTICS table
CREATE TABLE ANALYTICS (
    analytics_id INT AUTO_INCREMENT PRIMARY KEY,
    tournament_id INT,
    match_id INT,
    FOREIGN KEY (tournament_id) REFERENCES TOURNAMENT(tournament_id) ON DELETE CASCADE,
    FOREIGN KEY (match_id) REFERENCES PLAYED_MATCH(match_id) ON DELETE CASCADE
);
