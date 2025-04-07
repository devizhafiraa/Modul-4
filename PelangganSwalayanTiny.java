// Kelas ini merepresentasikan data dan perilaku dari pelanggan swalayan
public class PelangganSwalayanTiny {
    // === Poin 1: Informasi akun pelanggan dibuat private (Encapsulation) ===
    private String nama; // Nama pelanggan
    private String nomorPelanggan; // Nomor pelanggan terdiri dari 10 digit
    private double saldo; // Saldo pelanggan
    private String pin; // PIN untuk autentikasi transaksi
    private String jenisPelanggan; // Jenis pelanggan: Silver, Gold, Platinum
    private boolean diblokir = false; // Status blokir akun (poin 5)
    private int percobaanGagal = 0; // Jumlah kesalahan autentikasi (poin 5)

    // Konstruktor untuk membuat objek pelanggan baru
    public PelangganSwalayanTiny(String nama, String nomorPelanggan, double saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.jenisPelanggan = tentukanJenisPelanggan(nomorPelanggan); // === Poin 2 ===
    }
 // Getter nama pelanggan
 public String getNama() {
    return nama;
}
    // Getter untuk nomor pelanggan
    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    // Getter untuk saldo
    public double getSaldo() {
        return saldo;
    }

    // Getter untuk jenis pelanggan
    public String getJenisPelanggan() {
        return jenisPelanggan;
    }

    // === Poin 2: Menentukan jenis pelanggan berdasarkan 2 digit awal ===
    private String tentukanJenisPelanggan(String nomor) {
        if (nomor.startsWith("38")) return "Silver";
        else if (nomor.startsWith("56")) return "Gold";
        else if (nomor.startsWith("74")) return "Platinum";
        else return "Tidak Dikenal";
    }

    // Mengecek apakah akun diblokir
    public boolean isDiblokir() {
        return diblokir;
    }

    // === Poin 5: Autentikasi dengan maksimal 3 kali percobaan ===
    public boolean autentikasi(String pinInput) {
        if (diblokir) return false;
        if (this.pin.equals(pinInput)) {
            percobaanGagal = 0; // Reset jika berhasil
            return true;
        } else {
            percobaanGagal++;
            System.out.println("PIN salah! Percobaan ke-" + percobaanGagal);
            if (percobaanGagal >= 3) {
                diblokir = true;
                System.out.println("Akun diblokir karena terlalu banyak percobaan gagal!");
            }
            return false;
        }
    }

    // === Poin 3: Pembelian harus menyisakan minimal saldo 10.000 ===
    public boolean beli(double jumlah) {
        if (saldo - jumlah < 10000) {
            System.out.println("Saldo tidak mencukupi untuk transaksi ini!. Saldo harus ada minimal 10.000");
            return false;
        }

        double cashback = 0.0;

        // === Poin 2: Cashback sesuai jenis pelanggan ===
        if (jumlah > 1_000_000) {
            switch (jenisPelanggan) {
                case "Silver": cashback = 0.05 * jumlah; break; // Silver 5%
                case "Gold": cashback = 0.07 * jumlah; break;   // Gold 7%
                case "Platinum": cashback = 0.10 * jumlah; break; // Platinum 10%
            }
        } else {
            switch (jenisPelanggan) {
                case "Gold": cashback = 0.02 * jumlah; break; // Gold 2%
                case "Platinum": cashback = 0.05 * jumlah; break; // Platinum 5%
            }
        }

        saldo -= jumlah;
        saldo += cashback;

        System.out.println("Pembelian sukses! Cashback diterima: " + cashback);
        return true;
    }

    // Fungsi untuk top up saldo
    public boolean topUp(double jumlah) {
        saldo += jumlah;
        System.out.println("Top up berhasil. Saldo sekarang: Rp " + saldo);
        return true;
    }
}