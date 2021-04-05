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

INSERT INTO dbs_registration(db_type, db_name, db_desc, db_url, user, password, save_passwd)
VALUES (:db_type, :db_name, :db_desc, :db_url, :user, :password, :save_passwd);

UPDATE dbs_registration
SET db_type     = :db.db_type,
    db_name     = :db.db_name,
    db_desc     = :db.db_desc,
    db_url      = :db.db_url,
    user        = :db.user,
    password    = :db.password,
    save_passwd = :db.save_passwd
WHERE id = :id;

SELECT id,
       db_type,
       db_name,
       db_desc,
       db_url,
       user,
       password,
       save_passwd
FROM dbs_registration;

DELETE
FROM dbs_registration
WHERE id = :id;

SELECT DISTINCT 1
FROM dbs_registration;

DROP TABLE dbs_registration;