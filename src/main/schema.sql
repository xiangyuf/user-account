CREATE SCHEMA `user_account` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `user_account`.`user` (
  `user_id` BIGINT NOT NULL,
  `coins` BIGINT DEFAULT 0,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
