<?php

namespace PidevBundle\Controller;

use Cassandra\Date;
use PidevBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\File\UploadedFile;
class UserController extends Controller
{

    public function parsingUserDisplayAction()
    {
        $users=$this->getDoctrine()->getRepository(User::class)->findAll();
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            $data[$key]['password_requested_at']=$u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['numtel']=$u->getNumTel();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance();
            $data[$key]['Salaire']=$u->getSalaire();
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['NbHeures']=$u->getNbHeures();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }

    public function parsingAdminsDisplayAction()
    {
        $users=$this->getDoctrine()->getRepository(User::class)->AfficherAdminsEtEmployes();
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            $data[$key]['password_requested_at']=$u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['numtel']=$u->getNumTel();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance();
            $data[$key]['Salaire']=$u->getSalaire();
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['NbHeures']=$u->getNbHeures();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }
    public function parsingUserOneAction($username,$mdp)
    {
        $users=$this->getDoctrine()->getRepository(User::class)->findBy([
            'username'=>$username,
            'mdp'=>$mdp,
            'role'=>"Parent"
        ]);
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            if($u->getLastLogin()!=null)
                $data[$key]['last_login']=$u->getLastLogin()->format("d/m/Y H:s");
            else
                $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            if($u->getPasswordRequestedAt()!=null)
                $data[$key]['password_requested_at']=$u->getPasswordRequestedAt()->format("d/m/Y H:s");
            else
                $u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance()->format("d/m/Y H:s");
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }
    public function modifyUserAction($id,$username,$email,$password,$nom,$prenom,$image,$numtel,$day,$mounth,$year,$hour,$minute,$region,$ville,$rue,$codepostal)
    {
        $fileS=fopen("C:/Users/ben younes/AppData/Local/Temp/".$image,"r+");
        $fileSource=fread($fileS,filesize("C:/Users/ben younes/AppData/Local/Temp/".$image));
        $extension=substr($image,strrpos($image,".")+1);
        $userManager = $this->get('fos_user.user_manager');
        echo ($extension);
        $name=rand(1, 99999).'.'.$extension;
        $user=new User();
        $user->setId($id);
        $user->setUsername($username);
        $user->setUsernameCanonical(strtolower($username));
        $user->setEmail($email);
        $user->setEmailCanonical(strtolower($email));
        $user->setPlainPassword($password);
        $user->setMdp($password);
        $user->setRole("Parent");
        $user->addRole("ROLE_PARENT");
        $user->setEnabled(true);
        $user->setNbenfant(0);
        $user->setNom($nom);
        $user->setPrenom($prenom);
        $user->setNumtel($numtel);
        $user->setRegion($region);
        $user->setVille($ville);
        $user->setCodepostal($codepostal);
        $user->setRue($rue);
        $user->setImage($name);
        $dateT=$day."/".$mounth."/".$year." "."0:00";
        var_dump($dateT);
        $date=\DateTime::createFromFormat("d/m/Y H:i",$dateT);
        var_dump($date);
        $user->setDatenaissance($date);
        $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
        $fileDestination=fopen($directory.$name,"w+");
        fwrite($fileDestination,$fileSource);
        fclose($fileS);
        fclose($fileDestination);

        $userManager->updatePassword($user);
        var_dump($user);
        $em=$this->getDoctrine()->getRepository(User::class);
        $em->update($user);




        return new JsonResponse($name);
    }


