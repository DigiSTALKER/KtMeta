CREATE TABLE IF NOT EXISTS metalibs_registration
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    lib_name TEXT                                                    NOT NULL UNIQUE,
    lib_desc TEXT                                                    NOT NULL,
    assign_plugin REFERENCES meta_plugins_registration (plugin_name) NOT NULL,
    assign_db REFERENCES dbs_registration (database) UNIQUE,
    assign_index REFERENCES indices_registration (index_name) UNIQUE
);