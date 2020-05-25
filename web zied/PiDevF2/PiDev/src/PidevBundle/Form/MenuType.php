<?php

namespace PidevBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class MenuType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nom')
                ->add('image',FileType::class)
                ->add('description',TextareaType::class)
                ->add('Jours',ChoiceType::class,[
                    'choices'=>[
                        'Lundi'=>2,
                        'Mardi'=>3,
                        'Mercredi'=>4,
                        'Jeudi'=>5,
                        'Vendredi'=>6,
                        'Samedi'=>7,
                        ],
                    'multiple'=>true,
                    'expanded'=>true,
                    'choice_attr'=>[
                        'class'=>'custom-control-input'
                    ]
                    ])
                ->add("Valider",SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Menu'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_menu';
    }


}
