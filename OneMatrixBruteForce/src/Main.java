import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10; // change this to the size of the matrix
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
        int maxArea = 0;

        // Iterate over all possible rectangles
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {

                        // Check if all elements in the rectangle are 1
                        boolean allOnes = true;
                        for (int m = i; m <= k; m++) {
                            for (int p = j; p <= l; p++) {
                                if (matrix[m][p] != 1) {
                                    allOnes = false;
                                }
                            }
                        }

                        // If all elements in the rectangle are 1, calculate its area
                        if (allOnes) {
                            int width = l - j + 1;
                            int height = k - i + 1;
                            int area = width * height;

                            // Update the maximum area if necessary
                            if (area > maxArea) {
                                maxArea = area;
                            }
                        }
                    }
                }
            }
        }

        return maxArea;
    }
}
