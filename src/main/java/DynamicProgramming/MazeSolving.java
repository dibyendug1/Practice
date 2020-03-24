package DynamicProgramming;

/**
 * Maze with r row and c col
 * Start 0,0
 * End r,c
 * Find a Path
 */
public class MazeSolving {
  public static void main(String[] args) {
    int[][] maze = { { 1, 1, 1, 1 },
                     { 0, 0, 1, 1 },
                     { 1, 1, 1, 1 },
                     { 1, 0, 0, 1 },
                     { 1, 1, 1, 1 }};
    if (!findPath(maze, 0, 0, 5 - 1, 4 - 1)) {
      System.out.println("No path exists.");
    }
    int pathLength = findShortestPath(maze, 0, 0, 5 - 1, 4 - 1);
    if (pathLength == Integer.MAX_VALUE) {
      System.out.println("No path exists.");
    } else {
      System.out.println(pathLength);
    }
  }

  private static boolean findPath(int[][] maze, int i, int j, int r, int c) {
    if (i == r && j == c) {
      System.out.println("[" + i + " ," + j + "]");
      return true;
    }

    if (validMove(maze, i, j + 1) && findPath(maze, i, j + 1, r, c)) {
      System.out.println("[" + i + " ," + j + "]");
      return true;
    }

    if (validMove(maze, i + 1, j) && findPath(maze, i + 1, j, r, c)) {
      System.out.println("[" + i + " ," + j + "]");
      return true;
    }
    return false;
  }

  private static int findShortestPath(int[][] maze, int i, int j, int r, int c) {
    if (i == r && j == c) {
      //System.out.println("[" + i + " ," + j + "]");
      return 1;
    }

    int right = Integer.MAX_VALUE;
    if (validMove(maze, i, j + 1)){
      right = findShortestPath(maze, i, j + 1, r, c);
    }
    int down = Integer.MAX_VALUE;
    if( validMove(maze, i + 1, j)){
      down = findShortestPath(maze, i + 1, j, r, c);
    }

    if (right < down & right != Integer.MAX_VALUE) {
      return right + 1;
    } else if (down != Integer.MAX_VALUE) {
      return down + 1;
    }

    return Integer.MAX_VALUE;
  }

  private static boolean validMove(int[][] maze, int i, int j) {
    int n = maze.length;
    int m = maze[0].length;
    if (i >= 0 && i < n && j >= 0 & j < m && maze[i][j] == 1) {
      return true;
    }
    return false;
  }
}
