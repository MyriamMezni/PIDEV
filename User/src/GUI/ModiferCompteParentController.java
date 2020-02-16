/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class ModiferCompteParentController implements Initializable {

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
    private ComboBox<?> region;
    @FXML
    private JFXTextField ville;
    @FXML
    private JFXTextField rue;
    @FXML
    private JFXTextField codePostal;
    @FXML
    private JFXButton image;
    @FXML
    private JFXButton imageN;
    @FXML
    private JFXButton valider;
    private entity.Parent p;
    @FXML
    private ImageView image1;
    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private HBox cantine;
    @FXML
    private Label Evenement;

    /**
     * Initializes the controller class.
     */
    
    public void setP(entity.Parent p) {
        this.p = p;
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisirImage(ActionEvent event) {
    }

    @FXML
    private void PrendreImage(ActionEvent event) {
    }

    @FXML
    private void AjouterParent(ActionEvent event) {
    }

    @FXML
    private void gesionCompte(MouseEvent event) {
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
    
}
