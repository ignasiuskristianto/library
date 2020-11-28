package com.ams.model.base;

import java.io.Serializable;

import com.ams.model.Buku;
import com.ams.model.Sewa;
import com.ams.model.User;


/**
 * This is an object that contains data related to the detail_sewa table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="detail_sewa"
 */

public abstract class BaseDetailSewa  implements Serializable {

	public static String REF = "DetailSewa";
	public static String PROP_SEWA = "Sewa";
	public static String PROP_BUKU = "Buku";
	public static String PROP_JUMLAH = "Jumlah";
	public static String PROP_CREATED_BY = "CreatedBy";
	public static String PROP_CREATED_ON = "CreatedOn";
	public static String PROP_UPDATED_BY = "UpdatedBy";
	public static String PROP_UPDATED_ON = "UpdatedOn";


	// constructors
	public BaseDetailSewa () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDetailSewa (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private Sewa sewa;
	private Buku buku;
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

	public Sewa getSewa() {
		return sewa;
	}

	public void setSewa(Sewa sewa) {
		this.sewa = sewa;
	}

	public Buku getBuku() {
		return buku;
	}

	public void setBuku(Buku buku) {
		this.buku = buku;
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
		if (!(obj instanceof com.ams.model.DetailSewa)) return false;
		else {
			com.ams.model.DetailSewa detailSewa = (com.ams.model.DetailSewa) obj;
			return (this.getId() == detailSewa.getId());
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