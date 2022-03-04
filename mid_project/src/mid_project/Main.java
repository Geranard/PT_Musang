package mid_project;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner sc = new Scanner(System.in);
	Random rand = new Random(System.currentTimeMillis());
	Vector<Karyawan> karyawan = new Vector<Karyawan>();
	
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
		
		Karyawan temp = new Karyawan(temp_kode, temp_nama, temp_kelamin, temp_jabatan, temp_gaji);
		karyawan.add(temp);
		
		System.out.println("Karyawan berhasil ditambahkan dengan ID " + temp_kode);
		
		int flag_manager = 0;
		int flag_supervisor = 0;
		int flag_admin = 0;
		
		int ctr = 0;
		if(temp_jabatan.equals("Manager")) {
			for(int i=0; i<karyawan.size() - 1; i++) {
				if(karyawan.get(i).jabatan.equals("Manager")) {
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
			for(int i=0; i<karyawan.size() - 1; i++) {
				if(karyawan.get(i).jabatan.equals("Supervisor")) {
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
			for(int i=0; i<karyawan.size() - 1; i++) {
				if(karyawan.get(i).jabatan.equals("Admin")) {
					ctr += 1;
				}
			}
			if(ctr % 3 == 0 && ctr >= 3) {
				System.out.print("Bonus sebesar 5% diberikan kepada karyawan dengan ID");
				flag_admin = 1;
			}
		}
		
		for(int i=0; i<karyawan.size()-1; i++) {
			if(flag_manager == 1 && karyawan.get(i).jabatan.equals("Manager")) {
				System.out.print(" "+karyawan.get(i).kode+",");
				karyawan.get(i).gaji = karyawan.get(i).gaji + karyawan.get(i).gaji * 0.1;
			}
			if(flag_supervisor == 1 && karyawan.get(i).jabatan.equals("Supervisor")) {
				System.out.print(" "+karyawan.get(i).kode+",");
				karyawan.get(i).gaji = karyawan.get(i).gaji + karyawan.get(i).gaji * 0.075;
			}
			if(flag_admin == 1 && karyawan.get(i).jabatan.equals("Admin")) {
				System.out.print(" "+karyawan.get(i).kode+",");
				karyawan.get(i).gaji = karyawan.get(i).gaji + karyawan.get(i).gaji * 0.05;
			}
		}
		
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void perbarui() {
		if(karyawan.size() == 0) {
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
		}while(index > karyawan.size() || index < 0);
		
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
			karyawan.get(index).nama = temp_nama;
		}
		if(!temp_kelamin.equals("0")) {
			karyawan.get(index).jenisKelamin = temp_kelamin;
		}
		if(!temp_jabatan.equals("0")) {
			karyawan.get(index).jabatan = temp_jabatan;
		}
		
		System.out.println("Berhasil mengupdate karyawan dengan ID " + karyawan.get(index).kode);
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void hapus() {
		if(karyawan.size() == 0) {
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
		}while(index > karyawan.size() || index < 0);
		
		System.out.println("Karyawan dengan kode " + karyawan.get(index).kode + " berhasil dihapus");
		karyawan.remove(index);
		System.out.println("dan tekan enter untuk kembali");
		sc.nextLine();
	}
	
	public void tampil() {
		if(karyawan.size() == 0) {
			System.out.println("Tidak ada karyawan. Tekan enter untuk kembali.");
			sc.nextInt();
			return;
		}
		for(int i=0; i<karyawan.size(); i++) {
			for(int j=i+1; j<karyawan.size(); j++) {
				if(karyawan.get(i).nama.compareTo(karyawan.get(j).nama) > 0) {
					Collections.swap(karyawan, i, j);
				}
			}
		}
		
		for(int i=0; i<karyawan.size(); i++) {
			int num = i + 1;
			System.out.println("===========================================");
			System.out.println("No. " + num);
			System.out.println("Kode: " + karyawan.get(i).kode);
			System.out.println("Nama: " + karyawan.get(i).nama);
			System.out.println("Jenis Kelamin: " + karyawan.get(i).jenisKelamin);
			System.out.println("Jabatan: " + karyawan.get(i).jabatan);
			System.out.println("Gaji Karyawan: " + karyawan.get(i).gaji);
			System.out.println("===========================================");
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
