import java.util.Scanner;

public class main {

    // ===== INSERTION SORT berdasarkan denda terbesar =====
    static void insertionSort(Peminjaman[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Peminjaman key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].denda < key.denda) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ===== BINARY SEARCH berdasarkan NIM =====
    // Binary search memerlukan data terurut berdasarkan NIM terlebih dahulu
    static void sortByNim(Peminjaman[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Peminjaman key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].mhs.nim.compareTo(key.mhs.nim) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void binarySearchNim(Peminjaman[] arr, String nimCari) {
        // Buat salinan array dan urutkan berdasarkan NIM untuk binary search
        Peminjaman[] sorted = arr.clone();
        sortByNim(sorted);

        int left = 0, right = sorted.length - 1;
        int firstFound = -1;

        // Cari posisi pertama ditemukan
        while (left <= right) {
            int mid = (left + right) / 2;
            int cmp = sorted[mid].mhs.nim.compareTo(nimCari);
            if (cmp == 0) {
                firstFound = mid;
                right = mid - 1; // terus cari ke kiri untuk temukan yang pertama
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (firstFound == -1) {
            System.out.println("Data peminjaman dengan NIM " + nimCari + " tidak ditemukan.");
        } else {
            System.out.println("Hasil pencarian NIM: " + nimCari);
            // Tampilkan semua peminjaman dengan NIM tersebut
            for (int i = firstFound; i < sorted.length; i++) {
                if (sorted[i].mhs.nim.equals(nimCari)) {
                    sorted[i].tampilPeminjaman();
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // ===== DATA MAHASISWA =====
        Mahasiswa[] mahasiswas = {
            new Mahasiswa("22001", "Andi", "Teknik Informatika"),
            new Mahasiswa("22002", "Budi", "Teknik Informatika"),
            new Mahasiswa("22003", "Citra", "Sistem Informasi Bisnis")
        };

        // ===== DATA BUKU =====
        Buku[] bukus = {
            new Buku("B001", "Algoritma", 2020),
            new Buku("B002", "Basis Data", 2019),
            new Buku("B003", "Pemrograman", 2021),
            new Buku("B004", "Fisika", 2024)
        };

        // ===== DATA PEMINJAMAN =====
        Peminjaman[] peminjamans = {
            new Peminjaman(mahasiswas[0], bukus[0], 7),   // Andi - Algoritma
            new Peminjaman(mahasiswas[1], bukus[1], 3),   // Budi - Basis Data
            new Peminjaman(mahasiswas[2], bukus[2], 10),  // Citra - Pemrograman
            new Peminjaman(mahasiswas[2], bukus[3], 6),   // Citra - Fisika
            new Peminjaman(mahasiswas[0], bukus[1], 4)    // Andi - Basis Data
        };

        Scanner sc = new Scanner(System.in);
        int pilih = -1;

        while (pilih != 0) {
            System.out.println("\n=== SISTEM PEMINJAMAN RUANG BACA JTI ===");
            System.out.println("1. Tampilkan Mahasiswa");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Tampilkan Peminjaman");
            System.out.println("4. Urutkan Berdasarkan Denda");
            System.out.println("5. Cari Berdasarkan NIM");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();

            switch (pilih) {
                case 1:
                    System.out.println("\nDaftar Mahasiswa:");
                    for (Mahasiswa m : mahasiswas) {
                        m.tampilMahasiswa();
                    }
                    break;

                case 2:
                    System.out.println("\nDaftar Buku:");
                    for (Buku b : bukus) {
                        b.tampilBuku();
                    }
                    break;

                case 3:
                    System.out.println("\nData Peminjaman:");
                    for (Peminjaman p : peminjamans) {
                        p.tampilPeminjaman();
                    }
                    break;

                case 4:
                    // Insertion Sort - urutkan salinan agar data asli tidak berubah
                    Peminjaman[] sorted = peminjamans.clone();
                    insertionSort(sorted);
                    System.out.println("\nSetelah diurutkan (Denda terbesar):");
                    for (Peminjaman p : sorted) {
                        p.tampilPeminjaman();
                    }
                    break;

                case 5:
                    System.out.print("Masukkan NIM: ");
                    String nim = sc.next();
                    binarySearchNim(peminjamans, nim);
                    break;

                case 0:
                    System.out.println("Terima kasih, program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        sc.close();
    }
}