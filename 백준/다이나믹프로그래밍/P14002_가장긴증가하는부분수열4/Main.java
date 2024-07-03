package 백준.다이나믹프로그래밍.P14002_가장긴증가하는부분수열4;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static List<Integer>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new List[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i ++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = solution();

        bw.write(list.size() + "\n");
        for(int i : list){
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }

    public static List<Integer> solution(){
        for(int i = 0; i < n; i++){
            dp[i] = List.of(arr[i]);

            int max_size_index = i;
            for(int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i] && dp[j].size() >= dp[max_size_index].size()) {
                    max_size_index = j;
                }
            }

            if(max_size_index == i) continue;

            List<Integer> list = new ArrayList<>(dp[max_size_index]);
            list.add(arr[i]);
            dp[i] = list;
        }

        int max_size_index = 0;
        for(int i = 1; i < n; i++) {
            if (dp[i].size() > dp[max_size_index].size()) {
                max_size_index = i;
            }
        }
        return dp[max_size_index];
    }
}
