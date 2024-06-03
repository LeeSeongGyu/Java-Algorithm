package 백준.자료구조.P9012_괄호;

import java.util.*;
import java.io.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i =0; i < n; i++){
            if(isVPS(br.readLine())){
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean isVPS(String str){
        Stack<Character> stack = new Stack<>();

        for(char c : str.toCharArray()){
            if(c == '('){
                stack.push(c);
            }
            else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }
}
