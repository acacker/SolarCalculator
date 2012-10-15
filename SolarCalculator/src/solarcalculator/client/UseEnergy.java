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
	private Double YearlyEnergyUse = 0.0;
	
	public UseEnergy(){
		
	}
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
	public void setEnergyUse(Double d)
	{
		String temp = d.toString();
		// TODO Auto-generated method stub
		if (!temp.matches("^[0-9.]*$")) {
			Window.alert("'" + d + "' is not a valid symbol.");
			return;
		}	
		else
			EnergyUse = d;
	}
	
	public Double getEnergyUse(){
		return EnergyUse;
	}

}
