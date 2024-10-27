CREATE TABLE Ratings
(
    rating_id  SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    item_id    INTEGER REFERENCES Items (item_id) ON DELETE CASCADE,
    rating     SMALLINT CHECK (rating BETWEEN 1 AND 5), -- Ratings can range from 1 to 5
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP      -- Track when rating was added
);