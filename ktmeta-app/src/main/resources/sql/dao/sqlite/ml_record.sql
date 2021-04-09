CREATE TABLE IF NOT EXISTS metalibs_registration
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    lib_name TEXT                                                    NOT NULL UNIQUE,
    lib_desc TEXT                                                    NOT NULL,
    assign_plugin REFERENCES meta_plugins_registration (plugin_name) NOT NULL,
    assign_db REFERENCES dbs_registration (database) UNIQUE,
    assign_index REFERENCES indices_registration (index_name) UNIQUE
);

INSERT INTO metalibs_registration (lib_name, lib_desc, assign_plugin, assign_db, assign_index)
VALUES (:lib_name, :lib_desc, :assign_plugin, :assign_db, :assign_index);

UPDATE metalibs_registration
SET lib_name      = :ml.lib_name,
    lib_desc      = :ml.lib_desc,
    assign_plugin = :ml.assign_plugin,
    assign_db     = :ml.assign_db,
    assign_index  = :ml.assign_index
WHERE id = :id;

SELECT id, lib_name, lib_desc, assign_plugin, assign_db, assign_index
FROM metalibs_registration;

DELETE
FROM metalibs_registration
WHERE id = :id;

SELECT DISTINCT 1
FROM metalibs_registration;

DROP TABLE metalibs_registration;