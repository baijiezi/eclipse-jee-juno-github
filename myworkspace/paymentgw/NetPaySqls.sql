/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : paymentgw

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : UTF-8
@author 			  : wormwood
Date: 2010-08-23 14:18:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ali_pay_receive_detail`
-- 支付宝交易返回数据详细记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ali_pay_receive_detail` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据库记录自动ID ',
  `notify_type` char(64) NOT NULL COMMENT '通知类型',
  `notify_id` char(255) NOT NULL COMMENT '通知ID 用于验证',
  `notify_time` char(32) NOT NULL COMMENT '通知时间',
  `sign` varchar(512) NOT NULL COMMENT '签名	',
  `sign_type` char(8) NOT NULL COMMENT '签名方式',
  `trade_no` char(128) NOT NULL COMMENT '交易流水号',
  `out_trade_no` char(128) NOT NULL COMMENT '外部订单号',
  `subject` varchar(256) NOT NULL COMMENT '商品名称',
  `price` float(20) NOT NULL COMMENT '商品单价',
  `quantity` int(8) NOT NULL COMMENT '商品数量',
  `use_coupon` char(4) NOT NULL COMMENT '是否使用红包T｜F',
  `is_total_fee_adjust` char(4) NOT NULL COMMENT '总价调整标志T｜F',
  `trade_status` char(128) NOT NULL COMMENT '交易状态',
  `seller_email` char(128) NOT NULL COMMENT '卖方Email',
  `seller_id` char(128) NOT NULL COMMENT '卖方支付宝ID',
  `buyer_email` char(128) NOT NULL COMMENT '买方Email',
  `buyer_id` char(128) NOT NULL COMMENT '买方支付宝ID',
  `body` char(128) DEFAULT '0' COMMENT '商品描述NULL',
  `gmt_create` char(32) DEFAULT '0' COMMENT '交易创建时间 NULL',
  `gmt_payment` char(32) DEFAULT '0' COMMENT '交易支付时间 NULL',
  `gmt_send_goods` char(32) DEFAULT '0' COMMENT '发货时间 NULL',
  `gmt_refund` char(32) DEFAULT '0' COMMENT '退款时间 NULL',
  `gmt_close` char(32) DEFAULT '0' COMMENT '交易结束时间 NULL',
  `discount` char(32) DEFAULT '0' COMMENT '商品折扣 NULL',
  `paymentType` char(32) DEFAULT '0' COMMENT '支付宝支付类型 NULL',
  `add_time` bigint(20) NOT NULL COMMENT '数据添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8

-- ----------------------------
-- Table structure for `china_pay_receive_detail`
-- 银联交易返回数据详细记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `china_pay_receive_detail` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据库记录自动ID',
  `trans_date` char(32) NOT NULL COMMENT '交易日期	yyyyMMdd',
  `mer_id` char(255) NOT NULL COMMENT '商户ID',
  `order_id` char(255) NOT NULL COMMENT '生成银联用订单号',
  `out_trade_no` char(32) NOT NULL COMMENT '外部订单号',
  `trans_type` char(12) NOT NULL COMMENT '银联交易类型',
  `trans_amt` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `cury_id` char(12) NOT NULL COMMENT '货币类型',
  `chk_value` varchar(512) NOT NULL COMMENT '签名结果',
  `order_status` char(12) NOT NULL COMMENT '订单状态',
  `gate_id` char(12) NOT NULL COMMENT '默认支付银行',
  `add_time` bigint(20) NOT NULL COMMENT '数据添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- ----------------------------
-- Table structure for `ten_pay_receive_detail`
-- 财付通交易返回数据详细记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ten_pay_receive_detail` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据库记录自动ID',
  `cmdno` char(4) NOT NULL COMMENT '接口服务类型',
  `pay_result` char(4) NOT NULL COMMENT '订单状态',
  `date` char(12) NOT NULL COMMENT '交易日期	yyyyMMdd',
  `transaction_id` char(64) NOT NULL COMMENT '生成财付通对帐订单号',
  `sp_billno` char(32) NOT NULL COMMENT '外部订单号',
  `total_fee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `fee_type` char(6) NOT NULL COMMENT '货币类型',
  `attach` char(128) NOT NULL COMMENT '私有数据 商品代码',
  `sign` varchar(512) NOT NULL COMMENT '签名结果',
  `bargainor_id` char(64) NOT NULL COMMENT '商户ID',
  `add_time` bigint(20) NOT NULL COMMENT '数据添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-- ----------------------------
