<?php

namespace PidevBundle\Form;

use Doctrine\ORM\EntityRepository;
use PidevBundle\Entity\Chaise;
use PidevBundle\Entity\TableCantine;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ChaiseType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('numTable',EntityType::class,[
            'class'=>TableCantine::class,
            'query_builder' => function (EntityRepository $er) {
                return $er->createQueryBuilder('t')
                    ->where('t.capacite>:c')
                    ->setParameter('c',0);
            },
            'choice_label'=>function ($table) {
                return "Table №".$table->getIdTable();
            }
        ])->add('Valider',SubmitType::class);
    }/**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'PidevBundle\Entity\Chaise'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'pidevbundle_chaise';
    }


}
