package 백준.P2812_크게만들기;

import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static String number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        number = br.readLine();

        bw.write(solution());
        bw.flush();
        bw.close();
    }

    public static String solution() {
        char[] chars = number.toCharArray();
        Stack<Integer> stack = new Stack<>();
        int popCount = 0;

        for (char c : chars) {
            int digit = (int)c - '0';
            while (!stack.isEmpty() && stack.peek() < digit && popCount < k) {
                stack.pop();
                popCount++;
            }
            stack.push(digit);
        }

        StringBuilder sb = new StringBuilder();
        for(int digit : stack){
            sb.append(digit);
        }
        return sb.substring(0, n-k);
    }
}
