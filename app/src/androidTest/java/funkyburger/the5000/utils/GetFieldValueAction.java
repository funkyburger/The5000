package funkyburger.the5000.utils;

import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matcher;

public class GetFieldValueAction implements ViewAction {
    private String value;


    public GetFieldValueAction() {
    }

    @Override
    public Matcher<View> getConstraints() {
        return isAssignableFrom(TextView.class);
    }

    @Override
    public String getDescription() {
        return "getting text from a TextView";
    }

    @Override
    public void perform(UiController uiController, View view) {
        TextView tv = (TextView)view;
        value = tv.getText().toString();
    }

    public String getValue() {
        return value;
    }
}
