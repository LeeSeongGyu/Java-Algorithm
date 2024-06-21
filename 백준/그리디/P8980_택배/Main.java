package 백준.그리디.P8980_택배;

import java.io.*;
import java.util.*;

public class Main {

	static int n, c, m;
	static Package[] packages;
	static int[] weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		packages = new Package[m];
		weight = new int[n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			packages[i] = new Package(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}

		bw.write("" + solution());
		bw.flush();
		bw.close();
	}

	public static int solution(){
		int count = 0;
		Arrays.sort(packages);

		for(Package p: packages){
			int max = 0;
			for(int i = p.start; i < p.end; i++){
				max = Math.max(max, weight[i]);
			}

			int amount = Math.min(p.amount, c - max);
			for(int i = p.start; i < p.end; i++){
				weight[i] += amount;
			}
			count += amount;
		}

		return count;
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
			return this.end - p.end;
		}
	}
}
