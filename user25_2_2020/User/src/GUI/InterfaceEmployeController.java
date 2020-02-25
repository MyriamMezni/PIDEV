/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entity.Employe;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceEmployeController implements Initializable {

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
    private HBox SignOut;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    private Employe e;

    /**
     * Initializes the controller class.
     */
    
    public void setE(Employe e) {
        this.e = e;
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        File f=new File(e.getImage());
        Image i=new Image(f.toURI().toString());
        image.setImage(i);
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gesionCompte(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceModificationEmploye.fxml"));

                    Parent root=fxml.load();

                    nom.getScene().setRoot(root);

                    InterfaceModificationEmployeController iec= fxml.getController();
                    System.out.println("hi3");
                    iec.setE(e);
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
    
}
