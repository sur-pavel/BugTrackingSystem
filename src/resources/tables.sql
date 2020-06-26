CREATE TABLE tasks (
	task_id INTEGER PRIMARY KEY,
	project_id INTEGER NOT NULL UNIQUE,
	user_id INTEGER NOT NULL UNIQUE,
	theme VARCHAR(50),
	task_type VARCHAR(50),
	priority INTEGER,
	description TEXT
);

CREATE TABLE users(
user_id INTEGER PRIMARY KEY,
task_id INTEGER,
project_id INTEGER,
first_name VARCHAR(50),
last_name VARCHAR(50),
);

CREATE TABLE projects(
project_id INTEGER PRIMARY KEY,
project_name VARCHAR(50),
file VARCHAR(100)
);