/**
 * Copyright 2014  XCL-Charts
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @Project XCL-Charts
 * @Description Android图表基类库
 * @author XiongChuanLiang<br />(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.5
 */
package com.wen.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import org.xclcharts.chart.PointD;
import org.xclcharts.chart.ScatterChart;
import org.xclcharts.chart.ScatterData;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.plot.PlotGrid;
import org.xclcharts.view.ChartView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ScatterChartView extends ChartView {
    private String TAG = "ScatterChartView";
    private ScatterChart chart = new ScatterChart();
    //分类轴标签集合
    private LinkedList<String> labels = new LinkedList<String>();
    private List<ScatterData> chartData = new LinkedList<ScatterData>();
    private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int axisMax = 100, axisMin = 0, axisStep = 10;
    private int labelMax = 6, labelMin = 0;

    public ScatterChartView(Context context) {
        super(context);
        initView();
    }

    public ScatterChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScatterChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        chartLabels();
        chartDataSet();
        chartRender();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        chart.setChartRange(w, h);
    }


    private void chartRender() {
        try {
            //设置绘图区默认缩进px值,留置空间显示
            int dip = DensityUtil.dip2px(getContext(), 40);
            chart.setPadding(30, dip, 30, dip);

            //数据源
            chart.setCategories(labels);
            chart.setDataSource(chartData);

            //坐标系
            //数据轴最大值
            chart.getDataAxis().setAxisMax(axisMax);
            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(axisStep);
            chart.getDataAxis().setAxisMin(axisMin);
            //标签轴最大值
            chart.setCategoryAxisMax(labelMax);
            //标签轴最小值
            chart.setCategoryAxisMin(labelMin);

            chart.getDataAxis().setHorizontalTickAlign(Align.LEFT);
            chart.getDataAxis().getTickLabelPaint().setTextAlign(Align.CENTER);

            chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack() {
                @Override
                public String textFormatter(String value) {
                    int m = 0;
                    try {
                        double abc = Double.parseDouble(value);
                        m = (int) abc;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    return m + "\u3000\u3000\u3000\u3000\u3000";
                }
            });
            chart.getDataAxis().getAxisPaint().setColor(Color.rgb(127, 204, 204));
            chart.getCategoryAxis().getAxisPaint().setColor(Color.rgb(127, 204, 204));
            chart.getDataAxis().getTickMarksPaint().setColor(Color.rgb(127, 204, 204));
            chart.getCategoryAxis().getTickMarksPaint().setColor(Color.rgb(127, 204, 204));
            chart.getDataAxis().getTickLabelPaint().setTextAlign(Align.LEFT);

            //定义交叉点标签显示格式,特别备注,因曲线图的特殊性，所以返回格式为:  x值,y值
            //请自行分析定制
            chart.setDotLabelFormatter(new IFormatterTextCallBack() {

                @Override
                public String textFormatter(String value) {
                    String label = "(" + value + ")";
                    return (label);
                }

            });
            //激活点击监听
            chart.ActiveListenItemClick();
            //为了让触发更灵敏，可以扩大5px的点击监听范围
            chart.extPointClickRange(10);
            chart.getPointPaint().setStrokeWidth(4);
            //显示十字交叉线
            chart.showDyLine();
            chart.getDyLine().setDyLineStyle(XEnum.DyLineStyle.BackwardDiagonal);
            chart.disableScale();

            //不使用精确计算，忽略Java计算误差
            chart.disableHighPrecision();

            PlotGrid plot = chart.getPlotGrid();
            plot.showHorizontalLines();
            plot.showVerticalLines();
            plot.getHorizontalLinePaint().setStrokeWidth(3);
            plot.getHorizontalLinePaint().setColor(Color.argb(100, 127, 204, 204));
            plot.setHorizontalLineStyle(XEnum.LineStyle.DASH);

            //把轴线设成和横向网络线一样和大小和颜色
            chart.getDataAxis().getAxisPaint().setStrokeWidth(
                    plot.getHorizontalLinePaint().getStrokeWidth());
            chart.getCategoryAxis().getAxisPaint().setStrokeWidth(
                    plot.getHorizontalLinePaint().getStrokeWidth());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        }
    }

    private void chartDataSet() {
        //线1的数据集
        ArrayList<PointD> linePoint1 = new ArrayList<PointD>();
        ScatterData dataSeries1 = new ScatterData("", linePoint1, Color.rgb(54, 141, 238), XEnum.DotStyle.DOT);
        dataSeries1.setLabelVisible(false);
        dataSeries1.getDotLabelPaint().setColor(Color.rgb(191, 79, 75));
        //设定数据源
        chartData.add(dataSeries1);
    }

    /**
     * 修改数据，会将原来的清除
     *
     * @param list
     */
    public void updateData(ArrayList<PointD> list) {
        chartData.clear();
        addData(list);
    }

    /**
     * 新增数据，会保留之前的数据
     *
     * @param list
     */
    public void addData(ArrayList<PointD> list) {
        ScatterData dataSeries = new ScatterData("", list, Color.rgb(54, 141, 238), XEnum.DotStyle.DOT);
        dataSeries.setLabelVisible(false);
        dataSeries.getDotLabelPaint().setColor(Color.rgb(191, 79, 75));
        chartData.add(dataSeries);
        invalidate();
    }

    /**
     * 清空数据
     */
    public void removeData() {
        chartData.clear();
        chart.setDataSource(chartData);
        invalidate();
    }

    public void setHorizontalLabels(List<String> labels) {
        if (labels != null) {
            labels.addAll(labels);
        }
    }
    // //坐标系
    //            //数据轴最大值
    //            chart.getDataAxis().setAxisMax(100);
    //            //数据轴刻度间隔
    //            chart.getDataAxis().setAxisSteps(10);
    //            chart.getDataAxis().setAxisMin(0);
    //            //标签轴最大值
    //            chart.setCategoryAxisMax(6);
    //            //标签轴最小值
    //            chart.setCategoryAxisMin(0);



    private void chartLabels() {
        labels.add("0");
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
    }

    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
//            triggerClick(event.getX(), event.getY());
        }
        return true;
    }


    //触发监听
    private void triggerClick(float x, float y) {
        //交叉线
        if (chart.getDyLineVisible()) chart.getDyLine().setCurrentXY(x, y);
        if (!chart.getListenItemClickStatus()) {
            if (chart.getDyLineVisible() && chart.getDyLine().isInvalidate()) this.invalidate();
        } else {
            PointPosition record = chart.getPositionRecord(x, y);
            if (null == record) return;

            ScatterData lData = chartData.get(record.getDataID());
            List<PointD> linePoint = lData.getDataSet();
            int pos = record.getDataChildID();
            int i = 0;
            Iterator it = linePoint.iterator();
            while (it.hasNext()) {
                PointD entry = (PointD) it.next();

                if (pos == i) {
                    Double xValue = entry.x;
                    Double yValue = entry.y;

                    //在点击处显示tooltip
                    mPaintTooltips.setColor(Color.RED);
                    chart.getToolTip().setCurrentXY(x, y);
                    chart.getToolTip().addToolTip(" Key:" + lData.getKey(), mPaintTooltips);
                    chart.getToolTip().addToolTip(
                            " Current Value:" + Double.toString(xValue) + "," + Double.toString(yValue), mPaintTooltips);
                    this.invalidate();
                    break;
                }
                i++;
            }
        }

    }

}
