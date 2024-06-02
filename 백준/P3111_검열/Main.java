package 백준.P3111_검열;

import java.io.*;
import java.util.*;

public class Main {

    static String word;
    static String reverseWord;
    static String text;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        word = br.readLine();
        StringBuffer sb = new StringBuffer(word);
        reverseWord = sb.reverse().toString();
        text = br.readLine();

        bw.write(solution());
        bw.flush();
        bw.close();
    }

    public static String solution(){
        Stack<Character> stack = new Stack<>();
        Stack<Character> reverseStack = new Stack<>();
        Deque<Character> deque = new ArrayDeque<>();

        for(int i = 0; i < text.length(); i++){
            deque.offer(text.charAt(i));
        }

        while(true){
            // 앞부터 찾기
            boolean flag = false;
            while(!deque.isEmpty()){
                stack.push(deque.pollFirst());
                if(findWord(stack, word)){
                    for (int i = 0; i < word.length(); i++) {
                        stack.pop();
                    }
                    flag = true;
                    break;
                }
            }
            // 못 찾으면 종료
            if(!flag) break;
            // 뒤부터 찾기
            flag = false;
            while(!deque.isEmpty()){
                reverseStack.push(deque.pollLast());
                if(findWord(reverseStack, reverseWord)){
                    for (int i = 0; i < word.length(); i++) {
                        reverseStack.pop();
                    }
                    flag = true;
                    break;
                }
            }
            //몾찾으면 종료
            if(!flag) break;
        }

        // 스텍에 남은 문자 확인
        while(!reverseStack.isEmpty()){
            stack.push(reverseStack.pop());
            if(findWord(stack, word)){
                for (int i = 0; i < word.length(); i++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static boolean findWord(Stack<Character> stack, String word){
        if(stack.size() < word.length()){
            return false;
        }

        char[] chars = word.toCharArray();
        for(int i = 0; i < word.length(); i++){
            if(chars[i] != stack.get(stack.size() - word.length() + i)){
                return false;
            }
        }

        return true;
    }
}
