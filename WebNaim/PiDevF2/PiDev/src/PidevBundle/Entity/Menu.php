<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Menu
 *
 * @ORM\Table(name="menu")
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\MenuRepository")
 */
class Menu
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
     * @ORM\Column(name="nom", type="string", length=200, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=200, nullable=true)
     */
    private $image;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=1000, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="jour_de_la_semaine", type="string", length=7, nullable=true)
     */
    private $jourDeLaSemaine;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Enfant", inversedBy="idMenu")
     * @ORM\JoinTable(name="menu_commande",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_menu", referencedColumnName="id")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="id_enfant", referencedColumnName="id_enfant")
     *   }
     * )
     */
    private $idEnfant;
    /**
     * @var array
     */

    private $Jours;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->idEnfant = new \Doctrine\Common\Collections\ArrayCollection();
        $this->Jours=array();
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
    public function getJourDeLaSemaine()
    {
        return $this->jourDeLaSemaine;
    }

    /**
     * @param string $jourDeLaSemaine
     */
    public function setJourDeLaSemaine($jourDeLaSemaine)
    {
        $this->jourDeLaSemaine = $jourDeLaSemaine;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getIdEnfant()
    {
        return $this->idEnfant;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $idEnfant
     */
    public function setIdEnfant($idEnfant)
    {
        $this->idEnfant = $idEnfant;
    }

    /**
     * @return array
     */
    public function getJours()
    {
        return $this->Jours;
    }

    /**
     * @param array $Jours
     */
    public function setJours($Jours)
    {
        $this->Jours = $Jours;
    }

    public function transferer()
    {
        $this->jourDeLaSemaine="";
        foreach ($this->Jours as $j)
        {
            $this->jourDeLaSemaine=$this->jourDeLaSemaine.$j;

        }
    }
    public function transfererInverse()
    {
        $this->Jours=str_split($this->jourDeLaSemaine);
    }




}

