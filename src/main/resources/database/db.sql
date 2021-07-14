CREATE TABLE `springsecurity`.`users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45)  NOT NULL,
    `password` VARCHAR(150) NULL,
    `email`    VARCHAR(45)  NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
);

CREATE TABLE `springsecurity`.`roles`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `springsecurity`.`users_roles`
(
    `user_id` INT NOT NULL,
    `role_id` INT NOT NULL,
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `springsecurity`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `role_id`
        FOREIGN KEY (`role_id`)
            REFERENCES `springsecurity`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO `springsecurity`.`roles` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `springsecurity`.`roles` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `springsecurity`.`users` (`id`, `username`, `password`, `email`) VALUES ('1', 'user', '$2y$12$LFip8MtHVwgbmE2OqDI2TOM3edpD5HJlI5wZGLLICX1mP9.LCxo32', 'user@mail.ru');
insert into springsecurity.users_roles(user_id, role_id) values (1,2);