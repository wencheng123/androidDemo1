//package com.wen.demo;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.util.AttributeSet;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
//import java.util.List;
//
///***
// * @Author wen
// * @Date 2023/4/3 14:37
// * @Desc
// *
// ***/
//public class ScatterView  extends View {
//    private int axisColor;		// 轴线颜色
//    private float axisWith;     // 轴线宽度
//    private int[] dotColor; 	// 数据点颜色
//    private int[] dotWidth;	    // 数据点大小
//    private int textColor;      // 文本颜色
//    private int textSize;      	// 文本字体
//    private int leftMargins;    // 左边距
//    private int rightMargins;  	// 右边距
//    private int bottomMargins;  // 下边距
//    private int topMargins;    	// 上边距
//    private float XScale;       // X的刻度长度
//    private int xLength;        // X轴的长度
//    private int xMax;           // X轴最大值
//    private int YScale;         // Y的刻度长度
//    private int yLength;        // Y轴的长度
//    private int yMax;           // Y轴最大值
//    private List<int[]> yData;  // y轴数据
//    private List<int[]> xData;	// x轴数据
//    private boolean hasYAxis;  	// 显示Y轴轴线
//    private boolean hasXAxis;  	// 显示X轴轴线
//    private boolean hasYScale;  // 显示Y轴刻度
//    private boolean hasXScale;  // 显示X轴刻度
//    private boolean showData;	// 显示数据值
//    private Paint axisPaint, textPaint, dotPaint;
//    private int[] colors = new int[]{Color.BLUE, Color.CYAN, Color.RED, Color.GREEN, Color.YELLOW};
//    private int[] dotType = new int[]{ 1, 2 };  	// 数据点类型
//
//    public ScatterView(Context context) {
//        this(context,null);
//    }
//
//    public ScatterView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//        initPaint();
//    }
//
//    //构造函数
//    public ScatterView(Context context, List<int []> yData, List<int []> xData){
//        super(context);
//        setData(yData, xData);
//        setDotColor(colors);
//    }
//
//
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
//        if (null!= yData  && null!= xData) {
//            init();
//            initPaint();
//            axisDraw(canvas);
//            Draw(canvas);
//        }
//    }
//
//    //绘制散点图
//    private void Draw(Canvas canvas){
//
//        int len = yData.size();
//        for (int j = 0; j < len; j++){
//            int []y = yData.get(j);
//            int []x = xData.get(j);
//            dotPaint.setColor(getDotColor()[j]);
//            if ( j % 2 == 0)
//                dotPaint.setStyle(Paint.Style.FILL);
//            else
//                dotPaint.setStyle(Paint.Style.STROKE);
//            for (int i = 0; i < y.length; i ++) {
//                canvas.drawCircle(XCoord(x[i]), YCoord(y[i]), 5, dotPaint);
//            }
//        }
//    }
//
//    //绘制坐标系
//    private void axisDraw(Canvas canvas){
//        if (isHasYAxis()) {
//            canvas.drawLine(leftMargins, topMargins + yLength,
//                    leftMargins,topMargins, axisPaint);
//        }
//        if (isHasXAxis()) {
//            canvas.drawLine(leftMargins, topMargins + yLength,
//                    xLength + leftMargins, topMargins + yLength, axisPaint);
//        }
//        for (int i = 0; i <= 5; i ++){
//            if (i < 5)
//                canvas.drawLine(leftMargins - 5, i * yLength / 5f + topMargins,
//                        xLength + leftMargins, i * yLength / 5f + topMargins, axisPaint);
//
//            canvas.drawText(String.valueOf(yMax * (5 - i ) / 5), leftMargins - 20,
//                    i * yLength / 5f + topMargins + 5, textPaint);
//        }
//        for (int i = 0; i <= 3; i ++){
//            if (i > 0)
//                canvas.drawLine(leftMargins +  i * xLength / 3f, topMargins,
//                        leftMargins + i * xLength / 3f, topMargins + yLength +5, axisPaint);
//            canvas.drawText(String.valueOf(xMax * i / 3), leftMargins + i * xLength / 3f,
//                    topMargins + yLength + 25, textPaint);
//        }
//    }
//
//    private void init(){
//        //初始化绘图范围
//        setLeftMargins(50);
//        setRightMargins(50);
//        setTopMargins(50);
//        setBottomMargins(50);
//        yLength = getHeight() - bottomMargins - topMargins;
//        xLength = getWidth() - leftMargins - rightMargins;
//        xMax = getMax(xData);
//        yMax = getMax(yData);
//
//        //初始化轴显示
//        setHasXAxis(true);
//        setHasYAxis(true);
//        setHasXScale(true);
//        setHasYData(true);
//
//        //初始化轴线画笔
//        axisPaint = new Paint();
//        axisPaint.setStyle(Paint.Style.STROKE);
//        axisPaint.setAntiAlias(true);
//        axisPaint.setColor(Color.GRAY);
//        axisPaint.setStrokeWidth(1);
//
//        //初始化数据点画笔
//        dotPaint = new Paint();
//        dotPaint.setStyle(Paint.Style.STROKE);
//        dotPaint.setAntiAlias(true);
//        dotPaint.setColor(Color.BLACK);
//
//        //初始化文本画笔
//        textPaint = new Paint();
//        textPaint.setStyle(Paint.Style.STROKE);
//        textPaint.setAntiAlias(true);
//        textPaint.setColor(Color.BLACK);
//        textPaint.setTextSize(11);
//        textPaint.setTextAlign(Paint.Align.RIGHT);
//    }
//
//    //设置画笔
//    private void initPaint() {
//        if (getAxisColor() != 0)
//            axisPaint.setColor(getAxisColor());
//        if (getAxisWidth() != 0)
//            axisPaint.setStrokeWidth(getAxisWidth());
//        if (getTextColor() != 0)
//            textPaint.setColor(getTextColor());
//        if (getTextSize() != 0)
//            textPaint.setTextSize(getTextSize());
//    }
//
//    //定位
//    private int YCoord(int y){
//        return (int) (yLength + topMargins - y * (yLength / (float) yMax));
//    }
//
//    private int XCoord(int x){
//        return (int) (leftMargins + x * (xLength / (float) xMax));
//    }
//
//    //获取图例最大值
//    private int getMax(List<int []> data){
//        int max = 0;
//        for (int j = 0; j< data.size(); j++)
//        {
//            int id = 0;
//            int[] temp = data.get(j);
//            for (int i = 1; i < temp.length; i++){
//                if (temp[i] > temp[id])
//                    id = i;
//            }
//            if (temp [id] > max)
//                max = temp[id];
//        }
//        int i=0;
//        while (max > 10){
//            max /= 10;
//            i ++;
//        }
//        return (int) ((max + 1) * Math.pow(10, i));
//
//    }
//
//    //数据设置
//    public int getYData(int i, int j) {
//        return yData.get(i)[j];
//    }
//
//    public int getXData(int i, int j) {
//        return xData.get(i)[j];
//    }
//
//    public void setData(List<int[]> yData,List<int[]> xData) {
//        this.yData = yData;
//        this.xData = xData;
//    }
//
//    //轴线设置
//    public int getAxisColor() {
//        return axisColor;
//    }
//
//    public void setAxisolor(int axisColor) {
//        this.axisColor = axisColor;
//    }
//
//    public float getAxisWidth() {
//        return axisWith;
//    }
//
//    public void setAxisWidth(int axisWith) {
//        this.axisWith = axisWith;
//    }
//
//    //数据点设置
//    public void setDotColor(int[] dotColor) {
//        this.dotColor = dotColor;
//    }
//
//    public int[] getDotColor() {
//        return dotColor;
//    }
//
//    public void setDotStyle(int i) {
//        switch (dotColor[i]){
//            case 1: dotPaint.setStyle(Paint.Style.FILL);
//                break;
//            case 2: dotPaint.setStyle(Paint.Style.STROKE);
//                break;
//        }
//    }
//
//    //X轴设置
//    public boolean isHasXAxis() {
//        return hasXAxis;
//    }
//
//    public void setHasXAxis(boolean hasXAxis) {
//        this.hasXAxis = hasXAxis;
//    }
//
//    public float getXScale() {
//        return XScale;
//    }
//
//    public void setXScale(float xScale) {
//        XScale = xScale;
//    }
//
//    public int getXLength() {
//        return xLength;
//    }
//
//    public void setXLength(int xLength) {
//        this.xLength = xLength;
//    }
//
//    public boolean isHasXScale() {
//        return hasXScale;
//    }
//
//    public void setHasXScale(boolean hasXScale) {
//        this.hasXScale = hasXScale;
//    }
//
//    //Y轴设置
//    public boolean isHasYAxis() {
//        return hasYAxis;
//    }
//
//    public void setHasYAxis(boolean hasYAxis) {
//        this.hasYAxis = hasYAxis;
//    }
//
//    public int getYScale() {
//        return YScale;
//    }
//
//    public void setYScale(int yScale) {
//        YScale = yScale;
//    }
//
//    public int getYLength() {
//        return yLength;
//    }
//
//    public void setYLength(int yLength) {
//        this.yLength = yLength;
//    }
//
//    public boolean isHasYScale() {
//        return hasYScale;
//    }
//
//    public void setHasYData(boolean hasYScale) {
//        this.hasYScale = hasYScale;
//    }
//
//    //文本设置
//    public int getTextSize() {
//        return textSize;
//    }
//
//    public void setTextSize(int textSize) {
//        this.textSize = textSize;
//    }
//
//    public int getTextColor() {
//        return textColor;
//    }
//
//    public void setTextColor(int textColor) {
//        this.textColor = textColor;
//    }
//
//    //边距设置
//    public int getLeftMargins() {
//        return leftMargins;
//    }
//
//    public void setLeftMargins(int leftMargins) {
//        this.leftMargins = leftMargins;
//    }
//
//    public int getRightMargins() {
//        return rightMargins;
//    }
//
//    public void setRightMargins(int rightMargins) {
//        this.rightMargins = rightMargins;
//    }
//
//    public int getBottomMargins() {
//        return bottomMargins;
//    }
//
//    public void setBottomMargins(int buttomMargins) {
//        this.bottomMargins = buttomMargins;
//    }
//
//    public int getTopMargins() {
//        return topMargins;
//    }
//
//    public void setTopMargins(int topMargins) {
//        this.topMargins = topMargins;
//    }
//
//}
