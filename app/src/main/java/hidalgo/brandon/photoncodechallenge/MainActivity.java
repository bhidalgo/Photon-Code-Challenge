package hidalgo.brandon.photoncodechallenge;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import hidalgo.brandon.photoncodechallenge.view.POLCView;

public class MainActivity extends AppCompatActivity implements POLCView, MatrixDimensFragment.MatrixDimensFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        initializeMatrixDimensFragment();
    }

    @Override
    public void createMatrixWithDimens(int rows, int cols) {
        Toast.makeText(this, "FAB Clicked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure(int cost, int[] path) {

    }

    @Override
    public void showMatrix(int[][] matrix) {

    }

    @Override
    public void showPOLC(int path) {

    }

    @Override
    public void showSuccess(int cost, int[] path) {

    }

    private void initializeMatrixDimensFragment() {
        MatrixDimensFragment matrixDimensFragment = MatrixDimensFragment.getInstance();

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragmentContainer, matrixDimensFragment).commit();
    }
}
