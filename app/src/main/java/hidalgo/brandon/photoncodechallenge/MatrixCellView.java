package hidalgo.brandon.photoncodechallenge;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import static android.text.InputType.TYPE_CLASS_NUMBER;

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

    public int getInput() {
        String input = mCellEditText.getText().toString();

        if (input.isEmpty())
            input = "0";

        return Integer.parseInt(input);
    }

    public void highlightYellow() {
        setBackgroundColor(getResources().getColor(R.color.yellow));
    }

    public void highlightRed() {
        setBackgroundColor(getResources().getColor(R.color.red));
    }

    public void removeHighlight() {
        setBackgroundColor(getResources().getColor(R.color.gray));
    }

    private void init(Context context) {
        inflate(context, R.layout.matrix_cell_view, this);

        mCellEditText = findViewById(R.id.cellEditText);

        mCellEditText.setInputType(TYPE_CLASS_NUMBER);

        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        setLayoutParams(params);
    }
}
