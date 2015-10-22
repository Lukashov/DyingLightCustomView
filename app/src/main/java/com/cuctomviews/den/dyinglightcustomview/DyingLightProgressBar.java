package com.cuctomviews.den.dyinglightcustomview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Den on 19.10.15.
 */
public class DyingLightProgressBar extends View {

    private Paint mPaint, mRectPaint;
    private Rect mCentreSquare, mLeftTopSquare, mLeftBottomSquare, mRightTopSquare, mRightBottomSquare;
    private Line mLeftSide, mTopSide, mRightSide, mBottomSide, mFirstDiagonal, mSecondDiagonal;

    private  int mSideSquare;

    private List<Coordinates> mCoordinatesListPointA;
    private List<Coordinates> mCoordinatesListPointB;
    private List<Coordinates> mCoordinatesListPointC;
    private List<Coordinates> mCoordinatesListPointD;

    private CoordinateUtils mCoordinateUtils;

    public DyingLightProgressBar(Context context) {
        super(context);
        init();
    }

    public DyingLightProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DyingLightProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DyingLightProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(0xFF6176EC);
        mRectPaint.setColor(0xFF6176EC);

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setAntiAlias(true);
        mRectPaint.setStyle(Paint.Style.FILL);

        mCentreSquare = new Rect();
        mLeftTopSquare = new Rect();
        mLeftBottomSquare = new Rect();
        mRightTopSquare = new Rect();
        mRightBottomSquare = new Rect();

        mLeftSide = new Line();
        mTopSide = new Line();
        mRightSide = new Line();
        mBottomSide = new Line();
        mFirstDiagonal = new Line();
        mSecondDiagonal = new Line();

        mCoordinatesListPointA = new ArrayList<>();
        mCoordinatesListPointB = new ArrayList<>();
        mCoordinatesListPointC = new ArrayList<>();
        mCoordinatesListPointD = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mSideSquare = getSideParentSquare();

        setPointsToSquares();

