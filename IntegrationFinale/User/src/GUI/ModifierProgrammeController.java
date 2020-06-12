/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Activite;
import entity.Programme;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import util.Connexion;
import Service.ServiceActivite;
import Service.ServiceProgramme;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierProgrammeController implements Initializable {
    
    ObservableList<String> listDebut=FXCollections.observableArrayList("Janvier","Fevrier","Mars","Avril","Mai","Juin","Septembre","Octobre","Novembre","Decembre");
     ObservableList<String> listFin=FXCollections.observableArrayList("Janvier","Fevrier","Mars","Avril","Mai","Juin","Septembre","Octobre","Novembre","Decembre");
     private Connection con=null;
    private ResultSet rs=null;
    List<String> firstfile;
    

    private PreparedStatement pst=null;
    @FXML
    private ComboBox<String> TxtIntitule;
    @FXML
    private ComboBox<String> TxtDebut;
    @FXML
    private ComboBox<String> TxtFin;
    @FXML
    private Button Modifier;
    @FXML
    private Button file;
    @FXML
    private TextField singlefile;
        private   ObservableList<String> listActivite;
                private   ObservableList<Integer> listProg;

    @FXML
    private ComboBox<Integer> TxtProgramme;
    @FXML
    private Button Annuler;
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
            TxtDebut.setItems(listDebut);
            TxtFin.setItems(listFin);
                listActivite=FXCollections.observableArrayList();
   try {
            pst=con.prepareStatement("select Intitule from activite");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listActivite.add(rs.getString(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
TxtIntitule.setItems(listActivite); 




 listProg=FXCollections.observableArrayList();
   try {
            pst=con.prepareStatement("select IdProgramme from programme");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listProg.add(rs.getInt(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
TxtProgramme.setItems(listProg);



             firstfile=new ArrayList<>();
        firstfile.add("*.txt");
        firstfile.add("*.doc");
        firstfile.add("*.docx");
        firstfile.add("*.DOC");
        firstfile.add("*.DOCX");
    }    

    @FXML
    private void Modifier(ActionEvent event) {
        
         ServiceProgramme  sa=new ServiceProgramme();

 /*  try { int val=-1;
            pst=con.prepareStatement("select IdProgramme from programme where IdProgramme=?");
            pst.setInt(1,TxtProgramme.getValue());

            rs=pst.executeQuery();
            while(rs.next())
            {
            val=rs.getInt(1);
            

            }*/
 if((TxtIntitule.getValue()==null)||(singlefile.getText().equals(""))||(TxtDebut.getValue() == null)||(TxtFin.getValue() == null ))
{           JOptionPane.showMessageDialog(null,"Un champ manquant");}
else
 {sa.modifierProgramme(new Programme(TxtProgramme.getValue(),TxtIntitule.getValue(),singlefile.getText(),TxtDebut.getValue(),TxtFin.getValue()));
    JOptionPane.showMessageDialog(null,"Modification effectué");}

  /*
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
  */      
    }

    @FXML
    private void file(ActionEvent event) {

         FileChooser fc=new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word files", firstfile));
        File f=fc.showOpenDialog(null);
        if(f!=null)
        {
        singlefile.setText(f.getAbsolutePath());
        }
        
    }

    @FXML
    private void Annuler(ActionEvent event) throws IOException {
        
        
        
         AnchorPane pane=FXMLLoader.load(getClass().getResource("AfficherProgramme.fxml"));
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
