<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inscrevt
 *
 * @ORM\Table(name="inscrevt")
 * @ORM\Entity
 */
class Inscrevt
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
     * @var integer
     *
     * @ORM\Column(name="id_enfant", type="integer", nullable=false)
     */
    private $idEnfant;


}
