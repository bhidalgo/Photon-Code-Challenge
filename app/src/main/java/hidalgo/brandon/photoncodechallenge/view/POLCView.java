package hidalgo.brandon.photoncodechallenge.view;

/**
 * Created by Brandon Hidalgo on 1/22/2018.
 */

public interface POLCView {
    void showFailure(int cost, int[] path);

    void showPOLC(int path);

    void showSuccess(int cost, int[] path);
}
