package 백준.자료구조.P2493_탑;

import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<Top> tops = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            tops.add(new Top(i, Integer.valueOf(st.nextToken())));
        }

        bw.write(solution());
        bw.flush();
        bw.close();
    }

    public static String solution() {
        int[] answer = new int[n + 1];
        Stack<Top> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            Top top = tops.get(i);

            while(!stack.isEmpty() && stack.peek().h < top.h){
                stack.pop();
            }
            if(stack.isEmpty()){
                answer[top.index] = 0;
            } else if (stack.peek().h == top.h){
                answer[top.index] = stack.peek().index;
                stack.pop();
            } else {
                answer[top.index] = stack.peek().index;
            }
            stack.push(top);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            sb.append(answer[i]).append(" ");
        }

        return sb.toString();
    }

    public static class Top {

        int index;
        int h;

        public Top(int index, int h) {
            this.index = index;
            this.h = h;
        }
    }
}
