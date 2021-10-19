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

	@Override
	public Costume[] getRand(int temperature, String gender) {
		Costume[] result = new Costume[2];

		try (Connection conn = JDBCTemplate.getConnection()) {
			List<Costume> costumesTop = costumeDao.selectCostumesIf(conn, temperature, "T", gender);
			List<Costume> costumesPants = costumeDao.selectCostumesIf(conn, temperature, "P", gender);

			Costume costume = new Costume();
			if(costumesTop.isEmpty()) {
				costume.setTitle("의상 없음");
				costume.setThumbNail("no-img.PNG");
				result[0] = costume;
			} else {
				result[0] = costumesTop.get((int) (Math.random() * costumesTop.size()));
			}

			if(costumesPants.isEmpty()) {
				costume.setTitle("의상 없음");
				costume.setThumbNail("no-img.PNG");
				result[1] = costume;
			} else {
				result[1] = costumesPants.get((int) (Math.random() * costumesPants.size()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
