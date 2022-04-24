package com.he;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Islands {
    // No of rows and columns

    // A function to check if a given cell (row, col) can
    // be included in DFS
    boolean isSafe(List<List<Integer>> matx, int row, int col,
                   List<List<Boolean>> visited) {
        // row number is in range, column number is in range
        // and value is 1 and not yet visited
        return (row >= 0) && (row < matx.size()) && (col >= 0) && (col < matx.get(0).size()) && (matx.get(row).get(col) == 1 && !visited.get(row).get(col));
    }

    // A utility function to do DFS for a 2D boolean matrix.
    // It only considers the 8 neighbors as adjacent vertices
    void DFS(List<List<Integer>> matx, int row, int col, List<List<Boolean>> visited) {

        List<Integer> rowNbr = Arrays.asList(-1, -1, -1, 0, 0, 1, 1, 1);
        List<Integer> colNbr = Arrays.asList(-1, 0, 1, -1, 1, -1, 0, 1);

        // Mark this cell as visited
        Boolean b = visited.get(row).get(col);
        b = Boolean.TRUE;

        // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
            if (isSafe(matx, row + rowNbr.get(k), col + colNbr.get(k), visited))
                DFS(matx, row + rowNbr.get(k), col + colNbr.get(k), visited);
    }

    // The main function that returns count of islands in a given
    // boolean 2D matrix
    int countIslands(List<List<Integer>> matrix) {
        // Make a bool array to mark visited cells.
        // Initially all cells are unvisited
        List<List<Boolean>> visited = //new ArrayList<List<Boolean>>();
                    Arrays.asList(
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false));

        // Initialize count as 0 and travese through the all cells
        // of given matrix
        int count = 0;
        for (int i = 0; i < matrix.size(); ++i)
            for (int j = 0; j < matrix.get(i).size(); ++j)
                if (matrix.get(i).get(j) == 1 && !visited.get(i).get(j)) // If a cell with
                {
                    DFS(matrix, i, j, visited);
                    ++count;
                }

        return count;
    }

    // Driver method
    public static void main(String[] args) throws Exception {
        List<List<Integer>> matx = Arrays.asList(
                Arrays.asList(1, 1, 0, 0, 0),
                Arrays.asList(0, 1, 0, 0, 1),
                Arrays.asList(1, 0, 0, 1, 1),
                Arrays.asList(0, 0, 0, 0, 0),
                Arrays.asList(1, 0, 1, 0, 1));
        Islands I = new Islands();
        System.out.println("Number of islands is: " + I.countIslands(matx));
    }
}