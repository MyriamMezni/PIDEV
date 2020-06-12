/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Babysitting;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceBabysitting;
import com.mycompany.myapp.services.ServiceEnfant;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HEDI MSELMI
 */
public class afficheBabysitting extends SideMenuBaseForm {

    EncodedImage enc;
    Image img;
    User u;
    String url = "http://localhost/PidevF2/pidev/web/Images/";
    Image profilePic;
    TextField tx_typedem;
    TextField tx_descriptiondem;
    TextField tx_maildem;
    Picker s;
    Button bt_supprimer;
    Button bt_modifier;
    // User u;
    // User u;
    Resources res;
    Button bt_ajout, bt_affiche, bt_chercher;
    TableLayout tl;
    int spanButton = 2;

    public afficheBabysitting(Resources res, User u) {
        super(BoxLayout.y());
        this.u = u;
        url = url + u.getImage();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        img = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
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
        //   add(menuButton);

        // setTitle("Affiche Enfant"); Button settingsButton = new Button("");
        Button settingsButton = new Button("");
        settingsButton.setUIID("Title");
        //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);

        Label space = new Label("", "TitlePictureSpace");
        space.setShowEvenIfBlank(true);
        Container titleComponent
                = BorderLayout.north(
                        BorderLayout.west(menuButton).add(BorderLayout.EAST, settingsButton)
                ).
                        //  add(BorderLayout.CENTER, space).
                        add(BorderLayout.SOUTH,
                                FlowLayout.encloseIn(
                                        new Label("Affiche Babysitter", "WelcomeBlue")
                                ));
        titleComponent.setUIID("BottomPaddingContainer");
        tb.setTitleComponent(titleComponent);
        bt_chercher = new Button("mail");

        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceEnfant.getInstance().getListReclamations().get(serviceDemande.getListReclamations().size()-1).getNom().toString());
        // sp.setText(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getImage());
        add(sp);

