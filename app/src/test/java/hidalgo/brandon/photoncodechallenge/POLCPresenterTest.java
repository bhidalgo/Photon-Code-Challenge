package hidalgo.brandon.photoncodechallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import hidalgo.brandon.photoncodechallenge.model.POLCPresenterImpl;
import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class POLCPresenterTest {
    @Mock
    private POLCView view;

    @Mock
    private POLCPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new POLCPresenterImpl(view);
    }

    @Test
    public void passMatrixNormalFlow1() {
        int[][] testMatrix =
                {{3, 4, 1, 2, 8, 6},
                        {6, 1, 8, 2, 7, 4},
                        {5, 9, 3, 9, 9, 5},
                        {8, 4, 1, 3, 2, 6},
                        {3, 7, 2, 8, 6, 4}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(16, new int[]{1, 2, 3, 4, 4, 5});
    }

    @Test
    public void passMatrixNormalFlow2() {
        int[][] testMatrix =
                {{3, 4, 1, 2, 8, 6},
                        {6, 1, 8, 2, 7, 4},
                        {5, 9, 3, 9, 9, 5},
                        {8, 4, 1, 3, 2, 6},
                        {3, 7, 2, 1, 2, 3}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(11, new int[]{1, 2, 1, 5, 5, 5});
    }

    @Test
    public void failMatrixNoPath() {
        int[][] testMatrix =
                {{19, 10, 19, 10, 19},
                        {21, 23, 20, 19, 12},
                        {20, 12, 20, 11, 10}};

        presenter.computePOLC(testMatrix);

        verify(view).showFailure(48, new int[]{1, 1, 1});
    }

    @Test
    public void passSingleRow() {
        int[][] testMatrix =
                {{5, 8, 5, 3, 5}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(26, new int[]{1, 1, 1, 1, 1});
    }

    @Test
    public void passSingleColumn() {
        int[][] testMatrix =
                {{5},
                        {8},
                        {5},
                        {3},
                        {5}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(3, new int[]{4});
    }

    @Test
    public void failMatrixStartingGreaterThanFifty() {
        int[][] testMatrix =
                {{69, 10, 19, 10, 19},
                        {51, 23, 20, 19, 12},
                        {60, 12, 20, 11, 10}};

        presenter.computePOLC(testMatrix);

        verify(view).showFailure(0, new int[]{});
    }

    @Test
    public void passMatrixOneGreaterThanFifty() {
        int[][] testMatrix =
                {{60, 3, 3, 6},
                        {6, 3, 7, 9},
                        {5, 6, 8, 3}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(14, new int[]{3, 1, 1, 3});
    }

    @Test
    public void passMatrixWithNegativeValues() {
        int[][] testMatrix =
                {{6, 3, -5, 9},
                        {-5, 2, 4, 10},
                        {3, -2, 6, 10},
                        {6, -1, -2, 10}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(0, new int[]{2, 3, 4, 1});
    }

    @Test
    public void passMatrixCompletePathAndLowerCostIncompletePath() {
        int[][] testMatrix =
                {{51, 51},
                        {0, 51},
                        {51, 51},
                        {5, 5}};


        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(10, new int[]{4, 4});
    }

    @Test
    public void failMatrixLongerIncompletePathAndLowerCostIncompletePath() {
        int[][] testMatrix =
                {{51, 51, 51},
                        {0, 51, 51},
                        {51, 51, 51},
                        {5, 5, 51}};


        presenter.computePOLC(testMatrix);

        verify(view).showFailure(10, new int[]{4, 4});
    }

    @Test
    public void passMatrixWithLargeNumberOfColumns() {
        int[][] testMatrix =
                {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(20, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1});
    }

    @Test
    public void passMatrixWithLargeNumberOfRows() {
        int[][] testMatrix =
                {{1, 5, 10},
                        {3, 10, 2},
                        {5, 10, 7},
                        {-2, 8, 6},
                        {6, 2, 0},
                        {-1, 1, 1},
                        {1, 0, 0},
                        {0, 0, 0},
                        {3, 4, 7},
                        {5, 5, 10},
                        {3, 1, 1},
                        {1, 1, 2},
                        {1, 0, 0},
                        {205, 5, 5},
                        {6, 10, 1},
                        {1, 2, 3},
                        {3, 3, 3},
                        {2, 60, 1},
                        {1, 2, 1}};

        presenter.computePOLC(testMatrix);

        verify(view).showSuccess(-1, new int[]{6, 7, 8});
    }
}
