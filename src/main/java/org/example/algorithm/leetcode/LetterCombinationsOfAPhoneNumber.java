package org.example.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
	private static final Map<Character, List<String>> numbers = new HashMap<>();
	private static List<String> result;

	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber l = new LetterCombinationsOfAPhoneNumber();
		List<String> strings = l.letterCombinations("");
		System.out.println(strings.size());
	}

	public List<String> letterCombinations(String digits) {
		result = new ArrayList<>();
		init();
		if (digits.isEmpty()) {
			return result;
		}
		List<List<String>> list = new ArrayList<>();
		char[] charArray = digits.toCharArray();
		for (char c : charArray) {
			list.add(numbers.get(c));
		}

		dfs(list, 0, new StringBuilder());
		return result;
	}

	// [a, b, c]
	// [d, e, f]
	// [g, h, i]
	// -> adi, adh, adg, aei, aeh, aeg, afi, afh, afg, ...
	private void dfs(List<List<String>> strings, int depth, StringBuilder sb) {
		if (depth == strings.size() && depth > 0) {
			result.add(sb.toString());
			return;
		}
		List<String> list = strings.get(depth);
		for (String s : list) {
			sb.append(s);
			dfs(strings, depth + 1, sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	private void init() {
		numbers.put('2', List.of("a", "b", "c"));
		numbers.put('3', List.of("d", "e", "f"));
		numbers.put('4', List.of("g", "h", "i"));
		numbers.put('5', List.of("j", "k", "l"));
		numbers.put('6', List.of("m", "n", "o"));
		numbers.put('7', List.of("p", "q", "r", "s"));
		numbers.put('8', List.of("t", "u", "v"));
		numbers.put('9', List.of("w", "x", "y", "z"));
	}
}
