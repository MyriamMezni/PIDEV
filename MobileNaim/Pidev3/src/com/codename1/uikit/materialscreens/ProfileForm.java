/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.materialscreens;

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

/**
 * Represents a user profile in the app, the first form we open after the walkthru
 *
 * @author Shai Almog
 */
public class ProfileForm extends SideMenuBaseForm {
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    User u;
    Image profilePic ;
    String filename;
    public ProfileForm(Resources res,User u) {
        super(BoxLayout.y());
        this.u=u;
        System.out.println(Statics.CONNECTED_USER);
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic= img;
        imgv=new ImageViewer();
        imgv.setImage(img);
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());

        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU); 
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        /*Container remainingTasks = BoxLayout.encloseY(
                        new Label("12", "CenterTitle"),
                        new Label("remaining tasks", "CenterSubTitle")
                );
        remainingTasks.setUIID("RemainingTasks");
        Container completedTasks = BoxLayout.encloseY(
                        new Label("32", "CenterTitle"),
                        new Label("completed tasks", "CenterSubTitle")
        );
        completedTasks.setUIID("CompletedTasks");*/

        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton),
                        BorderLayout.centerAbsolute(
                                BoxLayout.encloseY(
                                    new Label(u.getUsername(), "Title"),
                                    new Label("  ", "SubTitle")
                                )
                            ).add(BorderLayout.WEST, profilePicLabel)
                        //GridLayout.encloseIn(2, remainingTasks, completedTasks)
                );
        
        FloatingActionButton fab= FloatingActionButton.createFAB(FontImage.MATERIAL_UPDATE);
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
        TextField username=new TextField(u.getUsername());
        username.setUIID("InputText");
        TextField email=new TextField(u.getEmail());
        email.setUIID("InputText");
        TextField password=new TextField("","",20,TextField.PASSWORD);
        password.setUIID("InputText");
        TextField Cpassword=new TextField("","",20,TextField.PASSWORD);
        Cpassword.setUIID("InputText");
        TextField prenom=new TextField(u.getPrenom());
        prenom.setUIID("InputText");
        TextField nom=new TextField(u.getNom());
        nom.setUIID("InputText");
        Button image=new Button("Parcourir...");
        image.setUIID("Parcourir");
        TextField numtel=new TextField(new Integer(u.getNumTel()).toString());
        numtel.setUIID("InputText");
        ComboBox<String> Region=new ComboBox<>();
        Region.addItem("Tunis");
        Region.addItem("Ariana");
        Region.addItem("Ben Arous");
        TextField ville=new TextField(u.getVille());
        ville.setUIID("InputText");
        TextField rue=new TextField(u.getRue());
        rue.setUIID("InputText");
        TextField codepostal=new TextField(new Integer(u.getCodePostal()).toString());
        codepostal.setUIID("InputText");
        Picker datedenaissance=new Picker();
        datedenaissance.setDate(u.getDateNaissance());
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
               if(Dialog.show("Confirmer", "Etes vous sur de vouloir confirmer les modifications?", "Confirmer","Annuler"))
               {
                    String numT=numtel.getText();
                    Integer num=Integer.parseInt(numT);
                    Integer code=Integer.parseInt(codepostal.getText());
                    User u1=new User(u.getIdUser(), username.getText(), email.getText(),password.getText(), nom.getText(),prenom.getText(),filename,num, datedenaissance.getDate(), Region.getSelectedItem(), ville.getText(),rue.getText(),code);
                    System.out.println(Statics.BASE_URL + "parsing/modifyuser/"+u1.generateurl());
                    System.out.println(u1);
                    if(ServiceUser.getInstance().modifyUser(u1))
                    {
                        Dialog.show("Success","Votre profile est mis a jour","ok",null);
                        this.u=u1;
                        Statics.CONNECTED_USER=u1;
                        refreshTheme();
                    }
                    else
                    {
                       Dialog.show("Erreur","Une erreur est survenue","ok",null); 
                    }
                   
               }
               
           }
           
        });
        
        
        //add(new Label("Today", "TodayTitle"));
        
       // FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
        
        //addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
        //addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
        //addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
        //addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);
        setupSideMenu(res,this.u);
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

    @Override
    protected void showOtherForm(Resources res) {
        //new StatsForm(res).show();
    }
}
