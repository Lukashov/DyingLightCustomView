package com.cuctomviews.den.dyinglightcustomview;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
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

    private Coordinates mCoordinatesA;
    private Coordinates mCoordinatesB;
    private Coordinates mCoordinatesC;
    private Coordinates mCoordinatesD;

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

    public void init(){

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

        if (getWidth() < getHeight()){
            mSideSquare = getWidth();
        }else{
            mSideSquare = getHeight();
        }

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

        fillCoordinatesLists();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

// Draw small squares

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

        canvas.drawRect(mLeftTopSquare, mPaint);
        canvas.drawRect(mLeftBottomSquare, mPaint);
        canvas.drawRect(mRightTopSquare, mPaint);
        canvas.drawRect(mRightBottomSquare, mPaint);

// Draw six lines

        myDrawLine(canvas, mLeftSide, mPaint);
        myDrawLine(canvas, mTopSide, mPaint);
        myDrawLine(canvas, mRightSide, mPaint);
        myDrawLine(canvas, mBottomSide, mPaint);
        myDrawLine(canvas, mFirstDiagonal, mPaint);
        myDrawLine(canvas, mSecondDiagonal, mPaint);

//Draw big square in centre View
        canvas.drawRect(mCentreSquare, mRectPaint);

    }

    public void myDrawLine(Canvas canvas, Line _line, Paint paint){
        canvas.drawLine(_line.startX, _line.startY, _line.endX, _line.endY, paint);
    }

    public void fillCoordinatesLists(){

        int delta = mSideSquare - mSideSquare / 8;

        int aX = 0;
        int aY = 0;

        int bX = 0;
        int bY = 0;

        int cX = 0;
        int cY = 0;

        int dX = 0;
        int dY = 0;

        for (int i = 0; i < (15 * delta / 8) ; i++) {

            mCoordinatesA = new Coordinates();
            mCoordinatesB = new Coordinates();
            mCoordinatesC = new Coordinates();
            mCoordinatesD = new Coordinates();

            if(i >=0 && i < delta / 4){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aY++;
            }

            if(i >= (delta / 4) && i < (delta / 2)){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aY++; bX++;
            }

            if(i >= (delta / 2) && i < (3 * delta / 4)){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                bX++; cY++;
            }

            if(i >= (3 * delta / 4) && i < (7 * delta / 8)){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                cY++; dX++;
            }

            if(i >= (7 * delta / 8) && i < delta){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX++; cY++; dX++;
            }

            if(i >= delta && i < 9 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX++; bY++; dX++;
            }

            if(i >= 9 * delta / 8 && i < 5 * delta / 4){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX++; bY++; cX++; dX++;
            }

            if(i >= 5 * delta / 4 && i < 11 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX++; bY++; cX++;
            }

            if(i >= 11 * delta / 8 && i < 3 * delta / 2){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                bY++; cX++; dY++;
            }

            if(i >= 3 * delta / 2 && i < 13 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                cX++; dY++;
            }

            if(i >= 13 * delta / 8 && i < 15 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                dY++;
            }

            if(i == 15 * delta / 8){
                aX = 0; bX = 0; cX = 0; dX = 0;
                aY = 0; bY = 0; cY = 0; dY = 0;
            }
        }
    }

    private void offsetVertices(int _aX, int _aY, int _bX, int _bY, int _cX, int _cY, int _dX, int _dY) {

        mCoordinatesA.x = mRightBottomSquare.centerX() - _aX;
        mCoordinatesA.y = mRightBottomSquare.centerY() - _aY;
        mCoordinatesListPointA.add(mCoordinatesA);

        mCoordinatesB.x = mLeftBottomSquare.centerX() + _bX;
        mCoordinatesB.y = mLeftBottomSquare.centerY() - _bY ;
        mCoordinatesListPointB.add(mCoordinatesB);

        mCoordinatesC.x = mLeftTopSquare.centerX() + _cX;
        mCoordinatesC.y = mLeftTopSquare.centerY() + _cY;
        mCoordinatesListPointC.add(mCoordinatesC);

        mCoordinatesD.x = mRightTopSquare.centerX() - _dX;
        mCoordinatesD.y = mRightTopSquare.centerY() + _dY;
        mCoordinatesListPointD.add(mCoordinatesD);

    }

    public void moveRectangle(Rect _rect, int _centerX, int _centerY){

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

                    for (int i = 0; i < 4; i++) {
                        for (int j = 255; j >= 0; j--) {
                            Thread.sleep(3);
                            mRectPaint.setAlpha(j);
                            postInvalidate();
                        }
                        for (int k = 0; k < 255; k++) {
                            Thread.sleep(3);
                            mRectPaint.setAlpha(k);
                            postInvalidate();
                        }
                        Thread.sleep(15);
                    }
                    Thread.sleep(15);
                    for (int i = 255; i >= 0; i--) {
                        Thread.sleep(3);
                        mRectPaint.setAlpha(i);
                        postInvalidate();
                    }

                    for (int i = 0; i < mCoordinatesListPointB.size(); i++) {

                        Thread.sleep(4);

                        moveRectangle(mRightBottomSquare,
                                mCoordinatesListPointA.get(i).x,
                                mCoordinatesListPointA.get(i).y);

                        moveRectangle(mLeftBottomSquare,
                                mCoordinatesListPointB.get(i).x,
                                mCoordinatesListPointB.get(i).y);

                        moveRectangle(mLeftTopSquare,
                                mCoordinatesListPointC.get(i).x,
                                mCoordinatesListPointC.get(i).y);

                        moveRectangle(mRightTopSquare,
                                mCoordinatesListPointD.get(i).x,
                                mCoordinatesListPointD.get(i).y);

                        postInvalidate();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
