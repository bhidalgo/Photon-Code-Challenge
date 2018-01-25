package hidalgo.brandon.photoncodechallenge;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MatrixDimensFragment.MatrixDimensFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        initializeMatrixDimensFragment();
    }

    @Override
    public void createMatrixWithDimens(int rows, int cols) {
        Bundle matrixArgs = new Bundle();

        matrixArgs.putInt("rows", rows);

        matrixArgs.putInt("columns", cols);

        MatrixFragment matrixFragment = MatrixFragment.getInstance(matrixArgs);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.fragmentContainer, matrixFragment).addToBackStack("").commit();
    }

    private void initializeMatrixDimensFragment() {
        MatrixDimensFragment matrixDimensFragment = MatrixDimensFragment.getInstance();

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragmentContainer, matrixDimensFragment).commit();
    }
}
