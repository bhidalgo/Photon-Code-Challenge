package hidalgo.brandon.photoncodechallenge.view.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import hidalgo.brandon.photoncodechallenge.R;
import hidalgo.brandon.photoncodechallenge.databinding.MatrixCellViewBinding;

import static android.text.InputType.TYPE_CLASS_NUMBER;

/**
 * The custom view acts as a cell in the matrix
 */
public class MatrixCellView extends LinearLayout {
    private EditText mCellEditText;

    public MatrixCellView(Context context) {
        super(context);

        init(context);
    }

    public MatrixCellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    /**
     * Retrieves the number entered inside the cell
     * @return integer
     */
    public int getInput() {
        //We need to clean and convert the input as it is natively an Editable type.
        String input = mCellEditText.getText().toString();

        //If no input provided, replace with 0
        if (input.isEmpty())
            return 0;

        return Integer.parseInt(input);
    }

    /**
     * Highlights the cell yellow a complete path
     */
    public void highlightYellow() {
        setBackgroundColor(getResources().getColor(R.color.yellow));
    }

    /**
     * Highlights the cell red to show incomplete path
     */
    public void highlightRed() {
        setBackgroundColor(getResources().getColor(R.color.red));
    }

    /**
     * Removes the highlight
     */
    public void removeHighlight() {
        setBackgroundColor(getResources().getColor(R.color.gray));
    }

    /**
     * Inflates the view, initializes its children, and applies layout parameters
     * @param context the context in which the view is being created
     */
    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        MatrixCellViewBinding binding = MatrixCellViewBinding.inflate(layoutInflater, this, true);

        mCellEditText = binding.cellEditText;

        mCellEditText.setInputType(TYPE_CLASS_NUMBER);

        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        setLayoutParams(params);
    }
}