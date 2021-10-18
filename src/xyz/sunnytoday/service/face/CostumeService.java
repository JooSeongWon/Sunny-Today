package xyz.sunnytoday.service.face;

import java.util.List;

import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;

public interface CostumeService {
	
	/**
	 * 해당하는 온도의 Costume 리스트 조회
	 * 
	 * @param temperatures - 조회한 일정의 온도
	 * @return List<Costume> - 조회된 Costume 리스트
	 */
	public List<Costume> selectCostumeList(int temperatures);
	
	/**
	 * file_no와 일치하는 File 조회
	 * 
	 * @param file_no - 조회한 Costume에서 추출한 file_no
	 * @return - File - 조회된 File
	 */
	public File selectCostume_File(int file_no);
	
	
}
