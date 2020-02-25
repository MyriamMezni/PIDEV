/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private ComboBox<String> region;
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
    private HBox evenement;
    @FXML
    private HBox signout;
    @FXML
    private JFXPasswordField mdp1;
    @FXML
    private Label tarif;
    private String chemin="";
    /**
     * Initializes the controller class.
     */
    
    public void setP(entity.Parent p) {
        this.p = p;
        prenom.setText(this.p.getPrenom());
        nom.setText(this.p.getNom());
        email.setText(this.p.getEmail());
        numTel.setText(new Integer(this.p.getNumTel()).toString());
        File f=new File(this.p.getImage());
        Image i =new Image(f.toURI().toString());
        image1.setImage(i);
        ville.setText(this.p.getVille());
        rue.setText(this.p.getRue());
        codePostal.setText(this.p.getCodePostal());
        tarif.setText(new Float(this.p.getTarif()).toString()+"Dt");
        region.getItems().removeAll(region.getItems());
        region.getItems().add("Tunis");
        region.getItems().add("Ben Arous");
        region.getItems().add("Ariana");
        region.getSelectionModel().select(this.p.getRegion());
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        chemin=f.getPath();
        Image i= new Image(f.toURI().toString());
        image1.setImage(i);
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
        image1.setImage(i);
        chemin=f.getAbsolutePath();
    }

    @FXML
    private void AjouterParent(ActionEvent event) throws IOException {
        if(mdp1.getText().equals(p.getMDP()) && !mdp1.getText().equals(""))
        {
            
            if(!mdp.getText().equals(""))
            {
                if(mdp.getText().equals(mdpc.getText()))
                {
                    p.setMDP(mdp.getText());
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Les champs Mot de passe et confirmation ne sont pas identiques!");
                }
            }
        }
        System.out.println("chemin:"+chemin);
        if(chemin.equals(""))
        {
            chemin=p.getImage(); 
        }

        entity.Parent p1= new entity.Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),p.getTarif(),p.getNbEnfant(),p.getId_User(),nom.getText(),prenom.getText(),email.getText(), p.getMDP(), chemin, Integer.parseInt(numTel.getText()));
        System.out.println(p1);
        ParentService ps =new ParentService();
        ps.update(p1);
        
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
        javafx.scene.Parent root=fxml.load();
        mdp.getScene().setRoot(root);
        InterfaceParentController ipc= fxml.getController();
        System.out.println("hi3");
        ipc.setP(p1);
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

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
    }
    
}
