package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.com/problems/reconstruct-itinerary/description/
public class ReconstructItinerary {

	public static void main(String[] args) {
		ReconstructItinerary r = new ReconstructItinerary();
		List<List<String>> s = new ArrayList<>();
		List<String> e = new ArrayList<>();
		fromTo(e, "JFK", "SFO");
		s.add(e);
		List<String> e1 = new ArrayList<>();
		fromTo(e1, "JFK", "ATL");
		s.add(e1);

		List<String> e2 = new ArrayList<>();
		fromTo(e2, "SFO", "ATL");
		s.add(e2);

		List<String> e3 = new ArrayList<>();
		fromTo(e3, "ATL", "JFK");
		s.add(e3);

		List<String> e4 = new ArrayList<>();
		fromTo(e4, "ATL", "SFO");
		s.add(e4);

		System.out.println(r.findItinerary(s));
	}

	// {
	// 	ICN = [ATL],
	// 	ATL = [ICN, JFK],
	// 	JFK = [ATL, ICN],
	// }

	public List<String> findItinerary(List<List<String>> tickets) {
		List<String> results = new LinkedList<>();
		Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();
		// 우선순위 큐에 넣어서 어휘순으로 방문하도록 구성
		for (List<String> ticket : tickets) {
			fromToMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
			fromToMap.get(ticket.get(0)).add(ticket.get(1));
		}
		dfs(results, fromToMap, "JFK");

		return results;
	}

	public void dfs(List<String> results, Map<String, PriorityQueue<String>> fromToMap, String from) {
		// from 을 키로 가지고 있고, 도착 정보가 비어 있지 않으면 dfs 탐색 계속
		while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
			dfs(results, fromToMap, fromToMap.get(from).poll());
		}
		// dfs 탐색 끝날때마다 첫번째 요소에 출발을 넣음
		results.addFirst(from);
	}

	public static void fromTo(List<String> ticket, String from, String to) {
		ticket.add(from);
		ticket.add(to);
	}
}
