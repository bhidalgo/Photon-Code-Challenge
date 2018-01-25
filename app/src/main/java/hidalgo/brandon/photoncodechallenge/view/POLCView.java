package hidalgo.brandon.photoncodechallenge.view;

/**
 * The MVP view interface to be implemented by UI components
 */
public interface POLCView {
    void showFailure(int cost, int[] path);

    void showSuccess(int cost, int[] path);
}
