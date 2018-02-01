package hidalgo.brandon.photoncodechallenge.Matchers;

import android.graphics.drawable.ColorDrawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import hidalgo.brandon.photoncodechallenge.view.components.MatrixCellView;

/**
 * A class providing Hamcrest Matchers for MatrixCellViews
 */
public class MatrixCellViewMatcher {

    /**
     * A matcher for matching against a MatrixCellView
     *
     * @return boolean
     */
    public static Matcher<View> isMatrixCell() {
        return new Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return item.getClass() == MatrixCellView.class;
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("Expected MatrixCellView, but received " + item.getClass().getSimpleName());
            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
                //
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is instance of MatrixCellView: ");
            }
        };
    }

    /**
     * A matcher for matching against a highlighted cell
     *
     * @param highlightColor the expected highlight color
     * @return boolean
     */
    public static Matcher<View> withHighlight(final int highlightColor) {
        return new BoundedMatcher<View, MatrixCellView>(MatrixCellView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has highlight: " + highlightColor);
            }

            @Override
            protected boolean matchesSafely(MatrixCellView item) {
                if (item.getBackground() instanceof ColorDrawable) {
                    int actualColor = ((ColorDrawable) item.getBackground()).getColor();

                    return actualColor == highlightColor;
                }

                return false;
            }
        };
    }

    /**
     * A matcher for matching against a MatrixCellView's input
     *
     * @param input the expected input
     * @return boolean
     */
    public static Matcher<View> withInput(final String input) {
        return new BoundedMatcher<View, MatrixCellView>(MatrixCellView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("matrix with input: ");
            }

            @Override
            protected boolean matchesSafely(MatrixCellView item) {
                return item.getInput().equals(input);
            }
        };
    }
}
