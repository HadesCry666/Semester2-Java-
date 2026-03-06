-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 26 Bulan Mei 2024 pada 18.02
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `numnum`
--

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `daily_data_view`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `daily_data_view` (
`tanggal` datetime
,`total_harga` int(11)
,`jenis_data` varchar(9)
);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `daily_total_view`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `daily_total_view` (
`total_harga_hari_ini` decimal(32,0)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pesanan`
--

CREATE TABLE `detail_pesanan` (
  `id_pesanan` char(15) NOT NULL,
  `id_menu` char(5) NOT NULL,
  `jumlah` int(3) NOT NULL,
  `harga_donat` int(10) NOT NULL,
  `id_rasa` char(6) NOT NULL,
  `jumlah_rasa` int(3) NOT NULL,
  `total_rasa` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_transaksi` char(15) NOT NULL,
  `id_menu` char(5) NOT NULL,
  `qty_donat` int(3) NOT NULL,
  `harga_total` int(10) NOT NULL,
  `id_rasa` char(6) NOT NULL,
  `jumlahrs` int(3) NOT NULL,
  `totalrs` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `ambilstok` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
	UPDATE menu set stok = stok - NEW.qty_donat
    WHERE id_menu = NEW.id_menu;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `history_hapus_akun`
--

CREATE TABLE `history_hapus_akun` (
  `id_log` int(5) NOT NULL,
  `id_akun` char(5) DEFAULT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `no_telp` char(13) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `level` char(15) NOT NULL,
  `kartu` char(15) NOT NULL,
  `waktu` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id_akun` char(5) NOT NULL,
  `nama_kasir` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `no_telp` char(13) NOT NULL,
  `email` char(50) NOT NULL,
  `alamat` char(15) NOT NULL,
  `level` char(10) NOT NULL,
  `kartu` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id_akun`, `nama_kasir`, `username`, `password`, `no_telp`, `email`, `alamat`, `level`, `kartu`) VALUES
('AKN01', 'admin', 'admin', 'admin', '08125555555', 'tytydak@gmail.com', 'lumajang', 'admin', '1854637502');

--
-- Trigger `karyawan`
--
DELIMITER $$
CREATE TRIGGER `BEFORE_DELETE_AKUN` BEFORE DELETE ON `karyawan` FOR EACH ROW BEGIN
INSERT INTO history_hapus_akun SET 
id_akun = old.id_akun,
nama = old.nama_kasir,
username = old.username,
password = old.password,
no_telp = old.no_telp,
alamat = old.alamat,
level = old.level,
kartu = old.kartu,
waktu = now();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `log_menu`
--

CREATE TABLE `log_menu` (
  `id_log` int(11) NOT NULL,
  `id_menu` char(5) NOT NULL,
  `menu_baru` varchar(30) NOT NULL,
  `menu_lama` varchar(30) NOT NULL,
  `harga_lama` int(6) NOT NULL,
  `harga_baru` int(6) NOT NULL,
  `stok_lama` int(3) NOT NULL,
  `stok_baru` int(3) NOT NULL,
  `waktu` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `log_menu`
--

INSERT INTO `log_menu` (`id_log`, `id_menu`, `menu_baru`, `menu_lama`, `harga_lama`, `harga_baru`, `stok_lama`, `stok_baru`, `waktu`) VALUES
(0, 'MN01', 'donat', 'donat', 3000, 3000, 13, 11, '2024-05-26 21:14:30');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `id_menu` char(5) NOT NULL,
  `nama` char(15) NOT NULL,
  `hargadnt` int(8) NOT NULL,
  `stok` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Trigger `menu`
--
DELIMITER $$
CREATE TRIGGER `UPDATE_MENU` BEFORE UPDATE ON `menu` FOR EACH ROW BEGIN INSERT
INTO log_menu SET id_menu = old.id_menu,
menu_lama = old.nama,
menu_baru = new.nama,
harga_lama = old.hargadnt,
harga_baru = new.hargadnt,
stok_lama = old.stok,
stok_baru = new.stok,
waktu = now();
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengeluaran`
--

CREATE TABLE `pengeluaran` (
  `kode_pengeluaran` char(7) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `nominal` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pesanan`
--

CREATE TABLE `pesanan` (
  `id_pesanan` char(15) NOT NULL,
  `id_karyawan` char(5) NOT NULL,
  `nama_customer` varchar(20) NOT NULL,
  `total_harga` int(8) NOT NULL,
  `bayar` int(8) NOT NULL,
  `kembalian` int(8) NOT NULL,
  `tanggal` datetime NOT NULL,
  `tanggal_ambil` datetime NOT NULL,
  `status_pembayaran` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Trigger `pesanan`
--
DELIMITER $$
CREATE TRIGGER `trg_before_update_bayar` BEFORE UPDATE ON `pesanan` FOR EACH ROW BEGIN
    -- Menjumlahkan nilai lama dengan nilai baru
    SET NEW.bayar = OLD.bayar + NEW.bayar;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `rasa`
--

CREATE TABLE `rasa` (
  `id_rasa` char(6) NOT NULL,
  `nama_rasa` varchar(25) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(15) NOT NULL,
  `tanggal` datetime NOT NULL,
  `id_karyawan` char(5) NOT NULL,
  `total_harga` int(8) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur untuk view `daily_data_view`
--
DROP TABLE IF EXISTS `daily_data_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `daily_data_view`  AS SELECT `pesanan`.`tanggal` AS `tanggal`, `pesanan`.`total_harga` AS `total_harga`, 'pesanan' AS `jenis_data` FROM `pesanan` WHERE cast(`pesanan`.`tanggal` as date) = curdate()union select `transaksi`.`tanggal` AS `tanggal`,`transaksi`.`total_harga` AS `total_harga`,'transaksi' AS `jenis_data` from `transaksi` where cast(`transaksi`.`tanggal` as date) = curdate()  ;

-- --------------------------------------------------------

--
-- Struktur untuk view `daily_total_view`
--
DROP TABLE IF EXISTS `daily_total_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `daily_total_view`  AS SELECT coalesce(sum(`combined_data`.`total_harga`),0) AS `total_harga_hari_ini` FROM (select `transaksi`.`total_harga` AS `total_harga`,`transaksi`.`tanggal` AS `tanggal` from `transaksi` where cast(`transaksi`.`tanggal` as date) = curdate() union all select `pesanan`.`total_harga` AS `total_harga`,`pesanan`.`tanggal` AS `tanggal` from `pesanan` where cast(`pesanan`.`tanggal` as date) = curdate()) AS `combined_data` ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD KEY `id_pesanan` (`id_pesanan`),
  ADD KEY `id_menu` (`id_menu`),
  ADD KEY `id_rasa` (`id_rasa`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_menu` (`id_menu`),
  ADD KEY `id_rasa` (`id_rasa`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indeks untuk tabel `pengeluaran`
--
ALTER TABLE `pengeluaran`
  ADD PRIMARY KEY (`kode_pengeluaran`);

--
-- Indeks untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id_pesanan`),
  ADD KEY `id_akun` (`id_karyawan`);

--
-- Indeks untuk tabel `rasa`
--
ALTER TABLE `rasa`
  ADD PRIMARY KEY (`id_rasa`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_akun` (`id_karyawan`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_pesanan`
--
ALTER TABLE `detail_pesanan`
  ADD CONSTRAINT `detail_pesanan_ibfk_2` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`),
  ADD CONSTRAINT `detail_pesanan_ibfk_3` FOREIGN KEY (`id_rasa`) REFERENCES `rasa` (`id_rasa`),
  ADD CONSTRAINT `detail_pesanan_ibfk_4` FOREIGN KEY (`id_pesanan`) REFERENCES `pesanan` (`id_pesanan`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_3` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`),
  ADD CONSTRAINT `detail_transaksi_ibfk_4` FOREIGN KEY (`id_rasa`) REFERENCES `rasa` (`id_rasa`),
  ADD CONSTRAINT `detail_transaksi_ibfk_5` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_akun`);

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_akun`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
