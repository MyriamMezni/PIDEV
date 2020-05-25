<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * MenuCommande
 *
 * @ORM\Table(name="menu_commande", indexes={@ORM\Index(name="fk_enfant_menuCommande", columns={"id_enfant"})})
 * @ORM\Entity
 */
class MenuCommande
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_menu", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idMenu;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_enfant", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idEnfant;

    /**
     * @var string
     *
     * @ORM\Column(name="jour_de_la_semaine", type="string", length=7, nullable=true)
     */
    private $jourDeLaSemaine;

    /**
     * @return int
     */
    public function getIdMenu()
    {
        return $this->idMenu;
    }

    /**
     * @param int $idMenu
     */
    public function setIdMenu($idMenu)
    {
        $this->idMenu = $idMenu;
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




}

