package 백준.완전탐색.P13913_숨바꼭질4;

import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static final int MAX = 100_000;
    static int[] ch = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(ch, -1);

        BFS();
        bw.write(trace());
        bw.flush();
        bw.close();
    }

    public static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        ch[n] = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (poll == k) {
                return;
            }

            for (int j = 0; j < 3; j++) {
                int next = getNext(poll, j);

                if (next >= 0 && next <= MAX && ch[next] == -1) {
                    ch[next] = poll;
                    queue.offer(next);
                }
            }

        }

    }

    public static int getNext(int base, int operation) {
        if (operation == 0) {
            return base - 1;
        } else if (operation == 1) {
            return base + 1;
        } else {
            return base * 2;
        }
    }

    public static String trace(){
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int next = k;

        while(true){
            stack.push(next);
            next = ch[next];
            if(next == Integer.MIN_VALUE){
                break;
            }
        }

        sb.append(stack.size() - 1).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }
}
