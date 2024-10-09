package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/network-delay-time/description/
// 시작 노드에서 모든 노드로의 최단 거리를 구한 후 최단 거리 배열이 무한인게 있으면 -1 없으면 최단 거리중 가장 큰값 반환
public class NetworkDelayTime {

	private static boolean[] visited;

	public static void main(String[] args) {
		NetworkDelayTime n = new NetworkDelayTime();
		System.out.println(n.networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
		System.out.println(n.networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1));
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		// 인접 리스트로 구현
		List<List<Graph>> graphList = new ArrayList<>();
		// 최단거리 배열 초기화
		int[] result = init(times, n, k, graphList);

		for (int i = 1; i <= n; i++) {
			// 최단거리 배열중 가장 작은 수 찾기
			int minIndex = getMinValueIndex(result);
			if (minIndex == -1) {
				continue;
			}
			visited[minIndex] = true;
			List<Graph> graphs = graphList.get(minIndex);
			for (Graph graph : graphs) {
				if (result[graph.getEnd()] > result[graph.getStart()] + graph.getWeigh()) {
					result[graph.getEnd()] = result[graph.getStart()] + graph.getWeigh();
				}
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++) {
			int i1 = result[i];
			if (i1 == Integer.MAX_VALUE) {
				return -1;
			}
			max = Math.max(max, i1);
		}
		return max;
	}

	private int[] init(int[][] times, int n, int k, List<List<Graph>> graphList) {
		visited = new boolean[n + 1];
		visited[0] = true;

		for (int i = 0; i <= n; i++) {
			graphList.add(new ArrayList<>());
		}
		for (int[] time : times) {
			Graph graph = new Graph(time[0], time[1], time[2]);
			graphList.get(time[0]).add(graph);
		}

		int[] result = new int[n + 1];
		Arrays.fill(result, Integer.MAX_VALUE);
		result[k] = 0;
		return result;
	}

	private int getMinValueIndex(int[] result) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < result.length; i++) {
			if (!visited[i]) {
				int i1 = result[i];
				if (i1 < min) {
					min = i1;
					index = i;
				}
			}
		}
		return index;
	}

	static class Graph {
		private int start;
		private int end;
		private int weigh;

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public int getWeigh() {
			return weigh;
		}

		public Graph(int start, int end, int weigh) {
			this.start = start;
			this.end = end;
			this.weigh = weigh;
		}
	}
}
