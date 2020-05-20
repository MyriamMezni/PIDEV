/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.capture.Capture;
import com.codename1.components.OnOffSwitch;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceEnfant;
import com.mycompany.myapp.utils.Statics;
import static com.mycompany.myapp.utils.Statics.CONNECTED_USER;


import java.io.IOException;
import java.util.Date;



/**
 *
 * @author HEDI MSELMI
 */
public class AjouterEnfant extends SideMenuBaseForm {
    Form AjouterDemande;
    String url="http://localhost/PidevF2/pidev/web/Images/";
    TextField tx_typedem;
    TextField tx_descriptiondem;
    TextField tx_maildem;
    Picker s;
    User u;
    Resources res;
    Button bt_ajout,bt_affiche,bt_chercher;
    TableLayout tl;
         int spanButton = 2;
Image profilePic ;
    public AjouterEnfant(Resources res,User u) {
   super(BoxLayout.y());
        this.u=u;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
         
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
          
         img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic = img;
        //Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
              //  add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label("Ajout Enfant", "WelcomeBlue")
                                
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
     Validator val = new Validator();

//TextModeLayout tl = new TextModeLayout(3, 2);
         Label l_descriptiondem= new Label ("Nom :");
         l_descriptiondem.getAllStyles().setFgColor(0x000000);
        tx_descriptiondem = new TextField("","Nom:",70, TextArea.ANY);
        tx_descriptiondem.getAllStyles().setFgColor(0x4169E1);
     //   val.addConstraint(tx_descriptiondem, new NumericConstraint(false));
  //      val.getErrorMessageUIID();
        
         Label l_maildem= new Label ("Prenom :");
         l_maildem.getAllStyles().setFgColor(0x000000);
        tx_maildem = new TextField("","Prenom :", 100, TextArea.EMAILADDR);
        tx_maildem.getAllStyles().setFgColor(0x4169E1);
        Label t=new Label("Date");
        t.getAllStyles().setFgColor(0x000000);
         Picker s=new Picker();
         s.getAllStyles().setFgColor(0x4169E1);
         Label l_remarque= new Label ("Remarque :");
         l_remarque.getAllStyles().setFgColor(0x000000);
        TextArea tx_remarque = new TextArea(5, 20);
        tx_remarque.setHint("Remarque....");
        tx_remarque.getAllStyles().setFgColor(0x4169E1);
        Label l_image= new Label ("Image :");
        l_image.getAllStyles().setFgColor(0x000000);
        TextField tx_image = new TextField("","Image :", 100, TextArea.ANY);
        //tx_image.getAllStyles().setFgColor(0xFF0000);
         Label l_document= new Label ("Document :");
         l_document.getAllStyles().setFgColor(0x000000);
        TextField tx_document = new TextField("","Document :", 100, TextArea.ANY);
       // tx_document.getAllStyles().setFgColor(0xFF0000);
         Label l_cantine=new Label("Cantine");
         l_cantine.getAllStyles().setFgColor(0x000000);
        ComboBox cantine=new ComboBox(0,1);
        bt_ajout = new Button("ajouter");
        bt_ajout.getAllStyles().setBgColor(spanButton);
        bt_affiche=new Button("Image");
        bt_chercher=new Button("Document"); 
        OnOffSwitch genre= new OnOffSwitch();
      genre.setOn("1");
      genre.setOff("0");
        add(l_descriptiondem);
        add(tx_descriptiondem );
              add(l_maildem);
        add(tx_maildem);
        add(t);
        add(s);
        add(l_cantine);
         add(genre);
         add(l_remarque);
         add(tx_remarque);
         add(l_image);
         add(tx_image);
         add(l_document);
         add(tx_document);
          Container c1 = new Container(BoxLayout.x());
          c1.add(bt_ajout);
          c1.add(bt_affiche);
          c1.add(bt_chercher);
        add(c1);
      //  add(Content);
      bt_ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tx_descriptiondem.getText().length()==0)||(tx_maildem.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                  
                        int p;
                         if(genre.isValue()){
             p = 1;
      }
      else{
            p = 0;
      }
                        Enfant t = new Enfant(tx_descriptiondem.getText(), tx_maildem.getText(),(Date) s.getDate(),tx_remarque.getText(),tx_image.getText(),tx_document.getText(),Statics.CONNECTED_USER.getIdUser(),p);
                                ServiceEnfant.getInstance().AjoutEnfant(t,Statics.CONNECTED_USER.getIdUser());
                               Dialog.show("Success","Connection accepted",new Command("OK"));
                        
                        
                  //      else{Dialog.show("ERROR", "Server error", new Command("OK"));}
                }
                
                
            }
        });
       
          bt_affiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                String i=Capture.capturePhoto(200,200);
            Util.downloadImageToFileSystem(i, i);
           // Display.getInstance().execute(i);
                System.out.print("le path: "+i);
                
                  //  Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(i));
                    tx_image.setText(i);
                
                
            }
        });
                          bt_chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                String i=Capture.capturePhoto(200,200);
            Util.downloadImageToFileSystem(i, i);
           // Display.getInstance().execute(i);
                System.out.print("le path: "+i);
                
                  //  Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(i));
                    tx_document.setText(i);
                
                
            }
        }); 
            
        
