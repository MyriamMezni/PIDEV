/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.EmployeService;
import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import entity.Employe;
import entity.Parent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class GestionEmployeController implements Initializable {

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
    private JFXTabPane tabPane;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXPasswordField mdpc;
    @FXML
    private JFXTextField numTel;
    @FXML
    private ComboBox<String> region;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField rue;
    @FXML
    private JFXTextField codePostal;
    @FXML
    private JFXButton image1;
    @FXML
    private JFXButton imageN;
    @FXML
    private JFXTextField salaire;
    @FXML
    private JFXTextField heure;
    @FXML
    private JFXDatePicker date;
    @FXML
    private ImageView imageP2;
    @FXML
    private JFXButton Ajouter;
    private String chemin="";
    @FXML
    private ComboBox<String> type;
    @FXML
    private Tab Afficher;
    @FXML
    private TableView<EmployeAffichage> tableView;
    @FXML
    private JFXButton Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         region.getItems().removeAll(region.getItems());
        region.getItems().add("Tunis");
        region.getItems().add("Ben Arous");
        region.getItems().add("Ariana");
        region.getSelectionModel().select("Tunis");
        region.getItems().removeAll(region.getItems());
        type.getItems().add("Cantine");
        type.getItems().add("Activite");
        type.getSelectionModel().select("Cantine");
    }   
    
    
    public ObservableList<EmployeAffichage> getEmployes()
    {
        ObservableList<EmployeAffichage> o=FXCollections.observableArrayList();
        EmployeService es=new EmployeService();
        for(Employe e:es.displayAll())
        {
            EmployeAffichage ep= new EmployeAffichage();
            ep.transformer(e);
            o.add(ep);
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
    private void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        chemin=f.getPath();
        Image i= new Image(f.toURI().toString());
        imageP2.setImage(i);
    }

    @FXML
    private void PrendreImage(ActionEvent event) throws IOException {
        Webcam wb =Webcam.getDefault();
        wb.open();
        String name="D:\\sauv chokri\\Esprit\\3emeannee\\Pidev\\Images\\"+prenom.getText()+nom.getText()+".jpg";
        File f= new File(name);
        ImageIO.write(wb.getImage(),"JPG" ,f);
        System.out.println("Ok");
        Image i =new Image(f.toURI().toString());
        imageP2.setImage(i);
        chemin=f.getAbsolutePath();
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        int year=date.getValue().getYear()-1900;
        int mounth=date.getValue().getMonthValue()-1;
        int day=date.getValue().getDayOfYear();
        Date d= new Date(year,mounth,day);
        Employe e= new Employe(d, new Float(salaire.getText()) ,new Integer(heure.getText()), type.getSelectionModel().getSelectedItem(),region.getSelectionModel().getSelectedItem(),ville.getText(),rue.getText(),codePostal.getText(),nom.getText(),prenom.getText(),email.getText(), mdp.getText(), chemin, Integer.parseInt(numTel.getText()));
        System.out.println(e);
        EmployeService es =new EmployeService();
        es.insert(e);
        
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        EmployeService es=new EmployeService();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            es.delete(tableView.getSelectionModel().getSelectedItem().getId_User());
            tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("im"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                
                TableColumn<EmployeAffichage,Integer> NbHeuresColumn=new TableColumn<>("Nb heures");
                NbHeuresColumn.setMinWidth(100);
                NbHeuresColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Integer>("NbHeures"));
                TableColumn<EmployeAffichage,Float> Salaire=new TableColumn<>("Salaire");
                Salaire.setMinWidth(100);
                Salaire.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Float>("Salaire"));
                
                tableView.setItems(getEmployes());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,NbHeuresColumn,Salaire);
        }
        
    }

    @FXML
    private void TabChange(Event event) {
        tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("im"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                
                TableColumn<EmployeAffichage,Integer> NbHeuresColumn=new TableColumn<>("Nb heures");
                NbHeuresColumn.setMinWidth(100);
                NbHeuresColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Integer>("NbHeures"));
                TableColumn<EmployeAffichage,Float> Salaire=new TableColumn<>("Salaire");
                Salaire.setMinWidth(100);
                Salaire.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Float>("Salaire"));
                
                tableView.setItems(getEmployes());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,NbHeuresColumn,Salaire);
        
    }
   /* private void Supprimer(ActionEvent event) {
        /*ParentService ps=new ParentService();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            ps.delete(tableView.getSelectionModel().getSelectedItem().getId_User());
            tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("Image"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                TableColumn<EmployeAffichage,String> EmailColumn=new TableColumn<>("Email");
                EmailColumn.setMinWidth(150);
                EmailColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("Email"));
                TableColumn<EmployeAffichage,String> NumTelColumn=new TableColumn<>("NumTel");
                NumTelColumn.setMinWidth(100);
                NumTelColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("NumTel"));
                TableColumn<EmployeAffichage,String> NbEnfantsColumn=new TableColumn<>("NBEnfants");
                NbEnfantsColumn.setMinWidth(100);
                NbEnfantsColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("NbEnfant"));
                
                tableView.setItems(getParents());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,EmailColumn,NbEnfantsColumn,NumTelColumn);*/
    //}
    
}
