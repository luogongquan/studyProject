4、Sharding-jdbc的SQL支持情况总结
在使用分表设计的项目中，想达到直接扫描目标表的效果前提条件是where条件中必须传入分表字段。

下面提到的表t_order为逻辑表，实际的物理表为t_order_1...t_order_4，分片字段为creat_time，分片规则为取其月份分表）

4.1 SQL关键字使用
4.1.1 扫描所有分表
1. SQL中针对分表字段使用LIKE、to_days()函数、<=，扫描所有表

例：SELECT*FROM t_order WHERE creat_time like #{creat_time}

SELECT*FROM t_order WHERE to_days(creat_time)= to_days(#{creat_time})

SELECT*FROM t_order WHERE creat_time <= #{creat_time}

4.1.2 扫描目标表
1. 针对分片字段使用between and 并直接传值，可以扫描对应的表

例：SELECT*FROM t_order WHERE creat_time BETWEEN #{creat_time} AND #{creat_time1}

2.关于针对单表使用COUNT、MAX、MIN，并对分表字段传入条件值，可以直接扫描目标表

例：select count(*) from t_order where 1=1 and creat_time BETWEEN ? and ?

select max(id) from t_order where 1=1 and creat_time BETWEEN ? and ?

select min(id) from t_order where 1=1 and creat_time BETWEEN ? and ?

3.sql中使用group by 分组函数时，并对分表字段出入条件值，可以直接扫描目标表

例：select * from t_order where creat_time =? group by id

4.1.3 不支持使用的sql关键字
1. SQL语句中针对所有字段使用OR 、DISTINCT、UNION、having，会报错

例：select distinct t_order.id,t_order.name .....from t_order.....







2. SQL语句中针对分表字段使用between()and时，其中的参数包含子查询，会报错

例：SELECT*FROM t_order WHERE creat_time BETWEEN(select creat_time from t_order_1 where user_id=”6”) AND #{creat_time}

4.2 SQL中包含子查询
4.2.1 扫描所有分表
1.  SQL语句的where 条件没有传入分表的字段值，扫描所有表

例：SELECT * FROM  t_order

2.  SQL语句中包含冗余括号的子查询，扫描所有表

例：select*from(<include refid="selectSql"/>) x limit 0,100

注：selectSql为“SELECT * FROM  t_order where creat_time=”20180430””

4.2.2 扫描目标表
1.在查询条件中针对非分表字段含有子查询、分表字段传入条件值，可以直接扫描目标表

例：SELECT*FROM t_order WHERE 1=1 AND userName in (select userName from    t_order_1 where user_id="6")AND creat_time BETWEEN '20180419' and '20180420'



4.3 SQL联表查询
4.3.1 扫描所有分表
1.SQL中使用left join进行联表查询，并传入了分表字段，扫描所有表

例：from t_order LEFT JOIN main_users on t_order.u_ch_id = main_users.uid where 1=1
    and creat_time BETWEEN#{creatTime,jdbcType=TIMESTAMP}and #{creatTime1,jdbcType=TIMESTAMP}

4.3.2 扫描目标表
1.拆分的表与未进行拆分的表进行联表查，并传入分表字段值，可以直接扫描目标表

例：From t_order，main_users where t_order.u_ch_id=main_users.uid

and t_order.creat_time BETWEEN  #{creatTime,jdbcType=TIMESTAMP}and #{creatTime1,jdbcType=TIMESTAMP}



4.4 关于DML语句的支持
4.4.1 INSERT
1.insert语句针对分表字段使用SYSDATE()获取数据库系统时间，会报错

例：INSERT INTO t_order
(order_id,user_id,userName,passWord,user_sex,description,creat_time)
VALUES
(#{order_id},#{user_id},#{userName},#{passWord},#{userSex},#{description},SYSDATE())



2. insert语句中，针对分表字段必须传入正确的值，才可以直接插入到目标表

例：insert into t_order (id,name,creat_time) values (  “2”,”啊啊啊”,”2018-04-27”);

4.4.2 UPDATE
1.update语句中，如果where条件中没有分表字段，则会修改所有分表中满足条件的数据

例：update t_order set name=? where id=?



2.update语句中，如果where条件含有分表字段，则只修改目标表的数据

例：update t_order set name=? where id=? and creat_time=?



4.4.3 DELETE
1.delete语句中，如果where条件中没有分表字段，则会删除所有分表中满足条件的数据

例：delete from t_order where id = ?

2.delete语句中，如果where条件含有分表字段，则只修改目标表的数据

例：delete from t_order where id=? and creat_time=?



针对上述DML语句案例分析，各个分表间的主键id不应有重复（例如不能使用int自增型）。

4.5 关于事务的支持


针对数据库事务得的操作，只需自爱配置事务的数据源时引用shardingDataSource



在需要事务的地方使用@Transactional注解


