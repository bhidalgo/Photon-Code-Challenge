package hidalgo.brandon.photoncodechallenge.view.components;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import hidalgo.brandon.photoncodechallenge.R;
import hidalgo.brandon.photoncodechallenge.databinding.MainActivityBinding;

/**
 * This Activity is in charge of only handling fragment transactions
 */
public class MainActivity extends AppCompatActivity implements MatrixDimensFragment.MatrixDimensFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        binding.setActivity(this);

        initializeMatrixDimensFragment();
    }

    /**
     * Adds the MatrixFragment to the fragment container
     * @param rows the amount of rows in the matrix
     * @param cols the amount of columns in the matrix
     */
    @Override
    public void createMatrixWithDimens(int rows, int cols) {
        //Set up the fragment arguments
        Bundle matrixArgs = new Bundle();

        matrixArgs.putInt("rows", rows);

        matrixArgs.putInt("columns", cols);

        //Retrieve an instance of the fragment
        MatrixFragment matrixFragment = MatrixFragment.getInstance(matrixArgs);

        //Replace the previous fragment with the matrix fragment
        FragmentManager fm = getSupportFragmentManager();

        //addToBackStack() preserves the navigation flow -- no need to pass a tag for this simple case
        fm.beginTransaction().replace(R.id.fragmentContainer, matrixFragment).addToBackStack("").commit();
    }

    /**
     * Adds the MatrixDimensFragment to the fragment container
     */
    private void initializeMatrixDimensFragment() {
        //Retrieve an instance of the fragment
        MatrixDimensFragment matrixDimensFragment = MatrixDimensFragment.getInstance();

        //Add the fragment to the container
        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragmentContainer, matrixDimensFragment).commit();
    }
}
