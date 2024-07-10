package BOJ.DynamicProgramming.P12852_1로만들기2;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][2];

        for(int[] arr: dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dp[n][1] = 0;

        recursive(n);
        bw.write(dp[1][1] + "\n");
        bw.write(backtrace());

        bw.flush();
        bw.close();
    }

    public static void recursive(int i){
        if(i % 3 == 0 && dp[i/3][1] > dp[i][1] + 1){
            dp[i/3][0] = i;
            dp[i/3][1] = dp[i][1] + 1;
            recursive(i/3);
        }

        if(i % 2 == 0 && dp[i/2][1] > dp[i][1] + 1){
            dp[i/2][0] = i;
            dp[i/2][1] = dp[i][1] + 1;
            recursive(i/2);
        }

        if(i > 1 && dp[i-1][1] > dp[i][1] + 1){
            dp[i - 1][0] = i;
            dp[i - 1][1] = dp[i][1] + 1;
            recursive(i - 1);
        }
    }

    public static String backtrace(){
        Stack<Integer> stack = new Stack<>();
        int index = 1;

        while(index != n){
            stack.push(index);
            index = dp[index][0];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }
}
