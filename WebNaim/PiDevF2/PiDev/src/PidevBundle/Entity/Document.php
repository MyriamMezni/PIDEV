<?php

namespace PidevBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Document
 *
 * @ORM\Table(name="document", indexes={@ORM\Index(name="fk_enfant_document", columns={"id_enfant"})})
 * @ORM\Entity
 */
class Document
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_document", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idDocument;

    /**
     * @var string
     *
     * @ORM\Column(name="source", type="string", length=500, nullable=false)
     */
    private $source;

    /**
     * @var integer
     *
     * @ORM\Column(name="id_enfant", type="integer", nullable=false)
     */
    private $idEnfant;


}

