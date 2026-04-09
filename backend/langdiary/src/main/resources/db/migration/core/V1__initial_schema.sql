CREATE SEQUENCE IF NOT EXISTS media_items_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS monthly_progress_summaries_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS progress_update_logs_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS types_of_media_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE additional_media_properties
(
    media_item_id  INTEGER      NOT NULL,
    property_value VARCHAR(255),
    property_key   VARCHAR(255) NOT NULL,
    CONSTRAINT pk_additional_media_properties PRIMARY KEY (media_item_id, property_key)
);

CREATE TABLE media_items
(
    id              INTEGER                     NOT NULL,
    title           VARCHAR(255)                NOT NULL,
    rating          INTEGER,
    language        VARCHAR(255),
    main_skill      VARCHAR(255),
    deleted         BOOLEAN                     NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    media_type_id   INTEGER,
    user_id         INTEGER                     NOT NULL,
    metric_type     VARCHAR(255),
    max_value       INTEGER,
    has_subtitles   BOOLEAN,
    has_audio       BOOLEAN,
    has_visuals     BOOLEAN,
    study_intensity VARCHAR(255),
    CONSTRAINT pk_media_items PRIMARY KEY (id)
);

CREATE TABLE monthly_progress_summaries
(
    id                INTEGER NOT NULL,
    media_item_id     INTEGER NOT NULL,
    user_id           INTEGER NOT NULL,
    month             date,
    progress_at_start INTEGER,
    progress_at_end   INTEGER,
    CONSTRAINT pk_monthly_progress_summaries PRIMARY KEY (id)
);

CREATE TABLE progress_update_logs
(
    id             INTEGER                     NOT NULL,
    media_item_id  INTEGER                     NOT NULL,
    user_id        INTEGER                     NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    progress_value INTEGER                     NOT NULL,
    CONSTRAINT pk_progress_update_logs PRIMARY KEY (id)
);

CREATE TABLE types_of_media
(
    id        INTEGER      NOT NULL,
    name      VARCHAR(255) NOT NULL,
    is_global BOOLEAN      NOT NULL,
    user_id   INTEGER,
    CONSTRAINT pk_types_of_media PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         INTEGER                     NOT NULL,
    username   VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE INDEX idx_month ON monthly_progress_summaries (month);

ALTER TABLE media_items
    ADD CONSTRAINT FK_MEDIA_ITEMS_ON_MEDIA_TYPE FOREIGN KEY (media_type_id) REFERENCES types_of_media (id);

ALTER TABLE media_items
    ADD CONSTRAINT FK_MEDIA_ITEMS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE monthly_progress_summaries
    ADD CONSTRAINT FK_MONTHLY_PROGRESS_SUMMARIES_ON_MEDIA_ITEM FOREIGN KEY (media_item_id) REFERENCES media_items (id) ON DELETE CASCADE;

ALTER TABLE monthly_progress_summaries
    ADD CONSTRAINT FK_MONTHLY_PROGRESS_SUMMARIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE progress_update_logs
    ADD CONSTRAINT FK_PROGRESS_UPDATE_LOGS_ON_MEDIA_ITEM FOREIGN KEY (media_item_id) REFERENCES media_items (id) ON DELETE CASCADE;

ALTER TABLE progress_update_logs
    ADD CONSTRAINT FK_PROGRESS_UPDATE_LOGS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE types_of_media
    ADD CONSTRAINT FK_TYPES_OF_MEDIA_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE additional_media_properties
    ADD CONSTRAINT fk_additional_media_properties_on_media_item FOREIGN KEY (media_item_id) REFERENCES media_items (id);