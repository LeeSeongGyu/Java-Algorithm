package BOJ.ExhaustiveSearch.P12100_2048;

import java.io.*;
import java.util.*;

public class Main {

    static int n, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];
        max = 0;

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, board);

        bw.write("" + max);
        bw.flush();
        bw.close();
    }

    public static void solution(int cnt, int[][] board){
        if(cnt == 5){
            return;
        }

        solution(cnt + 1, up(board));
        solution(cnt + 1, down(board));
        solution(cnt + 1, left(board));
        solution(cnt + 1, right(board));
    }

    public static int[][] up(int[][] board){
        int[][] nb = new int[n][n];
        for(int j = 0; j < n; j++){
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = board[i][j];
            }
            int[] na = move(arr);
            for(int i = 0; i < n; i++){
                nb[i][j] = na[i];
            }
        }
        return nb;
    }

    public static int[][] down(int[][] board){
        int[][] nb = new int[n][n];
        for(int j = 0; j < n; j++){
            int[] arr = new int[n];
            for(int i = n - 1; i >= 0; i--){
                arr[n - i - 1] = board[i][j];
            }
            int[] na = move(arr);
            for(int i = n - 1; i >= 0; i--){
                nb[i][j] = na[n - i - 1];
            }
        }
        return nb;
    }

    public static int[][] left(int[][] board){
        int[][] nb = new int[n][n];
        for(int i = 0; i < n; i++){
            int[] arr = new int[n];
            for(int j = 0; j < n; j++){
                arr[j] = board[i][j];
            }
            int[] na = move(arr);
            for(int j = 0; j < n; j++){
                nb[i][j] = na[j];
            }
        }
        return nb;
    }

    public static int[][] right(int[][] board){
        int[][] nb = new int[n][n];
        for(int i = 0; i < n; i++){
            int[] arr = new int[n];
            for(int j = n - 1; j >= 0; j--){
                arr[n - j - 1] = board[i][j];
            }
            int[] na = move(arr);
            for(int j = n - 1; j >= 0; j--){
                nb[i][j] = na[n - j - 1];
            }
        }
        return nb;
    }


    public static int[] move(int[] arr){
        int[] na = new int[arr.length];
        int index = 0;
        int recent = 0;

        for(int i = 0; i < arr.length; i++){
            if(arr[i] != 0){
                if(arr[i] == recent){
                    na[index - 1] = recent * 2;
                    max = Math.max(max, recent * 2);
                    recent = 0;
                } else {
                    na[index++] = arr[i];
                    max = Math.max(max, arr[i]);
                    recent = arr[i];
                }
            }
        }

        return na;
    }
}
