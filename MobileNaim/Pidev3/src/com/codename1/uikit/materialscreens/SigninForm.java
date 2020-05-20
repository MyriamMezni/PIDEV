/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

/**
 *
 * @author ben younes
 */

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;

public class SigninForm extends Form{
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    Image profilePic ;
    String filename;
    public SigninForm(Resources res)
    {
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        try {
            imgv=new ImageViewer(Image.createImage("/giphy.gif"));
        } catch (IOException ex) {
            
        }
        Container titleCmp = BoxLayout.encloseY(
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(" ", "Title"),
                                    new Label("  ", "SubTitle")
                                )
                            )
                        //GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab= FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        Container UsernameGroup=new Container(BoxLayout.x());
        Container EmailGroup=new Container(BoxLayout.x());
        Container PassWordGroup=new Container(BoxLayout.x());
        Container CPassWordGroup=new Container(BoxLayout.x());
        Container PrenomGroup=new Container(BoxLayout.x());
        Container NomGroup=new Container(BoxLayout.x());
        Container ImageGroup=new Container(BoxLayout.x());
        Container NumTelGroup=new Container(BoxLayout.x());
        Container RegionGroup=new Container(BoxLayout.x());
        Container VilleGroup=new Container(BoxLayout.x());
        Container RueGroup=new Container(BoxLayout.x());
        Container CodePostalGroup=new Container(BoxLayout.x());
        Container DateDeNaissanceGroup=new Container(BoxLayout.x());
        TextField username=new TextField();
        username.setUIID("InputText");
        TextField email=new TextField();
        email.setUIID("InputText");
        TextField password=new TextField("","",20,TextField.PASSWORD);
        password.setUIID("InputText");
        TextField Cpassword=new TextField("","",20,TextField.PASSWORD);
        Cpassword.setUIID("InputText");
        TextField prenom=new TextField();
        prenom.setUIID("InputText");
        TextField nom=new TextField();
        nom.setUIID("InputText");
        Button image=new Button("Parcourir...");
        image.setUIID("Parcourir");
        TextField numtel=new TextField();
        numtel.setUIID("InputText");
        ComboBox<String> Region=new ComboBox<>();
        Region.addItem("Tunis");
        Region.addItem("Ariana");
        Region.addItem("Ben Arous");
        TextField ville=new TextField();
        ville.setUIID("InputText");
        TextField rue=new TextField();
        rue.setUIID("InputText");
        TextField codepostal=new TextField();
        codepostal.setUIID("InputText");
        Picker datedenaissance=new Picker();
        datedenaissance.setType(Display.PICKER_TYPE_DATE);
        datedenaissance.setUIID("InputText");
        UsernameGroup.add(new Label("UserName","InputLabel"));
        UsernameGroup.add(username);
        
        
        EmailGroup.add(new Label("Email","InputLabel"));
        EmailGroup.add(email);
        
        PassWordGroup.add(new Label("Password","InputLabel"));
        PassWordGroup.add(password);
        
        CPassWordGroup.add(new Label("Confirmer Password","InputLabel"));
        CPassWordGroup.add(Cpassword);
        
        PrenomGroup.add(new Label("Prenom","InputLabel"));
        PrenomGroup.add(prenom);
        
        NomGroup.add(new Label("Nom","InputLabel"));
        NomGroup.add(nom);
        
        DateDeNaissanceGroup.add(new Label("Date de naissance","InputLabel"));
        DateDeNaissanceGroup.add(datedenaissance);
        
        ImageGroup.add(new Label("Image","InputLabel"));
        ImageGroup.add(image);
        ImageGroup.add(imgv);
        
        RegionGroup.add(new Label("Region","InputLabel"));
        RegionGroup.add(Region);
        
        VilleGroup.add(new Label("Ville","InputLabel"));
        VilleGroup.add(ville);
        
        RueGroup.add(new Label("Rue","InputLabel"));
        RueGroup.add(rue);
        
        CodePostalGroup.add(new Label("Code postal","InputLabel"));
        CodePostalGroup.add(codepostal);
        
