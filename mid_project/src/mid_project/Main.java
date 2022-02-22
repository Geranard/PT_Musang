package mid_project;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random(System.currentTimeMillis());
	Vector<String> kode = new Vector<String>();
	Vector<String> nama = new Vector<String>();
	Vector<String> jenisKelamin = new Vector<String>();
	Vector<String> jabatan = new Vector<String>();
	Vector<Double> gaji = new Vector<Double>();
	
	public Main() {
		menu();
	}

	public void menu() {
		int option = 0;
		while(option != 5){
			System.out.println("1. Tambah karyawan");
			System.out.println("2. Perbarui karyawan");
			System.out.println("3. Hapus karyawan");
			System.out.println("4. Lihat karyawan");
			System.out.println("5. Keluar");
			
			option = sc.nextInt(); sc.nextLine();
			if(option == 1) {
				tambah();
			}
			else if(option == 2) {
				perbarui();
			}
			else if(option == 3) {
				hapus();
			}
			else if(option == 4) {
				tampil();
				System.out.println("dan tekan enter untuk kembali");
				sc.nextLine();
			}
			else if(option == 5) {
				System.out.println("Terima kasih sudah menggunakan aplikasi kami.");
			}
		}
	}
	
	public void tambah() {
		String temp_kode = String.format("%c%c-%d%d%d%d", rand.nextInt(91-65) + 65, rand.nextInt(91-65) + 65, rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
		
		String temp_nama;
		do {
			System.out.println("Nama: ");
			temp_nama = sc.nextLine();
		}while(temp_nama.length()<3);
	
		String temp_kelamin;
		do {
			System.out.println("Jenis kelamin [Laki-laki/Perempuan] (Case Sensitive): ");
			temp_kelamin = sc.nextLine();
		}while(!(temp_kelamin.equals("Laki-laki") || temp_kelamin.equals("Perempuan")));
		
		String temp_jabatan;
		do {
			System.out.println("Jabatan [Manager/Supervisor/Admin] (Case Sensitive): ");
			temp_jabatan = sc.nextLine();
		}while(!(temp_jabatan.equals("Manager") || temp_jabatan.equals("Supervisor") || temp_jabatan.equals("Admin")));
		
		double temp_gaji = 0;
		if(temp_jabatan.equals("Manager")) {
			temp_gaji = 8000000;
		}
		else if(temp_jabatan.equals("Supervisor")) {
			temp_gaji = 6000000;
		}
		else if(temp_jabatan.equals("Admin")) {
			temp_gaji = 4000000;
		}
		kode.add(temp_kode);
		nama.add(temp_nama);
		jenisKelamin.add(temp_kelamin);
		jabatan.add(temp_jabatan);
		gaji.add(temp_gaji);
		System.out.println("Karyawan berhasil ditambahkan dengan ID " + temp_kode);
		
		int flag_manager = 0;
		int flag_supervisor = 0;
		int flag_admin = 0;
		
		int ctr = 0;
		if(temp_jabatan.equals("Manager")) {
			for(int i=0; i<jabatan.size() - 1; i++) {
				if(jabatan.get(i).equals("Manager")) {
					ctr += 1;
				}
			}
			if(ctr % 3 == 0 && ctr >= 3) {
				System.out.print("Bonus sebesar 10% diberikan kepada karyawan dengan ID");
				flag_manager = 1; 
			}
		}
		
		ctr = 0;
		if(temp_jabatan.equals("Supervisor")) {
			for(int i=0; i<jabatan.size() - 1; i++) {
				if(jabatan.get(i).equals("Supervisor")) {
					ctr += 1;
				}
			}
			if(ctr % 3 == 0 && ctr >= 3) {
				System.out.print("Bonus sebesar 7.5% diberikan kepada karyawan dengan ID");
				flag_supervisor = 1;
			}
		}
		
		ctr = 0;
		if(temp_jabatan.equals("Admin")) {
			for(int i=0; i<jabatan.size() - 1; i++) {
				if(jabatan.get(i).equals("Admin")) {
					ctr += 1;
				}
			}
			if(ctr % 3 == 0 && ctr >= 3) {
				System.out.print("Bonus sebesar 5% diberikan kepada karyawan dengan ID");
				flag_admin = 1;
			}
		}
		
		for(int i=0; i<jabatan.size()-1; i++) {
			if(flag_manager == 1 && jabatan.get(i).equals("Manager")) {
				System.out.print(" "+kode.get(i)+",");
				gaji.set(i, gaji.get(i) + gaji.get(i) * 0.1);
			}
			if(flag_supervisor == 1 && jabatan.get(i).equals("Supervisor")) {
				System.out.print(" "+kode.get(i)+",");
				gaji.set(i, gaji.get(i) + gaji.get(i) * 0.075);
			}
			if(flag_admin == 1 && jabatan.get(i).equals("Admin")) {
				System.out.print(" "+kode.get(i)+",");
				gaji.set(i, gaji.get(i) + gaji.get(i) * 0.05);
			}
		}
		
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void perbarui() {
		if(kode.size() == 0) {
			System.out.println("Tidak ada karyawan. Tekan enter untuk kembali.");
			sc.nextInt();
			return;
		}
		tampil();
		
		int index = 0;
		do {
			System.out.println("Input nomor urutan karyawan yang ingin diupdate: ");
			index = sc.nextInt(); sc.nextLine();
			index -= 1;
		}while(index > kode.size() || index < 0);
		
		String temp_nama;
		do {
			System.out.println("Input nama karyawan [>= 3]: ");
			temp_nama = sc.nextLine();
		}while(!(temp_nama.length() >= 3 || temp_nama.equals("0")));
		
		String temp_kelamin;
		do {
			System.out.println("Input jenis kelamin [Laki-laki/Perempuan] (Case Sensitive): ");
			temp_kelamin = sc.nextLine();
		}while(!(temp_kelamin.equals("Laki-laki") || temp_kelamin.equals("Perempuan") || temp_kelamin.equals("0")));
		
		String temp_jabatan;
		do {
			System.out.println("Jabatan [Manager/Supervisor/Admin] (Case Sensitive): ");
			temp_jabatan = sc.nextLine();
		}while(!(temp_jabatan.equals("Manager") || temp_jabatan.equals("Supervisor") || temp_jabatan.equals("Admin") || temp_jabatan.equals("0")));
	
		if(!temp_nama.equals("0")) {
			nama.set(index, temp_nama);
		}
		if(!temp_kelamin.equals("0")) {
			jenisKelamin.set(index, temp_kelamin);
		}
		if(!temp_jabatan.equals("0")) {
			jabatan.set(index, temp_jabatan);
		}
		
		System.out.println("Berhasil mengupdate karyawan dengan ID " + kode.get(index));
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void hapus() {
		if(kode.size() == 0) {
			System.out.println("Tidak ada karyawan. Tekan enter untuk kembali.");
			sc.nextInt();
			return;
		}
		tampil();
		
		int index = 0;
		do {
			System.out.println("Input nomor urutan karyawan yang ingin dihapus: ");
			index = sc.nextInt(); sc.nextLine();
			index -= 1;
		}while(index > kode.size() || index < 0);
		
		System.out.println("Karyawan dengan kode " + kode.get(index) + " berhasil dihapus");
		kode.remove(index);
		nama.remove(index);
		jenisKelamin.remove(index);
		jabatan.remove(index);
		gaji.remove(index);
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void tampil() {
		if(kode.size() == 0) {
			System.out.println("Tidak ada karyawan. Tekan enter untuk kembali.");
			sc.nextInt();
			return;
		}
		for(int i=0; i<nama.size(); i++) {
			for(int j=i+1; j<nama.size(); j++) {
				if(nama.get(i).compareTo(nama.get(j)) > 0) {
					Collections.swap(kode, i, j);
					Collections.swap(nama, i, j);
					Collections.swap(jenisKelamin, i, j);
					Collections.swap(jabatan, i, j);
					Collections.swap(gaji, i, j);
				}
			}
		}
		
		for(int i=0; i<kode.size(); i++) {
			int num = i + 1;
			System.out.println("===========================================");
			System.out.println("No. " + num);
			System.out.println("Kode: " + kode.get(i));
			System.out.println("Nama: " + nama.get(i));
			System.out.println("Jenis Kelamin: " + jenisKelamin.get(i));
			System.out.println("Jabatan: " + jabatan.get(i));
			System.out.println("Gaji Karyawan: " + gaji.get(i));
			System.out.println("===========================================");
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
