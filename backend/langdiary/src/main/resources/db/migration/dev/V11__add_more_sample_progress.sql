INSERT INTO types_of_media (id, name, is_global, user_id) VALUES
    (101, 'Novel', true, null);

INSERT INTO media_items (
    id, title, rating, language, main_skill, deleted, created_at, updated_at,
    media_type_id, user_id, metric_type_id, max_value, has_subtitles, has_audio, has_visuals, study_intensity
) VALUES
(101, '本好きの下剋上', 10, 'Japanese', 'READING', false, '2026-03-12', '2026-03-12', 101, 1, 3, 135000, false, true, false, 'Medium');

INSERT INTO additional_media_properties (media_item_id, property_key, property_value) VALUES
(101, 'author', '香月美夜');

INSERT INTO progress_update_logs (id, media_item_id, user_id, updated_at, progress_value) VALUES
(101, 101, 1, '2026-03-12 07:00:00', 0),
(102, 101, 1, '2026-03-19 07:00:00', 25000),
(103, 101, 1, '2026-03-27 07:00:00', 54000),
(104, 101, 1, '2026-04-03 07:00:00', 80000),
(105, 101, 1, '2026-04-07 07:00:00', 84000),
(106, 101, 1, '2026-04-11 07:00:00', 91000);

INSERT INTO monthly_progress_summaries (id, media_item_id, user_id, month, progress_at_start, progress_at_end) VALUES
(101, 101, 1, '2026-03-01', 0, 68857),
(102, 101, 1, '2026-04-01', 68857, 91000);

INSERT INTO time_frame_progress_summaries (id, media_item_id, user_id, starting_point, time_frame, progress_at_start, progress_at_end) VALUES
(101, 101, 1, '2026-03-01', 'MONTH', 0, 68857),
(102, 101, 1, '2026-04-01', 'MONTH', 68857, 91000);