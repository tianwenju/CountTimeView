package com.delta.counttimeview;

/**
 * @description :其他的实体类继承该类
 * @autHor :  V.Wenju.Tian
 * @date : 2017/2/6 15:24
 */


public class TimeEntity {
    protected int entityId;
    //结束时间主要用于倒计时
    protected long endTime;
    //开始时间主要正计时
    protected long createTime;

    public TimeEntity() {
    }

    public TimeEntity(int id, long endTime, long createTime) {
        this.entityId = id;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
