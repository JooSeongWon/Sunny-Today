package sample;

import java.util.ArrayList;
import java.util.List;

import xyz.sunnytoday.common.util.ThumbnailMaker;

public class Test {

	public static void main(String[] args) {
		
//		ThumbnailMaker.makeThumbnail("하의_공용_108821c3d5.PNG", "C:\\Users\\KIM\\Desktop\\KH\\workspace_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SunnyToday\\upload", 100, 100);
		
		int num1 = 0; //2, 4, 6, 8
		int num2 = 0; //0, 2, 4, 6
		
		List<Integer> resultFileCnt = new ArrayList<>();
		
		for(int i=0; i<4; i++) {
			
			
			if(num1 != 0) {
				num2 += 3;
			}
			
			num1 += 3;
			
			resultFileCnt.add((int) Math.floor( ( Math.random() * (num1 - num2) + num2 ) ));
			
		}
		
		System.out.println(resultFileCnt.get(0));
		System.out.println(resultFileCnt.get(1));
		System.out.println(resultFileCnt.get(2));
		System.out.println(resultFileCnt.get(3));
//		
//		System.out.println("최대값: " + (num1-1));
//		System.out.println("최소값: " + num2);
		
	}

}
