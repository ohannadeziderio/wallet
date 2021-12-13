CREATE SEQUENCE transaction_seq START 1;

CREATE TABLE transaction (
    id INTEGER,
    account_id INTEGER NOT NULL,
    operation_type_id INTEGER NOT NULL,
    amount DECIMAL NOT NULL,
    event_date TIMESTAMP,
    PRIMARY KEY(id),
    FOREIGN KEY(account_id) REFERENCES account(id),
    FOREIGN KEY(operation_type_id) REFERENCES operation_type(id)
);