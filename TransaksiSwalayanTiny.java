// Kelas untuk mengelola transaksi beli dan top-up
public class TransaksiSwalayanTiny {
    // Fungsi beli: autentikasi dulu, lalu beli jika benar
    public static void beli(PelangganSwalayanTiny p, double jumlah, String pin) {
        if (p.autentikasi(pin)) {
            p.beli(jumlah);
        }
    }

    // Fungsi top up: autentikasi dulu, baru top up
    public static void topUp(PelangganSwalayanTiny p, double jumlah, String pin) {
        if (p.autentikasi(pin)) {
            p.topUp(jumlah);
        }
    }
}