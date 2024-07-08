package 백준.시뮬레이션.P3190_뱀;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k, l;
    static int[][] board;

    static Map<Integer, Character> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        l = Integer.parseInt(br.readLine());

        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            map.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        bw.write("" + solution());
        bw.flush();
        bw.close();
    }

    public static int solution() {
        board[0][0] = 2;
        int[] head = new int[]{0, 0};
        Queue<int[]> snake = new ArrayDeque<>();
        snake.offer(head);

        int d = 0;
        int time = 0;

        while (true) {
            // 방향 변경
            if (map.containsKey(time)) {
                if (map.get(time) == 'L') {
                    if (d == 0) {
                        d = 3;
                    } else {
                        d--;
                    }
                } else {
                    if (d == 3) {
                        d = 0;
                    } else {
                        d++;
                    }
                }
            }

            int[] next = move(head, d);
            int nx = next[0];
            int ny = next[1];

            // 벽이나 자신을 만났을때
            if (nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 2) {
                return time + 1;
            }

            // 이동할 곳이 비어있을 때, 꼬리 삭제
            if (board[nx][ny] == 0) {
                int[] tail = snake.poll();
                board[tail[0]][tail[1]] = 0;
            }

            // 이동
            board[nx][ny] = 2;
            head = new int[]{nx, ny};
            snake.offer(head);

            time++;
        }
    }

    public static int[] move(int[] pos, int d) {
        int[] next = pos.clone();

        if(d == 0){
            next[1]++;
        } else if(d == 1){
            next[0]++;
        } else if(d == 2){
            next[1]--;
        } else if(d == 3){
            next[0]--;
        }

        return next;
    }
}
