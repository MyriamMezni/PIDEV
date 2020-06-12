<?php

namespace PidevBundle\Form;

use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TimeType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ActiviteType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('intitule')->add('niveau',ChoiceType::class,array('choices'=>array('3 ans' =>'3 ans','4 ans'=>'4 ans','5 ans'=>'5 ans')))->add('responsable',EntityType::class,array('class'=>'PidevBundle:User','choice_label'=>'prenom','multiple'=>false))->add('type',ChoiceType::class,array('choices'=>array('Education' =>'Education','Loisirs'=>'Loisirs')))->add('heuredebut',TimeType::class,array('input'  => 'string'))->add('heurefin',TimeType::class,array(
            'input'  => 'string'))->add('Ajouter',SubmitType::class);    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Activite'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_activite';
    }


}
