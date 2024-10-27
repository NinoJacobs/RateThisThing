CREATE TABLE Categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track category creation time
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of category
);