/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import util.ControleSaisie;
import util.servicebcrypt;
import util.staticvar;

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
    private JFXPasswordField mdp1;
    @FXML
    private Label tarif;
    private String chemin="";
    @FXML
    private HBox Noter;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField username;
    /**
     * Initializes the controller class.
     */
    
    public void setP(entity.Parent p) {
        this.p = p;
        prenom.setText(this.p.getPrenom());
        nom.setText(this.p.getNom());
        email.setText(this.p.getEmail());
        numTel.setText(new Integer(this.p.getNumTel()).toString());
        File f=new File(staticvar.Image_URL+this.p.getImage());
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
        username.setText(this.p.getUsername());
        // TODO
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        image1.setImage(i);
    }

    @FXML
    private void PrendreImage(ActionEvent event) throws IOException {
        /* Webcam wb =Webcam.getDefault();
        wb.open();
        String name="D:\\sauv chokri\\Esprit\\3emeannee\\Pidev\\Images\\"+prenom.getText()+nom.getText()+".jpg";
        File f= new File(name);
        ImageIO.write(wb.getImage(),"JPG" ,f);
        
        System.out.println("Ok");
        Image i =new Image(f.toURI().toString());
        image1.setImage(i);
        chemin=f.getAbsolutePath();*/
        
        
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
        image1.setImage(i);
        chemin=name;
        wb.close();
        
        
    }

    @FXML
    private void AjouterParent(ActionEvent event) throws IOException {
        /*if(mdp1.getText().equals(p.getMDP()) && !mdp1.getText().equals(""))
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
        System.out.println("chemin:"+chemin);*/
        if(chemin.equals(""))
        {
            chemin=p.getImage(); 
        }
        String motdp=p.getMDP();
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
                    AdminService a=new AdminService();
                    entity.Parent p1= new entity.Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),p.getTarif(),p.getNbEnfant(),p.getId_User(),nom.getText(),prenom.getText(),email.getText(), motdp, chemin, Integer.parseInt(numTel.getText()));
                    p1.setEmail_canonical(email.getText().toLowerCase());
                    p1.setUsername(username.getText());
                    p1.setUsername_canonical(username.getText().toLowerCase());
                    String passwd=servicebcrypt.hashpw(motdp, staticvar.SALT);
                    p1.setPasword(passwd);
                    if(date.getValue()!=null)
                    {
                        int year=date.getValue().getYear()-1900;
                        int mounth=date.getValue().getMonthValue()-1;
                        int day=date.getValue().getDayOfYear();
                        Date d= new Date(year,mounth,day);
                        p1.setDateNaissance(d);
                    }
                    else
                    {
                        p1.setDateNaissance(p.getDateNaissance());
                    }
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
                else
                {
                    JOptionPane.showMessageDialog(null, "Les champs\"Mot de passe\" et \" Confirmer mot de passe\" ne sont pas conforment");
                }
                
            }
            else
            {
                AdminService a=new AdminService();
                entity.Parent p1= new entity.Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),p.getTarif(),p.getNbEnfant(),p.getId_User(),nom.getText(),prenom.getText(),email.getText(), motdp, chemin, Integer.parseInt(numTel.getText()));
                p1.setEmail_canonical(email.getText().toLowerCase());
                p1.setUsername(username.getText());
                p1.setUsername_canonical(username.getText().toLowerCase());
                String passwd=servicebcrypt.hashpw(motdp, staticvar.SALT);
                p1.setPasword(passwd);
                if(date.getValue()!=null)
                {
                    int year=date.getValue().getYear()-1900;
                    int mounth=date.getValue().getMonthValue()-1;
                    int day=date.getValue().getDayOfYear();
                    Date d= new Date(year,mounth,day);
                    p1.setDateNaissance(d);
                }
                else
                {
                    p1.setDateNaissance(p.getDateNaissance());
                }
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
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Verifier vos champs");
        }

        /*entity.Parent p1= new entity.Parent(region.getSelectionModel().getSelectedItem(), ville.getText(),rue.getText() , codePostal.getText(),p.getTarif(),p.getNbEnfant(),p.getId_User(),nom.getText(),prenom.getText(),email.getText(), motdp, chemin, Integer.parseInt(numTel.getText()));
        System.out.println(p1);
        ParentService ps =new ParentService();
        ps.update(p1);
        
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceParent.fxml"));
        javafx.scene.Parent root=fxml.load();
        mdp.getScene().setRoot(root);
        InterfaceParentController ipc= fxml.getController();
        System.out.println("hi3");
        ipc.setP(p1);*/
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
    private void gestionEnfants(MouseEvent event) throws IOException {
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
        Parent root=fxml.load();
            
        nom.getScene().setRoot(root);
        AjouterEnfantController EC=fxml.getController();
        EC.setP(p);
    }

    @FXML
    private void cantine(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceReserverUnMenu.fxml"));
            
        Parent root=fxml.load();
            
        nom.getScene().setRoot(root);
        InterfaceReserverUnMenuController irmc=fxml.getController();
        irmc.setP(p);
        
        
    }

    @FXML
    private void Evenement(MouseEvent event) throws IOException {
        
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("InscrEvt.fxml"));
            
        Parent root=fxml.load();
            
        nom.getScene().setRoot(root);
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

    
}
