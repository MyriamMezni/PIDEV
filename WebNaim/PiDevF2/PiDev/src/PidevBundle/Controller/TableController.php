<?php

namespace PidevBundle\Controller;

use PidevBundle\Entity\Chaise;
use PidevBundle\Entity\TableCantine;
use PidevBundle\Form\TableCantineType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class TableController extends Controller
{
    public function ajouterTableAction(Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $tableCantine=new TableCantine();
                $em=$this->getDoctrine()->getManager();
                $form=$this->createForm(TableCantineType::class,$tableCantine);
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $em->persist($tableCantine);
                        $em->flush();
                        return $this->redirectToRoute("AfficherTablesBack");
                    }
                }
                return $this->render("@Pidev/TableCantine/Back/AjouterTable.html.twig",array("form"=>$form->createView()));
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
    public function afficherTableBackAction()
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $rep=$this->getDoctrine()->getRepository(TableCantine::class);
                $tables=$rep->findAll();
                return $this->render("@Pidev/TableCantine/Back/AfficherTables.html.twig",array("tables"=>$tables));
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
    public function modifierTableBackAction($idTable,Request $request)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $rep=$this->getDoctrine()->getRepository(TableCantine::class);
                $tableCantine=$rep->find($idTable);
                $em=$this->getDoctrine()->getManager();
                $form=$this->createForm(TableCantineType::class,$tableCantine);
                $form->handleRequest($request);
                if($form->isSubmitted())
                {
                    if($form->isValid())
                    {
                        $em->persist($tableCantine);
                        $em->flush();
                        return $this->redirectToRoute("AfficherTablesBack");
                    }
                }
                return $this->render("@Pidev/TableCantine/Back/ModifierTable.html.twig",array("form"=>$form->createView()));
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

    public function supprimerTableAction($idTable)
    {
        if($this->getUser()!=null)
        {
            if($this->getUser()->getRole()=="Employe" or $this->getUser()->getRole()=="Admin")
            {
                $repC=$this->getDoctrine()->getRepository(Chaise::class);
                $repT=$this->getDoctrine()->getRepository(TableCantine::class);
                $chaises=$repC->findByNumTable($idTable);
                $em=$this->getDoctrine()->getManager();


                foreach ($chaises as $c)
                {
                    $enfant=$c->getIdEnfant();
                    if($enfant!=null)
                    {
                        $enfant->setCantine(false);
                        $em->persist($enfant);
                    }

                    $em->remove($c);
                }
                $tableCantine=$repT->find($idTable);
                $em->remove($tableCantine);
                $em->flush();
                return $this->redirectToRoute("AfficherTablesBack");
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
