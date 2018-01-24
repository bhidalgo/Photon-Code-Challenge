package hidalgo.brandon.photoncodechallenge.view;

public interface POLCView {
    void showFailure(int cost, int[] path);

    void showMatrix(int[][] matrix);

    void showPOLC(int path);

    void showSuccess(int cost, int[] path);
}
