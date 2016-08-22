package mtgsim;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Simulator<SR extends SimulationResult> {

	public List<SR> simulate(int numIterations) {
		List<SR> results = new LinkedList<>();
		for (int seed = 0; seed < numIterations; seed++) {
			Random random = new Random(seed);
			results.add(simulateIteration(random));
		}
		return results;
	}

	protected abstract SR simulateIteration(Random random);

}
