<?php

namespace PidevBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EvenementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('capacite')->add('nom')->add('heureD')->add('description')->add('dateEvt')->add('image',FileType::class)->add('type')->add('depart',ChoiceType::class,array('choices'=>array('Rades' =>'Rades','Gabes'=>'Gabes','Hammamet' =>'Hammamet','Tozeur'=>'Tozeur')))->add('destination',ChoiceType::class,array('choices'=>array('Rades' =>'Rades','Gabes'=>'Gabes','Hammamet' =>'Hammamet','Tozeur'=>'Tozeur')))->add('lieu')->add('Ajouter',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Evenement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_evenement';
    }


}
