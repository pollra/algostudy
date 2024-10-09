package org.example.algorithm.leetcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/network-delay-time/description/
// 시작 노드에서 모든 노드로의 최단 거리를 구한 후 최단 거리 배열이 무한인게 있으면 -1 없으면 최단 거리중 가장 큰값 반환
public class NetworkDelayTime2 {

	private static boolean[] visited;

	public static void main(String[] args) {
		NetworkDelayTime2 n = new NetworkDelayTime2();
		System.out.println(n.networkDelayTime(new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
		System.out.println(n.networkDelayTime(new int[][] {{1, 2, 1}}, 2, 1));
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] time : times) {
			graph.putIfAbsent(time[0], new HashMap<>());
			graph.get(time[0]).put(time[1], time[2]);
		}

		// 정렬 기준은 도착지와 소요 시간 총 소요시간을 기준으로 한다.
		Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
		pq.add(new AbstractMap.SimpleEntry<>(k, 0));

		Map<Integer, Integer> dist = new HashMap<>();

		while (!pq.isEmpty()) {
			Map.Entry<Integer, Integer> cur = pq.poll();
			Integer u = cur.getKey();
			Integer dist_u = cur.getValue();

			if (!dist.containsKey(u)) {
				dist.put(u, dist_u);
				if (graph.containsKey(u)) {
					for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
						int alt = dist_u + v.getValue();
						pq.add(new AbstractMap.SimpleEntry<>(v.getKey(), alt));
					}
				}
			}

		}

		if (dist.size() == n) {
			return Collections.max(dist.values());
		}
		return -1;
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
