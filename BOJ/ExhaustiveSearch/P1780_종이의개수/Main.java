package BOJ.ExhaustiveSearch.P1780_종이의개수;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;
    static int n;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        count = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0, n - 1, 0, n - 1);
        for (int i : count) {
            bw.write("" + i + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static void recursive(int xStart, int xEnd, int yStart, int yEnd) {
        if (isAllSame(xStart, xEnd, yStart, yEnd)) {
            return;
        }

        int split = (xEnd - xStart + 1) / 3;

        for (int i = xStart; i <= xEnd; i += split) {
            for (int j = yStart; j <= yEnd; j += split) {
                recursive(i, i + split - 1, j, j + split - 1);
            }
        }

    }

    public static boolean isAllSame(int xStart, int xEnd, int yStart, int yEnd) {
        int standard = board[xStart][yStart];

        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                if (board[i][j] != standard) {
                    return false;
                }
            }
        }

        if (standard == -1) {
            count[0]++;
        } else if (standard == 0) {
            count[1]++;
        } else {
            count[2]++;
        }
        return true;
    }
}
