-- V2__insert_sample_data_expanded_with_metric_types.sql

-- ---------------------------------------------------------
-- 1. Users
-- Constraints: Exactly one user, username 'sample_user'
-- ---------------------------------------------------------
INSERT INTO users (id, username, email, password, role, created_at)
VALUES (1, 'sample_user', 'abc123fff@sample_email.com', '$2a$10$2.g0MRT2jKJCWNfyl6mSZe5LwtKcJGNSU8SyJVot6P09LM9LVzgkm', 'user', CURRENT_TIMESTAMP);


-- ---------------------------------------------------------
-- 2. Types of Media
-- Constraints: user_id is NULL when is_global = true, non-null otherwise
-- ---------------------------------------------------------
INSERT INTO types_of_media (id, name, is_global, user_id) VALUES
(1, 'Anime', true, null),
(2, 'Manga', true, null),
(3, 'Visual Novel', false, 1),
(4, 'Light Novel', true, null),
(5, 'Podcast', true, null),
(6, 'Live Action Drama', false, 1);

-- ---------------------------------------------------------
-- 3. Metric Types
-- Constraints: user_id is NULL when is_global = true, non-null otherwise
-- ---------------------------------------------------------
INSERT INTO metric_types (id, name, is_global, user_id) VALUES
(1, 'minutes', true, null),
(2, 'pages', true, null),
(3, 'characters', true, null),
(4, 'episodes', false, 1); -- Example of a custom metric for the user

-- ---------------------------------------------------------
-- 4. Media Items
-- Constraints: Language always 'Japanese', main_skill is reading/listening
-- metric_type_id links to the new metric_types table
-- ---------------------------------------------------------
INSERT INTO media_items (
    id, title, rating, language, main_skill, deleted, created_at, updated_at, 
    media_type_id, user_id, metric_type_id, max_value, has_subtitles, has_audio, has_visuals, study_intensity
) VALUES
-- Anime & Drama & Podcasts (Listening, Minutes -> ID 1)
(1, 'Sen to Chihiro no Kamikakushi', 10, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 125, true, true, true, 'Medium'),
(2, 'Shirokuma Cafe', 8, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 1200, false, true, true, 'Low'),
(3, 'Nihongo Con Teppei', 9, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 1, 1, 5000, false, true, false, 'Low'),
(4, 'Terrace House: Boys & Girls in the City', 7, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1, 1, 1380, true, true, true, 'High'),
(5, 'Kimi no Na wa (Your Name)', 10, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 107, true, true, true, 'Medium'),
(6, 'Jujutsu Kaisen Season 1', 9, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 576, true, true, true, 'Medium'),
(7, 'Alice in Borderland Season 1', 8, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1, 1, 400, true, true, true, 'High'),
(8, 'Death Note', 9, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 1, 885, true, true, true, 'High'),
(9, 'Let''s Learn Japanese from Small Talk', 8, 'Japanese', 'listening', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 1, 1, 1200, false, true, false, 'Low'),

-- Manga & Light Novels (Reading, Pages -> ID 2)
(10, 'Yotsuba&! Volume 1', 9, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 2, 224, false, false, true, 'Low'),
(11, 'Yotsuba&! Volume 2', 9, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 2, 224, false, false, true, 'Low'),
(12, 'Harry Potter to Kenja no Ishi', 8, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 1, 2, 350, false, false, false, 'High'),
(13, 'Bishoujo Senshi Sailor Moon Vol 1', 7, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 2, 200, false, false, true, 'Medium'),
(14, 'Kino no Tabi Volume 1', 9, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 1, 2, 250, false, false, false, 'Medium'),
(15, 'Shingeki no Kyojin Vol 1', 10, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 2, 192, false, false, true, 'High'),
(16, 'One Piece Vol 1', 10, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 1, 2, 216, false, false, true, 'Low'),

-- Visual Novels (Reading, Characters -> ID 3)
(17, 'Steins;Gate', 10, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1, 3, 800000, true, true, true, 'High'),
(18, 'Clannad', 9, 'Japanese', 'reading', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1, 3, 1200000, true, true, true, 'Medium');

