package xyz.sunnytoday.dao.face;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;

public interface CostumeDao {

	List<Costume> selectCostumeList(Connection conn, int temperatures);

	File selectCostume_File(Connection conn, int file_no);

	List<Costume> selectCostumesIf(Connection connection, int temp, String type, String gender);
	
}
