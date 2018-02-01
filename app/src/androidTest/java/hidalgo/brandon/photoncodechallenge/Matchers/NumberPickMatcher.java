package hidalgo.brandon.photoncodechallenge.Matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.NumberPicker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class NumberPickMatcher {
    public static Matcher<View> withRange(final int min, final int max) {
        return new BoundedMatcher<View, NumberPicker>(NumberPicker.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("number picker with min: " + min + " and max: " + max);
            }

            @Override
            protected boolean matchesSafely(NumberPicker item) {
                return item.getMinValue() == min && item.getMaxValue() == max;
            }
        };
    }
}
