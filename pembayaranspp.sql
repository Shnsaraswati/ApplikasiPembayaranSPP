-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2018 at 02:12 PM
-- Server version: 10.1.25-MariaDB
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pembayaranspp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `IdAdmin` varchar(30) NOT NULL,
  `Nama` varchar(30) NOT NULL,
  `Alamat` text NOT NULL,
  `No_Telp` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`IdAdmin`, `Nama`, `Alamat`, `No_Telp`, `password`) VALUES
('Admin001', 'Namia Putri', 'Jalan Bukit Dieng Raya No 24', '082178815258', 'admin1'),
('Admin002', 'Dinda Ayu', 'Jalan Ijen Nirwana Residence No 07', '0341147665', 'admin2');

-- --------------------------------------------------------

--
-- Table structure for table `data_murid`
--

CREATE TABLE `data_murid` (
  `NIS` varchar(30) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Kelas` varchar(10) NOT NULL,
  `Kota_Kelahiran` varchar(20) NOT NULL,
  `TTL` varchar(25) NOT NULL,
  `Alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_murid`
--

INSERT INTO `data_murid` (`NIS`, `Nama`, `Kelas`, `Kota_Kelahiran`, `TTL`, `Alamat`) VALUES
('1910001', 'Cantika Sari', 'VII', 'Surabaya', '10 Januari 2005', 'Jalan Simpang BAlapan no 12'),
('1910002', 'Irving Wahyudi', 'VIII', 'Bekasi', '11 Maret 2004', 'Jalan Tanjung Duren no 15');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `NIS` varchar(30) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `Semester` varchar(20) NOT NULL,
  `tanggal` varchar(50) NOT NULL,
  `pembayaran_untuk_berapa_bulan` varchar(10) NOT NULL,
  `totalbayar` int(50) NOT NULL,
  `uangbayar` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `NIS`, `Nama`, `kelas`, `Semester`, `tanggal`, `pembayaran_untuk_berapa_bulan`, `totalbayar`, `uangbayar`) VALUES
(1, '1910001', 'Cantika Sari', 'VII', 'Genap', '15/12/2018', '2', 300000, 300000);

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_pembayaran`
--

CREATE TABLE `riwayat_pembayaran` (
  `IdAdmin` varchar(30) NOT NULL,
  `NIS` varchar(30) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `Semester` varchar(20) NOT NULL,
  `tanggal` date NOT NULL,
  `uangspp` int(50) NOT NULL,
  `pembayaran_untuk_berepa_bulan` varchar(10) NOT NULL,
  `totalbayar` int(50) NOT NULL,
  `uangbayar` int(50) NOT NULL,
  `uangkembali` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`IdAdmin`);

--
-- Indexes for table `data_murid`
--
ALTER TABLE `data_murid`
  ADD PRIMARY KEY (`NIS`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `NIS` (`NIS`);

--
-- Indexes for table `riwayat_pembayaran`
--
ALTER TABLE `riwayat_pembayaran`
  ADD KEY `NIS` (`NIS`),
  ADD KEY `IdAdmin` (`IdAdmin`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `data_murid` (`NIS`);

--
-- Constraints for table `riwayat_pembayaran`
--
ALTER TABLE `riwayat_pembayaran`
  ADD CONSTRAINT `riwayat_pembayaran_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `data_murid` (`NIS`),
  ADD CONSTRAINT `riwayat_pembayaran_ibfk_2` FOREIGN KEY (`IdAdmin`) REFERENCES `admin` (`IdAdmin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
