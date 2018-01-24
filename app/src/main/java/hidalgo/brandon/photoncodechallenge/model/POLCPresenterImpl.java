package hidalgo.brandon.photoncodechallenge.model;

import java.util.Arrays;

import hidalgo.brandon.photoncodechallenge.presenter.POLCPresenter;
import hidalgo.brandon.photoncodechallenge.view.POLCView;

public class POLCPresenterImpl implements POLCPresenter {
    private POLCView mView;

    public POLCPresenterImpl(POLCView view) {
        mView = view;
    }

    @Override
    public void computePOLC(int[][] matrix) {
        int numRows = matrix.length;

        int numCols = matrix[0].length;

        //COLUMN MATRIX
        if(numRows == 1) {
            //1x1 MATRIX
            if(numCols == 1) {
                findPOLCSingleItem(matrix);
            }
            //1xN MATRIX
            else
            {
                findPOLCRow(matrix, numCols);
            }
        }
        //ROW MATRIX
        else if(numCols == 1) {
            findPOLCColumn(matrix, numRows);
        }
        //NxN MATRIX
        else {
            findPOLCMatrix(matrix);
        }
    }

    private void findPOLCMatrix(int[][] matrix) {
        int[][] costMatrix = buildCostMatrix(matrix);

        traverseCostMatrixForPOLC(matrix, costMatrix);
    }

    private void findPOLCSingleItem(int[][] matrix) {
        if(matrix[0][0] < 50)
            mView.showSuccess(matrix[0][0], new int[]{1});
        else
            mView.showFailure(0, new int[]{});
    }

    private void findPOLCRow(int[][] matrix, int numCols) {
        int pathCost = 0;

        int[] path = new int[numCols];

        for(int i = 0; i < numCols; i++) {
            if(pathCost + matrix[0][i] < 50) {
                pathCost += matrix[0][i];

                path[i] = 1;
            }
            else
                mView.showFailure(pathCost, path);
        }

        mView.showSuccess(pathCost, path);
    }

    private void findPOLCColumn(int[][] matrix, int numRows) {
        int rowOfPathCost = 0;

        int pathCost = matrix[0][0];

        for(int i = 0; i < numRows; i++) {
            if(matrix[i][0] < pathCost) {
                pathCost = matrix[i][0];

                rowOfPathCost = i + 1;
            }
        }

        if(pathCost <= 50)
            mView.showSuccess(pathCost, new int[]{rowOfPathCost});
        else
            mView.showFailure(0, new int[]{});
    }

    private void traverseCostMatrixForPOLC(int[][] matrix, int[][] costMatrix) {
        int numRows = costMatrix.length;

        int numCols = costMatrix[0].length;

        int lowestCostStart = costMatrix[0][0];

        int lowestCostStartRow = 1;

        for(int row = 1; row < numRows; row++) {
            if(costMatrix[row][0] < lowestCostStart) {
                lowestCostStart = costMatrix[row][0];

                lowestCostStartRow = row + 1;
            }
        }

        if(matrix[lowestCostStartRow - 1][0] > 50) {
            mView.showFailure(0, new int[]{});

            return;
        }

        int currentRow = lowestCostStartRow - 1;

        int currentCost = matrix[currentRow][0];

        int[] path = new int[]{lowestCostStartRow};

        for(int col = 1; col < numCols; col++) {
            int diagUp = currentRow - 1;

            if(diagUp < 0)
                diagUp = numRows - 1;

            int straight = currentRow;

            int diagDown = currentRow + 1;
            if(diagDown == numRows)
                diagDown = 0;

            currentRow = minCostIndex(costMatrix, col, diagUp, straight, diagDown);

            if(currentCost + matrix[currentRow][col] < 50) {
                currentCost += matrix[currentRow][col];

                path = Arrays.copyOf(path, path.length + 1);

                path[path.length - 1] = currentRow + 1;
            }
            else
            {
                mView.showFailure(currentCost, path);

                return;
            }
        }

        mView.showSuccess(lowestCostStart, path);
    }

    private int minCostIndex(int[][] costMatrix, int col, int row1, int row2, int row3) {
        if(costMatrix[row1][col] < costMatrix[row2][col])
            return (costMatrix[row1][col] < costMatrix[row3][col]) ? row1 : row3;
        else
            return (costMatrix[row2][col] < costMatrix[row3][col]) ? row2 : row3;
    }

    private int[][] buildCostMatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        int[][] costMatrix = new int[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            costMatrix[i][numCols - 1] = matrix[i][numCols - 1];
        }

        for(int i = numCols -2 ; i >= 0; i--) {
            for(int j = 0; j < numRows; j++) {
                int diagUp = j - 1;
                if(diagUp < 0)
                    diagUp = numRows - 1;

                int straight = j;

                int diagDown = j + 1;
                if(diagDown == numRows)
                    diagDown = 0;

                costMatrix[j][i] = matrix[j][i] + min(costMatrix[diagUp][i + 1], costMatrix[straight][i + 1], costMatrix[diagDown][i + 1]);
            }
        }

        return costMatrix;
    }

    private int min(int x, int y, int z){
        if(x < y)
            return (x < z) ? x : z;
        else
            return (y < z) ? y : z;
    }
}
