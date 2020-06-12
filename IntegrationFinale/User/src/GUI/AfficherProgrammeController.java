/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Activite;
import entity.Programme;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Connexion;
import Service.ServiceActivite;
import Service.ServiceProgramme;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherProgrammeController implements Initializable {
    private  ObservableList<Programme> data;   
 private Connection con=null;
    private PreparedStatement pst=null;
    
    private ResultSet rs=null;
    
    @FXML
    private TableView<Programme> table;
    @FXML
    private TableColumn<Programme, String> IdProgramme;
    @FXML
    private TableColumn<Programme, String> Intitule;
    @FXML
    private TableColumn<Programme, String> Cours;
    @FXML
    private TableColumn<Programme, String> Debut;
    @FXML
    private TableColumn<Programme, String> Fin;
    @FXML
    private Button Afficher;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private ComboBox<Integer> TxtSupp;
         private   ObservableList<Integer> listSupp;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Retour;
    @FXML
    private TextField Recherche;
    @FXML
    private Button Contenu;
    private ImageView image;
    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private TitledPane cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox Activite;
    @FXML
    private HBox SignOut;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        con=Connexion.getInstance().getCnx();
                        data =FXCollections.observableArrayList();
                        
            setCelltable();  
            listSupp=FXCollections.observableArrayList();
   try {
            pst=con.prepareStatement("select IdProgramme from programme");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listSupp.add(rs.getInt(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
TxtSupp.setItems(listSupp);
    }    
     private void setCelltable()
    {
         IdProgramme.setCellValueFactory(new PropertyValueFactory<>("IdProgramme"));
        Intitule.setCellValueFactory(new PropertyValueFactory<>("Intitule"));
        Cours.setCellValueFactory(new PropertyValueFactory<>("Cours"));
        Debut.setCellValueFactory(new PropertyValueFactory<>("Debut"));
        Fin.setCellValueFactory(new PropertyValueFactory<>("Fin"));
    }

    @FXML
    private void Afficher(ActionEvent event) {
        
        try {
            data.clear();

                pst=con.prepareStatement("Select * from programme");
            rs=pst.executeQuery();
            while(rs.next())
            {
                
             data.add(new Programme(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table.setItems(data);  
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
    
         AnchorPane pane=FXMLLoader.load(getClass().getResource("ModifierProgramme.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
        
    }
    
    

    @FXML
    private void Supprimer(ActionEvent event) {
    

                ServiceProgramme  sa=new ServiceProgramme();
                sa.supprimerProgramme(TxtSupp.getValue());
                listSupp.clear();
                 try {
            pst=con.prepareStatement("select IdProgramme from programme");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listSupp.add(rs.getInt(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
                TxtSupp.setItems(listSupp);   
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
      AnchorPane pane=FXMLLoader.load(getClass().getResource("AjouterProgramme.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
      
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
           AnchorPane pane=FXMLLoader.load(getClass().getResource("ActProg.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
    }

    @FXML
    private void Recherche(ActionEvent event) {



 try {
            data.clear();

            pst=con.prepareStatement("select * from programme where Intitule=?");
            pst.setString(1,Recherche.getText());
            rs=pst.executeQuery();
            while(rs.next())
            {
                
             data.add(new Programme(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
 table.setItems(data);

    }

    @FXML
    private void Contenu(ActionEvent event) throws IOException {
         AnchorPane pane=FXMLLoader.load(getClass().getResource("ContenuCours.fxml"));
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
