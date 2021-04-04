CREATE TABLE IF NOT EXISTS dbs_registration
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    db_type     TEXT    NOT NULL,        -- database type
    db_name     TEXT    NOT NULL UNIQUE, -- database name
    db_desc     TEXT    NOT NULL,        -- database description
    db_url      TEXT    NOT NULL UNIQUE, -- database url
    user        TEXT,                    -- database username
    password    TEXT,                    -- database password (encrypted)
    save_passwd INTEGER NOT NULL,        -- has password or not, 0 -> false; 1 -> true
    CONSTRAINT db_type_check CHECK ( db_type IN ('Sqlite', 'Postgresql', 'Mysql', 'H2') ),
    CONSTRAINT save_passwd_or_not CHECK ( save_passwd IN (0, 1))
);