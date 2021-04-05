CREATE TABLE IF NOT EXISTS indices_registration
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    index_name TEXT NOT NULL UNIQUE, -- elasticsearch index name
    index_desc TEXT NOT NULL,        -- elasticsearch index description
    index_url  TEXT NOT NULL UNIQUE  -- elasticsearch index url
);

INSERT INTO indices_registration(index_name, index_desc, index_url)
VALUES (:index_name, :index_desc, :index_url);

UPDATE indices_registration
SET index_name = :es.index_name,
    index_desc = :es.index_desc,
    index_url  = :es.index_url
WHERE id = :id;

SELECT id, index_name, index_desc, index_url
FROM indices_registration;

DELETE
FROM indices_registration
WHERE id = :id;

SELECT DISTINCT 1
FROM indices_registration;

DROP TABLE indices_registration;