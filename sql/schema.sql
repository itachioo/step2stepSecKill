-- init sql
CREATE DATABASE seckill;
use seckill;
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` VARCHAR (120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '商品数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=uts8 COMMENT='秒杀库存表';

-- init data
INSERT into seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone6',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('800元秒杀ipad',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('6600元秒杀mac book pro',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('7000元秒杀iMac',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');

CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL  COMMENT '秒杀商品id',
  `user_phone` BIGINT NOT NULL  COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识：-1无效 0：成功 1：已付款 2：已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY(seckill_id,user_phone),
  key idx_create_time(create_time)
)ENGINE=INNODB  DEFAULT CHARSET=uts8 COMMENT='秒杀成功明细表';