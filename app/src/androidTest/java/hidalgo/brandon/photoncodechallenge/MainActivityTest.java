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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * A suite of tests for the MainActivity
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Tests MainActivity.initializeDimensFragment()
     */
    @Test
    public void testInitializeDimensFragment() {
        onView(withId(R.id.rowsTextView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.columnsTextView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.rowsNumberPicker))
                .check(matches(isDisplayed()));

        onView(withId(R.id.columnsNumberPicker))
                .check(matches(isDisplayed()));

        onView(withId(R.id.textView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.appCompatButton))
                .check(matches(isDisplayed()));
    }

    //********************************************************************************************//
    ///////////////////// TESTS FOR MainActivity.createMatrixWithDimens(...) ///////////////////////
    //********************************************************************************************//
    @Test
    public void testCreateMatrixWithDimensSingleValue() {
        //Testing 1x1
        testCreateMatrixWithDimensWithDimens(1, 1);
    }

    @Test
    public void testCreateMatrixWithDimensSmallMatrix() {
        //Testing 3x3
        testCreateMatrixWithDimensWithDimens(3, 3);
    }

    @Test
    public void testCreateMatrixWithDimensMediumMatrix() {
        //Testing 10x10
        testCreateMatrixWithDimensWithDimens(10, 10);
    }

    @Test
    public void testCreateMatrixWithDimensLargeMatrixExtreme() {
        //Testing 10x100
        testCreateMatrixWithDimensWithDimens(10, 100);
    }

    @Test
    public void testCreateMatrixWithDimensLargeMatrixRowExtreme() {
        //Testing 10x1
        testCreateMatrixWithDimensWithDimens(10, 1);
    }

    @Test
    public void testCreateMatrixWithDimensLargeMatrixColumnExtreme() {
        //Testing 1x100
        testCreateMatrixWithDimensWithDimens(1, 100);
    }

    /**
     * Tests whether the MainActivity is displaying a matrix with given dimensions
     * @param rowsToBeSelected the amount of rows desired
     * @param columnsToBeSelected the amount of columns desired
     */
    private void testCreateMatrixWithDimensWithDimens(final int rowsToBeSelected, final int columnsToBeSelected) {
        NumberPicker.setValue(withId(R.id.rowsNumberPicker), rowsToBeSelected);

        NumberPicker.setValue(withId(R.id.columnsNumberPicker), columnsToBeSelected);

        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withId(R.id.matrixView))
                .check(matches(MatrixMatchers.withDimensions(rowsToBeSelected, columnsToBeSelected)));
    }
}
