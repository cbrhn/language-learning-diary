ALTER TABLE media_items
    ADD finished BOOLEAN NOT NULL DEFAULT false;

ALTER TABLE media_items
    ADD finished_date date;