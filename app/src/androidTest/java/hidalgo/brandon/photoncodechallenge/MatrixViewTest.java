package hidalgo.brandon.photoncodechallenge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hidalgo.brandon.photoncodechallenge.Matchers.MatrixMatchers;
import hidalgo.brandon.photoncodechallenge.ViewActions.NumberPicker;
import hidalgo.brandon.photoncodechallenge.view.components.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * A suite of tests for the MatrixView
 */
@RunWith(AndroidJUnit4.class)
public class MatrixViewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    //********************************************************************************************//
    //////////////////////////////// TESTS FOR MatrixView.init() ///////////////////////////////////
    //********************************************************************************************//

    @Test
    public void testInitSingleCell() {
        testInitWithDimensions(1, 1);
    }

    @Test
    public void testInitSmallMatrix() {
        testInitWithDimensions(3, 3);
    }

    @Test
    public void testInitMediumMatrix() {
        testInitWithDimensions(10, 10);
    }

    @Test
    public void testInitLargeMatrix() {
        testInitWithDimensions(10, 100);
    }

    /**
     * Tests to see if the Matrix is being displayed with the proper dimensions
     * @param numberRows the expected rows
     * @param numberColumns the expected columns
     */
    private void testInitWithDimensions(int numberRows, int numberColumns) {
        NumberPicker.setValue(withId(R.id.rowsNumberPicker), numberRows);

        NumberPicker.setValue(withId(R.id.columnsNumberPicker), numberColumns);

        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withId(R.id.matrixView))
                .check(matches(MatrixMatchers.displaysRowsAndColumns(numberRows, numberColumns)));
    }
}
