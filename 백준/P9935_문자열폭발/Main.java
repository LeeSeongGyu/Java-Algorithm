package 백준.P9935_문자열폭발;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str = br.readLine();
		String bomb = br.readLine();

		bw.write(solution(str, bomb));
		bw.flush();
		bw.close();
	}

	public static String solution(String str, String bomb) {
		Stack<Character> stack = new Stack<>();
		char[] chars = str.toCharArray();

		for (char c : chars) {
			stack.push(c);
			while (findBomb(stack, bomb)) {
				for (int i = 0; i < bomb.length(); i++) {
					stack.pop();
				}
			}
		}

		if (stack.isEmpty()) {
			return "FRULA";
		}

		StringBuilder sb = new StringBuilder();
		for (char c : stack) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static boolean findBomb(Stack<Character> stack, String bomb) {
		if (stack.size() < bomb.length()) {
			return false;
		}
		char[] chars = bomb.toCharArray();
		for (int i = 0; i < bomb.length(); i++) {
			if (chars[i] != stack.get(stack.size() - bomb.length() + i)) {
				return false;
			}
		}
		return true;
	}
}
