/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.AdminComboBox;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ben younes
 */
public class SendMailForm extends SideMenuBaseForm{
    String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    User u;
    Image profilePic ;

    public SendMailForm(Resources res,User u) {
        super(BoxLayout.y());
        this.u=u;
        url=url+u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        try {
            enc=EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
        img=URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        profilePic= img;
        //Image profilePic = res.getImage("user-picture.jpg");        
        Image tintedImage = Image.createImage(profilePic.getWidth(), profilePic.getHeight());
        Graphics g = tintedImage.getGraphics();
        g.drawImage(profilePic, 0, 0);
        g.drawImage(res.getImage("gradient-overlay.png"), 0, 0, profilePic.getWidth(), profilePic.getHeight());
        
        tb.getUnselectedStyle().setBgImage(tintedImage);
        
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());

        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
        
        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent = 
                BorderLayout.north(
                    BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                add(BorderLayout.CENTER, space).
                add(BorderLayout.SOUTH, 
                        FlowLayout.encloseIn(
                                new Label(" "+u.getPrenom()+" ", "WelcomeBlue"),
                                new Label(u.getNom(), "WelcomeWhite")
                        ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        ArrayList<User> admins=ServiceUser.getInstance().getAdmins();
        ArrayList<AdminComboBox> cbadmins=new ArrayList<>();
        for(User a:admins)
        {
            if(a.getIdUser()!=u.getIdUser())
            {
                AdminComboBox cba=new AdminComboBox(a);
                cbadmins.add(cba);
            }
        }
        ComboBox<AdminComboBox> cb=new ComboBox<>();
        for (AdminComboBox cba:cbadmins)
        {
            cb.addItem(cba);
        }
        Container Content=new Container(BoxLayout.y());
        Content.add(cb);
        Container Subject=new Container(BoxLayout.x());
        Label SubjetL=new Label("Sujet: ","InputLabel");
        TextField SubjectT=new TextField("","Sujet");
        SubjectT.setUIID("InputText");
        Subject.add(SubjetL);
        Subject.add(SubjectT);
        Content.add(Subject);
        Container Body=new Container(BoxLayout.x());
        Label BodyL=new Label("Contenu: ","InputLabel");
        TextArea BodyT=new TextArea(10, 15);
        BodyT.setUIID("InputText");
        Body.add(BodyL);
        Body.add(BodyT);
        Content.add(Body);
        Button send=new Button(FontImage.MATERIAL_SEND, "SendMail");
        Content.add(send);
        send.addActionListener((evt) -> {
            Message m = new Message(BodyT.getText());
            Display.getInstance().sendMessage(new String[] {cb.getSelectedItem().getU().getEmail()},SubjectT.getText(), m);
        });
        add(Content);
        setupSideMenu(res,this.u);
    }
    @Override
    protected void showOtherForm(Resources res) {
        //new ProfileForm(res).show();
    }
    
    
}
