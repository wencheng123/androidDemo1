package com.wen.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.xclcharts.chart.PointD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GlActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl);

        ScatterChart chart = findViewById(R.id.chart);

        chart.getDescription().setEnabled(false);
//        chart.setOnChartValueSelectedListener(this);

        chart.setDrawGridBackground(false);
        chart.setTouchEnabled(true);
        chart.setMaxHighlightDistance(50f);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        chart.setMaxVisibleValueCount(200);
        chart.setPinchZoom(true);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXOffset(5f);

        YAxis yl = chart.getAxisLeft();
        yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xl = chart.getXAxis();
        xl.setDrawGridLines(false);

        ArrayList<Entry> values1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            float val = (float) (Math.random() * 100) + 3;
            values1.add(new Entry(i, val));
        }

        // create a dataset and give it a type
        ScatterDataSet set1 = new ScatterDataSet(values1, "DS 1");
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);

        set1.setScatterShapeSize(8f);

        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1); // add the data sets

        // create a data object with the data sets
        ScatterData data = new ScatterData(dataSets);

        chart.setData(data);
        chart.invalidate();


        List<int[]> yData = new ArrayList<>();
        List<int[]> xData = new ArrayList<>();

        int[] y1 = new int[]{10, 56};
        int[] y2 = new int[]{56, 20};
        int[] y3 = new int[]{100, 25};
        yData.add(y1);
        yData.add(y2);
        yData.add(y3);

        int[] x1 = new int[]{11, 20};
        int[] x2 = new int[]{10, 100};
        int[] x3 = new int[]{22, 55};
        xData.add(x1);
        xData.add(x2);
        xData.add(x3);

//
        FrameLayout frameLayout = findViewById(R.id.scatter);
//        ScatterView scatterView = new ScatterView(this,yData,xData);
//        frameLayout.addView(scatterView);
//        scatterView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
//        scatterView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//        scatterView.setTextSize(30);
//        scatterView.setXScale(10);
//        scatterView.setYScale(10);

        chartView = new ScatterChartView(this);
        frameLayout.addView(chartView);
        chartView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        chartView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;


        findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });


        findViewById(R.id.bt_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();

            }
        });

        findViewById(R.id.bt_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chartView.removeData();
            }
        });

    }

    ScatterChartView chartView;

    private void add() {
        //线1的数据集
        ArrayList<PointD> linePoint1 = new ArrayList<PointD>();
        //测试数据
        for (int i = 0; i < 10; i++) {
            int i1 = new Random().nextInt(7);
            int i2 = new Random().nextInt(100);
            linePoint1.add(new PointD((double) i1, (double) i2));
        }
        chartView.addData(linePoint1);
    }


    private void update() {
        //线1的数据集
        ArrayList<PointD> linePoint1 = new ArrayList<PointD>();
        //测试数据
        for (int i = 0; i < 10; i++) {
            int i1 = new Random().nextInt(7);
            int i2 = new Random().nextInt(100);
            linePoint1.add(new PointD((double) i1, (double) i2));
        }
        chartView.updateData(linePoint1);
    }
}