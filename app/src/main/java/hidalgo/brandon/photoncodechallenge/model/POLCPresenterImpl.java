package hidalgo.brandon.photoncodechallenge.model;

import java.util.Arrays;

import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

/**
 * A class to handle the business logic form finding the path of lowest cost in a given matrix.
 */
public class POLCPresenterImpl implements POLCPresenter {
    private POLCView mView;

    public POLCPresenterImpl(POLCView view) {
        mView = view;
    }

    /**
     * Builds a cost matrix from a given matrix by calculating the minimum cost for each index backwards.
     * This implies that when traversing the cost matrix forwards, you know which rows to traverse given
     * that each element represents the subsequent cost of that index.
     *
     * @param matrix the original matrix which will be represented by the cost matrx
     * @return a cost matrix
     */
    private int[][] buildCostMatrix(int[][] matrix) {
        //Determine the size of the matrix
        int numRows = matrix.length;

        int numCols = matrix[0].length;

        //Create an empty cost matrix
        int[][] costMatrix = new int[numRows][numCols];

        //Initialize the last column of the cost matrix.
        //The last column of the cost matrix is just the last column of the original matrix
        //since there are no elements beyond the last column
        for (int i = 0; i < numRows; i++) {
            costMatrix[i][numCols - 1] = matrix[i][numCols - 1];
        }

        //Starting from the second to last column, use the columns that come after to calculate the costs within the current column
        for (int i = numCols - 2; i >= 0; i--) {
            for (int j = 0; j < numRows; j++) {
                //For reach row, we need to know the cost of the three possible paths (Diagonally Up, Straight ahead, Diagonally Down)
                int diagUp = j - 1;

                //Check bounds
                if (diagUp < 0)
                    diagUp = numRows - 1;

                int straight = j;

                int diagDown = j + 1;

                //Check bounds
                if (diagDown == numRows)
                    diagDown = 0;

                //The cost of the current index is equal to the element within the original matrix and the minimum cost of the three possible paths
                costMatrix[j][i] = matrix[j][i] + min(costMatrix[diagUp][i + 1], costMatrix[straight][i + 1], costMatrix[diagDown][i + 1]);
            }
        }

        return costMatrix;
    }

    @Override
    public void computePOLC(String[][] matrix) {
        //First make sure we have a valid matrix
        if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0)
            mView.showInvalidMatrixError();
        else {
            int rows = matrix.length;

            int columns = matrix[0].length;

            int[][] integerMatrix = new int[rows][columns];

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    try {
                        integerMatrix[i][j] = Integer.parseInt(matrix[i][j]);
                    }
                    catch(NumberFormatException e) {
                        mView.showInvalidMatrixError();

                        return;
                    }
                }
            }

            findPOLCMatrix(integerMatrix);
        }
    }

    /**
     * Finds the path with the lowest cost within a two dimensional array matrix
     *
     * @param matrix the matrix which will be used to find the path of lowest cost
     */
    private void findPOLCMatrix(int[][] matrix) {
        //Build a cost matrix from matrix
        int[][] costMatrix = buildCostMatrix(matrix);

        //Use the cost matrix to determine the path of lowest cost
        traverseCostMatrixForPOLC(matrix, costMatrix);
    }

    /**
     * Compares three integers and returns the smallest
     *
     * @param x integer one
     * @param y integer two
     * @param z integer three
     * @return the smallest integer
     */
    private int min(int x, int y, int z) {
        if (x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    }

    /**
     * Compares three rows within a certain column inside a cost matrix and returns the row with the smallest cost
     *
     * @param costMatrix the cost matrix containing the rows to be compared
     * @param col        the value of the column containing the rows
     * @param row1       the value of the first row
     * @param row2       the value of the second row
     * @param row3       the value of the fourth row
     * @return the index of the row with the smallest cost
     */
    private int minCostIndex(int[][] costMatrix, int col, int row1, int row2, int row3) {
        if (costMatrix[row1][col] < costMatrix[row2][col])
            return (costMatrix[row1][col] < costMatrix[row3][col]) ? row1 : row3;
        else
            return (costMatrix[row2][col] < costMatrix[row3][col]) ? row2 : row3;
    }

    /**
     * Finds the path of lowest cost for a matrix by traversing its cost matrix
     *
     * @param matrix     the original matrix for which we want to find the path of lowest cost
     * @param costMatrix a matrix representing the subsequent costs for each index
     */
    private void traverseCostMatrixForPOLC(int[][] matrix, int[][] costMatrix) {
        //Determine the size of the matrices
        int numRows = costMatrix.length;

        int numCols = costMatrix[0].length;

        //Traverse each row within the first column and see which has the smallest cost
        int lowestCostStart = costMatrix[0][0];

        int lowestCostStartRow = 0;

        for (int row = 1; row < numRows; row++) {
            if (costMatrix[row][0] < lowestCostStart) {
                lowestCostStart = costMatrix[row][0];

                lowestCostStartRow = row;
            }
        }

        //If the smallest cost in the first row is more than fifty, that means that there is no path and triggers a failure
        if (matrix[lowestCostStartRow][0] > 50) {
            mView.showFailure(0, new int[]{});

            return;
        }

        //If the smallest cost is less than fifty, we can continue finding the path
        //Start from the row with the lowest cost
        int currentRow = lowestCostStartRow;

        //The current cost will start as the value of the element with the lowest subsequent cost
        int currentCost = matrix[currentRow][0];

        //Initialize the path with the first step
        int[] path = new int[]{lowestCostStartRow + 1};

        //Traverse the rest of the matrix one column at a time
        for (int col = 1; col < numCols; col++) {

            //We need to find the minimum cost from the three possible paths a step can take when going from column to column
            int diagUp = currentRow - 1;

            if (diagUp < 0)
                diagUp = numRows - 1;

            int straight = currentRow;

            int diagDown = currentRow + 1;
            if (diagDown == numRows)
                diagDown = 0;

            //choose the row with the smallest cost in the next column
            currentRow = minCostIndex(costMatrix, col, diagUp, straight, diagDown);

            //Make sure our current cost does not exceed 50
            if (currentCost + matrix[currentRow][col] <= 50) {
                //If we are still under fifty, update the cost and update the path
                currentCost += matrix[currentRow][col];

                //This will make sure our array will grow with each step
                path = Arrays.copyOf(path, path.length + 1);

                //Add the current row at the end of our path
                path[path.length - 1] = currentRow + 1;
            } else {
                //If we exceed fifty, trigger a failure and terminate
                mView.showFailure(currentCost, path);

                return;
            }
        }

        //The path with the lowest cost is below fifty, trigger a success
        mView.showSuccess(lowestCostStart, path);
    }
}
