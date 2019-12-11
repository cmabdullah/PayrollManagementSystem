-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2019 at 05:45 PM
-- Server version: 5.7.21
-- PHP Version: 7.1.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `PMSV2`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `user_info_id` int(11) DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `login_ip_address` varchar(255) DEFAULT NULL,
  `working_hours` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`id`, `grade_id`, `shift_id`, `user_info_id`, `login_date`, `login_ip_address`, `working_hours`) VALUES
(19, 1, 1, 1, '2019-11-16', '0:0:0:0:0:0:0:1', 8),
(30, 1, 2, 2, '2019-11-17', '0:0:0:0:0:0:0:1', 9),
(31, 1, 1, 1, '2019-11-17', '0:0:0:0:0:0:0:1', 6),
(32, 1, 1, 1, '2019-11-27', '0:0:0:0:0:0:0:1', 5),
(33, 1, 1, 1, '2019-11-30', '0:0:0:0:0:0:0:1', 100),
(35, 2, 2, 4, '2019-11-30', '0:0:0:0:0:0:0:1', 120);

-- --------------------------------------------------------

--
-- Table structure for table `attendance_attendance_log`
--

CREATE TABLE `attendance_attendance_log` (
  `attendance_id` int(11) NOT NULL,
  `attendance_log_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `attendance_log`
--

CREATE TABLE `attendance_log` (
  `id` int(11) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `shift_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `attendance_id` int(11) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance_log`
--

INSERT INTO `attendance_log` (`id`, `login_time`, `logout_time`, `shift_id`, `user_id`, `attendance_id`, `ip_address`) VALUES
(14, '2019-11-16 15:19:28', '2019-11-16 21:12:40', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(15, '2019-11-16 20:19:28', '2019-11-16 20:40:41', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(16, '2019-11-16 20:19:28', '2019-11-16 20:44:07', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(17, '2019-11-16 20:19:28', '2019-11-16 20:48:14', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(18, '2019-11-16 15:19:28', '2019-11-16 20:50:17', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(19, '2019-11-16 15:19:28', '2019-11-16 21:12:40', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(20, '2019-11-16 15:19:28', '2019-11-16 21:15:59', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(21, '2019-11-16 15:19:28', '2019-11-16 21:18:05', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(22, '2019-11-16 15:19:28', '2019-11-16 21:22:54', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(23, '2019-11-16 15:19:28', '2019-11-16 21:24:14', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(24, '2019-11-16 15:19:28', '2019-11-16 21:24:58', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(25, '2019-11-16 15:19:28', '2019-11-16 21:30:20', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(26, '2019-11-16 15:19:28', '2019-11-16 21:33:59', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(27, '2019-11-16 15:19:28', '2019-11-16 21:55:04', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(28, '2019-11-16 15:19:28', '2019-11-16 22:25:13', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(29, '2019-11-16 23:34:41', '2019-11-16 23:34:41', 2, 2, 20, '0:0:0:0:0:0:0:1'),
(30, '2019-11-16 23:34:41', '2019-11-16 23:35:05', 2, 2, 20, '0:0:0:0:0:0:0:1'),
(31, '2019-11-16 15:19:28', '2019-11-16 23:37:25', 1, 1, 19, '0:0:0:0:0:0:0:1'),
(32, '2019-11-16 23:39:25', '2019-11-16 23:39:25', 2, 2, 21, '0:0:0:0:0:0:0:1'),
(33, '2019-11-17 10:37:04', '2019-11-17 01:37:04', 2, 2, 26, '0:0:0:0:0:0:0:1'),
(34, '2019-11-17 10:49:04', '2019-11-17 10:49:04', 2, 2, 27, '0:0:0:0:0:0:0:1'),
(35, '2019-11-17 10:00:00', '2019-11-17 10:00:00', 2, 2, 29, '0:0:0:0:0:0:0:1'),
(36, '2019-11-17 10:00:00', '2019-11-17 10:00:00', 2, 2, 30, '0:0:0:0:0:0:0:1'),
(37, '2019-11-17 12:29:26', '2019-11-17 12:29:26', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(38, '2019-11-17 12:29:26', '2019-11-17 12:30:18', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(39, '2019-11-17 12:29:26', '2019-11-17 19:06:39', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(40, '2019-11-17 12:29:26', '2019-11-17 19:09:54', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(41, '2019-11-17 12:29:26', '2019-11-17 19:13:12', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(42, '2019-11-17 12:29:26', '2019-11-17 19:18:28', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(43, '2019-11-17 12:29:26', '2019-11-17 19:23:36', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(44, '2019-11-17 12:29:26', '2019-11-17 19:24:33', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(45, '2019-11-17 12:29:26', '2019-11-17 19:25:55', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(46, '2019-11-17 12:29:26', '2019-11-17 19:26:25', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(47, '2019-11-17 12:29:26', '2019-11-17 19:27:11', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(48, '2019-11-17 12:29:26', '2019-11-17 19:28:15', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(49, '2019-11-17 12:29:26', '2019-11-17 19:28:27', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(50, '2019-11-17 12:29:26', '2019-11-17 19:29:22', 1, 1, 31, '0:0:0:0:0:0:0:1'),
(51, '2019-11-17 10:00:00', '2019-11-17 19:31:29', 2, 2, 30, '0:0:0:0:0:0:0:1'),
(52, '2019-11-27 17:00:00', '2019-11-27 17:00:00', 1, 1, 32, '0:0:0:0:0:0:0:1'),
(53, '2019-11-27 17:00:00', '2019-11-27 22:31:25', 1, 1, 32, '0:0:0:0:0:0:0:1'),
(54, '2019-11-27 17:00:00', '2019-11-27 22:35:04', 1, 1, 32, '0:0:0:0:0:0:0:1'),
(55, '2019-11-30 17:51:18', '2019-11-30 17:51:18', 1, 1, 33, '0:0:0:0:0:0:0:1'),
(57, '2019-11-30 19:00:00', '2019-11-30 19:00:00', 2, 4, 35, '0:0:0:0:0:0:0:1');

-- --------------------------------------------------------

--
-- Table structure for table `cuser`
--

CREATE TABLE `cuser` (
  `id` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cuser`
--

INSERT INTO `cuser` (`id`, `password`, `username`) VALUES
(1, 'cm', 'cm');

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `basic` int(11) NOT NULL,
  `grade_type` varchar(255) DEFAULT NULL,
  `house_rent` int(11) NOT NULL,
  `lunch` int(11) NOT NULL,
  `medical_allowence` int(11) NOT NULL,
  `study` int(11) NOT NULL,
  `transport` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `basic`, `grade_type`, `house_rent`, `lunch`, `medical_allowence`, `study`, `transport`) VALUES
