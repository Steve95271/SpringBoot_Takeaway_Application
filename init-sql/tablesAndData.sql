-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (arm64)
--
-- Host: 127.0.0.1    Database: sky_take_out
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `address_book`
--
DROP DATABASE IF EXISTS sky_take_out;
CREATE DATABASE sky_take_out;
USE sky_take_out;

DROP TABLE IF EXISTS `address_book`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_book`
(
    `id`            bigint                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`       bigint                                                NOT NULL COMMENT '用户id',
    `consignee`     varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin          DEFAULT NULL COMMENT '收货人',
    `sex`           varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin           DEFAULT NULL COMMENT '性别',
    `phone`         varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
    `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '省级区划编号',
    `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '省级名称',
    `city_code`     varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '市级区划编号',
    `city_name`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '市级名称',
    `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '区级区划编号',
    `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci   DEFAULT NULL COMMENT '区级名称',
    `detail`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '详细地址',
    `label`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '标签',
    `is_default`    tinyint(1)                                            NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='地址簿';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_book`
--

LOCK TABLES `address_book` WRITE;
/*!40000 ALTER TABLE `address_book`
    DISABLE KEYS */;
INSERT INTO `address_book`
VALUES (2, 4, '吴彦祖', '0', '18500002222', '44', '广东省', '4401', '广州市', '440114', '花都区', '花东镇杨一村', '2',
        1),
       (3, 4, '李小龙', '0', '13799998888', '44', '广东省', '4401', '广州市', '440106', '天河区', '天河南一路', '1', 0);
/*!40000 ALTER TABLE `address_book`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category`
(
    `id`          bigint                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `type`        int                                                            DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '分类名称',
    `sort`        int                                                   NOT NULL DEFAULT '0' COMMENT '顺序',
    `status`      int                                                            DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
    `create_time` datetime                                                       DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                       DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint                                                         DEFAULT NULL COMMENT '创建人',
    `update_user` bigint                                                         DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_category_name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='菜品及套餐分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category`
    DISABLE KEYS */;
INSERT INTO `category`
VALUES (11, 1, '酒水饮料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1),
       (12, 1, '传统主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1),
       (13, 2, '人气套餐', 12, 1, '2022-06-09 22:11:38', '2022-06-10 11:04:40', 1, 1),
       (15, 2, '商务套餐', 13, 1, '2022-06-09 22:14:10', '2022-06-10 11:04:48', 1, 1),
       (16, 1, '蜀味烤鱼', 5, 1, '2022-06-09 22:15:37', '2024-04-14 21:07:50', 1, 1),
       (17, 1, '蜀味牛蛙', 1, 1, '2022-06-09 22:16:14', '2024-04-14 21:07:56', 1, 1),
       (18, 1, '特色蒸菜', 6, 1, '2022-06-09 22:17:42', '2022-06-09 22:17:42', 1, 1),
       (19, 1, '新鲜时蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1),
       (20, 1, '水煮鱼', 8, 1, '2022-06-09 22:22:29', '2022-06-09 22:23:45', 1, 1),
       (21, 1, '汤类', 11, 1, '2022-06-10 10:51:47', '2022-06-10 10:51:47', 1, 1);
/*!40000 ALTER TABLE `category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish`
(
    `id`          bigint                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '菜品名称',
    `category_id` bigint                                                NOT NULL COMMENT '菜品分类id',
    `price`       decimal(10, 2)                                         DEFAULT NULL COMMENT '菜品价格',
    `image`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
    `status`      int                                                    DEFAULT '1' COMMENT '0 停售 1 起售',
    `create_time` datetime                                               DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                               DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint                                                 DEFAULT NULL COMMENT '创建人',
    `update_user` bigint                                                 DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 74
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='菜品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish`
    DISABLE KEYS */;
INSERT INTO `dish`
VALUES (46, '王老吉', 11, 6.00,
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/f219ac9a-5aae-4cc9-a358-91fdfaffbb30.jpeg', '', 1,
        '2022-06-09 22:40:47', '2024-04-22 10:26:53', 1, 1),
       (47, '北冰洋', 11, 4.00,
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/c6446ac4-d682-4dec-abc3-89d85e011b0b.jpeg',
        '还是小时候的味道', 1, '2022-06-10 09:18:49', '2024-04-22 10:27:05', 1, 1),
       (48, '雪花啤酒', 11, 4.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/bf8cbfc1-04d2-40e8-9826-061ee41ab87c.png', '', 1,
        '2022-06-10 09:22:54', '2022-06-10 09:22:54', 1, 1),
       (49, '米饭', 12, 2.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/76752350-2121-44d2-b477-10791c23a8ec.png',
        '精选五常大米', 1, '2022-06-10 09:30:17', '2022-06-10 09:30:17', 1, 1),
       (50, '馒头', 12, 1.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png',
        '优质面粉', 1, '2022-06-10 09:34:28', '2022-06-10 09:34:28', 1, 1),
       (51, '老坛酸菜鱼', 20, 56.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png', '原料：汤，草鱼，酸菜',
        1, '2022-06-10 09:40:51', '2022-06-10 09:40:51', 1, 1),
       (52, '经典酸菜鮰鱼', 20, 66.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/5260ff39-986c-4a97-8850-2ec8c7583efc.png',
        '原料：酸菜，江团，鮰鱼', 1, '2022-06-10 09:46:02', '2022-06-10 09:46:02', 1, 1),
       (53, '蜀味水煮草鱼', 20, 38.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a6953d5a-4c18-4b30-9319-4926ee77261f.png', '原料：草鱼，汤', 1,
        '2022-06-10 09:48:37', '2022-06-10 09:48:37', 1, 1),
       (54, '清炒小油菜', 19, 18.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/3613d38e-5614-41c2-90ed-ff175bf50716.png', '原料：小油菜', 1,
        '2022-06-10 09:51:46', '2022-06-10 09:51:46', 1, 1),
       (55, '蒜蓉娃娃菜', 19, 18.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png', '原料：蒜，娃娃菜', 1,
        '2022-06-10 09:53:37', '2022-06-10 09:53:37', 1, 1),
       (56, '清炒西兰花', 19, 18.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/e9ec4ba4-4b22-4fc8-9be0-4946e6aeb937.png', '原料：西兰花', 1,
        '2022-06-10 09:55:44', '2022-06-10 09:55:44', 1, 1),
       (57, '炝炒圆白菜', 19, 18.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/22f59feb-0d44-430e-a6cd-6a49f27453ca.png', '原料：圆白菜', 1,
        '2022-06-10 09:58:35', '2022-06-10 09:58:35', 1, 1),
       (58, '清蒸鲈鱼', 18, 98.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', '原料：鲈鱼', 1,
        '2022-06-10 10:12:28', '2022-06-10 10:12:28', 1, 1),
       (59, '东坡肘子', 18, 138.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', '原料：猪肘棒', 1,
        '2022-06-10 10:24:03', '2022-06-10 10:24:03', 1, 1),
       (60, '梅菜扣肉', 18, 58.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/6080b118-e30a-4577-aab4-45042e3f88be.png', '原料：猪肉，梅菜', 1,
        '2022-06-10 10:26:03', '2022-06-10 10:26:03', 1, 1),
       (61, '剁椒鱼头', 18, 66.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/13da832f-ef2c-484d-8370-5934a1045a06.png', '原料：鲢鱼，剁椒', 1,
        '2022-06-10 10:28:54', '2022-06-10 10:28:54', 1, 1),
       (62, '金汤酸菜牛蛙', 17, 88.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png', '原料：鲜活牛蛙，酸菜',
        1, '2022-06-10 10:33:05', '2022-06-10 10:33:05', 1, 1),
       (63, '香锅牛蛙', 17, 56.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/f5ac8455-4793-450c-97ba-173795c34626.png',
        '配料：鲜活牛蛙，莲藕，青笋', 1, '2022-06-10 10:35:40', '2024-04-21 18:10:53', 1, 1),
       (64, '馋嘴牛蛙', 17, 98.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png',
        '配料：鲜活牛蛙，丝瓜，黄豆芽', 1, '2022-06-10 10:37:52', '2024-04-21 18:10:33', 1, 1),
       (65, '草鱼2斤', 16, 68.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/b544d3ba-a1ae-4d20-a860-81cb5dec9e03.png',
        '原料：草鱼，黄豆芽，莲藕', 1, '2022-06-10 10:41:08', '2022-06-10 10:41:08', 1, 1),
       (66, '江团鱼2斤', 16, 119.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png',
        '配料：江团鱼，黄豆芽，莲藕', 1, '2022-06-10 10:42:42', '2022-06-10 10:42:42', 1, 1),
       (67, '鮰鱼2斤', 16, 72.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/8cfcc576-4b66-4a09-ac68-ad5b273c2590.png',
        '原料：鮰鱼，黄豆芽，莲藕', 1, '2022-06-10 10:43:56', '2022-06-10 10:43:56', 1, 1),
       (68, '鸡蛋汤', 21, 4.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c09a0ee8-9d19-428d-81b9-746221824113.png', '配料：鸡蛋，紫菜', 1,
        '2022-06-10 10:54:25', '2022-06-10 10:54:25', 1, 1),
       (69, '平菇豆腐汤', 21, 6.00,
        'https://sky-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png', '配料：豆腐，平菇', 1,
        '2022-06-10 10:55:02', '2022-06-10 10:55:02', 1, 1),
       (73, '干炒牛河', 12, 250.00,
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/39f4c896-84e2-4751-b0bb-5137fb458f43.jpeg', '', 1,
        '2024-04-17 10:57:38', '2024-04-17 15:31:41', 1, 1);
/*!40000 ALTER TABLE `dish`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_flavor`
--

DROP TABLE IF EXISTS `dish_flavor`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_flavor`
(
    `id`      bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dish_id` bigint NOT NULL COMMENT '菜品',
    `name`    varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '口味名称',
    `value`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '口味数据list',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 111
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='菜品口味关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_flavor`
--

LOCK TABLES `dish_flavor` WRITE;
/*!40000 ALTER TABLE `dish_flavor`
    DISABLE KEYS */;
INSERT INTO `dish_flavor`
VALUES (40, 10, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]'),
       (41, 7, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (42, 7, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]'),
       (45, 6, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (46, 6, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (47, 5, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (48, 5, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]'),
       (49, 2, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]'),
       (50, 4, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]'),
       (51, 3, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]'),
       (52, 3, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (86, 52, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (87, 52, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (88, 51, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (89, 51, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (92, 53, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (93, 53, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (94, 54, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\"]'),
       (95, 56, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (96, 57, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (97, 60, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]'),
       (101, 66, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (102, 67, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (103, 65, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (104, 72, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]'),
       (110, 73, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
/*!40000 ALTER TABLE `dish_flavor`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee`
(
    `id`          bigint                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
    `username`    varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
    `password`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
    `phone`       varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
    `sex`         varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL COMMENT '性别',
    `id_number`   varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '身份证号',
    `status`      int                                                   NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:启用',
    `create_time` datetime                                                       DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                       DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint                                                         DEFAULT NULL COMMENT '创建人',
    `update_user` bigint                                                         DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='员工信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee`
    DISABLE KEYS */;
INSERT INTO `employee`
VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1,
        '2022-02-15 15:51:20', '2022-02-17 09:16:20', 10, 1),
       (2, '史蒂夫', 'steve', 'e10adc3949ba59abbe56e057f20f883e', '18502072474', '1', '440111199707132419', 1,
        '2024-04-13 18:28:02', '2024-04-15 10:12:20', 10, 1),
       (4, '鲍勃', 'bob', 'e10adc3949ba59abbe56e057f20f883e', '18502072474', '1', '440111199707132222', 1,
        '2024-04-13 18:55:38', '2024-04-14 17:32:16', 1, 1),
       (5, '大卫', 'david', 'e10adc3949ba59abbe56e057f20f883e', '18502034352', '1', '440123412013421', 1,
        '2024-04-14 20:40:55', '2024-04-15 10:46:06', 1, 1),
       (6, 'Eason', 'eason', 'e10adc3949ba59abbe56e057f20f883e', '18202009876', '1', '440100187502311111', 1,
        '2024-04-15 10:48:26', '2024-04-15 10:48:26', 1, 1);
/*!40000 ALTER TABLE `employee`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '名字',
    `image`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
    `order_id`    bigint         NOT NULL COMMENT '订单id',
    `dish_id`     bigint                                                 DEFAULT NULL COMMENT '菜品id',
    `setmeal_id`  bigint                                                 DEFAULT NULL COMMENT '套餐id',
    `dish_flavor` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '口味',
    `number`      int            NOT NULL                                DEFAULT '1' COMMENT '数量',
    `amount`      decimal(10, 2) NOT NULL COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 50
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail`
    DISABLE KEYS */;
INSERT INTO `order_detail`
VALUES (5, 'A套餐',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/5d0e4715-42fc-4481-8848-93048d4369fe.jpeg', 4,
        NULL, 35, NULL, 1, 999.00),
       (6, '蒜蓉娃娃菜', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png', 4,
        55, NULL, NULL, 1, 18.00),
       (7, '东坡肘子', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', 4, 59,
        NULL, NULL, 1, 138.00),
       (8, '清蒸鲈鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', 4, 58,
        NULL, NULL, 1, 98.00),
       (9, '北冰洋',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/c6446ac4-d682-4dec-abc3-89d85e011b0b.jpeg', 5, 47,
        NULL, NULL, 1, 4.00),
       (10, '平菇豆腐汤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png', 5,
        69, NULL, NULL, 1, 6.00),
       (11, '清蒸鲈鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', 5,
        58, NULL, NULL, 1, 98.00),
       (12, '米饭', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/76752350-2121-44d2-b477-10791c23a8ec.png', 6, 49,
        NULL, NULL, 1, 2.00),
       (13, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        6, 62, NULL, NULL, 1, 88.00),
       (14, '清蒸鲈鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', 6,
        58, NULL, NULL, 1, 98.00),
       (15, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        7, 62, NULL, NULL, 1, 88.00),
       (16, '馋嘴牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', 8,
        64, NULL, NULL, 1, 98.00),
       (17, '江团鱼2斤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png', 9,
        66, NULL, '不辣', 1, 119.00),
       (18, '馋嘴牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', 10,
        64, NULL, NULL, 1, 98.00),
       (19, '东坡肘子', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', 11,
        59, NULL, NULL, 1, 138.00),
       (20, '蒜蓉娃娃菜', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png', 12,
        55, NULL, NULL, 1, 18.00),
       (21, '馋嘴牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', 13,
        64, NULL, NULL, 1, 98.00),
       (22, '蒜蓉娃娃菜', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png', 13,
        55, NULL, NULL, 1, 18.00),
       (23, '伤心冲喜套餐',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/81c54eef-ab56-44c8-9e0c-8300fc5b97a6.jpeg', 14,
        NULL, 33, NULL, 1, 1688.00),
       (24, '江团鱼2斤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png', 14,
        66, NULL, '不辣', 1, 119.00),
       (25, '草鱼2斤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/b544d3ba-a1ae-4d20-a860-81cb5dec9e03.png', 14,
        65, NULL, '不辣', 1, 68.00),
       (26, '东坡肘子', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', 15,
        59, NULL, NULL, 1, 138.00),
       (27, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        16, 62, NULL, NULL, 1, 88.00),
       (28, '清蒸鲈鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', 17,
        58, NULL, NULL, 1, 98.00),
       (29, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        18, 62, NULL, NULL, 1, 88.00),
       (30, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        19, 62, NULL, NULL, 1, 88.00),
       (31, '馋嘴牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', 20,
        64, NULL, NULL, 1, 98.00),
       (32, '馒头', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png', 21, 50,
        NULL, NULL, 1, 1.00),
       (33, '干炒牛河',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/39f4c896-84e2-4751-b0bb-5137fb458f43.jpeg', 21,
        73, NULL, '不要葱', 1, 250.00),
       (34, '东坡肘子', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', 22,
        59, NULL, NULL, 1, 138.00),
       (35, '清蒸鲈鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', 22,
        58, NULL, NULL, 1, 98.00),
       (36, '老坛酸菜鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png', 22,
        51, NULL, '不要葱,不辣', 1, 56.00),
       (37, '馒头', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png', 22, 50,
        NULL, NULL, 1, 1.00),
       (38, '干炒牛河',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/39f4c896-84e2-4751-b0bb-5137fb458f43.jpeg', 22,
        73, NULL, '不要辣', 1, 250.00),
       (39, '老坛酸菜鱼', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png', 23,
        51, NULL, '不要葱,不辣', 1, 56.00),
       (40, '干炒牛河',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/39f4c896-84e2-4751-b0bb-5137fb458f43.jpeg', 23,
        73, NULL, '不要葱', 1, 250.00),
       (41, '平菇豆腐汤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png', 23,
        69, NULL, NULL, 1, 6.00),
       (42, '鸡蛋汤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c09a0ee8-9d19-428d-81b9-746221824113.png', 23, 68,
        NULL, NULL, 1, 4.00),
       (43, '雪花啤酒', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/bf8cbfc1-04d2-40e8-9826-061ee41ab87c.png', 23,
        48, NULL, NULL, 1, 4.00),
       (44, '北冰洋',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/c6446ac4-d682-4dec-abc3-89d85e011b0b.jpeg', 23,
        47, NULL, NULL, 1, 4.00),
       (45, '干炒牛河',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/39f4c896-84e2-4751-b0bb-5137fb458f43.jpeg', 24,
        73, NULL, '不要香菜', 1, 250.00),
       (46, '馋嘴牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', 25,
        64, NULL, NULL, 1, 98.00),
       (47, '金汤酸菜牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png',
        25, 62, NULL, NULL, 1, 88.00),
       (48, '香锅牛蛙', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/f5ac8455-4793-450c-97ba-173795c34626.png', 25,
        63, NULL, NULL, 1, 56.00),
       (49, '江团鱼2斤', 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png', 26,
        66, NULL, '中辣', 1, 119.00);
/*!40000 ALTER TABLE `order_detail`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `id`                      bigint         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `number`                  varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '订单号',
    `status`                  int            NOT NULL                                DEFAULT '1' COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
    `user_id`                 bigint         NOT NULL COMMENT '下单用户',
    `address_book_id`         bigint         NOT NULL COMMENT '地址id',
    `order_time`              datetime       NOT NULL COMMENT '下单时间',
    `checkout_time`           datetime                                               DEFAULT NULL COMMENT '结账时间',
    `pay_method`              int            NOT NULL                                DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
    `pay_status`              tinyint        NOT NULL                                DEFAULT '0' COMMENT '支付状态 0未支付 1已支付 2退款',
    `amount`                  decimal(10, 2) NOT NULL COMMENT '实收金额',
    `remark`                  varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '备注',
    `phone`                   varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '手机号',
    `address`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '地址',
    `user_name`               varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '用户名称',
    `consignee`               varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '收货人',
    `cancel_reason`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单取消原因',
    `rejection_reason`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '订单拒绝原因',
    `cancel_time`             datetime                                               DEFAULT NULL COMMENT '订单取消时间',
    `estimated_delivery_time` datetime                                               DEFAULT NULL COMMENT '预计送达时间',
    `delivery_status`         tinyint(1)     NOT NULL                                DEFAULT '1' COMMENT '配送状态  1立即送出  0选择具体时间',
    `delivery_time`           datetime                                               DEFAULT NULL COMMENT '送达时间',
    `pack_amount`             int                                                    DEFAULT NULL COMMENT '打包费',
    `tableware_number`        int                                                    DEFAULT NULL COMMENT '餐具数量',
    `tableware_status`        tinyint(1)     NOT NULL                                DEFAULT '1' COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders`
    DISABLE KEYS */;
INSERT INTO `orders`
VALUES (4, '1713876255753', 6, 4, 2, '2024-04-23 20:44:15', NULL, 1, 0, 1263.00, '', '18500002222', NULL, NULL,
        '吴彦祖', '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 21:43:00', 0, NULL, 4, 0, 0),
       (5, '1713876356201', 6, 4, 2, '2024-04-23 20:45:56', NULL, 1, 0, 117.00, '', '18500002222', NULL, NULL, '吴彦祖',
        '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 21:45:00', 0, NULL, 3, 0, 0),
       (6, '1713876407651', 6, 4, 2, '2024-04-23 20:46:48', NULL, 1, 0, 200.00, '', '18500002222', NULL, NULL, '吴彦祖',
        '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 21:46:00', 0, NULL, 4, 0, 0),
       (7, '1713876433037', 6, 4, 2, '2024-04-23 20:47:13', NULL, 1, 0, 95.00, '', '18500002222', NULL, NULL, '吴彦祖',
        '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 21:47:00', 0, NULL, 1, 0, 0),
       (8, '1713881991214', 6, 4, 2, '2024-04-23 22:19:51', NULL, 1, 0, 105.00, '', '18500002222', NULL, NULL, '吴彦祖',
        '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 23:19:00', 0, NULL, 1, 0, 0),
       (9, '1713883754200', 6, 4, 2, '2024-04-23 22:49:14', NULL, 1, 0, 126.00, '', '18500002222', NULL, NULL, '吴彦祖',
        '订单超时，自动取消', NULL, '2024-04-25 11:59:31', '2024-04-23 23:49:00', 0, NULL, 1, 0, 0),
       (10, '1713884333356', 5, 4, 2, '2024-04-23 22:58:53', '2024-04-23 22:59:46', 1, 1, 105.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-23 23:58:00', 0, NULL, 1, 0, 0),
       (11, '1713884780832', 6, 4, 2, '2024-04-23 23:06:21', '2024-04-23 23:06:24', 1, 1, 145.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, '订单量较多，暂时无法接单', '2024-04-24 21:52:00', '2024-04-23 00:06:00', 0, NULL, 1,
        0, 0),
       (12, '1713884858494', 6, 4, 2, '2024-04-23 23:07:38', '2024-04-23 23:07:54', 1, 2, 25.00, '', '18500002222',
        NULL, NULL, '吴彦祖', '用户取消', NULL, '2024-04-24 12:01:34', '2024-04-23 00:07:00', 0, NULL, 1, 0, 0),
       (13, '1713932515499', 5, 4, 2, '2024-04-24 12:21:55', '2024-04-24 12:21:57', 1, 1, 124.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-24 13:21:00', 0, NULL, 2, 0, 0),
       (14, '1713966614269', 5, 4, 2, '2024-04-24 21:50:14', '2024-04-24 21:50:17', 1, 1, 1884.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-24 22:50:00', 0, '2024-04-24 21:51:52', 3, 0, 0),
       (15, '1714033931974', 5, 4, 2, '2024-04-25 16:32:12', '2024-04-25 16:32:13', 1, 1, 145.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-25 17:32:00', 0, '2024-04-25 16:43:29', 1, 0, 0),
       (16, '1714034155122', 5, 4, 2, '2024-04-25 16:35:55', '2024-04-25 16:36:00', 1, 1, 95.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-25 17:35:00', 0, '2024-04-25 16:43:36', 1, 0, 0),
       (17, '1714034591496', 5, 4, 2, '2024-04-25 16:43:11', '2024-04-25 16:43:12', 1, 1, 105.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-25 17:43:00', 0, '2024-04-25 16:43:36', 1, 0, 0),
       (18, '1714034714329', 5, 4, 2, '2024-04-25 16:45:14', '2024-04-25 16:45:17', 1, 1, 95.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-25 17:45:00', 0, '2024-04-25 17:04:32', 1, 0, 0),
       (19, '1714035774944', 6, 4, 2, '2024-04-25 17:02:55', '2024-04-25 17:02:57', 1, 1, 95.00, '', '18500002222',
        NULL, NULL, '吴彦祖', '客户电话取消', NULL, '2024-04-25 17:04:22', '2024-04-25 18:02:00', 0, NULL, 1, 0, 0),
       (20, '1714035804272', 6, 4, 2, '2024-04-25 17:03:24', '2024-04-25 17:03:25', 1, 1, 105.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, '菜品已销售完，暂时无法接单', '2024-04-25 17:04:12', '2024-04-25 18:03:00', 0, NULL,
        1, 0, 0),
       (21, '1714195577191', 5, 4, 2, '2024-04-27 13:26:17', '2024-04-27 13:26:18', 1, 1, 259.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-27 14:26:00', 0, '2024-04-27 13:26:55', 2, 0, 0),
       (22, '1714203685078', 5, 4, 2, '2024-04-27 15:41:25', '2024-04-27 15:41:27', 1, 1, 554.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-27 16:41:00', 0, '2024-04-30 16:37:02', 5, 0, 0),
       (23, '1714368647148', 5, 4, 2, '2024-04-29 13:30:47', '2024-04-29 13:30:48', 1, 1, 336.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-04-29 14:30:00', 0, '2024-04-30 16:37:01', 6, 0, 0),
       (24, '1720752283977', 5, 4, 2, '2024-07-12 10:44:44', '2024-07-12 10:44:45', 1, 1, 257.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-07-12 11:44:00', 0, '2024-07-12 10:46:09', 1, 0, 0),
       (25, '1720752385287', 6, 4, 2, '2024-07-12 10:46:25', '2024-07-12 10:46:27', 1, 1, 251.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, '菜品已销售完，暂时无法接单', '2024-07-12 10:47:14', '2024-07-12 11:46:00', 0, NULL,
        3, 0, 0),
       (26, '1720752521236', 3, 4, 2, '2024-07-12 10:48:41', '2024-07-12 10:48:43', 1, 1, 126.00, '', '18500002222',
        NULL, NULL, '吴彦祖', NULL, NULL, NULL, '2024-07-12 11:48:00', 0, NULL, 1, 0, 0);
/*!40000 ALTER TABLE `orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setmeal`
--

DROP TABLE IF EXISTS `setmeal`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setmeal`
(
    `id`          bigint                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `category_id` bigint                                                NOT NULL COMMENT '菜品分类id',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '套餐名称',
    `price`       decimal(10, 2)                                        NOT NULL COMMENT '套餐价格',
    `status`      int                                                    DEFAULT '1' COMMENT '售卖状态 0:停售 1:起售',
    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述信息',
    `image`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
    `create_time` datetime                                               DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                               DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint                                                 DEFAULT NULL COMMENT '创建人',
    `update_user` bigint                                                 DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 36
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='套餐';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setmeal`
--

LOCK TABLES `setmeal` WRITE;
/*!40000 ALTER TABLE `setmeal`
    DISABLE KEYS */;
INSERT INTO `setmeal`
VALUES (33, 13, '伤心冲喜套餐', 1688.00, 1, '',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/81c54eef-ab56-44c8-9e0c-8300fc5b97a6.jpeg',
        '2024-04-17 23:39:06', '2024-04-18 10:38:44', 1, 1),
       (35, 13, 'A套餐', 999.00, 1, '',
        'https://sky-takeaway-app-steve.oss-cn-guangzhou.aliyuncs.com/5d0e4715-42fc-4481-8848-93048d4369fe.jpeg',
        '2024-04-21 12:33:02', '2024-04-21 12:33:02', 1, 1);
/*!40000 ALTER TABLE `setmeal`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setmeal_dish`
--

DROP TABLE IF EXISTS `setmeal_dish`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setmeal_dish`
(
    `id`         bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `setmeal_id` bigint                                                DEFAULT NULL COMMENT '套餐id',
    `dish_id`    bigint                                                DEFAULT NULL COMMENT '菜品id',
    `name`       varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
    `price`      decimal(10, 2)                                        DEFAULT NULL COMMENT '菜品单价（冗余字段）',
    `copies`     int                                                   DEFAULT NULL COMMENT '菜品份数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 70
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='套餐菜品关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setmeal_dish`
--

LOCK TABLES `setmeal_dish` WRITE;
/*!40000 ALTER TABLE `setmeal_dish`
    DISABLE KEYS */;
INSERT INTO `setmeal_dish`
VALUES (62, 33, 59, '东坡肘子', 138.00, 1),
       (63, 33, 60, '梅菜扣肉', 58.00, 1),
       (64, 33, 61, '剁椒鱼头', 66.00, 1),
       (65, 33, 62, '金汤酸菜牛蛙', 88.00, 1),
       (66, 33, 63, '香锅牛蛙', 88.00, 1),
       (67, 35, 62, '金汤酸菜牛蛙', 88.00, 1),
       (68, 35, 63, '香锅牛蛙', 88.00, 1),
       (69, 35, 64, '馋嘴牛蛙', 88.00, 1);
/*!40000 ALTER TABLE `setmeal_dish`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart`
(
    `id`          bigint         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '商品名称',
    `image`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '图片',
    `user_id`     bigint         NOT NULL COMMENT '主键',
    `dish_id`     bigint                                                 DEFAULT NULL COMMENT '菜品id',
    `setmeal_id`  bigint                                                 DEFAULT NULL COMMENT '套餐id',
    `dish_flavor` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '口味',
    `number`      int            NOT NULL                                DEFAULT '1' COMMENT '数量',
    `amount`      decimal(10, 2) NOT NULL COMMENT '金额',
    `create_time` datetime                                               DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 60
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `shopping_cart`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `openid`      varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '微信用户唯一标识',
    `name`        varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '姓名',
    `phone`       varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '手机号',
    `sex`         varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin   DEFAULT NULL COMMENT '性别',
    `id_number`   varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '身份证号',
    `avatar`      varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL COMMENT '头像',
    `create_time` datetime                                               DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb3
  COLLATE = utf8mb3_bin COMMENT ='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (4, 'os6ED7bi6zGa-xmXDKYahLvKA3so', NULL, NULL, NULL, NULL, NULL, '2024-04-21 10:05:44');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2025-03-25 15:08:49
