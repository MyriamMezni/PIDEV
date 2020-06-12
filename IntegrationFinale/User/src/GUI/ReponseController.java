/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Service.AdminService;
import entity.Enfant;
import entity.JavaMailRefuse;
import entity.JavaMailUtil;
import entity.User;
import Service.ParentService;
import Service.ServiceEnfant;
import Service.chaiseservice;
import util.Connexion;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Desktop;
import java.awt.print.PageFormat;
import java.io.BufferedInputStream;
import java.io.File;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javax.swing.text.Document;
import util.staticvar;

/**
 * FXML Controller class
 *
 * @author HEDI MSELMI
 */
public class ReponseController implements Initializable {
    
ObservableList<String> list;


    @FXML
    private TableView<Enfant> table;
    @FXML
    private TableColumn<Enfant, String> nom;
    @FXML
    private TableColumn<Enfant, String> prenom;
    @FXML
    private TableColumn<Enfant, String> datenaissance;
    @FXML
    private TableColumn<Enfant, String> remarque;
    @FXML
    private TableColumn<Enfant, String> image;
    @FXML
    private TableColumn<Enfant, String> parent;
    @FXML
    private TableColumn<Enfant, String> cantine;

    
     ObservableList<String> list1;
    ObservableList<Enfant> oblist= FXCollections.observableArrayList();
   private final ObservableList<Enfant> t = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;

    @FXML
    private TextField nom1;
    @FXML
    private TextField prenom1;
    @FXML
    private DatePicker datenaissance1;
    @FXML
    private TextArea remarque1;
   
    @FXML
    private TextField image1;
    
    private ComboBox parent1;
   
