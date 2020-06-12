<?php


namespace PidevBundle\Repository;

use PidevBundle\Entity\Activite;
use Doctrine\ORM\Query;
use Doctrine\ORM\EntityRepository;

class ActiviteRepository extends EntityRepository
{

    public function findEntitiesByString($str)
    {

        return $this->getEntityManager()->createQuery('select p from PidevBundle:Activite p where p.intitule LIKE :str')->setParameter('str','%'.$str.'%')->getResult();
    }
}