/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
public class InterfaceModificationEmployeController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionEnfants;
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
    private Label salaire;
    @FXML
    private Label heure;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private ComboBox<String> type;
    private Employe e;
    private String chemin="";
    @FXML
    private TitledPane cantine1;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    @FXML
    private HBox Activite;
    @FXML
    private JFXTextField username;

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
        type.getItems().add("BabySitter");
        type.getSelectionModel().select(e.getType());
        ville.setText(e.getVille());
        rue.setText(e.getRue());
        codePostal.setText(e.getCodePostal());
        Integer nbHeuresI=new Integer(e.getNbHeures());
        Float salairef=new Float(e.getSalaire());
        salaire.setText(salairef.toString());
        heure.setText(nbHeuresI.toString());
        File f=new File(staticvar.Image_URL+e.getImage());
        Image i=new Image(f.toURI().toString());
        image.setImage(i);
        username.setText(this.e.getUsername());
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
    private void Evenement(MouseEvent event) {
    }

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
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
        image.setImage(i);
    }

    @FXML
    private void PrendreImage(ActionEvent event) throws IOException {
       Random r=new Random();
        String ran="";
        String alphabet="123456789";
        for(int i=0;i<15;i++)
        {

            ran+=alphabet.charAt(r.nextInt(alphabet.length()));

        }
        String name=ran+".png";
        File f= new File(staticvar.Image_URL+name);
        Webcam wb =Webcam.getDefault();
        wb.getDevice().toString();
        wb.open();

        ImageIO.write(wb.getImage(),"png" ,f);
        System.out.println("Ok");
        Image i =new Image(f.toURI().toString());
        image.setImage(i);
        chemin=name;
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        String motdp=e.getMDP();
        if(!mdp.getText().trim().equals(""))
        {
            motdp=mdp.getText();
        }
        boolean prenomok=ControleSaisie.isString(prenom.getText().trim());
        boolean nomok=ControleSaisie.isString(nom.getText().trim());
        boolean emailok=ControleSaisie.valiEmail(email.getText());
        boolean mdpok=ControleSaisie.validPassword(motdp)&&(motdp.length()>=8);
        boolean numtelok=ControleSaisie.isTel(numTel.getText());
        boolean villeok=ControleSaisie.isString(ville.getText().trim());
        boolean rueok=ControleSaisie.adresse(rue.getText().trim());
        boolean codeok=ControleSaisie.isNumber(codePostal.getText())&&codePostal.getText().length()==4;
        boolean salaireok=ControleSaisie.isNumber(salaire.getText());
        boolean heureok=ControleSaisie.isNumber(heure.getText());
         System.out.println(prenomok);
        System.out.println(nomok);
        System.out.println(emailok);
        System.out.println(mdpok);
        System.out.println(numtelok);
        System.out.println(villeok);
        System.out.println(rueok);
        System.out.println(codeok);
        if(prenomok && nomok && emailok && mdpok && numtelok && villeok && villeok && rueok && codeok)
        {
            if(!mdp.getText().equals(""))
            {
                if(mdp.getText().equals(mdpc.getText()))
                {
                    Date d=null;
                    if(date.getValue()!=null)
                    {
                        int year=date.getValue().getYear()-1900;
                        int mounth=date.getValue().getMonthValue()-1;
                        int day=date.getValue().getDayOfYear();
                        d= new Date(year,mounth,day);
                    }
                    else 
                    {
                        d=this.e.getDateNaissance();
                    }
                    int id=e.getId_User();
                    String mdp3=e.getMDP();
                    if(chemin.equals(""))
                    {
                        chemin=e.getImage();
                    }
                    Employe e1= new Employe(d, new Float(salaire.getText()) ,new Integer(heure.getText()), type.getSelectionModel().getSelectedItem(),region.getSelectionModel().getSelectedItem(),ville.getText(),rue.getText(),codePostal.getText(), id ,nom.getText(),prenom.getText(),email.getText(), mdp.getText(), chemin, Integer.parseInt(numTel.getText()));
                    e1.setEmail_canonical(email.getText().toLowerCase());
                    e1.setUsername(username.getText());
                    e1.setUsername_canonical(username.getText().toLowerCase());
                    String passwd=servicebcrypt.hashpw(motdp, staticvar.SALT);
                    e1.setPasword(passwd);
                    System.out.println(e1);
                    EmployeService es =new EmployeService();
                    es.update(e1);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Les champs\"Mot de passe\" et \" Confirmer mot de passe\" ne sont pas conforment");
                }
                
            }
            else
            {
                    Date d=null;
                    if(date.getValue()!=null)
                    {
                        int year=date.getValue().getYear()-1900;
                        int mounth=date.getValue().getMonthValue()-1;
                        int day=date.getValue().getDayOfYear();
                        d= new Date(year,mounth,day);
                    }
                    else 
                    {
                        d=this.e.getDateNaissance();
                    }
                    int id=e.getId_User();
                    String mdp3=e.getMDP();
                    if(chemin.equals(""))
                    {
                        chemin=e.getImage();
                    }
                    Employe e1= new Employe(d, new Float(salaire.getText()) ,new Integer(heure.getText()), type.getSelectionModel().getSelectedItem(),region.getSelectionModel().getSelectedItem(),ville.getText(),rue.getText(),codePostal.getText(), id ,nom.getText(),prenom.getText(),email.getText(), mdp3, chemin, Integer.parseInt(numTel.getText()));
                    e1.setEmail_canonical(email.getText().toLowerCase());
                    e1.setUsername(username.getText());
                    e1.setUsername_canonical(username.getText().toLowerCase());
                    String passwd=servicebcrypt.hashpw(motdp, staticvar.SALT);
                    e1.setPasword(passwd);
                    System.out.println(e1);
                    EmployeService es =new EmployeService();
                    es.update(e1);
                
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }
        
        
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
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAjouterUnMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void CantineOff(MouseEvent event) {
    }

    @FXML
    private void CantineOn(MouseEvent event) {
    }

    @FXML
    private void ActiviteOff(MouseEvent event) {
    }

    @FXML
    private void ActiviteOn(MouseEvent event) {
    }

    @FXML
    private void Activite(MouseEvent event) {
    }
    
}
