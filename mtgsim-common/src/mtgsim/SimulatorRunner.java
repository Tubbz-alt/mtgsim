package mtgsim;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;

public class SimulatorRunner {
	private Simulator<? extends SimulationResult> simulator;
	private int numIterations = 100_000;
	private PrintStream outputStream = System.out;

	public SimulatorRunner(Simulator<? extends SimulationResult> simulator, PrintStream outputStream) {
		this.simulator = simulator;
		this.outputStream = outputStream;
	}

	public void run() {

		List<? extends SimulationResult> results = simulator.simulate(numIterations);
		int i = 0;
		for (SimulationResult result : results) {
			Map<String, Object> fields = result.getFields();
			if (i == 0) {
				// write header
				int j = 0;
				for (String field : fields.keySet()) {
					if (j > 0) {
						outputStream.print(",");
					}
					outputStream.print(field);
					j++;
				}
				outputStream.println();
			}

			int j = 0;
			for (String field : fields.keySet()) {
				if (j > 0) {
					outputStream.print(",");
				}
				outputStream.print(fields.get(field));
				j++;
			}
			outputStream.println();
			i++;
		}
	}
}
