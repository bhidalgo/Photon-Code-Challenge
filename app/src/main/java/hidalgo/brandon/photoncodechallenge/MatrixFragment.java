package hidalgo.brandon.photoncodechallenge;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Arrays;

import hidalgo.brandon.photoncodechallenge.databinding.MatrixFragmentBinding;
import hidalgo.brandon.photoncodechallenge.model.POLCPresenterImpl;
import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

public class MatrixFragment extends Fragment implements POLCView{
    private int mNumberRows;

    private int mNumberColumns;

    private LinearLayout mMatrix;

    private TextView mResultTextView;

    private POLCPresenter mPresenter;

    public MatrixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();

        mNumberRows = arguments.getInt("rows");

        mNumberColumns = arguments.getInt("columns");

        mPresenter = new POLCPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MatrixFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.matrix_fragment, container, false);

        View mainView = binding.getRoot();

        binding.setFragment(this);

        ScrollView matrixContainer = mainView.findViewById(R.id.matrixContainer);

        mResultTextView = mainView.findViewById(R.id.resultTextView);

        setUpMatrix(matrixContainer);

        return mainView;
    }

    @SuppressWarnings("unused")
    public void handleOnNextClicked(View view) {
        int[][] matrix = new int[mNumberRows][mNumberColumns];

        for(int i = 0; i < mNumberRows; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrix.getChildAt(i);

            for(int j = 0; j < mNumberColumns; j++) {
                MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(j);

                currentCell.removeHighlight();

                matrix[i][j] = currentCell.getInput();
            }
        }

        mPresenter.computePOLC(matrix);
    }

    public static MatrixFragment getInstance(Bundle args) {
        MatrixFragment newFragment = new MatrixFragment();

        newFragment.setArguments(args);

        return newFragment;
    }

    private void setUpMatrix(FrameLayout parentView) {
        mMatrix = new LinearLayout(getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.gravity = Gravity.CENTER;

        mMatrix.setLayoutParams(layoutParams);

        mMatrix.setOrientation(LinearLayout.VERTICAL);

        for(int i = 0; i < mNumberRows; i++) {
            LinearLayout matrixRow = new LinearLayout(getContext());

            matrixRow.setLayoutParams(layoutParams);

            matrixRow.setOrientation(LinearLayout.HORIZONTAL);

            matrixRow.setGravity(Gravity.CENTER);

            for(int j = 0; j < mNumberColumns; j++) {
                MatrixCellView cell = new MatrixCellView(getContext());

                matrixRow.addView(cell);
            }

            mMatrix.addView(matrixRow);
        }

        parentView.addView(mMatrix);
    }

    @Override
    public void showFailure(int cost, int[] path) {
        for(int i = 0; i < path.length; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrix.getChildAt(path[i] - 1);

            MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(i);

            currentCell.highlightRed();
        }

        mResultTextView.setText(getString(R.string.answer, "No", cost, Arrays.toString(path)));
    }

    @Override
    public void showSuccess(int cost, int[] path) {
        for(int i = 0; i < path.length; i++) {
            LinearLayout currentRow = (LinearLayout) mMatrix.getChildAt(path[i] - 1);

            MatrixCellView currentCell = (MatrixCellView) currentRow.getChildAt(i);

            currentCell.highlightYellow();
        }

        mResultTextView.setText(getString(R.string.answer, "Yes", cost, Arrays.toString(path)));
    }
}
