package org.zerock.jdbcex.dao;

import lombok.Cleanup;
import org.zerock.jdbcex.domain.TodoVO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public String getTime() throws SQLException{
        String now = null;
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();
        rs.next();
        now = rs.getString(1);
        return now;
    }



    public void insert(TodoVO vo)throws Exception{
        String sql = "insert into tbl_todo(title, dueDate, finished) values(?,?,?)";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());
        pstmt.executeUpdate();
    }

    public List<TodoVO> selectAll() throws Exception{
        String sql = "select * from tbl_todo";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();
        while(rs.next()){
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    public TodoVO selectOne(Long tno) throws Exception{
        String sql = "select * from tbl_todo where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, tno);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        TodoVO vo = null;
        if(rs.next()){
            vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
        }else{
            System.out.println("tno = " + tno +"에 해당하는 데이터가 테이블에 존재하지 않습니다.");
            return null;
        }
        return vo;
    }

    public boolean delete(Long tno) throws Exception {
        String sql = "delete from tbl_todo where tno = ?";
        // Lombok의 @Cleanup을 사용하여 자동으로 리소스를 해제하도록 함
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, tno);
        int affectedRows = pstmt.executeUpdate();

        // affectedRows가 0보다 크면 true (성공), 그렇지 않으면 false (실패) 반환
        return affectedRows > 0;
    }

    public boolean update(TodoVO vo) throws Exception{
        String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());
        pstmt.setLong(4, vo.getTno());
        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
    }
}
