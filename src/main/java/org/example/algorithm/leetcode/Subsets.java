package org.example.algorithm.leetcode;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// https://leetcode.com/problems/subsets
public class Subsets {

	public static void main(String[] args) {
		Subsets s = new Subsets();
		List<List<Integer>> subsets = s.subsets(new int[] {1, 2, 3});
		System.out.println(subsets);
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<Integer> sortNums = Arrays.stream(nums).sorted().boxed().collect(toList());
		List<List<Integer>> result = new ArrayList<>();

		dfs(result, sortNums, 0, new Stack<Integer>());

		return result;
	}

	public static void dfs(List<List<Integer>> result, List<Integer> sortNums, int index, Stack<Integer> integers) {
		result.add(new ArrayList<>(integers));
		for (int i = index; i < sortNums.size(); i++) {
			integers.push(sortNums.get(i));
			dfs(result, sortNums, i + 1, integers);
			integers.pop();
		}
	}
}
