package solarcalculator.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.google.gwt.i18n.client.NumberFormat;

public class ProjectTest {
	
	private Project instance0;
	private Project instance1;

	@Before
	public void setUp() throws Exception {
		instance0= new Project();
		instance1=new Project(1.0, 1.0);
	}

	@Test
	public void testProject() {
		assertEquals(Project.class, instance0.getClass());
	}

	@Test
	public void testProjectDoubleDouble() {
		assertEquals(Project.class, instance1.getClass());
	}

	@Test
	public void testGetYear() {
		assertTrue(instance0.getYear() == 0);
	}

	@Test
	public void testSetYear() {
		instance0.setYear(1);
		instance1.setYear(2);
		assertTrue(instance0.getYear()==1);
		assertTrue(instance1.getYear()==2);
	}

	@Test
	public void testGetSystemPower() {
		assertTrue(instance0.getSystemPower()==4.5);
	}

	@Test
	public void testSetSystemPower() {
		instance0.setSystemPower(1.0);
		assertTrue(instance0.getSystemPower()==1.0);
	}

	@Test
	public void testGetDailyHours() {
		assertTrue(instance0.getDailyHours()==4.0);
	}

	@Test
	public void testSetDailyHours() {
		instance0.setDailyHours(1.0);
		assertTrue(instance0.getDailyHours()==1.0);
	}

	@Test
	public void testGetCulmulativeSave() {
		assertEquals(null, instance0.getCulmulativeSave());
	}

	@Test
	public void testSetIndicativePrice() {
		instance0.setIndicativePrice(1.0);
		assertTrue(instance0.getIndicativePrice()==1.0);
	}

	@Test
	public void testGetIndicativePrice() {
		assertTrue(instance0.getIndicativePrice()==10000.0);
	}

	@Test
	public void testGetReplacementGen() {
		assertTrue(instance0.getIndicativePrice()==10000.0);
	}

	@Test
	public void testSetReplacementGen() {
		instance0.setDailyHours(1.0);
		instance0.setReplacementGen(365.00);
		assertTrue(instance0.getReplacementGen()==1.0);
	}

	@Test
	public void testSetAnnualSolarGen() {
		instance0.setSystemPower(1.0);
		instance0.setEfficiencyNorth(1.0);
		instance0.setEfficiencyLossNorth(0.0);
		instance0.setEfficiencyWest(1.0);
		instance0.setEfficiencyLossWest(0.0);
		instance0.setPanelEfficiency(1.0);
		instance0.setPanelAgeEffLoss(0.0);
		instance0.setYear(1);
		instance0.setInverterEfficiency(1.0);
		instance0.setDailyHours(1.0);
		instance0.setReplacementGen(0.0);
		instance0.setAnnualSolarGen();
		assertTrue(instance0.getExportGen()==2.0);
		assertTrue(instance0.getAnnualSolarGen()==730.0);
	}

	@Test
	public void testGetAnnualSolarGen() {
		assertTrue(instance0.getAnnualSolarGen()==null);
	}

	@Test
	public void testSetAnnualSave() {
		instance0.setDailyHours(1.0);
		instance0.setReplacementGen(365.0);
		instance0.setAnnualTariffInc(0.0);
		instance0.setYear(1);
		instance0.setImportTariff(1.0);
		instance0.setExportGen(1.0);
		instance0.setFeedInFee(1.0);
		instance0.setAnnualSave();
		assertTrue(instance0.getAnnualSave()==730.00);
	}

	@Test
	public void testGetAnnualSave() {
		assertTrue(instance0.getAnnualSave()==null);
	}

	@Test
	public void testGetPaybackTime() {
		assertTrue(instance0.getPaybackTime()==1.0);
	}

	@Test
	public void testGetImportTariff() {
		assertTrue(instance0.getImportTariff()==0.19);
	}

	@Test
	public void testSetImportTariff() {
		instance0.setAnnualTariffInc(0.0);
		instance0.setYear(1);
		instance0.setImportTariff(1.0);
		assertTrue(instance0.getImportTariff()==1.0);
	}

	@Test
	public void testGetFeedInFee() {
		assertTrue(instance0.getFeedInFee()==0.3);
	}

	@Test
	public void testSetFeedInFee() {
		instance0.setFeedInFee(1.0);
		assertTrue(instance0.getFeedInFee()==1.0);
	}
}
