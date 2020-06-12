/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;




import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import entity.Activite;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import static javax.swing.text.html.HTML.Attribute.DIR;
import Service.ServiceActivite;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import util.Connexion;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutActiviteController implements Initializable {
     private Connection con=null;
    private PreparedStatement pst=null;
    
    private ResultSet rs=null;
    private String directorio;
    @FXML
    private TextField TxtIntitule;
    @FXML
    private TextField TxtNiveau;
    @FXML
    private ComboBox<String> TxtResponsable;
        private ObservableList<String> listResp;

    @FXML
    private ComboBox<String> TxtType;
    ObservableList<String> list=FXCollections.observableArrayList("Education","Loisirs");
    @FXML   
    private Button Ajouter;
    @FXML
    private JFXTimePicker TxtDebut;
    @FXML
    private JFXTimePicker TxtFin;
    @FXML
    private Button SECOND;
    @FXML
    private ImageView qrcode;
    @FXML
    private Button genererqr;
    @FXML
    private Button Retour;
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
    private HBox Activite;
    @FXML
    private HBox SignOut;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    con=Connexion.getInstance().getCnx();

         listResp=FXCollections.observableArrayList();
   try {
            pst=con.prepareStatement("select Nom from user where TypeEmploye='Activite'");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listResp.add(rs.getString(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
     directorio= new File("").getAbsolutePath();
     directorio +=File.separator + DIR ;
     File file = new File(directorio);
     if(!file.isDirectory())
         
     {
     
     file.mkdir();
     }
        TxtType.setItems(list);
        TxtResponsable.setItems(listResp); 

        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceActivite  sa=new ServiceActivite();
//        if ((TxtIntitule.equals(""))||(TxtNiveau.equals("")))
//               JOptionPane.showMessageDialog(null,"eurr effectué");
//        else
if((TxtIntitule.getText().equals(""))||(TxtNiveau.getText().equals(""))||(TxtResponsable.getValue() == null)||(TxtType.getValue() == null )|| (TxtDebut.getValue()==null)|| (TxtFin.getValue()==null))
        {           JOptionPane.showMessageDialog(null,"Un champ manquant");}
else
{  sa.ajouterActivite(new Activite(TxtIntitule.getText(), TxtNiveau.getText(), TxtResponsable.getValue(), TxtType.getValue(),TxtDebut.getValue().toString(),TxtFin.getValue().toString()));
   
    
    
    
    JOptionPane.showMessageDialog(null,"Ajout effectué");
}       
    //    FXMLLoader fxml=new FXMLLoader(getClass().getResource("Recuperation.fxml"));
      //  Parent root=fxml.load();
        //TxtNom.getScene().setRoot(root);
        //RecuperationController rc=fxml.getController();
        //rc.setLabelnom(TxtNom.getText());
        //rc.setLabelprenom(TxtPrenom.getText());
    
    }

    @FXML
    private void SECOND(ActionEvent event) throws IOException {
    
        AnchorPane pane=FXMLLoader.load(getClass().getResource("AfficherActivite.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
      
        //rootPane.getChildren().setAll(pane);
    }   

    @FXML
    private void genereqr(ActionEvent event) throws IOException {
        
        
        String Intitule=TxtIntitule.getText();
        if( !Intitule.isEmpty())
        {
        try {
            String qr="";
        qr+="Intitule : "+TxtIntitule.getText()+"\n";
        qr+="Niveau : "+TxtNiveau.getText()+"\n";
        qr+="Responsable : "+TxtResponsable.getValue()+"\n";
        qr+="Type : "+TxtType.getValue()+"\n";
        qr+="Debut : "+TxtDebut.getValue()+"\n";
        qr+="Fin : "+TxtFin.getValue()+"\n";
                FileOutputStream fout= new FileOutputStream(directorio +File.separator+ Intitule +".png");
                ByteArrayOutputStream bos=QRCode.from(qr).withSize(125,125).to(ImageType.PNG).stream();           
                fout.write(bos.toByteArray());
                bos.close();
                fout.close();
                fout.flush();
                Image image=new Image(new FileInputStream(directorio +File.separator+ Intitule +".png"));
                qrcode.setImage(image);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutActiviteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        

        }
        
        
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
           AnchorPane pane=FXMLLoader.load(getClass().getResource("ActProg.fxml"));
        Scene scene2=new Scene(pane);
        Stage Window=(Stage)((Node)event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
    }

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
    private void ParentOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void ParentOn(MouseEvent event) {
        //info.setText("Gerer les parents");
    }

    @FXML
    private void EmployeOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EmployeOn(MouseEvent event) {
        //info.setText("Gerer les employes");
    }

    @FXML
    private void EnfantOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EnfantOn(MouseEvent event) {
        //info.setText("Gerer les enfants");
    }

    @FXML
    private void EvenementOff(MouseEvent event) {
        //info.setText("");
    }

    @FXML
    private void EvenementOn(MouseEvent event) {
        //info.setText("Gerer les evenements");
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
