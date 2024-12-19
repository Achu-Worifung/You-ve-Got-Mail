package application;

import java.io.IOException;
import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {

	
	@FXML
	private PasswordField password;
	@FXML
	private TextField email;
	@FXML
	private DatePicker to;
	@FXML
	private DatePicker from;
	
	static Scene mainScene;
	
	private String pass,user_mail;
	LocalDate date_to;
	LocalDate date_from;
	
	//for switching
	private Stage stage;
	private Scene scene;
	private Parent root;
	

	



	public void onClick(ActionEvent event) throws IOException
	{
		//getting all data
		pass = password.getText();
		user_mail = email.getText();
		date_to = to.getValue();
		date_from = from.getValue();
//		pass = password.getText();
//		user_mail = email.getText();
//		date_from = from.getValue();
//		date_to = to.getValue();
		//checking for blanks in the form
		if(pass == "" || user_mail == "" || date_to == null || date_from == null)
		
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete Mail");
			alert.setHeaderText("WARNING No blanks allowed");
			alert.setContentText("All Inputs must be filled in");
			return;
		}
		//final warning before email deletion
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Mail");
		alert.setHeaderText("WARNING THIS CANNOT BE UNDONE");
		alert.setContentText("YOU ARE ABOUT TO DELETE YOUR EMAILS RECIEVED BETWEEN THE DATES " + date_from + " AND "+ date_to);
		if(alert.showAndWait().get() == ButtonType.OK)
		{
		try {
			DeleteMail mail = new DeleteMail(date_to, date_from , user_mail, pass);
			mail.deleateMyMail();
		}catch(Exception e)
		{
			System.out.println("we've got an error");
		}
			
			System.out.println("he confirmed");
		}

		
		
	
	
	}

	public void instructions(ActionEvent event) throws IOException
	{
		
		where("instruction", event);
		System.out.println("to instruction");
	}
	public void Delete(ActionEvent event) throws IOException
	{
		where("delete", event);
		System.out.println("to delete");
	}
	public void Back(ActionEvent event) throws IOException
	{
		where("back", event);
	}
	public void where(String whereto, ActionEvent event) throws IOException
	{
		root = null;
		if(whereto.equals("delete")) {
		root = FXMLLoader.load(getClass().getResource("UserIden.fxml"));
		}else if(whereto.equals("instruction"))
		{
			root = FXMLLoader.load(getClass().getResource("Instruction.fxml"));
		} else 
		{
			root = FXMLLoader.load(getClass().getResource("Start.fxml"));
		}
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}


	
}
