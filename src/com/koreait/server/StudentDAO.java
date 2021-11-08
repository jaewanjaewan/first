package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static void main(String[] args) {
       StudentVO param = new StudentVO();
       param.setSno(3);
       StudentVO vo = selStudent(param);
        System.out.println(vo.getSno());
        System.out.println(vo.getNm());
        System.out.println(vo.getAge());
        System.out.println(vo.getAddr());
    }

    public static DbUtils dbUtils = DbUtils.getInstance();

    //레코드 insert담당 메소드
    public static int insStudent(StudentVO vo) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null; //쿼리문 실행담당(쿼리문 완성까지)
        String sql = "INSERT INTO t_student2" + "(nm, age, addr)" + "VALUES" + "(?, ?, ?)";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            result = ps.executeUpdate(); //영향을 미친 행 수
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return result;
    }

    public static int updStudent(StudentVO vo) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null; //쿼리문 실행담당(쿼리문 완성까지)
        String sql = "UPDATE t_student2 SET nm = ?, age = ?, addr = ? WHERE sno = ?";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNm());
            ps.setInt(2, vo.getAge());
            ps.setString(3, vo.getAddr());
            ps.setInt(4, vo.getSno());
            result = ps.executeUpdate(); //영향을 미친 행 수
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return result;
    }

    public static int delStudent(StudentVO vo) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null; //쿼리문 실행담당(쿼리문 완성까지)
        String sql = "DELETE FROM t_student2 WHERE sno = ?";
        try {
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getSno());
            result = ps.executeUpdate(); //영향을 미친 행 수
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps);
        }
        return result;
    }

    public static List<StudentVO> selStudentList(){
        List<StudentVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT sno, nm FROM t_student2 ";

        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            while(rs.next()){
                StudentVO vo = new StudentVO();
                int sno = rs.getInt("sno");
                String nm = rs.getString("nm");
                vo.setSno(sno);
                vo.setNm(nm);
                list.add(vo);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps, rs);
        }

        return list;
    }

    public static StudentVO selStudent(StudentVO vo){
        StudentVO result = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM t_student2 WHERE sno = ?";
        try{
            con = dbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vo.getSno());
            rs = ps.executeQuery();

            if(rs.next()){
                result = new StudentVO();
                result.setSno(rs.getInt("sno"));
                result.setNm(rs.getString("nm"));
                result.setAge(rs.getInt("age"));
                result.setAddr(rs.getString("addr"));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dbUtils.close(con, ps, rs);
        }
        return result;
    }
}
