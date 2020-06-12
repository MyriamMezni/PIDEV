<?php


namespace PidevBundle\Controller;


use Ob\HighchartsBundle\Highcharts\Highchart;
use PidevBundle\Entity\Activite;
use PidevBundle\Entity\Ratingactivite;
use PidevBundle\Form\ActiviteType;
use PidevBundle\Form\RechercheActiviteType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class ActiviteController extends Controller
{
public function ReadActiviteAction()
{
    $Activites=$this->getDoctrine()->getRepository(Activite::class)->findAll();
    return $this->render('@Pidev/Activite/ReadActivite.html.twig',array('Activites'=>$Activites));

}

public function CreateActiviteAction(Request $request)
{

    $Activite= new Activite();
    $form=$this->createForm(ActiviteType::class,$Activite);
    $form=$form->handleRequest($request);
    if($form->isValid())
    {
        $em=$this->getDoctrine()->getManager();
        $em->persist($Activite);
        $em->flush();

        return $this->redirectToRoute('ReadActivite');
    }

    return $this->render('@Pidev/Activite/CreateActivite.html.twig',array('form'=>$form->createView()));

}


public function UpdateActiviteAction(Request $request,$idactivite)
{

    $em=$this->getDoctrine()->getManager();
    $Activites=$em->getRepository(Activite::class)->find($idactivite);

    if($request->isMethod('POST'))
    {
        $Activites->setIntitule($request->get('intitule'));
        $Activites->setNiveau($request->get('niveau'));
        $Activites->setResponsable($request->get('responsable'));
        $Activites->setType($request->get('type'));
        $Activites->setHeuredebut($request->get('heuredebut'));
        $Activites->setHeurefin($request->get('heurefin'));

        $em->flush();

        return $this->redirectToRoute('ReadActivite');

    }

    return $this->render('@Pidev/Activite/UpdateActivite.html.twig',array('Activites'=>$Activites));

}



    public function DeleteActiviteAction($idactivite)
    {
        $em = $this->getDoctrine()->getManager();
        $Activites=$em->getRepository(Activite::class)->find($idactivite);

        $em->remove($Activites);
        $em->flush();

        return $this->redirectToRoute('ReadActivite');
    }

    public function ReadActiviteFrontAction()
    {

        $Activites=$this->getDoctrine()->getRepository(Activite::class)->findAll();
        return $this->render('@Pidev/Activite/ReadActiviteFront.html.twig',array('Activites'=>$Activites));

    }

    public function searchAction(Request $request)
    {

        $voiture= new Activite();
        $form= $this->createForm(RechercheActiviteType::class,$voiture);
        $form->handleRequest($request);
        if($form->isSubmitted()){


                $voitures = $this->getDoctrine()->getRepository(Activite::class)
                    ->findBy(array('intitule' => $voiture->getIntitule()));

        }
        else{
            $voitures= $this->getDoctrine()->getRepository(Activite::class)
                ->findAll();
        }
        return $this->render("@Pidev/Activite/RechActiviteFront.html.twig",array("form"=>$form->createView(),'Activites'=>$voitures));

    }


    public function PlusInfoActiviteAction(Request $request,$intitule)
    {


        $em=$this->getDoctrine()->getManager();
        $Activites=$em->getRepository(Activite::class)->findOneByIntitule($intitule);
        return $this->render('@Pidev/Activite/InfoActivite.html.twig',array('Activites'=>$Activites));

    }



    public function SearchIntituleAction(Request $request)
    {

        $voiture= new Activite();
        $form= $this->createForm(RechercheActiviteType::class,$voiture);
        $form->handleRequest($request);
        if($form->isSubmitted()){


            $voitures = $this->getDoctrine()->getRepository(Activite::class)
                ->findBy(array('intitule' => $voiture->getIntitule()));

        }
        else{
            $voitures= $this->getDoctrine()->getRepository(Activite::class)
                ->findAll();
        }
        return $this->render("@Pidev/Activite/RechActiviteIntituleFront.html.twig",array("form"=>$form->createView(),'Activites'=>$voitures));

    }
    public function SearchResponsableAction(Request $request)
    {

        $voiture= new Activite();
        $form= $this->createForm(RechercheActiviteType::class,$voiture);
        $form->handleRequest($request);
        if($form->isSubmitted()){


            $voitures = $this->getDoctrine()->getRepository(Activite::class)
                ->findBy(array('intitule' => $voiture->getIntitule()));

        }
        else{
            $voitures= $this->getDoctrine()->getRepository(Activite::class)
                ->findAll();
        }
        return $this->render("@Pidev/Activite/RechActiviteResponsableFront.html.twig",array("form"=>$form->createView(),'Activites'=>$voitures));

    }
    public function SearchHeureAction(Request $request)
    {



        $voiture= new Activite();
        $form= $this->createForm(RechercheActiviteType::class,$voiture);
        $form->handleRequest($request);
        if($form->isSubmitted()){


            $voitures = $this->getDoctrine()->getRepository(Activite::class)
                ->findBy(array('intitule' => $voiture->getIntitule()));

        }
        else{
            $voitures= $this->getDoctrine()->getRepository(Activite::class)
                ->findAll();
        }
        return $this->render("@Pidev/Activite/RechActiviteHeureFront.html.twig",array("form"=>$form->createView(),'Activites'=>$voitures));

    }



    public function StatFrontAction()
    {
    $rate=new Ratingactivite();

        $ob=new Highchart();
        $ob->chart->renderTo('linechart');
        $ob->title->text('Statistique des activités selon notes des parents ');
        $ob->plotOptions->pie(array('allowPointSelect'=>true,'cursor'=>'pointer','dataLabels'=>array('enabled'=>false),'showInlegend'=>true));

        $em=$this->getDoctrine()->getEntityManager();
        $query=$em->createQuery('Select AVG(p.rate) as Rate,p.intitule as Intitule FROM PidevBundle:Ratingactivite p group by p.intitule  ');
    $resultat=$query->getResult();
    $data=array();
foreach ($resultat as $values)
{

    $a=array($values['Intitule'],intval($values['Rate']));
    array_push($data,$a);
}
        $ob->series(array(array('type'=>'pie','name'=>'Stat','data'=>$data)));
    return $this->render("@Pidev/Activite/StatFront.html.twig",array('chart'=>$ob));
    }







    public function allAction()
    {
        $activite=$this->getDoctrine()->getManager()->getRepository('PidevBundle:Activite')->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($activite);

        return new JsonResponse($formatted);
    }

    public function findAction($intitule)
    {

        $em=$this->getDoctrine()->getManager();
        $activite=$em->getRepository(Activite::class)->findOneByIntitule($intitule);
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($activite);
        return new JsonResponse($formatted);
    }




}