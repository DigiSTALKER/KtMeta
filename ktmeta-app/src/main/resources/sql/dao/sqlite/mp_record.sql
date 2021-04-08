CREATE TABLE IF NOT EXISTS metaplugins_registration
(
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    plugin_name       TEXT NOT NULL UNIQUE,
    plugin_version    TEXT NOT NULL UNIQUE,
    plugin_class_name TEXT NOT NULL, -- plugin class name, use reflection to load plugins
    plugin_desc       TEXT NOT NULL,
    plugin_helper     TEXT NOT NULL  -- plugin help message
);

INSERT INTO metaplugins_registration (plugin_name, plugin_version, plugin_class_name, plugin_desc, plugin_helper)
VALUES (:plugin_name, :plugin_version, :plugin_class_name, :plugin_desc, :plugin_helper);

UPDATE metaplugins_registration
SET plugin_name       = :mp.plugin_name,
    plugin_version    = :mp.plugin_version,
    plugin_class_name = :mp.plugin_class_name,
    plugin_desc       = :mp.plugin_desc,
    plugin_helper     = :mp.plugin_helper
WHERE id = :id;

SELECT id, plugin_name, plugin_version, plugin_class_name, plugin_desc, plugin_helper
FROM metaplugins_registration;

DELETE
FROM metaplugins_registration
WHERE id = :id;

SELECT DISTINCT 1
FROM metaplugins_registration;

DROP TABLE metaplugins_registration;