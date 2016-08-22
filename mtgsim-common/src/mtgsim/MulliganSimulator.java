package mtgsim;

import java.util.Random;

public abstract class MulliganSimulator<SR extends SimulationResult> extends Simulator<SR> {

	public static class MulliganDecision {
		private boolean keep = true;

		/**
		 * Hand size change after a mulligan is not always -1. It may be 0 if
		 * cards like Serum Powder are involved.
		 */
		private int handSizeChange;

		private MulliganDecision(boolean keep, int handSizeChange) {
			super();
			this.keep = keep;
			this.handSizeChange = handSizeChange;
		}

		public boolean isKeep() {
			return keep;
		}

		public int getHandSizeChange() {
			return handSizeChange;
		}

		public boolean isMulligan() {
			return !isKeep();
		}

		public static MulliganDecision mulligan(int handSizeChange) {
			return new MulliganDecision(false, handSizeChange);
		}

		public static MulliganDecision keep() {
			return new MulliganDecision(true, 0);
		}

	}

	protected int minCardsInHand = 5;

	@Override
	protected SR simulateIteration(Random random) {
		Deck deck = createDeck();
		Cards exile = new Cards();
		SR simulationResult = createSimulationResult();

		MulliganDecision mulliganResult;
		int numCardsToDraw = 7;
		do {
			deck.shuffle(random);
			Cards hand = deck.draw(numCardsToDraw);
			mulliganResult = analyze(hand, deck, exile, simulationResult);
			numCardsToDraw += mulliganResult.getHandSizeChange();
		} while (mulliganResult.isMulligan() && numCardsToDraw >= getMinCardsInHand());

		return simulationResult;

	}

	public int getMinCardsInHand() {
		return minCardsInHand;
	}

	public void setMinCardsInHand(int minCardsInHand) {
		this.minCardsInHand = minCardsInHand;
	}

	protected abstract MulliganDecision analyze(Cards hand, Deck deck, Cards exile, SR simulationResult);

	protected abstract SR createSimulationResult();

	protected abstract Deck createDeck();

}
