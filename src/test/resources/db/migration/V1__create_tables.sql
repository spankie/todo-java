CREATE TABLE tasks (
  id INT(11) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  title VARCHAR(150) NOT NULL,
  `description` TEXT,
  `status` ENUM('pending','done','inprogress') DEFAULT 'pending',
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NOW(),
  completed_at TIMESTAMP
);

INSERT INTO tasks VALUES (
  NULL, 'demo', 'demo description', 'pending', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL
);

-- 