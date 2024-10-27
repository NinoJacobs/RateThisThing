CREATE TABLE Comments
(
    comment_id   SERIAL PRIMARY KEY,
    review_id    INTEGER REFERENCES Reviews (review_id) ON DELETE CASCADE,
    user_id      INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    comment_text TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track comment creation time
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of comment
);