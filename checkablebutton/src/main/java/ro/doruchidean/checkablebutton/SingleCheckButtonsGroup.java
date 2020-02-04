package ro.doruchidean.checkablebutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class SingleCheckButtonsGroup extends LinearLayout {

    @Nullable
    private Listener listener;

    public SingleCheckButtonsGroup(Context context,
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

    public void setOnCheckChangedListener(@Nullable Listener listener) {
        this.listener = listener;
    }

    private void setupButton(View checkableButton) {
        checkableButton.setOnClickListener(onButtonClickListener);
    }

    private OnClickListener onButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseCheckableButton b = (BaseCheckableButton) v;
            if (b.isChecked()) {
                return;
            }
            deselectAll();
            b.setChecked(true);
            if (listener != null) {
                listener.onCheckChanged(b.getId());
            }
        }
    };

    private void deselectAll() {
        int count = getChildCount();
        for (int i=0; i<count; i++) {
            ((BaseCheckableButton)getChildAt(i)).setChecked(false);
        }
    }

    @Nullable
    public BaseCheckableButton getCheckedButton() {
        int count = getChildCount();
        for (int i=0; i<count; i++) {
            BaseCheckableButton b = (BaseCheckableButton) getChildAt(i);
            if (b.isChecked()) {
                return b;
            }
        }
        return null;
    }

    public void setChecked(int buttonResId) {
        int count = getChildCount();
        for (int i=0; i<count; i++) {
            BaseCheckableButton b = (BaseCheckableButton) getChildAt(i);
            if (b.getId() == buttonResId) {
                onButtonClickListener.onClick(b);
                break;
            }
        }
    }

    public interface Listener {
        void onCheckChanged(int buttonResId);
    }

}
