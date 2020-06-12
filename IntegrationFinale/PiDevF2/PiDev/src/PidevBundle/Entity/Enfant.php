<?php

namespace PidevBundle\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\ORM\Mapping as ORM;

/**
 * Enfant
 *
 * @ORM\Table(name="enfant", indexes={@ORM\Index(name="fk_parent", columns={"id_parent"})})
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\EnfantRepository")
 */
class Enfant
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_enfant", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEnfant;

    /**
     * @var string
     *@Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="votre nom ne peut pas contenir a number"
     * )
     * @ORM\Column(name="nom", type="string", length=100, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @Assert\Regex(
     *     pattern="/\d/",
     *     match=false,
     *     message="votre prenom ne peut pas contenir a number"
     * )
     * @ORM\Column(name="prenom", type="string", length=100, nullable=false)
     */
    private $prenom;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datenaissance", type="date", nullable=false)
     */
    private $datenaissance;

    /**
     * @var string
     *
     * @ORM\Column(name="remarque", type="string", length=500, nullable=false)
     */
    private $remarque;

    /**
     * @var string
     *
     * @Assert\Image(
     *     minWidth = 100,
     *     maxWidth = 1500,
     *     minHeight = 100,
     *     maxHeight = 1500,
     *     mimeTypes = {"image/jpeg", "image/gif", "image/png"},
     *     mimeTypesMessage = "Le fichier choisi ne correspond pas à une image valide"
     * )
     * @ORM\Column(name="image", type="string", length=500, nullable=true)
     */
    private $image;

    /**
     * @var boolean
     *
     * @ORM\Column(name="cantine", type="boolean", nullable=false)
     */
    private $cantine;

    /**
     * @var string
     *
     * @Assert\File(
     *     maxSize = "10000k",
     *     mimeTypes = {"application/pdf", "application/x-pdf"},
     *     mimeTypesMessage = "Please upload a valid PDF"
     * )
     * @ORM\Column(name="document", type="string", length=255, nullable=false)
     */
    private $document;

    /**
     * @var string
     *
     * @ORM\Column(name="status", type="string", length=30, nullable=false)
     */
    private $status="en attente";

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_parent", referencedColumnName="IdUser")
     * })
     */
    private $idParent;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="User", inversedBy="idEnfant")
     * @ORM\JoinTable(name="babysitter",
     *   joinColumns={
     *     @ORM\JoinColumn(name="id_enfant", referencedColumnName="id_enfant")
     *   },
     *   inverseJoinColumns={
     *     @ORM\JoinColumn(name="idBabysitter", referencedColumnName="IdUser")
     *   }
     * )
     */
    private $idbabysitter;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Evenement", mappedBy="idEnfant")
     */
    private $id;

    /**
     * @var \Doctrine\Common\Collections\Collection
     *
     * @ORM\ManyToMany(targetEntity="Menu", mappedBy="idEnfant")
     */
    private $idMenu;

    /**
     * Constructor
     */
    public function __construct()
    {
        $this->idbabysitter = new \Doctrine\Common\Collections\ArrayCollection();
        $this->id = new \Doctrine\Common\Collections\ArrayCollection();
        $this->idMenu = new \Doctrine\Common\Collections\ArrayCollection();
    }

    /**
     * @return int
     */
    public function getIdEnfant()
    {
        return $this->idEnfant;
    }

    /**
     * @param int $idEnfant
     */
    public function setIdEnfant($idEnfant)
    {
        $this->idEnfant = $idEnfant;
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
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return \DateTime
     */
    public function getDatenaissance()
    {
        return $this->datenaissance;
    }

    /**
     * @param \DateTime $datenaissance
     */
    public function setDatenaissance($datenaissance)
    {
        $this->datenaissance = $datenaissance;
    }

    /**
     * @return string
     */
    public function getRemarque()
    {
        return $this->remarque;
    }

    /**
     * @param string $remarque
     */
    public function setRemarque($remarque)
    {
        $this->remarque = $remarque;
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
     * @return bool
     */
    public function isCantine()
    {
        return $this->cantine;
    }

    /**
     * @param bool $cantine
     */
    public function setCantine($cantine)
    {
        $this->cantine = $cantine;
    }

    /**
     * @return string
     */
    public function getDocument()
    {
        return $this->document;
    }

    /**
     * @param string $document
     */
    public function setDocument($document)
    {
        $this->document = $document;
    }

    /**
     * @return string
     */
    public function getStatus()
    {
        return $this->status;
    }

    /**
     * @param string $status
     */
    public function setStatus($status)
    {
        $this->status = $status;
    }

    /**
     * @return \User
     */
    public function getIdParent()
    {
        return $this->idParent;
    }

    /**
     * @param \User $idParent
     */
    public function setIdParent($idParent)
    {
        $this->idParent = $idParent;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getIdbabysitter()
    {
        return $this->idbabysitter;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $idbabysitter
     */
    public function setIdbabysitter($idbabysitter)
    {
        $this->idbabysitter = $idbabysitter;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getIdMenu()
    {
        return $this->idMenu;
    }

    /**
     * @param \Doctrine\Common\Collections\Collection $idMenu
     */
    public function setIdMenu($idMenu)
    {
        $this->idMenu = $idMenu;
    }



}

