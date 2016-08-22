package mtgsim;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Main {

	public static void main(String[] args) throws IOException {

		PrintStream filePrinter;

		filePrinter = new PrintStream(new FileOutputStream("csv/EldraziStompy-OnDraw-SerumPowder.csv"));
		new SimulatorRunner(new EldraziStompyMulliganSimulatorOnDraw() {
			@Override
			protected Deck createDeck() {
				return new EldraziStompyWithSerumPowder();
			}
		}, filePrinter).run();
		filePrinter.close();

		filePrinter = new PrintStream(new FileOutputStream("csv/EldraziStompy-OnDraw-Chalice.csv"));
		new SimulatorRunner(new EldraziStompyMulliganSimulatorOnDraw() {
			@Override
			protected Deck createDeck() {
				return new EldraziStompyWithChaliceOfTheVoid();
			}
		}, filePrinter).run();
		filePrinter.close();

		filePrinter = new PrintStream(new FileOutputStream("csv/EldraziStompy-OnPlay-SerumPowder.csv"));
		new SimulatorRunner(new EldraziStompyMulliganSimulatorOnPlay() {
			@Override
			protected Deck createDeck() {
				return new EldraziStompyWithSerumPowder();
			}
		}, filePrinter).run();
		filePrinter.close();

		filePrinter = new PrintStream(new FileOutputStream("csv/EldraziStompy-OnPlay-Chalice.csv"));
		new SimulatorRunner(new EldraziStompyMulliganSimulatorOnPlay() {
			@Override
			protected Deck createDeck() {
				return new EldraziStompyWithChaliceOfTheVoid();
			}
		}, filePrinter).run();
		filePrinter.close();

	}
}
