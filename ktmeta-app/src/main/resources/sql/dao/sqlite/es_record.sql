CREATE TABLE IF NOT EXISTS indices_registration
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    index_name TEXT NOT NULL UNIQUE,
    index_desc TEXT NOT NULL,
    index_url  TEXT NOT NULL UNIQUE
);