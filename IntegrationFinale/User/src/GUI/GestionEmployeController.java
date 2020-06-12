/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import Service.EmployeService;
import Service.ParentService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import entity.Employe;
import entity.Parent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class GestionEmployeController implements Initializable {

    private ImageView image;
    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private TitledPane cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox SignOut;
    @FXML
    private JFXTabPane tabPane;
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
    private ImageView imageP2;
    @FXML
    private JFXButton Ajouter;
    private String chemin="";
    @FXML
    private ComboBox<String> type;
    @FXML
    private Tab Afficher;
    @FXML
    private TableView<EmployeAffichage> tableView;
    @FXML
    private JFXButton Supprimer;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         region.getItems().removeAll(region.getItems());
        region.getItems().add("Tunis");
        region.getItems().add("Ben Arous");
        region.getItems().add("Ariana");
        region.getSelectionModel().select("Tunis");
        type.getItems().removeAll(type.getItems());
        type.getItems().add("Cantine");
        type.getItems().add("Activite");
        type.getItems().add("BabySitter");
        type.getSelectionModel().select("Cantine");
    }   
    
    
    public ObservableList<EmployeAffichage> getEmployes()
    {
        ObservableList<EmployeAffichage> o=FXCollections.observableArrayList();
        EmployeService es=new EmployeService();
        for(Employe e:es.displayAll())
        {
            EmployeAffichage ep= new EmployeAffichage();
            ep.transformer(e);
            o.add(ep);
        }
        return o;
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
        imageP2.setImage(i);
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
        imageP2.setImage(i);
        chemin=name;
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        boolean prenomok=ControleSaisie.isString(prenom.getText().trim());
        boolean nomok=ControleSaisie.isString(nom.getText().trim());
        boolean emailok=ControleSaisie.valiEmail(email.getText());
        boolean mdpok=ControleSaisie.validPassword(mdp.getText())&&(mdp.getText().length()>=8);
        boolean numtelok=ControleSaisie.isTel(numTel.getText());
        boolean villeok=ControleSaisie.isString(ville.getText().trim());
        boolean rueok=ControleSaisie.adresse(rue.getText().trim());
        boolean codeok=ControleSaisie.isNumber(codePostal.getText())&&codePostal.getText().length()==4;
        boolean salaireok=ControleSaisie.isNumber(salaire.getText());
        boolean heureok=ControleSaisie.isNumber(heure.getText());
        if(prenomok && nomok && emailok && mdpok && numtelok && villeok && villeok && rueok && codeok && salaireok && heureok && date.getValue()!=null)
        {
            if(mdp.getText().equals(mdpc.getText()))
            {
                AdminService a=new AdminService();
                if(!a.existe(email.getText().toLowerCase(), mdp.getText(),username.getText().toLowerCase()))
                {
                    int year=date.getValue().getYear()-1900;
                    int mounth=date.getValue().getMonthValue()-1;
                    int day=date.getValue().getDayOfYear();
                    Date d= new Date(year,mounth,day);
                    
                    Employe e= new Employe(d, new Float(salaire.getText()) ,new Integer(heure.getText()), type.getSelectionModel().getSelectedItem(),region.getSelectionModel().getSelectedItem(),ville.getText(),rue.getText(),codePostal.getText(),nom.getText(),prenom.getText(),email.getText(), mdp.getText(), chemin, Integer.parseInt(numTel.getText()));
                    e.setEmail_canonical(email.getText().toLowerCase());
                    e.setUsername(username.getText());
                    e.setUsername_canonical(username.getText().toLowerCase());
                    String passwd=servicebcrypt.hashpw(mdp.getText(), staticvar.SALT);
                    e.setPasword(passwd);
                    System.out.println(e);
                    EmployeService es =new EmployeService();
                    es.insert(e);
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
    private void Supprimer(ActionEvent event) {
        EmployeService es=new EmployeService();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            if(tableView.getSelectionModel().getSelectedItem().getBloque().equals("Actif"))
            {
                es.majEtat(0, tableView.getSelectionModel().getSelectedItem().getId_User());
            }
            else
            {
                es.majEtat(1, tableView.getSelectionModel().getSelectedItem().getId_User());
            }
            tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("im"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                
                TableColumn<EmployeAffichage,Integer> NbHeuresColumn=new TableColumn<>("Nb heures");
                NbHeuresColumn.setMinWidth(100);
                NbHeuresColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Integer>("NbHeures"));
                TableColumn<EmployeAffichage,Float> Salaire=new TableColumn<>("Salaire");
                Salaire.setMinWidth(100);
                Salaire.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Float>("Salaire"));
                TableColumn<EmployeAffichage,String> BloqueColumn=new TableColumn<>("Bloque");
                BloqueColumn.setMinWidth(100);
                BloqueColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("Bloque")); 
                
                tableView.setItems(getEmployes());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,NbHeuresColumn,Salaire,BloqueColumn);
        }
        
    }

    @FXML
    private void TabChange(Event event) {
        tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("im"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                
                TableColumn<EmployeAffichage,Integer> NbHeuresColumn=new TableColumn<>("Nb heures");
                NbHeuresColumn.setMinWidth(100);
                NbHeuresColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Integer>("NbHeures"));
                TableColumn<EmployeAffichage,Float> Salaire=new TableColumn<>("Salaire");
                Salaire.setMinWidth(100);
                Salaire.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,Float>("Salaire"));
                TableColumn<EmployeAffichage,String> BloqueColumn=new TableColumn<>("Bloque");
                BloqueColumn.setMinWidth(100);
                BloqueColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("Bloque"));
                
                tableView.setItems(getEmployes());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,NbHeuresColumn,Salaire,BloqueColumn);
        
    }
   /* private void Supprimer(ActionEvent event) {
        /*ParentService ps=new ParentService();
        if(tableView.getSelectionModel().getSelectedItem()!=null)
        {
            ps.delete(tableView.getSelectionModel().getSelectedItem().getId_User());
            tableView.getColumns().remove(0, tableView.getColumns().size());
                tableView.getItems().clear();
                TableColumn<EmployeAffichage,ImageView> imagesColumn=new TableColumn<>("Image");
                imagesColumn.setMinWidth(100);
                imagesColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,ImageView>("Image"));
                TableColumn<EmployeAffichage,String> NomColumn=new TableColumn<>("Nom");
                NomColumn.setMinWidth(100);
                NomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("nom"));
                TableColumn<EmployeAffichage,String> PrenomColumn=new TableColumn<>("Prenom");
                PrenomColumn.setMinWidth(100);
                PrenomColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("prenom"));
                TableColumn<EmployeAffichage,String> EmailColumn=new TableColumn<>("Email");
                EmailColumn.setMinWidth(150);
                EmailColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("Email"));
                TableColumn<EmployeAffichage,String> NumTelColumn=new TableColumn<>("NumTel");
                NumTelColumn.setMinWidth(100);
                NumTelColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("NumTel"));
                TableColumn<EmployeAffichage,String> NbEnfantsColumn=new TableColumn<>("NBEnfants");
                NbEnfantsColumn.setMinWidth(100);
                NbEnfantsColumn.setCellValueFactory(new PropertyValueFactory<EmployeAffichage,String>("NbEnfant"));
                
                tableView.setItems(getParents());
                tableView.getColumns().addAll(imagesColumn,NomColumn,PrenomColumn,EmailColumn,NbEnfantsColumn,NumTelColumn);*/
    //}

 @FXML
    private void gestionParents(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionParents.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEmploye(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("GestionEmploye.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("reponse.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void Evenement(MouseEvent event) throws IOException {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("Evenements.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    /**/

    @FXML
    private void SignOut(MouseEvent event) throws IOException {
         FXMLLoader fxml= new FXMLLoader(getClass().getResource("Login.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }

    @FXML
    private void Activite(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("Actprog.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }


    @FXML
    private void CantineOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void CantineOn(MouseEvent event) {
        //info.setText("Gerer les chaises,tables et menus");
    }


    @FXML
    private void ActiviteOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ActiviteOn(MouseEvent event) {
        //info.setText("Gerer les activites");
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
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceAjouterMenu.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    
}
