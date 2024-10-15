package org.example.algorithm.prgms;

import java.util.LinkedList;
import java.util.Queue;
//a
// https://school.programmers.co.kr/learn/courses/30/lessons/1844
public class 게임맵최단거리_1844 {

	private static int[] dx = {0, 0, 1, -1};
	private static int[] dy = {1, -1, 0, 0};
	private boolean[][] visited;

	public static void main(String[] args) {
		게임맵최단거리_1844 s = new 게임맵최단거리_1844();
		int solution = s.solution(new int[][] {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}});
		System.out.println(solution);
	}

	// [1,0,1,1,1]
	// [1,0,1,0,1]
	// [1,0,1,1,1]
	// [1,1,1,0,1]
	// [0,0,0,0,1]
	// 그냥 bfs 해서 1 위치를 동서남북 이동하면서 +1 하는데 만약 이동할때 더 크거나 1이 아닌경우는 안되게끔 하면 되는거 아님?
	public int solution(int[][] maps) {
		visited = new boolean[maps.length][maps[0].length];
		visited[0][0] = true;
		bfs(new Node(0, 0), maps);
		if (!visited[maps.length - 1][maps[0].length - 1]) {
			return -1;
		}
		return maps[maps.length - 1][maps[0].length - 1];
	}

	public void bfs(Node start, int[][] maps) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(start);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = current.x + dx[i];
				int nextY = current.y + dy[i];
				if (nextX >= 0 && nextY >= 0 && nextX < maps.length && nextY < maps[0].length) {
					if (maps[nextX][nextY] == 1) {
						maps[nextX][nextY] = maps[current.x][current.y] + 1;
						queue.add(new Node(nextX, nextY));
						visited[nextX][nextY] = true;
					}
				}
			}
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}

/**
 *
 * import java.util.LinkedList;
 * import java.util.Queue;
 *
 * class Solution {
 *
 *     private static int[] dx = {0, 0, 1, -1};
 *     private static int[] dy = {1, -1, 0, 0};
 *
 *     private static int[][] table;
 *     private static int n, m;
 *
 *     public int solution(int[][] maps) {
 *         table = maps;
 *         n = maps.length;
 *         m = maps[0].length;
 *         bfs();
 *         if (table[n - 1][m - 1] > 1) {
 *             return table[n - 1][m - 1];
 *         }
 *         return -1;
 *     }
 *
 *     class Point {
 *         int x;
 *         int y;
 *
 *         public Point(int x, int y) {
 *             this.x = x;
 *             this.y = y;
 *         }
 *     }
 *
 *     private void bfs() {
 *         Queue<Point> queue = new LinkedList<>();
 *         queue.add(new Point(0, 0));
 *         while (!queue.isEmpty()) {
 *             Point p = queue.remove();
 *             for (int i = 0; i < 4; i++) {
 *                 int nx = p.x + dx[i];
 *                 int ny = p.y + dy[i];
 *                 if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
 *                     if (table[nx][ny] == 1) {
 *                         queue.add(new Point(nx, ny));
 *                         table[nx][ny] = table[p.x][p.y] + 1;
 *                     }
 *                 }
 *             }
 *         }
 *     }
 * }
 */
