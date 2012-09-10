package solarcalculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Presentation on Monday 4: 35 room O412
 * 
 * content : see black board. template on black board.
 * 
 * 
 * Automata build tool. Maven or Hudson.
 */
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SolarCalculator implements EntryPoint {

	private Project project;
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel calculatePanel = new HorizontalPanel();
	private Button calculateButton = new Button("Calculate");
	private String calculateStatus = "Avaliabe";
	private Label statusLabel = new Label("Calculate status: "
			+ calculateStatus);

	private Label resultsLabel = new Label(
			"Indicative Price: $0.0, Annual output: 0.0kW, Yearly Value: $0.0, Payback Time: 0.0Years");

	// project tab
	// ====================================================================
	private VerticalPanel projectTab = new VerticalPanel();
	private Label projectTitleLabel = new Label(
			"You can select one of the RenSMART Example "
					+ "projects to get you started from the drop down list below.");
	private HorizontalPanel projectSelectionHorPanel = new HorizontalPanel();
	private Label projectSelectionTitleLabel = new Label("Example Projects:");
	private ListBox projectListBox = new ListBox();
	private Button projectLoadButton = new Button("Load");

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
		// assemble widgets in the calculate panel
		calculatePanel.add(calculateButton);
		calculateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				project=new Project();
				Double indicativePrice = project.getIndicativePrice();
				Double annualOutput = project.getAnnualSolarGen();
				Double yearlyValue = project.getAnnualSave();
				Double paybackTime = project.getPaybackTime();
				resultsLabel.setText("Indicative Price: $" + indicativePrice
						+ ", Annual output: " + annualOutput
						+ "kW, Yearly Value: $" + yearlyValue
						+ ", Payback Time: " + paybackTime + "Years");
			}
		});
		calculatePanel.add(statusLabel);

		// assemble widgets in project tab of input tab panel
		projectListBox.addItem("project 1", "1");
		projectListBox.addItem("project 2", "2");
		projectListBox.setVisibleItemCount(1);
		projectSelectionHorPanel.add(projectSelectionTitleLabel);
		projectSelectionHorPanel.add(projectListBox);
		projectSelectionHorPanel.add(projectLoadButton);
		projectTab.add(projectTitleLabel);
		projectTab.add(projectSelectionHorPanel);

		projectTitleLabel.addStyleName("TitleLabel");
		inputTabPanel.add(projectTab, "1. Your Project");
		inputTabPanel.selectTab(0);

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
		supplyInfoResultPanel.add(supplyInforResultTitleLabel);
		supplyInfoResultPanel.add(supplyInfoResultOutputPanel);
		supplyInfoResultPanel.add(supplyInfoResultButton);
		// Input Panel
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel1);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstTextBox2);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel3);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstTextBox4);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel5);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel1);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondTextBox2);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel3);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondTextBox4);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel5);
		supplyInfoInputPanel.add(supplyInfoInputTitle);
		supplyInfoInputPanel.add(supplyInfoInputDescription);
		supplyInfoInputPanel.add(supplyInfoInputFirstPanel);
		supplyInfoInputPanel.add(supplyInfoInputSecondPanel);
		supplyInfoInputPanel.add(supplyInfoInputButtion);
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

		inputTabPanel.add(energyUseVerTab, "2. Energy Use");
		// End energy use tab =============================================

		// assemble DisclosurePanel for notes
		notesDisPanel.add(noteLabel);

		// assemble main panel
		mainPanel.add(calculatePanel);
		mainPanel.add(resultsLabel);
		mainPanel.add(inputTabPanel);
		mainPanel.add(notesDisPanel);
		mainPanel.addStyleName("main");
		inputTabPanel.setWidth("1000px");

		// associate the main panel with html host page
		RootPanel.get("calculator").add(mainPanel);

	}
}
