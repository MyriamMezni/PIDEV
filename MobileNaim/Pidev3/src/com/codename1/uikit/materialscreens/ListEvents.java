/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.codename1.uikit.materialscreens;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.location.Geofence;
import com.codename1.location.GeofenceListener;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.EvenementServices;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author hamza
 */
public final class ListEvents extends SideMenuBaseForm  {
    SpanLabel sp= new SpanLabel();
    User u;
    Database db;
    String info;
     Form c=new Form("", BoxLayout.y());
     Form hi = new Form("Ajout Question -> ", new BoxLayout(BoxLayout.Y_AXIS));
     
         public ArrayList<Evenement> eventss;
          ComboBox<Map<String, Object>> combo;
          ComboBox<String> cb=new ComboBox<>();
          Button insc=new Button("Inscrire a un evnement!");
          Button Rec=new Button("Des Questions Sur Des Evenements");
        //  SpanLabel sp = new SpanLabel();
          Form current;
           boolean created = false;
  String url="http://localhost/PidevF2/pidev/web/Images/";
    EncodedImage enc;
    Image img;
    Image profilePic ;
    public ListEvents(Resources res,User u) {
         this.u=u;
        url=url+u.getImage();
                        setupSideMenu(res,this.u);
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
        
        setTitle("Events");
        current=this;
       
        Button details=new Button("details de l event");
      //  setTitle(d);
        setLayout(BoxLayout.y());
        
//         SpanLabel sp = new SpanLabel();
//        sp.setText(EvenementServices.getInstance().getAllTasks().toString());
//        
//        add(sp);
        
       eventss= EvenementServices.getInstance().getAllTasks();
       Evenement e1=new Evenement();
       
      ArrayList<String> list =new ArrayList<>();
        for (Evenement ev : eventss) {
            e1=ev; 
            System.out.println(e1);
            list.add(ev.getNom());
          
      }  
        
         for (String ev2 : list){
         
         cb.addItem(ev2);
         }
         
         details.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                  for (Evenement ev : eventss) {
                  
                  if(ev.getNom().toString().equals(cb.getSelectedItem().toString()))
                  {  Dialog dlg = new Dialog(ev.getNom());
    Style dlgStyle = dlg.getDialogStyle();
    dlgStyle.setBorder(Border.createEmpty());
    dlgStyle.setBgTransparency(255);
    dlgStyle.setBgColor(0xffffff);

    Label title = dlg.getTitleComponent();
   
    title.getUnselectedStyle().setFgColor(0xff00);
    title.getUnselectedStyle().setAlignment(Component.LEFT);

    dlg.setLayout(BoxLayout.y());
    Label blueLabel = new Label();
    blueLabel.setShowEvenIfBlank(true);
    blueLabel.getUnselectedStyle().setBgColor(0xff);
    blueLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    blueLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(blueLabel);
    TextArea ta = new TextArea("-"+ev.getType()+"\n"+"-"+ev.getHeure_d()+"\n"+"-"+ev.getDate_evt());
    ta.setEditable(false);
    ta.setUIID("DialogBody");
    ta.getAllStyles().setFgColor(0);
    dlg.add(ta);

    Label grayLabel = new Label();
    grayLabel.setShowEvenIfBlank(true);
    grayLabel.getUnselectedStyle().setBgColor(0xcccccc);
    grayLabel.getUnselectedStyle().setPadding(1, 1, 1, 1);
    grayLabel.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
    dlg.add(grayLabel);

    Button ok = new Button(new Command("OK"));
    ok.getAllStyles().setBorder(Border.createEmpty());
    ok.getAllStyles().setFgColor(0);
    dlg.add(ok);
    dlg.showDialog();
                      break; }
                  
                 
                  }
                  
                
            }
            
            
        });
         
  //     System.out.println(d);
       
       current.getToolbar().addMaterialCommandToRightBar("Question", FontImage.MATERIAL_BACKUP, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                               Toolbar.setGlobalToolbar(true);
Form hi = new Form("Ajout Question -> ", new BoxLayout(BoxLayout.Y_AXIS));
 hi.getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->current.showBack());
hi.getToolbar().addCommandToRightBar("+", null, e -> {
    TextField tf = new TextField("", "Titre Du Sujet", 20, TextField.ANY);
    TextArea body = new TextArea(5, 20);
    body.setHint("Contenu Du Sujet");
    Command ok = new Command("OK");
    Command cancel = new Command("Cancel");
    Command result = Dialog.show(cb.getSelectedItem().toString(), BorderLayout.north(tf).add(BorderLayout.CENTER, body), ok, cancel);
    if(ok == result) {
        try(OutputStream os = Storage.getInstance().createOutputStream(tf.getText());) {
            os.write(body.getText().getBytes("UTF-8"));
            createFileEntry(hi, tf.getText());
            hi.getContentPane().animateLayout(250);
        } catch(IOException err) {
            Log.e(err);
        }
    }
});
for(String file : Storage.getInstance().listEntries()) {
    createFileEntry(hi, file);
}
hi.show();
                        }
                    });
       
       insc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                Dialog.show("Merci", "Soyez Les Bienvenus "+u.getNom()+" "+u.getPrenom()+" ", "ok", null);
                
                  try {
                    created = Database.exists("aerocode6.db");
                    db = Database.openOrCreate("aerocode6.db");

                    if (created == false) {
                        db.execute("create table aerocode (id INTEGER PRIMARY KEY AUTOINCREMENT,code TEXT);");
                    }
                     
                    db.execute("insert into aerocode (code) values ('" + u.getNom() + "');");
                    Cursor result = db.executeQuery("select * from aerocode;");
                    while (result.next()) {
                        Row r = result.getRow();
                        info = " " + r.getString(1);
                        System.out.println(info);
                       
                 sp.setText(info);
                         
       
                
                    
                 

                    }
                    
                     c.add(new Label(info+" inscrit a "+cb.getSelectedItem().toString()+""));
                     c.getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->current.showBack());
                     
                     
                     Rec.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
     
                            
                        }
                    });
                     c.show();
                } catch (IOException ex) {
                }
               
        
            }
        });
       
       
       
      
         addAll(cb,details,insc);
       // getToolbar().addMaterialCommandToLeftBar("back",FontImage.MATERIAL_ARROW_BACK, e->precedent.showBack());
                        setupSideMenu(res,this.u);

    }
     private void createFileEntry(Form hi, String file) {
   Label fileField = new Label(file);
   Button delete = new Button();
   Button view = new Button();
   FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
   FontImage.setMaterialIcon(view, FontImage.MATERIAL_OPEN_IN_NEW);
   Container content = BorderLayout.center(fileField);
   int size = Storage.getInstance().entrySize(file);
   content.add(BorderLayout.EAST, BoxLayout.encloseX(new Label(size + "bytes"), delete, view));
   delete.addActionListener(e -> {
       Storage.getInstance().deleteStorageFile(file);
       content.setY(hi.getWidth());
       hi.getContentPane().animateUnlayoutAndWait(150, 255);
       hi.removeComponent(content);
       hi.getContentPane().animateLayout(150);
   });
   view.addActionListener(e -> {
       try(InputStream is = Storage.getInstance().createInputStream(file);) {
           String s = Util.readToString(is, "UTF-8");
           Dialog.show(file, s, "OK", null);
       } catch(IOException err) {
           Log.e(err);
       }
   });
   hi.add(content);
}

    @Override
    protected void showOtherForm(Resources res) {
    }
    
    
}



