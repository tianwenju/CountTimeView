package com.delta.counttimeview;

/**
 * @description :正计时的
 * @autHor :  V.Wenju.Tian
 * @date : 2017/2/6 15:31
 */


public class ItemEntity extends TimeEntity {

    private String title;

    private Long time;

    public ItemEntity() {
    }

    public ItemEntity(int id, long endTime, long createTime, String title, Long upTime) {
        super(id, endTime, createTime);
        this.title = title;
        this.time = upTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
