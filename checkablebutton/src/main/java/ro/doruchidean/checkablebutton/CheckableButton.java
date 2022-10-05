package ro.doruchidean.checkablebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CheckableButton extends BaseCheckableButton {

    public TextView button;
    private ImageView ivCheck;
    private boolean isChecked;

    public CheckableButton(@NonNull Context context,
                           @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initUI(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_checkable_button, this, true);
        button = findViewById(R.id.btn_checkable);
        ivCheck = findViewById(R.id.iv_check);
    }

    @Override
    protected void setupAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray attrsArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CheckableButton, 0, 0);
        String label;
        boolean isChecked;
        int buttonHeight;
        int ivCheckIcon;
        int background;
        try {
            label = attrsArray.getString(R.styleable.CheckableButton_text);
            isChecked = attrsArray.getBoolean(R.styleable.CheckableButton_isChecked, false);
            buttonHeight = attrsArray.getDimensionPixelSize(R.styleable.CheckableButton_buttonHeight, -1);
            ivCheckIcon = attrsArray.getResourceId(R.styleable.CheckableButton_cornerIcon, -1);
            background = attrsArray.getResourceId(R.styleable.CheckableButton_background, -1);
        } finally {
            attrsArray.recycle();
        }
        button.setText(label);
        if (background != -1) {
            button.setBackgroundResource(background);
        }
        if (buttonHeight >= 0) {
            ViewGroup.LayoutParams params = button.getLayoutParams();
            params.height = buttonHeight;
            button.setLayoutParams(params);
        }
        if (ivCheckIcon != -1) {
            ivCheck.setImageResource(ivCheckIcon);
        }
        setChecked(isChecked);
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

    public void setText(String text) {
        button.setText(text);
    }

    public String getText() {
        return button.getText().toString();
    }
}