//        
//        Slider noteSlider = new Slider(); 
//	    noteSlider.setEditable(true);
//	    noteSlider.setText(""); 
//	    noteSlider.setTextPosition(LEFT);
//	    noteSlider.setMinValue(0);
//	    noteSlider.setMaxValue(5);
//	    Button validate = new Button("validate");
//            
//            AjouterDemande.add(noteSlider);
//            AjouterDemande.add(validate);
//             
//            
//            noteSlider.addActionListener(new ActionListener() {
//			  @Override
//			  public void actionPerformed(ActionEvent evt) {
//				 noteSlider.setText("note "+noteSlider.getProgress());
//			  }
//		  });
//             validate.addActionListener(new ActionListener() {
//			  @Override
//			  public void actionPerformed(ActionEvent evt) {
//				  
//		        String displayNote = "vous avez attribué la note de "+noteSlider.getProgress(); 
//			Dialog.show("evaluation",displayNote,"OK", null); 
//				
//			  }
//		  });
//        
       
        setupSideMenu(res,this.u);
      
        
    }
    
 
    public Form getAjouterEnfant() {
        return AjouterDemande;
    }

    public void setAjouterDemande(Form AjouterDemande) {
        this.AjouterDemande = AjouterDemande;
    }

    public TextField getTx_typedem() {
        return tx_typedem;
    }

    public void setTx_typedem(TextField tx_typedem) {
        this.tx_typedem = tx_typedem;
    }

    public TextField getTx_descriptiondem() {
        return tx_descriptiondem;
    }

    public void setTx_descriptiondem(TextField tx_descriptiondem) {
        this.tx_descriptiondem = tx_descriptiondem;
    }

    public TextField getTx_maildem() {
        return tx_maildem;
    }

    public void setTx_maildem(TextField tx_maildem) {
        this.tx_maildem = tx_maildem;
    }

    public Button getBt_ajout() {
        return bt_ajout;
    }

    public void setBt_ajout(Button bt_ajout) {
        this.bt_ajout = bt_ajout;
    }

    public Button getBt_affiche() {
        return bt_affiche;
    }

    public void setBt_affiche(Button bt_affiche) {
        this.bt_affiche = bt_affiche;
    }

    public Picker getS() {
        return s;
    }

    public void setS(Picker s) {
        this.s = s;
    }

    public Button getBt_chercher() {
        return bt_chercher;
    }

    public void setBt_chercher(Button bt_chercher) {
        this.bt_chercher = bt_chercher;
    }

    @Override
    protected void showOtherForm(Resources res) {
      //To change body of generated methods, choose Tools | Templates.
    }

   

   


  

   

    

   

    
}
