package study.til.modernjava.ch7;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounterTest {

	public static final String SENTENCE =
		" Nel   mezzo del cammin  di nostra  vita "
			+ "mi  ritrovai in una  selva oscura"
			+ " che la  dritta via era   smarrita ";

	private static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
			WordCounter::accumulate,
			WordCounter::combine);

		return wordCounter.getCounter();
	}

	public static void main(String[] args) {
		Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);

		System.out.println("countWords(stream) = " + countWords(stream.parallel()));
	}
}
