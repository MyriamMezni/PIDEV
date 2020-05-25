<?php

namespace PidevBundle\Entity;

use FOS\UserBundle\Model\User as BaseUser;

use Doctrine\ORM\Mapping as ORM;

/**
 * User
 *
 * @ORM\Table(name="user", uniqueConstraints={@ORM\UniqueConstraint(name="UNIQ_957A647992FC23A8", columns={"username_canonical"}), @ORM\UniqueConstraint(name="UNIQ_957A6479A0D96FBF", columns={"email_canonical"}), @ORM\UniqueConstraint(name="UNIQ_957A6479C05FB297", columns={"confirmation_token"})})
 * @ORM\Entity(repositoryClass="PidevBundle\Repository\UserRepository")
 */
class User extends BaseUser
{
    /**
     * @ORM\Id
     * @ORM\Column(name="IdUser",type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Prenom", type="string", length=100, nullable=false)
     */
    protected $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="Nom", type="string", length=100, nullable=false)
     */
    protected $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=500, nullable=true)
     */
    protected $image;


    /**
     * @var string
     *
     * @ORM\Column(name="mdp", type="string", length=500, nullable=false)
     */
    protected $mdp;


    /**
     * @var integer
     *
     * @ORM\Column(name="NumTel", type="integer", nullable=false)
     */
    protected $numtel;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="DateNaissance", type="date", nullable=true)
     */
    protected $datenaissance;

    /**
     * @var integer
     *
     * @ORM\Column(name="Salaire", type="integer", nullable=true)
     */
    protected $salaire;

    /**
     * @var string
     *
     * @ORM\Column(name="Region", type="string", length=100, nullable=true)
     */
    protected $region;

    /**
     * @var string
     *
     * @ORM\Column(name="Ville", type="string", length=100, nullable=true)
     */
    protected $ville;

    /**
     * @var string
     *
     * @ORM\Column(name="Rue", type="string", length=100, nullable=true)
     */
    protected $rue;

    /**
     * @var string
     *
     * @ORM\Column(name="CodePostal", type="string", length=100, nullable=true)
     */
    protected $codepostal;

    /**
     * @var integer
     *
     * @ORM\Column(name="NbHeures", type="integer", nullable=true)
     */
    protected $nbheures;

    /**
     * @var string
     *
     * @ORM\Column(name="TypeEmploye", type="string", length=100, nullable=true)
     */
    protected $typeemploye;

    /**
     * @var integer
     *
     * @ORM\Column(name="Tarif", type="integer", nullable=true)
     */
    protected $tarif;

    /**
     * @var integer
     *
     * @ORM\Column(name="NbEnfant", type="integer", nullable=true)
     */
    protected $nbenfant;

    /**
     * @var string
     *
     * @ORM\Column(name="role", type="string", length=100, nullable=false)
     */
    protected $role;

    public function __construct()
    {
        parent::__construct();
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
    public function getMdp()
    {
        return $this->mdp;
    }

    /**
     * @param string $mdp
     */
    public function setMdp($mdp)
    {
        $this->mdp = $mdp;
    }



    /**
     * @return int
     */
    public function getNumtel()
    {
        return $this->numtel;
    }

    /**
     * @param int $numtel
     */
    public function setNumtel($numtel)
    {
        $this->numtel = $numtel;
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
     * @return int
     */
    public function getSalaire()
    {
        return $this->salaire;
    }

    /**
     * @param int $salaire
     */
    public function setSalaire($salaire)
    {
        $this->salaire = $salaire;
    }

    /**
     * @return string
     */
    public function getRegion()
    {
        return $this->region;
    }

    /**
     * @param string $region
     */
    public function setRegion($region)
    {
        $this->region = $region;
    }

    /**
     * @return string
     */
    public function getVille()
    {
        return $this->ville;
    }

    /**
     * @param string $ville
     */
    public function setVille($ville)
    {
        $this->ville = $ville;
    }

    /**
     * @return string
     */
    public function getRue()
    {
        return $this->rue;
    }

    /**
     * @param string $rue
     */
    public function setRue($rue)
    {
        $this->rue = $rue;
    }

    /**
     * @return string
     */
    public function getCodepostal()
    {
        return $this->codepostal;
    }

    /**
     * @param string $codepostal
     */
    public function setCodepostal($codepostal)
    {
        $this->codepostal = $codepostal;
    }

    /**
     * @return int
     */
    public function getNbheures()
    {
        return $this->nbheures;
    }

    /**
     * @param int $nbheures
     */
    public function setNbheures($nbheures)
    {
        $this->nbheures = $nbheures;
    }

    /**
     * @return string
     */
    public function getTypeemploye()
    {
        return $this->typeemploye;
    }

    /**
     * @param string $typeemploye
     */
    public function setTypeemploye($typeemploye)
    {
        $this->typeemploye = $typeemploye;
    }

    /**
     * @return int
     */
    public function getTarif()
    {
        return $this->tarif;
    }

    /**
     * @param int $tarif
     */
    public function setTarif($tarif)
    {
        $this->tarif = $tarif;
    }

    /**
     * @return int
     */
    public function getNbenfant()
    {
        return $this->nbenfant;
    }

    /**
     * @param int $nbenfant
     */
    public function setNbenfant($nbenfant)
    {
        $this->nbenfant = $nbenfant;
    }

    /**
     * @return string
     */
    public function getRole()
    {
        return $this->role;
    }

    /**
     * @param string $role
     */
    public function setRole($role)
    {
        $this->role = $role;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }



}

