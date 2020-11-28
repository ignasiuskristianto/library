package com.ams.model;

import java.text.SimpleDateFormat;

import com.ams.model.base.BaseAnggota;



public class Anggota extends BaseAnggota {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Anggota () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Anggota (long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/
	
	public String getFormatedTglLahir()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getTglLahir());
	}
	
	public String getFormatedTglGabung()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(getTglGabung());
	}
}