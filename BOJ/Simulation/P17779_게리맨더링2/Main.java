package BOJ.Simulation.P17779_게리맨더링2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, min_diff = Integer.MAX_VALUE;
    static int[][] population;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        population = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 2; x < n; x++) {
            for (int y = 2; y < n; y++) {
                int d1_length = Math.min(n - x, y - 1);
                for (int d1 = 1; d1 <= d1_length; d1++) {
                    int d2_length = Math.min(n - y, n - x - d1);
                    for (int d2 = 1; d2 <= d2_length; d2++) {
                        solution(x, y, d1, d2);
                    }
                }
            }
        }

        bw.write("" + min_diff);
        bw.flush();
        bw.close();
    }

    public static void solution(int x, int y, int d1, int d2) {
        int[] arr = new int[5];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (Math.max(y - r + x, y - x + r - 2 * d1) <= c && c <= Math.min(y + r - x, y + x - r + 2 * d2)) {
                    arr[0] += population[r][c];
                } else if (r < x + d1 && c <= y) {
                    arr[1] += population[r][c];
                } else if (r <= x + d2 && y < c) {
                    arr[2] += population[r][c];
                } else if (x + d1 <= r && c < y - d1 + d2) {
                    arr[3] += population[r][c];
                } else if (x + d2 < r && y - d1 + d2 <= c) {
                    arr[4] += population[r][c];
                }
            }
        }

        Arrays.sort(arr);
        min_diff = Math.min(min_diff, arr[4] - arr[0]);
    }
}

