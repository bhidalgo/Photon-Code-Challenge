package hidalgo.brandon.photoncodechallenge.ViewActions;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;

public class NumberPicker {
    public static void setValue(Matcher<View> numberPickerMatcher, final int value) {
        Espresso.onView(numberPickerMatcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.instanceOf(android.widget.NumberPicker.class);
            }

            @Override
            public String getDescription() {
                return "Set rows NumberPicker value.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((android.widget.NumberPicker) view).setValue(value);
            }
        }, closeSoftKeyboard());
    }
}
