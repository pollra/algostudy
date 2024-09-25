package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combinations/description
public class Combinations {

	public static List<List<Integer>> result;
	public boolean[] visited;

	public static void main(String[] args) {
		Combinations c = new Combinations();
		System.out.println(c.combine(4, 2));
	}

	// 1,2,3,4 중에 2개 뽑기 -> 순서 상관x 2,4와 4,2 는 같음
	// 1,2 1,3, 1,4, 2,3 2,4 3,4
	public List<List<Integer>> combine(int n, int k) {
		result = new ArrayList<>();
		visited = new boolean[n + 1];
		visited[0] = true;

		int[] intArray = new int[n + 1];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = i;
		}
		recursive(intArray, k, 1, new ArrayList<>());

		return result;
	}

	private void recursive(int[] intArray, int k, int index, List<Integer> list) {
		if (list.size() == k) {
			result.add(new ArrayList<>(list));
			return;
		}

		for (int i = index; i < intArray.length; i++) {
			int i1 = intArray[i];
			if (!visited[i1]) {
				list.add(i1);
				visited[i1] = true;

				recursive(intArray, k, i + 1, list);
				visited[i1] = false;
				list.removeLast();
			}
		}
	}
}
