package study.til.modernjava;

import lombok.Data;

@Data
public class Apple {

	private Integer weight;
	private String color;

	public static boolean isGreenApple(Apple apple) {
		return GREEN.equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

}
