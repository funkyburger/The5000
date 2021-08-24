package funkyburger.the5000.widget;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import funkyburger.the5000.MainActivity;
import funkyburger.the5000.*;
import funkyburger.the5000.R;
import funkyburger.the5000.utils.GetFieldValueAction;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EnsuringValueEditTextTest extends UiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void letBlankReturnsToDefault() {
        ViewInteraction firstField = getPlayerNameField(0);
        ViewInteraction secondField = getPlayerNameField(1);
        GetFieldValueAction fieldValue = new GetFieldValueAction();

        secondField.perform(fieldValue);
        String originalName = fieldValue.getValue();

        assertNotEquals("", originalName);

        secondField.perform(click());
        secondField.check(matches(withText("")));

        firstField.perform(click());
        secondField.check(matches(withText(originalName)));

        secondField.perform(replaceText("bidule"));
        firstField.perform(click());

        secondField.check(matches(withText("bidule")));
    }
}