package solarcalculator.client;

import java.text.DecimalFormat;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * Presentation on Monday 4: 35 room O412
 * 
 * need change:
 * project tab: SolarSystem mode
 * EnergyUse: layout need redesign
 * 
 * document:
 * short bullet point
 * digram
 * 
 * send email: number of upload vision and branch!!!
 * 
 * 
 */
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class SolarCalculator implements EntryPoint {
	
	private Project project;
	private VerticalPanel mainPanel = new VerticalPanel();
	// Banner 
	// ====================================================================
	private HorizontalPanel Banner = new HorizontalPanel();
	private Label BannerTitle = new Label("Solar Calculater");
	public void BuildBanner(){
		Banner.setSize("1000px", "100px");
		Banner.setStyleName("banner");
	}
	// project tab
	// ====================================================================
	private VerticalPanel projectVerTab = new VerticalPanel();

	private Label projectTitleLabel = new Label(
			"You can select one of the RenSMART Example "
					+ "projects to get you started from the drop down list below.");
	private HorizontalPanel projectSelectionHorPanel = new HorizontalPanel();
	private Label projectSelectionTitleLabel = new Label("Example Projects:");
	private ListBox projectSelectionListBox = new ListBox();
	private Button calculateButton = new Button("Calculate");
	private HorizontalPanel projectPriceHorPanel = new HorizontalPanel();
	private Label projectPriceTitle = new Label("Indicative Price: $");
	private TextBox projectPriceTextBox = new TextBox();
	private HorizontalPanel projectAnuOutHorPanel = new HorizontalPanel();
	private Label projectAnuOutTitle = new Label("Annual output:");
	private TextBox projectAnuOutTextBox = new TextBox();
	private HorizontalPanel projectYearVHorPanel = new HorizontalPanel();
	private Label projectYearVTitle = new Label("Yearly Value: ");
	private TextBox projectYearVTextBox = new TextBox();
	private HorizontalPanel projectPBHorPanel = new HorizontalPanel();
	private Label projectPBTitle = new Label("Payback Time: ");
	private TextBox projectPBTextBox = new TextBox();
	
	public void BuildProjectTab(){
		// assemble widgets in the calculate panel
		calculateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				project=new Project(5.1, 12000.00);
				String indicativePrice =  project.parseNumberFormat(project.getIndicativePrice());
				String annualOutput = project.parseNumberFormat(project.getAnnualSolarGen());
				String yearlyValue = project.parseNumberFormat(project.getAnnualSave());
				String paybackTime = project.parseNumberFormat(project.getPaybackTime());
				
				projectPriceTextBox.setText(indicativePrice);
				projectAnuOutTextBox.setText(annualOutput);
				projectYearVTextBox.setText(yearlyValue);
				projectPBTextBox.setText(paybackTime);
			}
		});
		
		
		// assemble widgets in project tab of input tab panel
		projectSelectionListBox.addItem("project 1", "1");
		projectSelectionListBox.addItem("project 2", "2");
		projectSelectionListBox.setVisibleItemCount(1);
		
		projectSelectionHorPanel.add(projectSelectionTitleLabel);
		projectSelectionHorPanel.add(projectSelectionListBox);
		projectSelectionHorPanel.add(calculateButton);
		
		projectPriceHorPanel.add(projectPriceTitle);
		projectPriceHorPanel.add(projectPriceTextBox);
		
		projectAnuOutHorPanel.add(projectAnuOutTitle);
		projectAnuOutHorPanel.add(projectAnuOutTextBox);
		
		projectYearVHorPanel.add(projectYearVTitle);
		projectYearVHorPanel.add(projectYearVTextBox);
		
		projectPBHorPanel.add(projectPBTitle);
		projectPBHorPanel.add(projectPBTextBox);
		
		projectVerTab.add(projectTitleLabel);
		projectVerTab.add(projectSelectionHorPanel);
		projectVerTab.add(projectPriceHorPanel);
		projectVerTab.add(projectAnuOutHorPanel);
		projectVerTab.add(projectYearVHorPanel);
		projectVerTab.add(projectPBHorPanel);
		
		projectTitleLabel.addStyleName("TitleLabel");
	}
	// energy use tab
	// ====================================================================
	private VerticalPanel energyUseVerTab = new VerticalPanel();
	// Title
	private Label EnergyUseTitle = new Label(
			"Enter the amount of electricity you use and how much"
					+ " you pay for it in the form below.");
	// Description
	private Label EnergyUseDescription = new Label(
			"We will use this information to estimate how much "
					+ "electricity you use on a typical day for each month of the year. This is then used "
					+ "to estimate how much renewable energy will be imported and exported from your chosen "
					+ "renewable energy source.");
	// Energy Use
	private UseEnergy energyUseCalcu = new UseEnergy();
	// -------------------------------------------------------------------
	private HorizontalPanel energyUseHorPanel = new HorizontalPanel();
	// Result
	private VerticalPanel energyUseResultPanel = new VerticalPanel();
	private Label energyUseResultTitleLabel = new Label("Energy Use");
	private HorizontalPanel energyUseProfileHorPanel = new HorizontalPanel();
	private Label energyUseProfileLabel = new Label("Energy Use Profile");
	private ListBox energyUseProfileListBox = new ListBox();
	private HorizontalPanel energyUseYearTotalPanel = new HorizontalPanel();
	private Label energyUseYearTotalLabel = new Label("Used Yearly");
	private TextBox energyUseYearTotalBox = new TextBox();
	private Label energyUseYearTotalUnit = new Label(" kWh/year	");
	// Input
	private VerticalPanel energyUseCalcSubPanel = new VerticalPanel();
	private Label energyUseCalcTitleSubLabel = new Label(
			"Energy Use Calculator");
	private Label energyUseCalcDecripLabel = new Label(
			"Use this calculator to calculate yearly "
					+ "electricity use if you have the amount used over a different period.");
	private HorizontalPanel energyUseCalcInputPanel = new HorizontalPanel();
	private ListBox energyUseCalcInputListBox = new ListBox();
	private TextBox energyUseCalcInputTextBox = new TextBox();
	private Label energyUseCalcInputUnit = new Label("kWh");
	private Button energyUseCalcButtion = new Button("Calculate Yearly Use");

	// Average Rate
	// Calculator-------------------------------------------------------------------
	private HorizontalPanel supplyInfoHorPanel = new HorizontalPanel();
	// Rate Result
	private VerticalPanel supplyInfoResultPanel = new VerticalPanel();
	private Label supplyInforResultTitleLabel = new Label("Supply Information");
	private HorizontalPanel supplyInfoResultOutputPanel = new HorizontalPanel();
	private Label supplyInfoResultOutputLabel = new Label("Import Tariff");
	private TextBox supplyInfoResultOutputTextBox = new TextBox();
	private Label supplyInfoResultOutputUnit = new Label("p/kWh");
	private Button supplyInfoResultButton = new Button("Recalculate Now");
	// Rate Input
	private VerticalPanel supplyInfoInputPanel = new VerticalPanel();
	private Label supplyInfoInputTitle = new Label("Average Rate Calculator");
	private Label supplyInfoInputDescription = new Label(
			"Use this calculator if your electricity"
					+ " bill gives two prices for electricity. We will calculate an average price per ");
	private HorizontalPanel supplyInfoInputFirstPanel = new HorizontalPanel();
	private Label supplyInfoInputFirstLabel1 = new Label("First Rate");
	private TextBox supplyInfoInputFirstTextBox2 = new TextBox();
	private Label supplyInfoInputFirstLabel3 = new Label("kWh	at ");
	private TextBox supplyInfoInputFirstTextBox4 = new TextBox();
	private Label supplyInfoInputFirstLabel5 = new Label("p");
	private HorizontalPanel supplyInfoInputSecondPanel = new HorizontalPanel();
	private Label supplyInfoInputSecondLabel1 = new Label("Second Rate");
	private TextBox supplyInfoInputSecondTextBox2 = new TextBox();
	private Label supplyInfoInputSecondLabel3 = new Label("kWh	at ");
	private TextBox supplyInfoInputSecondTextBox4 = new TextBox();
	private Label supplyInfoInputSecondLabel5 = new Label("p");
	private Button supplyInfoInputButtion = new Button(
			"Calculate Import Tariff");
	
	public void BuildEnergyUseTab(){
		// assemble widgets in energy use tab of input tab
		// panel=============================================
		// Energy Use ------------------------------------------------
		// Result panel
		energyUseProfileListBox.addItem("Domestic Home", "1");
		energyUseProfileListBox.addItem("Comercial Office", "2");
		
		energyUseProfileHorPanel.add(energyUseProfileLabel);
		energyUseProfileHorPanel.add(energyUseProfileListBox);
		energyUseYearTotalPanel.add(energyUseYearTotalLabel);
		energyUseYearTotalPanel.add(energyUseYearTotalBox);
		energyUseYearTotalPanel.add(energyUseYearTotalUnit);
		supplyInfoResultOutputUnit.addStyleName("unitLabel");
		energyUseResultPanel.add(energyUseResultTitleLabel);
		energyUseResultPanel.add(energyUseProfileHorPanel);
		energyUseResultPanel.add(energyUseYearTotalPanel);
		// Input panel
		energyUseCalcInputListBox.addItem("Daily Energy Use", "1");
		energyUseCalcInputListBox.addItem("Monthly Energy Use", "2");
		energyUseCalcInputListBox.addItem("Quarterly Energy Use", "3");
		
		energyUseCalcInputListBox.getSelectedIndex();

		energyUseCalcInputPanel.add(energyUseCalcInputListBox);
		energyUseCalcInputPanel.add(energyUseCalcInputTextBox);
		energyUseCalcInputPanel.add(energyUseCalcInputUnit);
		energyUseCalcInputUnit.addStyleName("unitLabel");
		energyUseCalcSubPanel.add(energyUseCalcTitleSubLabel);
		energyUseCalcSubPanel.add(energyUseCalcDecripLabel);
		energyUseCalcSubPanel.add(energyUseCalcInputPanel);
		energyUseCalcSubPanel.add(energyUseCalcButtion);
		
		energyUseCalcButtion.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				final String userInput;
				userInput = energyUseCalcInputTextBox.getText();
				energyUseCalcu.setEnergyUse(userInput);
				energyUseCalcu.setYearlyEnergyUse(energyUseCalcInputListBox.getSelectedIndex());
				energyUseYearTotalBox.setText(energyUseCalcu.getYearlyEnergyUse().toString());
			}
		});
		
		// Energy Use
		energyUseHorPanel.add(energyUseResultPanel);
		energyUseHorPanel.add(energyUseCalcSubPanel);
		// add style name
		energyUseResultTitleLabel.addStyleName("TitleLabel");
		energyUseCalcTitleSubLabel.addStyleName("TitleLabel");
		energyUseResultPanel.addStyleName("EnergyUseHorRes");
		energyUseCalcSubPanel.addStyleName("EnergyUseHor");
		// End Energy Use

		// Average Rate Calculator
		// ------------------------------------------------
		// Output Panel
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputLabel);
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputTextBox);
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputUnit);
		supplyInfoResultOutputUnit.addStyleName("unitLabel");
		supplyInfoResultPanel.add(supplyInforResultTitleLabel);
		supplyInfoResultPanel.add(supplyInfoResultOutputPanel);
		supplyInfoResultPanel.add(supplyInfoResultButton);
		// Input Panel
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel1);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstTextBox2);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel3);
		supplyInfoInputFirstLabel3.addStyleName("unitLabel");
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstTextBox4);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel5);
		supplyInfoInputFirstLabel5.addStyleName("unitLabel");
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel1);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondTextBox2);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel3);
		supplyInfoInputSecondLabel3.addStyleName("unitLabel");
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondTextBox4);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel5);
		supplyInfoInputSecondLabel5.addStyleName("unitLabel");
		supplyInfoInputPanel.add(supplyInfoInputTitle);
		supplyInfoInputPanel.add(supplyInfoInputDescription);
		supplyInfoInputPanel.add(supplyInfoInputFirstPanel);
		supplyInfoInputPanel.add(supplyInfoInputSecondPanel);
		supplyInfoInputPanel.add(supplyInfoInputButtion);
		
		supplyInfoInputButtion.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				energyUseCalcu.setPrice1(supplyInfoInputFirstTextBox4.getText());
				energyUseCalcu.setPrice2(supplyInfoInputSecondTextBox4.getText());
				energyUseCalcu.setRate1(supplyInfoInputFirstTextBox2.getText());
				energyUseCalcu.setRate2(supplyInfoInputSecondTextBox2.getText());
				energyUseCalcu.setAvgRate();
				supplyInfoResultOutputTextBox.setText(energyUseCalcu.getAvgRate().toString());
			}
		});
		// Average Rate Calculator
		supplyInfoHorPanel.add(supplyInfoResultPanel);
		supplyInfoHorPanel.add(supplyInfoInputPanel);
		// add style name
		supplyInforResultTitleLabel.addStyleName("TitleLabel");
		supplyInfoInputTitle.addStyleName("TitleLabel");
		supplyInfoResultPanel.addStyleName("EnergyUseHorRes");
		supplyInfoInputPanel.addStyleName("EnergyUseHor");

		// End Average Rate Calculator
		// Use vertical panel ------------------------------------------------
		energyUseVerTab.add(EnergyUseTitle);
		energyUseVerTab.add(EnergyUseDescription);
		energyUseVerTab.add(energyUseHorPanel);
		energyUseVerTab.add(supplyInfoHorPanel);

		EnergyUseTitle.addStyleName("TitleLabel");
		EnergyUseDescription.addStyleName("Description");
		energyUseCalcDecripLabel.addStyleName("Description");
		supplyInfoInputDescription.addStyleName("Description");
		energyUseYearTotalUnit.addStyleName("unitLabel");	
	}
	
	// Return On Investment
	// ====================================================================
	private VerticalPanel ROITabVerPanel = new VerticalPanel();
	private Label ROITitleLabel = new Label("The Financial Projection table gives year-by-year " +
			"estimates of the income and costs for your project");
	private HTML ROIDescriptionHTML = new HTML("All values in brackets are negative.</br>"+
			"No increase based on inflation have been applied except in the case of" +
			" import and export tariffs which may increase above the rate of inflation.</br>"+
			"As no increase based on inflation has been included, the estimates should " +
			"be taken as the value of money in today's terms.</br>"+
			"Further information on each of the columns can be found by pressing the " +
			"notes button at the bottom of the table.", true);
	private FlexTable ROITable = new FlexTable();
	private DisclosurePanel ROINote = new DisclosurePanel("Click Here For Notes.");
	private HTML ROINoteHTML = new HTML("Year - The financial year covered by this row with the project year in brackets." +
			" Project years run from April to April. Import Tariff - The cost of importing a kWh of energy from the grid " +
			"in pence.</br> This is the value you have entered under the Energy Use tab and is increased each project year " +
			"by the Tariff Increase % above inflation value that you will find under the Advanced Fields also under the " +
			"Energy Use tab.</br>Export Tariff - The value of exporting a kWh of energy to the grid in pence. This value can " +
			"be changed under the Advanced Fields on the Energy Use tab. Like the import tariff, this value is increased" +
			" each project year by the Tariff Increase % above inflation value that you will find under the Advanced" +
			" Fields also under the Energy Use tab. As part of the Clean Energy Cash Back scheme, the minimum value of" +
			" an exported kWh is 3p. It is not currently clear whether this value will be increased in line with energy " +
			"prices or the retail prices index.</br> Solar FIT Tariff - The amount given by your electricity supplier for " +
			"each kWh you generate from your proposed solar PV installation.</br>Wind FIT Tariff - The amount given by your " +
			"electricity supplier for each kWh you generate from your proposed wind turbine </br> Maintenance Cost - The " +
			"estimated cost of maintaining your system in running order each year. </br> Import Cost - The estimated " +
			"total cost of the energy used at your location </br> Export Value - The estimated total value of the energy " +
			"you export from your property to the grid </br> Used Value - The value of the energy you have generated that" +
			" you use at your location </br> Wind FIT Value - The total value of the feed-in tariff you would receive for " +
			"energy generated from the wind </br> Solar FIT Value - The total value of the feed-in tariff you would receive " +
			"for energy generated from the sun </br> Site Rental Cost - The yearly cost of renting the site where your " +
			"generation system is located. </br> Year Total - The total estimated income derived from your location over " +
			"the year </br> Accumulated Total - The accumulated income derived from your location since the system was" +
			" installed </br> ROI - The percentage return to you after the initial outlay has been subtracted", true);
	
	public void BuildROITab(){
		ROITable.setText(0, 0, "Year");
		ROITable.setText(0, 1, "Import Tariff");
		ROITable.setText(0, 2, "Export Tariff");
		ROITable.setText(0, 3, "Solar FIT Tariff");
		ROITable.setText(0, 4, "Import Cost");
		ROITable.setText(0, 5, "Export Value");
		ROITable.setText(0, 6, "Used Value");
		ROITable.setText(0, 7, "Solar FIT Value");
		ROITable.setText(0, 8, "Year Total");
		ROITable.setText(0, 9, "Accumulated Total");
		ROITable.setText(0, 10, "ROI");
		ROITable.getRowFormatter().addStyleName(0, "ROITableHeader");
		ROITable.setStyleName("ROITable");
		
		ROINote.add(ROINoteHTML);
		ROITabVerPanel.add(ROITitleLabel);
		ROITitleLabel.addStyleName("TitleLabel");
		ROITabVerPanel.add(ROIDescriptionHTML);
		ROIDescriptionHTML.addStyleName("Description");
		ROITabVerPanel.add(ROITable);
		ROITabVerPanel.add(ROINote);
	}

	// Disclosure
	// ====================================================================
	private DisclosurePanel notesDisPanel = new DisclosurePanel(
			"Click Here For Notes.");
	private Label noteLabel = new Label(
			"The entry fields above show the "
					+ "current project name and gives you a list of other projects you can select. You can "
					+ "change the project name at any time to create a new project."
					+ "Pressing the save button will store your project permanently with "
					+ "RenSMART. You will be able to load the project again when you revisit the "
					+ "site, and it will be listed on your RenSMART member home page");

	private TabPanel inputTabPanel = new TabPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		BuildBanner();
		BuildProjectTab();
		BuildEnergyUseTab();
		BuildROITab();

		inputTabPanel.add(projectVerTab, "Your Project");
		inputTabPanel.selectTab(0);
		inputTabPanel.add(energyUseVerTab, "Energy Use");
		inputTabPanel.add(ROITabVerPanel, "ROI");
		// assemble DisclosurePanel for notes
		notesDisPanel.add(noteLabel);
		
		// assemble main panel
		mainPanel.add(Banner);
		mainPanel.add(inputTabPanel);
		mainPanel.add(notesDisPanel);
		mainPanel.addStyleName("main");
		inputTabPanel.setWidth("1000px");

		// associate the main panel with html host page
		RootPanel.get("calculator").add(mainPanel);

	}
}
