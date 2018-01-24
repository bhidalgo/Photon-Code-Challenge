package hidalgo.brandon.photoncodechallenge;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MatrixCellView extends View {
    private ViewSwitcher mCellViewSwitcher;

    private EditText mCellEditText;

    private TextView mCellTextView;

    public MatrixCellView(Context context) {
        super(context);
    }

    public MatrixCellView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixCellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MatrixCellView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        //inflate(context, R.layout.matrix_cell_view, this);
    }
}
