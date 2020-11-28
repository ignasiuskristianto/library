package com.ams.model.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.ams.form.LaporanForm;
import com.ams.model.LaporanStok;

public class LaporanDAO extends _RootDAO{
	
	public LaporanDAO() {
		
	}
	
	public List<LaporanStok> getLaporanStok(LaporanForm form, int start, int count, int total){
		Session session = null;
		List<LaporanStok> laporanStokList = null;
		try {
			session = (Session) BukuDAO.getInstance().getSession();
			String sql = "select b.judul_buku as judulBuku, b.jumlah_buku as jumlahBuku, coalesce(qs.stok_sewa, 0) as stokSewa, b.jumlah_buku-coalesce(qs.stok_sewa, 0) as stokAda, qs.tgl_kembali as tglKembaliPertama "+
					"from ((buku b inner join kategori_buku kb on b.id_kategori = kb.id_kategori) left join (select ds.id_buku as id_buku, sum(ds.jumlah) as stok_sewa, min(s.tgl_kembali) as tgl_kembali "+
					"from detail_sewa ds inner join sewa s on s.id_sewa = ds.id_sewa where s.tgl_sewa <= :tgl and s.tgl_kembali > :tgl group by ds.id_buku) qs on b.id_buku = qs.id_buku) where 1=1 ";
			if(form.getString("idKategori")!=null && form.getString("idKategori").length()>0){
				sql += "and kb.id_kategori = "+form.getLong("idKategori")+" ";
			}
			if(form.getString("judulBuku")!=null && form.getString("judulBuku").length()>0){
				sql += "and b.judul_buku ilike '%"+form.getString("judulBuku")+"%' ";
			}
			
			total = session.createSQLQuery(sql).setDate("tgl", form.getCalendar("tgl").getTime()).list().size();
			
			if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("asc")){ 
				sql += "order by "+form.getString("orderBy")+" asc";
			}else if (form.getString("orderBy")!=null && form.getString("orderBy").length()>0 && form.getString("ascDesc")!=null && form.getString("ascDesc").length()>0 && form.getString("ascDesc").equalsIgnoreCase("desc")) {
				sql += "order by "+form.getString("orderBy")+" desc";
			}else{	
				sql += "order by b.id_buku asc";
			}
			Query query = session.createSQLQuery(sql)
					.addScalar("judulBuku", Hibernate.STRING)
					.addScalar("jumlahBuku", Hibernate.INTEGER)
					.addScalar("stokSewa", Hibernate.INTEGER)
					.addScalar("stokAda", Hibernate.INTEGER)
					.addScalar("tglKembaliPertama", Hibernate.DATE)
					.setDate("tgl", form.getCalendar("tgl").getTime());
			query.setFirstResult(start);
			query.setMaxResults(count);
			
			laporanStokList = query.setResultTransformer(Transformers.aliasToBean(LaporanStok.class)).list();
		}finally {
			if(session!=null) session.close();
		}
		return laporanStokList;
	}
}
