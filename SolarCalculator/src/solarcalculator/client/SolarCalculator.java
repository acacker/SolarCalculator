package solarcalculator.client;

import solarcalculator.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SolarCalculator implements EntryPoint {

	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel calculatePanel = new HorizontalPanel();
	private Button calculateButton = new Button("Calculate");
	private String calculateStatus = "Avaliabe";
	private Label statusLabel = new Label("Calculate status: "
			+ calculateStatus);
	private Double indicativePrice = 0.0;
	private Double annualOutput = 0.0;
	private Double yearlyValue = 0.0;
	private Double paybackTime = 0.0;
	private Label resultsLabel = new Label("Indicative Price: $"
			+ indicativePrice + ", Annual output: " + annualOutput
			+ "kW, Yearly Value: $" + yearlyValue + ", Payback Time: "
			+ paybackTime + "Years");
	//project tab ====================================================================
	private HorizontalPanel projectTab = new HorizontalPanel();
	private ListBox projectListBox = new ListBox();
	private Button loadButton = new Button("Load");
	
	//energy use tab ====================================================================
	private VerticalPanel energyUseVerTab = new VerticalPanel();
		// Title
	private Label EnergyUseTitle = new Label("Enter the amount of electricity you use and how much" +
			" you pay for it in the form below.");
		// Description
	private Label EnergyUseDescription = new Label("We will use this information to estimate how much " +
			"electricity you use on a typical day for each month of the year. This is then used " +
			"to estimate how much renewable energy will be imported and exported from your chosen " +
			"renewable energy source.");
		// Energy Use -------------------------------------------------------------------
	private HorizontalPanel energyUseHorPanel = new HorizontalPanel();
			// Result
	private VerticalPanel energyUseResultPanel = new VerticalPanel();
	private Label energyUseResultTitleLabel = new Label("Energy Use");
	private HorizontalPanel energyUseProfileHorPanel=new HorizontalPanel();
	private Label energyUseProfileLabel= new Label("Energy Use Profile");
	private ListBox energyUseProfileListBox=new ListBox();
	private HorizontalPanel energyUseYearTotalPanel = new HorizontalPanel();
	private Label energyUseYearTotalLabel = new Label("Used Yearly");
	private TextBox energyUseYearTotalBox = new TextBox();
	private Label energyUseYearTotalUnit = new Label(" kWh/year	");
			// Input
	private VerticalPanel energyUseCalcSubPanel= new VerticalPanel();
	private Label energyUseCalcTitleSubLabel=new Label("Energy Use Calculator");
	private Label energyUseCalcDecripLabel = new Label("Use this calculator to calculate yearly " +
			"electricity use if you have the amount used over a different period.");
	private HorizontalPanel energyUseCalcInputPanel= new HorizontalPanel();
	private ListBox energyUseCalcInputListBox = new ListBox();
	private TextBox energyUseCalcInputTextBox = new TextBox();
	private Label energyUseCalcInputUnit = new Label("kWh");
	private Button energyUseCalcButtion = new Button("Calculate Yearly Use");
	
		// Average Rate Calculator-------------------------------------------------------------------
	private HorizontalPanel supplyInfoHorPanel = new HorizontalPanel();
			// Rate Result
	private VerticalPanel supplyInfoResultPanel = new VerticalPanel();
	private Label supplyInforResultTitleLabel = new Label();
	private HorizontalPanel supplyInfoResultOutputPanel = new HorizontalPanel();
	private Label supplyInfoResultOutputLabel = new Label("Import Tariff");
	private TextBox supplyInfoResultOutputTextBox = new TextBox();
	private Label supplyInfoResultOutputUnit = new Label("p/kWh");
	private Button supplyInfoResultButton = new Button("Recalculate Now");
			// Rate Input
	private VerticalPanel supplyInfoInputPanel = new VerticalPanel();
	private Label supplyInfoInputTitle = new Label("Average Rate Calculator");
	private Label supplyInfoInputDescription = new Label("Use this calculator if your electricity" +
			" bill gives two prices for electricity. We will calculate an average price per ");
	private HorizontalPanel supplyInfoInputFirstPanel = new HorizontalPanel();
	private Label supplyInfoInputFirstLabel1 = new Label("First Rate");
	private TextBox supplyInfoInputFirstLabel2 = new TextBox();
	private Label supplyInfoInputFirstLabel3 = new Label("kWh	at ");
	private TextBox supplyInfoInputFirstLabel4 = new TextBox();
	private Label supplyInfoInputFirstLabel5 = new Label("p");
	private HorizontalPanel supplyInfoInputSecondPanel = new HorizontalPanel();
	private Label supplyInfoInputSecondLabel1 = new Label("First Rate");
	private TextBox supplyInfoInputSecondLabel2 = new TextBox();
	private Label supplyInfoInputSecondLabel3 = new Label("kWh	at ");
	private TextBox supplyInfoInputSecondLabel4 = new TextBox();
	private Label supplyInfoInputSecondLabel5 = new Label("p");	
	private Button supplyInfoInputButtion = new Button("Calculate Import Tariff");
	
	//Disclosure ====================================================================
	private DisclosurePanel notesDisPanel=new DisclosurePanel("Click for notes");
	private Label noteLabel=new Label("edit note here!");

	private TabPanel inputTabPanel = new TabPanel();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// assemble widgets in the calculate panel
		calculatePanel.add(calculateButton);
		calculatePanel.add(statusLabel);

		// assemble widgets in project tab of input tab panel
		projectListBox.addItem("project 1", "1");
		projectListBox.addItem("project 2", "2");
		projectListBox.setVisibleItemCount(1);
		projectTab.add(projectListBox);
		projectTab.add(loadButton);
		inputTabPanel.add(projectTab, "1. Your Project");
		inputTabPanel.selectTab(0);
		
		//assemble widgets in energy use tab of input tab panel=============================================
			//Energy Use ------------------------------------------------
				// Result panel
		energyUseProfileListBox.addItem("Domestic Home","1");
		energyUseProfileListBox.addItem("Comercial Office","2");
		
		energyUseProfileHorPanel.add(energyUseProfileLabel);
		energyUseProfileHorPanel.add(energyUseProfileListBox);
		energyUseYearTotalPanel.add(energyUseYearTotalLabel);
		energyUseYearTotalPanel.add(energyUseYearTotalBox);
		energyUseYearTotalPanel.add(energyUseYearTotalUnit);
		energyUseResultPanel.add(energyUseResultTitleLabel);
		energyUseResultPanel.add(energyUseProfileHorPanel);
		energyUseResultPanel.add(energyUseYearTotalPanel);
				// Input panel
		energyUseCalcInputListBox.addItem("Daily Energy Use","1");
		energyUseCalcInputListBox.addItem("Monthly Energy Use","2");
		energyUseCalcInputListBox.addItem("Quarterly Energy Use","3");
		
		energyUseCalcInputPanel.add(energyUseCalcInputListBox);
		energyUseCalcInputPanel.add(energyUseCalcInputTextBox);
		energyUseCalcInputPanel.add(energyUseCalcInputUnit);
		energyUseCalcSubPanel.add(energyUseCalcTitleSubLabel);
		energyUseCalcSubPanel.add(energyUseCalcDecripLabel);
		energyUseCalcSubPanel.add(energyUseCalcInputPanel);
		energyUseCalcSubPanel.add(energyUseCalcButtion);
			// Energy Use
		energyUseHorPanel.add(energyUseResultPanel);
		energyUseHorPanel.add(energyUseCalcSubPanel);
			// End Energy Use 
		
			// Average Rate Calculator ------------------------------------------------
				// Output Panel
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputLabel);
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputTextBox);
		supplyInfoResultOutputPanel.add(supplyInfoResultOutputUnit);
		supplyInfoResultPanel.add(supplyInforResultTitleLabel);
		supplyInfoResultPanel.add(supplyInfoResultOutputPanel );
		supplyInfoResultPanel.add(supplyInfoResultButton);	
				// Input Panel
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel1);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel2);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel3);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel4);
		supplyInfoInputFirstPanel.add(supplyInfoInputFirstLabel5);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel1);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel2);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel3);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel4);
		supplyInfoInputSecondPanel.add(supplyInfoInputSecondLabel5);
		supplyInfoInputPanel.add(supplyInfoInputTitle);
		supplyInfoInputPanel.add(supplyInfoInputDescription);
		supplyInfoInputPanel.add(supplyInfoInputFirstPanel);
		supplyInfoInputPanel.add(supplyInfoInputSecondPanel);
		supplyInfoInputPanel.add(supplyInfoInputButtion);
			// Average Rate Calculator 
		supplyInfoHorPanel.add(supplyInfoResultPanel);
		supplyInfoHorPanel.add(supplyInfoInputPanel);
			// End Average Rate Calculator
			// Use vertical panel  ------------------------------------------------
		energyUseVerTab.add(EnergyUseTitle);
		energyUseVerTab.add(EnergyUseDescription);
		energyUseVerTab.add(energyUseHorPanel); 
		energyUseVerTab.add(supplyInfoHorPanel);
		
		inputTabPanel.add(energyUseVerTab, "2. Energy Use");	
		// End energy use tab =============================================
		// TODO assemble other tabs

		// assemble DisclosurePanel for notes
		notesDisPanel.add(noteLabel);

		// assemble main panel
		mainPanel.add(calculatePanel);
		mainPanel.add(resultsLabel);
		mainPanel.add(inputTabPanel);
		mainPanel.add(notesDisPanel);

		// associate the main panel with html host page
		RootPanel.get("calculator").add(mainPanel);

	}
}
