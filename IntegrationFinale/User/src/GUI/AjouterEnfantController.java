/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import entity.Enfant;
import javafx.scene.control.TextField;
import Service.ServiceEnfant;

import entity.User;
import Service.ParentService;
import Service.chaiseservice;
import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import static jdk.nashorn.internal.runtime.Debug.id;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author HEDI MSELMI
 */
public class AjouterEnfantController implements Initializable {

    
    ObservableList<String> list1;
   private entity.Parent p;

    public void setP(entity.Parent p) {
        this.p = p;
        File f=new File(staticvar.Image_URL+this.p.getImage());
        Image i =new Image(f.toURI().toString());
        image1.setImage(i);
    }
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;
    @FXML
    private DatePicker txt3;
    @FXML
    private TextArea txt4;
    @FXML
    private TextField txt5;
    private ComboBox txt6;
    
    @FXML
    private Button txt8;
    
    private Button choix=new Button("choix");
    @FXML
    private Button image;
   
    @FXML
    private ComboBox txt7;
    @FXML
    private TextField txt9;
    @FXML
    private ImageView image1;
    @FXML
    private HBox gestionCompte;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private HBox cantine;
    @FXML
    private HBox evenements;
    @FXML
    private HBox Noter;
    @FXML
    private HBox SignOut;
    private String imagepath; 
    private String pdfpath;

    public AjouterEnfantController() throws SQLException {
        this.list1 = FXCollections.observableArrayList("oui", "non");
          
        ParentService t= new ParentService();
      //  this.list = FXCollections.observableArrayList(t.displayAllParId());
        
        
    }
    @FXML
    public void UploadEnfant(ActionEvent event) throws SQLException, IOException {
          
        FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        if(selectedFile !=null){
           txt5.setText(selectedFile.toURI().toString());
           String ext=selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            int ran=(int)(Math.random()*10000);
            String pathran=ran+ext;
            System.out.println(pathran);
            File fo=new File(staticvar.Image_URL+pathran);
            staticvar.copyFileUsingStream(selectedFile,fo);
            imagepath=pathran;
          //  Image i= new Image(imagepath);
          //  imagepreview.setImage(i);
        }
        
    }
    
    
  
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      //  txt6.setItems(list);
        txt7.setItems(list1);
    }   
    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
        ServiceEnfant S= new ServiceEnfant();
         ParentService w= new ParentService();
      // System.out.println("999"+ new User(w.selectParNom((String) txt6.getValue())));
        chaiseservice cs=new chaiseservice();
        if(txt7.getValue().equals("oui"))
        {
            if(cs.chaiseVide())
            {
                Enfant p1 = new Enfant(  txt1.getText(), txt2.getText(), new java.sql.Date(txt3.getValue().getYear()-1900,txt3.getValue().getMonthValue()-1,txt3.getValue().getDayOfMonth()),txt4.getText(), imagepath, pdfpath,p, (String) txt7.getValue());
                S.ajouter1(p1);
                cs.majEnfant(cs.numChaiseVide(), S.getLast());
                

            }
            else
            {
                Enfant p1 = new Enfant(  txt1.getText(), txt2.getText(), new java.sql.Date(txt3.getValue().getYear()-1900,txt3.getValue().getMonthValue()-1,txt3.getValue().getDayOfMonth()),txt4.getText(), imagepath, pdfpath,p, "non");
                S.ajouter1(p1);
            }
             /*Enfant p1 = new Enfant(  txt1.getText(), txt2.getText(), new java.sql.Date(txt3.getValue().getYear()-1900,txt3.getValue().getMonthValue()-1,txt3.getValue().getDayOfMonth()),txt4.getText(), imagepath, pdfpath,p, (String) txt7.getValue());
            S.ajouter1(p1);*/
            
        }
        else
        {
            Enfant p1 = new Enfant(  txt1.getText(), txt2.getText(), new java.sql.Date(txt3.getValue().getYear()-1900,txt3.getValue().getMonthValue()-1,txt3.getValue().getDayOfMonth()),txt4.getText(), imagepath, pdfpath,p, (String) txt7.getValue());
                S.ajouter1(p1);
        }
        
//      FXMLLoader fx=new FXMLLoader(getClass().getResource("reponse.fxml"));
//        Parent root=fx.load();
//        txt1.getScene().setRoot(root);
//        ReponseController rc=fx.getController();
       
        //            Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()); 
    }

    @FXML
    private void UploadPdf(ActionEvent event) throws IOException {
          FileChooser fc=new FileChooser();
          fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF (*.PDF, *.pdf)", "*.pdf","*.PDF"));
        File selectedFile=fc.showOpenDialog(null);
        
        if(selectedFile !=null){
           txt9.setText(selectedFile.toURI().toString());
            String ext=selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            int ran=(int)(Math.random()*10000);
            String pathran=ran+ext;
            System.out.println(pathran);
            File fo=new File(staticvar.Image_URL+pathran);
            staticvar.copyFileUsingStream(selectedFile,fo);
            pdfpath=pathran;
    }
    

  

    
    
    
    }

    @FXML
    private void gesionCompte(MouseEvent event) throws IOException {
       
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("ModiferCompteParent.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
            
        ModiferCompteParentController mcp= fxml.getController();

        mcp.setP(p);
            
    }

    @FXML
    private void gestionEnfants(MouseEvent event) throws IOException {
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        AjouterEnfantController EC=fxml.getController();
        EC.setP(p);
    }

    @FXML
    private void cantine(MouseEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("InterfaceReserverUnMenu.fxml"));
            
        Parent root=fxml.load();
            
        image.getScene().setRoot(root);
        InterfaceReserverUnMenuController irmc=fxml.getController();
        irmc.setP(p);
        
        
    }

    @FXML
    private void Evenement(MouseEvent event) throws IOException {
        
           FXMLLoader fxml= new FXMLLoader(getClass().getResource("InscrEvt.fxml"));
            
        Parent root=fxml.load();
            
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
