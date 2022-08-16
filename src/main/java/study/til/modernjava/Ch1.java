package study.til.modernjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ch1 {

	private void test() {
		List<Apple> inventory = new ArrayList<>();

		Collections.sort(inventory, new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});

		inventory.sort(Comparator.comparing(Apple::getWeight));
	}

}
