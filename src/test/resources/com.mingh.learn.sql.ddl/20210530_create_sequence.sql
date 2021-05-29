use learn;
-- 1. 新建序列表
drop table if exists sequence;
create table sequence (
                          seq_name        VARCHAR(50) NOT NULL, -- 序列名称
                          current_val     INT         NOT NULL, -- 当前值
                          increment_val   INT         NOT NULL    DEFAULT 1, -- 步长(跨度)
                          PRIMARY KEY (seq_name)   );
-- 2. 新增一个序列
INSERT INTO sequence VALUES ('seq_learn_num', '0', '1');
commit ;
-- 3. 创建 函数 用于获取序列当前值(v_seq_name 参数值 代表序列名称)
-- 如果这个地方报错(This function has none of DETERMINISTIC, NO SQL, or READS), 需要开启函数功能, 执行以下三条 sql 即可
-- show variables like '%func%';
-- set global log_bin_trust_function_creators=1;
-- show variables like '%func%';
create function currval(v_seq_name VARCHAR(50))
    returns integer
begin
    declare value integer;
    set value = 0;
select current_val into value  from sequence where seq_name = v_seq_name;
return value;
end;
-- 4. 查询当前值
select currval('seq_learn_num');
-- 5. 创建 函数 用于获取序列下一个值(v_seq_name 参数值 代表序列名称)
create function nextval (v_seq_name VARCHAR(50))
    returns integer
begin
update sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;
return currval(v_seq_name);
end;
-- 6. 查询下一个值
select nextval('seq_learn_num');