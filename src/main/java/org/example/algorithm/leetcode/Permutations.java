package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/permutations/description/
public class Permutations {
	private static List<List<Integer>> result;
	private static boolean[] visit;

	public static void main(String[] args) {
		Permutations permutations = new Permutations();
		List<List<Integer>> permute = permutations.permute(new int[] {1, 2, 3});
		System.out.println(permute);
	}

	// nums.length!
	public List<List<Integer>> permute(int[] nums) {
		result = new ArrayList<>();
		visit = new boolean[nums.length];
		dfs(0, nums, new ArrayList<>());

		return result;
	}

	// 1,2,3 같은 배열  visit 으로 방문 체크하고 뎁스 같으면 해당 결과 저장
	private void dfs(int depth, int[] nums, List<Integer> list) {
		if (depth == nums.length) {
			result.add(new ArrayList<>(list));
		}

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (!visit[i]) {
				visit[i] = true;
				list.add(num);
				dfs(depth + 1, nums, list);
				list.removeLast();
				visit[i] = false;
			}
		}
	}
}
