package ro.doruchidean.checkablebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MultipleCheckButtonsGroup extends LinearLayout {

    private Listener listener;

    public MultipleCheckButtonsGroup(Context context,
                                     @Nullable AttributeSet attrs) {
        super(context, attrs);
        getViewTreeObserver().addOnGlobalLayoutListener(getLayoutListener());
    }

    private ViewTreeObserver.OnGlobalLayoutListener getLayoutListener() {
        return new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int children = getChildCount();
                for (int i=0; i<children; i++) {
                    setupButton(getChildAt(i));
                }
            }
        };
    }

    public void setOnCheckChangedListener(Listener listener) {
        this.listener = listener;
    }

    public void setChecked(int buttonResId) {
        int count = getChildCount();
        for (int i=0; i<count; i++) {
            BaseCheckableButton b = (BaseCheckableButton) getChildAt(i);
            if (b.getId() == buttonResId) {
                b.setChecked(true);
                if (listener != null) {
                    listener.onCheckChanged(buttonResId, true);
                }
                break;
            }
        }
    }

    private void setupButton(View checkableButton) {
        checkableButton.setOnClickListener(onButtonClickListener);
    }

    private OnClickListener onButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseCheckableButton b = (BaseCheckableButton) v;
            b.setChecked(!b.isChecked());
            if (listener != null) {
                listener.onCheckChanged(b.getId(), b.isChecked());
            }
        }
    };

    public interface Listener {
        void onCheckChanged(int buttonResId, boolean isChecked);
    }

}