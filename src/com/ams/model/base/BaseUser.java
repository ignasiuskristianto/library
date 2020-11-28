package com.ams.model.base;

import java.io.Serializable;

import com.ams.model.User;


/**
 * This is an object that contains data related to the users table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="pengguna"
 */

public abstract class BaseUser  implements Serializable {

	public static String REF = "User";
	public static String PROP_USERNAME = "Username";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_CREATED_BY = "CreatedBy";
	public static String PROP_CREATED_ON = "CreatedOn";
	public static String PROP_UPDATED_BY = "UpdatedBy";
	public static String PROP_UPDATED_ON = "UpdatedOn";


	// constructors
	public BaseUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUser (long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private long id;

	// fields
	private java.lang.String username;
	private java.lang.String password;
	private java.lang.String createdBy;
	private java.sql.Timestamp createdOn;
	private java.lang.String updatedBy;
	private java.sql.Timestamp updatedOn;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="id_pengguna"
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




	/**
	 * Return the value associated with the column: nama_pengguna
	 */
	public java.lang.String getUsername () {
		return username;
	}

	/**
	 * Set the value related to the column: nama_pengguna
	 * @param username the username value
	 */
	public void setUsername (java.lang.String username) {
		this.username = username;
	}



	/**
	 * Return the value associated with the column: sandi
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: sandi
	 * @param password the password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
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
		if (!(obj instanceof com.ams.model.User)) return false;
		else {
			com.ams.model.User user = (com.ams.model.User) obj;
			return (this.getId() == user.getId());
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