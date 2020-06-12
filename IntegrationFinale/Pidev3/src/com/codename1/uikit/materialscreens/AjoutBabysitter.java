/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;


import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Validator;
import com.mycompany.myapp.entities.BabysitterComboBox;
import com.mycompany.myapp.entities.Babysitting;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.EnfantComboBox;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBabysitting;
import com.mycompany.myapp.services.ServiceEnfant;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
//import com.mycompany.Entite.Babysitting;
//import com.mycompany.Entite.Enfant;
//import com.mycompany.Entite.User;
//import com.mycompany.Services.ServiceBabysitting;
//import com.mycompany.Services.ServiceEnfant;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author HEDI MSELMI
 */
public class AjoutBabysitter extends SideMenuBaseForm {
    int w;
   Form AjouterBabysitter;
   EncodedImage enc;
    Image img;
    Picker s;
     String url="http://localhost/PidevF2/pidev/web/Images/";
    Button bt_ajout,bt_affiche,bt_chercher;
    Image profilePic ;
    TableLayout tl;
         int spanButton = 2;

    public AjoutBabysitter(Resources res,User u) {
         super(BoxLayout.y());
        this.u=u;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       
        
        
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
                                new Label("Ajout Babysitter", "WelcomeBlue")
                                
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
     Validator val = new Validator();


      /* ComboBox cb_typedem = new ComboBox("");
           cb_typedem.addItem("information sur les procédures");
           cb_typedem.addItem("information sur les garants");
           cb_typedem.addItem("information sur adhésion");
           cb_typedem.addItem(" prise en charge");
           cb_typedem.addItem(" devis");*/
   //  Home y=new Home(theme);
      
            //  y.getAjouterEnfant().show();
      //  getToolbar().addCommandToLeftBar("back", null, e -> y.getAjouterEnfant().show());
       
         Label l_heuredebut= new Label ("heure debut :");
         l_heuredebut.getAllStyles().setFgColor(0x000000);
         Label l_heurefin= new Label ("heure fin :");
          l_heurefin.getAllStyles().setFgColor(0x000000);
         Label l_joursemaine= new Label ("jour de  semaine :");
         l_joursemaine.getAllStyles().setFgColor(0x000000);
         Label l_prixheure= new Label ("prix heure :");
         l_prixheure.getAllStyles().setFgColor(0x000000);
       TextField tx_prixheure = new TextField("","Prix:",70, TextArea.NUMERIC);
        tx_prixheure.getAllStyles().setFgColor(0x4169E1);
      //  bt_ajout = new Button("ajouter");
      //  bt_ajout.getAllStyles().setBgColor(0x000000);
         bt_ajout= new Button("Ajout");
        bt_ajout.setUIID("LoginButton");
        bt_affiche=new Button("Affichage");
        bt_chercher=new Button("Chercher"); 
       Picker timePicker = new Picker();
       timePicker.getAllStyles().setFgColor(0x4169E1);
       timePicker.setType(Display.PICKER_TYPE_TIME);
       Picker timePicker1 = new Picker();
       timePicker1.getAllStyles().setFgColor(0x4169E1);
       timePicker1.setType(Display.PICKER_TYPE_TIME);
        
      // AjouterDemande.add(cb_typedem);
      Label l=new Label("Babysitter");
      l.getAllStyles().setFgColor(0x000000);
       Label l1=new Label("Enfant");
      l1.getAllStyles().setFgColor(0x000000);
       ArrayList<User> enfantsAll= ServiceUser.getInstance().getAllBabysitter();
        ArrayList<User> enfants=new ArrayList<>();
        for (User e:enfantsAll)
        {
            
                enfants.add(e);
            
        }
        ComboBox<BabysitterComboBox> t=new ComboBox<>();
        for(User e:enfants)
        {
            BabysitterComboBox ecb=new BabysitterComboBox(e);
            t.addItem(ecb);
        }
        
        ArrayList<Enfant> babysittersAll= ServiceEnfant.getInstance().getEnfantsPourParent(Statics.CONNECTED_USER.getIdUser());
        ArrayList<Enfant> babysitters=new ArrayList<>();
        for (Enfant e:babysittersAll)
        {
            
                babysitters.add(e);
            
        }
        ComboBox<EnfantComboBox> t1=new ComboBox<>();
        for(Enfant e:babysitters)
        {
            EnfantComboBox ecb1=new EnfantComboBox(e);
            t1.addItem(ecb1);
        }
        Container Reservations=new Container(BoxLayout.y());
       // add(CBenfants);
     // ComboBox t=new ComboBox(CBenfants);
      Container c1 = new Container(BoxLayout.x());
      add(l);
          add(t);
          add(l1);
          add(t1);
       add(l_heuredebut);
        add(timePicker);
       add(l_heurefin);
        add(timePicker1);
        Container c2 = new Container(BoxLayout.x());
        CheckBox lundi= new CheckBox("lundi");
        lundi.getAllStyles().setFgColor(0x4169E1);
        CheckBox mardi= new CheckBox("mardi");
        mardi.getAllStyles().setFgColor(0x4169E1);
        CheckBox mercredi= new CheckBox("mercredi");
         mercredi.getAllStyles().setFgColor(0x4169E1);
        CheckBox jeudi= new CheckBox("jeudi");
        jeudi.getAllStyles().setFgColor(0x4169E1);
        CheckBox vendredi= new CheckBox("vendredi");
        vendredi.getAllStyles().setFgColor(0x4169E1);
        c2.add(lundi);
        c2.add(mardi);
        c2.add(mercredi);
        c2.add(jeudi);
        Container s = new Container(BoxLayout.x());
        s.add(vendredi);
        add(l_joursemaine);
        add(c2);
        add(s);
        add(l_prixheure);
        add(tx_prixheure);
          
          
          c1.add(bt_ajout);
       //   c1.add(bt_affiche);
        //  c1.add(bt_chercher);
        //  Button affiche = new Button("afficher");
        //  c1.add(affiche);
     //   affiche.setUIID("Title");
       // FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU); 
       // affiche.addActionListener(e -> new afficheBabysitting(res,this.u).getAfficheBabysitting().show());
        add(c1);
        
           Button reserver=new Button("Reserver");
                reserver.setUIID("Reserver");
            bt_ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tx_prixheure.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   
        
       
      
              String s="";
        if(lundi.isSelected())
                { s+="lundi";}
        if(mardi.isSelected())
        {s+=" mardi";}
        if(mercredi.isSelected())
        {s+=" mercredi";}
        if(jeudi.isSelected())
        {s+=" jeudi";}
        if(vendredi.isSelected())
        {s+=" vendredi";}
                        
                  int test= ServiceUser.getInstance().getIdBabysitter(t.getSelectedItem().getUsername()).getIdUser();
                  int test1= ServiceEnfant.getInstance().getIdEnfant(t1.getSelectedItem().getNom()).getId_enfant();
                        System.out.println("test::: "+test);
                         Babysitting t = new Babysitting(test, (int) timePicker.getValue(),(int) timePicker1.getValue(),s,Integer.parseInt(tx_prixheure.getText()),new Enfant(12));
                         ServiceBabysitting.getInstance().AjoutBabysitter(t,test,test1);
                        //  afficheBabysitting aff= new afficheBabysitting();
                             //   aff.getAfficheBabysitting().show();
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                     
                       // afficheBabysitting aff= new afficheBabysitting();
                            //    aff.getAfficheBabysitting().add(Integer.toString(13));
                        
        
        
       
                
                
                
            }
       
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

    public Form getAjouterBabysitter() {
        return AjouterBabysitter;
    }

    public void setAjouterBabysitter(Form AjouterBabysitter) {
        this.AjouterBabysitter = AjouterBabysitter;
    }

    public Picker getS() {
        return s;
    }

    public void setS(Picker s) {
        this.s = s;
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

    public Button getBt_chercher() {
        return bt_chercher;
    }

    public void setBt_chercher(Button bt_chercher) {
        this.bt_chercher = bt_chercher;
    }

    public TableLayout getTl() {
        return tl;
    }

    public void setTl(TableLayout tl) {
        this.tl = tl;
    }

    public int getSpanButton() {
        return spanButton;
    }

    public void setSpanButton(int spanButton) {
        this.spanButton = spanButton;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }
    
   

    @Override
    protected void showOtherForm(Resources res) {
       //To change body of generated methods, choose Tools | Templates.
    }
    
 
    

    
   


}
