# CheckableButtons

### Gradle Setup

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.doruchidean:CheckableButtons:v1.0.1'
}
```

### Example
```xml
<ro.doruchidean.checkablebutton.SingleCheckButtonsGroup
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:text="@string/label_cat"
            app:isChecked="true"
            />
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:text="@string/label_dog"
            />
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:text="@string/label_hamster"
            />
        <ro.doruchidean.checkablebutton.CheckableImageButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:src="@drawable/ic_android"
            app:scaleType="fitXY"
            />
    </ro.doruchidean.checkablebutton.SingleCheckButtonsGroup>
```

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SingleCheckButtonsGroup singleGroup = findViewById(R.id.single_group);
        singleGroup.setOnCheckChangedListener(getOnSingleCheckListener());

        singleGroup.setChecked(R.id.btn_cat);
    }

    private SingleCheckButtonsGroup.Listener getOnSingleCheckListener() {
        return new SingleCheckButtonsGroup.Listener() {
            @Override
            public void onCheckChanged(int buttonResId) {
                //use as needed
            }
        };
    }
}
```