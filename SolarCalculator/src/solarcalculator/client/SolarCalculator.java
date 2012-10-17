package solarcalculator.client;

import java.text.DecimalFormat;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
import com.google.gwt.user.client.ui.Widget;

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
	private UseEnergy usedEnergy;
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel bodyPanel = new HorizontalPanel();
	// Banner 
	// ====================================================================
	private HorizontalPanel Banner = new HorizontalPanel();
	public void BuildBanner(){
		Banner.setSize("1000px", "100px");
		Banner.setStyleName("banner");
	}
	// Output Panel
	// ====================================================================
	private VerticalPanel outputPanel = new VerticalPanel();
	private Label outputPorjectName = new Label("Current Project:");
	private TextBox outputNameTextBox = new TextBox();
	private HorizontalPanel outputPriceHorPanel = new HorizontalPanel();
	private Label outputPriceTitle = new Label("Indicative Price: $");
	private TextBox outputPriceTextBox = new TextBox();
	private HorizontalPanel outputAnuOutHorPanel = new HorizontalPanel();
	private Label outputAnuOutTitle = new Label("Annual output:");
	private TextBox outputAnuOutTextBox = new TextBox();
	private HorizontalPanel outputYearVHorPanel = new HorizontalPanel();
	private Label outputYearVTitle = new Label("Yearly Value: ");
	private TextBox outputYearVTextBox = new TextBox();
	private HorizontalPanel outputPBHorPanel = new HorizontalPanel();
	private Label outputPBTitle = new Label("Payback Time: ");
	private TextBox outputPBTextBox = new TextBox();
	private Button calculateButton = new Button("Calculate");
	
	public void buildOutputPanel(){
		// assemble widgets in the calculate panel
		calculateButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int selected = projectSelectionListBox.getSelectedIndex();
				switch(selected){
				case 1:
					
				break;
				case 2:
					
				break;
				case 3:
					project = new Project();
					project.setSystemPower(Double.parseDouble(SystemPowerTB.getText()));
					project.setIndicativePrice(Double.parseDouble(outputPriceTextBox.getText()));
					project.setIndexTariff(Double.parseDouble(importTariffTextBox.getText()));
					project.setDayTimeUsage(Double.parseDouble(energyUseYearTotalBox.getText()));
				}
				
				String indicativePrice =  project.parseNumberFormat(project.getIndicativePrice());
				String annualOutput = project.parseNumberFormat(project.getAnnualSolarGen());
				String yearlyValue = project.parseNumberFormat(project.getAnnualSave());
				String paybackTime = project.parseNumberFormat(project.getPaybackTime());
				
				outputPriceTextBox.setText(indicativePrice);
				outputAnuOutTextBox.setText(annualOutput);
				outputYearVTextBox.setText(yearlyValue);
				outputPBTextBox.setText(paybackTime);
				
				buildROITable();
			}
		});
		outputPriceHorPanel.add(outputPriceTitle);
		outputPriceHorPanel.add(outputPriceTextBox);
		
		outputAnuOutHorPanel.add(outputAnuOutTitle);
		outputAnuOutHorPanel.add(outputAnuOutTextBox);
		
		outputYearVHorPanel.add(outputYearVTitle);
		outputYearVHorPanel.add(outputYearVTextBox);
		
		outputPBHorPanel.add(outputPBTitle);
		outputPBHorPanel.add(outputPBTextBox);
		
		outputPanel.add(outputPorjectName);
		outputPanel.add(outputNameTextBox);
		outputPanel.add(outputPriceHorPanel);
		outputPanel.add(outputAnuOutHorPanel);
		outputPanel.add(outputYearVHorPanel);
		outputPanel.add(outputPBHorPanel);
		outputPanel.add(calculateButton);
		outputNameTextBox.setWidth("200px");
		outputPanel.addStyleName("outputPanel");
		outputPorjectName.addStyleName("TitleLabel");
	}
	
	// project tab
	// ====================================================================
	private VerticalPanel projectVerTab = new VerticalPanel();
	private Label projectTitleLabel = new Label("Select Project");
	private HorizontalPanel projectSelectionHorPanel = new HorizontalPanel();
	private Label projectSelectionTitleLabel = new Label("Example Projects:");
	private ListBox projectSelectionListBox = new ListBox();
	private HorizontalPanel projectLocatonHorPanel = new HorizontalPanel();
	private Label projectLocationTitleLabel = new Label("Location:");
	private ListBox projectLocationListBox = new ListBox();
	private HorizontalPanel systemPowerHorPanel = new HorizontalPanel();
	private Label SystemPower = new Label("System Power:");
	private TextBox SystemPowerTB = new TextBox();
	private HorizontalPanel dailyHorPanel = new HorizontalPanel();
	private Label DailyHr = new Label("Daily Hour:");
	private TextBox DailyHrTB = new TextBox();



	private DisclosurePanel projectNotesDisPanel = new DisclosurePanel(
			"Click Here For Notes.");
	private HTML projectNoteLabel = new HTML(
			"The entry fields above show the "+ 
			"current project name and gives you a list of other projects you can select. You can "+
			"change the project name at any time to create a new project." + 
			"Pressing the save button will store your project permanently with " +
			"RenSMART. You will be able to load the project again when you revisit the " +
			"site, and it will be listed on your RenSMART member home page");

	private TabPanel inputTabPanel = new TabPanel();
	
	public void BuildProjectTab(){
		
		projectSelectionListBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				int selected = projectSelectionListBox.getSelectedIndex();
				switch(selected){
				case 1:
					project=new Project(4.5, 10000.00);
				break;
				case 2:
					project=new Project(5.1, 18000.00);
				break;
				case 3:
					project = new Project(0.0,0.0);
				break;
				}
				SystemPowerTB.setText(project.getSystemPower().toString());
				outputNameTextBox.setText(projectSelectionListBox.getItemText(selected));
				outputAnuOutTextBox.setText("0.0");
				outputPBTextBox.setText("0.0");
				outputPriceTextBox.setText("0.0");
				outputYearVTextBox.setText("0.0");
			}
		});

		projectLocationListBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				switch(projectLocationListBox.getSelectedIndex()){
				case 0:
					project.setDailyHours(5.0);
					break;
				case 1:
					project.setDailyHours(6.0);
					break;
				case 2:
					project.setDailyHours(9.0);
					break;
				case 3:
					project.setDailyHours(4.0);
					break;
				case 4:
					project.setDailyHours(6.5);
					break;
				case 5:
					project.setDailyHours(5.5);
					break;
				case 6:
					project.setDailyHours(3.0);
					break;
				case 7:
					project.setDailyHours(6.0);
					break;
				}
				
				DailyHrTB.setText(project.getDailyHours().toString());
			}
		});
		
		
		// assemble DisclosurePanel for notes
		projectNotesDisPanel.add(projectNoteLabel);
		
		// assemble widgets in project tab of input tab panel
		projectSelectionListBox.addItem("Please select your system model", "0");
		projectSelectionListBox.addItem("Solar System Model-4.5kW", "1");
		projectSelectionListBox.addItem("Solar System Model-5.1kW", "2");
		projectSelectionListBox.addItem("Customize");
		projectSelectionListBox.setVisibleItemCount(0);
		
		projectLocationListBox.addItem("NSW","0");
		projectLocationListBox.addItem("ACT","1");
		projectLocationListBox.addItem("NT","2");
		projectLocationListBox.addItem("QLD","3");
		projectLocationListBox.addItem("SA","4");
		projectLocationListBox.addItem("TAS","5");
		projectLocationListBox.addItem("VIC","6");
		projectLocationListBox.addItem("WA","7");
		projectLocationListBox.setVisibleItemCount(0);
		
		projectSelectionHorPanel.add(projectSelectionTitleLabel);
		projectSelectionHorPanel.add(projectSelectionListBox);
		projectLocatonHorPanel.add(projectLocationTitleLabel);
		projectLocatonHorPanel.add(projectLocationListBox);
		
		systemPowerHorPanel.add(SystemPower);
		systemPowerHorPanel.add(SystemPowerTB);
		dailyHorPanel.add(DailyHr);
		dailyHorPanel.add(DailyHrTB);

		projectVerTab.add(projectTitleLabel);
		projectVerTab.add(projectSelectionHorPanel);
		projectVerTab.add(projectLocatonHorPanel);
		projectVerTab.add(systemPowerHorPanel);
		projectVerTab.add(dailyHorPanel);
		projectVerTab.add(projectNotesDisPanel);
		projectTitleLabel.addStyleName("TitleLabel");
	}
	// energy use tab
	// ====================================================================
	// Energy Use
	private UseEnergy energyUseCalcu = new UseEnergy();
	// -------------------------------------------------------------------
	// Result
	private Label energyUseResultTitleLabel = new Label("Energy Use");
	private HorizontalPanel energyUseYearTotalPanel = new HorizontalPanel();
	private Label energyUseYearTotalLabel = new Label("Used Yearly:");
	private TextBox energyUseYearTotalBox = new TextBox();
	private Label energyUseYearTotalUnit = new Label(" kWh/year	");
	// Input
	private Label energyUseCalcTitleSubLabel = new Label("Energy Use Calculator");
	private HorizontalPanel energyUseCalcInputPanel = new HorizontalPanel();
	private ListBox energyUseCalcInputListBox = new ListBox();
	private TextBox energyUseCalcInputTextBox = new TextBox();
	private Label energyUseCalcInputUnit = new Label("kWh");

	// Average Rate
	// Calculator-------------------------------------------------------------------
	// Rate Result
	private Label importTariffTitleLabel = new Label("Step 2: Tariff");
	private HorizontalPanel importTariffHorPanel = new HorizontalPanel();
	private Label importTariffLabel = new Label("Import Tariff:");
	private TextBox importTariffTextBox = new TextBox();
	private Label importTariffUnit = new Label("p/kWh");
	private HorizontalPanel tariffIncrRateHorPanel = new HorizontalPanel();
	private Label tariffIncrRateLabel = new Label("Annual bill inflation:");
	private TextBox tariffIncrRateTextBox = new TextBox();
	private Label tariffIncrRateUnit = new Label("%");
	private HorizontalPanel feedInTariffHorPanel = new HorizontalPanel();
	private Label feedInTariffLabel = new Label("Feed In Tariff:");
	private TextBox feedInTariffTextBox = new TextBox();
	private Label feedInTariffUnit = new Label("p/kWh");
	
	public void BuildEnergyUseTab(){
		
		// assemble widgets in energy use tab of input tab
		// panel=============================================
		// Energy Use ------------------------------------------------
		// Input panel
		energyUseCalcInputTextBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				final String userInput;
				userInput = energyUseCalcInputTextBox.getText();
				energyUseCalcu.setEnergyUse(userInput);
				energyUseCalcu.setYearlyEnergyUse(energyUseCalcInputListBox.getSelectedIndex());
				energyUseYearTotalBox.setText(energyUseCalcu.getYearlyEnergyUse().toString());
				project.setDayTimeUsage(Double.parseDouble(energyUseYearTotalBox.getText()));
			}
		});
		
		energyUseCalcInputListBox.addItem("Daily Energy Use", "1");
		energyUseCalcInputListBox.addItem("Monthly Energy Use", "2");
		energyUseCalcInputListBox.addItem("Quarterly Energy Use", "3");
		
		energyUseCalcInputListBox.getSelectedIndex();

		energyUseCalcInputPanel.add(energyUseCalcInputListBox);
		energyUseCalcInputPanel.add(energyUseCalcInputTextBox);
		energyUseCalcInputPanel.add(energyUseCalcInputUnit);
		energyUseCalcInputUnit.addStyleName("unitLabel");

		// Result panel
		energyUseYearTotalPanel.add(energyUseYearTotalLabel);
		energyUseYearTotalPanel.add(energyUseYearTotalBox);
		energyUseYearTotalPanel.add(energyUseYearTotalUnit);
		importTariffUnit.addStyleName("unitLabel");
		
		// add style name
		energyUseResultTitleLabel.addStyleName("TitleLabel");
		energyUseCalcTitleSubLabel.addStyleName("TitleLabel");
		// End Energy Use


		// Import tariff Panel
		importTariffHorPanel.add(importTariffLabel);
		importTariffHorPanel.add(importTariffTextBox);
		importTariffHorPanel.add(importTariffUnit);
		importTariffHorPanel.addStyleName("unitLabel");
		
		tariffIncrRateHorPanel.add(tariffIncrRateLabel);
		tariffIncrRateHorPanel.add(tariffIncrRateTextBox);
		tariffIncrRateHorPanel.add(tariffIncrRateUnit);
		tariffIncrRateUnit.addStyleName("unitLabel");
		
		//feed in panel
		feedInTariffHorPanel.add(feedInTariffLabel);
		feedInTariffHorPanel.add(feedInTariffTextBox);
		feedInTariffHorPanel.add(feedInTariffUnit);
		feedInTariffHorPanel.addStyleName("unitLabel");
		// add style name
		importTariffTitleLabel.addStyleName("TitleLabel");

		// End Average Rate Calculator
		// Use vertical panel ------------------------------------------------
		projectVerTab.add(energyUseCalcTitleSubLabel);
		projectVerTab.add(energyUseCalcInputPanel);
		projectVerTab.add(energyUseYearTotalPanel);
		projectVerTab.add(importTariffTitleLabel);
		projectVerTab.add(tariffIncrRateHorPanel);
		projectVerTab.add(importTariffHorPanel);
		projectVerTab.add(feedInTariffHorPanel);
		

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
		ROITable.setText(0, 3, "Export Value");
		ROITable.setText(0, 4, "Used Value");
		ROITable.setText(0, 5, "Annual Generate");
		ROITable.setText(0, 6, "Year Total");
		ROITable.setText(0, 7, "Accumulated Total");
		ROITable.setText(0, 8, "ROI");
		ROITable.getRowFormatter().addStyleName(0, "ROITableHeader");
		ROITable.setWidth("650px");
		
		ROINote.add(ROINoteHTML);
		ROITabVerPanel.add(ROITitleLabel);
		ROITitleLabel.addStyleName("TitleLabel");
		ROITabVerPanel.add(ROIDescriptionHTML);
		ROIDescriptionHTML.addStyleName("Description");
		ROITabVerPanel.add(ROITable);
		ROITabVerPanel.add(ROINote);
	}

	public void buildROITable(){
		Double cumSaving = 0.0;
		Double ROI = 0.0;
		for(int i = 1; i < 15; i++){
			
			project.setYear(i);
			project.setTariffFee();
			project.getAnnualSave();
			cumSaving = cumSaving + project.getAnnualSave();
			ROI = (cumSaving/project.getIndicativePrice()- 1)*100;
			project.parseNumberFormat(project.getTariffFee());
			
			ROITable.setText(i, 0, project.getYear().toString());
			ROITable.setText(i, 1, project.parseNumberFormat(project.getTariffFee()));
			ROITable.setText(i, 2, project.parseNumberFormat(project.getFeedInFee()));
			ROITable.setText(i, 3, project.parseNumberFormat(project.getExportValue()));
			ROITable.setText(i, 4, project.parseNumberFormat(project.getUsedValue()));
			ROITable.setText(i, 5, project.parseNumberFormat(project.getAnnualSolarGen()));
			ROITable.setText(i, 6, project.parseNumberFormat(project.getAnnualSave()));
			ROITable.setText(i, 7, project.parseNumberFormat(cumSaving));
			ROITable.setText(i, 8, project.parseNumberFormat(ROI)+ "%");
			ROITable.getRowFormatter().addStyleName(i, "ROITableCell");
		}
	}
	
	// Location
	// ====================================================================
	private  VerticalPanel locationVerTab = new VerticalPanel();
	
	// footer
	// ====================================================================
	private HorizontalPanel footer = new HorizontalPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		BuildBanner();
		BuildProjectTab();
		BuildEnergyUseTab();
		BuildROITab();
		buildOutputPanel();
		
		inputTabPanel.add(projectVerTab, "Calculator");
		inputTabPanel.selectTab(0);
		inputTabPanel.add(ROITabVerPanel, "Return On Inversment");

		
		// assemble main panel
		mainPanel.add(Banner);
		bodyPanel.add(inputTabPanel);
		bodyPanel.add(outputPanel);
		mainPanel.add(bodyPanel);
		
		mainPanel.addStyleName("main");
		inputTabPanel.setWidth("700px");

		// associate the main panel with html host page
		RootPanel.get("calculator").add(mainPanel);

	}
}
