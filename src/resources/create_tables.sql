
CREATE TABLE projects(
id INTEGER PRIMARY KEY,
name VARCHAR(50),
file VARCHAR(100)
);

CREATE TABLE users(
user_id INTEGER PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50),
project_id INTEGER,
FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE tasks (
	task_id INTEGER PRIMARY KEY,
	project_id INTEGER NOT NULL UNIQUE,
	user_id INTEGER NOT NULL UNIQUE,
	theme VARCHAR(50),
	task_type VARCHAR(50),
	priority INTEGER,
	description TEXT
	FOREIGN KEY (project_id) REFERENCES projects(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);


