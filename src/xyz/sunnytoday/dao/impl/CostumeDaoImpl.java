package xyz.sunnytoday.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.dao.face.CostumeDao;
import xyz.sunnytoday.dto.Costume;
import xyz.sunnytoday.dto.File;

public class CostumeDaoImpl implements CostumeDao {

    @Override
    public List<Costume> selectCostumeList(Connection conn, int temperatures) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT costume_no, min_temperatures, max_temperatures, type, gender, file_no, TITLE";
        sql += " FROM costume";
        sql += " WHERE min_temperatures <= ? and max_temperatures >= ?";

        //결과 저장할 List
        List<Costume> costumeList = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql); //SQL수행 객체
            ps.setInt(1, temperatures);
            ps.setInt(2, temperatures);

            rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

            //조회 결과 처리
            while (rs.next()) {
                Costume c = new Costume(); //결과값 저장 객체

                //결과값 한 행 처리
                c.setCostume_no(rs.getInt("costume_no"));
                c.setMin_temperatures(rs.getInt("min_temperatures"));
                c.setMax_temperatures(rs.getInt("max_temperatures"));
                c.setType(rs.getString("type"));
                c.setGender(rs.getString("gender"));
                c.setFile_no(rs.getInt("file_no"));
                c.setTitle(rs.getString("title"));

                //리스트에 결과값 저장
                costumeList.add(c);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //DB객체 닫기
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        //최종 결과 반환
        return costumeList;

    }

    @Override
    public File selectCostume_File(Connection conn, int file_no) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";

        sql += "SELECT file_no, url, thumbnail_url, origin_name, user_no";
        sql += " FROM \"FILE\"";
        sql += " WHERE file_no = ?";

        //결과 저장할 List
        File resultFile = new File();

        try {
            ps = conn.prepareStatement(sql); //SQL수행 객체
            ps.setInt(1, file_no);

            rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

            //조회 결과 처리
            while (rs.next()) {

                //결과값 한 행 처리
                resultFile.setFile_no(rs.getInt("file_no"));
                resultFile.setUrl(rs.getString("url"));
                resultFile.setThumbnail_url(rs.getString("thumbnail_url"));
                resultFile.setOrigin_name(rs.getString("origin_name"));
                resultFile.setUser_no(rs.getInt("user_no"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //DB객체 닫기
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        //최종 결과 반환
        return resultFile;

    }


    @Override
    public List<Costume> selectCostumesIf(Connection connection, int temp, String type, String gender) {
        String sql = "select c.TITLE, \"FILE\".THUMBNAIL_URL from (select TITLE, FILE_NO from COSTUME where MAX_TEMPERATURES >= ? and MIN_TEMPERATURES <= ? and TYPE = ? and (GENDER = 'A' or GENDER = ?)) c inner join \"FILE\" on \"FILE\".FILE_NO = c.FILE_NO";

        List<Costume> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, temp);
            preparedStatement.setInt(2, temp);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, gender);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Costume costume = new Costume();
                costume.setThumbNail(resultSet.getString("thumbnail_url"));
                String title = resultSet.getString("title");
                costume.setTitle(title == null ? "이름 없음" : title);

                list.add(costume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(resultSet);
        }

        return list;
    }
}
