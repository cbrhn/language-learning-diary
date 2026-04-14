ALTER TABLE media_items
    ADD media_status VARCHAR(255) NOT NULL DEFAULT 'NOT_SET';

ALTER TABLE media_items
    DROP COLUMN finished;