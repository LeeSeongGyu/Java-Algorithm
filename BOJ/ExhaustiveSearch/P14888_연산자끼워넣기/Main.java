package BOJ.ExhaustiveSearch.P14888_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class Main {

    static int n, min, max;
    static int[] arr, operation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        arr = new int[n];
        operation = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0, 0, 0, arr[0]);

        bw.write(max + "\n" + min);
        bw.flush();
        bw.close();
    }

    public static void dfs(int depth, int add, int sub, int mul, int div, int out) {
        if (depth == n - 1) {
            max = Math.max(max, out);
            min = Math.min(min, out);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (i == 0 && add < operation[i]) {
                dfs(depth + 1, add + 1, sub, mul, div, out + arr[depth + 1]);
            } else if (i == 1 && sub < operation[i]) {
                dfs(depth + 1, add, sub + 1, mul, div, out - arr[depth + 1]);
            } else if (i == 2 && mul < operation[i]) {
                dfs(depth + 1, add, sub, mul + 1, div, out * arr[depth + 1]);
            } else if (i == 3 && div < operation[i]) {
                dfs(depth + 1, add, sub, mul, div + 1, out / arr[depth + 1]);
            }

        }
    }
}
