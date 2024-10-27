CREATE TABLE Images
(
    image_id    SERIAL PRIMARY KEY,
    user_id     INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    item_id     INTEGER REFERENCES Items (item_id) ON DELETE CASCADE,
    image_url   VARCHAR(255) NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Track when image was added
);
