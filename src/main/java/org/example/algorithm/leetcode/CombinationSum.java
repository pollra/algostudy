package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

// https://leetcode.com/problems/combination-sum/description/
public class CombinationSum {
	private static ArrayList<List<Integer>> result;

	public static void main(String[] args) {
		CombinationSum combinationSum = new CombinationSum();
		List<List<Integer>> lists = combinationSum.combinationSum(new int[] {2, 3, 6, 7}, 7);
		List<List<Integer>> lists2 = combinationSum.combinationSum(new int[] {2, 3, 5}, 8);
		List<List<Integer>> lists3 = combinationSum.combinationSum(new int[] {3,5,7}, 10);
		List<List<Integer>> lists4 = combinationSum.combinationSum(new int[] {1,2,3,4}, 4);
		System.out.println(lists);
		System.out.println();
		System.out.println(lists2);
		System.out.println();
		System.out.println(lists3);
		System.out.println(lists4);
	}


	// 2,3,6,7 / 7 => [2,2,3], [7]
	// 조합에서 뽑는 경우의 수가 1~n 까지 모든 경우에 대해서 조합 실행
	// start를 정해서 이미 넘어간 수는 탐색하지 않도록
	// + 정렬해서 합이 넘어가는 경우 가지치기 및
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		result = new ArrayList<>();
		List<Integer> sortCandidates = Arrays.stream(candidates).boxed().sorted().collect(Collectors.toList());
		Integer i1 = sortCandidates.get(0);
		int r = target / i1;
		for (int i = 1; i <= r; i++) {
			Stack<Integer> stack = new Stack<>();

			combinations(0,0, i, sortCandidates, stack, target);
		}
		return result;
	}

	public void combinations(int start, int depth, int r, List<Integer> candidates, Stack<Integer> stack, int target) {
		int sum = stack.stream().mapToInt(value -> value).sum();
		if (depth == r) {
			if (target == sum) {
				start++;
				result.add(new ArrayList<>(stack));
			}
		}
		if (sum > target) {
			return;
		}

		for (int i = start; i < candidates.size(); i++) {
			Integer i1 = candidates.get(i);
			stack.add(i1);
			combinations(i,depth + 1, r, candidates, stack, target);
			stack.pop();
		}
	}
}
