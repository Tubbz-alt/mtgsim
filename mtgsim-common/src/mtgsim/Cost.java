package mtgsim;

import java.util.HashMap;
import java.util.Map;

public class Cost {

	public enum CostComponent {
		GENERIC
	}

	private Map<CostComponent, Integer> cost = new HashMap<>();

	public Cost(String cost) {
//		this.cost = parseCost(cost);
	}

//	private static Map<CostComponent, Integer> parseCost(String cost) {
//		Map<CostComponent, Integer> breakdown = new HashMap<>();
//		if (cost.contains("X")) {
//			breakdown.put(CostComponent.GENERIC, 0);
//		} else {
//			breakdown.put(CostComponent.GENERIC, Integer.parseInt(cost));
//		}
//		return breakdown;
//	}

	public int getCmc() {
		return cost.values().stream().mapToInt(a -> a).sum();
	}
}