    public function addUserAction($id,$username,$email,$password,$nom,$prenom,$image,$numtel,$day,$mounth,$year,$hour,$minute,$region,$ville,$rue,$codepostal)
    {
        $fileS=fopen("C:/Users/ben younes/AppData/Local/Temp/".$image,"r+");
        $fileSource=fread($fileS,filesize("C:/Users/ben younes/AppData/Local/Temp/".$image));
        $extension=substr($image,strrpos($image,".")+1);
        $userManager = $this->get('fos_user.user_manager');
        echo ($extension);
        $name=rand(1, 99999).'.'.$extension;
        $user=new User();
        $user->setId($id);
        $user->setUsername($username);
        $user->setUsernameCanonical(strtolower($username));
        $user->setEmail($email);
        $user->setEmailCanonical(strtolower($email));
        $user->setPlainPassword($password);
        $user->setMdp($password);
        $user->setRole("Parent");
        $user->addRole("ROLE_PARENT");
        $user->setEnabled(true);
        $user->setNbenfant(0);
        $user->setNom($nom);
        $user->setPrenom($prenom);
        $user->setNumtel($numtel);
        $user->setRegion($region);
        $user->setVille($ville);
        $user->setCodepostal($codepostal);
        $user->setRue($rue);
        $user->setImage($name);
        $dateT=$day."/".$mounth."/".$year." "."0:00";
        var_dump($dateT);
        $date=\DateTime::createFromFormat("d/m/Y H:i",$dateT);
        var_dump($date);
        $user->setDatenaissance($date);
        $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
        $fileDestination=fopen($directory.$name,"w+");
        fwrite($fileDestination,$fileSource);
        fclose($fileS);
        fclose($fileDestination);
        $userManager->updateUser($user);
        /*$userManager->updatePassword($user);
        var_dump($user);
        $em=$this->getDoctrine()->getRepository(User::class);
        $em->update($user);*/




        return new JsonResponse($name);
    }

    public function parsingBabysitterDisplayAction()
    {
        $users=$this->getDoctrine()->getRepository(User::class)->AfficherBabysitter();
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            $data[$key]['password_requested_at']=$u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['numtel']=$u->getNumTel();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance();
            $data[$key]['Salaire']=$u->getSalaire();
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['NbHeures']=$u->getNbHeures();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }

    public function parsingNomBabysitterDisplayAction($username)
    {
        $users=$this->getDoctrine()->getRepository(User::class)->AfficherNomBabysitter($username);
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            $data[$key]['password_requested_at']=$u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['numtel']=$u->getNumTel();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance();
            $data[$key]['Salaire']=$u->getSalaire();
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['NbHeures']=$u->getNbHeures();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }


    public function AfficheNomBabysitterParIdAction($id)
    {
        $users=$this->getDoctrine()->getRepository(User::class)->AfficherBabysitterParId($id);
        $data=array();
        foreach ($users as $key=>$u)
        {
            $data[$key]['idUser']=$u->getId();
            $data[$key]['username']=$u->getUserName();
            $data[$key]['username_canonical']=$u->getUsernameCanonical();
            $data[$key]['email']=$u->getEmail();
            $data[$key]['email_canonical']=$u->getEmailCanonical();
            $data[$key]['enabled']=$u->isEnabled();
            $data[$key]['salt']=$u->getSalt();
            $data[$key]['password']=$u->getPassword();
            $data[$key]['last_login']=$u->getLastLogin();
            $data[$key]['confirmation_token']=$u->getConfirmationToken();
            $data[$key]['password_requested_at']=$u->getPasswordRequestedAt();
            $data[$key]['roles']=$u->getRoles();
            $data[$key]['nom']=$u->getNom();
            $data[$key]['prenom']=$u->getPrenom();
            $data[$key]['numtel']=$u->getNumTel();
            $data[$key]['image']=$u->getImage();
            $data[$key]['mdp']=$u->getmdp();
            $data[$key]['NumTel']=$u->getNumTel();
            $data[$key]['DateNaissance']=$u->getDateNaissance();
            $data[$key]['Salaire']=$u->getSalaire();
            $data[$key]['Region']=$u->getRegion();
            $data[$key]['Ville']=$u->getVille();
            $data[$key]['Rue']=$u->getRue();
            $data[$key]['CodePostal']=$u->getCodePostal();
            $data[$key]['NbHeures']=$u->getNbHeures();
            $data[$key]['Tarif']=$u->getTarif();
            $data[$key]['NbEnfant']=$u->getNbEnfant();
            $data[$key]['role']=$u->getRole();
        }
        return new JsonResponse($data);
    }

}
