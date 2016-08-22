package mtgsim;

import mtgsim.cards.BlinkmothNexus;
import mtgsim.cards.CavernOfSouls;
import mtgsim.cards.Dismember;
import mtgsim.cards.EldraziMimic;
import mtgsim.cards.EldraziTemple;
import mtgsim.cards.EndlessOne;
import mtgsim.cards.EternalScourge;
import mtgsim.cards.GemstoneCaverns;
import mtgsim.cards.GhostQuarter;
import mtgsim.cards.MatterReshaper;
import mtgsim.cards.Mutavault;
import mtgsim.cards.RealitySmasher;
import mtgsim.cards.RelicOfProgenitus;
import mtgsim.cards.SeaGateWreckage;
import mtgsim.cards.SerumPowder;
import mtgsim.cards.ThoughtKnotSeer;
import mtgsim.cards.UrborgTombOfYawgmoth;
import mtgsim.cards.Wastes;

public class EldraziStompyWithSerumPowder extends Deck {

	{
		setName("Eldrazi Stompy with Serum Powder");
	}

	public EldraziStompyWithSerumPowder() {
		this.addMany(new EldraziMimic(), 4);
		this.addMany(new RealitySmasher(), 4);
		this.addMany(new EternalScourge(), 4);
		this.addMany(new MatterReshaper(), 4);
		this.addMany(new ThoughtKnotSeer(), 4);
		this.addMany(new EndlessOne(), 4);
		this.addMany(new SerumPowder(), 4);
		this.addMany(new RelicOfProgenitus(), 4);
		this.addMany(new Dismember(), 4);
		this.addMany(new EldraziTemple(), 4);
		this.addMany(new GemstoneCaverns(), 3);
		this.addMany(new GhostQuarter(), 4);
		this.addMany(new BlinkmothNexus(), 4);
		this.addMany(new Mutavault(), 2);
		this.addMany(new SeaGateWreckage(), 2);
		this.addMany(new CavernOfSouls(), 2);
		this.addMany(new UrborgTombOfYawgmoth(), 1);
		this.addMany(new Wastes(), 2);

		if (this.size() != 60) {
			throw new IllegalStateException("" + this.size());
		}
	}
}
