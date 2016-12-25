package com.steve.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.steve.lib.AdapterItem;
import com.steve.lib.DatePriceAdapterBuilder;
import com.steve.lib.DatePriceVO;
import com.steve.lib.basic.BaseDatePriceAdapter;
import com.steve.lib.basic.DatePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DatePickerView datePickerView = (DatePickerView) findViewById(R.id.daypicker);
        ArrayMap<String, DatePriceVO> datas = new ArrayMap<>();

        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < 100; i++) {
            DatePriceVO vo = new DatePriceVO();
            String date = formatDate(cal);
            vo.setDate(date);
            vo.setPrice("¥" + i);
            vo.setStock(i);
            datas.put(date, vo);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        DatePriceAdapterBuilder builder = new DatePriceAdapterBuilder();
        String selectedDate = "2016-12-30";
        builder.withSelected(selectedDate).withOnItemClickListener(new DatePriceAdapterBuilder.OnItemClickListener() {
            @Override
            public void onClick(AdapterItem item) {
                Toast.makeText(MainActivity.this, "click" + item.getDate(), Toast.LENGTH_SHORT).show();
            }
        }).getDayAdapter(datas, new Subscriber<BaseDatePriceAdapter>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseDatePriceAdapter baseDatePriceAdapter) {
                datePickerView.setAdapter(baseDatePriceAdapter);
                datePickerView.setScrollPosition(baseDatePriceAdapter.getScrollPosition());
            }
        });

    }


    private String formatDate(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = cal.getTime();
        return sdf.format(date);
    }
}
