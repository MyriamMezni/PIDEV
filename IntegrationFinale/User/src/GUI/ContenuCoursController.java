/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import util.Connexion;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ContenuCoursController implements Initializable {
  private Connection con=null;
    private ResultSet rs=null;

    private PreparedStatement pst=null;
    @FXML
    private ComboBox<String> TxtIntitule;
        private   ObservableList<String> listActivite;

    @FXML
    private TextArea area;
    @FXML
    private Button enzel;
    @FXML
    private Button charger;
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
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    @FXML
    private HBox Activite1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                     con=Connexion.getInstance().getCnx();
          listActivite=FXCollections.observableArrayList();

          try {
            pst=con.prepareStatement("select Intitule from programme");
            rs=pst.executeQuery();
            while(rs.next())
            {
            listActivite.add(rs.getString(1));
            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
TxtIntitule.setItems(listActivite);  
    }    

    private void open(ActionEvent event)  {
     BufferedReader reader;
        FileReader in;
        String line;
    String chemin="";
     try {
            pst=con.prepareStatement("select Cours from programme where Intitule=?");
            pst.setString(1,TxtIntitule.getValue());

            rs=pst.executeQuery();
            while(rs.next())
            {
            chemin=(rs.getString(1));
 FileWriter lu;
      try {
          lu = new FileWriter(chemin);
            BufferedWriter out=new BufferedWriter(lu);


out.write(area.getText());
out.close(); 
      } catch (IOException ex) {
          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
      }


            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      //  System.out.println(chemin);
//      try {
//          in= new FileReader(new File(chemin));
//          reader = new BufferedReader(in);
//          do {
//          
//          line=reader.readLine();
//          if (line!=null)
//          area.setText(area.getText().toString()+line+"\n");
//          } while(line!=null);
//           
//      } catch (FileNotFoundException ex) {
//          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      catch (IOException ex) {
//          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
//      }


      
      




    
    }

    @FXML
    private void enzel(ActionEvent event) {
     BufferedReader reader;
        FileReader in;
        String line;
    String chemin="";
     try {
            pst=con.prepareStatement("select Cours from programme where Intitule=?");
            pst.setString(1,TxtIntitule.getValue());

            rs=pst.executeQuery();
            while(rs.next())
            {
            chemin=(rs.getString(1));
                System.out.println(chemin);
 FileWriter lu;
      try {
          lu = new FileWriter(chemin);
            BufferedWriter out=new BufferedWriter(lu);


out.write(area.getText());
out.close(); 
      } catch (IOException ex) {
          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
      }


            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      //  System.out.println(chemin);
//      try {
//          in= new FileReader(new File(chemin));
//          reader = new BufferedReader(in);
//          do {
//          
//          line=reader.readLine();
//          if (line!=null)
//          area.setText(area.getText().toString()+line+"\n");
//          } while(line!=null);
//           
//      } catch (FileNotFoundException ex) {
//          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      catch (IOException ex) {
//          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
//      }


      
      




    }

    @FXML
    private void charger(ActionEvent event) {
    
    
    BufferedReader reader = null;
    FileReader in;
    String line;
    
    String chemin="";
     try {
            pst=con.prepareStatement("select Cours from programme where Intitule=?");
            pst.setString(1,TxtIntitule.getValue());

            rs=pst.executeQuery();
            while(rs.next())
            {
            chemin=(rs.getString(1));
 FileReader lu;
      try {
          lu = new FileReader(chemin);
            BufferedReader out=new BufferedReader(lu);
do{ 
    line=reader.readLine();
  //  if(line!=null)  
        area.setText(area.getText()+line+"\n");

}while(line!=null);
      } catch (IOException ex) {
          Logger.getLogger(ContenuCoursController.class.getName()).log(Level.SEVERE, null, ex);
      }


            

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
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
