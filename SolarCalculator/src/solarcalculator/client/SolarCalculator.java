package solarcalculator.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.BarChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

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
	private Label warnning = new Label("* please fill this.");
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel bodyPanel = new HorizontalPanel();
	// Banner 
	// ====================================================================
	private Label Banner = new Label();
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
	
	// Build Out Put Panel ====================================================
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
					if(projectLocationListBox.getSelectedIndex() == 0){
						Window.alert("Please select a 'Location'");
						return;
					}else if(SystemPowerLB.getSelectedIndex() == 0){
						Window.alert("Please select a 'System Power'");
						return;
					}else if(DailyHrTB.getText().isEmpty()){
						Window.alert("Please input a ¡®Location¡¯");
						return;
					}else if(energyUseCalcInputTextBox.getText().isEmpty()){
						Window.alert("Please input your 'used ennergy'");
						return;
					}else if(importTariffTextBox.getText().isEmpty()){
						Window.alert("Please input a 'import tariff'");
						return;
					}else if(tariffIncrRateTextBox.getText().isEmpty()){
						Window.alert("Please input a 'tariff increase rate'");
						return;
					}else if(feedInTariffTextBox.getText().isEmpty()){
						Window.alert("Please input a 'feed in tariff'");
						return;
					}else{
						project.setReplacementGen(Double.parseDouble(energyUseYearTotalBox.getText()));
						project.setImportTariff(Double.parseDouble(importTariffTextBox.getText()));
						project.setAnnualTariffInc(Double.parseDouble(tariffIncrRateTextBox.getText())/100);
						project.setFeedInFee(Double.parseDouble(feedInTariffTextBox.getText()));
						project.setImportTariff(Double.parseDouble(importTariffTextBox.getText()));
					}
					break;
				}
				project.setAnnualSolarGen();
				project.setAnnualSave();
				project.setPaybackTime();
				
				String indicativePrice =  project.parseNumberFormat(project.getIndicativePrice());
				String annualOutput = project.parseNumberFormat(project.getAnnualSolarGen());
				String yearlyValue = project.parseNumberFormat(project.getAnnualSave());
				String paybackTime = project.getPaybackTime().toString();
				
				outputPriceTextBox.setText(indicativePrice);
				outputAnuOutTextBox.setText(annualOutput);
				outputYearVTextBox.setText(yearlyValue);
				outputPBTextBox.setText(paybackTime);
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
	private Label projectTitleLabel = new Label("Step1: Select Project");
	private HorizontalPanel projectSelectionHorPanel = new HorizontalPanel();
	private Label projectSelectionTitleLabel = new Label("Project");
	private ListBox projectSelectionListBox = new ListBox();
	private HorizontalPanel projectLocatonHorPanel = new HorizontalPanel();
	private Label projectLocationTitleLabel = new Label("Location:");
	private ListBox projectLocationListBox = new ListBox();
	private HorizontalPanel systemPowerHorPanel = new HorizontalPanel();
	private Label SystemPower = new Label("System Power:");
	private ListBox SystemPowerLB = new ListBox();
	private HorizontalPanel dailyHorPanel = new HorizontalPanel();
	private Label DailyHr = new Label("Daily Hour:");
	private TextBox DailyHrTB = new TextBox();

	private TabPanel inputTabPanel = new TabPanel();
	
	// Build Project Tab ====================================================================
	public void BuildProjectTab(){
		// 
		projectSelectionListBox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				int selected = projectSelectionListBox.getSelectedIndex();
				switch(selected){
				case 1:
					project=new Project(4.0, 10000.00);
					usedEnergy = new UseEnergy();
					SystemPowerLB.setSelectedIndex(1);
					usedEnergy.setEnergyUse("0.24");
					usedEnergy.setYearlyEnergyUse(0);
					projectLocationListBox.setSelectedIndex(1);
					project.setDailyHours(5.0);
					DailyHrTB.setText(project.getDailyHours().toString());
					energyUseCalcInputTextBox.setText(usedEnergy.getEnergyUse().toString());
					energyUseYearTotalBox.setText(usedEnergy.getYearlyEnergyUse().toString());
					importTariffTextBox.setText(project.getImportTariff().toString());
					feedInTariffTextBox.setText(project.getFeedInFee().toString());
					Double incri = project.getAnnualTariffInc()*100;
					tariffIncrRateTextBox.setText(incri.toString());
				break;
				case 2:
					project=new Project(5.0, 12000.00);
					usedEnergy = new UseEnergy();
					SystemPowerLB.setSelectedIndex(2);
					usedEnergy.setEnergyUse("0.24");
					usedEnergy.setYearlyEnergyUse(0);
					projectLocationListBox.setSelectedIndex(1);
					project.setDailyHours(5.0);
					DailyHrTB.setText(project.getDailyHours().toString());
					energyUseCalcInputTextBox.setText(usedEnergy.getEnergyUse().toString());
					energyUseYearTotalBox.setText(usedEnergy.getYearlyEnergyUse().toString());
					importTariffTextBox.setText(project.getImportTariff().toString());
					feedInTariffTextBox.setText(project.getFeedInFee().toString());
					Double incri2 = project.getAnnualTariffInc()*100;
					tariffIncrRateTextBox.setText(incri2.toString());
				break;
				case 3:
					
					project = new Project(0.0,0.0);
					projectLocationListBox.setSelectedIndex(0);
					SystemPowerLB.setSelectedIndex(0);
					DailyHrTB.setText(null);
					energyUseCalcInputTextBox.setText(null);
					energyUseYearTotalBox.setText(null);
					importTariffTextBox.setText(null);
					feedInTariffTextBox.setText(null);
					tariffIncrRateTextBox.setText(null);
				break;
				}
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
					project.setDailyHours(null);
				case 1:
					project.setDailyHours(5.0);
					break;
				case 2:
					project.setDailyHours(6.0);
					break;
				case 3:
					project.setDailyHours(9.0);
					break;
				case 4:
					project.setDailyHours(4.0);
					break;
				case 5:
					project.setDailyHours(6.5);
					break;
				case 6:
					project.setDailyHours(5.5);
					break;
				case 7:
					project.setDailyHours(3.0);
					break;
				case 8:
					project.setDailyHours(6.0);
					break;
				}
				
				DailyHrTB.setText(project.getDailyHours().toString());
			}
		});
		
		SystemPowerLB.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				switch(SystemPowerLB.getSelectedIndex()){
				case 0:
					project.setSystemPower(null);
					project.setIndicativePrice(null);
				case 1:
					project.setSystemPower(3.0);
					project.setIndicativePrice(8000.0);
					break;
				case 2:
					project.setSystemPower(4.0);
					project.setIndicativePrice(10000.0);
					break;
				case 3:
					project.setSystemPower(5.0);
					project.setIndicativePrice(12000.0);
					break;
				case 4:
					project.setSystemPower(7.0);
					project.setIndicativePrice(14000.0);
					break;
				case 5:
					project.setSystemPower(9.0);
					project.setIndicativePrice(16000.0);
					break;
				case 6:
					project.setSystemPower(12.0);
					project.setIndicativePrice(18000.0);
					break;
				case 7:
					project.setSystemPower(18.0);
					project.setIndicativePrice(20000.0);
					break;
				}
			}
		});
		
		
		// assemble widgets in project tab of input tab panel
		projectSelectionListBox.addItem("Please select your system model", "0");
		projectSelectionListBox.addItem("Solar System Model-4.5kW", "1");
		projectSelectionListBox.addItem("Solar System Model-5.1kW", "2");
		projectSelectionListBox.addItem("Customize");
		projectSelectionListBox.setVisibleItemCount(0);
		
		projectLocationListBox.addItem("Location", "0");
		projectLocationListBox.addItem("NSW","1");
		projectLocationListBox.addItem("ACT","2");
		projectLocationListBox.addItem("NT","3");
		projectLocationListBox.addItem("QLD","4");
		projectLocationListBox.addItem("SA","5");
		projectLocationListBox.addItem("TAS","6");
		projectLocationListBox.addItem("VIC","7");
		projectLocationListBox.addItem("WA","8");
		projectLocationListBox.setVisibleItemCount(0);
		
		SystemPowerLB.addItem("System Power", "0");
		SystemPowerLB.addItem("3.0","1");
		SystemPowerLB.addItem("4.0","2");
		SystemPowerLB.addItem("5.0","3");
		SystemPowerLB.addItem("7.0","4");
		SystemPowerLB.addItem("9.0","5");
		SystemPowerLB.addItem("12.0","6");
		SystemPowerLB.addItem("18.0","7");
		projectLocationListBox.setVisibleItemCount(0);
		
		
		projectSelectionHorPanel.add(projectSelectionTitleLabel);
		projectSelectionHorPanel.add(projectSelectionListBox);
		projectLocatonHorPanel.add(projectLocationTitleLabel);
		projectLocatonHorPanel.add(projectLocationListBox);
		
		systemPowerHorPanel.add(SystemPower);
		systemPowerHorPanel.add(SystemPowerLB);
		dailyHorPanel.add(DailyHr);
		dailyHorPanel.add(DailyHrTB);

		projectVerTab.add(projectTitleLabel);
		projectVerTab.add(projectSelectionHorPanel);
		projectVerTab.add(projectLocatonHorPanel);
		projectVerTab.add(systemPowerHorPanel);
		projectVerTab.add(dailyHorPanel);
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
				project.setReplacementGen(Double.parseDouble(energyUseYearTotalBox.getText()));
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
		projectVerTab.add(importTariffTitleLabel);
		projectVerTab.add(tariffIncrRateHorPanel);
		projectVerTab.add(importTariffHorPanel);
		projectVerTab.add(feedInTariffHorPanel);
		projectVerTab.add(energyUseCalcTitleSubLabel);
		projectVerTab.add(energyUseCalcInputPanel);
		projectVerTab.add(energyUseYearTotalPanel);
		

		energyUseYearTotalUnit.addStyleName("unitLabel");	

	}
	
	// Return On Investment
	// ====================================================================
	private VerticalPanel ROITabVerPanel = new VerticalPanel();
	private Label ROITitleLabel = new Label("The Financial Projection table gives year-by-year " +
			"estimates of the income and costs for your project");
	private HorizontalPanel optionHorPanel = new HorizontalPanel();
	private Label optionTitle = new Label("Select a View");
	private ListBox viewOption = new ListBox();
	private FlexTable ROITable = new FlexTable();
	
	public void BuildROITab(){
		viewOption.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if(viewOption.getSelectedIndex() <= 3){
					buildChart();
				}
				else if (viewOption.getSelectedIndex() == 4){
					buildROITable();
				}
				
			}
		});
		viewOption.addItem("View Annual Generate","0");
		viewOption.addItem("View Annual Total","1");
		viewOption.addItem("View Accumulated Total","2");
		viewOption.addItem("View Return On Inversment","3");
		viewOption.addItem("Overview Table","4");
		viewOption.setSelectedIndex(0);
		
		optionHorPanel.add(optionTitle);
		optionHorPanel.add(viewOption);
	
		ROITabVerPanel.add(optionHorPanel);
		ROITabVerPanel.add(ROITable);
		ROITitleLabel.addStyleName("TitleLabel");
	}
	
	private ColumnChart columnChart;

	public void buildChart(){
        Window.enableScrolling(false);
        Window.setMargin("0px");

		 ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		 chartLoader.loadApi(new Runnable() {
		        @Override
		        public void run() {
		                // call method to show chart
		        	ROITabVerPanel.add(getColumnChart());
		        	drawColumnChart();
		        }
		 });

	}

	
    private Widget getColumnChart() {
        if (columnChart == null) {
        	columnChart = new ColumnChart();
        }
        return columnChart;
    }
   
    private void drawColumnChart() {
    	Double annuSaving;
		Double cumSaving = 0.0;
		Double ROI = 0.0;
		Double impTafTemp = project.getImportTariff();
        // Prepare the data
        DataTable dataTable = DataTable.create();
        int years;
        project.setYear(10);
        years = project.getYear();
		dataTable.addColumn(ColumnType.NUMBER, "Year");
		switch(viewOption.getSelectedIndex()){
			case 0:
				dataTable.addColumn(ColumnType.NUMBER, "Annual Generation");
				break;
			case 1:
				dataTable.addColumn(ColumnType.NUMBER, "Annual Saving");
				break;
			case 2:
				dataTable.addColumn(ColumnType.NUMBER, "culmulative Saving");
				break;
			case 3:
				dataTable.addColumn(ColumnType.NUMBER, "culmulative Saving");
				break;
			case 4:
				break;
				
			
		}
		
		dataTable.addRows(years);
		project.setYear(0);
		ColumnChartOptions options = ColumnChartOptions.create();
		
		for (int col = 0; col < years; col++) {
			project.setYear(col);
			project.setImportTariff(project.getImportTariff());
			project.setAnnualSolarGen();
			project.setAnnualSave();
			annuSaving = project.getAnnualSave();
			cumSaving = cumSaving + annuSaving;
			ROI = (cumSaving/project.getIndicativePrice()- 1)*100;
			switch(viewOption.getSelectedIndex()){
				case 0:
					dataTable.setValue(col, 1, project.getAnnualSolarGen());
					options.setTitle("Annual Solar Generation");
					options.setVAxis(VAxis.create("KW"));
					break;
				case 1: 
					dataTable.setValue(col, 1,  annuSaving);
					options.setTitle("Annual Saving");
					options.setVAxis(VAxis.create("$"));
					break;
				case 2: 
					dataTable.setValue(col, 1, cumSaving);
					options.setTitle("Culmulative Saving");
					options.setVAxis(VAxis.create("$"));
					break;
				case 3: 
					dataTable.setValue(col, 1, ROI);
					options.setTitle("Return On Inversment");
					options.setVAxis(VAxis.create("%"));
					break;
				case 4:
					break;
			}
		}
		for (int i = 0; i < years; i++) {
			dataTable.setValue(i, 0, i);
		}
	

        // Draw the chart
		
		options.setFontName("Tahoma");
		
		options.setHAxis(HAxis.create("Years"));
		
        columnChart.draw(dataTable,options);
        columnChart.setTitle("calculatorasdf");
        project.setYear(0);
        project.setImportTariff(impTafTemp);
    }

	public void buildROITable(){
		ROITable.setText(0, 0, "Year");
		ROITable.setText(1, 0, "Annual Generate");
		ROITable.setText(2, 0, "Annual Total");
		ROITable.setText(3, 0, "Accumulated Total");
		ROITable.setText(4, 0, "ROI");
		ROITable.getColumnFormatter().addStyleName(0, "ROITableHeader");
		ROITable.setWidth("690px");
		ROITable.setBorderWidth(1);
		
		Double annuSaving;
		Double cumSaving = 0.0;
		Double ROI = 0.0;
		Double impTafTemp = project.getImportTariff();
		for(int i = 1; i < 10; i++){
			
			project.setYear(i);
			project.setImportTariff(project.getImportTariff());
			project.setAnnualSolarGen();
			project.setAnnualSave();
			annuSaving = project.getAnnualSave();
			cumSaving = cumSaving + annuSaving;
			ROI = (cumSaving/project.getIndicativePrice()- 1)*100;
			
			ROITable.setText(0, i, project.getYear().toString());
			ROITable.setText(1, i, project.parseNumberFormat(project.getAnnualSolarGen()));
			ROITable.setText(2, i, project.parseNumberFormat(annuSaving));
			ROITable.setText(3, i, project.parseNumberFormat(cumSaving));
			ROITable.setText(4, i, project.parseNumberFormat(ROI)+ "%");
			ROITable.getRowFormatter().addStyleName(i, "ROITableCell");
		}
		project.setYear(0);
		project.setImportTariff(impTafTemp);
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