    @FXML
    private ComboBox cantine1;
    @FXML
    private ImageView imagepreview;
    @FXML
    private TableColumn<Enfant, String> document;
    @FXML
    private TableColumn<Enfant, String> status;
    @FXML
    private HBox gestionParent;
    @FXML
    private HBox gestionEmploye;
    @FXML
    private HBox gestionEnfants;
    @FXML
    private TitledPane cantine2;
    @FXML
    private HBox Chaise;
    @FXML
    private HBox Table;
    @FXML
    private HBox Menu;
    @FXML
    private HBox evenements;
    @FXML
    private HBox Activite;
    @FXML
    private HBox SignOut;
    private String imagepath;
    private String pdfpath;
   
  
    
 
    
    
    @FXML
    private void afficheTable(ActionEvent event) throws SQLException {
       // Connection con = Connexion.getInstance().getCnx();
       
ObservableList<Enfant> data = table.getItems();
        //instansiation du service
        ServiceEnfant s=new ServiceEnfant();
        AdminService k=new AdminService();
        // suppressesion de la base de données
         JavaMailRefuse.sendMail(k.SelectEmail(table.getSelectionModel().getSelectedItem().getId_parent().getId_User()));
        s.delete(table.getSelectionModel().getSelectedItem().getId_enfant());
          table.setItems(FXCollections.observableArrayList(s.readAll()));
      
        //remplissage list
        Connection con = Connexion.getInstance().getCnx();
        try {
            // System.out.println("****"+table.getItems().get(7));
            ResultSet rs=con.createStatement().executeQuery("select * from enfant ");
            while(rs.next()){
                oblist.add(new Enfant(rs.getInt(1), rs.getString("nom"), rs.getString(3), rs.getDate("datenaissance"), rs.getString("remarque"), rs.getString("image"),rs.getString("document"),new User(rs.getInt("id_parent")), rs.getString("cantine")));
            }
        } catch (SQLException ex) {
            
        }
        
    }
     public ReponseController() throws SQLException {
          
        ServiceEnfant t= new ServiceEnfant();
  //      this.list = FXCollections.observableArrayList(t.reponse());
        
        this.list1 = FXCollections.observableArrayList("oui", "non");
    }
     
     
             
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ParentService t= new ParentService();
        this.list = FXCollections.observableArrayList(t.displayAllParId());
        //parent1.setItems(list);
        cantine1.setItems(list1);
        Connection con = Connexion.getInstance().getCnx();
        try {
            // System.out.println("****"+table.getItems().get(7));
            ResultSet rs=con.createStatement().executeQuery("select * from enfant ");
            while(rs.next()){
                oblist.add(new Enfant(rs.getInt(1), rs.getString("nom"), rs.getString(3), rs.getDate("datenaissance"), rs.getString("remarque"), rs.getString("image"),new User(rs.getInt("id_parent")), rs.getString("cantine"),rs.getString("document"),rs.getString("status")));
            } //oblist.add(new Enfant(0, nom, prenom, datenaissance, remarque, image, document, id_parent, cantine))
        } catch (SQLException ex) {
            
        }
        //id.setCellValueFactory(new PropertyValueFactory<>("id_enfant"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        datenaissance.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        remarque.setCellValueFactory(new PropertyValueFactory<>("remarque"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        parent.setCellValueFactory(new PropertyValueFactory<>("id_parent"));
        cantine.setCellValueFactory(new PropertyValueFactory<>("cantine"));
        document.setCellValueFactory(new PropertyValueFactory<>("document"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(oblist);
        
        
        FilteredList<Enfant> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(enfant -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (enfant.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				else if (String.valueOf(enfant.getPrenom()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
                
                SortedList<Enfant> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
                
               
          
    }
     
    
    @FXML
    public void UploadEnfant(ActionEvent event) throws SQLException, IOException {
          
        FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        if(selectedFile !=null){
           image1.setText(selectedFile.toURI().toString());
            Image i= new Image(image1.getText());
            imagepreview.setImage(i);
            nom1.setText(nom.getText());
            String ext=selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
            int ran=(int)(Math.random()*10000);
            String pathran=ran+ext;
            System.out.println(pathran);
            File fo=new File(staticvar.Image_URL+pathran);
            staticvar.copyFileUsingStream(selectedFile,fo);
            imagepath=pathran;
        }
        
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        ObservableList<Enfant> data = table.getItems();
        ServiceEnfant s=new ServiceEnfant();
         ParentService w= new ParentService();
         chaiseservice cs=new chaiseservice();
         
       String id=table.getSelectionModel().getSelectedItem().getId_parent().getNom();
       if(cs.getChaiseParEnfant(table.getSelectionModel().getSelectedItem().getId_enfant())!=-1)
       {
           if(cantine1.getValue().equals("oui"))
           {
               Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,(String) cantine1.getValue(), "en attente");
               s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());
           }
           else
           {
               Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,(String) cantine1.getValue(), "en attente");
               s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());
               cs.majEnfantNull(cs.getChaiseParEnfant(table.getSelectionModel().getSelectedItem().getId_enfant()));
           }
       }
       else
       {
           if(cantine1.getValue().equals("oui"))
           {
               if(cs.chaiseVide())
               {
                   Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,(String) cantine1.getValue(), "en attente");
                   s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());
                   cs.majEnfant(cs.numChaiseVide(), table.getSelectionModel().getSelectedItem().getId_enfant());
                   
               }
               else
               {
                   Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,"non", "en attente");
                   s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());
               }
           }
           else
           {
                Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,(String) cantine1.getValue(), "en attente");
                s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());
               
           }
       }
           
        /*Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User(w.selectParNom(id)) ,(String) cantine1.getValue(), "en attente");
        s.update(t, table.getSelectionModel().getSelectedItem().getId_enfant());*/
           
        //id.setCellValueFactory(new PropertyValueFactory<>("id_enfant"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaissance.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
         remarque.setCellValueFactory(new PropertyValueFactory<>("remarque"));
         image.setCellValueFactory(new PropertyValueFactory<>("image"));
         parent.setCellValueFactory(new PropertyValueFactory<>("id_parent"));
         cantine.setCellValueFactory(new PropertyValueFactory<>("cantine"));
         status.setCellValueFactory(new PropertyValueFactory<>("status"));
          table.setItems(FXCollections.observableArrayList(s.readAll()));
         
       
    }

    @FXML
    private void inscrire(ActionEvent event) throws SQLException {
        AdminService s=new AdminService();
      ServiceEnfant k=new ServiceEnfant();
            AjouterEnfantController t=new AjouterEnfantController();
        JavaMailUtil.sendMail(s.SelectEmail(table.getSelectionModel().getSelectedItem().getId_parent().getId_User()));
       //  table.getSelectionModel().getSelectedItem().setStatus("valide");
       // Enfant t=new Enfant(nom1.getText(), prenom1.getText(), new java.sql.Date(datenaissance1.getValue().getYear()-1900,datenaissance1.getValue().getMonthValue()-1,datenaissance1.getValue().getDayOfMonth()), remarque1.getText(), imagepath, new User( (int) parent1.getValue()) ,(String) cantine1.getValue(),"valide");
      //  k.update(t, 614);
        Enfant p2 = new Enfant(table.getSelectionModel().getSelectedItem().getNom(),table.getSelectionModel().getSelectedItem().getPrenom(), new java.sql.Date(table.getSelectionModel().getSelectedItem().getDatenaissance().getYear(), table.getSelectionModel().getSelectedItem().getDatenaissance().getMonth(), table.getSelectionModel().getSelectedItem().getDatenaissance().getDay()), table.getSelectionModel().getSelectedItem().getRemarque(), table.getSelectionModel().getSelectedItem().getImage(), new User(table.getSelectionModel().getSelectedItem().getId_parent().getId_User()), table.getSelectionModel().getSelectedItem().getCantine(), "valide");
       k.update(p2, table.getSelectionModel().getSelectedItem().getId_enfant());
        //id.setCellValueFactory(new PropertyValueFactory<>("id_enfant"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         datenaissance.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
         remarque.setCellValueFactory(new PropertyValueFactory<>("remarque"));
         image.setCellValueFactory(new PropertyValueFactory<>("image"));
         parent.setCellValueFactory(new PropertyValueFactory<>("id_parent"));
         cantine.setCellValueFactory(new PropertyValueFactory<>("cantine"));
         status.setCellValueFactory(new PropertyValueFactory<>("status"));
    table.setItems(FXCollections.observableArrayList(k.readAll()));
    
   
    }
   
    @FXML
    private void afficheImage(ActionEvent event) {
        if(table.getSelectionModel().getSelectedCells()!=null)
         {   
             File fo=new File(staticvar.Image_URL+table.getSelectionModel().getSelectedItem().getImage());
             Image i=new Image(fo.toURI().toString());
             imagepreview.setImage(i); 
             nom1.setText(table.getSelectionModel().getSelectedItem().getNom());
             prenom1.setText(table.getSelectionModel().getSelectedItem().getPrenom());
            //datenaissance1.setValue(LocalDate.of(table.getSelectionModel().getSelectedItem().getDatenaissance().getYear()+1900, table.getSelectionModel().getSelectedItem().getDatenaissance().getMonth()+1, table.getSelectionModel().getSelectedItem().getDatenaissance().getDay()-2));
             remarque1.setText(table.getSelectionModel().getSelectedItem().getRemarque());
             image1.setText(table.getSelectionModel().getSelectedItem().getImage());
             imagepath=image1.getText();
//             parent1.setValue(table.getSelectionModel().getSelectedItem().getId_parent());
            
            
             if(table.getSelectionModel().getSelectedItem().getCantine().equals("1"))
             {
                 cantine1.setValue("oui");
             }
             else
             {
                 cantine1.setValue("non");
             }
             
         } else {
             
    }
    }

    @FXML
    private void download(ActionEvent event)throws IOException { 
        URL url = new URL(table.getSelectionModel().getSelectedItem().getImage());
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream("C:\\Users\\HEDI MSELMI\\Documents\\bonjour.jpg");

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
                
            
        
    Desktop d = Desktop.getDesktop();
    d.open(new File(table.getSelectionModel().getSelectedItem().getDocument()));
    }

    @FXML
    private void recherche(TouchEvent event) {
        
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

    @FXML
    private void BabySitter(ActionEvent event) throws IOException {
        FXMLLoader fxml= new FXMLLoader(getClass().getResource("AjoutBabySitter.fxml"));
            
        javafx.scene.Parent root=fxml.load();
            
        Chaise.getScene().setRoot(root);
    }
    
        
    

		
		
   
}
