-- ----------------------------
-- 考题管理
-- ----------------------------
drop table if exists t_exam_list;
create table t_exam_list (
    exam_id           bigint(20)    not null auto_increment,
	type_id	   bigint(20) 	 not null  comment '题目类型id',
    code             varchar(200)    not null,
    title            varchar(200)    not null,
	answer			varchar(3000)   null comment '答案',
	npoint          numeric(13,4)   null comment '分数',
	remark            varchar(500)    default null               comment '备注',
	status            char(1)         default '0'                comment '题目状态（0正常 1停用）',
	del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
	create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
	update_by         varchar(64)     default ''                 comment '更新者',
	update_time       datetime                                   comment '更新时间',
    primary key (exam_id)
) engine=innodb comment = '题库表' ;


drop table if exists t_exam_file;
create table t_exam_file (
    file_id           bigint(20)    not null auto_increment,
    exam_id           bigint(20)    not null ,
    filepath		  varchar(3000) comment '文件路径',
	create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
    primary key (file_id)
) engine=innodb comment = '题库附件表' ;



drop table if exists t_exam_type;
create table t_exam_type (
	type_id	   bigint(20) 	 not null auto_increment,
    code             varchar(200)    not null,
    name            varchar(200)    not null,
    primary key (type_id)
) engine=innodb auto_increment=2000 comment = '题目类型表';

commit;

drop table if exists t_exam_venues;
create table t_exam_venues (
	venues_id	   bigint(20) 	 not null auto_increment comment '场次',
	nyear             varchar(200)    not null comment '年度',
	nmonth             varchar(200)    not null comment '月份',
    code             varchar(200)    not null comment '场次编号',
    name            varchar(200)    not null comment '场次名称',
	create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
	update_by         varchar(64)     default ''                 comment '更新者',
	update_time       datetime                                   comment '更新时间',
    primary key (venues_id)
) engine=innodb comment = '考试场次';

commit;



drop table if exists sys_post_class;
create table sys_post_class (
	class_id	   bigint(20) 	 not null auto_increment comment '岗位职级',
	post_id        bigint(200)    not null comment '岗位',
	level          bigint(10)    not null comment '职位等级',
    create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
	update_by         varchar(64)     default ''                 comment '更新者',
	update_time       datetime                                   comment '更新时间',
    primary key (class)
) engine=innodb comment = '岗位职级';

commit;


drop table if exists t_exam_question;
create table t_exam_question (
	q_id		   bigint(20) not null auto_increment comment '试卷id',
	dept_id		bigint(20)  comment '部门',
	postc	   bigint(20) 	  comment '岗位职级',
	post_id        bigint(200)   comment '岗位',
	level          bigint(10)    comment '职位等级',
	venues		varchar(200)     comment '考试场次',
	name        varchar(200)	 comment '考试名称',
	user_id		bigint(20)		 comment '考员',
	npoint	     numeric(13,4)   null comment '总得分',
    create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
	update_by         varchar(64)     default ''                 comment '更新者',
	update_time       datetime                                   comment '更新时间',
    primary key (q_id)
) engine=innodb comment = '考题';

commit;

drop table if exists t_exam_detail;
create table t_exam_detail (
	detail_id		   bigint(20) not null auto_increment comment '试卷内容id',
	q_id	bigint(20) not null comment '考题id',
	exam_id bigint(20) not null comment '题目id',
    primary key (detail_id)
) engine=innodb comment = '考卷内容';

commit;


drop table if exists t_exam_mark;
create table t_exam_mark (
	mark_id		   bigint(20) not null auto_increment comment '评分id',
	q_id	bigint(20) comment '考题id',
	exam_id bigint(20)  comment '题目id',
	checker bigint(20)  comment '考官id',
	npoint	     numeric(13,4)   null comment '单项得分',
    primary key (mark_id)
) engine=innodb comment = '评分内容';
commit;



drop table if exists t_exam_check;
create table t_exam_check (
	check_id		   bigint(20) not null auto_increment comment '出题id',
	dept_id		bigint(20) comment '部门',
	post_id        bigint(200)    not null comment '岗位',
	venues		  bigint(200)     not null comment '考试场次',
	title        varchar(200)	 not null comment '考试名称',
	num1        int	 default null comment '数量1',
	num2        int	 default null comment '数量2',
	num3        int	 default null comment '数量3',
	num4        int	 default null comment '数量4',
	num5        int	 default null comment '数量5',
	type1        varchar(200)	default null comment '题目1',
	type2        varchar(200)	default null comment '题目2',
	type3        varchar(200)	default null comment '题目3',
	type4        varchar(200)	default null comment '题目4',
	type5        varchar(200)	default null comment '题目5',
	examiner        varchar(200) default	 null comment '考员人员',
	exam_dept        varchar(200)	default null comment '考员部门',
	checker        varchar(200)	 null comment '考官人员',
	check_dept        varchar(200)	 null comment '考官部门',
    create_by         varchar(64)     default ''                 comment '创建者',
	create_time 	    datetime                                   comment '创建时间',
    primary key (check_id)
) engine=innodb comment = '出题系统数据';

commit;