(1, 20000, 'A', 12000, 2000, 1000, 5000, 3000),
(2, 15000, 'B', 10000, 1000, 1000, 3000, 3000);

-- --------------------------------------------------------

--
-- Table structure for table `leaves`
--

CREATE TABLE `leaves` (
  `id` int(11) NOT NULL,
  `denied_leave_request` int(11) NOT NULL,
  `entry_from` datetime DEFAULT NULL,
  `entry_to` datetime DEFAULT NULL,
  `leave_type` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `total_leave_days` int(11) NOT NULL,
  `user_info_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaves`
--

INSERT INTO `leaves` (`id`, `denied_leave_request`, `entry_from`, `entry_to`, `leave_type`, `reason`, `status`, `total_leave_days`, `user_info_id`) VALUES
(8, 0, '2019-11-10 00:00:00', '2019-11-13 00:00:00', 'regular', 'regular', 1, 3, 4),
(10, 0, '2019-11-12 00:00:00', '2019-12-17 00:00:00', 'medical', 'medic', 1, 36, 4),
(11, 0, '2019-10-27 00:00:00', '2019-10-29 00:00:00', 'regular', 'gdfdgd', 1, 3, 4),
(12, 0, '2019-11-24 00:00:00', '2019-12-01 00:00:00', 'regular', 'dsdsf', 1, 8, 4),
(13, 0, '2019-12-02 00:00:00', '2019-12-04 00:00:00', 'regular', 'fvvf', 2, 3, 4),
(14, 0, '2019-11-24 00:00:00', '2019-12-01 00:00:00', 'regular', 'lk;lvj', 0, 8, 4);

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `amount_validation` varchar(6) DEFAULT NULL,
  `approve_date` datetime DEFAULT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  `place_date` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `user_info_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id`, `amount`, `amount_validation`, `approve_date`, `loan_type`, `place_date`, `reason`, `status`, `user_info_id`) VALUES
