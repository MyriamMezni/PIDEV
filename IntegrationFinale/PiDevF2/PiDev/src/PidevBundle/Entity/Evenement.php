<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Evenement
 *
 * @ORM\Table(name="evenement")
 * @ORM\Entity
 */
class Evenement
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
     * @ORM\Column(name="capacite", type="string", length=11, nullable=false)
     */
    private $capacite;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=55, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="heure_d", type="string", length=15, nullable=false)
     */
    private $heureD;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=55, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="date_evt", type="string", length=15, nullable=false)
     */
    private $dateEvt;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=55, nullable=false)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=12, nullable=false)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="depart", type="string", length=25, nullable=true)
     */
    private $depart;

    /**
     * @var string
     *
     * @ORM\Column(name="destination", type="string", length=25, nullable=true)
     */
    private $destination;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=25, nullable=true)
     */
    private $lieu;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Enfant", inversedBy="id")
     * @ORM\JoinTable(name="inscrevt",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_enfant", referencedColumnName="id_enfant")
     *   }
     * )
     */
    private $idEnfant;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->idEnfant = new \Doctrine\Common\Collections\ArrayCollection();
    }

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
    public function getCapacite()
    {
        return $this->capacite;
    }

    /**
     * @param string $capacite
     */
    public function setCapacite($capacite)
    {
        $this->capacite = $capacite;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getHeureD()
    {
        return $this->heureD;
    }

    /**
     * @param string $heureD
     */
    public function setHeureD($heureD)
    {
        $this->heureD = $heureD;
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
    public function getDateEvt()
    {
        return $this->dateEvt;
    }

    /**
     * @param string $dateEvt
     */
    public function setDateEvt($dateEvt)
    {
        $this->dateEvt = $dateEvt;
    }

    /**
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * @param string $image
     */
    public function setImage($image)
    {
        $this->image = $image;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getDepart()
    {
        return $this->depart;
    }

    /**
     * @param string $depart
     */
    public function setDepart($depart)
    {
        $this->depart = $depart;
    }

    /**
     * @return string
     */
    public function getDestination()
    {
        return $this->destination;
    }

    /**
     * @param string $destination
     */
    public function setDestination($destination)
    {
        $this->destination = $destination;
    }

    /**
     * @return string
     */
    public function getLieu()
    {
        return $this->lieu;
    }

    /**
     * @param string $lieu
     */
    public function setLieu($lieu)
    {
        $this->lieu = $lieu;
    }





}

