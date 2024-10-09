package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/description
public class CheapestFlightsWithinKStops {

	public static void main(String[] args) {
		CheapestFlightsWithinKStops c = new CheapestFlightsWithinKStops();
		System.out.println(c.findCheapestPrice(4, new int[][] {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1));
		System.out.println(c.findCheapestPrice(4, new int[][] {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1));
		System.out.println(c.findCheapestPrice(7, new int[][] {{0,3,7},{4,5,3},{6,4,8},{2,0,10},{6,5,6},{1,2,2},{2,5,9},{2,6,8},{3,6,3},{4,0,10},{4,6,8},{5,2,6},{1,4,3},{4,1,6},{0,5,10},{3,1,5},{4,3,1},{5,4,10},{0,1,6}}
			, 2, 4, 1));

		// 0 -1-> 1
		// 0 -5-> 2
		// 1 -1-> 2
		// 2 -1-> 3

		// 0 -> 3 으로 갈떄 경유 1
		// 무조건 0 -> 2 -> 3  이 경로니까 6 어덯게 3?

		// start 0 1 2 인거 가능
		// [0, 1, 2, 6]
		// 5 > 1 + 1 => 2 2가 먼저 업뎃되어서
		// 무한 > 5 + 1 => 6 가아니라 3이 되었구나
		// 0 2 5 6
		// [0, 3, 7], [6, 4, 8], [2, 0, 10], [6, 5, 6], [2, 5, 9], [2, 6, 8], [5, 2, 6], [0, 5, 10], [5, 4, 10], [0, 1, 6]
	}

	// 벨만포드 알고리즘
	// k는 경유자(노드이므로) k 가 1 -> 엣지를 2개 사용, k가 2 -> 엣지를 3개 사용...
	// 경로가 존재하지 않는 경우 : -1 => 도달할 수 없는 경우(무한인 경우)
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		int[] result = new int[n];
		Arrays.fill(result, Integer.MAX_VALUE);
		result[src] = 0;

		for (int i = 0; i < k + 1; i++) {
			List<Graph> selectedEdge = new ArrayList<>();
			for (int[] flight : flights) {
				int start = flight[0];
				int end = flight[1];
				int weigh = flight[2];
				if (result[start] != Integer.MAX_VALUE) {
					selectedEdge.add(new Graph(start, end, weigh));
				}
			}
			List<UpdateNode> updateNodes = new ArrayList<>();
			for (Graph graph : selectedEdge) {
				if (result[graph.end] > result[graph.start] + graph.weight) {
					updateNodes.add(new UpdateNode(graph.end, result[graph.start] + graph.weight));
				}
			}

			for (UpdateNode updateNode : updateNodes) {
				// 더 큰 값으로 업데이트 되는거 방지
				if (result[updateNode.index] > updateNode.weight) {
					result[updateNode.index] = updateNode.weight;
				}
			}
		}

		if (result[dst] == Integer.MAX_VALUE) {
			return -1;
		}
		return result[dst];
	}

	static class UpdateNode {
		private int index;
		private int weight;

		public UpdateNode(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}

	static class Graph {
		private int start;
		private int end;
		private int weight;

		@Override
		public String toString() {
			return new StringJoiner(", ", "[", "]")
				.add("" + start)
				.add("" + end)
				.add("" + weight)
				.toString();
		}

		public Graph(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}
