-- phpMyAdmin SQL Dump
-- version 4.0.6
-- http://www.phpmyadmin.net
--
-- Host: MYSQL5002
-- Generation Time: Nov 16, 2013 at 07:22 AM
-- Server version: 5.5.25
-- PHP Version: 5.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_9ab3ca_pranu`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `username` text NOT NULL,
  `subject` text NOT NULL,
  `stu` text NOT NULL,
  `status` text NOT NULL,
  `day` text NOT NULL,
  `month` text NOT NULL,
  `year` text NOT NULL,
  `phone` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`username`, `subject`, `stu`, `status`, `day`, `month`, `year`, `phone`) VALUES
('dinesh', 'Digital Image Processing', 'Naresh', 'Present', '16', '11', '2013', '8870538584'),
('dinesh', 'Digital Image Processing', 'leepthi', 'Absent', '16', '11', '2013', '8870538584'),
('dinesh', 'Digital Image Processing', 'Priya M', 'Present', '16', '11', '2013', '8870538584'),
('dinesh', 'Digital Image Processing', 'Neha Sharma', 'Present', '16', '11', '2013', '8870538584'),
('dinesh', 'Digital Image Processing', 'Ravali', 'Absent', '16', '11', '2013', '8870538584'),
('pranu', 'Web Services', 'Naresh', 'Present', '18', '11', '2013', '9092679292'),
('pranu', 'Web Services', 'Ravali', 'Present', '18', '11', '2013', '9092679292'),
('pranu', 'Web Services', 'Naresh', 'Absent', '17', '11', '2013', '9092679292'),
('pranu', 'Web Services', 'Ravali', 'Absent', '17', '11', '2013', '9092679292');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`username`, `password`) VALUES
('pranu', 'pranu'),
('divya', 'divya'),
('dinesh', 'dinesh');

-- --------------------------------------------------------

--
-- Table structure for table `percentage`
--

CREATE TABLE IF NOT EXISTS `percentage` (
  `username` text NOT NULL,
  `subject` text NOT NULL,
  `day` text NOT NULL,
  `month` text NOT NULL,
  `year` text NOT NULL,
  `student` text NOT NULL,
  `percen` text NOT NULL,
  `phone` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `percentage`
--

INSERT INTO `percentage` (`username`, `subject`, `day`, `month`, `year`, `student`, `percen`, `phone`) VALUES
('pranu', 'Data Structures', '16', '11', '2013', 'Ram Namburi', '80', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Tarun Mandava', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Srikanth M', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Sampath Kolli', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Divya Reddy', '80', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Sai krishna', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Saketh Poduri', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Krishna', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Venkatesh', '60', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Vamshikiran', '80', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Charan', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Satvik', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Tejaswini', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Varma', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Naresh', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Ravali', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Vivek B', '80', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Nanditha Reddy', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Prathyusha', '100', '9092679292'),
('pranu', 'Data Structures', '16', '11', '2013', 'Siddharath', '100', '9092679292'),
('pranu', 'Digital Image Processing', '17', '11', '2013', 'kamalakar reddy', '100', '9092679292'),
('pranu', 'Digital Image Processing', '17', '11', '2013', 'Priya Agarwal', '66.67', '9092679292'),
('pranu', 'Web Services', '18', '11', '2013', 'Ravali', '50', '8870538584'),
('pranu', 'Web Services', '18', '11', '2013', 'Naresh', '50', '8870538584'),
('dinesh', 'Digital Image Processing', '16', '11', '2013', 'Priya M', '100', '8870538584'),
('dinesh', 'Digital Image Processing', '16', '11', '2013', 'Naresh', '100', '8870538584'),
('dinesh', 'Digital Image Processing', '16', '11', '2013', 'Ravali', '0', '8870538584'),
('dinesh', 'Digital Image Processing', '16', '11', '2013', 'Neha Sharma', '100', '8870538584'),
('dinesh', 'Digital Image Processing', '16', '11', '2013', 'leepthi', '0', '8870538584');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `name` text NOT NULL,
  `rid` text NOT NULL,
  `subject` text NOT NULL,
  `subc` text NOT NULL,
  `faculty` text NOT NULL,
  `attendance` text,
  `phone` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`name`, `rid`, `subject`, `subc`, `faculty`, `attendance`, `phone`) VALUES
('Ravali', 'ms001', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Ravali', 'ms001', 'Digital Image Processing', 'swe104', 'dinesh', NULL, '8870538584'),
('Ravali', 'ms001', 'Linear Algebra', 'swe108', 'divya', NULL, '8870538584'),
('Ravali', 'ms001', 'Web Services', 'swe103', 'pranu', NULL, '8870538584'),
('Naresh', 'ms002', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Naresh', 'ms002', 'Web Services', 'swe103', 'pranu', NULL, '8870538584'),
('Naresh', 'ms002', 'Linear Algebra', 'swe108', 'divya', NULL, '8870538584'),
('varma', 'ms003', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('tejaswini', 'ms004', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Satvik', 'ms005', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Charan', 'ms006', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Vamshikiran', 'ms008', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Venkatesh', 'ms009', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Krishna', 'ms011', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Saketh Poduri', 'ms012', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Sai krishna', 'ms013', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Divya Reddy', 'ms014', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Sampath Kolli', 'ms015', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Srikanth M', 'ms016', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Tarun Mandava', 'ms017', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Ram Namburi', 'ms018', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Vivek B', 'ms019', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Nanditha Reddy', 'ms020', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Prathyusha', 'ms021', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Siddharath', 'ms022', 'Data Structures', 'swe101', 'pranu', NULL, '9092679292'),
('Neha Sharma', 'ms023', 'Digital Image Processing', 'swe104', 'dinesh', NULL, '8870538584'),
('Priya M', 'ms024', 'Digital Image Processing', 'swe104', 'dinesh', NULL, '8870538584'),
('leepthi', 'ms025', 'Digital Image Processing', 'swe104', 'dinesh', NULL, '8870538584'),
('Kamalakar Reddy', 'ms026', 'Digital Image Processing', 'swe104', 'pranu', NULL, '9092679292'),
('Priya Agarwal', 'ms026', 'Digital Image Processing', 'swe104', 'pranu', NULL, '9092679292'),
('Naresh', 'ms002', 'Digital Image Processing', 'swe104', 'dinesh', NULL, '8870538584');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `username` text NOT NULL,
  `subc` text NOT NULL,
  `subject` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`username`, `subc`, `subject`) VALUES
('dinesh', 'swe105', 'Programming in JAVA'),
('pranu', 'swe102', 'Operating Systems'),
('dinesh', 'swe104', 'Digital Image Processing'),
('pranu', 'swe103', 'Web Services'),
('pranu', 'swe101', 'Data Structures'),
('dinesh', 'swe106', 'Principles of UID'),
('divya', 'swe108', 'Linear Algebra'),
('divya', 'swe107', 'English for Engineers'),
('pranu', 'swe109', 'OOAD'),
('pranu', 'swe110', 'OOPS'),
('pranu', 'swe104', 'Digital Image Processing');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
