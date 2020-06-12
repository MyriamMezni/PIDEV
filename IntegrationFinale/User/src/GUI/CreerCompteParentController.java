/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entity.Parent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Random;
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
import util.ControleSaisie;
import util.servicebcrypt;
import util.staticvar;

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
    @FXML
    private JFXTextField username;
    @FXML
    private JFXDatePicker date;

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
    private void choisirImage(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpg","*.jpeg"));
        File f=fileChooser.showOpenDialog(null);
        String ext=f.getName().substring(f.getName().lastIndexOf("."));
        int ran=(int)(Math.random()*10000);
        String pathran=ran+ext;
        File fo=new File(staticvar.Image_URL+pathran);
        staticvar.copyFileUsingStream(f,fo);
        chemin=pathran;
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
        boolean prenomok=ControleSaisie.isString(prenom.getText().trim());
        boolean nomok=ControleSaisie.isString(nom.getText().trim());
        boolean emailok=ControleSaisie.valiEmail(email.getText());
        boolean mdpok=ControleSaisie.validPassword(mdp.getText())&&(mdp.getText().length()>=8);
        boolean numtelok=ControleSaisie.isTel(numTel.getText());
        boolean villeok=ControleSaisie.isString(ville.getText().trim());
        boolean rueok=ControleSaisie.adresse(rue.getText().trim());
        boolean codeok=ControleSaisie.isNumber(codePostal.getText())&&codePostal.getText().length()==4;
        if(prenomok && nomok && emailok && mdpok && numtelok && villeok && villeok && rueok && codeok)
        {
            if(mdp.getText().equals(mdpc.getText()))
            {
                AdminService a=new AdminService();
                if(!a.existe(email.getText().toLowerCase(), mdp.getText(),username.getText().toLowerCase()))
                {
                    Parent p= new Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),0,0,nom.getText(),prenom.getText(),email.getText(), mdp.getText(), chemin, Integer.parseInt(numTel.getText()));
                    p.setEmail_canonical(email.getText().toLowerCase());
                    p.setUsername(username.getText());
                    p.setUsername_canonical(username.getText().toLowerCase());
                    String passwd=servicebcrypt.hashpw(mdp.getText(), staticvar.SALT);
                    p.setPasword(passwd);
                    if(date.getValue()!=null)
                    {
                        int year=date.getValue().getYear()-1900;
                        int mounth=date.getValue().getMonthValue()-1;
                        int day=date.getValue().getDayOfYear();
                        Date d= new Date(year,mounth,day);
                        p.setDateNaissance(d);
                    }
                    System.out.println(p);
                    ParentService ps =new ParentService();
                    ps.insert(p);
                    JOptionPane.showMessageDialog(null, "Bienvenue");
                    FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
                    javafx.scene.Parent root=fxml.load();
                    mdp.getScene().setRoot(root);
                    InterfaceParentController ipc= fxml.getController();
                    System.out.println("hi3");
                    ipc.setP(p);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Email ou username existant!");
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Les champs\"Mot de passe\" et \" Confirmer mot de passe\" ne sont pas conforment");
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        
        
    }

    @FXML
    private void PrendreImage(ActionEvent event) throws IOException {
        Webcam wb =Webcam.getDefault();
        Random r=new Random();
        String ran="";
        String alphabet="123456789";
        for(int i=0;i<15;i++)
        {
            
            ran+=alphabet.charAt(r.nextInt(alphabet.length()));
            
        }
        String name=ran+".png";
        File f= new File(staticvar.Image_URL+name);
        wb.open();
        
        ImageIO.write(wb.getImage(),"png" ,f);
        System.out.println("Ok");
        Image i =new Image(f.toURI().toString());
        imagepreview.setImage(i);
        chemin=name;
       // wb.close();
    }
    
}
