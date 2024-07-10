package BOJ.DynamicProgramming.P2156_포도주시식;

import java.io.*;

public class Main {

    static int n;
    static int[] arr;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        dp = new Integer[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        dp[0] = 0;
        dp[1] = arr[1];

        if (n > 1) {
            dp[2] = arr[1] + arr[2];
        }

        return recursive(n);
    }

    public static int recursive(int i) {
        if (dp[i] == null) {
            dp[i] = Math.max(
                recursive(i - 1),
                Math.max(recursive(i - 3) + arr[i - 1], recursive(i - 2)) + arr[i]
            );
        }

        return dp[i];
    }
}
