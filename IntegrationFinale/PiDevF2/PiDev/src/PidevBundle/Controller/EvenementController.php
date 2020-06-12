<?php


namespace PidevBundle\Controller;


use PidevBundle\Entity\Evenement;
use PidevBundle\Form\EvenementRechType;
use PidevBundle\Form\EvenementType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\FormType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\HttpFoundation\Request;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Symfony\Component\HttpFoundation\Response;

class EvenementController extends Controller
{

    public function CreateExcursionAction(Request $request)
    {



        $Evenements= new Evenement();


        $formBuilder = $this->get('form.factory')->createBuilder(FormType::class, $Evenements);

        $formBuilder
            ->add('capacite',     ChoiceType::class,array('choices'=>array('25'=>'25','50'=>'50','75'=>'75')))
            ->add('nom',     TextType::class)
            ->add('heureD',   TimeType::class,array('input'  => 'string'))
            ->add('description',    TextType::class)
            ->add('dateEvt', DateType::class,array('input'  => 'string'))
            ->add('image',      FileType::class)
           ->add('type',     ChoiceType::class,array('choices'=>array('Excursion'=>'Excursion','fete'=>'fete')))
            ->add('depart',ChoiceType::class,array('choices'=>array('Rades' =>'Rades','Gabes'=>'Gabes','Hammamet' =>'Hammamet','Tozeur'=>'Tozeur')))
            ->add('destination',ChoiceType::class,array('choices'=>array('Rades' =>'Rades','Gabes'=>'Gabes','Hammamet' =>'Hammamet','Tozeur'=>'Tozeur')))
            ->add('Ajouter',SubmitType::class)

        ;

        $form = $formBuilder->getForm();


        

        $form=$form->handleRequest($request);
        $directory=$this->get('kernel')->getProjectDir()."\web\images\\";

        if($form->isValid())
        {
            $file=$form['image']->getData();
            $extension = $file->guessExtension();
            if (!$extension) {
                // extension cannot be guessed
                $extension = 'bin';
            }
            $name=rand(1, 99999).'.'.$extension;
            $Evenements->setImage($name);
            $file->move($directory, $name);

            $em=$this->getDoctrine()->getManager();
            $em->persist($Evenements);
            $em->flush();
            return $this->redirectToRoute('ReadExcursion');
        }
        return $this->render('@Pidev/Evenement/CreateExcursion.html.twig',array('form'=>$form->createView()));


    }


    public function ReadExcursionAction()
    {
        $Excursion=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
        return $this->render('@Pidev/Evenement/ReadExcursion.html.twig',array('Excursion'=>$Excursion));

    }

    public function ReadExcursionFrontAction()
    {
        $Excursion=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
        return $this->render('@Pidev/Evenement/ReadExcursionFront.html.twig',array('Excursion'=>$Excursion));


    }







    public function DeleteExcursionAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $Excursion=$em->getRepository(Evenement::class)->find($id);

        $em->remove($Excursion);
        $em->flush();

        return $this->redirectToRoute('ReadExcursion');
    }






    public function UpdateExcursionAction(Request $request,$id)
    {

        $em=$this->getDoctrine()->getManager();
        $Excursion=$em->getRepository(Evenement::class)->find($id);

        if($request->isMethod('POST'))
        {
            $Excursion->setCapacite($request->get('capacite'));
            $Excursion->setNom($request->get('nom'));
            $Excursion->setHeureD($request->get('heureD'));
            $Excursion->setDateEvt($request->get('dateEvt'));
            $Excursion->setImage($request->get('image',FileType::class));
            $Excursion->setType($request->get('type'));
            $Excursion->setDepart($request->get('depart'));
            $Excursion->setDestination($request->get('destination'));


            $em->flush();

            return $this->redirectToRoute('ReadExcursion');

        }

        return $this->render('@Pidev/Evenement/UpdateExcursion.html.twig',array('Excursion'=>$Excursion));

    }



