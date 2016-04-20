CREATE DATABASE IF NOT EXISTS futureDB DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use futureDB;

#此表用于装载所有的类目信息
CREATE TABLE allInfo (
tid varchar(24) DEFAULT "" primary key,  #类目id
ttable varchar(64) DEFAULT "" not NULL unique, #类目表名
tname varchar(64) DEFAULT "" not NULL, #类目名称
ttype int DEFAULT 0,              #类目类型，0数据中心,1资讯中心,2观点中心
ttypename varchar(24) DEFAULT "数据中心", #类目类型名称
torder int, #类目顺序id
turl varchar(512) DEFAULT "",  #类目对应的url
visible int DEFAULT 1,  #是否可见
isDelete  int DEFAULT 0, #是否被删除，0否
updateDate timestamp default 0 null#更新时间
);

#类目数据导入
insert into allInfo values ("001", "dt_goodST", "商品站", 0,"数据中心", 1, "http://www.100ppi.com/ppi/",1,0,now());
insert into allInfo values ("002", "dt_price", "价格", 0,"数据中心", 2, "http://www.100ppi.com/price/",1,0,now());
insert into allInfo values ("003", "dt_goodT", "商品表", 0,"数据中心", 3, "http://futures.100ppi.com/spb/",0,0,now());
insert into allInfo values ("004", "dt_goodFuture", "现期表", 0,"数据中心", 4, "http://www.100ppi.com/sf/",1,0,now());
insert into allInfo values ("005", "dt_InnerFuture", "内期表", 0,"数据中心", 5, "http://futures.100ppi.com/qhb/domestic/",1,0,now());
insert into allInfo values ("006", "dt_OuterFuture", "外期表", 0,"数据中心", 6, "http://futures.100ppi.com/qhb/overseas/",0,0,now());
insert into allInfo values ("007", "dt_OuterPan", "外盘表", 0,"数据中心", 7, "http://www.100ppi.com/ff/",1,0,now());
insert into allInfo values ("008", "dt_SameDate", "同期表", 0,"数据中心", 8, "http://futures.100ppi.com/tqb/",1,0,now());
insert into allInfo values ("009", "dt_goodEle", "现电表", 0,"数据中心", 9, "http://www.100ppi.com/se/",0,0,now());
insert into allInfo values ("010", "dt_goodFutureDiff", "基差表", 0,"数据中心", 10, "http://www.100ppi.com/sf2/",1,0,now());
insert into allInfo values ("011", "dt_goodFutureDiffP", "基差率", 0,"数据中心", 11, "http://www.100ppi.com/sf3/",1,0,now());
insert into allInfo values ("012", "dt_EleT", "电子表盘", 0,"数据中心", 12, "http://www.100ppi.com/exchange/dzpb/",0,0,now());
insert into allInfo values ("013", "dt_goodTop", "大宗榜", 0,"数据中心", 13, "http://top.100ppi.com/",1,0,now());
insert into allInfo values ("014", "dt_goodIndex", "商品指数", 0,"数据中心", 14, "http://www.100ppi.com/cindex/",1,0,now());
insert into allInfo values ("015", "dt_goodFutureIndex", "现期指数", 0,"数据中心", 15, "http://www.100ppi.com/cindex/qx.html",1,0,now());
insert into allInfo values ("016", "dt_industryIndex", "行业指数", 0,"数据中心", 16, "http://www.100ppi.com/cindex/nyzs.html",1,0,now());
insert into allInfo values ("017", "dt_blockIndex", "板块指数", 0,"数据中心", 17, "http://www.100ppi.com/cindex/xitu.html",1,0,now());
insert into allInfo values ("018", "dt_produceIndex", "产业链指数", 0,"数据中心", 18, "http://www.100ppi.com/cindex/ichain-362.html",1,0,now());
insert into allInfo values ("018", "dt_sellIndex", "交收指数", 0,"数据中心", 19, "http://cdindex.100ppi.com/",1,0,now());
insert into allInfo values ("019", "dt_cnMacroIndex", "中国宏观", 0,"数据中心", 20, "http://www.100ppi.com/mac/",1,0,now());
insert into allInfo values ("020", "dt_worldMacroIndex", "全球宏观", 0,"数据中心", 21, "http://www.100ppi.com/mac/world.html",1,0,now());


insert into allInfo values ("100", "news_fastNews", "快讯", 1,"资讯中心", 100, "http://www.100ppi.com/kx/",1,0,now());
insert into allInfo values ("101", "news_nowNews", "动态", 1,"资讯中心", 101, "http://www.100ppi.com/news/",1,0,now());
insert into allInfo values ("102", "news_hotNews", "热点", 1,"资讯中心", 102, "http://www.100ppi.com/hot/",1,0,now());
insert into allInfo values ("103", "news_dataNews", "数据资讯", 1,"资讯中心", 103, "http://www.100ppi.com/data/info/",1,0,now());
insert into allInfo values ("104", "news_reportNews", "研究报告", 1,"资讯中心", 104, "http://www.100ppi.com/report/",1,0,now());


insert into allInfo values ("201", "view_point", "观点", 1,"观点中心", 201, "http://www.100ppi.com/view/",1,0,now());
insert into allInfo values ("202", "view_analysis", "分析", 1,"观点中心", 202, "http://www.100ppi.com/forecast/",1,0,now());

