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

/*Table structure for table `tb_det_transaksi` */

DROP TABLE IF EXISTS `tb_det_transaksi`;

CREATE TABLE `tb_det_transaksi` (
  `id_det_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `id_transaksi` int(11) NOT NULL,
  `baju` int(11) DEFAULT NULL,
  `seprai` varchar(30) DEFAULT NULL,
  `handuk` int(11) DEFAULT NULL,
  `jas` int(11) DEFAULT NULL,
  `celana_pendek` int(11) DEFAULT NULL,
  `tas` int(11) DEFAULT NULL,
  `jaket` int(11) DEFAULT NULL,
  `celana_panjang` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_det_transaksi`),
  KEY `id_barang` (`baju`),
  KEY `fk_transaksi2` (`id_transaksi`),
  CONSTRAINT `fk_transaksi2` FOREIGN KEY (`id_transaksi`) REFERENCES `tb_transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_det_transaksi` */

insert  into `tb_det_transaksi`(`id_det_transaksi`,`id_transaksi`,`baju`,`seprai`,`handuk`,`jas`,`celana_pendek`,`tas`,`jaket`,`celana_panjang`) values 
(8,49,3,'4',5,6,0,0,0,0),
(14,56,2,'4',3,5,0,6,7,0),
(15,57,2,'3',4,5,0,0,0,0),
(16,58,2,'3',4,5,4,3,5,6),
(17,60,1,'4',5,4,0,0,0,0);

/*Table structure for table `tb_diskon` */

DROP TABLE IF EXISTS `tb_diskon`;

