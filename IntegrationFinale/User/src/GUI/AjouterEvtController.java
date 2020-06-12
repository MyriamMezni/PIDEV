/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ExcurtionService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entity.Excurtion;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterEvtController implements Initializable {
    private String chemin;
    @FXML
    private JFXTimePicker txtHeure;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private ComboBox<String> txtCapacite;
    ObservableList<String> listC=FXCollections.observableArrayList("25","50","75");
    List <String>firstfile;
    
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private TextField txtImage;
    @FXML
    private JFXTextField txtDepart;
    @FXML
    private JFXTextField txtDestination;
    @FXML
    private ComboBox<String> txtType;
    ObservableList<String> list=FXCollections.observableArrayList("Excursion");
    @FXML
    private Button Ajouter;
    @FXML
    private Button select;
    @FXML
    private Button AfficherExcu;
    @FXML
    private Button backEVT;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        txtType.setItems(list);
        txtCapacite.setItems(listC);
        firstfile=new ArrayList<>();
        firstfile.add("*.JPG");
        firstfile.add("*.PNG");
        firstfile.add("*.JPEG");
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
        ExcurtionService ES=new ExcurtionService();
        
        ES.insert(new Excurtion(txtDepart.getText(), txtDestination.getText(),txtCapacite.getValue(), txtNom.getText(),txtHeure.getValue().toString(), txtDescription.getText(), txtDate.getValue().toString(), chemin, txtType.getValue()));
        JOptionPane.showMessageDialog(null, "ajout termine");
    }

    @FXML
    private void select(ActionEvent event) throws IOException {
//        FileChooser fc=new FileChooser();
//        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", firstfile));
//        File F=fc.showOpenDialog(null);
//        if (F!=null)
//            txtImage.setText(F.getAbsolutePath());
FileChooser fileChooser = new FileChooser();
    
       // fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        
        txtImage.setText(f.toURI().toString());
        String ext=f.getName().substring(f.getName().lastIndexOf("."));
        int ran=(int)(Math.random()*10000);
        String pathran=ran+ext;
        File fo=new File(staticvar.Image_URL+pathran);
        staticvar.copyFileUsingStream(f,fo);
        System.out.println(f);
        chemin=pathran;
        Image i= new Image(f.toURI().toString());
        //imagepreview.setImage(i);
    }

    @FXML
    private void AfficherExcu(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("AfficherExcursion.fxml"));
        Scene s2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(s2);
        Window.show();
    }

    @FXML
    private void backEVT(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("Evenements.fxml"));
        Scene s2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(s2);
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
