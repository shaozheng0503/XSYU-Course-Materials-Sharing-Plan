package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public int addSchedule(SysSchedule schedule) {
        String sql ="insert into sys_schedule values(DEFAULT,?,?,?)";
        int rows = baseUpdate(sql,schedule.getUid(),schedule.getTitle(),schedule.getCompleted());
        return rows;
    }

    @Override
    public List<SysSchedule> findAll() {
        String sql ="select sid,uid,title,completed from sys_schedule";
        List<SysSchedule> scheduleList = baseQuery(SysSchedule.class, sql);
        return scheduleList;
    }

    @Override
    public List<SysSchedule> findItemListByUid(int uid) {
        String sql = "select sid ,uid,title,completed from sys_schedule where uid = ? ";
        List<SysSchedule> itemList = baseQuery(SysSchedule.class, sql, uid);
        return itemList;
    }

    @Override
    public Integer addDefault(int uid) {
        String sql = "insert into sys_schedule values (DEFAULT,?,'请输入日程',0) ";
        return baseUpdate(sql,uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule schedule) {
        String sql ="update sys_schedule set title = ? ,completed = ?  where  sid = ?";
        return baseUpdate(sql,schedule.getTitle(),schedule.getCompleted(),schedule.getSid());
    }

    @Override
    public Integer removeSchedule(int sid) {
        String sql = "delete from sys_schedule where sid = ?";
        return baseUpdate(sql,sid);
    }
}
