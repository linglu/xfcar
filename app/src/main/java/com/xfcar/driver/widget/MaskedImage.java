package com.xfcar.driver.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 *
 */
public class MaskedImage extends AppCompatImageView {
    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;
    private MaskShape mMaskShape = new CircleMask();    // 默认形状为圆形

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }

    public MaskedImage(Context paramContext) {
        super(paramContext);
    }

    public MaskedImage(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public MaskedImage(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public void setMaskShape(MaskShape shape) {
        if (shape == null) {
            throw new NullPointerException("shape can't be null ");
        }
        this.mMaskShape = shape;
    }

    protected void onDraw(Canvas paramCanvas) {
        Drawable localDrawable = getDrawable();
        if (localDrawable == null) {
            super.onDraw(paramCanvas);
            return;
        }

        try {
            if (this.paint == null) {
                Paint localPaint1 = new Paint();
                this.paint = localPaint1;
                this.paint.setFilterBitmap(false);
                Paint localPaint2 = this.paint;
                Xfermode localXfermode1 = MASK_XFERMODE;
                @SuppressWarnings("unused")
                Xfermode localXfermode2 = localPaint2.setXfermode(localXfermode1);
            }

            int startX = getPaddingLeft();
            int startY = getPaddingTop();
            int endX = getWidth() - getPaddingRight();
            int endY = getHeight() - getPaddingBottom();
            int i = paramCanvas.saveLayer(startX, startY, endX, endY, null, 31);
            localDrawable.setBounds(startX, startY, endX, endY);
            localDrawable.draw(paramCanvas);
            if ((this.mask == null) || (this.mask.isRecycled())) {
                Bitmap localBitmap1 = mMaskShape.createMask();
                this.mask = localBitmap1;
            }
            Bitmap localBitmap2 = this.mask;
            Paint localPaint3 = this.paint;
            paramCanvas.drawBitmap(localBitmap2, startX, startY, localPaint3);
            paramCanvas.restoreToCount(i);
            return;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public class CircleMask implements MaskShape {
        public Bitmap createMask() {
            int startX = getPaddingLeft();
            int startY = getPaddingTop();
            int endX = getWidth() - getPaddingRight();
            int endY = getHeight() - getPaddingBottom();
            int width = endX - startX;
            int height = endY - startY;
            Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
            Bitmap localBitmap = Bitmap.createBitmap(width, height, localConfig);
            Canvas localCanvas = new Canvas(localBitmap);
            Paint localPaint = new Paint(1);
            localPaint.setColor(-16777216);
            RectF localRectF = new RectF(0.0F, 0.0F, width, height);
            localCanvas.drawOval(localRectF, localPaint);
            return localBitmap;
        }
    }


    public interface MaskShape {
        public Bitmap createMask();
    }
}