/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Babysitting;
import entity.Enfant;
import Service.EmployeService;
import Service.ServiceBabysitting;
import Service.ServiceEnfant;
import util.Connexion;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HEDI MSELMI
 */
public class ConsulteAdminBabysiterController implements Initializable {
ObservableList<Babysitting> oblist= FXCollections.observableArrayList();
ObservableList<String> listE;
    @FXML
    private TableColumn<Babysitting, String> id;
    @FXML
    private TableColumn<Babysitting, String> heuredebut;
    @FXML
    private TableColumn<Babysitting, String> heurefin;
    @FXML
    private TableColumn<Babysitting, String> joursemaine;
    @FXML
    private TableColumn<Babysitting, String> prixheure;
    @FXML
    private TableColumn<Babysitting, String> enfant;
    @FXML
    private TableView<Babysitting> table;
    @FXML
    private JFXTimePicker heuredebut2;
    @FXML
    private JFXTimePicker heurefin2;
    @FXML
    private CheckBox lundi2;
    @FXML
    private CheckBox mardi2;
    @FXML
    private CheckBox mercredi2;
    @FXML
    private CheckBox jeudi2;
    @FXML
    private CheckBox vendredi2;
    @FXML
    private JFXTextField prix2;
    @FXML
    private ComboBox enfant2;
    @FXML
    private TextField id2;
    @FXML
    private TextField filterField;
    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private TitledPane cantine;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    @FXML
    private HBox evenements;
    @FXML
    private HBox Activite;
    @FXML
    private HBox SignOut;

    /**
     * Initializes the controller class.
     * @throws java.sql.SQLException
     */
    
    
    public ConsulteAdminBabysiterController() throws SQLException {
          
        
        ServiceEnfant k= new ServiceEnfant();
        this.listE= FXCollections.observableArrayList(k.display());
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                enfant2.setItems(listE);
         Connection con = Connexion.getInstance().getCnx();
        try {
           // System.out.println("****"+table.getItems().get(7));
            ResultSet rs=con.createStatement().executeQuery("select * from babysitter");
            while(rs.next()){
                oblist.add(new Babysitting(rs.getInt("idBabysitter"), rs.getInt("heureDebut")/3600, rs.getInt("heureFin")/3600, rs.getString("jourSemaine"), rs.getInt("prixHeure"), new Enfant(rs.getInt("id_enfant"))));
            }
        } catch (SQLException ex) {
            
        }
         id.setCellValueFactory(new PropertyValueFactory<>("idBabysitter"));
         heuredebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
         heurefin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
         joursemaine.setCellValueFactory(new PropertyValueFactory<>("jourSemaine"));
         prixheure.setCellValueFactory(new PropertyValueFactory<>("prixHeure"));
         enfant.setCellValueFactory(new PropertyValueFactory<>("id_enfant"));
         
         
         table.setItems(oblist);
         
         
         FilteredList<Babysitting> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate( babysitting -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(babysitting.getJourSemaine()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				
				     else  
				    	 return false; // Does not match.
			});
		});
                
            SortedList<Babysitting> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
        ObservableList<Babysitting> data = table.getItems();
        ServiceBabysitting s=new ServiceBabysitting();
          String t="";
        if(lundi2.isSelected())
                { t+="lundi";}
        if(mardi2.isSelected())
        {t+=" mardi";}
        if(mercredi2.isSelected())
        {t+=" mercredi";}
        if(jeudi2.isSelected())
        {t+=" jeudi";}
        if(vendredi2.isSelected())
        {t+=" vendredi";}
       
       EmployeService q=new EmployeService();
        ServiceEnfant z=new ServiceEnfant();
       
        Babysitting  p;
        p = new Babysitting(q.rechercheParNom( id2.getText()), heuredebut2.getValue().getHour()*3600, heurefin2.getValue().getHour()*3600, t, Integer.parseInt(prix2.getText()), new Enfant(z.AfficheParNom((String) enfant2.getValue())));
        s.update(p, table.getSelectionModel().getSelectedItem().getIdBabysitter());;
           
         id.setCellValueFactory(new PropertyValueFactory<>("idBabysitter"));
         heuredebut.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
         heurefin.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
         joursemaine.setCellValueFactory(new PropertyValueFactory<>("jourSemaine"));
         prixheure.setCellValueFactory(new PropertyValueFactory<>("prixHeure"));
         enfant.setCellValueFactory(new PropertyValueFactory<>("id_enfant"));
         
         ObservableList<Babysitting> k = FXCollections.observableArrayList(s.readAll());
          table.setItems(FXCollections.observableArrayList(k));
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         // String req="DELETE FROM  babysitter where idBabysitter = ?";
       final ObservableList<Babysitting> data = table.getItems();
        ServiceBabysitting s=new ServiceBabysitting();
       s.delete(table.getSelectionModel().getSelectedItem().getIdBabysitter());
//         data.removeAll(table.getSelectionModel().getSelectedItem());
         table.setItems(FXCollections.observableArrayList(s.readAll()));
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         AnchorPane pane=FXMLLoader.load(getClass().getResource("AjoutBabysitter.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
        
    }

    @FXML
    private void gestionParents(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionParents.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEmploye(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionEmploye.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void Evenement(MouseEvent event) throws IOException {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("Evenements.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    /**/

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Activite(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Actprog.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void CantineOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void CantineOn(MouseEvent event) {
        //info.setText("Gerer les chaises,tables et menus");
    }

    @FXML
    private void ParentOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ParentOn(MouseEvent event) {
        //info.setText("Gerer les parents");
    }

    @FXML
    private void EmployeOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EmployeOn(MouseEvent event) {
        //info.setText("Gerer les employes");
    }

    @FXML
    private void EnfantOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EnfantOn(MouseEvent event) {
        //info.setText("Gerer les enfants");
    }

    @FXML
    private void EvenementOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EvenementOn(MouseEvent event) {
        //info.setText("Gerer les evenements");
    }

    @FXML
    private void ActiviteOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ActiviteOn(MouseEvent event) {
        //info.setText("Gerer les activites");
    }

    @FXML
    private void Chaise(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceGestionDesChaise.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Table(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceGestionTable.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Menu(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAjouterMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    
    
}
