-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 21, 2023 at 07:34 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlinequizsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `loginl`
--

CREATE TABLE `loginl` (
  `username` varchar(15) NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loginl`
--

INSERT INTO `loginl` (`username`, `Password`) VALUES
('lakmal', 'lk123'),
('roshana', 'ro123'),
('upeka', 'up123'),
('yasitha', 'ys123');

-- --------------------------------------------------------

--
-- Table structure for table `logins`
--

CREATE TABLE `logins` (
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`username`, `password`) VALUES
('mano', 'mn123'),
('tharukshi', 'tr123');

-- --------------------------------------------------------

--
-- Table structure for table `quizes`
--

CREATE TABLE `quizes` (
  `quiz_id` varchar(15) NOT NULL,
  `question` varchar(100) NOT NULL,
  `answer1` varchar(50) NOT NULL,
  `answer2` varchar(50) NOT NULL,
  `answer3` varchar(50) NOT NULL,
  `answer4` varchar(50) NOT NULL,
  `correctanswer` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quizes`
--

INSERT INTO `quizes` (`quiz_id`, `question`, `answer1`, `answer2`, `answer3`, `answer4`, `correctanswer`) VALUES
('NP_01', 'First Letter of Alphabet?', 'Z', 'A', 'B', 'K', 'A'),
('SIA_01', 'ghjghj', 'ggggg', 'gggg', 'g', 'gg', 'gggg'),
('NP_02', 'How you doing?', 'Joey Tribiani', 'CHandler Bing', 'Monica Geller', 'Rachel Green', 'Joey Tribiani'),
('NP_01', 'What does NP stand for?', 'No Problem', 'No Reply', 'Network Programming', 'Not Possible', 'Network Programming'),
('NP_01', 'What is HTTP?', 'HyperText Transfer Protocol', 'He Took That Pen', 'Hii  There... TP??', 'He He TTP', 'HyperText Transfer Protocol'),
('SOC_01', 'what is soc', 'aaaa', 'bbbb', 'cccc', 'dddd', 'cccc'),
('NP_01', 'What year is this?', '2021', '2022', '2023', '2024', '2023'),
('NP_01', 'Where does sun set?', 'East', 'North', 'South', 'West', 'West'),
('NP_02', 'Who is the best villain in flash?', 'Savitar', 'ZOOM', 'Riverse Flash', 'De Voe', 'ZOOM'),
('SIA_01', 'whtv aaaaaaa', 'aaa', 'aa', 'a', 'aaaa', 'aaaa'),
('NP_01', 'Why Are You Running?', 'Jogging', 'Just CHilling', 'Scared', 'Why do you care', 'Why do you care'),
('SOC_01', 'xml or jason', '11111', '222222', '3333333', '4444444', '11111');

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `no` int(11) NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `quiz_id` varchar(15) NOT NULL,
  `score` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score`
--

INSERT INTO `score` (`no`, `student_name`, `quiz_id`, `score`) VALUES
(1, 'mano', 'NP_01', '.17');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loginl`
--
ALTER TABLE `loginl`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `logins`
--
ALTER TABLE `logins`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `quizes`
--
ALTER TABLE `quizes`
  ADD PRIMARY KEY (`question`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`no`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
