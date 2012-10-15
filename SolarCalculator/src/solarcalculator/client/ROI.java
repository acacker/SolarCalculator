/**
 * 
 */
package solarcalculator.client;

/**
 * @author greyerg
 *
 */
public class ROI {
	private Double increaseRate;
	private int year;
	private Double importTariff;
	private Double exportTariff;
	private Double SolarFitTariff;
	private Double importCost;
	private Double exportValue;
	private Double usedValue;
	private Double SolarFitValue;
	private Double YearTotal;
	private Double AcumTotal;
	private Double RateOI;
	
	private UseEnergy useEnergy;
	private Project project;
	
	public void initTable(){
		year = 0;
		importTariff = project.getImportCost();
		exportTariff = project.getFeedInFee();
		SolarFitTariff = project.getTariffFee();
	}
	
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int colum) {
		this.year = year + colum;
	}
	/**
	 * @return the importTariff
	 */
	public Double getImportTariff() {
		return importTariff;
	}
	/**
	 * @param importTariff the importTariff to set
	 */
	public void setImportTariff(int colum) {
		this.importTariff = importTariff*(1+Math.pow(increaseRate, colum));
	}
	/**
	 * @return the exportTariff
	 */
	public Double getExportTariff() {
		return exportTariff;
	}
	/**
	 * @param exportTariff the exportTariff to set
	 */
	public void setExportTariff(int colum) {
		this.exportTariff = exportTariff*(1+Math.pow(increaseRate, colum));
	}
	/**
	 * @return the solarFitTariff
	 */
	public Double getSolarFitTariff() {
		return SolarFitTariff;
	}
	/**
	 * @return the importCost
	 */
	public Double getImportCost() {
		return importCost;
	}
	/**
	 * @param importCost the importCost to set
	 */
	public void setImportCost(Double importCost) {
		this.importCost = (useEnergy.getYearlyEnergyUse() - project.getDayTimeUsage()) * importTariff;
	}
	/**
	 * @return the exportValue
	 */
	public Double getExportValue() {
		return exportValue;
	}
	/**
	 * @param exportValue the exportValue to set
	 */
	public void setExportValue(Double exportValue) {
		this.exportValue = (project.getAnnualSolarGen() - project.getDayTimeUsage()) * exportTariff;
	}
	/**
	 * @return the usedValue
	 */
	public Double getUsedValue() {
		return usedValue;
	}
	/**
	 * @param usedValue the usedValue to set
	 */
	public void setUsedValue(Double usedValue) {
		this.usedValue = project.getDayTimeUsage() * importTariff;
	}
	/**
	 * @return the solarFitValue
	 */
	public Double getSolarFitValue() {
		return SolarFitValue;
	}
	/**
	 * @param solarFitValue the solarFitValue to set
	 */
	public void setSolarFitValue(Double solarFitValue) {
		SolarFitValue = project.getAnnualSolarGen() * SolarFitTariff;
	}
	/**
	 * @return the yearTotal
	 */
	public Double getYearTotal() {
		return YearTotal;
	}
	/**
	 * @param yearTotal the yearTotal to set
	 */
	public void setYearTotal(Double yearTotal) {
	
	}
	/**
	 * @return the acumTotal
	 */
	public Double getAcumTotal() {
		return AcumTotal;
	}
	/**
	 * @param acumTotal the acumTotal to set
	 */
	public void setAcumTotal(Double acumTotal) {
		AcumTotal = acumTotal;
	}
	/**
	 * @return the rateOI
	 */
	public Double getRateOI() {
		return RateOI;
	}
	/**
	 * @param rateOI the rateOI to set
	 */
	public void setRateOI(Double rateOI) {
		RateOI = rateOI;
	}
	
	
}
