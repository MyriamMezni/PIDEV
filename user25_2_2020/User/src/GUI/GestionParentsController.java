/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ParentService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class GestionParentsController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private HBox cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox SignOut;
    @FXML
    private TableView<AffichageParent> tableView;
    @FXML
    private JFXTextField rechercheNom;
    @FXML
    private JFXButton Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
                tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<AffichageParent,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,ImageView>("Image"));
                TableColumn<AffichageParent,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("nom"));
                TableColumn<AffichageParent,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("prenom"));
                TableColumn<AffichageParent,String> EmailColumn=new TableColumn<>("Email");
                EmailColumn.setMinWidth(150);
                EmailColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("Email"));
                TableColumn<AffichageParent,String> NumTelColumn=new TableColumn<>("NumTel");
                NumTelColumn.setMinWidth(100);
                NumTelColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NumTel"));
                TableColumn<AffichageParent,String> NbEnfantsColumn=new TableColumn<>("NBEnfants");
                NbEnfantsColumn.setMinWidth(100);
                NbEnfantsColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NbEnfant"));
                
                tableView.setItems(getParents());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,EmailColumn,NbEnfantsColumn,NumTelColumn);
        
    }
    public ObservableList<AffichageParent> getParents()
    {
        ObservableList<AffichageParent> o=FXCollections.observableArrayList();
        ParentService ps=new ParentService();
        for(entity.Parent p:ps.displayAll())
        {
            AffichageParent ap= new AffichageParent();
            ap.transformer(p);
            o.add(ap);
        }
        return o;
    }    
    
    public ObservableList<AffichageParent> getParentsParNom(String nom)
    {
        ObservableList<AffichageParent> o=FXCollections.observableArrayList();
        ParentService ps=new ParentService();
        for(entity.Parent p:ps.rechercheNom(nom))
        {
            AffichageParent ap= new AffichageParent();
            ap.transformer(p);
            o.add(ap);
        }
        return o;
    }    

    @FXML
    private void gesionCompte(MouseEvent event) {
    }

    @FXML
    private void gestionParents(MouseEvent event) throws IOException {

         FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionParents.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
            
        //ModiferCompteParentController mcp= fxml.getController();
    }

    @FXML
    private void gestionEmploye(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionEmploye.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
    }

    @FXML
    private void gestionEnfants(MouseEvent event) {
    }

    @FXML
    private void cantine(MouseEvent event) {
    }

    @FXML
    private void Evenement(MouseEvent event) {
    }

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
    }


    @FXML
    private void rechercheNom(KeyEvent event) {
         tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<AffichageParent,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,ImageView>("Image"));
                TableColumn<AffichageParent,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("nom"));
                TableColumn<AffichageParent,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("prenom"));
                TableColumn<AffichageParent,String> EmailColumn=new TableColumn<>("Email");
                EmailColumn.setMinWidth(150);
                EmailColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("Email"));
                TableColumn<AffichageParent,String> NumTelColumn=new TableColumn<>("NumTel");
                NumTelColumn.setMinWidth(100);
                NumTelColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NumTel"));
                TableColumn<AffichageParent,String> NbEnfantsColumn=new TableColumn<>("NBEnfants");
                NbEnfantsColumn.setMinWidth(100);
                NbEnfantsColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NbEnfant"));
                
                tableView.setItems(getParentsParNom(rechercheNom.getText()));
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,EmailColumn,NbEnfantsColumn,NumTelColumn);
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        ParentService ps=new ParentService();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            ps.delete(tableView.getSelectionModel().getSelectedItem().getId_User());
            tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<AffichageParent,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,ImageView>("im"));
                TableColumn<AffichageParent,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("nom"));
                TableColumn<AffichageParent,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("prenom"));
                TableColumn<AffichageParent,String> EmailColumn=new TableColumn<>("Email");
                EmailColumn.setMinWidth(150);
                EmailColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("Email"));
                TableColumn<AffichageParent,String> NumTelColumn=new TableColumn<>("NumTel");
                NumTelColumn.setMinWidth(100);
                NumTelColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NumTel"));
                TableColumn<AffichageParent,String> NbEnfantsColumn=new TableColumn<>("NBEnfants");
                NbEnfantsColumn.setMinWidth(100);
                NbEnfantsColumn.setCellValueFactory(new PropertyValueFactory<AffichageParent,String>("NbEnfant"));
                
                tableView.setItems(getParents());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,EmailColumn,NbEnfantsColumn,NumTelColumn);
        }
    }
    
}
