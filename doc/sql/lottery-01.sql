/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.13 : Database - lottery_01
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lottery_01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `lottery_01`;

/*Table structure for table `user_strategy_export_001` */

DROP TABLE IF EXISTS `user_strategy_export_001`;

CREATE TABLE `user_strategy_export_001` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` mediumtext,
  `activity_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  `strategy_type` int(11) DEFAULT NULL,
  `grant_type` int(11) DEFAULT NULL,
  `grant_date` timestamp NULL DEFAULT NULL,
  `grant_state` int(11) DEFAULT NULL,
  `award_id` bigint(20) DEFAULT NULL,
  `award_type` int(11) DEFAULT NULL,
  `award_name` mediumtext,
  `award_content` mediumtext,
  `uuid` mediumtext,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户策略计算结果表';

/*Table structure for table `user_strategy_export_002` */

DROP TABLE IF EXISTS `user_strategy_export_002`;

CREATE TABLE `user_strategy_export_002` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` mediumtext,
  `activity_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  `strategy_type` int(11) DEFAULT NULL,
  `grant_type` int(11) DEFAULT NULL,
  `grant_date` timestamp NULL DEFAULT NULL,
  `grant_state` int(11) DEFAULT NULL,
  `award_id` bigint(20) DEFAULT NULL,
  `award_type` int(11) DEFAULT NULL,
  `award_name` mediumtext,
  `award_content` mediumtext,
  `uuid` mediumtext,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户策略计算结果表';

/*Table structure for table `user_strategy_export_003` */

DROP TABLE IF EXISTS `user_strategy_export_003`;

CREATE TABLE `user_strategy_export_003` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` mediumtext,
  `activity_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  `strategy_type` int(11) DEFAULT NULL,
  `grant_type` int(11) DEFAULT NULL,
  `grant_date` timestamp NULL DEFAULT NULL,
  `grant_state` int(11) DEFAULT NULL,
  `award_id` bigint(20) DEFAULT NULL,
  `award_type` int(11) DEFAULT NULL,
  `award_name` mediumtext,
  `award_content` mediumtext,
  `uuid` mediumtext,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户策略计算结果表';

/*Table structure for table `user_strategy_export_004` */

DROP TABLE IF EXISTS `user_strategy_export_004`;

CREATE TABLE `user_strategy_export_004` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` mediumtext,
  `activity_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  `strategy_type` int(11) DEFAULT NULL,
  `grant_type` int(11) DEFAULT NULL,
  `grant_date` timestamp NULL DEFAULT NULL,
  `grant_state` int(11) DEFAULT NULL,
  `award_id` bigint(20) DEFAULT NULL,
  `award_type` int(11) DEFAULT NULL,
  `award_name` mediumtext,
  `award_content` mediumtext,
  `uuid` mediumtext,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户策略计算结果表';

/*Table structure for table `user_take_activity` */

DROP TABLE IF EXISTS `user_take_activity`;

CREATE TABLE `user_take_activity` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` tinytext,
  `take_id` bigint(20) DEFAULT NULL,
  `activity_id` bigint(20) DEFAULT NULL,
  `activity_name` tinytext,
  `take_date` timestamp NULL DEFAULT NULL,
  `take_count` int(11) DEFAULT NULL,
  `uuid` tinytext,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户参与活动记录表';

/*Table structure for table `user_take_activity_count` */

DROP TABLE IF EXISTS `user_take_activity_count`;

CREATE TABLE `user_take_activity_count` (
  `id` bigint(20) DEFAULT NULL,
  `u_id` tinytext,
  `activity_id` bigint(20) DEFAULT NULL,
  `total_count` int(11) DEFAULT NULL,
  `left_count` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户活动参与次数表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
