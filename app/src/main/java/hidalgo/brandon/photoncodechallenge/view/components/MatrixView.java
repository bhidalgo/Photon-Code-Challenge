package hidalgo.brandon.photoncodechallenge.view.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

/**
 * A View that represents a NxN Matrix
 */
public class MatrixView extends LinearLayout {
    private int mRows;

    private int mColumns;

    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Builds the MatrixView with the desired dimensions
     * @param numberRows the number of desired rows
     * @param numberColumns the number of desired columns
     */
    public void init(int numberRows, int numberColumns) {
        mRows = numberRows;

        mColumns = numberColumns;

        setOrientation(LinearLayout.VERTICAL);

        //Create and configure the layout parameters for the rows
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.gravity = Gravity.CENTER;

        //Create the children view of the matrix (LinearLayouts that contain an array of MatrixCellViews)
        for (int i = 0; i < mRows; i++) {
            //Create the new row
            LinearLayout matrixRow = new LinearLayout(getContext());

            //Set the layout parameters
            matrixRow.setLayoutParams(layoutParams);

            //Configure the row
            matrixRow.setOrientation(LinearLayout.HORIZONTAL);

            matrixRow.setGravity(Gravity.CENTER);

            //Create the matrix cells for each row
            for (int j = 0; j < mColumns; j++) {
                //Create the cell
                MatrixCellView cell = new MatrixCellView(getContext());

                //Add it to the row
                matrixRow.addView(cell);
            }

            //Add the new row to the matrix
            addView(matrixRow);
        }
    }

    /**
     * Returns the number of rows this MatrixView should have
     * @return int
     */
    public int getRows() {
        return mRows;
    }

    /**
     * Returns the number of columns this MatrixView should have
     * @return
     */
    public int getColumns() {
        return mColumns;
    }
}
