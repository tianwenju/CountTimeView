package com.delta.counttimeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description :
 * @autHor :  V.Wenju.Tian
 * @date : 2017/2/6 14:42
 */


public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<ItemEntity> datas = new ArrayList<>();
    private ItemCountViewAdapter<ItemEntity> mMyAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
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
