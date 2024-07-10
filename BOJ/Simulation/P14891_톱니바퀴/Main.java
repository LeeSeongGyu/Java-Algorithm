package BOJ.Simulation.P14891_톱니바퀴;

import java.io.*;
import java.util.*;

public class Main {

    static int k;
    static int[][] origin, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        origin = new int[4][8];
        copy = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                origin[i][j] = str.charAt(j) - '0';
                copy[i][j] = str.charAt(j) - '0';
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken()) - 1;
            boolean direction = Integer.parseInt(st.nextToken()) == 1;
            solution(num, direction);
        }

        int score = origin[0][0] + origin[1][0] * 2 + origin[2][0] * 4 + origin[3][0] * 8;

        bw.write("" + score);
        bw.flush();
        bw.close();
    }

    public static void solution(int num, boolean direction){
        rotate(copy[num], direction);
        right(num, direction);
        left(num, direction);

        for(int j = 0; j < 4; j++){
            origin[j] = copy[j].clone();
        }
    }

    // 오른쪽에 있는 톱니바퀴 확인
    public static void right(int num, boolean direction) {
        if(num + 1 < 4 && origin[num][2] != origin[num + 1][6]){
            rotate(copy[num + 1], !direction);
            right(num + 1, !direction);
        }
    }

    // 왼쪽에 있는 톱니바퀴 확인
    public static void left(int num, boolean direction) {
        if(num - 1 >= 0 && origin[num][6] != origin[num - 1][2]){
            rotate(copy[num - 1], !direction);
            left(num - 1, !direction);
        }
    }

    public static void rotate(int[] gear, boolean direction) {
        // 시계 방향
        if (direction) {
            int tmp = gear[7];
            for (int i = 7; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = tmp;

        }
        // 반시계 방향
        if (!direction) {
            int tmp = gear[0];
            for (int i = 0; i < 7; i++) {
                gear[i] = gear[i + 1];
            }
            gear[7] = tmp;
        }
    }
}
