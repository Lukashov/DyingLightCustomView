package com.cuctomviews.den.dyinglightcustomview;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Den on 22.10.15.
 */
public class CoordinateUtils {

    private List<Coordinates> mCoordinatesListPointA = new ArrayList<>();
    private List<Coordinates> mCoordinatesListPointB = new ArrayList<>();
    private List<Coordinates> mCoordinatesListPointC = new ArrayList<>();
    private List<Coordinates> mCoordinatesListPointD = new ArrayList<>();

    private Coordinates mCoordinatesA;
    private Coordinates mCoordinatesB;
    private Coordinates mCoordinatesC;
    private Coordinates mCoordinatesD;

    private Rect mCentreSquare, mLeftTopSquare, mLeftBottomSquare, mRightTopSquare, mRightBottomSquare;

    public CoordinateUtils(Rect _leftTopSquare, Rect _leftBottomSquare, Rect _rightTopSquare, Rect _rightBottomSquare) {
        mLeftTopSquare = _leftTopSquare;
        mLeftBottomSquare = _leftBottomSquare;
        mRightTopSquare = _rightTopSquare;
        mRightBottomSquare = _rightBottomSquare;
    }

    public void fillCoordinateLists(int _side){

        int delta = _side - _side / 8;

        int aX = 0;
        int aY = 0;

        int bX = 0;
        int bY = 0;

        int cX = 0;
        int cY = 0;

        int dX = 0;
        int dY = 0;

        for (int i = 0; i < (4 * delta) ; i++) {

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

            if(i >= 15 * delta / 8 && i < 17 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX--;
            }

            if(i >= 17 * delta / 8 && i < 19 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aX--; bY--;
            }

            if(i >= 19 * delta / 8 && i < 21 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                bY--; cX--;
            }

            if(i >= 21 * delta / 8 && i < 23 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                cX--; dY--;
            }

            if(i >= 23 * delta / 8 && i < 25 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                dY--; aY--;
            }

            if(i >= 25 * delta / 8 && i < 13 * delta / 4){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aY--;
            }

            if(i >= 13 * delta / 4 && i < 27 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                aY--; bX--;
            }

            if(i >= 27 * delta / 8 && i < 7 * delta / 2){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                bX--; cY--;
            }

            if(i >= 7 * delta / 2 && i < 15 * delta / 4){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                bX--; cY--; dX--;
            }

            if(i >= 15 * delta / 4 && i < 31 * delta / 8){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                cY--; dX--;
            }

            if(i >= 31 * delta / 8 && i < 4 * delta){
                offsetVertices(aX, aY, bX, bY, cX, cY, dX, dY);

                dX--;
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

    public List<Coordinates> getCoordinatesListPointA() {
        return mCoordinatesListPointA;
    }

    public List<Coordinates> getCoordinatesListPointB() {
        return mCoordinatesListPointB;
    }

    public List<Coordinates> getCoordinatesListPointC() {
        return mCoordinatesListPointC;
    }

    public List<Coordinates> getCoordinatesListPointD() {
        return mCoordinatesListPointD;
    }
}
