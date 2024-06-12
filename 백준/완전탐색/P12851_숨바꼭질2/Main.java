package 백준.완전탐색.P12851_숨바꼭질2;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int MAX = 100000;
    static int[] ch = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(ch, Integer.MAX_VALUE);

        bw.write(BFS());
        bw.flush();
        bw.close();
    }

    public static String BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n, 0});
        ch[n] = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            if(poll[0] == k && poll[1] <= min){
                min = poll[1];
                count++;
                continue;
            }

            int tp = poll[0] * 2;
            if(tp <= MAX && ch[tp] >= poll[1] + 1){
                queue.offer(new int[]{tp, poll[1] + 1});
                ch[tp] = poll[1] + 1;
            }

            int fwd = poll[0] + 1;
            if(fwd <= MAX && ch[fwd] >= poll[1] + 1){
                queue.offer(new int[]{fwd, poll[1] + 1});
                ch[fwd] = poll[1] + 1;
            }

            int bwd = poll[0] - 1;
            if(bwd >= 0 && ch[bwd] >= poll[1] + 1){
                queue.offer(new int[]{bwd, poll[1] + 1});
                ch[bwd] = poll[1] + 1;
            }
        }

        return new StringBuilder().append(min).append("\n").append(count).toString();
    }
}
