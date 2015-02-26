
drop table if exists t_user;
drop table if exists t_order;

CREATE TABLE `t_user` (
  `id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(50)
             COLLATE utf8_bin NOT NULL,
  `password` VARCHAR(20)
             COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =15
  DEFAULT CHARSET =utf8
  COLLATE =utf8_bin;

CREATE TABLE `t_order` (
  `id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_no` VARCHAR(32)
             COLLATE utf8_bin NOT NULL,
  `user_id`  INT(11)          NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =6
  DEFAULT CHARSET =utf8
  COLLATE =utf8_bin;

INSERT INTO t_user (name, password) VALUES ("hello", "123");
INSERT INTO t_order (order_no, user_id) VALUES ("12345",1);