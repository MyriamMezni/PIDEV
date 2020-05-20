/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceEnfant;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends SideMenuBaseForm{
      Button bt_chercher;
      Button bt_SMS;
       String url="http://localhost/PidevF2/pidev/web/Images/";
      EncodedImage enc;
      Image profilePic ;
    Image img;
       User u;
       public static final String ACCOUNT_SID = "ACd4fcc9cd693d2182ef7aa8cb9e5d5122"; //fait
  public static final String AUTH_TOKEN = "b0f09ee62b893173ff005523d547be75";
    public ListTasksForm(Resources res,User u) throws IOException {
         this.u=u;
         url=url+u.getImage();
         Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
          img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic = img;
      //  Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
         Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU); 
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        add(menuButton);
        
        setTitle("Affiche Enfant");
         bt_chercher=new Button("mail");
          bt_SMS=new Button("SMS");
        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceEnfant.getInstance().getListReclamations().get(serviceDemande.getListReclamations().size()-1).getNom().toString());
       // sp.setText(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getImage());
        add(sp);
        
        String i = ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getImage();
        Image img = Image.createImage(FileSystemStorage.getInstance().openInputStream(i));
        img.scaledWidth(20);
        Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label l_nom = new Label(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getNom());
        Label l_prenom = new Label(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getPrenom());
       //  Date l_datenaissance = new Date(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getDatenaissance());
        Label l_remarque = new Label(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getRemarque());
        //  Label l_remarque = new Label(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getRemarque());
           Label l_cantine = new Label(Integer.toString(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getCantine()));
         
           Label s=new Label("Nom ");
          s.getAllStyles().setFgColor(0x000000);
          C2.add(s);
         l_nom.getAllStyles().setFgColor(0xFF0000);
          C2.add(l_nom);
          l_prenom.getAllStyles().setFgColor(0xFF0000);
          Label s1=new Label("Prenom: ");
          s1.getAllStyles().setFgColor(0x000000);
          C2.add(s1);
           C2.add(l_prenom);
        //   C2.add("Date naissance :");
         //  C2.add(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getDatenaissance().getDay());
            l_remarque.getAllStyles().setFgColor(0xFF0000);
           Label s2=new Label("Remarque: ");
          s2.getAllStyles().setFgColor(0x000000);
          C2.add(s2);
           C2.add(l_remarque);
           l_cantine.getAllStyles().setFgColor(0xFF0000);
           Label s3=new Label("Cantine: ");
          s3.getAllStyles().setFgColor(0x000000);
          C2.add(s3);
           System.out.println("***"+l_cantine.getText());
           if(l_cantine.getText().equals("1"))
           {  Label r=new Label("oui");
              r.getAllStyles().setFgColor(0xFF0000);
               C2.add(r); }
           else{ 
               Label r1=new Label("non");
              r1.getAllStyles().setFgColor(0xFF0000);
               C2.add(r1); }
            Container kk = new Container(new BoxLayout(BoxLayout.X_AXIS));
         
          C1.add(img);
            kk.add(bt_chercher);
           kk.add(bt_SMS);
     //      kk.add(C2);
          C2.add(kk);
          
          add(C1);
          add(C2);
          
           // Container YY = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           // YY.add(bt_SMS);
          //  add(YY);
     //   Display.getInstance().execute(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getImage())
       
			  
                             
                              //Toolbar tb1 = .getToolbar(); 
			    getToolbar().addCommandToOverflowMenu("Déconnecter",null, new ActionListener() {
					      @Override
					      public void actionPerformed(ActionEvent evt) {
			                          //  Connecter Connecter=new Connecter();
                                                 //   Connecter.getConnecter().show();
////				 
////				       Toolbar tb4 = AjouterReclamation.getToolbar();
				       
				     
		                     //  AjouterDemande.show();
                             
                                     
				   }
			   }); 
                             bt_chercher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
               TextField tf = new TextField("", "Objet", 20, TextField.ANY);
               tf.getAllStyles().setFgColor(0x4169E1);
    TextArea body = new TextArea(5, 20);
    body.setHint("Mail.........");
    body.getAllStyles().setFgColor(0x4169E1);
    Command ok = new Command("OK");
    Command cancel = new Command("Cancel");
    Command result = Dialog.show("Espace de Mail", BorderLayout.north(tf).add(BorderLayout.CENTER, body), ok, cancel);
    if(ok == result) {
            Message m = new Message(body.getText());
                String textAttachmentUri = "il y'a un probleme";
m.getAttachments().put(textAttachmentUri, "text/plain");
                String imageAttachmentUri = "testina";
m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"mohamedmselmi407@gmail.com"}, tf.getText(), m);
            }
        }

           
                          }); 
                             
                              bt_SMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
            //   TextField tf = new TextField("", "File Name", 20, TextField.ANY);
    TextArea body = new TextArea(5, 20);
    body.setHint("File Body");
    body.getAllStyles().setFgColor(0x4169E1);
    Command ok = new Command("OK");
    Command cancel = new Command("Cancel");
    Command result = Dialog.show("Espace d'SMS", BorderLayout.north(body), ok, cancel);
    if(ok == result) {
        
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
                .creator(new PhoneNumber("+21656239572"), // to
                        new PhoneNumber("+12058439203"), // from
                        body.getText()).create();

        System.out.println(message.getSid());
            }
        }

           
                          }); 
                     setupSideMenu(res,this.u);      
    }

    @Override
    protected void showOtherForm(Resources res) {
       //To change body of generated methods, choose Tools | Templates.
    }
}
    
    

