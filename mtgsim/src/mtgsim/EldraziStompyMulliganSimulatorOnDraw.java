package mtgsim;

import mtgsim.cards.EldraziTemple;
import mtgsim.cards.EternalScourge;
import mtgsim.cards.GemstoneCaverns;
import mtgsim.cards.SerumPowder;

public abstract class EldraziStompyMulliganSimulatorOnDraw extends MulliganSimulator<EldraziStompySimulationResult> {

	protected MulliganDecision analyze(Cards hand, Deck deck, Cards exile, EldraziStompySimulationResult result) {
		int numTemples = hand.count(Cards.is(new EldraziTemple()));

		boolean keepable = //
				hand.containsMin(2, Cards.is(Card.Type.Creature)) //
						&& (//
						(//
						numTemples > 0 //
								&& hand.containsBetween(2, 4, Cards.is(Card.Type.Land))//
						) || (//
						hand.containsMin(1, Cards.is(new GemstoneCaverns()))//
								&& hand.containsBetween(3, 5, Cards.is(Card.Type.Land))//
						)//
						)//
		;

		result.handSize = hand.size();
		result.numTemples = numTemples;
		result.keepable = keepable;
		result.scourgeExiled = exile.containsMin(1, Cards.is(new EternalScourge()));
		result.scourgeInHandWithCavern = hand.containsMin(1, Cards.is(new EternalScourge()))
				&& hand.containsMin(1, Cards.is(new GemstoneCaverns()));

		if (!keepable) {
			if (hand.containsMin(1, Cards.is(new SerumPowder()))) {
				// account for serum powder
				exile.addAll(hand);
				result.numSerumPowderUsages++;
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
	// EldraziStompyMulliganSimulatorOnDraw simulator = new
	// EldraziStompyMulliganSimulatorOnDraw(deckClazz);
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
	// System.out.println("On the draw. Mulligan up to 4.");
	// System.out.println(
	// "Keepable hand criteria: ( (has at least 1 Eldrazi Temple AND 2 to 4
	// lands) OR (has Gemstone Caverns AND 3 to 5 lands) )"
	// + " AND has at least 2 creatures");
	// for (int n = 7; n >= 4; n--) {
	// long numSuccess = getKeepable(result.stream(), n).count();
	// System.out.println(String.format("Keepable %d: %d (%.2f%% of all)", n,
	// numSuccess,
	// percentage(numSuccess, result.size())));
	// breakdown(result, n);
	// breakdownSerumUsages(result, n);
	// }
	// }
	//
	// private static void breakdown(List<EldraziStompySimulationResult> result,
	// int handSize) {
	// long interest1 = getKeepable(result.stream(), handSize).filter(r ->
	// r.scourgeExiled).count();
	// System.out.println(
	// String.format("\tIncluding with Eternal Scourge in exile: %d (%.2f%% of
	// all) (%.2f%% of group)", //
	// interest1, //
	// percentage(interest1, result.size()), //
	// percentage(interest1, getKeepable(result.stream(), handSize).count()), //
	// handSize));
	// long interest2 = getKeepable(result.stream(), handSize).filter(r ->
	// r.scourgeInHandWithCavern).count();
	// System.out.println(String.format(
	// "\tIncluding with Eternal Scourge to be exiled with Gemstone Caverns: %d
	// (%.2f%% of all) (%.2f%% of group)", //
	// interest2, //
	// percentage(interest2, result.size()), //
	// percentage(interest2, getKeepable(result.stream(), handSize).count()), //
	// handSize));
	// long interest3 = getKeepable(result.stream(), handSize)
	// .filter(r -> r.scourgeInHandWithCavern || r.scourgeExiled).count();
	// System.out.println(String.format(
	// "\tIncluding with Eternal Scourge in exile OR to be exiled with Gemstone
	// Caverns: %d (%.2f%% of all) (%.2f%% of group)", //
	// interest3, //
	// percentage(interest3, result.size()), //
	// percentage(interest3, getKeepable(result.stream(), handSize).count()), //
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
