package BOJ.P17822_원판돌리기;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, t;
    static int[][] scores, rotates;
    static int[] xMoves = {-1, 1, 0, 0};
    static int[] yMoves = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        scores = new int[n + 1][m + 1];
        rotates = new int[t][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotate(x, d, k);
            if (!delete()) {
                normalize();
            }
        }

        bw.write("" + getSum());
        bw.flush();
        bw.close();
    }

    public static void rotate(int x, int d, int k) {
        for(int u = x; u <= n; u++) {
            if (u % x == 0) {
                if (d == 0) {
                    for (int i = 0; i < k; i++) {
                        clockwise(u);
                    }
                }

                if (d == 1) {
                    for (int i = 0; i < k; i++) {
                        counterClockwise(u);
                    }
                }
            }
        }
    }

    public static void clockwise(int x) {
        int tmp = scores[x][m];

        for (int i = m; i > 0; i--) {
            scores[x][i] = scores[x][i - 1];
        }

        scores[x][1] = tmp;
    }

    public static void counterClockwise(int x) {
        int tmp = scores[x][1];

        for (int i = 1; i < m; i++) {
            scores[x][i] = scores[x][i + 1];
        }

        scores[x][m] = tmp;
    }

    public static boolean delete() {
        boolean find = false;

        int[][] copy = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                copy[i][j] = scores[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (scores[i][j] == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int nx = i + xMoves[k];
                    int ny = j + yMoves[k];
                    if (nx <= 0 || nx > n) {
                        continue;
                    }
                    if (ny == 0) {
                        ny = m;
                    }
                    if (ny == m + 1) {
                        ny = 1;
                    }

                    if (scores[i][j] == scores[nx][ny]) {
                        find = true;
                        copy[i][j] = 0;
                        copy[nx][ny] = 0;
                    }
                }
            }
        }

        scores = copy;

        return find;
    }

    public static int getSum() {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum += scores[i][j];
            }
        }

        return sum;
    }

    public static float getAverage() {
        float sum = 0;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (scores[i][j] != 0) {
                    count++;
                    sum += scores[i][j];
                }
            }
        }

        if(count == 0){
            return 0;
        }

        return sum / count;
    }

    public static void normalize() {
        float avg = getAverage();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (scores[i][j] == 0) {
                    continue;
                }
                if (scores[i][j] > avg) {
                    scores[i][j]--;
                } else if (scores[i][j] < avg) {
                    scores[i][j]++;
                }
            }
        }
    }
}