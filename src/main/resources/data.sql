-------------------------------
-- INSERT PAÍSES
-------------------------------
INSERT INTO country (id, name, code)
SELECT 1, 'Ucrania', 'UKR'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 1 OR code = 'UKR');

INSERT INTO country (id, name, code)
SELECT 2, 'Rusia', 'RUS'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 2 OR code = 'RUS');

INSERT INTO country (id, name, code)
SELECT 3, 'Estados Unidos', 'USA'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 3 OR code = 'USA');

INSERT INTO country (id, name, code)
SELECT 4, 'China', 'CHN'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 4 OR code = 'CHN');

INSERT INTO country (id, name, code)
SELECT 5, 'Israel', 'ISR'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 5 OR code = 'ISR');

INSERT INTO country (id, name, code)
SELECT 6, 'Palestina', 'PSE'
WHERE NOT EXISTS (SELECT 1 FROM country WHERE id = 6 OR code = 'PSE');

-------------------------------
-- AÑADIR COLUMNA SOFT DELETE
-------------------------------
ALTER TABLE conflict
ADD COLUMN IF NOT EXISTS deleted boolean NOT NULL DEFAULT false;

UPDATE conflict
SET deleted = false
WHERE deleted IS NULL;

-------------------------------
-- INSERT CONFLICTOS
-------------------------------
INSERT INTO conflict (id, name, start_date, status, description, deleted)
SELECT 1,
       'Guerra ruso-ucraniana',
       DATE '2014-02-20',
       'ACTIVE',
       'Conflicto armado entre Ucrania y Rusia iniciado en 2014 y escalado en 2022.',
       false
WHERE NOT EXISTS (SELECT 1 FROM conflict WHERE id = 1);

INSERT INTO conflict (id, name, start_date, status, description, deleted)
SELECT 2,
       'Conflicto Israel–Hamás',
       DATE '2023-10-07',
       'ACTIVE',
       'Enfrentamiento armado entre Israel y Hamás con epicentro en Gaza.',
       false
WHERE NOT EXISTS (SELECT 1 FROM conflict WHERE id = 2);

INSERT INTO conflict (id, name, start_date, status, description, deleted)
SELECT 3,
       'Guerra de Corea (armisticio)',
       DATE '1950-06-25',
       'FROZEN',
       'Conflicto finalizado mediante armisticio en 1953, sin tratado de paz definitivo.',
       false
WHERE NOT EXISTS (SELECT 1 FROM conflict WHERE id = 3);

-------------------------------
-- INSERT CONFLICTO <--> PAÍS
-------------------------------
INSERT INTO conflict_country (conflict_id, country_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 1 AND country_id = 1);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 1, 2
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 1 AND country_id = 2);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 1, 3
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 1 AND country_id = 3);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 2, 5
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 2 AND country_id = 5);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 2, 6
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 2 AND country_id = 6);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 3, 3
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 3 AND country_id = 3);

INSERT INTO conflict_country (conflict_id, country_id)
SELECT 3, 4
WHERE NOT EXISTS (SELECT 1 FROM conflict_country WHERE conflict_id = 3 AND country_id = 4);

-------------------------------
-- FACCIONES
-------------------------------
INSERT INTO faction (id, name, conflict_id)
SELECT 1, 'Ucrania y aliados', 1
WHERE NOT EXISTS (SELECT 1 FROM faction WHERE id = 1);

INSERT INTO faction (id, name, conflict_id)
SELECT 2, 'Federación Rusa', 1
WHERE NOT EXISTS (SELECT 1 FROM faction WHERE id = 2);

INSERT INTO faction (id, name, conflict_id)
SELECT 3, 'Estado de Israel', 2
WHERE NOT EXISTS (SELECT 1 FROM faction WHERE id = 3);

INSERT INTO faction (id, name, conflict_id)
SELECT 4, 'Hamás', 2
WHERE NOT EXISTS (SELECT 1 FROM faction WHERE id = 4);

INSERT INTO faction (id, name, conflict_id)
SELECT 5, 'Partes firmantes del armisticio', 3
WHERE NOT EXISTS (SELECT 1 FROM faction WHERE id = 5);

-------------------------------
-- FACCION <--> PAÍS
-------------------------------
INSERT INTO faction_country (faction_id, country_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 1 AND country_id = 1);

INSERT INTO faction_country (faction_id, country_id)
SELECT 1, 3
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 1 AND country_id = 3);

INSERT INTO faction_country (faction_id, country_id)
SELECT 2, 2
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 2 AND country_id = 2);

INSERT INTO faction_country (faction_id, country_id)
SELECT 3, 5
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 3 AND country_id = 5);

INSERT INTO faction_country (faction_id, country_id)
SELECT 4, 6
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 4 AND country_id = 6);

INSERT INTO faction_country (faction_id, country_id)
SELECT 5, 3
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 5 AND country_id = 3);

INSERT INTO faction_country (faction_id, country_id)
SELECT 5, 4
WHERE NOT EXISTS (SELECT 1 FROM faction_country WHERE faction_id = 5 AND country_id = 4);

-------------------------------
-- EVENTOS
-------------------------------
INSERT INTO event (id, event_date, location, description, conflict_id)
SELECT 1, DATE '2022-02-24', 'Kiev', 'Inicio de la invasión a gran escala.', 1
WHERE NOT EXISTS (SELECT 1 FROM event WHERE id = 1);

INSERT INTO event (id, event_date, location, description, conflict_id)
SELECT 2, DATE '2022-03-16', 'Mariúpol', 'Intensificación de los combates y crisis humanitaria.', 1
WHERE NOT EXISTS (SELECT 1 FROM event WHERE id = 2);

INSERT INTO event (id, event_date, location, description, conflict_id)
SELECT 3, DATE '2023-10-07', 'Sur de Israel / Gaza', 'Ataque inicial y comienzo del conflicto actual.', 2
WHERE NOT EXISTS (SELECT 1 FROM event WHERE id = 3);

INSERT INTO event (id, event_date, location, description, conflict_id)
SELECT 4, DATE '1953-07-27', 'Panmunjom', 'Firma del armisticio que congela el conflicto.', 3
WHERE NOT EXISTS (SELECT 1 FROM event WHERE id = 4);