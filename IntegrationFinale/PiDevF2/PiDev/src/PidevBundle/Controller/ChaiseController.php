<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Chaise;
use PidevBundle\Entity\TableCantine;
use PidevBundle\Form\ChaiseType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ChaiseController extends Controller
{
    public function ajouterChaiseAction(Request $request)
    {

        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $chaise=new Chaise();
                $em=$this->getDoctrine()->getManager();
                $repT=$this->getDoctrine()->getRepository(TableCantine::class);

                $form=$this->createForm(ChaiseType::class,$chaise);
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $table=$repT->find($chaise->getNumTable()->getIdTable());
                        $table->setCapacite($table->getCapacite()-1);
                        $chaise->setEtatPlat("Vide");
                        $em->persist($table);
                        $em->persist($chaise);
                        $em->flush();
                        return $this->redirectToRoute("AfficherChaisesBack");
                    }
                }
                return $this->render("@Pidev/Chaise/Back/AjouterChaise.html.twig",array("form"=>$form->createView()));
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }
    public function afficherChaisesBackAction()
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $rep=$this->getDoctrine()->getRepository(Chaise::class);
                $chaises=$rep->findAll();
                return $this->render("@Pidev/Chaise/Back/AfficherChaises.html.twig",array("chaises"=>$chaises));
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }

    }
    public function modifierChaiseBackAction($idChaise,Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $repC=$this->getDoctrine()->getRepository(Chaise::class);
                $chaise=$repC->find($idChaise);
                $em=$this->getDoctrine()->getManager();
                $repT=$this->getDoctrine()->getRepository(TableCantine::class);

                $form=$this->createForm(ChaiseType::class,$chaise);
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $table=$repT->find($chaise->getNumTable()->getIdTable());
                        $table->setCapacite($table->getCapacite()-1);
                        $chaise->setEtatPlat("Vide");
                        $em->persist($table);
                        $em->persist($chaise);
                        $em->flush();
                        return $this->redirectToRoute("AfficherChaisesBack");
                    }
                }
                return $this->render("@Pidev/Chaise/Back/ModifierChaise.html.twig",array("form"=>$form->createView()));
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
    public function supprimerChaiseBackAction($idChaise)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $repC=$this->getDoctrine()->getRepository(Chaise::class);
                $chaise=$repC->find($idChaise);
                $em=$this->getDoctrine()->getManager();
                $enfant=$chaise->getIdEnfant();
                if($enfant!=null)
                {
                    $enfant->setCantine(false);
                    $em->persist($enfant);
                }
                $em->remove($chaise);
                $em->flush();
                return $this->redirectToRoute("AfficherChaisesBack");
            }
            else{
                return $this->render('Front.html.twig');
            }
        }
        else
        {
            return $this->redirectToRoute("fos_user_security_login");
        }
    }
}
