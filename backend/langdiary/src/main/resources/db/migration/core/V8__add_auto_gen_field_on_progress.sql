ALTER TABLE progress_update_logs
    ADD COLUMN auto_generated BOOLEAN NOT NULL DEFAULT false;