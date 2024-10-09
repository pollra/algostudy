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
		List<List<Node>> nodes = new ArrayList<>();
		visited = new boolean[n + 1];
		visited[0] = true;

		for (int i = 0; i <= n; i++) {
			nodes.add(new ArrayList<>());
		}
		for (int[] time : times) {
			Node node = new Node(time[0], time[1], time[2]);
			nodes.get(time[0]).add(node);
		}

		int[] result = new int[n + 1];
		Arrays.fill(result, Integer.MAX_VALUE);
		result[k] = 0;

		for (int i = 1; i < n + 1; i++) {
			int minIndex = getMinIndex(result);
			if (minIndex == -1) {
				continue;
			}
			visited[minIndex] = true;
			List<Node> nodes1 = nodes.get(minIndex);
			for (Node node : nodes1) {
				if (result[node.getEnd()] > result[node.getStart()] + node.getWeigh()) {
					result[node.getEnd()] = result[node.getStart()] + node.getWeigh();
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

	private int getMinIndex(int[] result) {
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

	static class Node {
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

		public Node(int start, int end, int weigh) {
			this.start = start;
			this.end = end;
			this.weigh = weigh;
		}
	}
}
