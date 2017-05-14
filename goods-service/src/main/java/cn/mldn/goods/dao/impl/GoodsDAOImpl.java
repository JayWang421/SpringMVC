package cn.mldn.goods.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.mldn.goods.dao.IGoodsDAO;
import cn.mldn.goods.dao.abs.AbstractDAO;
import cn.mldn.goods.vo.Goods;

@Repository
public class GoodsDAOImpl extends AbstractDAO implements IGoodsDAO,RowMapper<Goods> {
	
	@Override
	public Goods mapRow(ResultSet rs, int arg1) throws SQLException {
		Goods vo=new Goods() ;
		vo.setGid(rs.getLong(1));
		vo.setIid(rs.getLong(2));
		vo.setSid(rs.getLong(3));
		vo.setTitle(rs.getString(4));
		vo.setPrice(rs.getDouble(5));
		vo.setPhoto(rs.getString(6));
		vo.setDelflag(rs.getInt(7));
		return vo;
	}

	@Override
	public boolean doCreate(Goods vo) throws Exception {
		String sql="insert into goods(iid,sid,title,price,photo,delflag) values(?,?,?,?,?,?) " ;
		return super.jdbcTemplate.update(sql,vo.getIid(),vo.getSid(),vo.getTitle(),vo.getPrice(),vo.getPhoto(),vo.getDelflag())>0;
	}

	@Override
	public List<Goods> findAllByDelflag(Long currentPage, Long lineSize, Integer del) throws Exception {
		String sql="select gid,iid,sid,title,price,photo,delflag from goods where delflag=? limit ?,? " ;
		return super.jdbcTemplate.query(sql, new Object[]{del,(currentPage-1)*lineSize,lineSize},this);
	}
	
	@Override
	public List<Goods> findAllByDelflag(Long currentPage, Long lineSize, String column, String keyWord, Integer del)
			throws Exception {
		String sql="select gid,iid,sid,title,price,photo,delflag from goods where delflag=? and "+column+" like ? limit ?,? " ;
		return super.jdbcTemplate.query(sql,new Object[]{del,"%"+keyWord+"%",(currentPage-1)*lineSize,lineSize},this);
	}
	
	@Override
	public Long getAllCountByDelflag(Integer del) throws Exception {
		String sql="select count(*) from goods where delflag=? " ;
		return super.jdbcTemplate.queryForObject(sql, new Object[]{del},Long.class);
	}
	
	@Override
	public Long getAllCountByDelflag(String column, String keyWord,Integer del) throws Exception {
		String sql="select count(*) from goods where delflag=? and "+column+" like ? " ;
		return super.jdbcTemplate.queryForObject(sql, new Object[]{del,"%"+keyWord+"%"},Long.class);
	}
	
	@Override
	public Goods findById(Long id) throws Exception {
		String sql="select gid,iid,sid,title,price,photo,delflag from goods where gid=? " ;
		return super.jdbcTemplate.queryForObject(sql, new Object[]{id},this);
	}
	
	@Override
	public boolean doUpdate(Goods vo) throws Exception {
		String sql="update goods set iid=?,sid=?,title=?,price=? where gid=? " ;
		return super.jdbcTemplate.update(sql,vo.getIid(),vo.getSid(),vo.getTitle(),vo.getPrice(),vo.getGid())>0;
	}


	@Override
	public boolean doRemoveBatch(Set<Long> ids) throws Exception {
		StringBuffer buf=new StringBuffer() ;
		buf.append("update goods set delflag=? where gid in ( ") ;
		Iterator<Long> iter=ids.iterator() ;
		while(iter.hasNext()){
			buf.append(iter.next()).append(",") ;
		}
		buf.delete(buf.length()-1, buf.length()).append(")") ;
		return super.jdbcTemplate.update(buf.toString(),1)>0;
	}
	
	@Override
	public boolean doRemove(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public List<Goods> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
