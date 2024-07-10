package BOJ.ExhaustiveSearch.P2667_단지번호붙이기;

import java.util.*;

public class Main {

    static int[][] board;
    static int n;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public void solution() {
        List<Integer> list = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    list.add(DFS(i, j));
                    count++;
                }
            }
        }
        Collections.sort(list);
        System.out.println(count);
        for(int i : list){
            System.out.println(i);
        }
    }

    public int DFS(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n || board[x][y] == 0) {
            return 0;
        }
        board[x][y] = 0;
        int area = 1;
        for (int i = 0; i < 4; i++) {
            area += DFS(x + xMoves[i], y + yMoves[i]);
        }

        return area;
    }

    public static void main(String[] args) {
        Main T = new Main();
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = in.next();
            for (int j = 0; j < n; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        T.solution();
    }
}
