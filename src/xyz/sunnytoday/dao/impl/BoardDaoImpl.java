package xyz.sunnytoday.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.sunnytoday.common.JDBCTemplate;
import xyz.sunnytoday.common.Paging;
import xyz.sunnytoday.dao.face.BoardDao;
import xyz.sunnytoday.dto.Board;
import xyz.sunnytoday.dto.Comments;
import xyz.sunnytoday.dto.File;
import xyz.sunnytoday.dto.Member;
import xyz.sunnytoday.dto.Post;
import xyz.sunnytoday.dto.PostFile;
import xyz.sunnytoday.dto.Report;

public class BoardDaoImpl implements BoardDao {

    @Override
    public List<Map<String, Object>> selectMainListAll(Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) MAINPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, paging.getStartNo());
            ps.setInt(2, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));

                map.put("post", post);
                map.put("board", b);

                map.put("nick", selectNickByUserno(conn, post));

                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public int selectCntAll(Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT count(*) FROM post where BOARD_NO != 0";

        //??? ????????? ???
        int count = 0;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return count;
    }

    @Override
    public int selectCntTitle(Connection conn, int boardno) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT count(*) FROM post";
        sql += "	WHERE board_no = ?";

        //??? ????????? ???
        int count = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, boardno);

            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return count;

    }

    @Override
    public List<Map<String, Object>> selectAskingListAll(Board board, Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND B.title = 'asking'";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) ASKINGPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";


        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, paging.getStartNo());
            ps.setInt(2, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));
                b.setBoard_no(rs.getInt("board_no"));

                map.put("post", post);
                map.put("board", b);

                map.put("nick", selectNickByUserno(conn, post));

                //???????????? ????????? ??????
                list.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectBuyListAll(Board board, Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND B.title = 'buy'";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) ASKINGPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";


        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, paging.getStartNo());
            ps.setInt(2, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));
                b.setBoard_no(rs.getInt("board_no"));

                map.put("post", post);
                map.put("board", b);

                map.put("nick", selectNickByUserno(conn, post));

                //???????????? ????????? ??????
                list.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectMineListAll(int userno, Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND user_no = ?";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) ASKINGPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";


        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userno);
            ps.setInt(2, paging.getStartNo());
            ps.setInt(3, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));
                b.setBoard_no(rs.getInt("board_no"));

                map.put("post", post);
                map.put("board", b);
                map.put("nick", selectNickByUserno(conn, post));

                //???????????? ????????? ??????
                list.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectShareListAll(Board board, Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND B.title = 'share'";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) ASKINGPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";


        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, paging.getStartNo());
            ps.setInt(2, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));
                b.setBoard_no(rs.getInt("board_no"));

                map.put("post", post);
                map.put("board", b);
                map.put("nick", selectNickByUserno(conn, post));

                //???????????? ????????? ??????
                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectDailyListAll(Board board, Connection conn, Paging paging) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, user_no, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND B.title = 'daily'";
        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) ASKINGPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";


        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, paging.getStartNo());
            ps.setInt(2, paging.getEndNo());

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));
                b.setBoard_no(rs.getInt("board_no"));

                map.put("post", post);
                map.put("board", b);
                map.put("nick", selectNickByUserno(conn, post));

                //???????????? ????????? ??????
                list.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public int selectNextPost_no(Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT post_seq.nextval FROM dual";

        //?????? ?????? ??????
        int nextPost_no = 0;

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                nextPost_no = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return nextPost_no;
    }

    @Override
    public int selectNextFile_no(Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT file_seq.nextval FROM dual";

        //?????? ?????? ??????
        int nextFile_no = 0;

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                nextFile_no = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return nextFile_no;
    }

    @Override
    public int changeBoardno(Connection conn, String value) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT board_no FROM board";
        sql += "	WHERE title = ?";

        //?????? ?????? ??????
        int board_no = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, value);

            rs = ps.executeQuery();

            while (rs.next()) {
                board_no = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return board_no;
    }

    @Override
    public int insert(Connection conn, Post post) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO post(post_no, board_no, user_no, title, content)";
        sql += "	VALUES (?, ?, ?, ?, ?)";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, post.getPost_no());
            ps.setInt(2, post.getBoard_no());
            ps.setInt(3, post.getUser_no());
            ps.setString(4, post.getTitle());
            ps.setString(5, post.getContent());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;

    }

    @Override
    public int insertFile(Connection conn, File file) {
        System.out.println(file);

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO \"FILE\"( file_no, url, thumbnail_url, origin_name, user_no )";
        sql += " VALUES( ?, ?, ?, ?, ? )";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, file.getFile_no());
            ps.setString(2, file.getUrl());
            ps.setString(3, file.getThumbnail_url());
            ps.setString(4, file.getOrigin_name());
            ps.setInt(5, file.getUser_no());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;

    }

    @Override
    public int insertFileInfo(Connection conn, PostFile postFile) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO post_file( post_no, file_no )";
        sql += " VALUES( ?, ? )";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, postFile.getPost_no());
            ps.setInt(2, postFile.getFile_no());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public int updateHit(Connection conn, Post post_no) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "UPDATE post";
        sql += " SET hit = hit + 1";
        sql += " WHERE post_no = ?";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql); //SQL?????? ??????
            ps.setInt(1, post_no.getPost_no()); //????????? ????????? ?????? ??????
            res = ps.executeUpdate(); //SQL ??????

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public Post selectPostByPostno(Connection conn, Post post_no) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM post";
        sql += " WHERE post_no = ?";

        Post detailPost = null;

        try {
            ps = conn.prepareStatement(sql); //SQL?????? ??????
            ps.setInt(1, post_no.getPost_no()); //????????? ????????? ?????? ??????
            rs = ps.executeQuery(); //SQL ?????? ??? ???????????? ??????

            //?????? ?????? ??????
            while (rs.next()) {
                detailPost = new Post(); //????????? ?????? ??????

                //????????? ??? ??? ??????
                detailPost.setPost_no(rs.getInt("post_no"));
                detailPost.setBoard_no(rs.getInt("board_no"));
                detailPost.setUser_no(rs.getInt("user_no"));
                detailPost.setWrite_date(rs.getDate("write_date"));
                detailPost.setLast_modify(rs.getDate("last_modify"));
                detailPost.setTitle(rs.getString("title"));
                detailPost.setContent(rs.getString("content"));
                detailPost.setHit(rs.getInt("hit"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        //?????? ?????? ??????
        return detailPost;
    }

    @Override
    public String selectNickByUserno(Connection conn, Post detailBoard) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT nick FROM member";
        sql += " WHERE user_no = ?";

        //?????? ????????? String ??????
        String usernick = null;

        try {
            ps = conn.prepareStatement(sql); //SQL?????? ??????
            ps.setInt(1, detailBoard.getUser_no()); //????????? no ??????

            rs = ps.executeQuery(); //SQL ?????? ??? ???????????? ??????

            //?????? ?????? ??????
            while (rs.next()) {
                usernick = rs.getString("nick");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //DB?????? ??????
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        //?????? ?????? ??????
        return usernick;
    }

    @Override
    public String selectNickByUserno(Connection conn, Comments comments) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT nick FROM member";
        sql += " WHERE user_no = ?";

        //?????? ????????? String ??????
        String usernick = null;

        try {
            ps = conn.prepareStatement(sql); //SQL?????? ??????
            ps.setInt(1, comments.getUser_no()); //????????? no ??????

            rs = ps.executeQuery(); //SQL ?????? ??? ???????????? ??????

            //?????? ?????? ??????
            while (rs.next()) {
                usernick = rs.getString("nick");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //DB?????? ??????
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        //?????? ?????? ??????
        return usernick;
    }


    @Override
    public File selectFile(Connection conn, int fileno) {

        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "";
        sql += "SELECT * FROM \"FILE\"";
        sql += " WHERE file_no = ?";

        File file = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, fileno);

            rs = ps.executeQuery();

            while (rs.next()) {
                file = new File();

                file.setFile_no(rs.getInt("file_no"));
                file.setUrl(rs.getString("url"));
                file.setThumbnail_url(rs.getString("thumbnail_url"));
                file.setOrigin_name(rs.getString("origin_name"));
                file.setUser_no(rs.getInt("user_no"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return file;

    }

    @Override
    public int changeFileno(Connection conn, Post post_no) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT file_no FROM post_file";
        sql += "	WHERE post_no = ?";
        sql += "	ORDER BY file_no";

        int fileno = -1;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, post_no.getPost_no());

            rs = ps.executeQuery();

            while (rs.next()) {

                fileno = rs.getInt("file_no");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return fileno;

    }

    @Override
    public int update(Connection conn, Post post) {

        String sql = "";
        sql += "UPDATE post";
        sql += " SET title = ?,";
        sql += " 	content = ?";
        sql += " WHERE post_no = ?";

        //DB ??????
        PreparedStatement ps = null;

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getPost_no());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }


    @Override
    public int delete(Connection conn, Post post) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "DELETE post";
        sql += " WHERE post_no = ?";

        int res = -1;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, post.getPost_no());

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public int deleteFile(Connection conn, int file_no) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "DELETE \"FILE\"";
        sql += " WHERE file_no = ?";

        int res = -1;

        try {
            //DB??????
            ps = conn.prepareStatement(sql);
            ps.setInt(1, file_no);

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public PostFile selectFileByPostno(Connection conn, Post post_no) {

        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "";
        sql += "SELECT post_no, file_no FROM post_file";
        sql += " WHERE post_no = ?";

        PostFile postFile = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, post_no.getPost_no());

            rs = ps.executeQuery();

            while (rs.next()) {
                postFile = new PostFile();

                postFile.setPost_no(rs.getInt("post_no"));
                postFile.setFile_no(rs.getInt("file_no"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return postFile;

    }

    @Override
    public File selectThum(Connection conn, Post post) {

        PreparedStatement ps = null;
        ResultSet rs = null;


        //inner join post + post_file + file
        String sql = "";
        sql += "select f.* from (select post_no from post where post_no = ? ) p ";
        sql += "	inner join post_file pf ";
        sql += "	on p.post_no = pf.post_no ";
        sql += "	inner join \"FILE\" f on pf.file_no = f.file_no";

        File file = null;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, post.getPost_no());

            rs = ps.executeQuery();

            if (rs.next()) {
                file = new File();

                file.setFile_no(rs.getInt("file_no"));
                file.setUrl(rs.getString("url"));
                file.setThumbnail_url(rs.getString("thumbnail_url"));
                file.setOrigin_name(rs.getString("origin_name"));
                file.setUser_no(rs.getInt("user_no"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return file;

    }

    @Override
    public List<Map<String, Object>> selectSearchMainList(Connection conn, Paging paging, String select, String keyword) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, P.user_no, nick, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B, Member M";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND p.user_no = M.user_no";

        if ("title".equals(select)) {
            sql += "		AND p.title LIKE ?";
        } else if ("content".equals(select)) {
            sql += "		AND content LIKE ?";
        } else if ("nick".equals(select)) {
            sql += "		AND nick LIKE ?";
        }

        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) SERACHPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setInt(2, paging.getStartNo());
            ps.setInt(3, paging.getEndNo());


            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));

                map.put("post", post);
                map.put("board", b);

                map.put("nick", selectNickByUserno(conn, post));

                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }

    @Override
    public List<Map<String, Object>> selectSearchList(Connection conn, Paging paging, String boardTitle, String select, String keyword) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT * FROM (";
        sql += " SELECT rownum rnum, POST.* FROM ( ";
        sql += "        SELECT post_no, P.board_no, P.user_no, nick, write_date, last_modify, P.title ptitle, B.title btitle, content, hit";
        sql += "        FROM post P, board B, Member M";
        sql += "		WHERE P.board_no = B.board_no";
        sql += "		AND p.user_no = M.user_no";
        sql += "		AND B.title = ?";

        if ("title".equals(select)) {
            sql += "		AND p.title LIKE ?";
        } else if ("content".equals(select)) {
            sql += "		AND content LIKE ?";
        } else if ("nick".equals(select)) {
            sql += "		AND nick LIKE ?";
        }

        sql += "        ORDER BY post_no DESC";
        sql += "	   ) POST";
        sql += "	) SERACHPOST";
        sql += "	WHERE rnum BETWEEN ? AND ?";

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, boardTitle);
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, paging.getStartNo());
            ps.setInt(4, paging.getEndNo());


            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                Post post = new Post();
                Board b = new Board();

                post.setPost_no(rs.getInt("post_no"));
                post.setBoard_no(rs.getInt("board_no"));
                post.setUser_no(rs.getInt("user_no"));
                post.setWrite_date(rs.getDate("write_date"));
                post.setLast_modify(rs.getDate("last_modify"));
                post.setTitle(rs.getString("ptitle"));
                post.setContent(rs.getString("content"));
                post.setHit(rs.getInt("hit"));
                b.setTitle(rs.getString("btitle"));

                map.put("post", post);
                map.put("board", b);

                map.put("nick", selectNickByUserno(conn, post));

                list.add(map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return list;
    }


    @Override
    public List<Map<String, Object>> selectCommentPost(Connection conn, Post post_no) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "";
        sql += "SELECT comments_no, post_no, user_no, write_date, last_modify, content, \"SHOW\"";
        sql += "	FROM comments";
        sql += "	WHERE post_no = ?";
        sql += "	ORDER BY comments_no";

        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();


        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, post_no.getPost_no());

            rs = ps.executeQuery();


            while (rs.next()) {

                map = new HashMap<>();

                Comments comments = new Comments();
                Member member = new Member();

                comments.setComments_no(rs.getInt("comments_no"));
                comments.setPost_no(rs.getInt("post_no"));
                comments.setUser_no(rs.getInt("user_no"));
                comments.setWrite_date(rs.getDate("write_date"));
                comments.setLast_modify(rs.getDate("last_modify"));
                comments.setContent(rs.getString("content"));
                comments.setShow(rs.getString("show"));

                map.put("comments", comments);
                map.put("member", selectNickByUserno(conn, comments));

                list.add(map);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }


        return list;
    }

    @Override
    public int insertComment(Connection conn, Post post_no, String content, int userno, String onlyWriter) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO comments( comments_no, post_no, user_no, content";
        if (onlyWriter == null) {
            sql += ")";
        } else {
            sql += ", \"SHOW\") ";
        }
        sql += " VALUES( comments_seq.nextval, ?, ?, ?";

        if (onlyWriter == null) {
            sql += ")";
        } else {
            sql += ", ?) ";
        }

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, post_no.getPost_no());
            ps.setInt(2, userno);
            ps.setString(3, content);
            if (onlyWriter != null) {
                ps.setString(4, "Y");
            }

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;

    }

    @Override
    public List<Map<String, Object>> selectDetail(Connection conn, Post param, Comments param2) {
        System.out.println("selectBoardDetail called");
        System.out.println("param : " + param.getPost_no());
        System.out.println("param2 : " + param2.getComments_no());

        String sql = "";
        sql += "SELECT m.id, p.title, p.post_no, m.user_no, nick";

        if (param2.getComments_no() > 0) {
            sql += " , c.comments_no";
        }
        sql += " FROM member m, post p";

        System.out.println("param2.getComments_no() = " + param2.getComments_no());
        System.out.println("param2 = " + param2);

        if (param2.getComments_no() > 0) {
            sql += " , comments c";
        }
        sql += " WHERE m.user_no = p.user_no";

        if (param2.getComments_no() > 0) {
            sql += " AND p.post_no = c.post_no ";
            sql += " AND comments_no = ?";
        }
        sql += " AND p.post_no = ?";

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            ps = conn.prepareStatement(sql);
            if (param2.getComments_no() > 0) {
                ps.setInt(1, param2.getComments_no());
                ps.setInt(2, param.getPost_no());
            } else {
                ps.setInt(1, param.getPost_no());
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();
                Post post = new Post();
                Member member = new Member();

                post.setPost_no(rs.getInt("post_no"));
                post.setTitle(rs.getString("title"));
                member.setUserid(rs.getString("id"));
                member.setUserno(rs.getInt("user_no"));
                member.setNick(rs.getString("nick"));

                if (param2.getComments_no() > 0) {
                    Comments comments = new Comments();
                    comments.setComments_no(rs.getInt("comments_no"));
                    map.put("c", comments);
                }
                map.put("p", post);
                map.put("m", member);


                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }
        return list;
    }

    @Override
    public int updateComments(Connection conn, int commentNo, String content, int userno) {

        String sql = "";
        sql += "UPDATE comments";
        sql += " SET content = ?";
        sql += " WHERE comments_no = ?";
        sql += " AND user_no = ?";

        //DB ??????
        PreparedStatement ps = null;

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1, content);
            ps.setInt(2, commentNo);
            ps.setInt(3, userno);

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCTemplate.close(ps);
        }

        return res;

    }

    @Override
    public int selectPostnoByCommentsNO(Connection conn, int commentNo) {

        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "";
        sql += "SELECT post_no FROM comments";
        sql += " WHERE comments_no = ?";

        int postno = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, commentNo);

            rs = ps.executeQuery();

            while (rs.next()) {
                postno = rs.getInt(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(rs);
            JDBCTemplate.close(ps);
        }

        return postno;
    }

    @Override
    public int deleteComments(Connection conn, int commentNo, int userno) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "DELETE comments";
        sql += " WHERE comments_no = ?";
        sql += " AND user_no = ?";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, commentNo);
            ps.setInt(2, userno);

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public int insertLikeDefault(Connection conn, int userno, int postno) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO \"LIKE\"( post_no, user_no, score )";
        sql += " VALUES( ?, ?, ? )";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, postno);
            ps.setInt(2, userno);
            ps.setInt(3, 0);

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    @Override
    public int likeSum(Connection conn, int postno, int sum) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "UPDATE \"LIKE\"";
        sql += " SET score = score";
        if (sum > 0) {
            sql += " +1";
        } else if (sum < 0) {
            sql += " -1";
        }
        sql += " WHERE post_no = ?";

        int res = 0;

        try {
            ps = conn.prepareStatement(sql); //SQL?????? ??????
            ps.setInt(1, postno); //????????? ????????? ?????? ??????
            res = ps.executeUpdate(); //SQL ??????

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }

    public int insertReport(Connection conn, Report param) {

        PreparedStatement ps = null;

        String sql = "";
        sql += "INSERT INTO user_report (report_no, report_c_no, user_no,";
        sql += " target_no, detail, report_type";
        if (param.getComments_no() > 0) {
            sql += " , comments_no, post_no)";
        } else {
            sql += " , post_no)";
        }
        sql += " VALUES (USER_REPORT_SEQ.nextval, ?, ?, ?, ?, ?";
        if (param.getComments_no() > 0) {
            sql += "	, ?, ? )";
        } else {
            sql += "	,? )";
        }

        int res = 0;

        try {
            ps = conn.prepareStatement(sql);

            ps.setInt(1, param.getReport_c_no());
            ps.setInt(2, param.getUser_no());
            ps.setInt(3, param.getTarget_no());
            ps.setString(4, param.getDetail());
            ps.setString(5, param.getReport_type());

            if (param.getComments_no() > 0) {
                ps.setInt(6, param.getComments_no());
                ps.setInt(7, param.getPost_no());
            } else {
                ps.setInt(6, param.getPost_no());
            }

            res = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(ps);
        }

        return res;
    }


    @Override
    public List<Post> selectBestPosts(Connection connection) {
        String sql = "select best_post.*, comments_num.comments_cnt, posts_file.THUMBNAIL_URL " +
                "from (select b.* " +
                "      from (select ROWNUM rnum, POST_NO, TITLE, HIT from (select * from POST where BOARD_NO != 0 and BOARD_NO != 1 order by hit desc) p) b " +
                "      where rnum < 7 " +
                "      order by rnum) best_post " +
                "         left outer join " +
                "         (select POST_NO, count(*) comments_cnt from COMMENTS group by POST_NO) comments_num " +
                "         on best_post.POST_NO = comments_num.POST_NO " +
                "         left outer join " +
                "     (select distinct (POST_NO) POST_NO, f.THUMBNAIL_URL " +
                "      from POST_FILE " +
                "               inner join (select FILE_NO, THUMBNAIL_URL from \"FILE\" order by FILE_NO desc) f " +
                "                          on POST_FILE.FILE_NO = f.FILE_NO) posts_file " +
                "     on best_post.POST_NO = posts_file.POST_NO";

        List<Post> list = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Post post = new Post();
                post.setPost_no(resultSet.getInt("post_no"));
                post.setHit(resultSet.getInt("hit"));
                post.setTitle(resultSet.getString("title"));
                post.setCommentsNum(resultSet.getInt("comments_cnt"));
                post.setThumbNail(resultSet.getString("thumbnail_url"));

                list.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Map<Integer, List<Post>> selectNotices(Connection connection) {
        String sql = "select ROWNUM rnum, POST_NO, TITLE, WRITE_DATE, BOARD_NO " +
                "from (select * from POST where BOARD_NO = ? order by POST_NO desc) p " +
                "where ROWNUM < 4 " +
                "union " +
                "select ROWNUM rnum, POST_NO, TITLE, WRITE_DATE, BOARD_NO " +
                "from (select * from POST where BOARD_NO = ? order by POST_NO desc) p " +
                "where ROWNUM < 4";

        Map<Integer, List<Post>> map = new HashMap<>();
        List<Post> notices = new ArrayList<>();
        List<Post> events = new ArrayList<>();

        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, Board.TYPE_NOTICE);
            preparedStatement.setInt(2, Board.TYPE_EVENT);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post();
                post.setPost_no(resultSet.getInt("post_no"));
                post.setBoard_no(resultSet.getInt("board_no"));
                post.setTitle(resultSet.getString("title"));
                post.setWrite_date(new Date(resultSet.getTimestamp("write_date").getTime()));

                if (post.getBoard_no() == Board.TYPE_NOTICE) {
                    notices.add(post);
                } else {
                    events.add(post);
                }
            }

            map.put(Board.TYPE_NOTICE, notices.isEmpty() ? null : notices);
            map.put(Board.TYPE_EVENT, events.isEmpty() ? null : events);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(resultSet);
        }

        return map;
    }
}
