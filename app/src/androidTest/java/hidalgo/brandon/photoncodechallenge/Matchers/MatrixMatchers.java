package hidalgo.brandon.photoncodechallenge.Matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import hidalgo.brandon.photoncodechallenge.view.components.MatrixView;

/**
 * A class that provides Hamcrest Matchers for MatrixViews
 */
public class MatrixMatchers {

    /**
     * Matches against a MatrixView with certain dimensions
     *
     * @param rows    the expected number of rows
     * @param columns the expected number of columns
     * @return boolean
     */
    public static Matcher<View> withDimensions(final int rows, final int columns) {
        return new BoundedMatcher<View, MatrixView>(MatrixView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with dimensions: " + rows + "x" + columns);
            }

            @Override
            protected boolean matchesSafely(MatrixView item) {
                return item.getRows() == rows && item.getColumns() == columns;
            }
        };
    }
}
