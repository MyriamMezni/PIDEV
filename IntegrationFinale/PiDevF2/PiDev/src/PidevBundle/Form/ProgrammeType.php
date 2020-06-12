<?php

namespace PidevBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProgrammeType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('cours',FileType::class,array('label'=>'choisir un fichier','data_class'=>null))->add('debut',ChoiceType::class,array('choices'=>array('Janvier' =>'Janvier','Fevrier'=>'Fevrier','Mars'=>'Mars','Avril'=>'Avril','Mai'=>'Mai','Juin'=>'Juin','Juillet'=>'Juillet','Aout'=>'Aout','Septembre'=>'Septembre','Octobre'=>'Octobre','Novembre'=>'Novembre','Decembre'=>'Decembre')))->add('fin',ChoiceType::class,array('choices'=>array('Janvier' =>'Janvier','Fevrier'=>'Fevrier','Mars'=>'Mars','Avril'=>'Avril','Mai'=>'Mai','Juin'=>'Juin','Juillet'=>'Juillet','Aout'=>'Aout','Septembre'=>'Septembre','Octobre'=>'Octobre','Novembre'=>'Novembre','Decembre'=>'Decembre')))->add('idactivite',EntityType::class,array('class'=>'PidevBundle:Activite','choice_label'=>'intitule','multiple'=>false))->add('ajouter',SubmitType::class);
      }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Programme'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_programme';
    }


}
