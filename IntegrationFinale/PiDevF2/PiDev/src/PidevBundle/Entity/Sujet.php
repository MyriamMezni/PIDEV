<?php

namespace PidevBundle\Entity;

//use Pidev\Entity\Notification;
use Doctrine\ORM\Mapping as ORM;
use SBC\NotificationsBundle\Builder\NotificationBuilder;
use SBC\NotificationsBundle\Model\NotifiableInterface;

/**
 * Sujet
 *
 * @ORM\Table(name="sujet", indexes={@ORM\Index(name="fk_sujet_user", columns={"id_user"})})
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\SujetRepository")
 */
class Sujet implements NotifiableInterface, \JsonSerializable
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Titre", type="string", length=500, nullable=false)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=2000, nullable=true)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Categorie", type="string", length=500, nullable=false)
     */
    private $categorie;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateCreation", type="datetime", nullable=false)
     */
    private $datecreation;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateModification", type="datetime", nullable=true)
     */
    private $datemodification;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_user", referencedColumnName="IdUser")
     * })
     */
    private $idUser;

    /**
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * @param string $titre
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param string $categorie
     */
    public function setCategorie($categorie)
    {
        $this->categorie = $categorie;
    }

    /**
     * @return \DateTime
     */
    public function getDatecreation()
    {
        return $this->datecreation;
    }

    /**
     * @param \DateTime $datecreation
     */
    public function setDatecreation($datecreation)
    {
        $this->datecreation = $datecreation;
    }

    /**
     * @return \DateTime
     */
    public function getDatemodification()
    {
        return $this->datemodification;
    }

    /**
     * @param \DateTime $datemodification
     */
    public function setDatemodification($datemodification)
    {
        $this->datemodification = $datemodification;
    }

    /**
     * @return \User
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * @param \User $idUser
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;
    }


    /**
     * Specify data which should be serialized to JSON
     * @link https://php.net/manual/en/jsonserializable.jsonserialize.php
     * @return mixed data which can be serialized by <b>json_encode</b>,
     * which is a value of any type other than a resource.
     * @since 5.4.0
     */
    public function jsonSerialize()
    {
        return get_object_vars($this);
    }

    /**
     * Build notifications on entity creation
     * @param NotificationBuilder $builder
     * @return NotificationBuilder
     */
    public function notificationsOnCreate(NotificationBuilder $builder)
    {
        /*$notification = new Notification();
        $notification
            ->setTitle('Nouveau Sujet!')
            ->setDescription($this->getIdUser()->getPrenom()." ".$this->getIdUser()->getNom()." a ajouté un nouveau sujet")
            ->setRoute("SetSeen")
            ->setParameters(array("sujetId"=>$this->getId(),"userid"=>$this->getIdUser()->getId()))
            ->setIcon($this->getIdUser()->getImage())
        ;
        $builder->addNotification($notification);*/

        return $builder;
    }

    /**
     * Build notifications on entity update
     * @param NotificationBuilder $builder
     * @return NotificationBuilder
     */
    public function notificationsOnUpdate(NotificationBuilder $builder)
    {
        //$date=new \DateTime('now', new \DateTimeZone('Africa/Tunis'));
        return $builder;
    }

    /**
     * Build notifications on entity delete
     * @param NotificationBuilder $builder
     * @return NotificationBuilder
     */
    public function notificationsOnDelete(NotificationBuilder $builder)
    {
        return $builder;
    }
}

