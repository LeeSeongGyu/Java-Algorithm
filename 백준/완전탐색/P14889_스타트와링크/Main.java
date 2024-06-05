package 백준.완전탐색.P14889_스타트와링크;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] s;
    static int min = Integer.MAX_VALUE;
    static int[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        s = new int[n][n];
        team = new int[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);

        bw.write("" + min);
        bw.flush();
        bw.close();
    }

    public static void DFS(int size, int index) {
        if (size == n / 2) {
            min = Math.min(min, getAbilityDiff());
            return;
        }
        for (int i = index; i < n; i++) {
            team[i] = 1;
            DFS(size + 1, i + 1);
            team[i] = 0;
        }
    }

    public static int getAbilityDiff(){
        int[] link = new int[n/2];
        int[] start = new int[n/2];
        int link_index = 0;
        int start_index = 0;

        for(int i = 0; i < n; i++){
            if(team[i] == 0){
                link[link_index++] = i;
            } else {
                start[start_index++] = i;
            }
        }

        return Math.abs(getAbility(link) - getAbility(start));

    }

    public static int getAbility(int[] arr) {
        int ability = 0;

        for (int i : arr) {
            for (int j : arr) {
                ability += s[i][j];
            }
        }
        return ability;
    }
}
