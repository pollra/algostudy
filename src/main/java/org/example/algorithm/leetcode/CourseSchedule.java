package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/course-schedule/description/
public class CourseSchedule {
	public static void main(String[] args) {
		CourseSchedule c = new CourseSchedule();
		System.out.println(c.canFinish(2, new int[][] {{1, 0}}));
		System.out.println(c.canFinish(2, new int[][] {{1, 0}, {0, 1}}));
	}

	// 1,0 => 1을 완료하기 위해 0을 완료해야함.
	// dfs 로 순환되는지 여부를 탐색
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> finishToTakeMap = new HashMap<>();
		for (int[] pre : prerequisites) {
			finishToTakeMap.putIfAbsent(pre[0], new ArrayList<>());
			// 완료하기 위해 처리해야 하는 노드 저장
			finishToTakeMap.get(pre[0]).add(pre[1]);
		}

		List<Integer> takes = new ArrayList<>();
		for (Integer finish : finishToTakeMap.keySet()) {
			if (!dfs(finishToTakeMap, finish, takes)) {
				return false;
			}
		}
		return true;
	}

	public boolean dfs(Map<Integer, List<Integer>> finishToTakeMap, Integer finish, List<Integer> takes) {
		if (takes.contains(finish)) {
			return false;
		}

		if (finishToTakeMap.containsKey(finish)) {
			takes.add(finish);
			for (Integer take : finishToTakeMap.get(finish)) {
				if (!dfs(finishToTakeMap, take, takes)) {
					return false;
				}
			}
			takes.remove(finish);
		}
		return true;
	}

}