(3, 45000, NULL, '2019-11-30 21:38:35', 'study', '2019-11-30 21:37:10', 'std', 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `loan_paid_details`
--

CREATE TABLE `loan_paid_details` (
  `id` int(11) NOT NULL,
  `installment_date` datetime DEFAULT NULL,
  `loan_id` int(11) NOT NULL,
  `installment` float NOT NULL,
  `paid_amount` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan_paid_details`
--

INSERT INTO `loan_paid_details` (`id`, `installment_date`, `loan_id`, `installment`, `paid_amount`) VALUES
(4, '2019-12-11 22:21:41', 3, 1425, 1425),
(5, '2019-12-11 22:22:00', 3, 1425, 2850),
(6, '2019-12-11 22:22:47', 3, 1425, 5700),
(7, '2019-12-11 22:28:19', 3, 1425, 11400),
(8, '2019-12-11 23:21:57', 3, 1425, 22800),
(9, '2019-12-11 23:27:16', 3, 825, 45000);

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `bonus` varchar(255) DEFAULT NULL,
  `monthly_working_hour` int(11) NOT NULL,
  `net_salary` float NOT NULL,
  `payment_date` datetime DEFAULT NULL,
  `regular` varchar(255) DEFAULT NULL,
  `total_leave_days` int(11) NOT NULL,
  `total_salary` float NOT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `leave_id` int(11) DEFAULT NULL,
  `loan_id` int(11) DEFAULT NULL,
  `user_info_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`id`, `bonus`, `monthly_working_hour`, `net_salary`, `payment_date`, `regular`, `total_leave_days`, `total_salary`, `grade_id`, `leave_id`, `loan_id`, `user_info_id`) VALUES
(1, NULL, 119, 35279.5, '2019-12-08 01:32:58', 'y', 3, 37136.4, 1, NULL, 3, 4),
(2, NULL, 119, 37136.4, '2019-12-11 21:29:48', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(3, NULL, 9, 0, '2019-12-11 21:29:48', 'y', 0, 0, 1, NULL, NULL, 2),
(4, NULL, 119, 37136.4, '2019-12-11 21:31:05', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(5, NULL, 9, 0, '2019-12-11 21:31:05', 'y', 0, 0, 1, NULL, NULL, 2),
(6, NULL, 119, 37136.4, '2019-12-11 21:35:34', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(7, NULL, 9, 0, '2019-12-11 21:35:34', 'y', 0, 0, 1, NULL, NULL, 2),
(8, NULL, 119, 37136.4, '2019-12-11 21:37:59', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(9, NULL, 119, 37136.4, '2019-12-11 21:57:30', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(10, NULL, 120, 27075, '2019-12-11 22:18:07', 'y', 11, 28500, 2, NULL, 3, 4),
(11, NULL, 120, 27075, '2019-12-11 22:18:11', 'y', 11, 28500, 2, NULL, 3, 4),
(12, NULL, 120, 27075, '2019-12-11 22:21:41', 'y', 11, 28500, 2, NULL, 3, 4),
(13, NULL, 120, 27075, '2019-12-11 22:22:00', 'y', 11, 28500, 2, NULL, 3, 4),
(14, NULL, 120, 27075, '2019-12-11 22:22:47', 'y', 11, 28500, 2, NULL, 3, 4),
(15, NULL, 119, 37136.4, '2019-12-11 22:28:19', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(16, NULL, 9, 0, '2019-12-11 22:28:19', 'y', 0, 0, 1, NULL, NULL, 2),
(17, NULL, 120, 27075, '2019-12-11 22:28:19', 'y', 11, 28500, 2, NULL, 3, 4),
(18, NULL, 119, 37136.4, '2019-12-11 23:21:57', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(19, NULL, 9, 0, '2019-12-11 23:21:57', 'y', 0, 0, 1, NULL, NULL, 2),
(20, NULL, 120, 27075, '2019-12-11 23:21:57', 'y', 11, 28500, 2, NULL, 3, 4),
(21, NULL, 119, 37136.4, '2019-12-11 23:27:16', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(22, NULL, 9, 0, '2019-12-11 23:27:16', 'y', 0, 0, 1, NULL, NULL, 2),
(23, NULL, 120, 27675, '2019-12-11 23:27:16', 'y', 11, 28500, 2, NULL, 3, 4),
(24, NULL, 119, 37136.4, '2019-12-11 23:28:52', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(25, NULL, 9, 0, '2019-12-11 23:28:52', 'y', 0, 0, 1, NULL, NULL, 2),
(26, NULL, 120, 28500, '2019-12-11 23:28:52', 'y', 11, 28500, 2, NULL, NULL, 4),
(27, NULL, 119, 37136.4, '2019-12-11 23:32:56', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(28, NULL, 9, 0, '2019-12-11 23:32:56', 'y', 0, 0, 1, NULL, NULL, 2),
(29, NULL, 120, 28500, '2019-12-11 23:32:56', 'y', 11, 28500, 2, NULL, NULL, 4),
(30, NULL, 119, 37136.4, '2019-12-11 23:41:41', 'y', 0, 37136.4, 1, NULL, NULL, 1),
(31, NULL, 9, 0, '2019-12-11 23:41:41', 'y', 0, 0, 1, NULL, NULL, 2),
(32, NULL, 120, 28500, '2019-12-11 23:41:41', 'y', 19, 28500, 2, NULL, NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

CREATE TABLE `shift` (
  `id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` varchar(50) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shift`
--

INSERT INTO `shift` (`id`, `duration`, `end_time`, `name`, `start_time`, `type`) VALUES
(1, 8, '17 PM', 'DAY Shift A', '08 AM', 'Engineer'),
(2, 8, '19 PM', 'DAY Shift B', '10 AM', 'Employee');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `confirm_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `joining_date` datetime DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `shift_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id`, `address`, `authority`, `confirm_password`, `email`, `enabled`, `fullname`, `joining_date`, `password`, `phone`, `username`, `grade_id`, `shift_id`) VALUES
(1, 'Bonosree', 'ROLE_ADMIN', '1234', 'c@g.com', b'1', 'CM', '2019-11-12 00:00:00', '1234', 12, 'cmaa', 1, 1),
(2, 'Mirpur', 'ROLE_ADMIN', '1234', 'anonimous@gmail.com', b'1', 'Anonymous', '2019-11-12 00:00:00', '$2a$10$Y61OiSSMtOsP3j8Jd2oaROds.krRHZ8FCbd6pVZnA29/Dy3Dc6SUW', 12, 'zxcv', 1, 2),
(3, 'House#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219\r\nHouse#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219', 'ROLE_ACCOUNTANT,ROLE_EMPLOYEE', '1234', 'a.kium.khan@gmail.com', b'0', 'C. M. Abdullah Khan', '2019-11-06 00:00:00', '1234', 1717243357, 'abcd', 2, 2),
(4, 'House#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219\r\nHouse#10, Road#5,Block#B, Banasree,Rampura,Dhaka-1219', 'ROLE_EMPLOYEE', '1234', 'a.kium.khan2@gmail.com', b'1', 'C. M. Abdullah Khan', '2019-01-06 00:00:00', '$2a$10$Y61OiSSMtOsP3j8Jd2oaROds.krRHZ8FCbd6pVZnA29/Dy3Dc6SUW', 1717243357, 'student', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_info_attendance`
--

CREATE TABLE `user_info_attendance` (
  `user_info_id` int(11) NOT NULL,
  `attendance_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9gg59ydjns2yu3lj9nd061rqp` (`grade_id`),
  ADD KEY `FK9gmenkx8eh7vy88vybmrbdyb4` (`shift_id`),
  ADD KEY `FK7itsj4axpjpqmd82bh7o9wcdj` (`user_info_id`);

--
-- Indexes for table `attendance_attendance_log`
--
ALTER TABLE `attendance_attendance_log`
  ADD UNIQUE KEY `UK_j3xdvfnw4fq6xndw7fuyyycuy` (`attendance_log_id`),
  ADD KEY `FKfiu958klubcbf86qk31ae75l1` (`attendance_id`);

--
-- Indexes for table `attendance_log`
--
ALTER TABLE `attendance_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7cpsfkivmjb92bl66eq9ki17y` (`attendance_id`);

--
-- Indexes for table `cuser`
--
ALTER TABLE `cuser`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `leaves`
--
ALTER TABLE `leaves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoreba2pb96ax5iyegcfjahung` (`user_info_id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4e4dbnc1mwoi6fxsuy92q3sln` (`user_info_id`);

--
-- Indexes for table `loan_paid_details`
--
ALTER TABLE `loan_paid_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqdpxg1ov80m5gvdifl2kk1ai0` (`loan_id`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnxf17rd9b6mejdd3j3sgu9002` (`grade_id`),
  ADD KEY `FKras79xurhuo2rk907i8g0aq7f` (`leave_id`),
  ADD KEY `FKgxt5c4ypfuiji4kqwtbxdu1bt` (`loan_id`),
  ADD KEY `FKa70xyoyx46mp3uonynwqn6ml1` (`user_info_id`);

--
-- Indexes for table `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh5v08gsnhwdpwqjsxf45gevh0` (`grade_id`),
  ADD KEY `FK9gtbg5xstyplkdgdycta4er0t` (`shift_id`);

--
-- Indexes for table `user_info_attendance`
--
ALTER TABLE `user_info_attendance`
  ADD UNIQUE KEY `UK_195pdlsv5mwmxq3foem7mn0kf` (`attendance_id`),
  ADD KEY `FKpdnpuks1goycnso7xrm8p9kbu` (`user_info_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `attendance_log`
--
ALTER TABLE `attendance_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `cuser`
--
ALTER TABLE `cuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `leaves`
--
ALTER TABLE `leaves`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loan_paid_details`
--
ALTER TABLE `loan_paid_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE `salary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `shift`
--
ALTER TABLE `shift`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
