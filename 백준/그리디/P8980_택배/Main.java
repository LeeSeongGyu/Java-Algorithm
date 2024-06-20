package 백준.그리디.P8980_택배;

import java.io.*;
import java.util.*;

public class Main {

	static int n, c, m;
	static Package[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		arr = new Package[m];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[i] = new Package(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){

	}

	public static class Package implements Comparable<Package>{
		int start;
		int end;
		int amount;

		public Package(int start, int end, int amount){
			this.start = start;
			this.end = end;
			this.amount = amount;
		}

		@Override
		public int compareTo(Package p){

		}
	}
}