-- ---------------------------------------------------------
-- 5. Additional Media Properties
-- ---------------------------------------------------------
INSERT INTO additional_media_properties (media_item_id, property_key, property_value) VALUES
(1, 'director', 'Hayao Miyazaki'),
(1, 'studio', 'Studio Ghibli'),
(2, 'studio', 'Pierrot'),
(3, 'host', 'Teppei'),
(4, 'network', 'Netflix'),
(5, 'director', 'Makoto Shinkai'),
(6, 'studio', 'MAPPA'),
(7, 'director', 'Shinsuke Sato'),
(8, 'studio', 'Madhouse'),
(10, 'author', 'Kiyohiko Azuma'),
(11, 'author', 'Kiyohiko Azuma'),
(12, 'author', 'J.K. Rowling'),
(12, 'translator', 'Yuko Matsuoka'),
(13, 'author', 'Naoko Takeuchi'),
(14, 'author', 'Keiichi Sigsawa'),
(15, 'author', 'Hajime Isayama'),
(16, 'author', 'Eiichiro Oda'),
(17, 'developer', 'MAGES.'),
(18, 'developer', 'Key');

-- ---------------------------------------------------------
-- 6. Progress Update Logs
-- ---------------------------------------------------------
INSERT INTO progress_update_logs (id, media_item_id, user_id, updated_at, progress_value) VALUES
(1, 1, 1, CURRENT_TIMESTAMP - INTERVAL '3 days', 45),
(2, 1, 1, CURRENT_TIMESTAMP - INTERVAL '2 days', 125),
(3, 2, 1, CURRENT_TIMESTAMP - INTERVAL '10 days', 300),
(4, 2, 1, CURRENT_TIMESTAMP - INTERVAL '5 days', 600),
(5, 3, 1, CURRENT_TIMESTAMP - INTERVAL '1 day', 150),
(6, 4, 1, CURRENT_TIMESTAMP - INTERVAL '14 days', 200),
(7, 5, 1, CURRENT_TIMESTAMP - INTERVAL '20 days', 107),
(8, 6, 1, CURRENT_TIMESTAMP - INTERVAL '8 days', 240),
(9, 7, 1, CURRENT_TIMESTAMP - INTERVAL '2 days', 400),
(10, 8, 1, CURRENT_TIMESTAMP - INTERVAL '6 days', 300),
(11, 9, 1, CURRENT_TIMESTAMP - INTERVAL '1 day', 60),
(12, 10, 1, CURRENT_TIMESTAMP - INTERVAL '5 days', 110),
(13, 10, 1, CURRENT_TIMESTAMP - INTERVAL '1 day', 224),
(14, 11, 1, CURRENT_TIMESTAMP - INTERVAL '12 hours', 50),
(15, 12, 1, CURRENT_TIMESTAMP - INTERVAL '15 days', 150),
(16, 13, 1, CURRENT_TIMESTAMP - INTERVAL '7 days', 200),
(17, 14, 1, CURRENT_TIMESTAMP - INTERVAL '3 days', 120),
(18, 15, 1, CURRENT_TIMESTAMP - INTERVAL '4 days', 192),
(19, 16, 1, CURRENT_TIMESTAMP - INTERVAL '9 days', 216),
(20, 17, 1, CURRENT_TIMESTAMP - INTERVAL '10 days', 15000),
(21, 17, 1, CURRENT_TIMESTAMP - INTERVAL '2 days', 45000),
(22, 18, 1, CURRENT_TIMESTAMP - INTERVAL '1 month', 100000);

-- ---------------------------------------------------------
-- 7. Monthly Progress Summaries
-- ---------------------------------------------------------
INSERT INTO monthly_progress_summaries (id, media_item_id, user_id, month, progress_at_start, progress_at_end) VALUES
(1, 1, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 125),
(2, 2, 1, DATE_TRUNC('month', CURRENT_DATE), 150, 600),
(3, 3, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 150),
(4, 4, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 200),
(5, 5, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 107),
(6, 6, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 240),
(7, 7, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 400),
(8, 8, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 300),
(9, 9, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 60),
(10, 10, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 224),
(11, 11, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 50),
(12, 12, 1, DATE_TRUNC('month', CURRENT_DATE), 50, 150),
(13, 13, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 200),
(14, 14, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 120),
(15, 15, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 192),
(16, 16, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 216),
(17, 17, 1, DATE_TRUNC('month', CURRENT_DATE), 0, 45000),
(18, 18, 1, DATE_TRUNC('month', CURRENT_DATE - INTERVAL '1 month'), 0, 100000);

-- ---------------------------------------------------------
-- 8. Sequence Alignment
-- ---------------------------------------------------------
ALTER SEQUENCE users_seq RESTART WITH 100;
ALTER SEQUENCE types_of_media_seq RESTART WITH 100;
ALTER SEQUENCE media_items_seq RESTART WITH 100;
ALTER SEQUENCE progress_update_logs_seq RESTART WITH 100;
ALTER SEQUENCE monthly_progress_summaries_seq RESTART WITH 100;
-- Assuming you created a sequence for your new table:
ALTER SEQUENCE metric_types_seq RESTART WITH 100;