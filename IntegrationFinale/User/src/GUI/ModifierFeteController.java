/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import util.Connexion;
import Service.ExcurtionService;
import Service.FeteService;
import com.jfoenix.controls.JFXTimePicker;
import entity.Excurtion;
import entity.Fete;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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
public class ModifierFeteController implements Initializable {
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    List <String>firstfile;

       private ObservableList<Integer> list;

    @FXML
    private ComboBox<Integer> idModifier;
    @FXML
    private ComboBox<String> txtCapacite;
    ObservableList<String> listC=FXCollections.observableArrayList("25","50","75");
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtImg;
    @FXML
     private ComboBox<String> txtType;
    ObservableList<String> list1=FXCollections.observableArrayList("Fête");
    @FXML
    private JFXTimePicker txtHeure;
    @FXML
    private DatePicker txtDate;
    @FXML
    private Button modifier;
    @FXML
    private TextField txtLieu;
    @FXML
    private Button select;
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
    private String chemin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCapacite.setItems(listC);
         firstfile=new ArrayList<>();
        firstfile.add("*.JPG");
        firstfile.add("*.PNG");
        firstfile.add("*.JPEG");
         con=Connexion.getInstance().getCnx();
        list=FXCollections.observableArrayList();
          txtType.setItems(list1);
        try {
            pst=con.prepareStatement("select id from evenement where type='fête'");
            rs=pst.executeQuery();
            while (rs.next())
            {
                
                list.add(rs.getInt(1));
            
            }
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(ModifierExcursionController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        idModifier.setItems(list);
    }    

    @FXML
    private void modifier(ActionEvent event) {
        
        FeteService FS=new FeteService();
        try {
            int val=-1;
            pst=con.prepareStatement("select id from evenement where id=? and type='fête'");
            pst.setInt(1,idModifier.getValue());
            rs=pst.executeQuery();
            while(rs.next())
            {
            val=rs.getInt(1);
            }
            FS.update(new Fete( txtLieu.getText(),val, txtCapacite.getValue(), txtNom.getText(), txtHeure.getValue().toString(), txtDescription.getText() , txtDate.getValue().toString(), chemin, txtType.getValue()));
            JOptionPane.showMessageDialog(null, "Modif termine");
        } catch (SQLException ex) {
            Logger.getLogger(ModifierFeteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void select(ActionEvent event) throws IOException {
        FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("image", firstfile));
        File F=fc.showOpenDialog(null);
        if (F!=null)
        {
            txtImg.setText(F.getAbsolutePath());
            String ext=F.getName().substring(F.getName().lastIndexOf("."));
            int ran=(int)(Math.random()*10000);
            String pathran=ran+ext;
            File fo=new File(staticvar.Image_URL+pathran);
            staticvar.copyFileUsingStream(F,fo);
            System.out.println(F);
            chemin=pathran;
        }
    }

    @FXML
    private void backEVT(ActionEvent event) throws IOException {
           AnchorPane pane=FXMLLoader.load(getClass().getResource("AfficherFete.fxml"));
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
