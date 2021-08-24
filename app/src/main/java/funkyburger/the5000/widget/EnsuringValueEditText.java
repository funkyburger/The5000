package funkyburger.the5000.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import funkyburger.the5000.utils.StringUtil;

public class EnsuringValueEditText extends android.support.v7.widget.AppCompatEditText {

    private String defaultValue = null;

    public EnsuringValueEditText(Context context, String defaultValue) {
        super(context);
        setDefaultValue(defaultValue, true);
        initialize();
    }

    public void setDefaultValue(String defaultValue) {
        setDefaultValue(defaultValue, false);
    }

    public void setDefaultValue(String defaultValue, boolean overwriteValue) {
        if(StringUtil.isNullOrEmpty(defaultValue)) {
            throw new RuntimeException("Default value neither be null nor empty.");
        }

        this.defaultValue = defaultValue;
        setText(defaultValue);
    }

    private void initialize() {
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean gotFocus) {
                if(gotFocus) {
                    if(getText().toString().equals(defaultValue)) {
                        setText("");
                    }
                } else {
                    if(StringUtil.isNullOrEmpty(getText().toString())) {
                        setText(defaultValue);
                    }
                }
            }
        });
    }
}