CREATE TABLE `tb_diskon` (
  `id_diskon` int(11) NOT NULL AUTO_INCREMENT,
  `diskon` varchar(30) DEFAULT NULL,
  `jumlah` float DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_akhir` date DEFAULT NULL,
  PRIMARY KEY (`id_diskon`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_diskon` */

insert  into `tb_diskon`(`id_diskon`,`diskon`,`jumlah`,`tgl_mulai`,`tgl_akhir`) values 
(2,'Pelajar',5,'2021-05-05','2021-05-31'),
(3,'Galungan',10,'2021-05-23','2021-05-28'),
(4,'Natal',30,'2021-05-24','2021-05-24'),
(16,'Imlek',12,'2021-02-05','2021-01-05'),
(17,'coba',12,'2021-05-24','2021-05-25'),
(18,'Akhir bulan',15,'2021-05-20','2021-05-31'),
(19,'Lewat',12,'2021-05-18','2021-05-22'),
(20,'Mahasiswa',12,'2021-05-25','2021-05-28'),
(22,'lewat ini',12,'2021-05-23','2021-05-29'),
(23,'hari pancasila',5,'2021-05-31','2021-06-02'),
(24,'pelajar',12,'2021-06-02','2021-06-30'),
(25,'Guru',10,'2021-06-03','2021-06-30'),
(27,'Mahasiswa',12,'2021-06-02','2021-06-29');

/*Table structure for table `tb_jenis_cucian` */

DROP TABLE IF EXISTS `tb_jenis_cucian`;

CREATE TABLE `tb_jenis_cucian` (
  `id_cucian` int(10) NOT NULL AUTO_INCREMENT,
  `jenis_cucian` varchar(30) DEFAULT NULL,
  `harga` varchar(30) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `baju` int(10) DEFAULT NULL,
  `seprai` int(10) DEFAULT NULL,
  `handuk` int(10) DEFAULT NULL,
  `jas` int(10) DEFAULT NULL,
  `celana_pendek` int(10) DEFAULT NULL,
  `tas` int(10) DEFAULT NULL,
  `jaket` int(10) DEFAULT NULL,
  `celana_panjang` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_cucian`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_jenis_cucian` */

insert  into `tb_jenis_cucian`(`id_cucian`,`jenis_cucian`,`harga`,`tanggal`,`baju`,`seprai`,`handuk`,`jas`,`celana_pendek`,`tas`,`jaket`,`celana_panjang`) values 
(1,'Kering','1000','2021-05-23',10000,2999,3000,2000,9000,8000,7000,6000),
(2,'Cuci Uap','10000','2021-05-22',1000,3000,2000,1000,2000,3000,4000,5000),
(15,'Setrika','5000','2021-06-03',400,1000,100,2000,500,500,1000,1000),
(16,'Cuci Setrika','8000','2021-06-04',1000,2000,1000,1000,1000,2000,1000,2000);

/*Table structure for table `tb_pelanggan` */

DROP TABLE IF EXISTS `tb_pelanggan`;

CREATE TABLE `tb_pelanggan` (
  `id_pelanggan` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(30) DEFAULT NULL,
  `alamat` varchar(30) DEFAULT NULL,
  `no_telepon` varchar(30) DEFAULT NULL,
  `jenis_kelamin` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_pelanggan`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_pelanggan` */

insert  into `tb_pelanggan`(`id_pelanggan`,`nama`,`alamat`,`no_telepon`,`jenis_kelamin`) values 
(42,'kiloan','123','123','Laki-Laki'),
(43,'cakra','123','123','Laki-Laki'),
(44,'cakra darma','jalan penamparan','089123','Laki-Laki'),
(45,'ayulia','jl pemuda','0891234','Perempuan'),
(46,'dewa','jl gatsu','0891234','Perempuan'),
(47,'dwi','jl kebo iwa','0891234','Perempuan'),
(48,'arsana','jl kebo ireng','0891234','Laki-Laki'),
(49,'darma','jl gatsu','0812313','Perempuan'),
(50,'gede','jl west gatsu','08912341','Perempuan'),
(51,'darma saputra','jl west gatsu','0891234123','Laki-Laki'),
(52,'putu bagus','jl akasia ','089123412342','Laki-Laki'),
(53,'made','jl akasia ','089123412342','Laki-Laki'),
(54,'ayus','jl akasia ','089123412342','Laki-Laki'),
(55,'adit','jl sesetan','0891234123','Laki-Laki'),
(56,'agus','jl tanah lot','0891234','Laki-Laki');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_pengguna` */

insert  into `tb_pengguna`(`id_pengguna`,`username`,`password`,`nama`,`no_telepon`,`alamat`,`email`) values 
(1,'admin','123','Admin','089123455','jalan melali','admin@gmail.com'),
(2,'admin2','123','Cakra','08912351231','jalan akasia','cakra@gmailcom'),
(7,'admin3','123','ayulia','jl pemuda','0891234','ayulia@gmail.com');

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
  `tanggal_keluar` date DEFAULT NULL,
  `id_jenis_cucian` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `tb_transaksi_ibfk_1` (`id_pelanggan`),
  KEY `fk_pengguna` (`id_pengguna`),
  CONSTRAINT `fk_pengguna` FOREIGN KEY (`id_pengguna`) REFERENCES `tb_pengguna` (`id_pengguna`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_transaksi_ibfk_1` FOREIGN KEY (`id_pelanggan`) REFERENCES `tb_pelanggan` (`id_pelanggan`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_transaksi` */

insert  into `tb_transaksi`(`id_transaksi`,`id_pengguna`,`id_pelanggan`,`total_harga`,`berat`,`tanggal_masuk`,`status_bayar`,`status_transaksi`,`tanggal_keluar`,`id_jenis_cucian`) values 
(47,1,43,432960,12,'2021-06-03','Belum Lunas','On Process',NULL,5),
(48,1,44,42240,10,'2021-06-03','Belum Lunas','On Process',NULL,5),
(49,1,45,27280,0,'2021-06-03','Belum Lunas','On Process','2021-06-03',2),
(51,1,47,43120,0,'2021-06-03','Lunas','Selesai','2021-06-03',2),
(52,1,48,56320,0,'2021-06-03','Lunas','Selesai','2021-06-03',2),
(54,1,50,1760,0,'2021-06-03','Lunas','Selesai','2021-06-03',2),
(56,1,52,62480,0,'2021-06-03','Belum Lunas','On Process','2021-06-03',2),
(57,1,53,21120,0,'2021-06-03','Belum Lunas','On Process',NULL,2),
(58,1,54,80080,0,'2021-06-03','Belum Lunas','On Process',NULL,2),
(59,1,55,13500,4,'2021-06-03','Lunas','Selesai','2021-06-03',15),
(60,1,56,25520,0,'2021-06-03','Lunas','Selesai','2021-06-03',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
