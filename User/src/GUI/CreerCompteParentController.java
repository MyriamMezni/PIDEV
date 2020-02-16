/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Parent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class CreerCompteParentController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXPasswordField mdpc;
    @FXML
    private ComboBox<String> region;
    @FXML
    private TextField ville;
    @FXML
    private TextField rue;
    @FXML
    private TextField codePostal;
    @FXML
    private Button image;
    @FXML
    private ImageView imagepreview;
    @FXML
    private Button valider;
    private String chemin;
    @FXML
    private Button imageN;
    @FXML
    private JFXTextField numTel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        region.getItems().removeAll(region.getItems());
        region.getItems().add("Tunis");
        region.getItems().add("Ben Arous");
        region.getItems().add("Ariana");
        region.getSelectionModel().select("Tunis");

        // TODO
    }    

    @FXML
    private void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        System.out.println(f.toURI().toString());
        chemin=f.getPath();
        Image i= new Image(f.toURI().toString());
        imagepreview.setImage(i);
    }

    @FXML
    private void AjouterParent(ActionEvent event) throws IOException {
        /*System.out.println(prenom.getText());
        System.out.println(nom.getText());
        System.out.println(mdp.getText());
        System.out.println(email.getText());
        System.out.println(region.getSelectionModel().getSelectedItem());
        System.out.println(ville.getText());
        System.out.println(rue.getText());
        System.out.println(codePostal.getText());
        System.out.println(chemin);*/
        Parent p= new Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),0,0,nom.getText(),prenom.getText(),email.getText(), mdp.getText(), chemin, Integer.parseInt(numTel.getText()));
        System.out.println(p);
        ParentService ps =new ParentService();
        ps.insert(p);
        JOptionPane.showMessageDialog(null, "Bienvenue");
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
        javafx.scene.Parent root=fxml.load();
        mdp.getScene().setRoot(root);
        
    }

    @FXML
    private void PrendreImage(ActionEvent event) throws IOException {
        Webcam wb =Webcam.getDefault();
        wb.open();
        String name=prenom.getText()+nom.getText()+".jpg";
        File f= new File(name);
        ImageIO.write(wb.getImage(),"JPG" ,f);
        System.out.println("Ok");
        Image i =new Image(f.toURI().toString());
        imagepreview.setImage(i);
        chemin=f.getAbsolutePath();
    }
    
}
