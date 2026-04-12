CREATE SEQUENCE IF NOT EXISTS metric_types_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE metric_types
(
    id        INTEGER      NOT NULL,
    name      VARCHAR(255) NOT NULL,
    is_global BOOLEAN      NOT NULL,
    user_id   INTEGER,
    CONSTRAINT pk_metric_types PRIMARY KEY (id)
);

ALTER TABLE media_items
    ADD metric_type_id INTEGER;

ALTER TABLE media_items
    ADD CONSTRAINT FK_MEDIA_ITEMS_ON_METRIC_TYPE FOREIGN KEY (metric_type_id) REFERENCES metric_types (id);

ALTER TABLE metric_types
    ADD CONSTRAINT FK_METRIC_TYPES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE media_items
    DROP COLUMN metric_type;

ALTER TABLE users
    ALTER COLUMN role SET NOT NULL;