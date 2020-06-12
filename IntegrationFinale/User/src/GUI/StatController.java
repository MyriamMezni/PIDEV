/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.Connexion;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatController implements Initializable {
    private Connection con=null;
    private ResultSet rs=null;
    private PreparedStatement pst=null;
    private entity.Parent p;

    @FXML
    private BarChart<String,Integer> Stat;
    @FXML
    private CategoryAxis Intitule;
    @FXML
    private NumberAxis Rate;
    @FXML
    private Button Load;
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
    private Label prenom;
    private Label nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       con=Connexion.getInstance().getCnx();

//        XYChart.Series set1=new XYChart.Series<>();
  //      Stat.getData().addAll(set1);
    }    

    @FXML
    private void Load(ActionEvent event) {
            XYChart.Series<String,Integer> set1=new XYChart.Series<>();

            
            
        try {  
            pst=con.prepareStatement("Select * from ratingactivite");
            rs=pst.executeQuery();
            while(rs.next())
            {
            set1.getData().add(new XYChart.Data<>(rs.getString(2),rs.getInt(3)));
            
            }
            
            
        Stat.getData().add(set1);
        } catch (SQLException ex) {
        Logger.getLogger(StatController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
          
            
            
            
    }

    private void Retour(ActionEvent event) throws IOException {
     AnchorPane pane=FXMLLoader.load(getClass().getResource("Stat.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
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

public void setP(entity.Parent p) {
        this.p = p;
        System.out.println(this.p);
        System.out.println(this.p.getImage());
        File f=new File(staticvar.Image_URL+this.p.getImage());
        Image i =new Image(f.toURI().toString());
        image.setImage(i);
    }
}
