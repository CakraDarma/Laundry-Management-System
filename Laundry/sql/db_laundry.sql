/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 10.4.16-MariaDB-log : Database - db_laundry
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_laundry` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_laundry`;

/*Table structure for table `tb_pelanggan` */

DROP TABLE IF EXISTS `tb_pelanggan`;

CREATE TABLE `tb_pelanggan` (
  `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `no_telepon` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_pelanggan` */

insert  into `tb_pelanggan`(`id_pelanggan`,`nama`,`alamat`,`no_telepon`) values 
(1,'cakra','123','345'),
(2,'cakra','123','345'),
(3,'tes','123','08901231'),
(4,'tes','123','123'),
(5,'dewa','123','123'),
(6,'kipas','234','234'),
(7,'coba','123','1'),
(8,'agung 123','22','2'),
(9,'gede','123','123');

/*Table structure for table `tb_pengguna` */

DROP TABLE IF EXISTS `tb_pengguna`;

CREATE TABLE `tb_pengguna` (
  `id_pengguna` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `no_telepon` varchar(30) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_pengguna`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_pengguna` */

insert  into `tb_pengguna`(`id_pengguna`,`username`,`password`,`nama`,`no_telepon`,`alamat`,`email`) values 
(1,'admin','123','Admin','089123455','jalan melali','admin@gmail.com'),
(2,'Cakra','123','tes','jl 123','0895375326441','cakra@gmailcom');

/*Table structure for table `tb_transaksi` */

DROP TABLE IF EXISTS `tb_transaksi`;

CREATE TABLE `tb_transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `id_pengguna` int(11) DEFAULT NULL,
  `id_pelanggan` int(11) DEFAULT NULL,
  `total_harga` int(11) DEFAULT NULL,
  `berat` int(11) DEFAULT NULL,
  `tanggal_masuk` date DEFAULT NULL,
  `status_bayar` varchar(30) DEFAULT NULL,
  `status_transaksi` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `fk_pengguna` (`id_pengguna`),
  KEY `tb_transaksi_ibfk_1` (`id_pelanggan`),
  CONSTRAINT `fk_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `tb_pengguna` (`id_pengguna`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `tb_pelanggan` (`id_pelanggan`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_transaksi` */

insert  into `tb_transaksi`(`id_transaksi`,`id_pengguna`,`id_pelanggan`,`total_harga`,`berat`,`tanggal_masuk`,`status_bayar`,`status_transaksi`) values 
(1,1,1,120000,12,'2021-05-06','Lunas','Selesai'),
(2,1,2,1230000,123,'2021-05-06','Belum Lunas','On Process'),
(5,1,4,1230000,123,'2021-05-10','Belum Lunas','On Process'),
(6,1,5,50000,5,'2021-05-10','Belum Lunas','On Process'),
(7,1,6,30000,3,'2021-05-10','Belum Lunas','On Process'),
(8,1,7,20000,2,'2021-05-10','Belum Lunas','On Process'),
(9,1,8,20000,2,'2021-05-10','Belum Lunas','On Process'),
(10,1,9,20000,2,'2021-05-10','Belum Lunas','On Process');

/*Table structure for table `useracc` */

DROP TABLE IF EXISTS `useracc`;

CREATE TABLE `useracc` (
  `username` varchar(25) NOT NULL,
  `password` varchar(30) NOT NULL,
  `sec_q` varchar(80) NOT NULL,
  `answer` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `useracc` */

insert  into `useracc`(`username`,`password`,`sec_q`,`answer`) values 
('123','123','123','123'),
('cakra','123','ayam123','123'),
('sdaf','123','ayam123','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
