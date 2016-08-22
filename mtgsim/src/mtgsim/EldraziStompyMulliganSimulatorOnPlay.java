package mtgsim;

import mtgsim.cards.EldraziTemple;
import mtgsim.cards.EternalScourge;
import mtgsim.cards.SerumPowder;

public abstract class EldraziStompyMulliganSimulatorOnPlay extends MulliganSimulator<EldraziStompySimulationResult> {

	protected MulliganDecision analyze(Cards hand, Deck deck, Cards exile, EldraziStompySimulationResult result) {
		int numTemples = hand.count(Cards.is(new EldraziTemple()));

		boolean keepable = numTemples > 0 //
				&& hand.containsMin(2, Cards.is(Card.Type.Creature)) //
				&& hand.containsBetween(2, 4, Cards.is(Card.Type.Land)) //
		;

		result.handSize = hand.size();
		result.numTemples = numTemples;
		result.keepable = keepable;
		result.scourgeExiled = exile.containsMin(1, Cards.is(new EternalScourge()));

		if (!keepable) {
			if (hand.containsMin(1, Cards.is(new SerumPowder()))) {
				result.numSerumPowderUsages++;
				// account for serum powder
				exile.addAll(hand);
				return MulliganDecision.mulligan(0);
			} else {
				// put the cards back into the deck
				deck.addAll(hand);
				return MulliganDecision.mulligan(-1);
			}

		}

		return MulliganDecision.keep();
	}

	@Override
	protected EldraziStompySimulationResult createSimulationResult() {
		return new EldraziStompySimulationResult();
	}

	// private static Stream<EldraziStompySimulationResult>
	// getKeepable(Stream<EldraziStompySimulationResult> stream,
	// int handSize) {
	// return stream.filter(r -> r.keepable && r.handSize == handSize);
	// }
	//
	// public static void main(String[] args) throws Exception {
	// simulateDeck(EldraziStompyWithSerumPowder.class);
	// simulateDeck(EldraziStompyWithChaliceOfTheVoid.class);
	// }
	//
	// private static void simulateDeck(Class<? extends Deck> deckClazz)
	// throws Exception, InstantiationException, IllegalAccessException {
	// EldraziStompyMulliganSimulatorOnPlay simulator = new
	// EldraziStompyMulliganSimulatorOnPlay(deckClazz);
	// int numIterations = 100_000;
	// List<EldraziStompySimulationResult> result =
	// simulator.simulate(numIterations);
	//
	// Deck deck = deckClazz.newInstance();
	// System.out.println("-------------------------------------------");
	// System.out.println("Deck Name: " + deck.getName());
	// System.out.println("Decklist: ");
	// deck.printListTo(System.out);
	// System.out.println("Total simulations: " + result.size());
	// System.out.println("On the play. Mulligan up to 5.");
	// System.out.println(
	// "Keepable hand criteria: has at least 1 Eldrazi Temple AND has at least 2
	// creatures AND has 2 to 4 lands.");
	// for (int n = 7; n >= 5; n--) {
	// long numSuccess = getKeepable(result.stream(), n).count();
	// System.out.println(String.format("Keepable %d: %d (%.2f%% of all)", n,
	// numSuccess,
	// percentage(numSuccess, result.size())));
	// breakdown(result, n);
	// breakdownSerumUsages(result, n);
	//
	// }
	// }
	//
	// private static void breakdown(List<EldraziStompySimulationResult> result,
	// int handSize) {
	// long numInterest = getKeepable(result.stream(), handSize).filter(r ->
	// r.scourgeExiled).count();
	// System.out.println(
	// String.format("\tIncluding with Eternal Scourge in exile: %d (%.2f%% of
	// all) (%.2f%% of group)", //
	// numInterest, //
	// percentage(numInterest, result.size()), //
	// percentage(numInterest, getKeepable(result.stream(), handSize).count()),
	// //
	// handSize));
	// }
	//
	// private static void
	// breakdownSerumUsages(List<EldraziStompySimulationResult> result, int
	// handSize) {
	// for (int i = 0; i <= 4; i++) {
	// int nUsages = i;
	// long numInterest = getKeepable(result.stream(), handSize).filter(r ->
	// r.numSerumPowderUsages == nUsages)
	// .count();
	// System.out.println(
	// String.format("\tIncluding with %d Serum Powder usage: %d (%.2f%% of all)
	// (%.2f%% of group)", //
	// nUsages, //
	// numInterest, //
	// percentage(numInterest, result.size()), //
	// percentage(numInterest, getKeepable(result.stream(), handSize).count()),
	// //
	// handSize));
	//
	// }
	// }
	//
	// private static double percentage(long n, long total) {
	// return 100d * n / total;
	// }

}
