package solarcalculator.client;
/**
 * project includes all data relevant for calculation
 * 
 * @author Lance
 *
 */
public class Project {

	private Double systemPower=4.95; // kW
	private Double efficiencyNorth=0.38;
	private Double efficiencyWest=0.62;
	private Double efficiencyLossNorth=0.05;
	private Double efficiencyLossWest=0.15;
	private Double panelEfficiency=1.0;//decreasing annually
	private Double PanelAgeEffLoss=0.01;
	private Double inverterEfficiency=0.96;
	private Double dailyHours=4.5;
	private Double tariffFee=0.19;//increasing annually
	private Double annualTariffInc=0.05;
	private Double feedInFee=0.50;
	private Double indicativePrice;
	private Double dailySolarGen;
	private Double dailySave;
	private Double paybackTime;
	private Double replacementGen=4.5;
	private Double exportGen;


	public Project() {
	}

	public Double getExportGen() {
		exportGen=dailySolarGen-replacementGen;
		return exportGen;
	}

	public void setExportGen(Double exportGen) {
		this.exportGen = exportGen;
	}

	public Double getIndicativePrice() {
		return indicativePrice;
	}

	public Double getDailySolarGen() {
		dailySolarGen = ((systemPower * efficiencyNorth * (1 - efficiencyLossNorth)) + (systemPower
				* efficiencyWest * (1 - efficiencyLossWest)))
				* panelEfficiency * inverterEfficiency * dailyHours*365;
		return dailySolarGen;
	}

	public Double getDailySave() {
		dailySave=replacementGen*tariffFee+exportGen*feedInFee;
		return dailySave;
	}

	public Double getPaybackTime() {
		return paybackTime;
	}

}