        fillCoordinateLists();
    }

    private int getSideParentSquare() {

        int side;

        if (getWidth() < getHeight()){
            side = getWidth();
        }else{
            side = getHeight();
        }

        return side;
    }

    private void fillCoordinateLists() {
        mCoordinateUtils = new CoordinateUtils(mLeftTopSquare,
                mLeftBottomSquare, mRightTopSquare, mRightBottomSquare);

        mCoordinateUtils.fillCoordinateLists(mSideSquare);

        mCoordinatesListPointA = mCoordinateUtils.getCoordinatesListPointA();
        mCoordinatesListPointB = mCoordinateUtils.getCoordinatesListPointB();
        mCoordinatesListPointC = mCoordinateUtils.getCoordinatesListPointC();
        mCoordinatesListPointD = mCoordinateUtils.getCoordinatesListPointD();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setPointsToLines();

        drawSquares(canvas);

        drawSidesAndDiagonals(canvas);
    }

    private void setPointsToSquares() {
        mCentreSquare.set(mSideSquare / 2 - mSideSquare / 4,
                mSideSquare / 2 - mSideSquare / 4,
                mSideSquare / 2 + mSideSquare / 4,
                mSideSquare / 2 + mSideSquare / 4);

        mLeftTopSquare.set(0, 0, mSideSquare / 8, mSideSquare / 8);

        mLeftBottomSquare.set(0, mSideSquare - mSideSquare / 8, mSideSquare / 8, mSideSquare);

        mRightTopSquare.set(mSideSquare - mSideSquare / 8, 0, mSideSquare, mSideSquare / 8);

        mRightBottomSquare.set(mSideSquare - mSideSquare / 8,
                mSideSquare - mSideSquare / 8,
                mSideSquare,
                mSideSquare);
    }

    private void setPointsToLines() {
        mLeftSide.setLine(mLeftTopSquare.centerX(),
                mLeftTopSquare.centerY(),
                mLeftBottomSquare.centerX(),
                mLeftBottomSquare.centerY());

        mTopSide.setLine(mLeftTopSquare.centerX(),
                mLeftTopSquare.centerY(),
                mRightTopSquare.centerX(),
                mRightTopSquare.centerY());

        mRightSide.setLine(mRightTopSquare.centerX(),
                mRightTopSquare.centerY(),
                mRightBottomSquare.centerX(),
                mRightBottomSquare.centerY());

        mBottomSide.setLine(mRightBottomSquare.centerX(),
                mRightBottomSquare.centerY(),
                mLeftBottomSquare.centerX(),
                mLeftBottomSquare.centerY());

        mFirstDiagonal.setLine(mLeftTopSquare.centerX(),
                mLeftTopSquare.centerY(),
                mRightBottomSquare.centerX(),
                mRightBottomSquare.centerY());

        mSecondDiagonal.setLine(mRightTopSquare.centerX(),
                mRightTopSquare.centerY(),
                mLeftBottomSquare.centerX(),
                mLeftBottomSquare.centerY());
    }

    private void drawSquares(Canvas _canvas) {
        _canvas.drawRect(mLeftTopSquare, mPaint);
        _canvas.drawRect(mLeftBottomSquare, mPaint);
        _canvas.drawRect(mRightTopSquare, mPaint);
        _canvas.drawRect(mRightBottomSquare, mPaint);

        _canvas.drawRect(mCentreSquare, mRectPaint);
    }

    private void drawSidesAndDiagonals(Canvas _canvas) {
        myDrawLine(_canvas, mLeftSide, mPaint);
        myDrawLine(_canvas, mTopSide, mPaint);
        myDrawLine(_canvas, mRightSide, mPaint);
        myDrawLine(_canvas, mBottomSide, mPaint);
        myDrawLine(_canvas, mFirstDiagonal, mPaint);
        myDrawLine(_canvas, mSecondDiagonal, mPaint);
    }

    private void myDrawLine(Canvas _canvas, Line _line, Paint _paint){
        _canvas.drawLine(_line.startX, _line.startY, _line.endX, _line.endY, _paint);
    }

    private void moveRectangle(Rect _rect, int _centerX, int _centerY){

        int halfSideSmallSquare = mSideSquare / 16;

        _rect.left = _centerX - halfSideSmallSquare;
        _rect.top = _centerY - halfSideSmallSquare;
        _rect.right = _centerX + halfSideSmallSquare;
        _rect.bottom = _centerY + halfSideSmallSquare;

    }

    public void startAnim(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    for (int n = 0; n < 3; n++) {

                        changeAlpha(mRectPaint);

                        int j;

                        for (int i = 0; i < mCoordinatesListPointA.size() + 5; i++) {

                            if(i == 0 && i < 5){
                                changeAlpha(mRectPaint);
                            }

                            if(i >= 5){

                                j = i - 5;

                                Thread.sleep(4);

                                moveRectangle(mRightBottomSquare,
                                        mCoordinatesListPointA.get(j).x,
                                        mCoordinatesListPointA.get(j).y);

                                moveRectangle(mLeftBottomSquare,
                                        mCoordinatesListPointB.get(j).x,
                                        mCoordinatesListPointB.get(j).y);

                                moveRectangle(mLeftTopSquare,
                                        mCoordinatesListPointC.get(j).x,
                                        mCoordinatesListPointC.get(j).y);

                                moveRectangle(mRightTopSquare,
                                        mCoordinatesListPointD.get(j).x,
                                        mCoordinatesListPointD.get(j).y);

                                if(j == (15 * (mSideSquare - mSideSquare / 8) / 8 ) ){
                                    changeAlpha(mPaint);
                                    mPaint.setAlpha(255);
                                }
                            }

                            postInvalidate();
                        }
                        mRectPaint.setAlpha(100);
                        postInvalidate();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void changeAlpha(Paint _paint) throws InterruptedException {
//        for (int i = 0; i < 1; i++) {
            for (int j = 255; j >= 0; j--) {
                Thread.sleep(3);
                _paint.setAlpha(j);
                postInvalidate();
            }
            for (int k = 0; k < 255; k++) {
                Thread.sleep(3);
                _paint.setAlpha(k);
                postInvalidate();
            }
//        }
        for (int i = 255; i >= 0; i--) {
            Thread.sleep(3);
            _paint.setAlpha(i);
            postInvalidate();
        }
    }
}
