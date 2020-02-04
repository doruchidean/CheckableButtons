package ro.lifeishard.checkablebuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;

import ro.lifeishard.checkablebutton.MultipleCheckButtonsGroup;
import ro.lifeishard.checkablebutton.SingleCheckButtonsGroup;

public class MainActivity extends AppCompatActivity {

    private TextView tvFavFood;
    private TextView tvFavPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingleCheckButtonsGroup singleGroup = findViewById(R.id.single_group);
        singleGroup.setOnCheckChangedListener(getOnSingleCheckListener());
        MultipleCheckButtonsGroup multiGroup = findViewById(R.id.multi_group);
        multiGroup.setOnCheckChangedListener(getOnMultiCheckListener());

        tvFavPet = findViewById(R.id.tv_favorite_pet);
        tvFavFood = findViewById(R.id.tv_favorite_food);

        singleGroup.setChecked(R.id.btn_cat);
        multiGroup.setChecked(R.id.btn_crickets);
    }

    private SingleCheckButtonsGroup.Listener getOnSingleCheckListener() {
        return new SingleCheckButtonsGroup.Listener() {
            @Override
            public void onCheckChanged(int buttonResId) {
                String label = getString(R.string.format_pet_preference);
                String value = "";
                switch (buttonResId) {
                    case R.id.btn_cat:
                        value = getString(R.string.label_cat_pet);
                        break;
                    case R.id.btn_dog:
                        value = getString(R.string.label_dog_pet);
                        break;
                    case R.id.btn_hamster:
                        value = getString(R.string.label_hamster_pet);
                        break;
                    case R.id.btn_android:
                        value = getString(R.string.label_android_pet);
                        break;
                }
                tvFavPet.setText(String.format(label, value));
            }
        };
    }

    private MultipleCheckButtonsGroup.Listener getOnMultiCheckListener() {
        return new MultipleCheckButtonsGroup.Listener() {

            private LinkedList<String> foodValues = new LinkedList<>();

            @Override
            public void onCheckChanged(int buttonResId, boolean isChecked) {
                String label = getString(R.string.format_food_preference);
                switch (buttonResId) {
                    case R.id.btn_bacon:
                        if (isChecked) {
                            foodValues.add(getString(R.string.label_bacon));
                        } else {
                            foodValues.remove(getString(R.string.label_bacon));
                        }
                        break;
                    case R.id.btn_pizza:
                        if (isChecked) {
                            foodValues.add(getString(R.string.label_pizza));
                        } else {
                            foodValues.remove(getString(R.string.label_pizza));
                        }
                        break;
                    case R.id.btn_crickets:
                        if (isChecked) {
                            foodValues.add(getString(R.string.label_crickets));
                        } else {
                            foodValues.remove(getString(R.string.label_crickets));
                        }
                        break;
                    case R.id.btn_fast_food:
                        if (isChecked) {
                            foodValues.add(getString(R.string.label_fast_food));
                        } else {
                            foodValues.remove(getString(R.string.label_fast_food));
                        }
                        break;
                }
                String values = Arrays.toString(foodValues.toArray())
                        .replace("[", "")
                        .replace("]", "");
                tvFavFood.setText(String.format(label, values));
            }
        };
    }
}
