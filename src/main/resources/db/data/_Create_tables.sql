-- Order of the table creation matters
-- Follow the same order to seed the data

-- Create Users table first
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

-- Create Categories table next
CREATE TABLE Categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track category creation time
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of category
);

-- Create Items table
CREATE TABLE Items
(
    item_id     SERIAL PRIMARY KEY,
    item_name   VARCHAR(100) NOT NULL,
    description TEXT,
    category_id INTEGER      REFERENCES Categories (category_id) ON DELETE SET NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Track item creation time
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Track last update of item details
);

-- Create Reviews table
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

-- Create Ratings table
CREATE TABLE Ratings
(
    rating_id  SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    item_id    INTEGER REFERENCES Items (item_id) ON DELETE CASCADE,
    rating     SMALLINT CHECK (rating BETWEEN 1 AND 5), -- Ratings can range from 1 to 5
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP      -- Track when rating was added
);

-- Create Comments table
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

-- Create Images table last
CREATE TABLE Images
(
    image_id    SERIAL PRIMARY KEY,
    user_id     INTEGER REFERENCES Users (user_id) ON DELETE CASCADE,
    item_id     INTEGER REFERENCES Items (item_id) ON DELETE CASCADE,
    image_url   VARCHAR(255) NOT NULL,
    upload_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Track when image was added
);