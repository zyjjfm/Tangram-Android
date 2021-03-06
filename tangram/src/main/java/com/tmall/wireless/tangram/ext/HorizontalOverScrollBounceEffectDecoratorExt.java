/*
 * MIT License
 *
 * Copyright (c) 2018 Alibaba Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.tmall.wireless.tangram.ext;

import android.view.MotionEvent;
import android.view.View;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

/**
 * Created by Kunlun on 2/17/16.
 */
public class HorizontalOverScrollBounceEffectDecoratorExt extends HorizontalOverScrollBounceEffectDecorator {
    private OnOverScrollListener overScrollListener;
    private OnMotionEventListener motionEventListener;

    public HorizontalOverScrollBounceEffectDecoratorExt(IOverScrollDecoratorAdapter viewAdapter) {
        super(viewAdapter);
    }

    public HorizontalOverScrollBounceEffectDecoratorExt(IOverScrollDecoratorAdapter viewAdapter,
                                                        float touchDragRatioFwd, float touchDragRatioBck, float decelerateFactor) {
        super(viewAdapter, touchDragRatioFwd, touchDragRatioBck, decelerateFactor);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (motionEventListener != null) {
            motionEventListener.onMotionEvent(v, event);
        }
        return super.onTouch(v, event);
    }

    @Override
    protected void translateView(View view, float offset) {
        super.translateView(view, offset);
        if (overScrollListener != null) {
            overScrollListener.onOverScroll(view, offset);
        }
    }

    public void setOnOverScrollListener(OnOverScrollListener listener) {
        this.overScrollListener = listener;
    }

    public void setOnMotionEventListener(OnMotionEventListener listener) {
        this.motionEventListener = listener;
    }

    public interface OnOverScrollListener {
        void onOverScroll(View view, float offset);
    }

    public interface OnMotionEventListener {
        void onMotionEvent(View view, MotionEvent event);
    }
}
