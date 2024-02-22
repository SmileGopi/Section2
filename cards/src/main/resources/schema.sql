CREATE TABLE IF NOT EXISTS cards (
    card_id int AUTO_INCREMENT PRIMARY KEY,
    mobile_number varchar(15) NOT NULL,
    card_number varchar(20) NOT NULL,
    card_type varchar(20) NOT NULL,
    total_limit int NOT NULL,
    amount_used int NOT NULL,
    available_amount int NOT NULL,
    created_at date NOT NULL,
    created_by varchar(30) NOT NULL,
    updated_at date DEFAULT NULL,
    updated_by varchar(30) DEFAULT NULL
);