package xyz.sunnytoday.common.util;

import java.util.UUID;

public class UUIDTest {
	public static void main(String[] args) {
		//UUID, Universal Uinque IDentifier
		//	범용 고유 식별자
		//	32자리의 16진수형태의 문자열을 생성한다
		//	8 - 4 - 4 - 4 - 12 개씩 구분해서 생성한다
		
		//랜덤 UUID값 생성
		UUID uuid =  UUID.randomUUID();
		
		System.out.println(uuid.toString());
		
		//전체 UUID중에서 앞에 한 그룹(8자리)만 추출하기
		System.out.println(uuid.toString().split("-")[0]);
		
		
		
		
		
		
	}
}
