CREATE TABLE IF NOT EXISTS metaplugins_registration
(
    id                INTEGER PRIMARY KEY AUTOINCREMENT,
    plugin_name       TEXT NOT NULL UNIQUE,
    plugin_version    TEXT NOT NULL UNIQUE,
    plugin_class_name TEXT NOT NULL, -- plugin class name, use reflection to load plugins
    plugin_desc       TEXT NOT NULL,
    plugin_helper     TEXT NOT NULL  -- plugin help message
);