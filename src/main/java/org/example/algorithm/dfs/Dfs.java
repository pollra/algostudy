package org.example.algorithm.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dfs {
	private final static Map<Integer, List<Integer>> graph = new HashMap<>();

	public static void main(String[] args) {
		graph.put(1, Arrays.asList(2, 3, 4));
		graph.put(2, Arrays.asList(5));
		graph.put(3, Arrays.asList(5));
		graph.put(4, Arrays.asList());
		graph.put(5, Arrays.asList(6, 7));
		graph.put(6, Arrays.asList());
		graph.put(7, Arrays.asList());

		System.out.println(recursiveDFS(1, new ArrayList<>()));
		System.out.println(iterativeDFS(1));
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

	public static List<Integer> iterativeDFS(int v) {
		List<Integer> discovered = new ArrayList<>();
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			v = stack.pop();
			if(!discovered.contains(v)) {
				discovered.add(v);
				for (Integer i : graph.get(v)) {
					stack.push(i);
				}
			}
		}

		return discovered;
	}
}
