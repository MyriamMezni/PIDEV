/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.ServiceEnfant;
import Service.chaiseservice;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import entity.Menu;
import entity.MenuCommande;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Service.manuservice;
import Service.menu_commandeservice;
import com.jfoenix.controls.JFXButton;
import entity.Parent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import util.JavaMailUtil;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author ben younes
 */
public class InterfaceReserverUnMenuController implements Initializable {

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
    private Tab Reserver;
    @FXML
    private ComboBox<String> enfantMenu;
    @FXML
    private ComboBox<String> jourDeSemaine;
    @FXML
    private AnchorPane MenuProposé;
    @FXML
    private ComboBox<String> EnfantEnregistrer;
    @FXML
    private HBox Noter;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    private Parent p;
    @FXML
    private JFXButton Rapport;
    /**
     * Initializes the controller class.
     */
    
    public void setP(Parent p) {
        this.p = p;
        ServiceEnfant se=new ServiceEnfant();
        System.out.println("Cantine");
        for(String prenom:se.getNomParParentAvecCantine(p.getId_User()))
        {
            System.out.println(prenom);
        }
        System.out.println("Non");
        for(String prenom:se.getNomParParentSansCantine(p.getId_User()))
        {
            System.out.println(prenom);
        }
        EnfantEnregistrer.getItems().removeAll(EnfantEnregistrer.getItems());
        for(String prenom:se.getNomParParentSansCantine(p.getId_User()))
        {
            EnfantEnregistrer.getItems().add(prenom);
        }
        EnfantEnregistrer.getSelectionModel().select(0);
        File f=new File(staticvar.Image_URL+this.p.getImage());
        Image i =new Image(f.toURI().toString());
        image.setImage(i);
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*EnfantEnregistrer.getItems().removeAll(EnfantEnregistrer.getItems());
        EnfantEnregistrer.getItems().add(1);
        EnfantEnregistrer.getItems().add(2);
        EnfantEnregistrer.getSelectionModel().select(0);
        enfantMenu.getItems().removeAll(enfantMenu.getItems());
        enfantMenu.getItems().add(1);
        enfantMenu.getItems().add(2);
        enfantMenu.getSelectionModel().select(0);*/
        jourDeSemaine.getItems().removeAll(jourDeSemaine.getItems());
        jourDeSemaine.getItems().add("");
        jourDeSemaine.getItems().add("Lundi");
        jourDeSemaine.getItems().add("Mardi");
        jourDeSemaine.getItems().add("Mercredi");
        jourDeSemaine.getItems().add("Jeudi");
        jourDeSemaine.getItems().add("Vendredi");
        jourDeSemaine.getItems().add("Samedi");
        jourDeSemaine.getSelectionModel().select(0);
        // TODO
    }    

    


    @FXML
    private void MisAJourMenu(ActionEvent event) {
        if(Reserver.isSelected())
        {
            
            System.out.println(jourDeSemaine.getSelectionModel().getSelectedItem());
        }
        manuservice ms=new manuservice();
        VBox v2=new VBox();
        MenuProposé.getChildren().clear();
        Integer jour=new Integer(jourDeSemaine.getSelectionModel().getSelectedIndex());
        jour+=1;
        System.out.println(jour);
        ToggleGroup g=new ToggleGroup();
        for (Menu m:ms.getParjour(jour.toString()))
        {
            VBox v=new VBox();
            
            HBox h=new HBox();
            Label nom=new Label(m.getNom());
            nom.setStyle("-fx-text-fill:#ffffff");
            Label desc=new Label(m.getDescription());
            desc.setStyle("-fx-text-fill:#ffffff");
            desc.setWrapText(true);
            desc.prefWidth(200);
            desc.minWidth(200);
            v.getChildren().add(nom);
            v.getChildren().add(desc);
            File f=new File(staticvar.Image_URL+m.getImage());
            Image i=new Image(f.toURI().toString());
            ImageView im=new ImageView();
            
            im.setImage(i);
            im.setFitHeight(50);
            im.setFitWidth(50);
            //JFXCheckBox check=new JFXCheckBox("");
            JFXRadioButton radio=new JFXRadioButton("");
            radio.setId(jour.toString()+m.getId());
            ServiceEnfant se=new ServiceEnfant();
            int id_enfant=se.getIdParPrenomEtParent(p.getId_User(), enfantMenu.getSelectionModel().getSelectedItem());
            radio.setToggleGroup(g);
            menu_commandeservice mcs2 =new menu_commandeservice();
            if(mcs2.existetout(id_enfant, m.getId(), jour.toString()))
            {
                radio.setSelected(true);
            }
            radio.setOnAction((e) -> {
                System.out.println(radio.getId());
                menu_commandeservice mcs=new menu_commandeservice();
                Integer jours=new Integer(jourDeSemaine.getSelectionModel().getSelectedIndex());
                jours+=1;
                System.out.println("jours:"+jours);
                if(mcs.existeEnfantEtJour(id_enfant, jours.toString()))
                {
                    String j=mcs.jourDeSemaine(id_enfant, jours.toString());
                    j=j.replace(jours.toString(), "");
                    System.out.println("j:"+j);
                    mcs.updateJourDeSamine(id_enfant,jours.toString(), j);
                }
                System.out.println("ce menu:"+m);
                
                if(mcs.existe(id_enfant, m.getId()))
                {
                    String joursN=mcs.getJours(id_enfant, m.getId());
                    joursN=joursN+jours.toString();
                    System.out.println(joursN);
                    MenuCommande mc=new MenuCommande(m,id_enfant , joursN);
                    mcs.update(mc);
                }
                else
                {
                    MenuCommande mc =new MenuCommande(m, id_enfant, jours.toString());
                    mcs.insert(mc);
                }
                
            });
            h.getChildren().add(im);
            h.getChildren().add(v);
            h.getChildren().add(radio);
            v2.getChildren().add(h);
            
            
            //MenuProposé.getChildren().add(new Button(m.getNom()));
            System.out.println(m);
        }
        MenuProposé.getChildren().add(v2);
        
        //MenuProposé.getChildren().add(Ne)
    }

