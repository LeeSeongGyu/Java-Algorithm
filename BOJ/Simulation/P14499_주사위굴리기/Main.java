package BOJ.Simulation.P14499_주사위굴리기;

import java.io.*;
import java.util.*;

public class Main {

    static int n, m, x, y, k;
    static int[] operation, dice;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dice = new int[6];

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        operation = new int[k];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }


        for (int i : solution()) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static List<Integer> solution() {
        int[] dice = new int[6];
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (operation[i] == 1) {
                if(y + 1 >= m) {
                    continue;
                }
                y++;
                int tmp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[2];
                dice[2] = tmp;
            } else if (operation[i] == 2) {
                if(y - 1 < 0) {
                    continue;
                }
                y--;
                int tmp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[4];
                dice[4] = tmp;
            } else if (operation[i] == 3) {
                if(x - 1 < 0) {
                    continue;
                }
                x--;
                int tmp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[1];
                dice[1] = tmp;
            } else {
                if(x + 1 >= n) {
                    continue;
                }
                x++;
                int tmp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = tmp;
            }

            if(map[x][y] == 0){
                map[x][y] = dice[5];
            } else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }

            answer.add(dice[0]);
        }

        return answer;
    }
}
