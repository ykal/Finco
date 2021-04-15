package framework;

import banking.BankController;
import banking.persistance.BankAccFile;
import framework.View;
import framework.controllers.CommandManager;
import framework.controllers.Controller;
import framework.models.Data;
import framework.observer.Observer;
import framework.persistence.ACCFile;
import framework.persistence.CUSFile;
import framework.persistence.REPFile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FinCo implements Observer {

	Data model;
	View view;
	Controller controller;
	CommandManager commandManager;
	REPFile repFile;
	ACCFile accFile;
	CUSFile cusFile;

	public FinCo(String title, Data dataModel) {
		commandManager = new CommandManager();
		repFile = new REPFile();
		cusFile = new CUSFile();
		this.model = dataModel;
		populateModelColumns(model);
		view = new View(title, model);
		addOperationButtons(view, dataModel);
		if( accFile != null)accFile.attach(this);
	}

	public Controller getController() {
		return controller;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public REPFile getRepFile() {
		return repFile;
	}

	public ACCFile getAccFile() {
		return accFile;
	}

	public CUSFile getCusFile() {
		return cusFile;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	public void setRepFile(REPFile repFile) {
		this.repFile = repFile;
	}

	public void setAccFile(ACCFile accFile) {
		this.accFile = accFile;
		this.accFile.attach(this);
	}

	public void setCusFile(CUSFile cusFile) {
		this.cusFile = cusFile;
	}

	public void populateModelColumns(Data model) {
		model.addColumn("AccountNr");
		model.addColumn("Email");
	}

	static public void main(String args[]){
		(new FinCo("FinCo Application", new Data())).view.start();
	}

	public View getView() {
		return  this.view;
	}

	public Data getModel() {
		return this.model;
	}

	public void setModel(Data model) {
		this.model = model;
	}

	private void addOperationButtons(View view, DefaultTableModel model) {
		// Todo:: add default buttons
	}

	@Override
	public void update(Data data) {
		System.out.println("update is called \n" + data.toString());
		this.model = data;
		this.view.setTableModel(data);
	}
}

