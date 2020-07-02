
CREATE TABLE projects(
id INTEGER PRIMARY KEY,
name VARCHAR(50),
file VARCHAR(100)
);

CREATE TABLE tasks (
    id INTEGER PRIMARY KEY,
    project_id INTEGER NOT NULL UNIQUE,    
    theme VARCHAR(50),
    task_type VARCHAR(50),
    priority INTEGER,
    description VARCHAR(100),
    FOREIGN KEY (project_id) REFERENCES projects(id)    
);

CREATE TABLE users(
id INTEGER PRIMARY KEY,
task_id INTEGER NOT NULL UNIQUE,
first_name VARCHAR(50),
last_name VARCHAR(50),
FOREIGN KEY (task_id) REFERENCES tasks(id)
);




