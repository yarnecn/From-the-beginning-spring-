/*
Navicat MySQL Data Transfer

Source Server         : yarne
Source Server Version : 50640
Source Host           : 39.106.158.69:3306
Source Database       : everythings

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-06-25 17:59:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_premisstion
-- ----------------------------
DROP TABLE IF EXISTS `sys_premisstion`;
CREATE TABLE `sys_premisstion` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '模块的名称',
  `type` varchar(50) DEFAULT NULL COMMENT '模块的类型',
  `url` varchar(255) DEFAULT NULL COMMENT '模块对应的url',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父资源',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '父资源',
  `permisstion` varchar(50) DEFAULT NULL COMMENT '所需要的权限',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态，0正常,1删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_premisstion
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(100) DEFAULT NULL COMMENT '详细信息',
  `resource_ids` varchar(255) DEFAULT NULL COMMENT '所包含的权限id',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户真实姓名',
  `age` int(10) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `status` tinyint(2) DEFAULT NULL COMMENT '用户状态',
  `createtime` datetime DEFAULT NULL COMMENT '用户创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '陶彦男', '16', 'yarne', 'q1111111', 'y_nell@163.com', '0', '2018-06-21 10:44:13');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL COMMENT '用户id',
  `user_role_ids` varchar(255) DEFAULT NULL COMMENT '用户所含有的权限',
  `status` varchar(2) DEFAULT NULL COMMENT '用户状态:0正常,1删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
