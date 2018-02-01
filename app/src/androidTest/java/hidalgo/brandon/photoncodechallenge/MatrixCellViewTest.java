package hidalgo.brandon.photoncodechallenge;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hidalgo.brandon.photoncodechallenge.Matchers.MatrixCellViewMatcher;
import hidalgo.brandon.photoncodechallenge.ViewActions.NumberPicker;
import hidalgo.brandon.photoncodechallenge.view.components.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MatrixCellViewTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    //********************************************************************************************//
    //////////////////////////// TESTS FOR MatrixCellView.getInput() ///////////////////////////////
    //********************************************************************************************//

    @Test
    public void testGetInputEmpty() {
        testGetInput("");
    }

    @Test
    public void testGetInputUnsignedInteger() {
        testGetInput("7");
    }

    @Test
    public void testGetInputSignedInteger() {
        testGetInput("-7");
    }

    @Test
    public void testGetInputNonAlphaNumeric() {
        testGetInput("...");
    }

    //********************************************************************************************//
    //////////////////////////// TESTS FOR MatrixCellView.getInput() ///////////////////////////////
    //********************************************************************************************//

    @Test
    public void testHighlightYellow() {
        String completePathInput = "15";

        int expectedHighlightId = R.color.yellow;

        createSimpleMatrix();

        testHighlightWithInput(completePathInput, expectedHighlightId);
    }

    @Test
    public void testHighlightRed() {
        String incompletePathInput = "26";

        int expectedHighlightId = R.color.red;

        createComplexMatrix(1, 2);

        testHighlightWithMultipleInput(incompletePathInput, expectedHighlightId);
    }

    @Test
    public void testRemoveHighlight() {
        String completePathInput = "...";

        int expectedHighlightId = R.color.gray;

        createSimpleMatrix();

        testHighlightWithInput(completePathInput, expectedHighlightId);
    }

    private void createSimpleMatrix() {
        onView(withId(R.id.appCompatButton))
                .perform(click());
    }

    private void createComplexMatrix(int rows, int columns) {
        NumberPicker.setValue(withId(R.id.rowsNumberPicker), rows);

        NumberPicker.setValue(withId(R.id.columnsNumberPicker), columns);

        onView(withId(R.id.appCompatButton))
                .perform(click());
    }

    private void clickSubmitMatrix() {
        onView(withId(R.id.floatingActionButton2))
                .perform(click());
    }

    private void testGetInput(String testInput) {
        createSimpleMatrix();

        onView(withId(R.id.cellEditText))
                .perform(typeText(testInput), closeSoftKeyboard());

        clickSubmitMatrix();

        onView(is(MatrixCellViewMatcher.isMatrixCell()))
                .check(matches(MatrixCellViewMatcher.withInput(testInput)));
    }

    private void testHighlightWithInput(String input, int colorId) {
        int color = ContextCompat.getColor(InstrumentationRegistry.getTargetContext(), colorId);

        onView(withId(R.id.cellEditText))
                .perform(typeText(input), closeSoftKeyboard());

        clickSubmitMatrix();

        onView(is(MatrixCellViewMatcher.isMatrixCell()))
                .check(matches(MatrixCellViewMatcher.withHighlight(color)));
    }

    private void testHighlightWithMultipleInput(String input, int colorId) {
        int color = ContextCompat.getColor(InstrumentationRegistry.getTargetContext(), colorId);

        onView(first(withId(R.id.cellEditText)))
                .perform(typeText(input));

        Espresso.closeSoftKeyboard();

        onView(second(withId(R.id.cellEditText)))
                .perform(typeText(input));

        Espresso.closeSoftKeyboard();

        clickSubmitMatrix();

        onView(first(MatrixCellViewMatcher.isMatrixCell()))
                .check(matches(MatrixCellViewMatcher.withHighlight(color)));
    }

    private <T> Matcher<T> first(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isFirst = true;

            @Override
            public boolean matches(final Object item) {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false;
                    return true;
                }

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return first matching item");
            }
        };
    }

    private <T> Matcher<T> second(final Matcher<T> matcher) {
        return new BaseMatcher<T>() {
            boolean isSecond = false;

            @Override
            public boolean matches(final Object item) {
                if (!isSecond && matcher.matches(item)) {
                    isSecond = true;
                } else if (isSecond && matcher.matches(item))
                    return true;

                return false;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("should return second matching item");
            }
        };
    }
}
