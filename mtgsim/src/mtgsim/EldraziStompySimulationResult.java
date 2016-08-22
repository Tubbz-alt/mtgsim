package mtgsim;

import java.util.LinkedHashMap;
import java.util.Map;

class EldraziStompySimulationResult implements SimulationResult {
	public int handSize;
	public int numTemples;
	public boolean keepable;
	public boolean scourgeExiled;
	public boolean scourgeInHandWithCavern;
	public int numSerumPowderUsages;

	@Override
	public Map<String, Object> getFields() {
		Map<String, Object> fields = new LinkedHashMap<>();
		fields.put("keepable", keepable);
		fields.put("hand size", handSize);
		fields.put("num temples", numTemples);
		fields.put("scourge exiled", scourgeExiled);
		fields.put("scourge in hand with cavern", scourgeInHandWithCavern);
		fields.put("serum powder usages", numSerumPowderUsages);
		return fields;
	}

}
