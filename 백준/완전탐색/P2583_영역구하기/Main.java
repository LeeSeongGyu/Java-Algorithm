package 백준.완전탐색.P2583_영역구하기;

import java.util.*;

public class Main {

    static int[][] board;
    static int m, n, k, count;
    static int[] xMove = {-1, 1, 0, 0};
    static int[] yMove = {0, 0, -1, 1};

    public void addRectangular(int[][] board, int lx, int ly, int rx, int ry) {
        for (int i = lx; i < rx; i++) {
            for (int j = ly; j < ry; j++) {
                board[i][j] = 1;
            }
        }
    }

    public int DFS(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] == 1) {
            return 0;
        }

        board[x][y] = 1;
        int area = 1;
        for (int i = 0; i < 4; i++) {
            area += DFS(x + xMove[i], y + yMove[i]);
        }

        return area;
    }

    public List<Integer> solution() {
        List<Integer> areas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    count++;
                    areas.add(DFS(i, j));
                }
            }
        }

        return areas;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner in = new Scanner(System.in);

        m = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();
        board = new int[n][m];
        count = 0;

        for (int i = 0; i < k; i++) {
            T.addRectangular(board, in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }

        List<Integer> areas = T.solution();
        Collections.sort(areas);

        System.out.println(count);
        for (int a : areas) {
            System.out.print(a + " ");
        }
    }
}
