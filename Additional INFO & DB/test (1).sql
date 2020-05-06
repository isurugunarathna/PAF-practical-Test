-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 05:21 PM
-- Server version: 10.1.40-MariaDB
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `alien`
--

CREATE TABLE `alien` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alien`
--

INSERT INTO `alien` (`id`, `name`, `points`) VALUES
(102, 'kamal', 55),
(103, 'kamal', 55),
(105, 'kasun', 82),
(107, 'kasun', 82),
(110, 'asiri', 46);

-- --------------------------------------------------------

--
-- Table structure for table `appoinment`
--

CREATE TABLE `appoinment` (
  `ap_id` int(11) NOT NULL,
  `ap_date` varchar(20) DEFAULT NULL,
  `ap_time` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appoinment`
--

INSERT INTO `appoinment` (`ap_id`, `ap_date`, `ap_time`, `id`) VALUES
(1, '2002/12/12', 2, 102),
(1001, '2002/10/2', 10, 110);

-- --------------------------------------------------------

--
-- Table structure for table `doc`
--

CREATE TABLE `doc` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `section` int(11) NOT NULL,
  `contact_no` int(11) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doc`
--

INSERT INTO `doc` (`id`, `name`, `section`, `contact_no`, `email`) VALUES
(102, 'saman', 2, 78645, 'saman@gmail.com'),
(110, 'charitha', 2, 786154, 'kamal@gmail.com'),
(111, 'isuru', 4, 783154789, 'isur@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alien`
--
ALTER TABLE `alien`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD PRIMARY KEY (`ap_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `doc`
--
ALTER TABLE `doc`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD CONSTRAINT `appoinment_ibfk_1` FOREIGN KEY (`id`) REFERENCES `doc` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