        NumTelGroup.add(new Label("Numero de telephone","InputLabel"));
        NumTelGroup.add(numtel);
        add(PrenomGroup);
        add(NomGroup);
        add(UsernameGroup);
        add(EmailGroup);
        add(PassWordGroup);
        add(CPassWordGroup);
        add(NumTelGroup);
        add(ImageGroup);
        add(DateDeNaissanceGroup);
        add(RegionGroup);
        add(VilleGroup);
        add(RueGroup);
        add(CodePostalGroup);
        image.addActionListener((e1) -> {
            Display.getInstance().openImageGallery((e2) -> {
                String filePath=(String)e2.getSource();
                System.out.println(filePath);
                filename=filePath.substring(filePath.lastIndexOf("/")+1);
                Image test = null;
                try {
                        test = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                        System.out.println(filename);
                        //byte[] buffer=new byte[1024];
                        /*byte[] buffer = new byte[FileSystemStorage.getInstance().openInputStream(filePath).available()];
                        String path="111.png";
                        OutputStream outStream = Storage.getInstance().createOutputStream(path);
                        int length=0;
                        System.out.println(outStream.toString());
                        length=FileSystemStorage.getInstance().openInputStream(filePath).read(buffer);
                        
                            outStream.write(buffer,0,length);
                            System.out.println(length);*/
                            
                        imgv.setImage(test);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                
            });
        });
        fab.addActionListener((evt) -> {
            Boolean passwordok=true;
            Boolean imageok=true;
            Boolean usernameok=true;
            Boolean emailok=true;
            Boolean prenomok=true;
            Boolean nomok=true;
            Boolean numtelok=true;
            Boolean Villeok=true;
            Boolean CodePostalok=true;
            Boolean rueok=true;
            
           if(prenom.getText()==null || prenom.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un prenom","ok",null);
               prenomok=false;
           }
           
           if(nom.getText()==null || nom.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un nom","ok",null);
               nomok=false;
           }
            
           if(username.getText()==null || username.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un username","ok",null);
               usernameok=false;
           }
            
           
           
           if(email.getText()==null || email.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer une adresse mail","ok",null);
               emailok=false;
           }
            
            
            
           if(password.getText()==null || password.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un mot de passe","ok",null);
               passwordok=false;
           }
           else
           {
               if(password.getText().equals(Cpassword.getText())==false)
               {
                   Dialog.show("Erreur", "Veulliez confirmer votre mot de passe","ok",null);
                   passwordok=false;
                   System.out.println(password.getText());
                   System.out.println(Cpassword.getText());
               }
           }
           
           if(filename==null)
           {
               Dialog.show("Erreur", "Veuillez choisir une image","ok",null);
               imageok=false;
           }
           
           if(numtel.getText()==null || numtel.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un numero de téléphone valide","ok",null);
               numtelok=false;
           }
           
           if(ville.getText()==null || ville.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer une ville valide","ok",null);
               Villeok=false;
           }
           
           if(codepostal.getText()==null || codepostal.getText().trim()=="")
           {
               Dialog.show("Erreur", "Veuillez entrer un code potal valide valide","ok",null);
               CodePostalok=false;
           }
           
           if(prenomok && nomok && usernameok && emailok && passwordok && imageok && numtelok && Villeok && CodePostalok)
           {
               if(Dialog.show("Confirmer", "Creer un compte?", "Confirmer","Annuler"))
               {
                    String numT=numtel.getText();
                    Integer num=Integer.parseInt(numT);
                    Integer code=Integer.parseInt(codepostal.getText());
                    User u1=new User(0, username.getText(), email.getText(),password.getText(), nom.getText(),prenom.getText(),filename,num, datedenaissance.getDate(), Region.getSelectedItem(), ville.getText(),rue.getText(),code);
                    System.out.println(Statics.BASE_URL + "parsing/modifyuser/"+u1.generateurl());
                    System.out.println(u1);
                    if(ServiceUser.getInstance().addUser(u1))
                    {
                        Dialog.show("Success","Votre compte est ajouté!","ok",null);
                        new LoginForm(res).show();
                    }
                    else
                    {
                       Dialog.show("Erreur","Une erreur est survenue","ok",null); 
                    }
                   
               }
               
           }
           
        });
        
    }
}
