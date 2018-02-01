package hidalgo.brandon.photoncodechallenge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hidalgo.brandon.photoncodechallenge.Matchers.NumberPickMatcher;
import hidalgo.brandon.photoncodechallenge.view.components.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * A test for the MatrixDimensFragment
 */
@RunWith(AndroidJUnit4.class)
public class MatrixDimensFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Tests MatrixDimensFragment.setUpNumberPickers()
     */
    @Test
    public void testSetUpNumberPickers() {
        onView(withId(R.id.rowsNumberPicker))
                .check(matches(NumberPickMatcher.withRange(1, 10)));

        onView(withId(R.id.columnsNumberPicker))
                .check(matches(NumberPickMatcher.withRange(1, 100)));
    }
}
