CREATE TABLE club
(
    id         INT AUTO_INCREMENT NOT NULL,
    title      VARCHAR(255)       NULL,
    photoURL   VARCHAR(255)       NULL,
    contact    VARCHAR(255)       NULL,
    CONSTRAINT pk_club PRIMARY KEY (id)
);

INSERT INTO `club` (`id`,  `contact`, `photoURL`, `title`) VALUES (NULL, 'test', 'C:\\Users\\oussa\\OneDrive\\Images\\photo.jpeg', 'oussama');
CREATE TABLE event
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)       NULL,
    email         VARCHAR(255)       NULL,
    `description` VARCHAR(255)       NULL,
    location      VARCHAR(255)       NULL,
    created_at    datetime           NULL,
    updated_at    datetime           NULL,
    club_id       INT                NULL,
    CONSTRAINT pk_event PRIMARY KEY (id)
);

ALTER TABLE event
    ADD CONSTRAINT FK_EVENT_ON_CLUB FOREIGN KEY (club_id) REFERENCES club (id);
ALTER TABLE club
    ADD CONSTRAINT uc_club_contact UNIQUE (contact);

ALTER TABLE club
    MODIFY title VARCHAR(255);
