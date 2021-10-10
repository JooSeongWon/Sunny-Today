package sample;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class SampleMain extends HttpServlet {
    
    
    /*
    * 
    * JDBCTemplate 에 적용한 커넥션풀은 톰캣이 실행되어야만 사용가능함으로 서블릿으로 테스트 해봐야 합니다.
    * url /test로 접속해서 결과를 확인해보세요
    * 해당코드는 모두 확인하시면 지우겠습니다.
    * 
    * */
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        TransactionSampleService transactionSampleService = new TransactionSampleService();

        System.out.println("모든계좌 조회");
        transactionSampleService.getSampleAccounts().forEach(System.out::println);

        System.out.println("A계좌에서 B계좌로 6천원 이체 시도");
        System.out.println(transactionSampleService.SendMoney("A", "B", 6000));

        System.out.println("모든계좌 한번더 조회");
        transactionSampleService.getSampleAccounts().forEach(System.out::println);

        System.out.println("A계좌에서 B계좌로 3천원 이체");
        System.out.println(transactionSampleService.SendMoney("A", "B", 3000));

        System.out.println("모든계좌 한번더 조회");
        transactionSampleService.getSampleAccounts().forEach(System.out::println);
    }
}
