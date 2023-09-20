CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    `name` VARCHAR(45),
    `email` VARCHAR(45),
    `city` VARCHAR(45),
    `street` VARCHAR(45),
    `number` VARCHAR(45)
);
