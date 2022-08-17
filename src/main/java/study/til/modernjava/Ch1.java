package study.til.modernjava;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Ch1 {

	private void sort() {
		List<Apple> inventory = new ArrayList<>();

		Collections.sort(inventory, new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});

		inventory.sort(Comparator.comparing(Apple::getWeight));
	}

	/**
	 * 메서드와 람다를 일급 시민으로
	 */
	private void ch1_3_1() {
		File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isHidden();
			}
		});

		File[] hiddenFiles = new File(".").listFiles(File::isHidden);
	}

	/**
	 * 코드 넘겨주기 : 예제
	 */
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (GREEN.eqauls(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterHeavyApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {
				result.add(apple);
			}
		}
		return result;
	}

	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();

		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	private void ch1_3_2(List<Apple> inventory) {
		filterApples(inventory, Apple::isGreenApple);
		filterApples(inventory, Apple::isHeavyApple);
	}

	/*
	프레디케이트(predicate)란?
	수학에서는 인수로 값을 받아 true 나 false를 반환하는 함수를 프레디 케이트라고 한다.
	자바 8에서는 Function<Apple, Boolean> 같이 코드를 구현할 수 있지만
	Predicate<Apple>을 사용하는 것이 더 표준적인 방식이다.(boolean을 Boolean으로 변환하는 과정이 없으므로 더 효율적이다)
	* */
}