public function  CreateFeteAction(Request $request)
{

    $Evenements= new Evenement();


    $formBuilder = $this->get('form.factory')->createBuilder(FormType::class, $Evenements);

    $formBuilder
        ->add('capacite',     ChoiceType::class,array('choices'=>array('25'=>'25','50'=>'50','75'=>'75')))
        ->add('nom',     TextType::class)
        ->add('heureD',   TimeType::class,array('input'  => 'string'))
        ->add('description',    TextType::class)
        ->add('dateEvt', DateType::class,array('input'  => 'string'))
        ->add('image',      FileType::class)
        ->add('type',     ChoiceType::class,array('choices'=>array('fete'=>'fete')))
        ->add('lieu',ChoiceType::class,array('choices'=>array('Rades' =>'Rades','Megrine'=>'Megrine','Marsa' =>'Marsa','Lac'=>'Lac')))

        ->add('Ajouter',SubmitType::class)

    ;

    $form = $formBuilder->getForm();




    $form=$form->handleRequest($request);
    $directory=$this->get('kernel')->getProjectDir()."\web\images\\";
    if($form->isValid())
    {$file=$form['image']->getData();
        $extension = $file->guessExtension();
        if (!$extension) {
            // extension cannot be guessed
            $extension = 'bin';
        }
        $name=rand(1, 99999).'.'.$extension;
        $Evenements->setImage($name);
        $file->move($directory, $name);
        $em=$this->getDoctrine()->getManager();
        $em->persist($Evenements);
        $em->flush();
        return $this->redirectToRoute('ReadFete');
    }
    return $this->render('@Pidev/Evenement/CreateFete.html.twig',array('form'=>$form->createView()));


}

public function ReadFeteAction()
{
    $Fete=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
    return $this->render('@Pidev/Evenement/ReadFete.html.twig',array('Fete'=>$Fete));

}

public function DeleteFeteAction($id)
{
    $em = $this->getDoctrine()->getManager();
    $Fete=$em->getRepository(Evenement::class)->find($id);

    $em->remove($Fete);
    $em->flush();

    return $this->redirectToRoute('ReadFete');

}
public function UpdateFeteAction(Request $request,$id)
{

    $em=$this->getDoctrine()->getManager();
    $Fete=$em->getRepository(Evenement::class)->find($id);

    if($request->isMethod('POST'))
    {
        $Fete->setCapacite($request->get('capacite'));
        $Fete->setNom($request->get('nom'));
        $Fete->setHeureD($request->get('heureD'));
        $Fete->setDateEvt($request->get('dateEvt'));
        $Fete->setImage($request->get('image',FileType::class));
        $Fete->setType($request->get('type'));
        $Fete->setDepart($request->get('lieu'));




        $em->flush();

        return $this->redirectToRoute('ReadFete');

    }
    return $this->render('@Pidev/Evenement/UpdateFete.html.twig',array('fete'=>$Fete));




}



public function RechercheEvenementAction(Request $request)
{

    $voiture= new Evenement();
    $form= $this->createForm(EvenementRechType::class,$voiture);
    $form->handleRequest($request);
    if($form->isSubmitted()){


        $voitures = $this->getDoctrine()->getRepository(Evenement::class)
            ->findBy(array('type' => $voiture->getType()));

    }
    else{
        $voitures= $this->getDoctrine()->getRepository(Evenement::class)
            ->findAll();
    }
    return $this->render("@Pidev/Evenement/ReadExcursionFront.html.twig",array("form"=>$form->createView(),'Excursion'=>$voitures));


}





public function PdfGeneratorAction()
{
    $Fete=$this->getDoctrine()->getRepository(Evenement::class)->findAll();
    $snappy=$this->get('knp_snappy.pdf');
    $html=$this->renderView('@Pidev/Evenement/pdfgenerate.html.twig',array('rootDir'=>$this->get('kernel')->getProjectDir()."", "title"=>"Kidzy Events : ",'fete'=>$Fete));
    $filename="pdf";
    return new Response($snappy->getOutPutFromHtml($html),200,array('Content-Type'=>'application/pdf','Content-Disposition'=>'inline; filename="'.$filename.'".pdf'));
}



}