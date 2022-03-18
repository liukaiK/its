# 把未审批的数据变成已审批
update eve_event
set status        = '1',
    approval_time = now()
where status = '0';


# 增加删除状态
alter table eve_event add deleted tinyint default 0 not null comment '是否已经删除';

# 将已作废的数据变成已经删除
update eve_event set deleted = 1 where status = 2;

# 删除无用的列和索引
alter table eve_event
    drop foreign key FKkkwmjljmrrx45ng53lw3annwo;
drop index FKkkwmjljmrrx45ng53lw3annwo on eve_event;
alter table eve_event
    drop column vehicle_id;

# 增加索引
drop index eve_event_license_plate_number_index on eve_event;
create index eve_event_license_plate_number_time_violation_name_index
    on eve_event (license_plate_number, time, violation_name);


# 修改数据类型
alter table eve_event
    modify score_id char(36) null;


# 把第一次的分数设置为0
update eve_event set score = 0 where violation_name = '超速1' and num = 1;
update eve_event set score = 0 where violation_name = '超速2' and num = 1;
update eve_event set score = 0 where violation_name = '违章停车' and num = 1;

# 删除错误次数的数据
delete
from eve_event
where license_plate_number in
      (select license_plate_number from (select license_plate_number from eve_event where deleted = 1) as t1);