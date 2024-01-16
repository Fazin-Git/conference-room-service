CREATE TABLE conference_room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    max_capacity INT NOT NULL
);
INSERT INTO conference_room (name, max_capacity) VALUES ('Amaze', 3),('Beauty', 7),('Inspire', 12),('Strive', 20);
CREATE TABLE booking (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    conference_room_id BIGINT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    num_of_people INT NOT NULL,
    created_on TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NOT NULL,
    FOREIGN KEY (conference_room_id) REFERENCES conference_room(id)
);

CREATE TABLE user_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_on TIMESTAMP NOT NULL,
    updated_on TIMESTAMP NOT NULL
);