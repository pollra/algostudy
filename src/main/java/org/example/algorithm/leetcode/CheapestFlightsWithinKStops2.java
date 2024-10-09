package org.example.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/cheapest-flights-within-k-stops/description
public class CheapestFlightsWithinKStops2 {

	public static void main(String[] args) {
		CheapestFlightsWithinKStops2 c = new CheapestFlightsWithinKStops2();
		System.out.println(c.findCheapestPrice(4, new int[][] {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1));
		System.out.println(c.findCheapestPrice(4, new int[][] {{0, 1, 1}, {0, 2, 5}, {1, 2, 1}, {2, 3, 1}}, 0, 3, 1));
		System.out.println(c.findCheapestPrice(7,
			new int[][] {{0, 3, 7}, {4, 5, 3}, {6, 4, 8}, {2, 0, 10}, {6, 5, 6}, {1, 2, 2}, {2, 5, 9}, {2, 6, 8}, {3, 6, 3}, {4, 0, 10}, {4, 6, 8}, {5, 2, 6},
				{1, 4, 3}, {4, 1, 6}, {0, 5, 10}, {3, 1, 5}, {4, 3, 1}, {5, 4, 10}, {0, 1, 6}}
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
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] flight : flights) {
			graph.putIfAbsent(flight[0], new HashMap<>());
			graph.get(flight[0]).put(flight[1], flight[2]);
		}

		Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));
		pq.add(Arrays.asList(src, 0, 0));

		Map<Integer, Integer> visited = new HashMap<>();

		while (!pq.isEmpty()) {
			List<Integer> cur = pq.poll();
			Integer u = cur.get(0);
			Integer price_u = cur.get(1);
			Integer k_visited = cur.get(2);

			if (u == dst) {
				return price_u;
			}

			visited.put(u, k_visited);

			if (k_visited <= k) {
				k_visited += 1;
				if (graph.containsKey(u)) {
					for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
						if (!visited.containsKey(v.getKey()) || k_visited < visited.get(v.getKey())) {
							int alt = price_u + v.getValue();
							pq.add(Arrays.asList(v.getKey(), alt, k_visited));
						}
					}
				}
			}
		}
		return -1;
	}
}
