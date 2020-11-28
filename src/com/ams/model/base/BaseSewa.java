package com.ams.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ams.model.Anggota;
import com.ams.model.DetailSewa;


/**
 * This is an object that contains data related to the sewa table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="sewa"
 */

public abstract class BaseSewa  implements Serializable {

	public static String REF = "Sewa";
	public static String PROP_ANGGOTA = "Anggota";
	public static String PROP_TGL_SEWA = "TglSewa";
	public static String PROP_TGL_KEMBALI = "TglKembali";
	public static String PROP_CREATED_BY = "CreatedBy";
	public static String PROP_CREATED_ON = "CreatedOn";
	public static String PROP_UPDATED_BY = "UpdatedBy";
	public static String PROP_UPDATED_ON = "UpdatedOn";
	public static String PROP_DETAIL_SEWA_LIST = "DetailSewaList";


	// constructors
	public BaseSewa () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSewa (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private Anggota anggota;
	private java.util.Date tglSewa;
	private java.util.Date tglKembali;
	private java.lang.String createdBy;
	private java.sql.Timestamp createdOn;
	private java.lang.String updatedBy;
	private java.sql.Timestamp updatedOn;
	private Set<DetailSewa> detailSewaList = new LinkedHashSet<DetailSewa>();


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="id_sewa"
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

	public Anggota getAnggota() {
		return anggota;
	}

	public void setAnggota(Anggota anggota) {
		this.anggota = anggota;
	}

	/**
	 * Return the value associated with the column: tgl_sewa
	 */
	public java.util.Date getTglSewa () {
		return tglSewa;
	}

	/**
	 * Set the value related to the column: tgl_sewa
	 * @param tglSewa the tgl_sewa value
	 */
	public void setTglSewa (java.util.Date tglSewa) {
		this.tglSewa = tglSewa;
	}

	/**
	 * Return the value associated with the column: tgl_kembali
	 */
	public java.util.Date getTglKembali () {
		return tglKembali;
	}

	/**
	 * Set the value related to the column: tgl_kembali
	 * @param tglKembali the tgl_kembali value
	 */
	public void setTglKembali (java.util.Date tglKembali) {
		this.tglKembali = tglKembali;
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
	
	public Set<DetailSewa> getDetailSewaList() {
		return detailSewaList;
	}

	public void setDetailSewaList(Set<DetailSewa> detailSewaList) {
		this.detailSewaList = detailSewaList;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.ams.model.Sewa)) return false;
		else {
			com.ams.model.Sewa sewa = (com.ams.model.Sewa) obj;
			return (this.getId() == sewa.getId());
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