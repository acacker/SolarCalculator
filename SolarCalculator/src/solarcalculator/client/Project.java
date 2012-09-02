package solarcalculator.client;

/**
 * project includes all data relevant for calculation
 * 
 * @author Lance
 * 
 */
public class Project {

	private Double systemPower = 4.95; // kW
	private Double efficiencyNorth = 0.38;
	private Double efficiencyWest = 0.62;
	private Double efficiencyLossNorth = 0.05;
	private Double efficiencyLossWest = 0.15;
	private Double panelEfficiency = 1.0;// decreasing annually
	private Double PanelAgeEffLoss = 0.01;
	private Double inverterEfficiency = 0.96;
	private Double dailyHours = 4.5;
	private Double tariffFee = 0.19;// increasing annually
	private Double annualTariffInc = 0.05;
	private Double feedInFee = 0.50;
	private Double indicativePrice = 18000.00;
	private Double dailySolarGen;
	private Double dailySave;
	private Double paybackTime;
	private Double replacementGen = 4.5;
	private Double exportGen;
	private Double compountInvRate=0.05;

	public Project() {
	}

	public Double getExportGen() {
		exportGen = this.getDailySolarGen() - replacementGen;
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
				* panelEfficiency * inverterEfficiency * dailyHours * 365;
		return dailySolarGen;
	}

	public Double getDailySave() {
		dailySave = replacementGen * tariffFee + this.getExportGen() * feedInFee;
		return dailySave;
	}

	public Double getAnnualSave(){
		return this.getDailySave()*365;
	}
	
	public Double getPaybackTime() {
		Double paybackTime=0.0;
		Double culmulativeSave=this.getAnnualSave()*paybackTime;
		Double compoundInvReturn=this.getIndicativePrice()*Math.pow(1+compountInvRate, paybackTime);
		while(culmulativeSave<=compoundInvReturn){
			paybackTime+=0.1;
			culmulativeSave=this.getAnnualSave()*paybackTime;
			compoundInvReturn=this.getIndicativePrice()*Math.pow(1+compountInvRate, paybackTime);
		}
		return paybackTime;
	}


}
