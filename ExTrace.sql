

/*数据库名称统一为 extrace，编码为UTF-8 */

DROP TABLE IF EXISTS user;  -- 用户表
DROP TABLE IF EXISTS customer; -- 客户表
DROP TABLE IF EXISTS expresssheet; -- 运单（快件）表
DROP TABLE IF EXISTS package; -- 包裹表
DROP TABLE IF EXISTS transnode; -- 转运结点表
DROP TABLE IF EXISTS distributecenter; -- 分拣中心表
DROP TABLE IF EXISTS region; -- 区域表
DROP TABLE IF EXISTS position; -- 位置表
DROP TABLE IF EXISTS transhistory; -- 转运历史表




/*
用户（揽收人，派送人，责任人）表
*/
CREATE TABLE user
  ( 
     id              INT(10) NOT NULL auto_increment, -- 用户ID（自增主键）
	 uname             VARCHAR(16) NOT NULL, -- 用户名
     password              VARCHAR(32) NOT NULL, -- 密码
     role            INT(4) NOT NULL, -- 角色 （客户（访客）-- 0，配送员 -- ,1，分拣员 -- 2，经理 -- 3）
     telcode          VARCHAR(24) NOT NULL, -- 联系电话
     status           INT(4) NOT NULL, -- 状态 （休息？正在派送？正在揽收？，这个有待商议）
     dptid            CHAR(8),        -- 区域ID，应该是具体到这个快递员所住的街道了，反正记住是8位数
     receivepid 	  CHAR(24),  -- 收件包裹ID
     deliverpid  	  CHAR(24),  -- 发件包裹ID
     transpid   	  CHAR(24),  -- 转运包裹ID
     PRIMARY KEY (id) 
  ) 
CHARACTER SET utf8; 

/*
客户（寄件人，收件人）信息表
*/
CREATE TABLE customer 
  ( 
     id         INT(10) NOT NULL auto_increment, -- 客户ID（主键自增）
     cname       VARCHAR(16) NOT NULL,  -- 客户姓名
     telcode    VARCHAR(24) NOT NULL,   -- 联系电话
     department VARCHAR(64) NOT NULL,   -- 区域信息（省、市、县）
     regioncode CHAR(6), 	   -- 邮政编码
     address    VARCHAR(64) NOT NULL,   -- 地址信息（街道、小区、楼、门牌）
     poscode   INT(10) NOT NULL, -- 位置编码?
     PRIMARY KEY (id) 
  ) 
CHARACTER SET utf8; 

/*
快件信息表
*/
CREATE TABLE expresssheet
  ( 
     id          CHAR(24) NOT NULL, -- 运单编码（主键）
     expresstype        INT(4) NOT NULL,   -- 快件类型（当日 -- 0、次晨 -- 1、次日 -- 2、隔日 -- 3、经济快件 -- 4）
     sender      INT(10), -- 发件人 和 客户表id相关联
     receiver     INT(10), -- 收件人 和 客户表id相关联
     weight      FLOAT,    -- 重量
     transcost     FLOAT,    -- 运费
     packagecost  FLOAT,     -- 包装费
     insurcost     FLOAT,     -- 保险费
     accepter    VARCHAR(16), -- 揽收员
     deliver     VARCHAR(16), -- 投递员
     acceptetime DATETIME NULL, -- 揽收时间
     delivetime  DATETIME NULL, -- 投递时间
     acc1        VARCHAR(64),   -- ？这两个暂时还不清楚，有待商议
     acc2        VARCHAR(64),   -- ？
     status      INT(4), -- 状态（到达却未签收 -- 0、已签收 -- 1、在途中 -- 3，还有其他状态吗？）
     PRIMARY KEY (id) 
  ) 
CHARACTER SET utf8; 

/*
包裹信息表
*/
CREATE TABLE package 
  ( 
     id         CHAR(24) NOT NULL,  -- 包裹ID（主键）
     sourcenode CHAR(8),  -- 包裹出发地（中心、网点编码）
     targetnode CHAR(8),  -- 包裹目的地（中心、网点编码）
     createtime DATETIME NULL, -- 打包时间
     status     INT(4) NOT NULL, -- 包裹状态（打包 -- 0、途中 -- 1、拆包 -- 2、丢失 -- 3、回收 -- 4）
     PRIMARY KEY (id) 
  ) 