    @FXML
    private void Reserver(Event event) {
        ServiceEnfant se=new ServiceEnfant();
        //System.out.println("Cantine");
        enfantMenu.getItems().removeAll(enfantMenu.getItems());
        
        for(String prenom:se.getNomParParentAvecCantine(p.getId_User()))
        {
            enfantMenu.getItems().add(prenom);
        }
        enfantMenu.getSelectionModel().select(0);
        
    }

    

    @FXML
    private void AjoutCantine(ActionEvent event) {
        ServiceEnfant se=new ServiceEnfant();
        int id_enfant=se.getIdParPrenomEtParent(p.getId_User(), EnfantEnregistrer.getSelectionModel().getSelectedItem());
        //System.out.println(se.getIdParPrenomEtParent(p.getId_User(), EnfantEnregistrer.getSelectionModel().getSelectedItem()));
        chaiseservice cs=new chaiseservice();
        if(cs.chaiseVide())
        {
            cs.majEnfant(cs.numChaiseVide(), id_enfant);
            se.setCantine(1, id_enfant);
        }
        EnfantEnregistrer.getItems().removeAll(EnfantEnregistrer.getItems());
        for(String prenom:se.getNomParParentSansCantine(p.getId_User()))
        {
            EnfantEnregistrer.getItems().add(prenom);
        }
        EnfantEnregistrer.getSelectionModel().select(0);
        
    }

    @FXML
    private void RapportMail(ActionEvent event) {
        menu_commandeservice mcs=new menu_commandeservice();
        manuservice ms=new manuservice();
        System.out.println("ok");
        System.out.println("Lundi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "2")));
        System.out.println("Mardi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "3")));
        System.out.println("Mercredi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "4")));
        System.out.println("Jeudi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "5")));
        System.out.println("Vendredi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "6")));
        System.out.println("Samedi="+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "7")));
        String message="<h1>Voici le rapport de votre enfant <b>"+enfantMenu.getSelectionModel().getSelectedItem()+"</b></h1>"
                +"<p><b>Lundi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "2"))+"</p>"
                +"<p><b>Mardi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "3"))+"</p>"
                +"<p><b>Mercredi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "4"))+"</p>"
                +"<p><b>Jeudi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "5"))+"</p>"
                +"<p><b>Vendredi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "6"))+"</p>"
                +"<p><b>Samedi:</b>"+ms.getNom(mcs.getMenuPourMail(enfantMenu.getSelectionModel().getSelectedItem(), "7"))+"</p>";
        JavaMailUtil.SendMail2(p.getEmail(), message);
    }
    
    
    @FXML
    private void gesionCompte(MouseEvent event) throws IOException {
       
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("ModiferCompteParent.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
            
        ModiferCompteParentController mcp= fxml.getController();

        mcp.setP(p);
            
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        AjouterEnfantController EC=fxml.getController();
        EC.setP(p);
    }

    @FXML
    private void cantine(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceReserverUnMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        InterfaceReserverUnMenuController irmc=fxml.getController();
        irmc.setP(p);
        
        
    }

    @FXML
    private void Evenement(MouseEvent event) throws IOException {
        
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("InscrEvt.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
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
    
}
//SELECT menu_commande.id_menu from menu_commande join enfant on enfant.id_enfant=menu_commande.id_enfant WHERE enfant.prenom='foulen' and jour_de_la_semaine like '%1%';