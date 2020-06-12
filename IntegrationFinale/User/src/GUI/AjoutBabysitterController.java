/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.EmployeService;
import entity.Babysitting;
import entity.Enfant;
import Service.ParentService;
import Service.ServiceBabysitting;
import Service.ServiceEnfant;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author HEDI MSELMI
 */
public class AjoutBabysitterController implements Initializable {

    
    ObservableList<Integer> listd=FXCollections.observableArrayList(20,21,22);
    ObservableList<Integer> listf=FXCollections.observableArrayList(4,5,6);
    ObservableList<String> listU;
    ObservableList<String> listE;
    ObservableList<String> listS=FXCollections.observableArrayList("lundi","mardi","mercredi","jeudi","vendredi");
    @FXML
    private Label idB;
    private ComboBox joursemaine;
    @FXML
    private TextField prixheure;
    @FXML
    private ComboBox id;
    @FXML
    private ComboBox enfant;
    @FXML
    private JFXTimePicker heurefin;
    @FXML
    private JFXTimePicker heuredebut;
    @FXML
    private CheckBox lundi;
    @FXML
    private CheckBox mardi;
    @FXML
    private CheckBox mercredi;
    @FXML
    private CheckBox jeudi;
    @FXML
    private CheckBox vendredi;
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
    
    
    public AjoutBabysitterController() throws SQLException {
        System.out.println("okok");
          
        EmployeService t= new EmployeService();
        this.listU= FXCollections.observableArrayList(t.listParNom());
        ServiceEnfant k= new ServiceEnfant();
        this.listE= FXCollections.observableArrayList(k.display());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  heuredebut.setItems(listd);
      //  heurefin.setItems(listf);
        id.setItems(listU);
        enfant.setItems(listE);
//        joursemaine.setItems(listS);
    }    

    @FXML
    private void ajout(ActionEvent event) throws SQLException, IOException {
        ServiceBabysitting S= new ServiceBabysitting();
        EmployeService k=new EmployeService();
        ServiceEnfant z=new ServiceEnfant();
//        AdminService t=new AdminService();
/*p;*/
        String s="";
        if(lundi.isSelected())
                { s+="lundi";}
        if(mardi.isSelected())
        {s+=" mardi";}
        if(mercredi.isSelected())
        {s+=" mercredi";}
        if(jeudi.isSelected())
        {s+=" jeudi";}
        if(vendredi.isSelected())
        {s+=" vendredi";}
         //ser.AfficheParNom("x")
        System.out.println("resulte "+new Enfant(z.AfficheParNom((String) enfant.getValue())));
      Babysitting  p;
        p = new Babysitting(k.rechercheParNom((String) id.getValue()), heuredebut.getValue().getHour()*3600,  heurefin.getValue().getHour()*3600, s, Integer.parseInt(prixheure.getText()),new Enfant(z.AfficheParNom((String) enfant.getValue())));
       
        S.ajouter(p);
         FXMLLoader fx=new FXMLLoader(getClass().getResource("ReponseAdmin.fxml"));
        Parent root=fx.load();
       id.getScene().setRoot(root);
    }

    @FXML
    private void affiche(ActionEvent event) throws IOException {
        FXMLLoader fx=new FXMLLoader(getClass().getResource("consulteAdminBabysiter.fxml"));
        Parent root=fx.load();
       id.getScene().setRoot(root);
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
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("reponse.fxml"));
            
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
