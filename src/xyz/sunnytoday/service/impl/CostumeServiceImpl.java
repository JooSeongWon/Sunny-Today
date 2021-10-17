package xyz.sunnytoday.service.impl;

import java.sql.Connection;
import java.util.List;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.CostumeDao;
import xyz.sunnytoday.dao.impl.CostumeDaoImpl;
import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.service.face.CostumeService;

public class CostumeServiceImpl implements CostumeService {
	
	//DAO 객체 생성
	private final CostumeDao costumeDao = new CostumeDaoImpl();

	@Override
	public List<Costume> selectCostumeList(int temperatures) {
		
		List<Costume> result = null;
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = costumeDao.selectCostumeList(conn, temperatures);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public File selectCostume_File(int file_no) {
		
		File result = null;
		
		try (Connection conn = JDBCTemplate.getConnection()) {
			result = costumeDao.selectCostume_File(conn, file_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
