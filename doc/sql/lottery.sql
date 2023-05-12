/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.13 : Database - lottery
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lottery` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `lottery`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint(20) DEFAULT NULL COMMENT '活动ID',
  `activity_name` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '活动名称',
  `activity_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '活动描述',
  `begin_date_time` datetime NOT NULL COMMENT '开始时间',
  `end_date_time` datetime NOT NULL COMMENT '结束时间',
  `stock_count` int(11) NOT NULL COMMENT '库存',
  `take_count` int(11) DEFAULT NULL COMMENT '每人可参与次数',
  `state` int(11) DEFAULT NULL COMMENT '活动状态：编辑、提审、撤审、通过、运行、拒绝、关闭、开启',
  `creator` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `activity_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='活动配置';

/*Table structure for table `award` */

DROP TABLE IF EXISTS `award`;

CREATE TABLE `award` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `award_id` bigint(20) DEFAULT NULL COMMENT '奖品ID',
  `award_type` int(4) DEFAULT NULL COMMENT '奖品类型（文字描述、兑换码、优惠券、实物奖品暂无）',
  `award_count` int(11) DEFAULT NULL COMMENT '奖品数量',
  `award_name` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品名称',
  `award_content` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '奖品内容「文字描述、Key、码」',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'updateTime',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='奖品配置';

/*Table structure for table `strategy` */

DROP TABLE IF EXISTS `strategy`;

CREATE TABLE `strategy` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `strategy_desc` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '策略描述',
  `strategy_mode` int(4) DEFAULT NULL COMMENT '策略方式「1:单项概率、2:总体概率」',
  `grant_type` int(4) DEFAULT NULL COMMENT '发放奖品方式「1:即时、2:定时[含活动结束]、3:人工」',
  `grant_date` datetime DEFAULT NULL COMMENT '发放奖品时间',
  `ext_info` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '扩展信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `strategy_strategy_id_uindex` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略配置';

/*Table structure for table `strategy_detail` */

DROP TABLE IF EXISTS `strategy_detail`;

CREATE TABLE `strategy_detail` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` bigint(11) NOT NULL COMMENT '策略ID',
  `award_id` bigint(11) DEFAULT NULL COMMENT '奖品ID',
  `award_count` int(11) DEFAULT NULL COMMENT '奖品数量',
  `award_rate` decimal(5,2) DEFAULT NULL COMMENT '中奖概率',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='策略明细';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
