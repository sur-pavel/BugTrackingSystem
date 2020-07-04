
CREATE TABLE projects(
id INTEGER PRIMARY KEY,
name VARCHAR(50),
file VARCHAR(100)
);

CREATE TABLE tasks (
    id INTEGER PRIMARY KEY,     
    theme VARCHAR(50) NOT NULL UNIQUE,
    task_type VARCHAR(50),
    priority INTEGER,
    description VARCHAR(100),
    project_id INTEGER NOT NULL UNIQUE,
    FOREIGN KEY (project_id) REFERENCES projects(id)    
);

CREATE TABLE users(
id INTEGER PRIMARY KEY,
first_name VARCHAR(50),
last_name VARCHAR(50),
task_id INTEGER NOT NULL UNIQUE,
FOREIGN KEY (task_id) REFERENCES tasks(id)
);




