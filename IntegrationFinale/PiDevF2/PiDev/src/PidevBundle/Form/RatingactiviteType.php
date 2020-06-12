<?php

namespace PidevBundle\Form;

use blackknight467\StarRatingBundle\Form\RatingType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RatingactiviteType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('intitule',EntityType::class,array('class'=>'PidevBundle:Activite','choice_label'=>'intitule','multiple'=>false))->add('rate',RatingType::class,['label'=>'Rating'])->add('Ajouter',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Ratingactivite'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_ratingactivite';
    }


}
