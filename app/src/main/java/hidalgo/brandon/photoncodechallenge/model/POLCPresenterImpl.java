package hidalgo.brandon.photoncodechallenge.model;

import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

public class POLCPresenterImpl implements POLCPresenter {
    private POLCView mView;

    public POLCPresenterImpl(POLCView view) {
        mView = view;
    }

    @Override
    public void computePOLC(int[][] matrix) {
        //
    }
}
