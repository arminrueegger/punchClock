INSERT INTO category (id, name) VALUES
                                  (1, 'Work'),
                                  (2, 'Study'),
                                  (3, 'Leisure'),
                                  (4, 'Errands')
ON CONFLICT (id) DO NOTHING;

INSERT INTO tag (id, title) VALUES
                              (1, 'Important'),
                              (2, 'Pairing'),
                              (3, 'Solo'),
                              (4, 'Remote')
ON CONFLICT (id) DO NOTHING;

INSERT INTO entry (id, checkin, checkout, categories_id, tag_id) VALUES
                                                                   (1, '2025-10-27 09:00:00', '2025-10-27 11:30:00', 1, 1),
                                                                   (2, '2025-10-27 13:00:00', '2025-10-27 16:00:00', 2, 2),
                                                                   (3, '2025-10-28 10:00:00', '2025-10-28 12:00:00', 3, 3),
                                                                   (4, '2025-10-28 14:00:00', '2025-10-28 17:15:00', 1, 4),
                                                                   (5, '2025-10-29 08:30:00', '2025-10-29 09:30:00', 4, 3),
                                                                   (6, '2025-10-29 15:00:00', '2025-10-29 18:00:00', 2, 1)
ON CONFLICT (id) DO NOTHING;

SELECT setval(pg_get_serial_sequence('entry','id'),    (SELECT COALESCE(MAX(id),0) FROM entry),    true);
SELECT setval(pg_get_serial_sequence('category','id'), (SELECT COALESCE(MAX(id),0) FROM category), true);
SELECT setval(pg_get_serial_sequence('tag','id'),      (SELECT COALESCE(MAX(id),0) FROM tag),      true);
