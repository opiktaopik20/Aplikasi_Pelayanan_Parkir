-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 22 Agu 2020 pada 04.19
-- Versi Server: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_pelayanan_parkir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_absensi`
--

CREATE TABLE `data_absensi` (
  `id_absensi` varchar(10) NOT NULL,
  `tanggal_absensi` date NOT NULL,
  `lokasi` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_absensi`
--

INSERT INTO `data_absensi` (`id_absensi`, `tanggal_absensi`, `lokasi`) VALUES
('AB03', '2020-08-16', 'TMN'),
('AB04', '2020-08-16', 'TMN'),
('AB05', '2020-08-22', 'TMN');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_kartu`
--

CREATE TABLE `data_kartu` (
  `no_kartu` varchar(30) NOT NULL,
  `jenis_kartu` varchar(30) NOT NULL,
  `harga` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_kartu`
--

INSERT INTO `data_kartu` (`no_kartu`, `jenis_kartu`, `harga`) VALUES
('6396907420318644', 'Member Motor', '250000'),
('6396907420318645', 'Member Motor', '250000'),
('6396907420318646', 'Member Motor', '250000'),
('6396907420318647', 'Member Motor', '250000'),
('6396909420318653', 'Member Mobil', '650000'),
('6396909420318655', 'Member Mobil', '650000'),
('6396909420318656', 'Member Mobil', '650000'),
('6396909420318657', 'Member Mobil', '650000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_karyawan`
--

CREATE TABLE `data_karyawan` (
  `nik` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `tgl_masuk` date NOT NULL,
  `tgl_akhir` date NOT NULL,
  `jabatan` varchar(30) NOT NULL,
  `lokasi` varchar(100) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `alamat` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_karyawan`
--

INSERT INTO `data_karyawan` (`nik`, `nama`, `tgl_masuk`, `tgl_akhir`, `jabatan`, `lokasi`, `telepon`, `alamat`) VALUES
('2061108346', 'Thomas Pramudya', '2006-11-01', '2020-07-31', 'Car Park Manager', 'Thamrin Nine', '089667247446', 'Depok'),
('3071311101', 'Gusti Raga', '2008-04-03', '2020-07-31', 'Administrasi Pelayanan Parkir', 'Thamrin Nine', '089667247445', 'Ciputat'),
('7022001653', 'Opik Taopik', '2020-08-01', '2020-08-12', 'Staf Pelayanan Lapangan', 'Thamrin Nine', '08966672456', 'Kuningan'),
('7022002108', 'Nugroho Bagus Santoso', '2020-02-24', '2021-02-23', 'Staf Pelayanan Lapangan', 'Thamrin Nine', '089667247449', 'Cibitung'),
('7032003450', 'Deni Mulyanto', '2020-03-19', '2021-03-18', 'Staf Pelayanan Lapangan', 'Thamrin Nine', '089667247444', 'Bekasi'),
('7032003619', 'Maisah', '2020-03-28', '2021-03-27', 'Staf Pelayanan Pos', 'Thamrin Nine', '089667247448', 'Condet Jakarta Timur'),
('7091909263', 'Abu Isa DP', '2019-09-01', '2020-07-31', 'Staf Pelayanan Pos', 'Thamrin Nine', '089667247441', 'Jakarta'),
('7111913441', 'Zulkifli', '2019-11-01', '2020-07-31', 'Staf Pelayanan Pos', 'Thamrin Nine', '089667247442', 'Ciledug Tangerang'),
('8021700908', 'Ahmad Zulfi', '2019-01-15', '2020-01-14', 'Staf Pelayanan Lapangan', 'Thamrin Nine', '08572208306', 'Pemalang'),
('8091610598', 'Abdul Azis', '2019-09-01', '2020-08-31', 'Staf Pelayanan Lapangan', 'Thamrin Nine', '08558730817', 'Bogor');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_pelanggan`
--

CREATE TABLE `data_pelanggan` (
  `ktp` varchar(50) NOT NULL,
  `nama_pelanggan` varchar(50) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `no_stnk` varchar(30) NOT NULL,
  `nopol` varchar(20) NOT NULL,
  `merk` varchar(30) NOT NULL,
  `warna` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_pelanggan`
--

INSERT INTO `data_pelanggan` (`ktp`, `nama_pelanggan`, `telepon`, `alamat`, `jenis`, `no_stnk`, `nopol`, `merk`, `warna`) VALUES
('320808820109550002', 'Opik', '0896624774440', 'Kuningan', 'Motor', '1234', '66789KL', 'Honda', 'Hitam');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_penjualan_kartu`
--

CREATE TABLE `data_penjualan_kartu` (
  `id_penjualan` varchar(20) NOT NULL,
  `tanggal_penjualan` date NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `id_perusahaan` varchar(20) NOT NULL,
  `nama_perusahaan` varchar(100) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `nama_pelanggan` varchar(200) NOT NULL,
  `no_kendaraan` varchar(20) NOT NULL,
  `no_kartu` varchar(30) NOT NULL,
  `jenis` varchar(30) NOT NULL,
  `harga` varchar(30) NOT NULL,
  `durasi` varchar(10) NOT NULL,
  `total` varchar(30) NOT NULL,
  `periode` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_penjualan_kartu`
--

INSERT INTO `data_penjualan_kartu` (`id_penjualan`, `tanggal_penjualan`, `nama_karyawan`, `id_perusahaan`, `nama_perusahaan`, `no_ktp`, `nama_pelanggan`, `no_kendaraan`, `no_kartu`, `jenis`, `harga`, `durasi`, `total`, `periode`) VALUES
('TR01', '2020-08-16', 'Thomas Pramudya', 'PR04', 'UOB KAYHIAN SECURITIES', '3208082010950002', 'Opik Taopik', 'B1122KK', '6396907420318641', 'Member Motor', '250000', '1', '250000', 'Agustus 2020'),
('TR02', '2020-08-16', 'Gusti Raga', 'PR03', 'BAUING INDONESIA', '3208082010950003', 'Erwin Irawan', 'B1122KP', '6396909420318651', 'Member Mobil', '650000', '1', '650000', 'Agustus'),
('TR03', '2020-08-17', 'Gusti Raga', 'PR04', 'UOB KAYHIAN SECURITIES', '3208082010950004', 'Fajar Septiandri', 'B8495MS', '6396907420318642', 'Member Motor', '250000', '1', '250000', 'Agustus'),
('TR04', '2020-08-17', 'Thomas Pramudya', 'PR01', 'MIDTOU ARYACOM FUTURES', '3208082010950007', 'Nyoman Ucok', 'B8495HG', '6396909420318652', 'Member Mobil', '650000', '1', '650000', 'September'),
('TR05', '2020-08-18', 'Thomas Pramudya', 'PR01', 'MIDTOU ARYACOM FUTURES', '320808820109550002', 'Opik', '66789KL', '6396907420318643', 'Member Motor', '250000', '1', '250000', 'Agustus 2020'),
('TR06', '2020-08-22', 'Thomas Pramudya', 'PR04', 'UOB KAYHIAN SECURITIES', '320808820109550002', 'Opik', '66789KL', '6396909420318654', 'Member Mobil', '650000', '1', '650000', 'Agustus 2020');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_perusahaan`
--

CREATE TABLE `data_perusahaan` (
  `id_perusahaan` varchar(20) NOT NULL,
  `nama_perusahaan` varchar(100) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `alamat` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_perusahaan`
--

INSERT INTO `data_perusahaan` (`id_perusahaan`, `nama_perusahaan`, `telepon`, `alamat`) VALUES
('PR01', 'MIDTOU ARYACOM FUTURES', '021-12342', 'Lantai L UOB Plaza'),
('PR02', 'DELTA ENERGY', '021-12348', 'Lantai M UOB Plaza'),
('PR03', 'BAUING INDONESIA', '021-63738', 'Lantai M UOB Plaza'),
('PR04', 'UOB KAYHIAN SECURITIES', '(021) 886651', 'Lantai 12 UOb Plaza');

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_tiket`
--

CREATE TABLE `data_tiket` (
  `id_tiket` varchar(10) NOT NULL,
  `tgl_tiket` date NOT NULL,
  `nik` varchar(20) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `shift` varchar(20) NOT NULL,
  `pos` varchar(10) NOT NULL,
  `jenis` varchar(20) NOT NULL,
  `tes_awal` varchar(20) NOT NULL,
  `tes_akhir` varchar(20) NOT NULL,
  `terjual` varchar(20) NOT NULL,
  `total` varchar(20) NOT NULL,
  `terpakai` varchar(20) NOT NULL,
  `batal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_tiket`
--

INSERT INTO `data_tiket` (`id_tiket`, `tgl_tiket`, `nik`, `nama`, `shift`, `pos`, `jenis`, `tes_awal`, `tes_akhir`, `terjual`, `total`, `terpakai`, `batal`) VALUES
('TK01', '2020-08-16', '7022001653', 'Opik Taopik', 'Pagi', 'PM1', 'Casual Mobil', '12345', '12346', '1', '2', '4', '1'),
('TK02', '2020-08-16', '7022002108', 'Nugroho Bagus Santoso', 'Pagi', 'PM2', 'Pass Mobil', '54321', '54322', '2', '2', '5', '1'),
('TK03', '2020-08-16', '7022002202', 'Faisal Desuja Putra', 'Siang', 'PM1', 'Casual Mobil', '57678', '57679', '3', '2', '7', '2'),
('TK04', '2020-08-16', '7032003450', 'Deni Mulyanto', 'Pagi', 'PM2', 'Casual Mobil', '34567', '34568', '4', '2', '2', '8'),
('TK05', '2020-08-16', '7091909263', 'Abu Isa DP', 'Siang', 'PM2', 'Pass Mobil', '12334', '12335', '1', '2', '4', '1');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_data_absensi`
--

CREATE TABLE `detail_data_absensi` (
  `detail_id_absensi` varchar(10) NOT NULL,
  `detail_tanggal_absensi` date NOT NULL,
  `detail_lokasi` varchar(100) NOT NULL,
  `id_absensi_baru` varchar(10) NOT NULL,
  `detail_nik` varchar(20) NOT NULL,
  `detail_nama` varchar(200) NOT NULL,
  `detail_shift` varchar(20) NOT NULL,
  `detail_jam_masuk` varchar(10) NOT NULL,
  `detail_jam_pulang` varchar(10) NOT NULL,
  `detail_keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_data_absensi`
--

INSERT INTO `detail_data_absensi` (`detail_id_absensi`, `detail_tanggal_absensi`, `detail_lokasi`, `id_absensi_baru`, `detail_nik`, `detail_nama`, `detail_shift`, `detail_jam_masuk`, `detail_jam_pulang`, `detail_keterangan`) VALUES
('AB04', '2020-08-16', 'TMN', 'KH01', '2061108346', 'Thomas Pramudya', 'Pagi', '08.30', '20.30', 'Hadir'),
('AB04', '2020-08-16', 'TMN', 'KH02', '3071311101', 'Gusti Raga', 'Pagi', '07.30', '19.30', 'Hadir'),
('AB04', '2020-08-16', 'TMN', 'KH03', '7022001653', 'Opik Taopik', 'Pagi', '06.30', '18.30', 'Hadir'),
('AB04', '2020-08-16', 'TMN', 'KH04', '7022002108', 'Nugroho Bagus Santoso', 'Pagi', '06.45', '18.45', 'Hadir'),
('AB03', '2020-08-15', 'TMN', 'KH05', '7022002202', 'Faisal Desuja Putra', 'Pagi', '06.10', '18.30', 'Hadir'),
('AB03', '2020-08-15', 'TMN', 'KH06', '7032003450', 'Deni Mulyanto', 'Pagi', '09.30', '21.30', 'Hadir'),
('AB05', '2020-08-22', 'TMN', 'KH07', '8091610598', 'Abdul Azis', 'Middle', '06.00', '18.30', 'Hadir');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_absensi`
--
ALTER TABLE `data_absensi`
  ADD PRIMARY KEY (`id_absensi`);

--
-- Indexes for table `data_kartu`
--
ALTER TABLE `data_kartu`
  ADD PRIMARY KEY (`no_kartu`);

--
-- Indexes for table `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `data_pelanggan`
--
ALTER TABLE `data_pelanggan`
  ADD PRIMARY KEY (`ktp`);

--
-- Indexes for table `data_penjualan_kartu`
--
ALTER TABLE `data_penjualan_kartu`
  ADD PRIMARY KEY (`id_penjualan`);

--
-- Indexes for table `data_perusahaan`
--
ALTER TABLE `data_perusahaan`
  ADD PRIMARY KEY (`id_perusahaan`);

--
-- Indexes for table `data_tiket`
--
ALTER TABLE `data_tiket`
  ADD PRIMARY KEY (`id_tiket`);

--
-- Indexes for table `detail_data_absensi`
--
ALTER TABLE `detail_data_absensi`
  ADD PRIMARY KEY (`id_absensi_baru`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
