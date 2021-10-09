package sample;

import xyz.sunnytoday.common.JDBCTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionSampleService {
    /*
    * 트랜잭션 처리 샘플코드입니다.
    *
    * 테스트용 sql 아래 구문을 추가하시면 됩니다.
    *
        create table sample_account ( name varchar2(20), amount number default 0);
        insert into SAMPLE_ACCOUNT values ('A', 5000);
        insert into SAMPLE_ACCOUNT values ('B', 1000);
        commit ;
    *
    * A계좌와 B계좌에 각각 5천원 1천원이 입금되어 있는 상태를 가정합니다.
    * */


    // DAO 객체를 멤버필드로 선언합니다.
    // 한번 선언되어서 수정하면 안돼는 객체는 final 키워드를 붙여주는게 좋습니다.
    private final TransactionSampleDao transactionSampleDao = new TransactionSampleDao();


    //단순 조회 경우 메서드 입니다.
    //모든 계좌 정보를 조회합니다. 여러계좌를 돌려준다는걸 보여주기위해 account의 복수형을 이름으로 씁니다.
    //메서드이름에 OrNull이 없는 것을 토대로 결과가 없을경우 null이 아닌 빈 list를 돌려준다는걸 예상할 수 있습니다.
    public List<SampleAccount> getSampleAccounts() {
        List<SampleAccount> result = null; // 빈 ArrayList 객체를 생성하지 않는 이유는 DAO에서 결과로 넘겨주는 List를 반환할것이기 때문입니다.

        //try with resources 구문입니다. try 옆에 소괄호에 변수를 선언할경우 finally에서 close 하는것과 같은일을
        //자동으로 처리해줍니다.
        try (Connection conn = JDBCTemplate.getConnection()) {

            //트랜잭션 처리의 의무가 service에 있음으로 dao에 connection 객체를 넘겨주고
            //connection을 생성하고 close하는 역할은 모두 service에서 처리해야 합니다.
            result = transactionSampleDao.selectAll(conn);

        } catch (SQLException e) {
            //selectAll에서 발생한 SQLException을 여기서 받아서 처리합니다.
            //단 해당 메서드는 단순 조회임으로 엄밀히 따지면 필요없는 과정이지만 해당 select 메소드를 트랜잭션 처리가
            //필요한 메서드에서 사용할 수 있음으로 통일해서 처리해주겠습니다.
            e.printStackTrace();
        }

        //결과를 반환합니다.
        return result;

        /*
         * 추가+++++++++++++++++++++
         * 단순 select 구문에서는 트랜잭션이 필요없는 관계로 굳이 service에서 예외처리를 해주지 않아도 됩니다.
         * 단 트랜잭션 처리가 필요한 메서드(아래의 돈을 옮기는)에서 중간에 select를 사용할 수도 있음으로 그냥 모든 dao의
         * sql 처리 메서드를 같은 형식으로 제작해주는게 깔끔합니다.
         *
         * */
    }


    //특정 이름의 계좌를 조회합니다.
    //이름이 단수임으로 1개의 결과를 조회한다는 것을 알 수 있습니다.
    //OrNull이 붙어있음으로 결과가 없는경우 Null이 리턴됨을 예상할 수 있습니다.
    public SampleAccount getSampleAccountByNameOrNull(String name) {
        //학원에서 배운 방법으로 try with resources 구문없이 작성했습니다.
        Connection conn = JDBCTemplate.getConnection();

        try {
            //여기서 곧바로 return이 되어도 finally는 실행됨으로 자원해제는 정상적으로 처리됩니다.
            return transactionSampleDao.selectByNameOrNull(conn, name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(conn); //꼭 connection 자원을 해제해야 합니다.
        }

        //여기까지 내려왔다는 것은 SQLException이 발생해서 위에 try문에서 return이 되지 않았다는 뜻 입니다.
        //널을 리턴해 줍니다.
        return null;
    }


    //아래 메서드 때문에 샘플을 만들었습니다.
    //특정계좌에서 다른계좌로 일정 금액만큼 송금합니다.
    //특정계좌의 잔액조회 > 특정계좌에서 잔액빼기 > 남은잔액이 0보다 큰지 확인 > 다른계좌에 잔액 더하기 이 모든 일이 하나의 트랜잭션으로 처리되어야 합니다.
    //한개의 sql문에서라도 오류가 발생하면 그전까지 진행했던 모든 일들을 롤백해야 합니다.
    //트랜잭션은 여러개의 sql문이 수행되지만 마치 하나의 구문처럼 동작해야 함을 의미하며 원자성을 가진다 라고 얘기합니다.
    //결과는 Map을 통해 처리결과, 메세지를 리턴해주도록 하겠습니다.
    public Map<String, String> SendMoney(String fromAccountName, String toAccountName, int amount) {

        Connection connection = JDBCTemplate.getConnection();
        Map<String, String> result = new HashMap<>();

        try {
            //중요!!! 트랜잭션 처리가 필요한경우 커넥션의 autoCommit을 false로 고쳐주어야 자동 커밋을 하지않습니다.
            connection.setAutoCommit(false);

            //from 계좌의 잔액을 확인합니다.
            SampleAccount fromAccount = transactionSampleDao.selectByNameOrNull(connection, fromAccountName);
            SampleAccount toAccount = transactionSampleDao.selectByNameOrNull(connection, toAccountName);
            //계좌가 없을경우 SQLException을 떨굽니다. 바로 아래 catch에서 캐치함으로 controller까지 내려가지 않고 여기서 끝냅니다.
            if (fromAccount == null || toAccount == null) {  //위에서 orNull을 통해 널이 반환될 수 있음을 알기때문에 이런 에러처리를 다른사람도 명확하게 이해하고 할 수 있습니다.

                //결과에 넣어줍니다.
                result.put("result", "fail");
                result.put("msg", "존재하지 않는 계좌입니다.");

                //던져지는 에러는 controller까지 가지않고 바로아래 catch문에서 잡힙니다.
                throw new SQLException();
            }
            if (fromAccount.getAmount() < amount) { //잔액부족도 처리해줍니다.

                result.put("result", "fail");
                result.put("msg", "잔액이 부족합니다.");

                throw new SQLException();
            }

            //잔액빼기
            transactionSampleDao.decreaseAmountByName(connection, fromAccountName, amount);

            //다시 조회해서 잔액이 0보다 큰지 확인
            //혹시 짧은 시간내에 자동이체가 된다던지 해서 잔액이 줄었을 수 있음으로 항상 반영후의 결과를 토대로 정상 처리됨을 확인합니다.
            fromAccount = transactionSampleDao.selectByNameOrNull(connection, fromAccountName);
            if (fromAccount.getAmount() < 0) { //만약 거래도중에 잔액이 줄었다면 sqlexception을 던져 catch에서 해당 거래를 롤백시켜버립니다.

                result.put("result", "fail");
                result.put("msg", "잔액이 부족합니다.");

                //어차피 롤백되며 잔액 차감전으로 돌아간다.
                throw new SQLException();
            }


            //다른계좌에 잔액을 증가시킨다.
            transactionSampleDao.increaseAmountByName(connection, toAccountName, amount);


            //여기까지 에러없이 왔다면 정상적으로 모든 sql이 처리되었음을 의미함으로 커밋을 수행해줍니다.
            //커밋이 되어야 위에서 수행했던 update문들이 전부 정상 적용됩니다.
            JDBCTemplate.commit(connection);


            result.put("result", "success");
            result.put("msg", "거래가 성공적으로 처리되었습니다.");


        } catch (SQLException e) {
            //위에 sql문을 수행하던 도중 하나의 문제라도 발생한다면 모든 처리했던 내용을 롤백시킵니다.
            //가장 중요합니다. 만약 A계좌에서 3천원을 뻈는데 B계좌에 더해주는 도중 에러가 발생한다면 A계좌의 돈만 내려가고
            //B계좌에는 돈이 올라가지 않는 현상이 발생할 수 있는데 그걸 방지하기 위해 트랜잭션 처리를 하는 것 입니다.
            JDBCTemplate.rollback(connection);

            if(result.isEmpty()) {
                result.put("result", "fail");
                result.put("msg", "서버 문제가 발생했습니다.");
                e.printStackTrace(); //여기 if문에 걸리지 않는건 예상가능 오류들임으로 구지 콘솔에 찍지 않습니다.
            }
        } finally {
            JDBCTemplate.close(connection); //꼭 자원을 해제해줍니다.
        }

        return result;
    }


    /*
     * 테스트용 DTO 클래스 입니다.
     * 테스트용이어서 내부클래스로 만들었지만 실제 구현할때는 DTO 패키지에 하나의 단독 클래스로
     * 저희 수업에서 배운대로 구현하시면 됩니다.
     * */
    public static class SampleAccount {
        private String name; // 계좌 이름
        private int amount; // 금액

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "SampleAccount{" +
                    "name='" + name + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}
