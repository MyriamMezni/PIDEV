<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Notification;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class NotificationController extends Controller
{
    public function afficheNotifBackAction()
    {
        $notifs=$this->getDoctrine()->getRepository(Notification::class)->findBy([
            'idUser'=>$this->getUser(),
            'seen'=>false
        ]);
        return $this->render("@Pidev/Notification/Back/Notification.html.twig",array("notifs"=>$notifs));
    }
    public function afficheNotifFrontAction()
    {
        $notifs=$this->getDoctrine()->getRepository(Notification::class)->findBy([
            'idUser'=>$this->getUser(),
            'seen'=>false
        ]);
        return $this->render("@Pidev/Notification/Front/Notification.html.twig",array("notifs"=>$notifs));
    }
    public function setSeenAction($notif)
    {
        $notification=$this->getDoctrine()->getRepository(Notification::class)->find($notif);
        $notification->setSeen(true);
        $em=$this->getDoctrine()->getManager();
        $em->persist($notification);
        $em->flush();
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Admin" or $this->getUser()->getRole()=="Employe")
            {
                if($notification->getParameters()['sujetId']==null)
                {
                    return $this->redirectToRoute("Afficher_Sujets_Back");
                }
                else
                {
                    return $this->redirectToRoute("Afficher_Commentaires_Back",array("idSujet"=>$notification->getParameters()['sujetId']));
                }


            }
            else
            {
                if($notification->getParameters()['sujetId']==null)
                {
                    return $this->redirectToRoute("Afficher_Sujets_Front");
                }
                else
                {
                    return $this->redirectToRoute("Afficher_Commentaires_Front",array("idSujet"=>$notification->getParameters()['sujetId']));
                }

            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
}
