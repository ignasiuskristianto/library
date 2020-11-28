package com.ams.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.ams.model.DetailSewa;
import com.ams.model.KategoriBuku;


/**
 * This is an object that contains data related to the buku table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="buku"
 */

public abstract class BaseBuku  implements Serializable {

	public static String REF = "Buku";
	public static String PROP_KATEGORI = "Kategori";
	public static String PROP_KODE = "Kode";
	public static String PROP_JUDUL = "Judul";
	public static String PROP_JUMLAH = "Jumlah";
	public static String PROP_CREATED_BY = "CreatedBy";
	public static String PROP_CREATED_ON = "CreatedOn";
	public static String PROP_UPDATED_BY = "UpdatedBy";
	public static String PROP_UPDATED_ON = "UpdatedOn";

	// constructors
	public BaseBuku () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBuku (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private KategoriBuku kategori;
	private java.lang.String kode;
	private java.lang.String judul;
	private Integer jumlah;
	private java.lang.String createdBy;
	private java.sql.Timestamp createdOn;
	private java.lang.String updatedBy;
	private java.sql.Timestamp updatedOn;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="id_buku"
     */
	public long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	
	public KategoriBuku getKategori() {
		return kategori;
	}

	public void setKategori(KategoriBuku kategori) {
		this.kategori = kategori;
	}

	/**
	 * Return the value associated with the column: kode_buku
	 */
	public java.lang.String getKode () {
		return kode;
	}

	/**
	 * Set the value related to the column: kode_buku
	 * @param kode the kode_buku value
	 */
	public void setKode (java.lang.String kode) {
		this.kode = kode;
	}



	/**
	 * Return the value associated with the column: judul_buku
	 */
	public java.lang.String getJudul () {
		return judul;
	}

	/**
	 * Set the value related to the column: judul_buku
	 * @param nama the judul_buku value
	 */
	public void setJudul (java.lang.String judul) {
		this.judul = judul;
	}

	/**
	 * Return the value associated with the column: jumlah
	 */
	public Integer getJumlah () {
		return jumlah;
	}

	/**
	 * Set the value related to the column: jumlah
	 * @param tglGabung the jumlah value
	 */
	public void setJumlah (Integer jumlah) {
		this.jumlah = jumlah;
	}

	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	public java.sql.Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(java.sql.Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public java.lang.String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public java.sql.Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(java.sql.Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.ams.model.Buku)) return false;
		else {
			com.ams.model.Buku buku = (com.ams.model.Buku) obj;
			return (this.getId() == buku.getId());
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			return (int) this.getId();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}