<?php
namespace PidevBundle\Repository;
use Doctrine\ORM\EntityRepository;
use PidevBundle\Entity\User;

class UserRepository extends EntityRepository
{
    public function AfficherAdminsEtEmployes()
    {
        $q=$this->getEntityManager()->createQuery('SELECT u FROM PidevBundle:user u where u.role <> :role ')
        ->setParameter("role","Parent");
        return $q->getResult();
    }

    public function update(User $user)
    {
        $q=$this->getEntityManager()->createQuery('Update PidevBundle:User u set 
        u.username=:username,
        u.usernameCanonical=:usernameCanonical,
        u.email=:email,
        u.emailCanonical=:emailCanonical,
        u.password=:password,
        u.prenom=:prenom,
        u.nom=:nom,
        u.image=:image,
        u.mdp=:mdp,
        u.numtel=:numtel,
        u.datenaissance=:datenaissance,
        u.region=:region,
        u.ville=:ville,
        u.rue=:rue,
        u.codepostal=:codepostal 
        where u.id=:id')
            ->setParameter("username",$user->getUserName())
            ->setParameter("usernameCanonical",$user->getUsernameCanonical())
            ->setParameter("email",$user->getEmail())
            ->setParameter("emailCanonical",$user->getEmailCanonical())
            ->setParameter("password",$user->getPassword())
            ->setParameter("prenom",$user->getPrenom())
            ->setParameter("nom",$user->getNom())
            ->setParameter("image",$user->getImage())
            ->setParameter("mdp",$user->getMdp())
            ->setParameter("numtel",$user->getNumtel())
            ->setParameter("datenaissance",$user->getDatenaissance())
            ->setParameter("region",$user->getRegion())
            ->setParameter("ville",$user->getVille())
            ->setParameter("rue",$user->getRue())
            ->setParameter("codepostal",$user->getCodepostal())
            ->setParameter("id",$user->getId())->execute();

    }

}