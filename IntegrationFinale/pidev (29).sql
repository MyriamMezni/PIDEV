-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 12 juin 2020 à 13:12
-- Version du serveur :  5.7.24
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `IdActivite` int(11) NOT NULL AUTO_INCREMENT,
  `Intitule` varchar(30) NOT NULL,
  `Niveau` varchar(30) NOT NULL,
  `Responsable` varchar(30) NOT NULL,
  `Type` varchar(30) NOT NULL,
  `HeureDebut` longtext NOT NULL,
  `HeureFin` varchar(30) NOT NULL,
  PRIMARY KEY (`IdActivite`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `activite`
--

INSERT INTO `activite` (`IdActivite`, `Intitule`, `Niveau`, `Responsable`, `Type`, `HeureDebut`, `HeureFin`) VALUES
(9, 'Anglais', '5 ans', 'Hamza', 'Education', '12:00:00', '13:00:00'),
(10, 'Dessin', '3 ans', 'Salma', 'Loisirs', '13:00:00', '14:00:00'),
(11, 'Coloriage', '3 ans', 'Salma', 'Loisirs', '14:10:00', '15:00:00'),
(12, 'Math', '5 ans', 'responsable', 'Education', '18:00:00', '21:00:00'),
(13, 'sport', '3 ans', 'MyriamMezni', 'Education', '00:00:00', '00:00:00'),
(14, 'a', '3 ans', 'Naim', 'Education', '00:00:00', '00:00:00'),
(15, 'Theatre', '5 ans', 'MyriamMezni', 'Loisirs', '02:03:00', '05:04:00');

-- --------------------------------------------------------

--
-- Structure de la table `babysitter`
--

DROP TABLE IF EXISTS `babysitter`;
CREATE TABLE IF NOT EXISTS `babysitter` (
  `idBabysitter` int(11) NOT NULL,
  `id_enfant` int(11) NOT NULL,
  `heuredebut` int(11) NOT NULL,
  `heurefin` int(11) NOT NULL,
  `prixheure` int(11) NOT NULL,
  `joursemaine` varchar(255) NOT NULL,
  PRIMARY KEY (`id_enfant`,`idBabysitter`),
  KEY `IDX_9E0E09041280B94F` (`id_enfant`),
  KEY `IDX_9E0E09041CFA17EE` (`idBabysitter`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chaise`
--

DROP TABLE IF EXISTS `chaise`;
CREATE TABLE IF NOT EXISTS `chaise` (
  `id_chaise` int(11) NOT NULL AUTO_INCREMENT,
  `id_enfant` int(11) DEFAULT NULL,
  `etat_plat` varchar(200) NOT NULL,
  `num_table` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_chaise`),
  KEY `fk_enfant_chaise` (`id_enfant`),
  KEY `fk_table_chaise` (`num_table`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `chaise`
--

INSERT INTO `chaise` (`id_chaise`, `id_enfant`, `etat_plat`, `num_table`) VALUES
(10, 2, 'Vide', 6),
(11, 5, 'Vide', 6),
(12, 6, 'Vide', 6),
(13, 6, 'Vide', 8),
(14, 9, 'Vide', 9),
(16, 10, 'Vide', 7);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
CREATE TABLE IF NOT EXISTS `commentaire` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_sujet` int(11) DEFAULT NULL,
  `Texte` varchar(10000) NOT NULL,
  `DateC` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `fk_commentaire_user` (`id_user`),
  KEY `fk_commentaire_sujet` (`id_sujet`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`Id`, `id_user`, `id_sujet`, `Texte`, `DateC`) VALUES
(6, 9, 9, 'Commantaire 1', '2020-04-03 10:46:47'),
(7, 9, 9, 'commantaire notif', '2020-04-09 16:11:03'),
(9, 9, 6, 'àkokkok', '2020-04-09 16:17:06'),
(10, 8, 4, 'hhahaha', '2020-04-09 16:18:29'),
(11, 9, 7, 'Notification', '2020-04-10 09:27:02'),
(12, 9, 25, 'hhhh okk', '2020-04-16 20:52:26'),
(13, 8, 25, 'okok', '2020-04-17 09:59:46'),
(16, 9, 27, 'okok', '2020-04-17 10:10:51'),
(17, 22, 5, 'okok', '2020-05-07 14:43:40'),
(18, 27, 27, 'ok', '2020-05-22 08:56:39'),
(19, 9, 27, 'hey', '2020-05-22 08:58:44'),
(20, 9, 4, 'Commantaire', '2020-05-22 09:11:34'),
(21, 9, 6, 'commantaire', '2020-05-22 09:14:18'),
(22, 27, 7, 'C\'est bien vu', '2020-05-22 09:19:29'),
(23, 8, 31, 'commantaire', '2020-05-22 09:23:20'),
(24, 8, 7, 'je vois', '2020-05-22 09:24:05'),
(25, 29, 4, 'okok', '2020-06-04 13:48:13'),
(26, 30, 21, 'haha', '2020-06-04 13:52:42'),
(27, 30, 21, 'haha', '2020-06-04 13:53:46'),
(28, 30, 5, 'okok', '2020-06-04 13:54:21'),
(29, 9, 32, 'yoyoyo', '2020-06-05 15:21:51'),
(30, 36, 32, 'okok', '2020-06-05 15:25:06');

-- --------------------------------------------------------

--
-- Structure de la table `document`
--

DROP TABLE IF EXISTS `document`;
CREATE TABLE IF NOT EXISTS `document` (
  `id_document` int(11) NOT NULL AUTO_INCREMENT,
  `source` varchar(500) NOT NULL,
  `id_enfant` int(11) NOT NULL,
  PRIMARY KEY (`id_document`),
  KEY `fk_enfant_document` (`id_enfant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `enfant`
--

DROP TABLE IF EXISTS `enfant`;
CREATE TABLE IF NOT EXISTS `enfant` (
  `id_enfant` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `datenaissance` date NOT NULL,
  `remarque` varchar(500) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `id_parent` int(11) DEFAULT NULL,
  `cantine` tinyint(1) NOT NULL,
  `document` varchar(255) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`id_enfant`),
  KEY `fk_parent` (`id_parent`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enfant`
--

INSERT INTO `enfant` (`id_enfant`, `nom`, `prenom`, `datenaissance`, `remarque`, `image`, `id_parent`, `cantine`, `document`, `status`) VALUES
(1, 'NomEnfant', 'PrenomEnfant', '2020-03-03', 'rien', '75134.jpeg', 8, 1, '75134.jpeg', 'valide'),
(2, 'ok', 'ok', '2020-04-07', 'ok', '75134.jpeg', 8, 1, '37625.pdf', 'valide'),
(3, 'zz', 'zz', '1902-01-01', 'zz', '57568.jpeg', 12, 1, '37625.pdf', 'en attente'),
(4, 'Ben younes', 'salah', '2016-12-06', 'voila', '76126.jpeg', 8, 1, '21356.pdf', 'valide'),
(5, 'ok', 'ok', '1902-01-01', 'okok', '6849.png', 8, 1, '27872.pdf', 'en attente'),
(6, 'test', 'test', '2018-01-01', 'remarque', '49359.png', 8, 1, '20945.pdf', 'valide'),
(7, 'test', 'test', '2016-01-01', 'ok', '99816.jpg', 8, 0, '94122.pdf', 'en attente'),
(8, 'Nom Enfant', 'Prenom', '2016-01-01', 'Remarque', '75134.jpeg', 8, 1, '94122.pdf', 'en attente'),
(9, 'nom', 'prenom nouveau', '2018-05-31', 'hahahahahahaha', '4213.jpg', 25, 1, '4585.pdf', 'en attente'),
(10, 'ban jr', 'ban jr', '2018-06-08', 'okokokok', '8469.jpg', 36, 1, '9291.pdf', 'en attente'),
(11, 'Ban jr2', 'ban jr2', '2016-01-01', 'okok', '85614.jpg', 36, 0, '10227.jpg', 'en attente'),
(12, 'ban jr 3', 'ban jr 3', '2016-01-01', 'okokokok', '60065.jpg', 36, 0, '56601.jpg', 'en attente'),
(13, 'Ban jr 4', 'ban jr 4', '2020-06-17', 'okokokokokok', '5038.jpg', 36, 0, '4003.pdf', 'en attente'),
(14, 'EnfantP5', 'EnfantP5', '2016-01-01', 'okokokokok', '77890.jpg', 16, 0, '35498.jpg', 'en attente');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacite` varchar(11) NOT NULL,
  `nom` varchar(55) NOT NULL,
  `heure_d` varchar(15) NOT NULL,
  `description` varchar(55) NOT NULL,
  `date_evt` varchar(15) NOT NULL,
  `image` varchar(55) NOT NULL,
  `type` varchar(12) NOT NULL,
  `depart` varchar(25) DEFAULT NULL,
  `destination` varchar(25) DEFAULT NULL,
  `lieu` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `capacite`, `nom`, `heure_d`, `description`, `date_evt`, `image`, `type`, `depart`, `destination`, `lieu`) VALUES
(1, '25', 'exucursion1', '01:00', 'dssf', '2015-01-01', '60441.jpeg', 'Excursion', 'Rades', 'Rades', NULL),
(2, '25', 'fete', '13:35', 'feteeee', '2020-07-03', '5176.jpg', 'Fête', '', '', 'tunis'),
(3, '25', 'fete22', '13:36', 'fte222', '2020-06-18', '7283.jpg', 'Fête', NULL, NULL, 'Tunis'),
(4, '50', 'Excurtion', '15:38', 'Excurtion desc', '2020-06-25', '1928.jpg', 'Excursion', 'Ben Arous', 'Zaghouan', NULL),
(5, '25', 'test excu', '15:20', 'desc', '2020-06-24', '95.jpg', 'Excursion', 'tunis', 'hamamet', NULL),
(6, '25', 'test excu', '15:20', 'desc', '2020-06-24', '95.jpg', 'Excursion', 'tunis', 'hamamet', NULL),
(7, '25', 'fete', '14:27', 'descfete', '2020-06-25', '4232.jpg', 'Fête', NULL, NULL, 'tunis');

-- --------------------------------------------------------

--
-- Structure de la table `fos_user`
--

DROP TABLE IF EXISTS `fos_user`;
CREATE TABLE IF NOT EXISTS `fos_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `inscrevt`
--

DROP TABLE IF EXISTS `inscrevt`;
CREATE TABLE IF NOT EXISTS `inscrevt` (
  `id` int(11) NOT NULL,
  `id_enfant` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_enfant`),
  KEY `IDX_830C2B3ABF396750` (`id`),
  KEY `IDX_830C2B3A1280B94F` (`id_enfant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscrevt`
--

INSERT INTO `inscrevt` (`id`, `id_enfant`) VALUES
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `mailemp`
--

DROP TABLE IF EXISTS `mailemp`;
CREATE TABLE IF NOT EXISTS `mailemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mail` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `object` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(200) NOT NULL,
  `image` varchar(200) DEFAULT NULL,
  `description` varchar(1000) NOT NULL,
  `jour_de_la_semaine` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `menu`
--

INSERT INTO `menu` (`id`, `nom`, `image`, `description`, `jour_de_la_semaine`) VALUES
(3, 'Titre2', '70956.jpeg', 'Description2', '67'),
(4, 'Menu', '60745.jpeg', 'Description', '23'),
(5, 'Menu nouveau', '93759.jpeg', 'Bonjour', '256'),
(6, 'Menutest4', '60672.jpeg', 'desc', '245'),
(7, 'menu5', '31317.jpeg', 'desc', '346'),
(8, 'menu1212', '89441.jpeg', 'menu1212', '6'),
(9, 'MenuNouveau5', '90.jpg', 'Menu ajouté ', '23456'),
(10, 'Menu06', '56631.jpeg', 'menu ajoute 2', '256');

-- --------------------------------------------------------

--
-- Structure de la table `menu_commande`
--

DROP TABLE IF EXISTS `menu_commande`;
CREATE TABLE IF NOT EXISTS `menu_commande` (
  `id_menu` int(11) NOT NULL,
  `id_enfant` int(11) NOT NULL,
  `jour_de_la_semaine` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id_menu`,`id_enfant`),
  KEY `IDX_42BBE3EB1280B94F` (`id_enfant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `menu_commande`
--

INSERT INTO `menu_commande` (`id_menu`, `id_enfant`, `jour_de_la_semaine`) VALUES
(3, 1, '7'),
(3, 6, '7'),
(4, 2, '2'),
(4, 4, '3'),
(4, 5, '3'),
(4, 6, '2'),
(5, 1, '52'),
(5, 4, '6'),
(6, 1, '4'),
(6, 6, '5'),
(7, 1, '3'),
(7, 6, '3'),
(8, 1, '6'),
(9, 6, '46');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `route_parameters` longtext COLLATE utf8_unicode_ci COMMENT '(DC2Type:array)',
  `notification_date` datetime NOT NULL,
  `seen` tinyint(1) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_BF5476CA6B3CA4B` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen`, `id_user`) VALUES
(7, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '97283.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-09 14:50:35', 1, 8),
(8, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '97283.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-09 14:50:35', 1, 9),
(9, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '97283.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-09 14:50:36', 0, 12),
(10, 'Nouvelle reclamation!', 'Mohamed Naim Younes a ajouté une réclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-09 15:25:30', 1, 9),
(11, 'Nouvelle reclamation!', 'Mohamed Naim Younes a ajouté une réclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-09 15:25:37', 0, 11),
(12, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Reclamation Titre', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:9;}', '2020-04-09 16:11:03', 1, 11),
(13, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Reclamation Titre', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:9;}', '2020-04-09 16:11:04', 1, 8),
(14, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-09 16:15:53', 1, 11),
(15, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-09 16:15:54', 1, 8),
(16, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: oki', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:6;}', '2020-04-09 16:17:06', 1, 11),
(17, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Yo yoy o', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-04-09 16:18:29', 1, 9),
(18, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Yo yoy o', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-04-09 16:18:30', 1, 11),
(19, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-10 09:25:52', 1, 8),
(20, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-10 09:25:52', 0, 11),
(21, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-10 09:25:52', 0, 12),
(22, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-10 09:25:52', 0, 13),
(23, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: hay hay', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-04-10 09:27:02', 1, 11),
(24, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: hay hay', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-04-10 09:27:03', 1, 8),
(25, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test notif', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:25;}', '2020-04-16 20:52:26', 0, 11),
(27, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test notif', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:25;}', '2020-04-16 20:52:27', 1, 8),
(28, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test notif', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:25;}', '2020-04-17 09:59:46', 1, 9),
(29, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test notif', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:25;}', '2020-04-17 09:59:46', 0, 11),
(31, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test notif', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:25;}', '2020-04-17 09:59:46', 0, 15),
(32, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:00:26', 0, 9),
(33, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:00:27', 0, 11),
(35, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:00:27', 0, 15),
(36, 'Nouvelle reclamation!', 'Mohamed Naim Younes a ajouté une réclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:12', 1, 9),
(37, 'Nouvelle reclamation!', 'Mohamed Naim Younes a ajouté une réclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:12', 0, 11),
(39, 'Nouvelle reclamation!', 'Mohamed Naim Younes a ajouté une réclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:12', 0, 15),
(40, 'Reclamation modifiée!', 'Mohamed Naim Younes a modifié une reclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:38', 1, 9),
(41, 'Reclamation modifiée!', 'Mohamed Naim Younes a modifié une reclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:38', 0, 11),
(43, 'Reclamation modifiée!', 'Mohamed Naim Younes a modifié une reclamation', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:08:38', 0, 15),
(44, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:09:06', 1, 9),
(45, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:09:07', 0, 11),
(47, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle Activité disponisble', '45697.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:1;}', '2020-04-17 10:09:07', 0, 15),
(48, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-04-17 10:10:51', 0, 11),
(50, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-04-17 10:10:51', 0, 15),
(51, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-04-17 10:10:51', 1, 8),
(52, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:11:49', 0, 8),
(53, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:11:49', 0, 11),
(54, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:11:49', 0, 12),
(55, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:11:49', 0, 13),
(57, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-04-17 10:11:49', 0, 15),
(58, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '61651.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-05-07 14:43:40', 1, 9),
(59, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '61651.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-05-07 14:43:41', 0, 11),
(60, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '61651.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-05-07 14:43:41', 0, 15),
(61, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:56:01', 0, 9),
(62, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:56:02', 0, 11),
(63, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:56:02', 0, 15),
(64, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: test2', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:56:39', 1, 9),
(65, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: test2', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:56:39', 0, 11),
(66, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: test2', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:56:39', 0, 15),
(67, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: test2', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:56:39', 0, 8),
(68, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:58:44', 0, 11),
(69, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:58:44', 0, 15),
(70, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:58:45', 1, 27),
(71, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: test2', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:27;}', '2020-05-22 08:58:45', 0, 8),
(72, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 8),
(73, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 11),
(74, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 12),
(75, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 13),
(76, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 15),
(77, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:58:59', 0, 16),
(78, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 17),
(79, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 22),
(80, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 23),
(81, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 24),
(82, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 25),
(83, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 26),
(84, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 1, 27),
(85, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 08:59:00', 0, 28),
(86, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:58', 0, 8),
(87, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 11),
(88, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 12),
(89, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 13),
(90, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 15),
(91, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 16),
(92, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 17),
(93, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 22),
(94, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 23),
(95, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 24),
(96, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:09:59', 0, 25),
(97, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:10:00', 0, 26),
(98, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:10:00', 0, 27),
(99, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:10:00', 0, 28),
(100, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Yo yoy o', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-05-22 09:11:35', 0, 11),
(101, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Yo yoy o', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-05-22 09:11:35', 0, 15),
(102, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Yo yoy o', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-05-22 09:11:35', 0, 8),
(103, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:45', 0, 8),
(104, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:45', 0, 11),
(105, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:45', 0, 12),
(106, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:45', 0, 13),
(107, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:45', 0, 15),
(108, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 16),
(109, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 17),
(110, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 22),
(111, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 23),
(112, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 24),
(113, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 25),
(114, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 26),
(115, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 27),
(116, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:12:46', 0, 28),
(117, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: oki', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:6;}', '2020-05-22 09:14:18', 0, 11),
(118, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: oki', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:6;}', '2020-05-22 09:14:18', 0, 15),
(119, 'Reclamation modifiée!', 'Mohamed Naim Ben Younes a modifié une reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:17:24', 0, 9),
(120, 'Reclamation modifiée!', 'Mohamed Naim Ben Younes a modifié une reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:17:24', 0, 11),
(121, 'Reclamation modifiée!', 'Mohamed Naim Ben Younes a modifié une reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:17:24', 0, 15),
(122, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: Relcamation titre', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:19:29', 0, 9),
(123, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: Relcamation titre', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:19:29', 0, 11),
(124, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: Relcamation titre', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:19:29', 0, 15),
(125, 'Nouveau Commantaire!', 'Salahe Salah a ajoute un commantaire a la publication: Relcamation titre', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:19:29', 0, 8),
(126, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:20:47', 0, 9),
(127, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:20:47', 0, 11),
(128, 'Nouvelle reclamation!', 'Salahe Salah a ajouté une réclamation', '72677.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-05-22 09:20:47', 0, 15),
(129, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:31;}', '2020-05-22 09:23:20', 0, 9),
(130, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:31;}', '2020-05-22 09:23:21', 0, 11),
(131, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:31;}', '2020-05-22 09:23:21', 0, 15),
(132, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Reclamation', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:31;}', '2020-05-22 09:23:21', 1, 27),
(133, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Relcamation titre', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:24:05', 0, 9),
(134, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Relcamation titre', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:24:05', 0, 11),
(135, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Relcamation titre', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:24:05', 0, 15),
(136, 'Nouveau Commantaire!', 'Mohamed Naim Ben Younes a ajoute un commantaire a la publication: Relcamation titre', '69662.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:7;}', '2020-05-22 09:24:05', 1, 27),
(137, 'Nouveau Commantaire!', 'Naim Ben Younes a ajoute un commantaire a la publication: Yo yoy o', '9129.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-06-04 13:48:14', 0, 9),
(138, 'Nouveau Commantaire!', 'Naim Ben Younes a ajoute un commantaire a la publication: Yo yoy o', '9129.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-06-04 13:48:14', 0, 11),
(139, 'Nouveau Commantaire!', 'Naim Ben Younes a ajoute un commantaire a la publication: Yo yoy o', '9129.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-06-04 13:48:14', 0, 15),
(140, 'Nouveau Commantaire!', 'Naim Ben Younes a ajoute un commantaire a la publication: Yo yoy o', '9129.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:4;}', '2020-06-04 13:48:14', 0, 8),
(141, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:52:42', 1, 9),
(142, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:52:43', 0, 11),
(143, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:52:43', 0, 15),
(144, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:53:46', 1, 9),
(145, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:53:47', 0, 11),
(146, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: ok', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:21;}', '2020-06-04 13:53:47', 0, 15),
(147, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-06-04 13:54:21', 0, 9),
(148, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-06-04 13:54:21', 0, 11),
(149, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-06-04 13:54:21', 0, 15),
(150, 'Nouveau Commantaire!', 'Mohamed Ben Younes a ajoute un commantaire a la publication: Yo yoy oooooo', '276144136834742.png', NULL, 'a:1:{s:7:\"sujetId\";i:5;}', '2020-06-04 13:54:21', 0, 22),
(151, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:29', 0, 8),
(152, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:29', 0, 11),
(153, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:29', 0, 12),
(154, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:29', 0, 13),
(155, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 15),
(156, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 16),
(157, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 17),
(158, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 22),
(159, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 23),
(160, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 24),
(161, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 25),
(162, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 26),
(163, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 27),
(164, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:30', 0, 28),
(165, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 29),
(166, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 30),
(167, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 31),
(168, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 32),
(169, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 33),
(170, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 34),
(171, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 0, 35),
(172, 'Nouveau Sujet!', 'Mohamed Naim Younes a ajouter un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:17:31', 1, 36),
(173, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:31', 0, 8),
(174, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:31', 0, 11),
(175, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:31', 0, 12),
(176, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 13),
(177, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 15),
(178, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 16),
(179, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 17),
(180, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 22),
(181, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 23),
(182, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 24),
(183, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 25),
(184, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 26),
(185, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:32', 0, 27),
(186, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 28),
(187, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 29),
(188, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 30),
(189, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 31),
(190, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 32),
(191, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 33),
(192, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 34),
(193, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 35),
(194, 'Sujet Moifié!', 'Mohamed Naim Younes a modifié un sujet', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";N;}', '2020-06-05 15:21:33', 0, 36),
(195, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle activite 050600', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:21:51', 0, 11),
(196, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle activite 050600', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:21:51', 0, 15),
(197, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle activite 050600', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:21:51', 0, 33),
(198, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle activite 050600', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:21:52', 0, 34),
(199, 'Nouveau Commantaire!', 'Mohamed Naim Younes a ajoute un commantaire a la publication: Nouvelle activite 050600', '4435.jpeg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:21:52', 0, 35),
(200, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 9),
(201, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 11),
(202, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 15),
(203, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 33),
(204, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 34),
(205, 'Nouveau Commantaire!', 'Medd ban a ajoute un commantaire a la publication: Nouvelle activite 050600', '5808.jpg', NULL, 'a:1:{s:7:\"sujetId\";i:32;}', '2020-06-05 15:25:07', 0, 35);

-- --------------------------------------------------------

--
-- Structure de la table `programme`
--

DROP TABLE IF EXISTS `programme`;
CREATE TABLE IF NOT EXISTS `programme` (
  `IdProgramme` int(11) NOT NULL AUTO_INCREMENT,
  `IdActivite` int(11) DEFAULT NULL,
  `Cours` longtext NOT NULL,
  `Debut` varchar(30) NOT NULL,
  `Fin` varchar(30) NOT NULL,
  PRIMARY KEY (`IdProgramme`) USING BTREE,
  KEY `IDX_3DDCB9FFA48B7C9E` (`IdActivite`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ratingactivite`
--

DROP TABLE IF EXISTS `ratingactivite`;
CREATE TABLE IF NOT EXISTS `ratingactivite` (
  `IdRate` int(11) NOT NULL AUTO_INCREMENT,
  `Intitule` varchar(25) NOT NULL,
  `Rate` int(11) NOT NULL,
  PRIMARY KEY (`IdRate`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ratingactivite`
--

INSERT INTO `ratingactivite` (`IdRate`, `Intitule`, `Rate`) VALUES
(8, 'Coloriage', 1),
(9, 'Coloriage', 1),
(10, 'Math', 5),
(12, 'Coloriage', 1),
(13, 'Anglais', 1),
(16, 'Coloriage', 5),
(17, 'Coloriage', 3),
(20, 'Math', 2),
(21, 'Math', 4),
(22, 'sport', 3),
(23, 'Coloriage', 3),
(24, 'Coloriage', 3),
(33, 'intitule', 4),
(34, 'Math', 4),
(35, 'Dessin', 4),
(37, 'Coloriage', 1),
(38, 'Sport', 2),
(41, 'Dessin', 0),
(42, 'Coloriage', 2),
(43, 'jaw', 4),
(44, 'jaw', 3),
(74, 'Math', 4),
(75, 'Math', 3),
(76, 'Dessin', 3),
(77, 'Anglais', 0),
(78, 'Dessin', 3),
(79, 'Dessin', 3),
(80, 'Sport', 3),
(81, 'Sport', 3),
(82, 'Sport', 4),
(83, 'Math', 4),
(84, 'Math', 4),
(85, 'Math', 2),
(86, 'Coloriage', 5);

-- --------------------------------------------------------

--
-- Structure de la table `sujet`
--

DROP TABLE IF EXISTS `sujet`;
CREATE TABLE IF NOT EXISTS `sujet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `Titre` varchar(500) NOT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `Categorie` varchar(500) NOT NULL,
  `DateCreation` datetime NOT NULL,
  `DateModification` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sujet_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `sujet`
--

INSERT INTO `sujet` (`id`, `id_user`, `Titre`, `Description`, `Categorie`, `DateCreation`, `DateModification`) VALUES
(4, 9, 'Yo yoy o', 'hahahahahaha', 'Evenement', '2020-03-30 21:58:17', NULL),
(5, 9, 'Yo yoy oooooo', 'hahahahahaha :::::', 'Evenement', '2020-03-30 21:58:19', '2020-03-31 21:45:14'),
(6, 9, 'oki', 'okii', 'Evenement', '2020-03-30 21:59:22', NULL),
(7, 8, 'Relcamation titre', 'Probleme avec un employé x', 'Reclamation', '2020-03-30 23:09:06', '2020-05-22 09:17:24'),
(8, 11, 'okkkkk', 'pl', 'Activite', '2020-03-31 20:46:02', '2020-04-09 13:15:02'),
(9, 8, 'Reclamation Titre', 'Test555', 'Reclamation', '2020-04-03 10:43:01', '2020-04-03 10:43:31'),
(16, 9, 'test notif', 'test notif', 'Activite', '2020-04-08 18:54:31', NULL),
(18, 9, 'testNotif2', 'desc', 'Activite', '2020-04-08 19:28:38', NULL),
(19, 9, 'test', 'test', 'Evenement', '2020-04-09 13:01:46', NULL),
(20, 9, 'titre', 'desc', 'Activite', '2020-04-09 14:00:04', NULL),
(21, 9, 'ok', 'okokokokokokok', 'Activite', '2020-04-09 14:02:33', NULL),
(22, 9, 'ok', 'ok', 'Activite', '2020-04-09 14:05:24', NULL),
(23, 9, 'ok', 'ok', 'Activite', '2020-04-09 14:07:23', NULL),
(24, 11, 'Eve', 'okokok', 'Evenement', '2020-04-09 14:50:35', NULL),
(25, 8, 'test notif', 'tesf notifffff depuis front', 'Reclamation', '2020-04-09 15:25:30', NULL),
(26, 9, 'Test notifacation', 'description', 'Activite', '2020-04-10 09:25:52', NULL),
(27, 8, 'test2', 'test', 'Reclamation', '2020-04-17 10:08:12', '2020-04-17 10:08:38'),
(28, 27, 'ok', 'ok', 'Reclamation', '2020-05-22 08:56:01', NULL),
(29, 9, 'Salut', 'salut', 'Evenement', '2020-05-22 08:58:59', NULL),
(30, 9, 'Nouveau menu disponible', 'Un menu est ajouté pour le lundi', 'Annonce', '2020-05-22 09:12:45', NULL),
(31, 27, 'Reclamation', 'reclamation', 'Reclamation', '2020-05-22 09:20:46', NULL),
(32, 9, 'Nouvelle activite 050600', 'check it out', 'Activite', '2020-06-05 15:17:29', '2020-06-05 15:21:31');

-- --------------------------------------------------------

--
-- Structure de la table `table_cantine`
--

DROP TABLE IF EXISTS `table_cantine`;
CREATE TABLE IF NOT EXISTS `table_cantine` (
  `Id_table` int(11) NOT NULL AUTO_INCREMENT,
  `capacite` int(11) NOT NULL,
  PRIMARY KEY (`Id_table`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `table_cantine`
--

INSERT INTO `table_cantine` (`Id_table`, `capacite`) VALUES
(2, 0),
(6, 0),
(7, 8),
(8, 19),
(9, 19);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `IdUser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `Prenom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Nom` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mdp` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `NumTel` int(11) NOT NULL,
  `DateNaissance` date DEFAULT NULL,
  `Salaire` int(11) DEFAULT NULL,
  `Region` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Ville` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Rue` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CodePostal` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NbHeures` int(11) DEFAULT NULL,
  `TypeEmploye` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Tarif` int(11) DEFAULT NULL,
  `NbEnfant` int(11) DEFAULT NULL,
  `role` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`IdUser`),
  UNIQUE KEY `UNIQ_957A647992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_957A6479A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_957A6479C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`IdUser`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `Prenom`, `Nom`, `image`, `mdp`, `NumTel`, `DateNaissance`, `Salaire`, `Region`, `Ville`, `Rue`, `CodePostal`, `NbHeures`, `TypeEmploye`, `Tarif`, `NbEnfant`, `role`) VALUES
(8, 'Naim', 'naim', 'mohamednaimbenyounes@gmail.com', 'mohamednaimbenyounes@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWeq6YB5Kna4heqI9.B9doc2Hw.BCVZa.a', '2020-06-05 11:12:31', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Ben Younes', '69662.jpg', 'naimnaim', 55555555, '1998-12-07', NULL, 'Tunis', 'boumhal', '3rue babel', '1002', NULL, NULL, 0, 0, 'Parent'),
(9, 'NaimA', 'naima', 'mohamednaim.benyounes@esprit.tn', 'mohamednaim.benyounes@esprit.tn', 1, NULL, '$2y$13$1JRB0o4T.SLlm0xqeojJ/eM5dWUqva2mtX90WRnQNc4bLqSOqNWym', '2020-06-12 10:59:11', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'Mohamed Naim', 'Younes', '4435.jpeg', 'naim', 55023485, '1902-01-01', NULL, 'Ben Arous', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Admin'),
(11, 'NaimE', 'naime', 'mohamednaimbenyoune@gmail.com', 'mohamednaimbenyoune@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWeG0v9kyCl1X/qgFfUBCk2O/hkJM1oXOa', '2020-04-10 08:27:23', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'Mohamed Naim', 'Ben', '1520.jpg', 'naimEEE1', 55023485, '1902-01-01', 50, 'Ben Arous', 'boumhal', '3rue babel', '1002', 50, 'Cantine', NULL, NULL, 'Employe'),
(12, 'NaimP', 'naimp', 'mohamednaimbeyounes@gmail.com', 'mohamednaimbeyounes@gmail.com', 1, NULL, '$2y$13$tj7.oQviEXvQh9uZ.UvAt.X3qOARWu3VncFe7QzntxSj1YXreG34S', '2020-04-16 19:28:34', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Younes', '75134.jpeg', 'naim', 55023485, '1904-02-22', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(13, 'NaimP2', 'naimp2', 'mohamenaiyounes@gmail.com', 'mohamenaiyounes@gmail.com', 1, NULL, '$2y$13$EeRAxuskcIU1b4nv9xT5/OSPb/VFFi02kdq2SxQ3Xrqu5tZhi5/7G', '2020-04-09 14:31:12', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Younes', '13001.jpeg', 'naim', 55023485, '1905-02-03', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(15, 'NaimE2', 'naime2', 'mohamebenyounes@gmail.com', 'mohamebenyounes@gmail.com', 1, NULL, '$2y$13$tXHTJicfvOA3yb3ux4KBVuxCM1d/5PusgfqL6b6pZ.ifDWWPI58Ze', '2020-04-17 08:33:08', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'Mohamed Naim', 'Younes', '50592.jpeg', 'naim', 55023485, '1990-01-01', 500, 'Ben Arous', 'boumhal', '3rue babel', '1002', 20, 'BabySitter', NULL, NULL, 'Employe'),
(16, 'NaimP5', 'naimp5', 'mohamednaiyounes@gmail.com', 'mohamednaiyounes@gmail.com', 1, NULL, '$2y$13$tXHTJicfvOA3yb3ux4KBVuxCM1d/5PusgfqL6b6pZ.ifDWWPI58Ze', '2020-04-27 22:04:27', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Younes', '32733.png', 'naimp', 55023485, '1902-01-01', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(17, 'NaimP7', 'naimp7', 'mohamednaimbe@gmail.com', 'mohamednaimbe@gmail.com', 1, NULL, '$2y$13$Ce42zXygsj/91lVkj0w99eny6iBNKZTHvEJdMKjUYtBXIU/fzx/KK', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Younes', '21945.png', 'naim', 55023485, '1902-01-01', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(22, 'NaimP8', 'naimp8', 'mohamed@gmail.com', 'mohamed@gmail.com', 1, NULL, '$2y$13$mGzq/h.Yv0FHOcciRc3.2O3SXab1Wu8loqnRxRa7BMexQEpKOg.Oq', '2020-05-07 13:42:49', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed', 'Ben Younes', '61651.jpg', 'naim', 55555555, '2004-05-07', NULL, 'Ben Arous', 'Test', 'Test', '1002', NULL, NULL, NULL, 0, 'Parent'),
(23, 'NaimP10', 'naimp10', 'mohmaed@ml.com', 'mohmaed@ml.com', 1, NULL, '$2y$13$.BTrdA4DvJwnnd.NM.v87.qzsKRlub4LI6sFdZPaCsuhS5o9wyk1i', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed', 'naim', '39374.jpg', 'naim', 55555555, '1999-01-01', NULL, 'Ariana', 'ok', 'ok', '2000', NULL, NULL, NULL, 0, 'Parent'),
(24, 'NaimP15', 'naimp15', 'mohamednaim@yahoo.com', 'mohamednaim@yahoo.com', 1, NULL, '$2y$13$4asNLPGeTOGC14VcVEfncu6kGQjIs3rtoym3SY2Gsedyt/Lc5HZa6', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Ben Younes', '60528.jpg', 'naim', 55555555, '2020-05-17', NULL, 'Ben Arous', 'Boumhal', '03 Rue Babel', '1002', NULL, NULL, NULL, 0, 'Parent'),
(25, 'NaimP20', 'naimp20', 'mohmae@gmail.com', 'mohmae@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWe1XjFbIlZ6k33Irrlo0prKKQS2peSyxS', '2020-06-03 17:11:32', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Ben Younes', '8004.jpg', 'naimPPP20', 55023485, '2020-11-15', NULL, 'Ben Arous', 'ariana', '30 rue babal', '1003', NULL, NULL, 0, 0, 'Parent'),
(26, 'Firas', 'firas', 'fira@gmail.com', 'fira@gmail.com', 1, NULL, '$2y$13$wKfYgvML00afYc8oW3yxDOE1ll57WOXaQ/7Lo74JgSTRaPZIUqdTK', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Firas', 'Ben Younes', '22818.jpg', 'firas', 55555556, '1997-01-21', NULL, 'Tunis', 'Menzah', '03 rue menzah', '1002', NULL, NULL, NULL, 0, 'Parent'),
(27, 'Salah', 'salah', 'salah@gmail.com', 'salah@gmail.com', 1, NULL, '$2y$13$Moyli5Gfx02VwhvawGom7ufJ61DyLWm56NSWbEb9qHWuEEVEa7d9.', '2020-05-22 08:24:29', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Salahe', 'Salah', '72677.jpeg', 'salah', 55555555, '1919-01-01', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(28, 'Test', 'test', 'testest@gmail.com', 'testest@gmail.com', 1, NULL, '$2y$13$ibipxxtCWmTTYFyTcF4lful4IlTzDU8bKhE0db4oQrXShHo0s/5Qq', '2020-05-22 07:57:32', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed Naim', 'Younes', '4859.jpeg', 'test', 55023485, '1902-01-01', NULL, 'Ben Arous', 'boumhal', '3rue babel', '1002', NULL, NULL, NULL, NULL, 'Parent'),
(29, 'NaimP30', 'naimp30', 'mohamenaimmm@gmail.com', 'mohamenaimmm@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWew6P82e2zAZmJvA46d0DhcboGOBe88Li', '2020-06-04 12:31:05', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Naim', 'Ben Younes', '9129.jpg', 'naimPPP30', 55023485, '1990-07-17', NULL, 'Ben Arous', 'ok', 'ok', '1005', NULL, NULL, 0, 0, 'Parent'),
(30, 'NaimP31', 'naimp31', 'mohaameed@gmail.com', 'mohaameed@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWesEcNRQU18hFm.V9o0KEQ15Xs9I2h17G', '2020-06-04 12:57:07', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Mohamed', 'Ben Younes', '3539.jpg', 'naimPP31', 55555555, NULL, NULL, 'Ariana', 'ok', 'ok', '1004', NULL, NULL, 0, 0, 'Parent'),
(31, 'NaimP40', 'naimp40', 'naimbe@ya.com', 'naimbe@ya.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWeqHCdcCxVVHorWfpdINMsrOUIGXJYTzy', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'naim', 'benyounes', '8311.jpg', 'naimpppp', 55555555, NULL, NULL, 'Tunis', 'ok', 'ok', '1003', NULL, NULL, 0, 0, 'Parent'),
(32, 'NaimP50', 'naimp50', 'nab@gm.com', 'nab@gm.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWeqHCdcCxVVHorWfpdINMsrOUIGXJYTzy', NULL, NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'naim', 'ben', '4100.jpg', 'naimpppp', 55555555, NULL, NULL, 'Tunis', 'ok', 'ok', '1002', NULL, NULL, 0, 0, 'Parent'),
(33, 'naimE5', 'naime5', 'nae@gm.fr', 'nae@gm.fr', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWe8L/Wr7eIOIZA0rMJ2gHhAooT3wIpqN2', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'naim', 'naim', '4127.jpg', 'naimEEEE', 55555555, '2020-11-09', 500, 'Tunis', 'ok', 'ok', '1008', 80, 'BabySitter', NULL, NULL, 'Employe'),
(34, 'NaimE7', 'naime7', 'ne55@gm.com', 'ne55@gm.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWe8L/Wr7eIOIZA0rMJ2gHhAooT3wIpqN2', '2020-06-04 20:23:44', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'naim', 'naim', '1754.jpg', 'naimEEEE', 55555555, '1997-11-02', 700, 'Tunis', 'ok', 'ok', '1007', 80, 'Cantine', NULL, NULL, 'Employe'),
(35, 'NaimE8', 'naime8', 'nananana@gm.na', 'nananana@gm.na', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWe8L/Wr7eIOIZA0rMJ2gHhAooT3wIpqN2', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'naim', 'naim', '4997.jpg', 'naimEEEE', 55555555, '1995-11-06', 800, 'Tunis', 'ok', 'ok', '1009', 60, 'BabySitter', NULL, NULL, 'Employe'),
(36, 'Ban', 'ban', 'ban@gmail.com', 'ban@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWewhI5rptOyI6eyQOVpXQutcElX3Bg9t6', '2020-06-05 14:23:06', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_PARENT\";}', 'Medd', 'ban', '5808.jpg', 'banbanban', 55555555, '1992-01-05', NULL, 'Tunis', 'ok', 'ok', '1005', NULL, NULL, 0, 0, 'Parent'),
(37, 'BabySitter5', 'babysitter5', 'babysitter5@gmail.com', 'babysitter5@gmail.com', 1, NULL, '$2a$10$OYty/sJuxGPcgxtIKQHiWet0ALOZwu4W.ZVV65WZJISam5q32QONu', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'Babysitter', 'Babysitter', '7424.jpg', 'babybaby', 55646464, '2020-11-16', 600, 'Tunis', 'ok', 'ok', '1009', 30, 'BabySitter', NULL, NULL, 'Employe');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `babysitter`
--
ALTER TABLE `babysitter`
  ADD CONSTRAINT `babysitter_ibfk_1` FOREIGN KEY (`id_enfant`) REFERENCES `enfant` (`id_enfant`),
  ADD CONSTRAINT `babysitter_ibfk_2` FOREIGN KEY (`idBabysitter`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `chaise`
--
ALTER TABLE `chaise`
  ADD CONSTRAINT `fk_enfant_chaise` FOREIGN KEY (`id_enfant`) REFERENCES `enfant` (`id_enfant`),
  ADD CONSTRAINT `fk_table_chaise` FOREIGN KEY (`num_table`) REFERENCES `table_cantine` (`Id_table`);

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `fk_commentaire_sujet` FOREIGN KEY (`id_sujet`) REFERENCES `sujet` (`id`),
  ADD CONSTRAINT `fk_commentaire_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `enfant`
--
ALTER TABLE `enfant`
  ADD CONSTRAINT `fk_parent` FOREIGN KEY (`id_parent`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `inscrevt`
--
ALTER TABLE `inscrevt`
  ADD CONSTRAINT `hamza` FOREIGN KEY (`id`) REFERENCES `evenement` (`id`),
  ADD CONSTRAINT `hamza2` FOREIGN KEY (`id_enfant`) REFERENCES `enfant` (`id_enfant`);

--
-- Contraintes pour la table `menu_commande`
--
ALTER TABLE `menu_commande`
  ADD CONSTRAINT `fk_enfant_menuCommande` FOREIGN KEY (`id_enfant`) REFERENCES `enfant` (`id_enfant`),
  ADD CONSTRAINT `fk_menu_menuCommande` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK_BF5476CA6B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`IdUser`);

--
-- Contraintes pour la table `programme`
--
ALTER TABLE `programme`
  ADD CONSTRAINT `FK_3DDCB9FFA48B7C9E` FOREIGN KEY (`IdActivite`) REFERENCES `activite` (`IdActivite`) ON DELETE CASCADE;

--
-- Contraintes pour la table `sujet`
--
ALTER TABLE `sujet`
  ADD CONSTRAINT `fk_sujet_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`IdUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
