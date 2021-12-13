CREATE SEQUENCE account_seq START 1;

CREATE TABLE account (
    id INTEGER,
    document_number INTEGER NOT NULL,
    PRIMARY KEY(id)
);