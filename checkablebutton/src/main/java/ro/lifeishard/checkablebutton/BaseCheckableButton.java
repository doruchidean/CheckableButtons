package ro.lifeishard.checkablebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseCheckableButton extends FrameLayout {

    public BaseCheckableButton(@NonNull Context context,
                               @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
        setupAttrs(context, attrs);
    }

    protected abstract void setupAttrs(Context context, AttributeSet attrs);

    protected abstract void initUI(Context context);

    public abstract boolean isChecked();

    public abstract void setChecked(boolean isChecked);
}
