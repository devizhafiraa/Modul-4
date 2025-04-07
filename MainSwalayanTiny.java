import java.util.Scanner;

// Kelas utama (main) untuk menjalankan aplikasi Swalayan Tiny
public class MainSwalayanTiny {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // === Membuat 3 pelanggan ===
        PelangganSwalayanTiny[] daftarPelanggan = {
            new PelangganSwalayanTiny("Ahmad J. Rumi", "3812345678", 2000000, "1234"), // Silver
            new PelangganSwalayanTiny("Iqbaal Ramadhan", "5612345678", 1500000, "1234"), // Gold
            new PelangganSwalayanTiny("Nicholas Saputra", "7412345678", 3000000, "1234")  // Platinum
        };

        System.out.println("Selamat datang di Swalayan Tiny!");
        System.out.print("Masukkan Nomor Pelanggan: ");
        String inputNomor = scanner.next();

        // Cari pelanggan dari input user
        PelangganSwalayanTiny pelangganAktif = null;
        for (PelangganSwalayanTiny p : daftarPelanggan) {
            if (p.getNomorPelanggan().equals(inputNomor)) {
                pelangganAktif = p;
                break;
            }
        }

        // Jika pelanggan tidak ditemukan
        if (pelangganAktif == null) {
            System.out.println("Nomor pelanggan tidak ditemukan.");
            return;
        }

        // Tampilkan informasi pelanggan aktif
        System.out.println("Nama Pelanggan: " + pelangganAktif.getNama());
        System.out.println("Nomor Pelanggan: " + pelangganAktif.getNomorPelanggan());
        System.out.println("Jenis Akun: " + pelangganAktif.getJenisPelanggan());
        System.out.println("Saldo Anda: Rp " + pelangganAktif.getSaldo());

        // === Menu utama ===
        while (true) {
            System.out.println("Menu: 1. Beli  2. Top-Up  3. Keluar");
            int pilihan = scanner.nextInt();
            if (pilihan == 3) break; // Keluar aplikasi

            if (pelangganAktif.isDiblokir()) {
                System.out.println("Akun ini telah diblokir. Transaksi tidak dapat dilakukan.");
                continue;
            }

            // Input PIN dan nominal transaksi
            System.out.print("Masukkan PIN: ");
            String pin = scanner.next();

            System.out.print("Masukkan jumlah: ");
            double jumlah = scanner.nextDouble();

            // Jalankan transaksi sesuai pilihan
            if (pilihan == 1) {
                TransaksiSwalayanTiny.beli(pelangganAktif, jumlah, pin);
            } else if (pilihan == 2) {
                TransaksiSwalayanTiny.topUp(pelangganAktif, jumlah, pin);
            } else {
                System.out.println("Pilihan tidak tersedia.");
            }
        }

        scanner.close();
    }
}