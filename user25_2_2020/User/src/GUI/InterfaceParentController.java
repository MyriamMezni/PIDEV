/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


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
public class InterfaceParentController implements Initializable {

    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private HBox cantine;
    private entity.Parent p;
    @FXML
    private ImageView image;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private HBox evenements;
    @FXML
    private HBox SignOut;


    public void setP(entity.Parent p) {
        System.out.println("hi4");
        this.p = p;
        System.out.println("hi5");
        System.out.println(this.p);
        System.out.println(this.p.getImage());
        prenom.setText(this.p.getPrenom());
        nom.setText(this.p.getNom());
        File f=new File(this.p.getImage());
        Image i =new Image(f.toURI().toString());
        image.setImage(i);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*System.out.println(p.getPrenom());
        System.out.println(p.getImage());
        System.out.println(p.getNom());*/
        //image.setImage(new Image(p.getImage()));
        
    }    

    @FXML
    private void gesionCompte(MouseEvent event) throws IOException {
       
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("ModiferCompteParent.fxml"));
            
        Parent root=fxml.load();
            
        nom.getScene().setRoot(root);
            
        ModiferCompteParentController mcp= fxml.getController();

        mcp.setP(p);
            
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
