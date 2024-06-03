package 백준.시뮬레이션.P17140_이차원배열과연산;

import java.io.*;
import java.util.*;

public class Main {

    static int r, c, k;
    static int[][] board = new int[100][100];
    static int row = 3;
    static int col = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        int count = 0;
        while (board[r - 1][c - 1] != k) {
            if(count == 100) return -1;
            if (row >= col) {
                R();
            } else {
                C();
            }
            count++;
        }
        return count;
    }

    public static void R() {
        int max_col = col;
        for (int i = 0; i < row; i++) {
            int[] arr = new int[col];
            for (int j = 0; j < col; j++) {
                arr[j] = board[i][j];
            }
            List<Integer> sort = sort(arr);
            for(int k = 0; k < 100; k++){
                if(sort.size() - 1 < k){
                    board[i][k] = 0;
                } else board[i][k] = sort.get(k);
            }
            max_col = Math.max(max_col, sort.size());
        }

        col = max_col;
    }

    public static void C() {
        int max_row = row;
        for (int i = 0; i < col; i++) {
            int[] arr = new int[row];
            for (int j = 0; j < row; j++) {
                arr[j] = board[j][i];
            }
            List<Integer> sort = sort(arr);
            for(int k = 0; k < 100; k++){
                if(sort.size() -1 < k){
                    board[k][i] = 0;
                } else board[k][i] = sort.get(k);
            }
            max_row = Math.max(max_row, sort.size());
        }

        row = max_row;
    }

    public static List<Integer> sort(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Count> counts = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();

        for (int i : arr) {
            if(i != 0){
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }

        for (int key : map.keySet()) {
            counts.add(new Count(key, map.get(key)));
        }

        Collections.sort(counts);
        for (Count c : counts) {
            newList.add(c.value);
            if (newList.size() > 100) {
                break;
            }
            newList.add(c.count);
            if (newList.size() > 100) {
                break;
            }
        }

        return newList;
    }

    public static class Count implements Comparable<Count> {

        int value;
        int count;

        public Count(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Count c) {
            if (this.count == c.count) {
                return this.value - c.value;
            }
            return this.count - c.count;
        }
    }
}
