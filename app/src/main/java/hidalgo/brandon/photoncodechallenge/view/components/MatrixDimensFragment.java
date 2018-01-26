package hidalgo.brandon.photoncodechallenge.view.components;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import hidalgo.brandon.photoncodechallenge.R;
import hidalgo.brandon.photoncodechallenge.databinding.MatrixDimensFragmentBinding;

/**
 * A fragment where the user may enter their desired dimensions for the matrix
 */
public class MatrixDimensFragment extends Fragment {
    private MatrixDimensFragmentListener listener;

    private NumberPicker mRowsNumberPicker;

    private NumberPicker mColumnsNumberPicker;

    /**
     * An interface for communicating with the Activity
     */
    public interface MatrixDimensFragmentListener {
        void createMatrixWithDimens(int rows, int cols);
    }

    public MatrixDimensFragment() {
        // Required empty public constructor
    }

    /**
     * Returns a new instance of this type of fragment
     * @return MatrixDimensFragment
     */
    public static MatrixDimensFragment getInstance() {
        return new MatrixDimensFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //We will set our listener here
        try {
            listener = (MatrixDimensFragmentListener) context;
        } catch (ClassCastException e) {
            Log.e("MatrixDimensFrag", "Context must implement the MatrixDimensFragmentListener.");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout
        MatrixDimensFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.matrix_dimens_fragment, container, false);

        //Get the root view
        View mainView = binding.getRoot();

        //Get the number pickers
        mRowsNumberPicker = binding.rowsNumberPicker;

        mColumnsNumberPicker = binding.columnsNumberPicker;

        setUpNumberPickers();

        //Set the binding data
        binding.setFragment(this);

        return mainView;
    }

    /**
     * Gets entered dimensions and sends them to the listener
     * @param view needed for applying onClickListeners through XML layout files
     */
    @SuppressWarnings("unused")
    public void handleOnNextClicked(View view) {
        int rows = mRowsNumberPicker.getValue();

        int cols = mColumnsNumberPicker.getValue();

        listener.createMatrixWithDimens(rows, cols);
    }

    /**
     * Sets the minimum and maximum values for the number pickers
     */
    private void setUpNumberPickers() {

        mRowsNumberPicker.setMinValue(1);

        mRowsNumberPicker.setMaxValue(10);

        mColumnsNumberPicker.setMinValue(1);

        mColumnsNumberPicker.setMaxValue(100);
    }
}
