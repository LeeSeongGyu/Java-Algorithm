package 백준.P10799_쇠막대기;

import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("" + solution(br.readLine()));

        bw.flush();
        bw.close();
    }

    public static int solution(String str) {
        int answer = 0;
        int sticks = 0;
        Stack<Character> stack = new Stack<>();

        for(char c : str.toCharArray()){
            if(c == '('){
                sticks++;
            } else {
                sticks--;
                if(stack.peek() == '('){
                    answer += sticks;
                } else {
                    answer++;
                }
            }
            stack.push(c);
        }

        return answer;
    }
}
