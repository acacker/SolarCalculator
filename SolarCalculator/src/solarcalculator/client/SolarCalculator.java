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
	//project tab
	private HorizontalPanel projectTab = new HorizontalPanel();
	private ListBox projectListBox = new ListBox();
	private Button loadButton = new Button("Load");
	//energy use tab
	private VerticalPanel energyUseVerTab = new VerticalPanel();
	private HorizontalPanel energyUseHorPanel = new HorizontalPanel();
	private VerticalPanel energyUseSubPanel = new VerticalPanel();
	private Label energyUseTitleLabel = new Label("Energy Use");
	private HorizontalPanel energyUseProfileHorPanel=new HorizontalPanel();
	private Label energyUseProfileLabel= new Label("Energy USe Profile");
	private ListBox energyUseProfileListBox=new ListBox();
	private VerticalPanel energyUseCalcSubPanel= new VerticalPanel();
	private Label energyUseCalcTitleSubLabel=new Label("Energy Use Calculator");
	private HorizontalPanel supplyInfoHorPanel = new HorizontalPanel();
	//Disclosure
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
		
		//assemble widgets in energy use tab of input tab panel
		energyUseSubPanel.add(energyUseTitleLabel);
		energyUseHorPanel.add(energyUseSubPanel);
		energyUseCalcSubPanel.add(energyUseCalcTitleSubLabel);
		energyUseHorPanel.add(energyUseCalcSubPanel);
		energyUseVerTab.add(energyUseHorPanel);
		energyUseVerTab.add(supplyInfoHorPanel);
		inputTabPanel.add(energyUseVerTab, "2. Energy Use");

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
