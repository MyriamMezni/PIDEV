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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author ben younes
 */
public class ServiceUser {
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    User u;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
    public ArrayList<User> parseUsers(String jsonText){
        try {
            ArrayList<User> Users=new ArrayList<>();
            User u=null;
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
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                int idUser=(int)Float.parseFloat(obj.get("idUser").toString());
                String username=obj.get("username").toString();
                String username_canonical=obj.get("username_canonical").toString();
                String email=obj.get("email").toString();
                String email_canonical=obj.get("email_canonical").toString();
                Boolean enabledB=Boolean.parseBoolean(obj.get("enabled").toString());
                String salt=null;
                if (obj.get("salt")!=null)
                    salt=obj.get("salt").toString();  
                String password=obj.get("password").toString();
                Date last_login=null;
                if(obj.get("last_login")!=null)
                    try {
                        last_login=new SimpleDateFormat("dd/MM/yyyy H:mm").parse(obj.get("last_login").toString());
                } catch (ParseException ex) {
                }
                String comfirmation_token=null;
                if(obj.get("confirmation_token")!=null)
                    comfirmation_token=obj.get("confirmation_token").toString();
                Date password_requested_at=null;
                if(obj.get("password_requested_at")!=null)
                    try {
                        password_requested_at=new SimpleDateFormat("dd/MM/yyyy H:mm").parse(obj.get("password_requested_at").toString());
                } catch (ParseException ex) {
                }
                String roles=obj.get("roles").toString();
                String nom=obj.get("nom").toString();
                String prenom=obj.get("prenom").toString();
                String image=obj.get("image").toString();
                String mdp=obj.get("mdp").toString();
                int NumTel=(int)Float.parseFloat(obj.get("NumTel").toString());
                Date DateNaissance=null;
                try {
                    DateNaissance = new SimpleDateFormat("dd/MM/yyy h:mm").parse(obj.get("DateNaissance").toString());
                } catch (ParseException ex) {
                    
                }
                String Region=obj.get("Region").toString();
                String Ville=obj.get("Ville").toString();
                String Rue=obj.get("Rue").toString();
                int CodePostal=(int)Float.parseFloat(obj.get("CodePostal").toString());
                int Tarif=0;
                if(obj.get("Tarif")!=null)
                    Tarif=(int)Float.parseFloat(obj.get("Tarif").toString());
                int NbEnfant=0;
                if(obj.get("NbEnfant")!=null)
                    NbEnfant=(int)Float.parseFloat(obj.get("NbEnfant").toString());
                String role=obj.get("role").toString();
                //Ajouter la tâche extraite de la réponse Json à la liste
                /*System.out.println("idUser:"+idUser+
                                   "\nusername:"+username+
                                   "\nusername_canonical:"+username_canonical+
                                   "\nemail:"+email+
                                   "\nemail_canonical:"+email_canonical+
                                   "\nenabled:"+enabledB+
                                   "\nsalt:"+salt+
                                   "\npassword"+password+
                                   "\nlast_login:"+last_login+
                                   "\ncomfirmation_token:"+comfirmation_token+
                                   "\npassword_requested_at"+password_requested_at+
                                   "\nroles:"+roles+
                                   "\nnom:"+nom+
                                   "\nprenom:"+prenom+
                                   "\nimage:"+image+
                                   "\nmdp:"+mdp+
                                   "\nNumTel"+NumTel+
                                   "\nDateNaissance"+DateNaissance+
                                   "\nRegion:"+Region+
                                   "\nVille:"+Ville+
                                   "\nRue:"+Rue+
                                   "\nCodePostal:"+CodePostal+
                                   "\nTarif:"+Tarif+
                                   "\nNbEnfant:"+NbEnfant+
                                   "\nrole:"+role
                );*/
                int enabled;
                if(enabledB)
                {
                    enabled=1;
                }
                else
                {
                    enabled=0;
                }
                Users.add(new User(idUser, username, username_canonical, email, email_canonical,enabled, salt, password, last_login, comfirmation_token, password_requested_at, roles, nom, prenom, image, mdp, NumTel, DateNaissance, Region, Ville, Rue, CodePostal, Tarif, NbEnfant, role));
            }
            return Users;
            
            
        } catch (IOException ex) {
            return null;
            
        }
       
    }
    
    public User getOne(String username,String mdp){
        String url = Statics.BASE_URL+"parsing/userone/"+username+"/"+mdp;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ArrayList<User> Users=new ArrayList<>();
                Users=parseUsers(new String(req.getResponseData()));
                if(Users.size()==1)
                {

                    u=Users.get(0);
                }
                else
                {
                    u=null;
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return u;
    }
    public boolean modifyUser(User u) {
        String url = Statics.BASE_URL + "parsing/modifyuser/"+u.generateurl() ;
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean addUser(User u) {
        String url = Statics.BASE_URL + "parsing/adduser/"+u.generateurl() ;
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
