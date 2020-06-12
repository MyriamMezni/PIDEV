/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import util.Connexion;
import Service.EvtEnfantService;
import Service.EvenementService;
import Service.ExcurtionService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import entity.EvtEnfant;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class InscrEvtController implements Initializable {
     private Connection con=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    

      private entity.Parent p;
      
    @FXML
    private JFXButton Inscrire;
    @FXML
    private JFXButton Annuler;
    @FXML
    private JFXComboBox<String> txtEvt;
     ObservableList <String>listEvt;
//      ObservableList <Integer>listID;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView image;
    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private HBox cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox Noter;
    @FXML
    private HBox SignOut;
    private Label nom;

    public void setP(entity.Parent p) {
        this.p = p;
        File f=new File(staticvar.Image_URL+this.p.getImage());
        javafx.scene.image.Image i =new javafx.scene.image.Image(f.toURI().toString());
        image.setImage(i);
    }
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         con=Connexion.getInstance().getCnx();
        listEvt=FXCollections.observableArrayList();
        
        try {
            pst=con.prepareStatement("select nom from evenement ");
               rs=pst.executeQuery();
        while(rs.next())
        {
        listEvt.add(rs.getString(1));
        
        
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(InscrEvtController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtEvt.setItems(listEvt);
        // TODO
    }    

    @FXML
    private void inscrire(ActionEvent event) {
        int pc=-1,enfantid=-1;
        pc=p.getId_User();
        
        String req="select id_enfant from enfant where id_parent='"+pc+"'";
         try {
             pst=con.prepareStatement(req);
             rs=pst.executeQuery();
             if (rs.first())
             {
                 enfantid=rs.getInt(1);
             }
         } catch (SQLException ex) {
             Logger.getLogger(InscrEvtController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       EvtEnfantService EV=new EvtEnfantService();
    EvenementService ES=new EvenementService();
    
        System.out.println(ES);
       int i=-1;
          i= ES.retourid(txtEvt.getValue());
          String url="";
       
             
            EV.insert(new EvtEnfant(i, enfantid));
    }

    @FXML
    private void Annuler(ActionEvent event) {
        EvenementService ES=new EvenementService();
        int i=-1;
        int pc=-1,enfantid=-1;
        pc=p.getId_User();
          i= ES.retourid(txtEvt.getValue());
         String req1="select id_enfant from enfant where id_parent='"+pc+"'";
         try {
             pst=con.prepareStatement(req1);
             rs=pst.executeQuery();
             if (rs.first())
             {
                 enfantid=rs.getInt(1);
             }
             
         }catch (SQLException ex) {
             Logger.getLogger(InscrEvtController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         String req2="delete from inscrevt where   id='"+i+"' and id_enfant='"+enfantid+"'";
         try {
             pst=con.prepareStatement(req1);
               rs=pst.executeQuery();
         } catch (SQLException ex) {
             Logger.getLogger(InscrEvtController.class.getName()).log(Level.SEVERE, null, ex);
         }
           
    }

    @FXML
    private void gesionCompte(MouseEvent event) throws IOException {
       
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("ModiferCompteParent.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
            
        ModiferCompteParentController mcp= fxml.getController();

        mcp.setP(p);
            
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        AjouterEnfantController EC=fxml.getController();
        EC.setP(p);
    }

    @FXML
    private void cantine(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceReserverUnMenu.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        InterfaceReserverUnMenuController irmc=fxml.getController();
        irmc.setP(p);
        
        
    }

    @FXML
    private void Evenement(MouseEvent event) throws IOException {
        
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("InscrEvt.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        InscrEvtController EC=fxml.getController();
        EC.setP(p);
        
    }

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
    }

    @FXML
    private void NoterActivité(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("RatingActivite.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        RatingActiviteController rac=fxml.getController();
        rac.setP(p);
    }

    @FXML
    private void CompteOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void CompteOn(MouseEvent event) {
        //info.setText("Gerer votre compte");
    }

    @FXML
    private void EnfantOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EnfantOn(MouseEvent event) {
        //info.setText("Gerer vos enfants");
    }

    @FXML
    private void Cantineoff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void CantineOn(MouseEvent event) {
        //info.setText("Reserver des menus");
    }

    @FXML
    private void EvenementOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EvenementOn(MouseEvent event) {
        //info.setText("Inscrivez vos enfants dans nos evenements");
    }

    @FXML
    private void NoteOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void NoteOn(MouseEvent event) {
        //info.setText("Notez nos activités");
    }

  
}
