# CheckableButtons

![Alt Text](https://drive.google.com/uc?export=view&id=17kvC_t97w9SkVAqJPVBEfM8-lFMPwyvm)

### Gradle Setup

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.doruchidean:CheckableButtons:v1.0.3'
}
```

### Example
```xml
<ro.doruchidean.checkablebutton.SingleCheckButtonsGroup
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:weightSum="4">
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="0dp"
            android:layout_weight="1"
            android:layout_height="wrap_content"
            app:text="@string/label_cat"
            app:isChecked="true"
            />
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="0dp"
            android:layout_weight="1"
            android:layout_height="wrap_content"
            app:text="@string/label_dog"
            />
        <ro.doruchidean.checkablebutton.CheckableButton
            android:layout_width="0dp"
            android:layout_weight="1"
            android:layout_height="wrap_content"
            app:text="@string/label_hamster"
            />
        <ro.doruchidean.checkablebutton.CheckableImageButton
            android:layout_width="0dp"
            android:layout_weight="1"
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

**Available xml attributes**
```xml
<resources>
    <!--CheckableButton-->
    <attr name="text" format="string"/>
    <attr name="isChecked" format="boolean"/>
    <attr name="buttonHeight" format="dimension"/>

    
    <!--CheckableImageButton-->
    <attr name="src" format="reference"/>
    <attr name="isChecked"/>
    <attr name="buttonHeight"/>
    <attr name="scaleType">
        <enum name="matrix"/>
        <enum name="fitXY"/>
        <enum name="fitStart"/>
        <enum name="fitCenter"/>
        <enum name="fitEnd"/>
        <enum name="center"/>
        <enum name="centerCrop"/>
        <enum name="centerInside"/>
    </attr>  
</resources>  
```