package hidalgo.brandon.photoncodechallenge;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import hidalgo.brandon.photoncodechallenge.databinding.MatrixDimensFragmentBinding;

public class MatrixDimensFragment extends Fragment {
    private MatrixDimensFragmentListener listener;

    private NumberPicker mRowsNumberPicker;

    private NumberPicker mColumnsNumberPicker;

    public MatrixDimensFragment() {
        // Required empty public constructor
    }

    public static MatrixDimensFragment getInstance() {
        return new MatrixDimensFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (MatrixDimensFragmentListener) context;
        } catch (ClassCastException e) {
            Log.e("MatrixDimensFrag", "Context must implement the MatrixDimensFragmentListener.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MatrixDimensFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.matrix_dimens_fragment, container, false);

        View mainView = binding.getRoot();

        setUpNumberPickers(mainView);

        binding.setFragment(this);

        return mainView;
    }

    @SuppressWarnings("unused")
    public void handleOnNextClicked(View view) {
        int rows = mRowsNumberPicker.getValue();

        int cols = mColumnsNumberPicker.getValue();

        listener.createMatrixWithDimens(rows, cols);
    }

    private void setUpNumberPickers(View parentView) {
        mRowsNumberPicker = parentView.findViewById(R.id.rowsNumberPicker);

        mRowsNumberPicker.setMinValue(1);

        mRowsNumberPicker.setMaxValue(10);

        mColumnsNumberPicker = parentView.findViewById(R.id.columnsNumberPicker);

        mColumnsNumberPicker.setMinValue(1);

        mColumnsNumberPicker.setMaxValue(10);
    }

    public interface MatrixDimensFragmentListener {
        void createMatrixWithDimens(int rows, int cols);
    }
}
