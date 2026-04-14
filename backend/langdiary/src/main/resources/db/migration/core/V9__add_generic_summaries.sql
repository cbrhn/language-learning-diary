CREATE SEQUENCE IF NOT EXISTS time_frame_progress_summaries_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE time_frame_progress_summaries
(
    id                BIGINT  NOT NULL,
    media_item_id     INTEGER NOT NULL,
    user_id           INTEGER NOT NULL,
    starting_point    date,
    time_frame        VARCHAR(255),
    progress_at_start INTEGER,
    progress_at_end   INTEGER,
    CONSTRAINT pk_time_frame_progress_summaries PRIMARY KEY (id)
);

CREATE INDEX idx_timeframe ON time_frame_progress_summaries (starting_point, time_frame);

ALTER TABLE time_frame_progress_summaries
    ADD CONSTRAINT FK_TIME_FRAME_PROGRESS_SUMMARIES_ON_MEDIA_ITEM FOREIGN KEY (media_item_id) REFERENCES media_items (id) ON DELETE CASCADE;

ALTER TABLE time_frame_progress_summaries
    ADD CONSTRAINT FK_TIME_FRAME_PROGRESS_SUMMARIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;