CREATE TABLE IF NOT EXISTS vehicle (
        VIN UUID NOT NULL PRIMARY KEY,
        regnr VARCHAR(100) NOT NULL UNIQUE,
        status VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS customer_vehicle (
        customer_id BIGINT NOT NULL REFERENCES customer (customer_id),
        VIN UUID NOT NULL REFERENCES vehicle (VIN),
        UNIQUE (customer_id, VIN)
);