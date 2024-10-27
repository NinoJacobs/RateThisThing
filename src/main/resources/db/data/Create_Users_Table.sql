CREATE TABLE Users
(
    user_id       SERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password      VARCHAR(100) NOT NULL,
    first_name    VARCHAR(50)  NOT NULL,
    last_name     VARCHAR(50)  NOT NULL,
    date_of_birth DATE,
    profile_image VARCHAR(255),
    bio           TEXT,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track account creation time
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track profile update time
);