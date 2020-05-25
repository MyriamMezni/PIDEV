<?php


namespace PidevBundle\Controller;


use PidevBundle\Entity\Programme;
use PidevBundle\Form\ProgrammeType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class ProgrammeController extends Controller
{
    public function CreateProgrammeAction(Request $request)
    {

        $Programmes= new Programme();
        $form=$this->createForm(ProgrammeType::class,$Programmes);
        $form=$form->handleRequest($request);
        if($form->isValid())
        {

            $file=$Programmes->getCours();
            $filename= md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory',$filename));
            $Programmes->setCours($filename);

            $em=$this->getDoctrine()->getManager();
            $em->persist($Programmes);
            $em->flush();

            return $this->redirectToRoute('ReadProgramme');
        }

        return $this->render('@Pidev/Programme/CreateProgramme.html.twig',array('form'=>$form->createView()));


    }



    public function ReadProgrammeAction()
    {
        $Programme=$this->getDoctrine()->getRepository(Programme::class)->findAll();
        return $this->render("@Pidev/Programme/ReadProgramme.html.twig",array('Programme'=>$Programme));
    }



    public function UpdateProgramme1Action(Request $request,$idprogramme)
    {
        $Programmes= $this->getDoctrine()->getRepository(Programme::class)
            ->find($idprogramme);
        $form= $this->createForm(ProgrammeType::class,$Programmes);
        $form->handleRequest($request);
        if ($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($Programmes);
            $em->flush();


            return $this->redirectToRoute("ReadProgramme");
        }
        return $this->render("@Pidev/Programme/UpdateProgramme.html.twig",
            array("form"=>$form->createView()));
    }
    public function UpdateProgrammeAction(Request $request,$idprogramme)
    {



        $em=$this->getDoctrine()->getManager();
        $Programmes=$em->getRepository(Programme::class)->find($idprogramme);

        if($request->isMethod('POST'))
        {
            $Programmes->setCours($request->get('cours'));
            $Programmes->setDebut($request->get('debut'));
            $Programmes->setFin($request->get('fin'));


            $em->flush();

            return $this->redirectToRoute('ReadProgramme');

        }

        return $this->render("@Pidev/Programme/UpdateProgramme.html.twig",array('Programmes'=>$Programmes));

    }


    public function DeleteProgrammeAction($idprogramme){

        $em= $this->getDoctrine()->getManager();
        $Programmes= $this->getDoctrine()->getRepository(Programme::class)->find($idprogramme);
        $em->remove($Programmes);
        $em->flush();
        return $this->redirectToRoute("ReadProgramme");
    }




}