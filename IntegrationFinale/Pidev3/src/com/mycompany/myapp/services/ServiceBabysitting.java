/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.entities.Babysitting;
import com.mycompany.myapp.entities.Enfant;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HEDI MSELMI
 */
public class ServiceBabysitting {

    public ArrayList<Enfant> tasks;
    ArrayList<Babysitting> BabysitterParB;
    public static ServiceBabysitting instance = null;
    public boolean resultOK;
    public String b;
    private ConnectionRequest req;

    public ServiceBabysitting() {
        req = new ConnectionRequest();
    }

    public static ServiceBabysitting getInstance() {
        if (instance == null) {
            instance = new ServiceBabysitting();
        }
        return instance;
    }

    public boolean AjoutBabysitter(Babysitting t, int id, int s) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// création d'une nouvelle demande de connexion   id_reclamation="+id_reclamation+"&description="+description
        String jour = "lundi";
        int nn = Math.round(t.getHeureDebut() / 60);

        int kd = (t.getHeureDebut() / 60) * 3600 + (t.getHeureDebut() - nn * 60) * 60;
        int nn1 = Math.round(t.getHeureFin() / 60);

        int kd1 = (t.getHeureFin() / 60) * 3600 + (t.getHeureFin() - nn1 * 60) * 60;
        String Url = "http://127.0.0.1/PidevF2/Pidev/web/app_dev.php/Pidev/json/ajout/" + id + "/" + s + "?heuredebut=" + kd + "&heurefin=" + kd1 + "&joursemaine=" + t.getJourSemaine() + "&prixheure=" + t.getPrixHeure();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //    System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return false; // Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public ArrayList<Babysitting> parseListReclamationJson(String json) {

        ArrayList<Babysitting> listReclamations = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> reclamations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamations.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Babysitting r = new Babysitting();
                Babysitting r1 = new Babysitting();
                //  int id_enfant = Integer.parseInt(obj.get("id_enfant").toString());
                //   r.setId_enfant(1) ;  	
                //  float idBabysitter = Float.parseFloat(obj.get("idbabysitter").toString());
                //    naimp2  NaimP2
                //  System.out.println("aa:"+obj.get("idbabysitter").toString());
                //  afficheBabysitting aff= new afficheBabysitting();
                ServiceBabysitting serviceDemande = new ServiceBabysitting();
                ArrayList<User> allBabysitters = ServiceUser.getInstance().getAllBabysitter();

                for (User e : allBabysitters) {
                    if (obj.get("idbabysitter").toString().contains(e.getUsername())) {
                        r.setIdBabysitter(e.getIdUser());
                    }
                    //     r.setId_enfant(new Enfant(ServiceBabysitting.getInstance().getEnfantParB(e.getIdUser())));
                }

                float heuredebut = Float.parseFloat(obj.get("heuredebut").toString());
                r.setHeureDebut((int) heuredebut);
                float heurefin = Float.parseFloat(obj.get("heurefin").toString());
                r.setHeureFin((int) heurefin);

                r.setJourSemaine(obj.get("joursemaine").toString());
                float prixHeure = Float.parseFloat(obj.get("prixheure").toString());
                r.setPrixHeure((int) prixHeure);
                //    com.mycompany.Entite.Enfant s;
                // float id_enfant = Float.parseFloat(obj.get("idEnfant").toString());
                // r.setId_enfant();
                //  System.out.println("ttt :"+obj.get("IdEnfant").toString());
                ArrayList<Enfant> babysittersAll = ServiceEnfant.getInstance().getEnfantsPourParent(Statics.CONNECTED_USER.getIdUser());
                ArrayList<Enfant> babysitters = new ArrayList<>();
                int s = 0;
                        
                //ObjectMapper mapper = new ObjectMapper();
                //Enfant[] tab = mapper.readValue(obj.get("IdEnfant").toString(), Enfant[].class);
 
         
                

                
                for (int t = 0; t < babysittersAll.size(); t++) {
                    
                    System.out.println("parcour :" + babysittersAll.get(t));
                    System.out.println(babysittersAll.size());
           


                    //System.out.println("contains:" + Float.parseFloat(Integer.toString(babysittersAll.get(t).getId_enfant())));
                    
                    
                    
                    int id_int = (int) babysittersAll.get(t).getId_enfant();
                    String id_str = Integer.toString(id_int);
                    String a=babysittersAll.get(t).getDocument();
                    System.out.println("m2 :"+id_str);
                    
                    
                                
                    if(obj.get("IdEnfant").toString().substring(0, 15).contains(id_str) && obj.get("IdEnfant").toString().contains(a))
                      {  s = t;
                        System.out.println("la valeur de s et : " + s);
                        r.setId_enfant(babysittersAll.get(s));
                        break;
                        

                    }

                }

                //    ;
                //  r.setId_enfant(babysittersAll.get(s));
                listReclamations.add(r);

            }
        } catch (IOException ex) {

        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        //   System.out.println(listReclamations);
        return listReclamations;
    }

    ArrayList<Babysitting> listReclamations = new ArrayList<>();

    public ArrayList<Babysitting> getListReclamations(int idParent) {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/PidevF2/Pidev/web/app_dev.php/Pidev/test2/" + idParent;
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceBabysitting ser = new ServiceBabysitting();
                listReclamations = ser.parseListReclamationJson(new String(con.getResponseData()));
                con.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }

    public void SupprimerBabysitting(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PidevF2/Pidev/web/app_dev.php/Pidev/remove/" + id);
        //  System.out.println("//////////////////////////////////////");
        //   System.out.println(id);
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public boolean ModifierBabysitter(int id, int s,int t,String h,int v,int d) {
        ConnectionRequest con = new ConnectionRequest();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");// création d'une nouvelle demande de connexion   id_reclamation="+id_reclamation+"&description="+description

        String Url = "http://localhost/PidevF2/Pidev/web/app_dev.php/Pidev/updatemobile/" + id + "/" + s + "/" + t+ "/" + h + "/" + v + "/" + d;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //    System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return false; // Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

}
