package com.delta.counttimeview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.delta.library.CountTimeView;

public class MainActivity extends AppCompatActivity implements CountTimeView.OnCountdownEndListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountTimeView mCvCountTimeViewTest1 = (CountTimeView) findViewById(R.id.cv_countdownViewTest1);
        mCvCountTimeViewTest1.setTag("test1");
        long time1 = (long) 5 * 60 * 60 * 1000;
        mCvCountTimeViewTest1.start(time1);

        CountTimeView mCvCountTimeViewTest2 = (CountTimeView) findViewById(R.id.cv_countdownViewTest2);
        mCvCountTimeViewTest2.setTag("test2");
        long time2 = (long) 30 * 60 * 1000;
        mCvCountTimeViewTest2.start(time2);

        CountTimeView cvCountTimeViewTest211 = (CountTimeView) findViewById(R.id.cv_countdownViewTest21);
        cvCountTimeViewTest211.setTag("test21");
        long time211 = (long) 30 * 60 * 1000;
        cvCountTimeViewTest211.start(time211);

        CountTimeView mCvCountTimeViewTest21 = (CountTimeView) findViewById(R.id.cv_countdownViewTest211);
        mCvCountTimeViewTest21.setTag("test21");
        long time21 = (long) 24 * 60 * 60 * 1000;
        mCvCountTimeViewTest21.start(time21);

        CountTimeView mCvCountTimeViewTest22 = (CountTimeView) findViewById(R.id.cv_countdownViewTest22);
        mCvCountTimeViewTest22.setTag("test22");
        long time22 = (long) 30 * 60 * 1000;
        mCvCountTimeViewTest22.start(time22);

        CountTimeView mCvCountTimeViewTest3 = (CountTimeView) findViewById(R.id.cv_countdownViewTest3);
        long time3 = (long) 9 * 60 * 60 * 1000;
        mCvCountTimeViewTest3.start(time3);

        CountTimeView mCvCountTimeViewTest4 = (CountTimeView) findViewById(R.id.cv_countdownViewTest4);
        long time4 = (long) 150 * 24 * 60 * 60 * 1000;
        mCvCountTimeViewTest4.start(time4);
        findViewById(R.id.bt_countUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CountUpActivity.class));
            }
        });
        findViewById(R.id.bt_countDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CountDownActivity.class));
            }
        });

    }

    @Override
    public void onEnd(CountTimeView cv) {
        Object tag = cv.getTag();
        if (null != tag) {
            Log.i("wg", "tag = " + tag.toString());
        }
    }


}


