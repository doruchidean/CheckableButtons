package ro.lifeishard.checkablebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CheckableButton extends BaseCheckableButton {

    private TextView button;
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
        try {
            label = attrsArray.getString(R.styleable.CheckableButton_text);
            isChecked = attrsArray.getBoolean(R.styleable.CheckableButton_isChecked, false);
        } finally {
            attrsArray.recycle();
        }
        button.setText(label);
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
