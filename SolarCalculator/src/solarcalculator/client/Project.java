package solarcalculator.client;

import java.text.DecimalFormat;

import com.google.gwt.i18n.client.NumberFormat;

/**
 * project includes all data relevant for calculation
 * 
 * @author Lance
 * 
 * limited 2 number after dot
 * 
 */
public class Project {
	
	private Integer year = 0;
	private Double systemPower = 4.5; // kW
	private Double efficiencyNorth = 0.38;
	private Double efficiencyWest = 0.62;
	private Double efficiencyLossNorth = 0.05;
	private Double efficiencyLossWest = 0.15;
	private Double panelEfficiency = 1.0;// decreasing annually
	private Double PanelAgeEffLoss = 0.01;
	private Double inverterEfficiency = 0.96;
	private Double dailySunLightHr = 4.5;
	private Double dayTimeUsage;
	private Double dayTimeRate;
	/**
	 * @return the dayTimeRate
	 */
	public Double getDayTimeRate() {
		return dayTimeRate;
	}

	/**
	 * @param dayTimeRate the dayTimeRate to set
	 */
	public void setDayTimeRate() {
		this.dayTimeRate = dailySunLightHr/24;
	}


	private Double tariffFee = 0.19;// increasing annually
	private Double annualTariffInc = 0.005;
	private Double feedInFee = 0.50;
	private Double indicativePrice = 10000.00;
	private Double dailySolarGen;
	private Double dailySave;
	private Double paybackTime;
	private Double exportGen;
	private Double compountInvRate=0.05;
	private Double importCost = 0.0;
	/**
	 * @return the annualTariffInc
	 */
	public Double getAnnualTariffInc() {
		return annualTariffInc;
	}

	/**
	 * @param annualTariffInc the annualTariffInc to set
	 */
	public void setAnnualTariffInc(Double annualTariffInc) {
		this.annualTariffInc = annualTariffInc;
	}




	public Project() {
	}
	
	public Project(Double power, Double price){
		systemPower=power;
		indicativePrice=price;
	}
	

	/**
	 * @return the systemPower
	 */
	public Double getSystemPower() {
		return systemPower;
	}

	/**
	 * @param systemPower the systemPower to set
	 */
	public void setSystemPower(Double systemPower) {
		this.systemPower = systemPower;
	}

	/**
	 * @return the efficiencyNorth
	 */
	public Double getEfficiencyNorth() {
		return efficiencyNorth;
	}

	/**
	 * @param efficiencyNorth the efficiencyNorth to set
	 */
	public void setEfficiencyNorth(Double efficiencyNorth) {
		this.efficiencyNorth = efficiencyNorth;
	}

	/**
	 * @return the efficiencyWest
	 */
	public Double getEfficiencyWest() {
		return efficiencyWest;
	}

	/**
	 * @param efficiencyWest the efficiencyWest to set
	 */
	public void setEfficiencyWest(Double efficiencyWest) {
		this.efficiencyWest = efficiencyWest;
	}

	/**
	 * @return the dailySunLightHr
	 */
	public Double getdailySunLightHr() {
		return dailySunLightHr;
	}

	/**
	 * @param dailySunLightHr the dailySunLightHr to set
	 */
	public void setdailySunLightHr(Double dailySunLightHr) {
		this.dailySunLightHr = dailySunLightHr;
	}

	/**
	 * @return the compountInvRate
	 */
	public Double getCompountInvRate() {
		return compountInvRate;
	}

	/**
	 * @param compountInvRate the compountInvRate to set
	 */
	public void setCompountInvRate(Double compountInvRate) {
		this.compountInvRate = compountInvRate;
	}

	/**
	 * @param indicativePrice the indicativePrice to set
	 */
	public void setIndicativePrice(Double indicativePrice) {
		this.indicativePrice = indicativePrice;
	}

	public Double getExportGen() {
		exportGen = this.getDailySolarGen() - dayTimeUsage;
		return exportGen;
	}

	public void setExportGen(Double exportGen) {
		this.exportGen = exportGen;
	}

	public Double getIndicativePrice() {
		return indicativePrice;
	}

	public Double getDailySolarGen() {
		dailySolarGen = (systemPower * (efficiencyNorth *(1-efficiencyLossNorth) + efficiencyWest *(1- efficiencyLossWest)))* 
				panelEfficiency*Math.pow((1-PanelAgeEffLoss), year) * inverterEfficiency * dailySunLightHr;
		return dailySolarGen;
	}
	
	public Double getAnnualSolarGen(){
		return getDailySolarGen()*365;
	}
	
	public Double getDayTimeUsage(){
		return dayTimeUsage;
	}

	public void setDayTimeUsage(Double yearlyEnergyUse) {
		this.dayTimeUsage = yearlyEnergyUse/365 * dayTimeRate;
	}

	public Double getDailySave() {
		dailySave = dayTimeUsage * tariffFee + this.getExportGen() * feedInFee;
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

	public Double getTariffFee() {
		return tariffFee;
	}

	public void setIndexTariff(Double taf) {
		this.tariffFee = taf;
	}
	public void setTariffFee() {
		this.tariffFee = tariffFee*Math.pow((1 + annualTariffInc) , year);
	}

	public Double getFeedInFee() {
		return feedInFee;
	}

	public void setFeedInFee(Double feedInFee) {
		this.feedInFee = feedInFee;
	}

	public String parseNumberFormat(Double value){
		NumberFormat fmt=NumberFormat.getFormat("#0.00");
		return fmt.format(value);
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the importCost
	 */
	public Double getImportCost() {
		return importCost;
	}
	
	public Double getUsedValue(){
		return dayTimeUsage * tariffFee;
	}
	
	
	public Double getExportValue(){
		return exportGen * feedInFee;
	}
	
}
