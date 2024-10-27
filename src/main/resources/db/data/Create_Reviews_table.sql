CREATE TABLE Reviews
(
    review_id   SERIAL PRIMARY KEY,
    user_id     INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    item_id     INTEGER REFERENCES Items (item_id) ON DELETE CASCADE,
    review_text TEXT NOT NULL,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track review creation time
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of review
);