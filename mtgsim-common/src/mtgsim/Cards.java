package mtgsim;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Predicate;

public class Cards {
	protected LinkedList<Card> cards = new LinkedList<>();

	public Cards addOne(Card e) {
		cards.add(e);
		return this;
	}

	public Cards addMany(Card e, int count) {
		for (int i = 0; i < count; i++) {
			cards.add(e);
		}
		return this;
	}

	public void shuffle(Random random) {
		Collections.shuffle(cards, random);
	}

	public int size() {
		return cards.size();
	}

	public boolean containsMin(int min, Predicate<? super Card> predicate) {
		return count(predicate) >= min;
	}

	public boolean containsMax(int max, Predicate<? super Card> predicate) {
		return count(predicate) <= max;
	}

	public boolean containsExactly(int exact, Predicate<? super Card> predicate) {
		return count(predicate) == exact;
	}

	public boolean containsBetween(int min, int max, Predicate<? super Card> predicate) {
		int count = count(predicate);
		return count >= min && count <= max;
	}

	public int count(Predicate<? super Card> predicate) {
		return (int) cards.stream().filter(predicate).count();
	}

	public static Predicate<? super Card> is(Card target) {
		return new Predicate<Card>() {

			@Override
			public boolean test(Card c) {
				return c.equals(target);
			}
		};
	}

	public static Predicate<? super Card> is(Card.Type type) {
		return new Predicate<Card>() {

			@Override
			public boolean test(Card c) {
				return c.getType().equals(type);
			}
		};
	}

	public void addAll(Cards hand) {
		this.cards.addAll(hand.cards);
	}

}
