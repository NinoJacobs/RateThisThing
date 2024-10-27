CREATE TABLE Items
(
    item_id     SERIAL PRIMARY KEY,
    item_name   VARCHAR(100) NOT NULL,
    description TEXT,
    category_id INTEGER      REFERENCES Categories (category_id) ON DELETE SET NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track item creation time
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of item details
);