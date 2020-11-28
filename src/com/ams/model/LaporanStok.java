package com.ams.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LaporanStok {
	private String judulBuku;
	private int jumlahBuku;
	private int stokSewa;
	private int stokAda;
	private Date tglKembaliPertama;
	
	public String getJudulBuku() {
		return judulBuku;
	}

	public void setJudulBuku(String judulBuku) {
		this.judulBuku = judulBuku;
	}

	public int getJumlahBuku() {
		return jumlahBuku;
	}

	public void setJumlahBuku(int jumlahBuku) {
		this.jumlahBuku = jumlahBuku;
	}

	public int getStokSewa() {
		return stokSewa;
	}

	public void setStokSewa(int stokSewa) {
		this.stokSewa = stokSewa;
	}

	public int getStokAda() {
		return stokAda;
	}

	public void setStokAda(int stokAda) {
		this.stokAda = stokAda;
	}

	public Date getTglKembaliPertama() {
		return tglKembaliPertama;
	}
	
	public void setTglKembaliPertama(Date tglKembaliPertama) {
		this.tglKembaliPertama = tglKembaliPertama;
	}
	
	public String getFormatedTglKembaliPertama() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return getTglKembaliPertama()!=null ? sdf.format(getTglKembaliPertama()) : "-";
	}
}
