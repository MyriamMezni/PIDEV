<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
class BabysitterRepository extends EntityRepository
{
    public function rechercheStatus()
    {
        $query=$this->getEntityManager()->createQuery('SELECT c.idUser FROM PidevBundle:User c ');

        return $query->getResult();
    }

}