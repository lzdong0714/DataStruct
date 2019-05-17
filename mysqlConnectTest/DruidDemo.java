//package mysqlConnectTest;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import org.junit.jupiter.api.Test;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.util.Utils;
//
//public class DruidDemo {
//	 @Test
//	    public void druidTest(){
//	        Connection conn = null;
//	        PreparedStatement pstmt = null;
//	        ResultSet rs = null;
//	        DruidDataSource dataSource = new DruidDataSource();
//	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	        dataSource.setUrl("jdbc:mysql://localhost:3306/taotao?characterEncoding=utf-8");
//	        dataSource.setUsername("root");
//	        dataSource.setPassword("123456");
//	        try{
//	            // 获得连接:
//	            conn = dataSource.getConnection();
//	            // 编写SQL：
//	            String sql = "select * from taotao.tb_item where id=536563 ";
//	            pstmt = conn.prepareStatement(sql);
//	            // 执行sql:
//	            rs = pstmt.executeQuery();
//	            while(rs.next()){
//	                System.out.println(rs.getInt("id")+"   "+rs.getString("title"));
//	            }
//	        }catch(Exception e){
//	            e.printStackTrace();
//	        }finally{
//	        	dataSource.close();
////	        	Utils.
////	            Utils.releaseResouce(rs, pstmt, conn);
//	        }
//
//	    }
//
//
//	 @Test
//	    public void druidTest1(){
//	        Connection conn = null;
//	        PreparedStatement pstmt = null;
//	        ResultSet rs = null;
//	        DruidDataSource dataSource = new DruidDataSource();
//	        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	        dataSource.setUrl("jdbc:mysql://localhost:3306/taotao?useSSL=false&serverTimezone=UTC");
//	        dataSource.setUsername("root");
//	        dataSource.setPassword("123456");
//	        try{
//	            // 获得连接:
//	            conn = dataSource.getConnection();
//	            // 编写SQL：
//	            String sql = "select * from taotao.tb_item where id=536563 ";
//	            pstmt = conn.prepareStatement(sql);
//	            // 执行sql:
//	            rs = pstmt.executeQuery();
//	            while(rs.next()){
//	                System.out.println(rs.getInt("id")+"   "+rs.getString("title"));
//	            }
//	        }catch(Exception e){
//	            e.printStackTrace();
//	        }finally{
//	        	dataSource.close();
//	        	//Utils
////	            Utils.releaseResouce(rs, pstmt, conn);
//	        }
//
//	    }
//}
