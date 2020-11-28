package com.ams.model.dao;

import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.ams.form.SewaForm;
import com.ams.model.Buku;
import com.ams.model.base.BaseSewaDAO;


public class SewaDAO extends BaseSewaDAO {

	/**
	 * Default constructor.  Can be used in place of getInstance()
	 */
	public SewaDAO () {

	}
	
	public Integer cekStok(SewaForm form, Buku buku) {
		Session session = null;
		Integer stok = 0;
		try {
			session = BukuDAO.getInstance().getSession();
			String sql = "select b.jumlah_buku-coalesce(qs.stok_sewa, 0) as stokAda from (buku b left join "+
					"(select ds.id_buku as id_buku, sum(ds.jumlah) as stok_sewa from detail_sewa ds "+
					"inner join sewa s on s.id_sewa = ds.id_sewa where s.tgl_sewa < :tglAkhir and "+
					"s.tgl_kembali > :tglMulai ";
			if(form.getLong("id") > 0) sql += "and s.id_sewa <> "+form.getLong("id")+" ";
			sql += "group by ds.id_buku) qs on b.id_buku = qs.id_buku) where b.id_buku = :idBuku";
			stok = (Integer) session.createSQLQuery(sql)
					.addScalar("stokAda", Hibernate.INTEGER)
					.setDate("tglMulai", form.getCalendar("tglSewa").getTime())
					.setDate("tglAkhir", form.getCalendar("tglKembali").getTime())
					.setLong("idBuku", buku.getId())
					.uniqueResult();
		}finally {
			if(session!=null) session.close();
		}
		return stok;
	}
	
}