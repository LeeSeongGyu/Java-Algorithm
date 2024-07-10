package BOJ.Greedy.P7570_줄세우기;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution(){
        int[] dp = new int[n + 1];

        for(int i = 0; i < n; i++){
            dp[arr[i]] = dp[arr[i] - 1] + 1;
        }

        return n - Arrays.stream(dp).max().getAsInt();
    }
}
