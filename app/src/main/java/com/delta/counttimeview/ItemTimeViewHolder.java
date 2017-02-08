package com.delta.counttimeview;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.delta.library.CountTimeView;

public class ItemTimeViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    public CountTimeView mCountdownViewTest;

    private TimeEntity mItemInfo;
    private boolean isCountUp;

    public boolean isCountUp() {
        return isCountUp;
    }

    public void setCountUp(boolean countUp) {
        isCountUp = countUp;
    }

    public ItemTimeViewHolder(View itemView, int countViewId) {
        super(itemView);
        mViews = new SparseArray<>();
        //AutoUtils.autoSize(itemView);
        mCountdownViewTest = (CountTimeView) itemView.findViewById(countViewId);
        isCountUp = mCountdownViewTest.isCountUp();
    }

    public void bindData(TimeEntity itemInfo) {
        mItemInfo = itemInfo;
        refreshTime(System.currentTimeMillis());
    }

    public void refreshTime(long curTimeMillis) {
        if (null == mItemInfo) {
            return;
        }
        if (isCountUp) {
            mCountdownViewTest.updateShow(curTimeMillis - mItemInfo.getCreateTime());
        } else {
            if (mItemInfo.getEndTime() - curTimeMillis <= 0) {
                resetZero();
                return;
            }
            mCountdownViewTest.updateShow(mItemInfo.getEndTime() - curTimeMillis);
        }
    }

    public void resetZero() {
        mCountdownViewTest.allShowZero();
    }

    public <TView extends View> TView getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (TView) view;
    }

    public ItemTimeViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);

        return this;
    }

    public TimeEntity getBean() {
        return mItemInfo;
    }
}
