create table if not exists `learn`.`t_learn_jdbc_base`
(
    id bigint not null
    primary key,
    name varchar(50) default null comment '名称',
    age integer default null comment '年龄',
    birthday date default null comment '出生日期',
    description varchar(500) default null comment '说明',
    create_time datetime null comment '创建时间',
    update_time datetime null comment '修改时间'
) comment 'created for learning jdbc' ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;