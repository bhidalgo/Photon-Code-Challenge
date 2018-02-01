package hidalgo.brandon.photoncodechallenge;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hidalgo.brandon.photoncodechallenge.view.components.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MatrixFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testInit() {
        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withText("Fill in your matrix and hit the button"))
                .check(matches(isDisplayed()));

        onView(withId(R.id.matrixView))
                .check(matches(isDisplayed()));

        onView(withId(R.id.floatingActionButton2))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShowFailure() {
        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withId(R.id.cellEditText))
                .perform(typeText("51"));


        Espresso.closeSoftKeyboard();

        onView(withId(R.id.floatingActionButton2))
                .perform(click());

        onView(withText("Answer: No\n" +
                "Cost: 0\n" +
                "Path: []"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShowInvalidMatrixError() {
        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withId(R.id.cellEditText))
                .perform(typeText("A"));


        Espresso.closeSoftKeyboard();

        onView(withId(R.id.floatingActionButton2))
                .perform(click());

        onView(withText("Invalid matrix"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testShowSuccess() {
        onView(withId(R.id.appCompatButton))
                .perform(click());

        onView(withId(R.id.cellEditText))
                .perform(typeText("25"));


        Espresso.closeSoftKeyboard();

        onView(withId(R.id.floatingActionButton2))
                .perform(click());

        onView(withText("Answer: Yes\n" +
                "Cost: 25\n" +
                "Path: [1]"))
                .check(matches(isDisplayed()));
    }
}
