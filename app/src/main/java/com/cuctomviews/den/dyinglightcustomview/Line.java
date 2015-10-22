package com.cuctomviews.den.dyinglightcustomview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by Den on 20.10.15.
 */
public class Line {
    public int startX;
    public int startY;
    public int endX;
    public int endY;

    public void setLine(int _startX, int _startY, int _endX, int _endY){
        this.startX = _startX;
        this.startY = _startY;
        this.endX = _endX;
        this.endY = _endY;
    }
}
