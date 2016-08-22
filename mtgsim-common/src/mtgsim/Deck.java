package mtgsim;

import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Deck extends Cards {

	private String name;

	public Cards draw(int count) {
		Cards result = new Cards();
		for (int i = 0; i < count; i++) {
			result.addOne(cards.pop());
		}
		return result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printListTo(PrintStream out) {
		Map<String, List<Card>> buckets = cards.stream().collect(Collectors.groupingBy(c -> c.getName()));
		List<String> sortedKeys = new LinkedList<>(buckets.keySet());
		Collections.sort(sortedKeys);
		for (String name : sortedKeys) {
			out.println(String.format("%d %s", buckets.get(name).size(), name));
		}

	}

}