CHARACTER SET utf8; 


/*
转运节点表
*/
CREATE TABLE transnode 
  ( 
     id         CHAR(8) NOT NULL, -- 转运节点ID（主键）
     nodename   VARCHAR(32),      -- 转运结点名称
     nodetype   INT(10) NOT NULL, -- 转运结点类型（中心 -- 0， 网点 -- 1） 
     regioncode VARCHAR(6),       -- 邮政编码
     poscode   INT(10), 		  -- 位置编码
     telcode    VARCHAR(24) NOT NULL, -- 联系电话
     PRIMARY KEY (id) 
  ) 
CHARACTER SET utf8; 

/*
分拣中心信息表
*/
CREATE TABLE distributecenter 
  ( 
     sn             INT(11) NOT NULL auto_increment, -- 注册码（主键自增）
     expresssheetid CHAR(24) NOT NULL, -- 运单ID
     packageid CHAR(24) NOT NULL, -- 包裹ID
     PRIMARY KEY (sn) 
  ) 
CHARACTER SET utf8; 

/*
区域表
*/
CREATE TABLE region 
  ( 
     regioncode CHAR(6) NOT NULL, -- 邮政编码（主键）
     province       VARCHAR(32),  -- 省份
     city        VARCHAR(32),     -- 城市
     town        VARCHAR(32),     -- 县（镇）
     stage      INT(4) NOT NULL,  -- 理解为等级吧（省级 -- 1，市辖区、县、市 -- 2，具体到某个区、县以及市 -- 3）
     PRIMARY KEY (regioncode) 
  ) 
CHARACTER SET utf8; 



/*
位置信息表
*/
CREATE TABLE position 
  ( 
     poscode INT(10) NOT NULL auto_increment, -- 位置编码（主键自增）
     x       FLOAT NOT NULL, -- X坐标
     y       FLOAT NOT NULL, -- Y坐标
     PRIMARY KEY (poscode) 
  ) 
CHARACTER SET utf8; 



/*
转运历史表
*/
CREATE TABLE transhistory 
  ( 
     sn       INT(11) NOT NULL auto_increment,  -- 注册码（主键自增）
	 packageid CHAR(24) NOT NULL, -- 包裹ID
     acttime  DATETIME NOT NULL,  -- 揽收时间
     poscode  INT(10),      -- 位置编码
     uidfrom  INT(10) NOT NULL, -- 发件人ID
     uidto    INT(10) NOT NULL, -- 收件人ID
     PRIMARY KEY (sn) 
  ) 
CHARACTER SET utf8; 





/*设置快件ID为分拣中心表的expressid的外键*/
ALTER TABLE distributecenter
  ADD INDEX fktranspacka696996 (expresssheetid), 
  ADD CONSTRAINT fktranspacka696996 FOREIGN KEY (expresssheetid) REFERENCES 
  expresssheet (id); 

/*设置包裹ID为分拣中心transpackageid的外键*/
ALTER TABLE distributecenter
  ADD INDEX fktranspacka501479 (packageid), 
  ADD CONSTRAINT fktranspacka501479 FOREIGN KEY (packageid) REFERENCES 
  package (id); 

  /*设置包裹ID为转运历史packageid的外键*/
ALTER TABLE transhistory 
  ADD INDEX fktranshisto601520 (packageid), 
  ADD CONSTRAINT fktranshisto601520 FOREIGN KEY (packageid) REFERENCES 
  package (id); 

  /*运单表的sender和客户表id相关联*/
ALTER TABLE expresssheet 
  ADD INDEX fkexpressshe115006 (sender), 
  ADD CONSTRAINT fkexpressshe115006 FOREIGN KEY (sender) REFERENCES customer
  (id); 

  /*运单表的receiver和客户表id相关联*/
ALTER TABLE expresssheet 
  ADD INDEX fkexpressshe199945 (receiver), 
  ADD CONSTRAINT fkexpressshe199945 FOREIGN KEY (receiver) REFERENCES 
  customer (id); 