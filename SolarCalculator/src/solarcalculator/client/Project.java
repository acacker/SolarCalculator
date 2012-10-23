package solarcalculator.client;

import java.text.DecimalFormat;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HTML;

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

	private Double dailyHours = 4.0;
	private Double indicativePrice = 10000.00;
	private Double dailySolarGen;
	private Double annualSolarGen;
	private Double dailySave;
	private Double annualSave;
	private int paybackTime = 1;
	private Double replacementGen = 4.5;
	private Double exportGen;
	private Double culmulativeSave = 0.0;
	
	//advance information
	private Double importTariff = 0.19;// increasing annually
	private Double annualTariffInc = 0.05;
	private Double feedInFee = 0.30;
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
		dailySolarGen = (systemPower * (efficiencyNorth *(1 - efficiencyLossNorth) + efficiencyWest *(1- efficiencyLossWest)))* 
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
		int i = 0;
		paybackTime = -1;
		Double tempImportTaf = importTariff;
		while(culmulativeSave < indicativePrice){
			setYear(i);
			setImportTariff(importTariff);
			setAnnualSave();
			culmulativeSave += annualSave;
			paybackTime += 1;
			i++;
		}
		setYear(0);
		setImportTariff(tempImportTaf);
		culmulativeSave = 0.0;
	}
	public Integer getPaybackTime(){
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
	
	public Double getDailySolarGen() {
		return dailySolarGen;
	}

	public void setDailySolarGen(Double dailySolarGen) {
		this.dailySolarGen = dailySolarGen;
	}

	public Double getExportGen() {
		return exportGen;
	}

	public void setExportGen(Double exportGen) {
		this.exportGen = exportGen;
	}

	public Double getEfficiencyNorth() {
		return efficiencyNorth;
	}

	public void setEfficiencyNorth(Double efficiencyNorth) {
		this.efficiencyNorth = efficiencyNorth;
	}

	public Double getEfficiencyWest() {
		return efficiencyWest;
	}

	public void setEfficiencyWest(Double efficiencyWest) {
		this.efficiencyWest = efficiencyWest;
	}

	public Double getEfficiencyLossNorth() {
		return efficiencyLossNorth;
	}

	public void setEfficiencyLossNorth(Double efficiencyLossNorth) {
		this.efficiencyLossNorth = efficiencyLossNorth;
	}

	public Double getEfficiencyLossWest() {
		return efficiencyLossWest;
	}

	public void setEfficiencyLossWest(Double efficiencyLossWest) {
		this.efficiencyLossWest = efficiencyLossWest;
	}

	public Double getPanelEfficiency() {
		return panelEfficiency;
	}

	public void setPanelEfficiency(Double panelEfficiency) {
		this.panelEfficiency = panelEfficiency;
	}

	public Double getPanelAgeEffLoss() {
		return PanelAgeEffLoss;
	}

	public void setPanelAgeEffLoss(Double panelAgeEffLoss) {
		PanelAgeEffLoss = panelAgeEffLoss;
	}

	public Double getInverterEfficiency() {
		return inverterEfficiency;
	}

	public void setInverterEfficiency(Double inverterEfficiency) {
		this.inverterEfficiency = inverterEfficiency;
	}

	public Double getDailySave() {
		return dailySave;
	}

	public void setDailySave(Double dailySave) {
		this.dailySave = dailySave;
	}
	
}
