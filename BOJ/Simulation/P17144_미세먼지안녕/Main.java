package BOJ.Simulation.P17144_미세먼지안녕;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c, t;
    static int[][] board;

    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};
    static int[] acRow = new int[2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[r][c];
        int row = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < c; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    acRow[row++] = i;
                }
            }
        }

        int time = 0;
        while (++time <= t) {
            spread();
            airCleaner();
        }

        bw.write("" + getSum());
        bw.flush();
        bw.close();
    }

    // 미세먼지 확산
    public static void spread() {
        int[][] spread = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] > 0) {
                    int amount = board[i][j] / 5;
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + xMoves[k];
                        int ny = j + yMoves[k];
                        if (nx >= 0 && ny >= 0 && nx < r && ny < c && board[nx][ny] != -1) {
                            spread[nx][ny] += amount;
                            count++;
                        }
                    }
                    spread[i][j] -= amount * count;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] += spread[i][j];
            }
        }
    }

    // 공기청정기 작동
    public static void airCleaner() {
        // 위쪽 반시계방향
        for (int i = acRow[0] - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }
        for (int i = 0; i <= c - 2; i++) {
            board[0][i] = board[0][i + 1];
        }
        for (int i = 0; i <= acRow[0] - 1; i++) {
            board[i][c - 1] = board[i + 1][c - 1];
        }
        for (int i = c - 1; i >= 2; i--) {
            board[acRow[0]][i] = board[acRow[0]][i - 1];
        }
        board[acRow[0]][1] = 0;

        // 아래쪽 시계방향
        for (int i = acRow[1] + 1; i <= r - 2; i++) {
            board[i][0] = board[i + 1][0];
        }
        for (int i = 0; i <= c - 2; i++) {
            board[r - 1][i] = board[r - 1][i + 1];
        }
        for (int i = r - 1; i >= acRow[1] + 1; i--) {
            board[i][c - 1] = board[i - 1][c - 1];
        }
        for (int i = c - 1; i >= 2; i--) {
            board[acRow[1]][i] = board[acRow[1]][i - 1];
        }
        board[acRow[1]][1] = 0;
    }

    // 전체 미세먼지 양 계산
    public static int getSum() {
        int sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += board[i][j];
            }
        }
        return sum + 2;
    }
}
