package BOJ.Simulation.P16235_나무재테크;

import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k;
	static int[][] nutrient, plus;
	static PriorityQueue<Integer>[][] trees;
	static int[] xMoves = {-1, 0, 1, 0, -1, 1, -1, 1};
	static int[] yMoves = {0, -1, 0, 1, -1, -1, 1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		plus = new int[n][n];
		nutrient = new int[n][n];
		trees = new PriorityQueue[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				nutrient[i][j] = 5;
				trees[i][j] = new PriorityQueue<>();
			}
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				plus[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees[x][y].offer(age);
		}

		for(int i = 0; i < k; i++){
			cycle();
		}

		bw.write("" + count());
		bw.flush();
		bw.close();
	}

	public static void cycle(){
		// 봄
		int[][] dead = new int[n][n];

		for(int i = 0; i< n; i++){
			for(int j = 0; j < n; j++){
				PriorityQueue<Integer> grows = new PriorityQueue<>();
				PriorityQueue<Integer> queue = trees[i][j];

				while (!queue.isEmpty()){
					int age = queue.poll();
					if(age <= nutrient[i][j]){
						nutrient[i][j] -= age;
						grows.offer(age + 1);
					} else {
						dead[i][j] += age/2;
					}
				}

				trees[i][j] = grows;
			}
		}

		// 여름
		for(int i = 0; i< n; i++) {
			for (int j = 0; j < n; j++) {
				nutrient[i][j] += dead[i][j];
			}
		}

		// 가을
		List<int[]> newTrees = new ArrayList<>();

		for(int i = 0; i< n; i++) {
			for (int j = 0; j < n; j++) {
				for(Integer age : trees[i][j]){
					if(age % 5 == 0){
						for(int m = 0; m < 8; m++){
							int nx = i + xMoves[m];
							int ny = j + yMoves[m];
							if(nx >= 0 && ny >= 0 && nx < n && ny < n){
								newTrees.add(new int[]{nx, ny});
							}
						}
					}
				}
			}
		}

		for(int[] newTree : newTrees){
			trees[newTree[0]][newTree[1]].offer(1);
		}

		// 겨울
		for(int i = 0; i< n; i++) {
			for (int j = 0; j < n; j++) {
				nutrient[i][j] += plus[i][j];
			}
		}
	}

	public static int count(){
		int count = 0;

		for(int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				count += trees[i][j].size();
			}
		}

		return count;
	}
}
