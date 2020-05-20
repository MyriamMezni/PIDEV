<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
class SujetRepository extends EntityRepository
{
    public function AfficherSujetEtUser()
    {
        $q=$this->getEntityManager()->createQuery('SELECT s FROM PidevBundle:Sujet s JOIN s.idUser ');
        return $q->getResult();
    }
}