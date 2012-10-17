package solarcalculator.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.user.client.ui.TextBox;

public class UseEnergyTest {
	private UseEnergy instance;

	@Before
	public void setUp() throws Exception {
		instance= new UseEnergy();
	}

	@Test
	public void testSetYearlyEnergyUseByDailyUsage() {
		String energyUse="1.0";
		instance.setEnergyUse(energyUse);
		assertTrue(instance.getEnergyUse()==1.0);
		instance.setYearlyEnergyUse(0);
		assertTrue(instance.getYearlyEnergyUse()==365.0);
	}
	
	@Test
	public void testSetYearlyEnergyUseByMonthlyUsage() {
		String energyUse="1.0";
		instance.setEnergyUse(energyUse);
		assertTrue(instance.getEnergyUse()==1.0);
		instance.setYearlyEnergyUse(1);
		assertTrue(instance.getYearlyEnergyUse()==12.0);
	}
	
	@Test
	public void testSetYearlyEnergyUseByQuarterlyUsage() {
		String energyUse="1.0";
		instance.setEnergyUse(energyUse);
		assertTrue(instance.getEnergyUse()==1.0);
		instance.setYearlyEnergyUse(2);
		assertTrue(instance.getYearlyEnergyUse()==4.0);
	}


	@Test
	public void testSetEnergyUse() {
		String energyUse="1.0";
		instance.setEnergyUse(energyUse);
		assertTrue(instance.getEnergyUse()==1.0);
	}
	
	@Test
	public void testGetEnergyUse() {
		instance.setEnergyUse("123.00");
		assertTrue(instance.getEnergyUse()==123.00);
	}

}
