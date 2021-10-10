package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionSampleDao {

    //db에서 모든 계좌정보를 조회합니다.
    //이름에 OrNull이 없는것을 토대로 결과가 없을경우 빈 List가 반환됨을 유추할 수 있습니다.
    //throws SQLException 구문은 해당 메소드가 SQLException을 던진다는걸 알려주는 역할 입니다.
    //모든 트랜잭션처리는 service가 책임짐으로 sql 예외가 발생하면 dao에서 처리하지 않고 service로 이관해야 합니다.
    public List<TransactionSampleService.SampleAccount> selectAll(Connection conn) throws SQLException {
        String sql = "select * from SAMPLE_ACCOUNT"; //sql문
        List<TransactionSampleService.SampleAccount> list = new ArrayList<>(); // 빈 ArrayList 생성

        //Serivce에서 설명한 try with resources 구문입니다.
        //여러개의 변수 선언도 가능합니다.
        //try with resources 구문이 익숙해지면 편하지만 복잡하다면 원래 배우신대로 finally에서 사용한 자원을 close 해주시면 됩니다.
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {

            //배운 내용임으로 생략합니다.
            //결과가 없는경우 아래 while문이 한번도 수행되지 않음으로 최종 리턴값이 빈 ArrayList가 됩니다.
            //결과가 있다면 결과들이 DTO객체를 통해 채워져서 반환됩니다.
            while (resultSet.next()) {
                TransactionSampleService.SampleAccount account = new TransactionSampleService.SampleAccount();

                account.setAmount(resultSet.getInt("amount"));
                account.setName(resultSet.getString("name"));

                list.add(account);
            }

        } catch (SQLException e) {
            throw e; //발생한 에러를 service로 처리를 미룹니다.
        }

        return list;
    }


    //이름으로 계좌를 조회합니다.
    //이름에 OrNull이 있음으로 결과가 없는경우 Null이 리턴됨을 예상 할 수 있습니다.
    public TransactionSampleService.SampleAccount selectByNameOrNull(Connection conn, String name) throws SQLException {
        String sql = "select * from SAMPLE_ACCOUNT where NAME = ?";

        //try with resources 미사용 구문
        //해당 sql문은 물음표를 채워야 함으로 try with resources를 사용한다 하더라도 resultset에는 사용할 수 없습니다.
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        TransactionSampleService.SampleAccount account = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new TransactionSampleService.SampleAccount();
                account.setName(resultSet.getString("name"));
                account.setAmount(resultSet.getInt("amount"));
            }
        } catch (SQLException e) {
            throw e;
        }

        return account;
    }


    //해당 name의 계좌의 금액을 amount만큼 차감합니다.
    public void decreaseAmountByName(Connection conn, String name, int amount) throws SQLException {
        String sql = "update SAMPLE_ACCOUNT set AMOUNT = AMOUNT - ? where NAME = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, name);

            if(preparedStatement.executeUpdate() == 0) { //반영된 결과가 없다면 sqlexception을 service 로 던집니다.
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e; //도중에 오류가 발생할경우 exception을 서비스에게 던집니다.
        }
    }

    //해당 name의 계좌의 금액을 amount만큼 증가시킵니다.
    public void increaseAmountByName(Connection conn, String name, int amount) throws SQLException {
        String sql = "update SAMPLE_ACCOUNT set AMOUNT = AMOUNT + ? where NAME = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, name);

            if(preparedStatement.executeUpdate() == 0) { //반영된 결과가 없다면 sqlexception을 service 로 던집니다.
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e; //도중에 오류가 발생할경우 exception을 서비스에게 던집니다.
        }
    }
}
