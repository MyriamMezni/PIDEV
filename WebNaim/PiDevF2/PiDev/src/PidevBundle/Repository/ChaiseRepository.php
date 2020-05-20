<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
class ChaiseRepository extends EntityRepository
{
   /* public function AfficherNbChaisesParTable()
    {
        //$q=$this->getEntityManager()->createQuery('SELECT count(c.idChaise),t.idTable FROM PidevBundle:Chaise c RIGHT OUTER JOIN c.numTable t group by t.idTable');
        $q=$this->createQueryBuilder('c')->leftJoin('c.numTable','t')->groupBy('t.idTable')->getQuery();
        return $q->getResult();
    }*/
}