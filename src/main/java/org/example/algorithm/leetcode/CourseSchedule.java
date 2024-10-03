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
		// 그래프를 인접 리스트로 표현
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}

		// prerequisites를 바탕으로 그래프 생성
		for (int[] pre : prerequisites) {
			int course = pre[0];
			int prerequisite = pre[1];
			graph.get(prerequisite).add(course);
		}

		// 각 노드의 방문 상태를 저장하는 배열
		int[] visited = new int[numCourses];

		// 모든 코스에 대해 DFS 탐색 시도
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {  // 아직 방문하지 않은 코스일 때만 탐색
				if (!dfs(i, graph, visited)) {
					return false;  // 사이클이 발견되면 false 반환
				}
			}
		}

		return true;  // 모든 코스를 탐색했는데 사이클이 없다면 true 반환
	}

	// DFS 메서드
	private boolean dfs(int course, List<List<Integer>> graph, int[] visited) {
		if (visited[course] == 1) {
			return false;  // 현재 노드가 이미 탐색 중이면 사이클이 존재하는 것
		}
		if (visited[course] == 2) {
			return true;  // 이미 탐색이 끝난 노드이므로 다시 탐색할 필요 없음
		}

		visited[course] = 1;  // 현재 노드를 탐색 중으로 표시

		// 현재 코스에서 이동할 수 있는 다른 코스들에 대해 DFS 실행
		for (int nextCourse : graph.get(course)) {
			if (!dfs(nextCourse, graph, visited)) {
				return false;
			}
		}

		visited[course] = 2;  // 탐색 완료 표시
		return true;
	}

}
