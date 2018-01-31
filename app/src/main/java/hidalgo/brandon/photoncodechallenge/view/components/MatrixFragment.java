package hidalgo.brandon.photoncodechallenge.view.components;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import hidalgo.brandon.photoncodechallenge.R;
import hidalgo.brandon.photoncodechallenge.databinding.MatrixFragmentBinding;
import hidalgo.brandon.photoncodechallenge.model.POLCPresenterImpl;
import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

/**
 * A fragment that displays a matrix that can be configured and tested for path of lowest cost.
 */
public class MatrixFragment extends Fragment implements POLCView {
    private int mNumberRows;

    private int mNumberColumns;

    private MatrixView mMatrixView;

    private TextView mResultTextView;

    private POLCPresenter mPresenter;

    public MatrixFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this fragment
     * @param args a bundle containing the row and column values for the matrix
     * @return MatrixFragment
     */
    public static MatrixFragment getInstance(Bundle args) {
        MatrixFragment newFragment = new MatrixFragment();

        newFragment.setArguments(args);

        return newFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Retrieve the arguments for the matrix
        Bundle arguments = getArguments();

        mNumberRows = arguments.getInt("rows");

        mNumberColumns = arguments.getInt("columns");

        //Set the presenter implementation (in the MVP model layer) to this fragment (MVP View)
        mPresenter = new POLCPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout
        MatrixFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.matrix_fragment, container, false);

        //Get the root view
        View mainView = binding.getRoot();

        //Set the binding data
        binding.setFragment(this);

        mResultTextView = binding.resultTextView;

        mMatrixView = binding.matrixView;

        mMatrixView.init(mNumberRows, mNumberColumns);

        mResultTextView.setMovementMethod(new ScrollingMovementMethod());

        return mainView;
    }

    /**
     * Creates the matrix from the inputted values and instructs the presenter to computer the path of lowest cost
     * @param view need for setting OnClickListeners through the XML layout file
     */
    @SuppressWarnings("unused")
    public void handleOnNextClicked(View view) {
        //Create an empty matrix
        String[][] matrix = new String[mNumberRows][mNumberColumns];

        //Populate the matrix by going through each row and obtaining the value from the proper cell
        for (int i = 0; i < mNumberRows; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrixView.getChildAt(i);

            for (int j = 0; j < mNumberColumns; j++) {
                MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(j);

                //Remove in case it is highlighted from a previous calculation
                currentCell.removeHighlight();

                matrix[i][j] = currentCell.getInput();
            }
        }

        mPresenter.computePOLC(matrix);
    }

    /**
     * Traverses the matrix following the path as a guide to which rows (the path array elements) to highlight red in each column (the path array index)
     * @param cost the cost of the path
     * @param path the steps of the path
     */
    @Override
    public void showFailure(int cost, int[] path) {
        for (int i = 0; i < path.length; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrixView.getChildAt(path[i] - 1);

            MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(i);

            currentCell.highlightRed();
        }

        //Display the result in the designated TextView
        mResultTextView.setText(getString(R.string.answer, "No", cost, Arrays.toString(path)));
    }

    /**
     * Shows an error message in the log and in the result TextView
     */
    @Override
    public void showInvalidMatrixError() {
        //Present in log
        Log.e("MatrixFragment", getString(R.string.invalid_matrix_error));

        //Present in UI
        mResultTextView.setText(R.string.invalid_matrix_error);
    }

    /**
     * Traverses the matrix following the path as a guide to which rows (the path array elements) to highlight yellow in each column (the path array index)
     * @param cost the cost of the path
     * @param path the steps of the path
     */
    @Override
    public void showSuccess(int cost, int[] path) {
        for (int i = 0; i < path.length; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrixView.getChildAt(path[i] - 1);

            MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(i);

            currentCell.highlightYellow();
        }

        //Display the result in the designated TextView
        mResultTextView.setText(getString(R.string.answer, "Yes", cost, Arrays.toString(path)));
    }
}
