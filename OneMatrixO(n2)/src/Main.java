import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 15; // change this to the size of the matrix
        int numMatrices = 100000;
        int[][][] matrices = new int[numMatrices][n][n];
        Random rand = new Random();
        createRandomMatrices(n, numMatrices, matrices, rand);
        maxRectangleFinder(numMatrices, matrices);
    }

    private static void maxRectangleFinder(int numMatrices, int[][][] matrices) {
        // run maxRectangleBruteForce on each matrix and print the result
        for (int i = 0; i < numMatrices; i++) {
            int[][] matrix = matrices[i];
//            System.out.println("Matrix " + (i+1) + ":");
//            printMatrix(matrix);
            int maxArea = findMaxRectangularOfOnes(matrix);
//            System.out.println("Maximum rectangular area of 1s in the matrix: " + maxArea);
//            System.out.println();
        }
    }

    private static void createRandomMatrices(int n, int numMatrices, int[][][] matrices, Random rand) {
        // create 10 random binary matrices
        for (int i = 0; i < numMatrices; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int random =  rand.nextInt(4);
                    if (random > 0)
                        matrices[i][j][k] = 1;
                }
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int findMaxRectangularOfOnes(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n]; // dp[i][j] stores the maximum height of the rectangle with matrix[i][j] as the bottom-right corner
        int maxArea = 0;

        // Initialize the first row and column of dp
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
            dp[0][i] = matrix[0][i];
        }

        // Calculate the maximum height of the rectangle with matrix[i][j] as the bottom-right corner
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        // Calculate the maximum area of the rectangle
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int area = dp[i][j] * dp[i][j];
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;
    }
}