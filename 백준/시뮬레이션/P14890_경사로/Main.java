package 백준.시뮬레이션.P14890_경사로;

import java.io.*;
import java.util.*;

public class Main {

    static int n, l;
    static int[][] board;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            if (cross(board[i])) {
                count++;
            }
            if (cross(getColumn(board, i))) {
                count++;
            }
        }

        bw.write("" + count);
        bw.flush();
        bw.close();
    }

    public static int[] getColumn(int[][] array, int columnIndex) {
        int[] column = new int[n];
        for (int i = 0; i < n; i++) {
            column[i] = array[i][columnIndex];
        }
        return column;
    }

    public static boolean cross(int[] arr) {
        int index = 0;
        int[] ch = new int[n];

        while (true) {
            // 현재 위치가 마지막이면
            if (index == n - 1) {
                return true;
            }
            // 다음 발판의 높이가 같으면 전진
            if (arr[index] == arr[index + 1]) {
                index++;
                continue;
            }
            // 다음 발판이 한 칸 아래일 때
            if (arr[index] - 1 == arr[index + 1]) {
                // 아래로 내려가는 발판을 깔 수 있다면 이동
                if (putDescendingBoard(arr, ch, index + 1)) {
                    index += l;
                    continue;
                } else { // 깔 수 없다면
                    return false;
                }
            }
            // 다음 발판이 한 칸 위일 때
            if (arr[index] + 1 == arr[index + 1]) {
                // 위로 올라가는 발판을 깔 수 있다면 이동
                if (putAscendingBoard(arr, ch, index - l + 1)) {
                    index++;
                    continue;
                } else { // 깔 수 없다면
                    return false;
                }
            }
            return false;
        }
    }

    // 현재 위치(index)부터 아래로 내려가는 발판을 깔 수 있는 지 확인
    public static boolean putDescendingBoard(int[] arr, int[] ch, int index) {
        for (int i = 0; i < l; i++) {
            if (index + i >= n || ch[index + i] == 1 || arr[index] != arr[index + i]) {
                return false;
            }
        }
        for (int i = 0; i < l; i++) {
            ch[index + i] = 1;
        }
        return true;
    }

    // 현재 위치(index)부터 위로 올라가는 발판을 깔 수 있는 지 확인
    public static boolean putAscendingBoard(int[] arr, int[] ch, int index) {
        if (index < 0) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            if (ch[index + i] == 1 || arr[index] != arr[index + i]) {
                return false;
            }
        }
        for (int i = 0; i < l; i++) {
            ch[index + i] = 1;
        }
        return true;
    }
}
