-------------------------------
-- INSERT PAÍSES
-------------------------------
INSERT INTO country (id, name, code) VALUES (1, 'Ucrania', 'UKR');
INSERT INTO country (id, name, code) VALUES (2, 'Rusia', 'RUS');
INSERT INTO country (id, name, code) VALUES (3, 'Estados Unidos', 'USA');
INSERT INTO country (id, name, code) VALUES (4, 'China', 'CHN');
INSERT INTO country (id, name, code) VALUES (5, 'Israel', 'ISR');
INSERT INTO country (id, name, code) VALUES (6, 'Palestina', 'PSE');

-------------------------------
-- INSERT CONFLICTOS
-------------------------------
INSERT INTO conflict (id, name, start_date, status, description) VALUES
(1, 'Guerra ruso-ucraniana', '2014-02-20', 'ACTIVE',
 'Conflicto armado entre Ucrania y Rusia iniciado en 2014 y escalado en 2022.'),

(2, 'Conflicto Israel–Hamás', '2023-10-07', 'ACTIVE',
 'Enfrentamiento armado entre Israel y Hamás con epicentro en Gaza.'),

(3, 'Guerra de Corea (armisticio)', '1950-06-25', 'FROZEN',
 'Conflicto finalizado mediante armisticio en 1953, sin tratado de paz definitivo.');

-- =========================
-- INSERT CONFLICTO <--> PAÍS (ManyToMany)
-- =========================
INSERT INTO conflict_country (conflict_id, country_id) VALUES (1, 1); -- Ucrania
INSERT INTO conflict_country (conflict_id, country_id) VALUES (1, 2); -- Rusia
INSERT INTO conflict_country (conflict_id, country_id) VALUES (1, 3); -- Estados Unidos

INSERT INTO conflict_country (conflict_id, country_id) VALUES (2, 5); -- Israel
INSERT INTO conflict_country (conflict_id, country_id) VALUES (2, 6); -- Palestina

INSERT INTO conflict_country (conflict_id, country_id) VALUES (3, 3); -- Estados Unidos
INSERT INTO conflict_country (conflict_id, country_id) VALUES (3, 4); -- China

-- =========================
-- FACCIONES  → Conflict
-- =========================
INSERT INTO faction (id, name, conflict_id) VALUES
(1, 'Ucrania y aliados', 1),
(2, 'Federación Rusa', 1),
(3, 'Estado de Israel', 2),
(4, 'Hamás', 2),
(5, 'Partes firmantes del armisticio', 3);

-------------------------------
-- INSERT EVENTOS → Conflict
-------------------------------
INSERT INTO event (id, event_date, location, description, conflict_id) VALUES
(1, '2022-02-24', 'Kiev', 'Inicio de la invasión a gran escala.', 1),
(2, '2022-03-16', 'Mariúpol', 'Intensificación de los combates y crisis humanitaria.', 1),
(3, '2023-10-07', 'Sur de Israel / Gaza', 'Ataque inicial y comienzo del conflicto actual.', 2),
(4, '1953-07-27', 'Panmunjom', 'Firma del armisticio que congela el conflicto.', 3);