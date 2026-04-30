/**
 * [BOJ] 20010 - 악덕 영주 혜유
 * - 제출 날짜: 2026년 3월 16일
 * - 결과: 맞았습니다!!
 * - 메모리: 31732 KB
 * - 시간: 244 ms
 */

import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<int[]>[] edge;
	static ArrayList<int[]>[] graph;
	static int[] dist;
	static int[] parents;
	static boolean[] visited;
	static int c, max_cost = -1, v;
	
	public static void main(String[] args) throws IOException {
		c = System.in.read();
		int n = readInt();
		int k = readInt();
		
		inputEdge(n, k);
		int min_cost = calculateMinCost(n, k);
		
		max_cost = -1;
		dfs(0, 0, false);
		
		max_cost = -1;
		dfs(v, 0, true);
		System.out.println(min_cost + "\n" + max_cost);
	}
	
	private static void inputEdge(int n, int k) throws IOException {
		edge = new ArrayList[n];
		graph = new ArrayList[n];
		dist = new int[n];
		visited = new boolean[n];
		parents = new int[n];
		
		for(int i = 0; i < n; i++) {
			edge[i] = new ArrayList<>();
			graph[i] = new ArrayList<>();
			dist[i] = 1_000_005;
			parents[i] = -1;
		}
		
		while(k-- > 0) {
			int v1 = readInt();
			int v2 = readInt();
			int w = readInt();
			edge[v1].add(new int[] {v2, w});
			edge[v2].add(new int[] {v1, w});
		}
	}
	
	private static void dfs(int idx, int cost, boolean flag) {
		if(cost > max_cost) {
			max_cost = cost;
			v = idx;
		}
		visited[idx] = flag;
		for(int[] next : graph[idx]) {
			int node = next[0];
			int w = next[1];
			if(visited[node] != flag) {
				dfs(node, cost + w, flag);
			}
		}
	}
	
	private static int calculateMinCost(int n, int k) {
		int cnt = 0, u = 0, d = 0;
		visited[u] = true;
		dist[0] = 0;
		while(cnt < n - 1) {
			for(int[] next : edge[u]) {
				int cur = next[0];
				int w = next[1];
				if(!visited[cur] && dist[cur] > w) {
					dist[cur] = w;
					parents[cur] = u;
				}
			}
			
			int cost = 1_000_005;
			for(int i = 0; i < n; i++) {
				if(!visited[i] && dist[i] < cost) {
					cost = dist[i];
					u = i;
				}
			}
			
			graph[u].add(new int[]{parents[u], cost});
			graph[parents[u]].add(new int[]{u, cost});
			visited[u] = true;
			d += cost;
			cnt++;
		}
		
		return d;
	}	
	
	private static int readInt() throws IOException {
		while(c <= ' ') c = System.in.read();
		int n = 0;
		while(c >= '0' && c <= '9') {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}
}
