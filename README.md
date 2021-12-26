#收获地址表
##sql语句：
CREATE TABLE t_address (
	aid INT AUTO_INCREMENT COMMENT '收货地址id',
	uid INT COMMENT '归属的用户id',
	name VARCHAR(20) COMMENT '收货人姓名',
	province_name VARCHAR(15) COMMENT '省-名称',
	province_code CHAR(6) COMMENT '省-行政代号',
	city_name VARCHAR(15) COMMENT '市-名称',
	city_code CHAR(6) COMMENT '市-行政代号',
	area_name VARCHAR(15) COMMENT '区-名称',
	area_code CHAR(6) COMMENT '区-行政代号',
	zip CHAR(6) COMMENT '邮政编码',
	address VARCHAR(50) COMMENT '详细地址',
	phone VARCHAR(20) COMMENT '手机',
	tel VARCHAR(20) COMMENT '固话',
	tag VARCHAR(6) COMMENT '标签',
	is_default INT COMMENT '是否默认：0-不默认，1-默认',
	created_user VARCHAR(20) COMMENT '创建人',
	created_time DATETIME COMMENT '创建时间',
	modified_user VARCHAR(20) COMMENT '修改人',
	modified_time DATETIME COMMENT '修改时间',
	PRIMARY KEY (aid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

##收货地址实体类Adress，定义表中相关字段，继承BaseEntity
##新增收获地址
获取表单数据insert进数据库
查询现有uid下的收获地址
配置sql映射
##测试
创建测试类对以上两个语句