        // Container YY = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        // YY.add(bt_SMS);
        //  add(YY);
        //   Display.getInstance().execute(ServiceEnfant.getInstance().getListReclamations().get(ServiceEnfant.getInstance().getListReclamations().size()-1).getImage())
        //Toolbar tb1 = .getToolbar(); 
        getToolbar().addCommandToOverflowMenu("Déconnecter", null, new ActionListener() {
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
                if (ok == result) {
                    Message m = new Message(body.getText());
                    String textAttachmentUri = "il y'a un probleme";
                    m.getAttachments().put(textAttachmentUri, "text/plain");
                    String imageAttachmentUri = "testina";
                    m.getAttachments().put(imageAttachmentUri, "image/png");
                    Display.getInstance().sendMessage(new String[]{"mohamedmselmi407@gmail.com"}, tf.getText(), m);
                }
            }

        });

        setupSideMenu(res, this.u);
        ServiceBabysitting serviceDemande = new ServiceBabysitting();
        ArrayList<Enfant> TT = ServiceEnfant.getInstance().getEnfantsPourParent(Statics.CONNECTED_USER.getIdUser());
        ArrayList<Babysitting> allBabysitters = ServiceBabysitting.getInstance().getListReclamations(Statics.CONNECTED_USER.getIdUser());
        System.out.println(allBabysitters);
        for (int i = 0; i < allBabysitters.size(); i++) {

            int iddem = allBabysitters.get(i).getIdBabysitter();
            System.out.println(allBabysitters.get(i) + ":::" + i);
            Enfant iddem1 = allBabysitters.get(i).getId_enfant();
            int heuredebut = allBabysitters.get(i).getHeureDebut();
            int heurefin = allBabysitters.get(i).getHeureFin();
            int prixheure = (int) allBabysitters.get(i).getPrixHeure();
            String jour = allBabysitters.get(i).getJourSemaine();

            Label ll = new Label("Date: ");
            ll.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(ll);
            // AfficheDemande.add(new Label( formatter.format(datedem)));

            Label l = new Label("Type: ");
            l.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(l);

            //AfficheDemande.add(new Label(typedem));
            Label l112 = new Label("Description: ");
            l112.getAllStyles().setFgColor(0x0E36D7);

            Label n1 = new Label( iddem1.getNom());
            n1.getAllStyles().setFgColor(0x000000);
            Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label l_id = new Label("Babysitter");
            Label l_id1 = new Label("Enfant");
            Label l_heured = new Label("heure debut");
            Label l_heuref = new Label("heure fin");
            Label l_jour = new Label("jour de semaine");
            //  AfficheBabysitting.add("test");
            l_id.getAllStyles().setFgColor(0xFF0000);

            C2.add(l_id);
            Label n = new Label(ServiceUser.getInstance().getNomBabysitter(iddem));
            n.getAllStyles().setFgColor(0x000000);
            C2.add(n);
            l_id1.getAllStyles().setFgColor(0xFF0000);
            C2.add(l_id1);
            C2.add(n1);
            l_heured.getAllStyles().setFgColor(0xFF0000);
            C2.add(l_heured);
            int zzz = heuredebut / 60;
            int nn = Math.round(zzz / 60);

            String t = Integer.toString(zzz / 60) + ":" + Integer.toString(zzz - nn * 60);

            Label k = new Label(t);
            k.getAllStyles().setFgColor(0x000000);
            C2.add(k);
            l_heuref.getAllStyles().setFgColor(0xFF0000);
            C2.add(l_heuref);
            int zzz1 = heurefin / 60;
            int nn1 = Math.round(zzz1 / 60);
            String tt = Integer.toString(zzz1 / 60) + ":" + Integer.toString(zzz1 - nn1 * 60);
            Label kk = new Label(tt);
            kk.getAllStyles().setFgColor(0x000000);
            C2.add(kk);
            l_jour.getAllStyles().setFgColor(0xFF0000);
            C2.add(l_jour);
            Label jj = new Label(jour);
            jj.getAllStyles().setFgColor(0x000000);
            C2.add(jj);
            add(C2);

            //  int nn=Math.round(t.getHeureDebut()/60);
            //  int kd=(t.getHeureDebut()/60)*3600+(t.getHeureDebut()-nn*60)*60;
//show();
            // AfficheDemande.add(new Label(descriptiondem));
            Label l3 = new Label("Etat: ");
            l3.getAllStyles().setFgColor(0x0E36D7);
//AfficheDemande.add(l3);
//Label l1113 =new Label("_______________");
//l1113.getAllStyles().setFgColor(0xEAEAEA);
            // AfficheDemande.add(new Label(etatdem));
            //AfficheDemande.add(l1113);

            bt_supprimer = new Button("Supprimer");
            bt_supprimer.setUIID("LoginButton");
            bt_supprimer.setWidth(20);

            bt_supprimer.addActionListener((e) -> {
                ServiceBabysitting ser = new ServiceBabysitting();
                // Reclamation t = new Reclamation();
                ser.SupprimerBabysitting(iddem);
                Dialog.show("Success", "suppression accepted", new Command("OK"));
            });
            add(bt_supprimer);

            bt_modifier = new Button("modifier");
            bt_modifier.setUIID("LoginButton");

            bt_modifier.addActionListener(new ActionListener() {
                User u;
                EncodedImage enc;
                Image img;
                Resources res;

                @Override
                public void actionPerformed(ActionEvent evt) {
                    //modifierBabysitter = new Form(" Demande "+ iddem);
                    Form aff = new Form(" Modifier babysitter " + iddem);
                    Button bt_ajout;
                    this.u = u;

                    Toolbar tb = getToolbar();
                    tb.setTitleCentered(false);

                    Button retour = new Button("retour");
                    //   menuButton.setUIID("Title");
                    //FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> new afficheBabysitting(res,this.u).show());

                    //FontImage.setMaterialIcon(settingsButton, FontImage.MATERIAL_SETTINGS);
                    Label space = new Label("", "TitlePictureSpace");
                    space.setShowEvenIfBlank(true);

                    Container c1 = new Container(BoxLayout.y());
                    Label l_heuredebut = new Label("heure debut :");
                    l_heuredebut.getAllStyles().setFgColor(0x000000);
                    Label l_heurefin = new Label("heure fin :");
                    l_heurefin.getAllStyles().setFgColor(0x000000);
                    Label l_joursemaine = new Label("jour de  semaine :");
                    l_joursemaine.getAllStyles().setFgColor(0x000000);
                    Label l_prixheure = new Label("prix heure :");
                    l_prixheure.getAllStyles().setFgColor(0x000000);
                    TextField tx_prixheure = new TextField("", "Prix:", 70, TextArea.ANY);
                    tx_prixheure.getAllStyles().setFgColor(0x4169E1);
                    //  c1.add(t);
                    Picker timePicker = new Picker();
                    timePicker.getAllStyles().setFgColor(0x4169E1);
                    timePicker.setType(Display.PICKER_TYPE_TIME);
                    Picker timePicker1 = new Picker();
                    timePicker1.getAllStyles().setFgColor(0x4169E1);
                    timePicker1.setType(Display.PICKER_TYPE_TIME);
                    c1.add(l_heuredebut);

                    c1.add(timePicker);
                    c1.add(l_heurefin);

                    c1.add(timePicker1);
                    // setupSideMenu(res,this.u); 
                    //  modifierBabysitter.add(c1);
                    Container c2 = new Container(BoxLayout.x());
                    CheckBox lundi = new CheckBox("lundi");
                    lundi.getAllStyles().setFgColor(0x4169E1);
                    CheckBox mardi = new CheckBox("mardi");
                    mardi.getAllStyles().setFgColor(0x4169E1);
                    CheckBox mercredi = new CheckBox("mercredi");
                    mercredi.getAllStyles().setFgColor(0x4169E1);
                    CheckBox jeudi = new CheckBox("jeudi");
                    jeudi.getAllStyles().setFgColor(0x4169E1);
                    CheckBox vendredi = new CheckBox("vendredi");
                    vendredi.getAllStyles().setFgColor(0x4169E1);
                    Container s = new Container(BoxLayout.y());
                    s.add(vendredi);
                    c2.add(lundi);
                    c2.add(mardi);
                    c2.add(mercredi);
                    c2.add(jeudi);
                    aff.add(l_joursemaine);
                    aff.add(c2);
                    aff.add(s);
                    c1.add(l_prixheure);
                    c1.add(tx_prixheure);
                    bt_ajout = new Button("modifier");
                    bt_ajout.setUIID("LoginButton");

                    bt_ajout.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if ((tx_prixheure.getText().length() == 0)) {
                                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                            } else {

                                String s = "";
                                if (lundi.isSelected()) {
                                    s += "lundi";
                                }
                                if (mardi.isSelected()) {
                                    s += " mardi";
                                }
                                if (mercredi.isSelected()) {
                                    s += " mercredi";
                                }
                                if (jeudi.isSelected()) {
                                    s += " jeudi";
                                }
                                if (vendredi.isSelected()) {
                                    s += " vendredi";
                                }
                                ServiceBabysitting ser = new ServiceBabysitting();
                                // Reclamation t = new Reclamation();
                               // ser.SupprimerBabysitting(iddem);
                                Babysitting t = new Babysitting(iddem, (int) timePicker.getValue(), (int) timePicker1.getValue(), s, Integer.parseInt(tx_prixheure.getText()), iddem1);
                                int nn = Math.round((int) timePicker.getValue() / 60);
                                int ss=((int) timePicker.getValue() / 60) * 3600 + ((int) timePicker.getValue() - nn * 60) * 60;
                                  int nn1 = Math.round((int) timePicker1.getValue() / 60);
                                int ss1=((int) timePicker1.getValue() / 60) * 3600 + ((int) timePicker1.getValue() - nn1 * 60) * 60;
                                ser.ModifierBabysitter(iddem, ss, ss1,s,Integer.parseInt(tx_prixheure.getText()),iddem1.getId_enfant());
                                Dialog.show("Success", "modification accepted", new Command("OK"));

                            }
                        }
                    });
                    aff.getToolbar().addCommandToLeftBar("back", null, e -> showBack());
                    retour.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            ProfileForm s = new ProfileForm(res, u);
                            s.show();
                        }
                    });

                    aff.add(c1);
                    aff.add(bt_ajout);

                    aff.show();
                }

            });
            add(bt_modifier);

            //Label l11 =new Label("---------------------------------------------------------------");
            Label l11 = new Label("__________________________________________________________________");
            l11.getAllStyles().setFgColor(0xC0C0C0);

//AfficheDemande.add(l11);
            //  setupSideMenu(res,this.u); 
        }

    }

    public Button getBt_supprimer() {
        return bt_supprimer;
    }

    public void setBt_supprimer(Button bt_supprimer) {
        this.bt_supprimer = bt_supprimer;
    }

    public Button getBt_modifier() {
        return bt_modifier;
    }

    public void setBt_modifier(Button bt_modifier) {
        this.bt_modifier = bt_modifier;
    }

    @Override
    protected void showOtherForm(Resources res) {
        //To change body of generated methods, choose Tools | Templates.
    }

}

