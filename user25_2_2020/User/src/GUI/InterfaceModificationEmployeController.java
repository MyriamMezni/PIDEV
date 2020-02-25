/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.EmployeService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Employe;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceModificationEmployeController implements Initializable {

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
    private JFXButton image1;
    @FXML
    private JFXButton imageN;
    @FXML
    private JFXTextField salaire;
    @FXML
    private JFXTextField heure;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private ComboBox<String> type;
    @FXML
    private ImageView imageP2;
    private Employe e;
    private String chemin="";

    /**
     * Initializes the controller class.
     */
    
    public void setE(Employe e) {
        this.e = e;
        nom.setText(e.getNom());
        prenom.setText(e.getPrenom());
        email.setText(e.getEmail());
        Integer num =new Integer(e.getNumTel());
        numTel.setText(num.toString());
        region.getItems().remove(region.getItems());
        region.getItems().add("Tunis");
        region.getItems().add("Ben Arous");
        region.getItems().add("Ariana");
        region.getSelectionModel().select(e.getRegion());
        type.getItems().remove(type.getItems());
        type.getItems().add("Cantine");
        type.getItems().add("Activite");
        type.getSelectionModel().select(e.getType());
        ville.setText(e.getVille());
        rue.setText(e.getRue());
        codePostal.setText(e.getCodePostal());
        Integer nbHeuresI=new Integer(e.getNbHeures());
        Float salairef=new Float(e.getSalaire());
        salaire.setText(salairef.toString());
        heure.setText(nbHeuresI.toString());
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

    @FXML
    private void choisirImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg"));
        File f=fileChooser.showOpenDialog(null);
        chemin=f.getPath();
        Image i= new Image(f.toURI().toString());
        image.setImage(i);
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
        image.setImage(i);
        chemin=f.getAbsolutePath();
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        int year=date.getValue().getYear()-1900;
        int mounth=date.getValue().getMonthValue()-1;
        int day=date.getValue().getDayOfYear();
        Date d= new Date(year,mounth,day);
        int id=e.getId_User();
        String mdp3=e.getMDP();
        Employe e= new Employe(d, new Float(salaire.getText()) ,new Integer(heure.getText()), type.getSelectionModel().getSelectedItem(),region.getSelectionModel().getSelectedItem(),ville.getText(),rue.getText(),codePostal.getText(), id ,nom.getText(),prenom.getText(),email.getText(), mdp3, chemin, Integer.parseInt(numTel.getText()));
        System.out.println(e);
        EmployeService es =new EmployeService();
        es.update(e);
    }
    
}
