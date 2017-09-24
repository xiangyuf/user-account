CREATE SCHEMA `user_account` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `user_account`.`user` (
  `uid` BIGINT NOT NULL,
  `coins` BIGINT DEFAULT 0,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;