-- Table structure for `ten_pay_receive_query_detail`
-- 财付通交易查询返回数据详细记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `ten_pay_receive_query_detail` (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '数据库记录自动ID',
  `cmdno` char(4) NOT NULL COMMENT '接口服务类型',
  `pay_result` char(4) NOT NULL COMMENT '订单状态',
  `date` char(12) NOT NULL COMMENT '交易日期	yyyyMMdd',
  `transaction_id` char(64) NOT NULL COMMENT '生成财付通对帐订单号',
  `sp_billno` char(32) NOT NULL COMMENT '外部订单号',
  `total_fee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `fee_type` char(6) NOT NULL COMMENT '货币类型',
  `attach` char(128) NOT NULL COMMENT '私有数据 商品代码',
  `sign` varchar(512) NOT NULL COMMENT '签名结果',
  `bargainor_id` char(64) NOT NULL COMMENT '商户ID',
  `retmsg` char(64) NULL COMMENT '交易信息',
  `retcode` char(64) NULL COMMENT '交易代码', 
  `charset` char(64) NULL COMMENT '字符代码', 
  `output_xml` char(64) NULL COMMENT 'XML数据标识', 
  `pay_info` char(255) NULL COMMENT '支付信息',
  `return_url` char(255) NOT NULL COMMENT '通知地址', 
  `add_time` bigint(20) NOT NULL COMMENT '数据添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8



-- ----------------------------
-- Table structure for `fault`
-- ----------------------------
DROP TABLE IF EXISTS `fault`;
CREATE TABLE `fault` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `add_time` bigint(20) NOT NULL,
  `ExtOrderID` char(32) NOT NULL,
  `CardNO` char(32) NOT NULL,
  `PayOrderID` char(32) NOT NULL,
  `TransType` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fault
-- ----------------------------

-- ----------------------------
-- Table structure for `id`
-- ----------------------------
DROP TABLE IF EXISTS `id`;
CREATE TABLE `id` (
  `id` int(11) NOT NULL,
  `date_time` char(15) NOT NULL,
  PRIMARY KEY (`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_content` longtext NOT NULL,
  `add_time` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data`
-- 多支付服务 分发线程数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_async`
-- 多支付服务 数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_async` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_log`
-- 多支付服务 分发线程数据表 LOG记录表 字段说明同上
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_log` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_exception`
-- 多支付服务 已接收数据异常 数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_exception` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `tranLineDetail` char(255) NOT NULL COMMENT '实际支付流水号ID',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `addTime` bigint(20) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_fee_exception`
-- 多支付服务 总金额异常表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_fee_exception` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_succeed`
-- 多支付服务 交易成功记录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_succeed` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_fail`
-- 多支付服务 交易失败记录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_fail` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_expire`
-- 多支付服务 交易超时记录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_expire` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `inputParams` VARCHAR(1024) NOT NULL COMMENT '入口XML参数',
  `outputParams` VARCHAR(1024) NOT NULL COMMENT '出口XML参数'
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_data_warning`
-- 多支付服务 交易发送失败记录表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_data_warning` (
  `ID` char(32) NOT NULL COMMENT '多支付系统流水ID',
  `payType` int(1) NOT NULL COMMENT '实际支付类型',
  `tranLineDetail` char(255) NOT NULL COMMENT '多支付系统流水ID',
  `outOrderNo` char(128) NOT NULL COMMENT '外部订单号',
  `totalFee` bigint(20) NOT NULL COMMENT '交易总金额:分',
  `waresCode` char(128) NOT NULL COMMENT '商品代码:按订单号自定义',
  `checkKey` char(255) NOT NULL COMMENT '加密字符KEY',
  `checkValue` varchar(512) NOT NULL COMMENT '加密结果',
  `payStatus` int(1) NOT NULL COMMENT '交易状态',
  `initTime` bigint(20) NOT NULL COMMENT '初始化时间',
  `expireTime` bigint(20) NOT NULL COMMENT '时间毫秒数',
  `receiveTime` bigint(20) NOT NULL COMMENT '接收时间',
  `distributeTime` bigint(20) NOT NULL COMMENT '分发时间',
  `clientIP` char(64) NOT NULL COMMENT '实际支付客户端IP',
  `returnURL` char(255) NOT NULL COMMENT '业务系统通知地址',
  `distributeStatus` int(1) NOT NULL DEFAULT '3' COMMENT '分发状态',
  `receiveStatus` int(1) NOT NULL DEFAULT '1' COMMENT '接收状态',
  `TryTimes` int(1) NOT NULL DEFAULT '3' COMMENT '分发次数',
  `validateStatus` int(1) NOT NULL COMMENT '验证状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_id`
-- 多支付系统 流水ID表 
-- ----------------------------
DROP TABLE IF EXISTS `net_pay_id`;
CREATE TABLE `net_pay_id` (
  `id` int(11) NOT NULL,
  `date_time` char(15) NOT NULL,
  PRIMARY KEY (`date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_log`
-- 多支付系统  系统日志
-- ----------------------------
DROP TABLE IF EXISTS `net_pay_log`;
CREATE TABLE `net_pay_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_content` longtext NOT NULL,
  `add_time` bigint(20) NOT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `net_pay_log`
-- 多支付系统  系统异常日志[用于分离异常错误和正常的日志]
-- ----------------------------
CREATE TABLE IF NOT EXISTS `net_pay_exception_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增流水ID',
  `log_content` longtext NOT NULL COMMENT '日志内容' ,
  `add_time` bigint(20) NOT NULL COMMENT '日志日期' ,
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `payment`
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `ID` char(32) NOT NULL,
  `TransType` int(1) NOT NULL,
  `PayOrderID` char(32) NOT NULL,
  `ExtOrderID` char(32) NOT NULL,
  `CardNO` char(32) NOT NULL,
  `Flag` int(1) NOT NULL,
  `Message` char(255) NOT NULL,
  `Add_Time` bigint(20) NOT NULL,
  `Status` int(1) NOT NULL,
  `TryTimes` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `payment_log`
-- ----------------------------
DROP TABLE IF EXISTS `payment_log`;
CREATE TABLE `payment_log` (
  `ID` char(32) NOT NULL,
  `TransType` int(1) NOT NULL,
  `PayOrderID` char(32) NOT NULL,
  `ExtOrderID` char(32) NOT NULL,
  `CardNO` char(32) NOT NULL,
  `Flag` int(1) NOT NULL,
  `Message` char(255) NOT NULL,
  `Add_Time` bigint(20) NOT NULL,
  `Status` int(1) NOT NULL,
  `TryTimes` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
