package solarcalculator.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Use Energy include all the function need to have in Use Energy Tab.
 * @author grey
 *
 */

public class UseEnergy {
	private Double EnergyUse;
	private Double YearlyEnergyUse;
	private Double AvgRate;
	private Double price1;
	private Double price2;
	private Double rate1;
	private Double rate2;
	
	// show yearly energy use Result
	public Double getYearlyEnergyUse() {
		return YearlyEnergyUse;
	}
	public void setYearlyEnergyUse(int selection) {
		if(selection == 0)
			YearlyEnergyUse = EnergyUse * 365;
		else if(selection == 1)
			YearlyEnergyUse = EnergyUse * 12;
		else if(selection == 2)
			YearlyEnergyUse = EnergyUse * 4;
		else
			return;
	}
	// input usage
	public void setEnergyUse(String input)
	{
		// TODO Auto-generated method stub
		if (!input.matches("^[0-9]*$")) {
			Window.alert("'" + input + "' is not a valid symbol.");
			return;
		}	
		EnergyUse = Double.parseDouble(input);
	}
	
	public Double getEnergyUse(){
		return EnergyUse;
	}
	/**
	 * @return the avgRate
	 */
	public Double getAvgRate() {
		return AvgRate;
	}
	/**
	 * @param avgRate the avgRate to set
	 */
	public void setAvgRate() {
		AvgRate = (rate1 * price1 + rate2 * price2)/(rate1 + rate2);
	}
	/**
	 * @return the price1
	 */
	public Double getPrice1() {
		return price1;
	}
	/**
	 * @param price1 the price1 to set
	 */
	public void setPrice1(String input) {
		if (!input.matches("^[0-9]*$")) {
			Window.alert("'" + input + "' is not a valid symbol.");
			return;
		}	
		this.price1 = Double.parseDouble(input);
	}
	/**
	 * @return the price2
	 */
	public Double getPrice2() {
		return price2;
	}
	/**
	 * @param price2 the price2 to set
	 */
	public void setPrice2(String input) {
		if (!input.matches("^[0-9]*$")) {
			Window.alert("'" + input + "' is not a valid symbol.");
			return;
		}	
		this.price2 = Double.parseDouble(input);
	}
	/**
	 * @return the rate1
	 */
	public Double getRate1() {
		return rate1;
	}
	/**
	 * @param rate1 the rate1 to set
	 */
	public void setRate1(String input) {
		if (!input.matches("^[0-9]*$")) {
			Window.alert("'" + input + "' is not a valid symbol.");
			return;
		}	
		this.rate1 = Double.parseDouble(input);
	}
	/**
	 * @return the rate2
	 */
	public Double getRate2() {
		return rate2;
	}
	/**
	 * @param rate2 the rate2 to set
	 */
	public void setRate2(String input) {
		if (!input.matches("^[0-9]*$")) {
			Window.alert("'" + input + "' is not a valid symbol.");
			return;
		}	
		this.rate2 = Double.parseDouble(input);
	}
}
