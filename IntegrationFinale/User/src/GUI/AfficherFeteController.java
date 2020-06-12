/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import util.Connexion;
import Service.ExcurtionService;
import Service.FeteService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Fete;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherFeteController implements Initializable {
        //private ObservableList<Fete> data;
    private ObservableList<Fete> data;
    @FXML
    private Button afficher;
    private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
     
    
    @FXML
    private TableColumn<Fete, String> txtCapacite;
    @FXML
    private TableColumn<Fete, String> txtNom;
     @FXML
    private TableColumn<Fete, String> txtHeure;
    @FXML
    private TableColumn<Fete, String> txtDescription;
    @FXML
    private TableColumn<Fete, String> txtDate;
    @FXML
    private TableColumn<Fete, String> txtImage;
    @FXML
    private TableColumn<Fete, String> txtType;
    @FXML
    private TableColumn<Fete, String> txtLieu;
    @FXML
    private TableView<Fete> table;
    @FXML
    private TextField txtRech;
    @FXML
    private ComboBox<String> txtSupp;
    private ObservableList<String> listsupp;
    @FXML
    private Button supprimer;
    @FXML
    private Button backAjout;
    @FXML
    private Button Modifier;
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
    @FXML
    private Button pdf;
    
    
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          con=Connexion.getInstance().getCnx();
        data =FXCollections.observableArrayList();
        setCellTable();
         listsupp=FXCollections.observableArrayList();
        try {
            pst=con.prepareStatement("select nom from evenement where type='fête'");
               rs=pst.executeQuery();
        while(rs.next())
        {
        listsupp.add(rs.getString(1));
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherExcursionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtSupp.setItems(listsupp);
     
        
        // TODO
    }  
    
    private void setCellTable(){
    //txtId.setCellValueFactory(new PropertyValueFactory<>("id"));
     txtCapacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
      txtNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       txtHeure.setCellValueFactory(new PropertyValueFactory<>("heure_d"));
       txtDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        txtDate.setCellValueFactory(new PropertyValueFactory<>("date_evt"));
        
          txtImage.setCellValueFactory(new PropertyValueFactory<>("image"));
          txtType.setCellValueFactory(new PropertyValueFactory<>("type"));
            txtLieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) {
        
        try {
            data.clear();
            pst=con.prepareStatement("select * from evenement where type='fête'");
            rs=pst.executeQuery();
        while(rs.next()){
        data.add(new Fete(rs.getString(11),rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));               
        
        }
        } catch (SQLException ex) {
           Logger.getLogger(AfficherFeteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(data);
     
    }


    @FXML
    private void rechercher(KeyEvent event) {
        try {
            data.clear();
            pst=con.prepareStatement("select * from evenement where type='fête' and id='"+txtRech.getText()+"'");
            rs=pst.executeQuery();
        while(rs.next()){
        data.add(new Fete(rs.getString(11),rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));               
        
        }
        } catch (SQLException ex) {
           Logger.getLogger(AfficherFeteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(data);
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
        
        FeteService ES=new FeteService();
        int val=-1;
        try {
            pst=con.prepareStatement("select id from evenement where nom=?");
            pst.setString(1, txtSupp.getValue());
            rs=pst.executeQuery();
            while(rs.next()){
                val=rs.getInt(1);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherExcursionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ES.delete(val);
        listsupp.clear();
        
        try {
            pst=con.prepareStatement("select nom from evenement where type='fête'");
               rs=pst.executeQuery();
        while(rs.next())
        {
        listsupp.add(rs.getString(1));
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherExcursionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtSupp.setItems(listsupp);
        
    }

    @FXML
    private void backAjout(ActionEvent event) throws IOException {
        
        
        AnchorPane pane=FXMLLoader.load(getClass().getResource("AjouterFete.fxml"));
        Scene s2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(s2);
        Window.show();
        
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
        
         AnchorPane pane=FXMLLoader.load(getClass().getResource("ModifierFete.fxml"));
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
     //   info.setText("");
    }

    @FXML
    private void CantineOn(MouseEvent event) {
       // info.setText("Gerer les chaises,tables et menus");
    }

    @FXML
    private void ParentOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ParentOn(MouseEvent event) {
    //    info.setText("Gerer les parents");
    }

    @FXML
    private void EmployeOff(MouseEvent event) {
      //  info.setText("");
    }

    @FXML
    private void EmployeOn(MouseEvent event) {
        //info.setText("Gerer les employes");
    }

    @FXML
    private void EnfantOff(MouseEvent event) {
    //    info.setText("");
    }

    @FXML
    private void EnfantOn(MouseEvent event) {
      //  info.setText("Gerer les enfants");
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

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, SQLException {
        
        try {
             String filename="C:\\pdf\\Fete.pdf";
            Document doucment = new Document();
            PdfWriter.getInstance(doucment, new FileOutputStream(filename));
            
            doucment.open();
            String req="select * from evenement where type='Excursion'";
        int i=0;
            pst=con.prepareStatement(req);
            rs=pst.executeQuery();
             rs=pst.executeQuery();
              while(rs.next()){
                  i++;
        Paragraph para=new Paragraph("-"+i+"- "+" Depart: "+rs.getString(9)+"----->Destination:"+rs.getString(10)+" ---- Id:"+rs.getInt(1)+" ---- Capacite:"+rs.getString(2)+" ---- Nom: "+rs.getString(3)+" ---- Heure Debut:"+rs.getString(4)+" ---- Description:"+rs.getString(5)+" ---- Date Evt:"+rs.getString(6)+" ---- Chemindu photo:"+rs.getString(7)+" ---- Type:"+rs.getString(8)+"\n"+"\n");
        doucment.add(para);
       
        }
            doucment.close();
            JOptionPane.showMessageDialog(null, "ajout en format pdf terminé");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherExcursionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

   
    
}