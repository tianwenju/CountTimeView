## 基于CountDownView的时间控件扩展
### 前言
首先，新年的一年里祝大家，心想事成，鸡年大吉。去年的时候，我们做时间控件的时候一直遗留一个问题那就是正计时控件一直没有好的解决方案，我们很想把CountDownView既支持正计时又能支持倒计时。基于这个想法，便有了今天这篇文章，原理不在介绍，其实很简单，主要是我们知道怎么用，此控件的优点有：
* 实现了正计时倒计时的统一
* 优化了Adapter,不再绑定控件Id
* 一个属性实现正倒计时
* 不在为具体的时间属性设置别名
#### 具体用法
1. xml文件

属性 app:isCountUp="false"代表倒计时 true为正计时
```
<com.delta.library.CountTimeView
        android:id="@+id/cv_countView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_gravity="center_horizontal"
        android:layout_marginTop="20dp"
        app:isCountUp="false"
        app:isShowDay="true"
        app:isShowHour="true"
        app:isShowMillisecond="false"
        app:isShowMinute="true"
        app:isShowSecond="true"
        app:suffixDay="天"
        app:suffixDayRightMargin="10dp"
        app:suffixGravity="center"
        app:suffixHour=":"
        app:suffixMinute=":"
        app:suffixTextColor="#e61010"
        app:suffixTextSize="17sp"
        app:timeTextColor="#e60b0b"
        app:timeTextSize="20sp" />
```
2. 实体类

要继承TimeEntity
eg:
```
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

```

3.Adapter如下

倒计时 主要是==要注意在数据源的时候初始化endTime属性，也就是截止时间还有相应的EntityId==
```

public class CountDownActivity extends AppCompatActivity {


    private RecyclerView rv;
    private List<ItemEntity> datas = new ArrayList<>();
    private ItemCountViewAdapter<ItemEntity> mMyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        rv = ((RecyclerView) findViewById(R.id.rv));
        long time =System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setEntityId(i);
            entity.setTitle("第" + i);
            entity.setTime(i *60 *1000l);
            entity.setEndTime(time + entity.getTime());
            datas.add(entity);
        }
        mMyAdapter = new ItemCountViewAdapter<ItemEntity>(this, datas) {
            @Override
            protected int getCountViewId() {
                return R.id.cv_countView;
            }

            @Override
            protected int getLayoutId() {
                return R.layout.item_count_down;
            }

            @Override
            protected void convert(ItemTimeViewHolder holder, ItemEntity itemEntity, int position) {

                holder.setText(R.id.tv_title, itemEntity.getTitle());
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mMyAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mMyAdapter) {
            mMyAdapter.startRefreshTime();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mMyAdapter) {
            mMyAdapter.cancelRefreshTime();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mMyAdapter) {
            mMyAdapter.cancelRefreshTime();
        }
    }
}

```
正计时 ==主要是在获得数据源的时候初始化createTime,也就是起始时间和EntityId==

```

public class CountUpActivity extends AppCompatActivity {


    private RecyclerView rv;
    private List<ItemEntity> datas = new ArrayList<>();
    private ItemCountViewAdapter<ItemEntity> mMyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_up);
        rv = ((RecyclerView) findViewById(R.id.rv));

        for (int i = 0; i < 100; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setEntityId(i);
            entity.setTitle("第" + i);
            entity.setTime(i * 60 * 60 * 1000l);
            entity.setCreateTime(System.currentTimeMillis() - entity.getTime());
            datas.add(entity);
        }
        mMyAdapter = new ItemCountViewAdapter<ItemEntity>(this, datas) {
            @Override
            protected int getCountViewId() {
                return R.id.cv_countView;
            }

            @Override
            protected int getLayoutId() {
                return R.layout.list_item;
            }

            @Override
            protected void convert(ItemTimeViewHolder holder, ItemEntity itemEntity, int position) {

                holder.setText(R.id.tv_title, itemEntity.getTitle());
            }
        };
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mMyAdapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (null != mMyAdapter) {
            mMyAdapter.startRefreshTime();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != mMyAdapter) {
            mMyAdapter.cancelRefreshTime();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mMyAdapter) {
            mMyAdapter.cancelRefreshTime();
        }
    }
}

```
