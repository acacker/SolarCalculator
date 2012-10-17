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
	private Integer year;
	private Double systemPower = 4.5; // kW

	private Double dailyHours = 4.5;
	private Double indicativePrice = 10000.00;
	private Double dailySolarGen;
	private double annualSolarGen;
	private Double dailySave;
	private Double annualSave;
	private Double paybackTime;
	private Double replacementGen = 4.5;
	private Double exportGen;
	private Double compountInvRate=0.05;
	private Double culmulativeSave;
	
	//advance information
	private Double importTariff = 0.19;// increasing annually
	private Double annualTariffInc = 0.005;
	private Double feedInFee = 0.50;
	private Double efficiencyNorth = 0.38;
	private Double efficiencyWest = 0.62;
	private Double efficiencyLossNorth = 0.05;
	private Double efficiencyLossWest = 0.15;
	private Double panelEfficiency = 1.0;// decreasing annually
	private Double PanelAgeEffLoss = 0.01;
	private Double inverterEfficiency = 0.96;

	public Project() {
	}
	
	public Project(Double power, Double price){
		systemPower=power;
		indicativePrice=price;
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
	public void setYear(Integer year) {
		this.year = year;
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
	 * @return the dailyHours
	 */
	public Double getDailyHours() {
		return dailyHours;
	}
	/**
	 * @param dailyHours the dailyHours to set
	 */
	public void setDailyHours(Double dailyHours) {
		this.dailyHours = dailyHours;
	}
	
	/**
	 * @return the culmulativeSave
	 */
	public Double getCulmulativeSave() {
		return culmulativeSave;
	}

	/**
	 * @param indicativePrice the indicativePrice to set
	 */
	public void setIndicativePrice(Double indicativePrice) {
		this.indicativePrice = indicativePrice;
	}
	/*
	 * @return indicativePrice
	 */
	public Double getIndicativePrice() {
		return indicativePrice;
	}	

	/**
	 * @return the replacementGen
	 */
	public Double getReplacementGen() {
		return replacementGen;
	}
	/**
	 * @param replacementGen the replacementGen to set
	 */
	public void setReplacementGen(Double yearlyEnergyUse) {
		this.replacementGen = dailyHours * 1.0 * yearlyEnergyUse/365;
	}
	
	public void setAnnualSolarGen(){
		dailySolarGen = (systemPower * (efficiencyNorth *(1-efficiencyLossNorth) + efficiencyWest *(1- efficiencyLossWest)))* 
				panelEfficiency*Math.pow((1-PanelAgeEffLoss), year) * inverterEfficiency * dailyHours;
		exportGen = dailySolarGen - replacementGen;
		annualSolarGen = dailySolarGen*365;
	}
	public Double getAnnualSolarGen(){
		return annualSolarGen;
	}

	public void setAnnualSave(){
		dailySave = replacementGen * importTariff + exportGen * feedInFee;
		annualSave = dailySave*365;
	}
	public Double getAnnualSave(){
		return annualSave;
	}
	
	public void setPaybackTime() {
		culmulativeSave = annualSave * paybackTime;
		Double compoundInvReturn = indicativePrice * Math.pow(1 + compountInvRate, paybackTime);
		while(culmulativeSave <= compoundInvReturn){
			paybackTime += 0.1;
			culmulativeSave = annualSave * paybackTime;
			compoundInvReturn= annualSave * Math.pow(1+compountInvRate, paybackTime);
		}
		
	}
	public Double getPaybackTime(){
		return paybackTime;
	}

	public Double getImportTariff() {
		return importTariff;
	}

	public void setImportTariff(Double taf) {
		this.importTariff = taf * Math.pow((1 + annualTariffInc),year);
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
}
