package 백준.시뮬레이션.P11559_PuyoPuyo;

import java.io.*;
import java.util.*;

public class Main {

    static char[][] board = new char[12][6];
    static int count = 0;

    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        solution();

        bw.write("" + count);
        bw.flush();
        bw.close();
    }

    public static void solution() {
        boolean find = true;

        while (find) {
            find = false;
            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] != '.') {
                        if (delete(i, j)) {
                            find = true;
                        }
                    }
                }
            }
            if (find) {
                gravity();
                count++;
            }
        }
    }

    public static boolean delete(int x, int y) {
        List<int[]> list = new ArrayList<>();
        int[][] ch = new int[12][6];

        find(x, y, list, ch);

        if (list.size() > 3) {
            for (int[] arr : list) {
                board[arr[0]][arr[1]] = '.';
            }
            return true;
        }
        return false;
    }

    public static void find(int x, int y, List<int[]> list, int[][] ch) {
        list.add(new int[]{x, y});
        ch[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + xMoves[i];
            int ny = y + yMoves[i];
            if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && board[x][y] == board[nx][ny]
                && ch[nx][ny] == 0) {
                find(nx, ny, list, ch);
            }
        }
    }

    public static void gravity() {
        for (int y = 0; y < 6; y++) {
            List<Character> list = new ArrayList<>();
            for (int x = 11; x >= 0; x--) {
                if (board[x][y] != '.') {
                    list.add(board[x][y]);
                    board[x][y] = '.';
                }
            }
            int index = 11;
            for (char c : list) {
                board[index--][y] = c;
            }
        }
    }
}
