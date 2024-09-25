package org.example.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bfs {
	private final static Map<Integer, List<Integer>> graph = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= n; i++) {
			graph.put(i, new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			List<Integer> list = graph.get(key);
			list.add(value);
			graph.put(key, list);
			List<Integer> list2 = graph.get(value);
			list2.add(key);
			graph.put(value, list2);
			list.sort(Comparator.comparingInt(o -> o));
			list2.sort(Comparator.comparingInt(o -> o));
		}
		List<Integer> x = recursiveDFS(v, new ArrayList<>());
		for (Integer i : x) {
			System.out.print(i + " ");
		}
		// System.out.println(x);
		System.out.println();
		List<Integer> x1 = iterativeBFS(v);
		for (Integer i : x1) {
			System.out.print(i + " ");
		}
		// System.out.println(x1);

	}

	public static List<Integer> iterativeBFS(int startV) {
		List<Integer> result = new ArrayList<>();
		result.add(startV);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startV);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (Integer i : graph.get(v)) {
				if (!result.contains(i)) {
					result.add(i);
					queue.add(i);
				}
			}
		}
		return result;
	}

	public static List<Integer> recursiveDFS(int v, List<Integer> discovered) {
		discovered.add(v);
		for (Integer w : graph.get(v)) {
			if (!discovered.contains(w)) {
				discovered = recursiveDFS(w, discovered);
			}
		}
		return discovered;
	}
}
