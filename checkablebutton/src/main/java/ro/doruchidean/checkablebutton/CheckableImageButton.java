package ro.doruchidean.checkablebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CheckableImageButton extends BaseCheckableButton {

    public ImageView button;
    public ImageView ivCheck;
    private boolean isChecked;

    public CheckableImageButton(@NonNull Context context,
                                @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initUI(Context context) {
        LayoutInflater.from(context).inflate(
                R.layout.view_checkable_image_button, this, true);
        button = findViewById(R.id.btn_checkable_image);
        ivCheck = findViewById(R.id.iv_check);
    }

    @Override
    protected void setupAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray attrsArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CheckableImageButton, 0, 0);
        int imageResId;
        boolean isChecked;
        int scaleType;
        int buttonHeight;
        int ivCheckIcon;
        int background;
        try {
            imageResId = attrsArray.getResourceId(R.styleable.CheckableImageButton_src, 0);
            isChecked = attrsArray.getBoolean(R.styleable.CheckableImageButton_isChecked, false);
            scaleType = attrsArray.getInt(R.styleable.CheckableImageButton_imageScaleType, -1);
            buttonHeight = attrsArray.getDimensionPixelSize(R.styleable.CheckableImageButton_buttonHeight, -1);
            ivCheckIcon = attrsArray.getResourceId(R.styleable.CheckableImageButton_cornerIcon, -1);
            background = attrsArray.getResourceId(R.styleable.CheckableButton_background, -1);
        } finally {
            attrsArray.recycle();
        }
        if (background != -1) {
            button.setBackgroundResource(background);
        }
        if (scaleType >= 0) {
            button.setScaleType(getScaleType(scaleType));
        }
        if (buttonHeight >= 0) {
            ViewGroup.LayoutParams params = button.getLayoutParams();
            params.height = buttonHeight;
            button.setLayoutParams(params);
        }
        if (ivCheckIcon != -1) {
            ivCheck.setImageResource(ivCheckIcon);
        }

        button.setImageResource(imageResId);
        setChecked(isChecked);
    }

    private ImageView.ScaleType getScaleType(int scaleType) {
        switch (scaleType) {
            case 0: return ImageView.ScaleType.MATRIX;
            case 1: return ImageView.ScaleType.FIT_XY;
            case 2: return ImageView.ScaleType.FIT_START;
            case 3: return ImageView.ScaleType.FIT_CENTER;
            case 4: return ImageView.ScaleType.FIT_END;
            case 6: return ImageView.ScaleType.CENTER_CROP;
            case 7: return ImageView.ScaleType.CENTER_INSIDE;
            default: return ImageView.ScaleType.CENTER;
        }
    }

    @Override
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
        ivCheck.setVisibility(isChecked ? VISIBLE : INVISIBLE);
        button.setSelected(isChecked);
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

}
