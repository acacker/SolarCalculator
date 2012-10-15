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
	public void testGetYearlyEnergyUse() {
		instance.setEnergyUse(120.00);
		instance.setYearlyEnergyUse(2);
		System.out.println(instance.getYearlyEnergyUse().toString());
	}

	@Test
	public void testSetYearlyEnergyUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEnergyUse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEnergyUse() {
		instance.setEnergyUse(1.00);
		System.out.println(instance.getEnergyUse());
	}

}
