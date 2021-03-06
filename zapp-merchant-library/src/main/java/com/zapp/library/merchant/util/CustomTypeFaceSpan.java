/*
 * Copyright (c) 2020 Mastercard
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.zapp.library.merchant.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

/**
 * Custom typeFaceSpan
 */
public final class CustomTypeFaceSpan extends TypefaceSpan {
    private final Typeface newType;

    CustomTypeFaceSpan(@NonNull final Typeface type) {
        this("", type);
    }

    private CustomTypeFaceSpan(@NonNull final String family, @NonNull final Typeface type) {
        super(family);
        newType = type;
    }

    @Override
    public void updateDrawState(@NonNull final TextPaint textPaint) {
        applyCustomTypeFace(textPaint, newType);
    }

    @Override
    public void updateMeasureState(@NonNull final TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    private static void applyCustomTypeFace(@NonNull final Paint paint, @NonNull final Typeface tf) {
        final int oldStyle;
        final Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        final int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }
}
