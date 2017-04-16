package com.lf.helper;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

/**
* <p>Title: SVNHelper.java<／p>
* <p>Description: svn操作帮助类<／p>
* @author Liangfeng
* @date 2017-01-15
* @version 1.0
 */
public class SVNHelper {
	
	
	/**
	 * 初始化SVNHelper帮助类
	 */
	static {
		try{
			//注册svn sqlite数据库
			Class.forName("org.sqlite.JDBC");
		}catch (Exception e) {
			throw new RuntimeException("初始化SVNHelper 异常",e);
		}
	}
	
	/**
	 * SVNHelper构造私有化
	 */
	private SVNHelper(){};
	
	/**
	 * 获取svn数据库连接
	 * @param url 数据库url
	 * @return
	 */
	private static Connection getConnection(String url) {
		Connection conn = null;
		// 创建数据库连接
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			throw new RuntimeException("创建SVN数据库连接发生异常，请检查！ URL:" + url );
		}
		return conn;
	}
	
	/**
	 * 关闭svn数据库
	 * 
	 * @param connection
	 * @param statement
	 * @param resultSet
	 */
	private static void close(Connection conn, Statement stmt,ResultSet rs) {

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {

				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					throw new RuntimeException(e);
				} finally {
					conn = null;
				}

			}
		}
	}
	
	
	/**
	 * 获取指定工程的svn文件修改历史记录
	 * @param workspacePath 工作空间名称
	 * @param projectName 工程名
	 * @param revisionStart 开始版本，但不包含该版本
	 * @param revisionEnd 结束版本，包含该版本
	 * @return
	 * @throws Exception
	 */
	public static Set<String> querySvnHistoryData(String workspacePath,String projectName,Integer revisionBegin,Integer revisionEnd ) throws Exception {
		//定义
		Set<String> set = new TreeSet<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			//1.获取连接
			String svnDBUrl = "jdbc:sqlite:" + workspacePath + "/" + projectName + "/.svn/wc.db";
			conn = getConnection(svnDBUrl);
			//2.构造查询sql 根据实际需求增加或删除过滤条件
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("select local_relpath,checksum from nodes where checksum is not null ");
			sqlSb.append(" and local_relpath not like '%.jar' and local_relpath not like '%web.xml'");
			//sqlSb.append(" and local_relpath not like '%spring-servlet.xml' ");
			//sqlSb.append(" and local_relpath not like '%.properties' ");
			sqlSb.append(" and local_relpath not like '%.prefs' ");
			sqlSb.append(" and local_relpath not like '%.class' ");
			sqlSb.append(" and local_relpath not like '%.project' ");
			sqlSb.append(" and local_relpath not like '%.mymetadata' ");
			sqlSb.append(" and local_relpath not like '%MANIFEST.MF' ");
			sqlSb.append(" and local_relpath not like '%build.xml' ");
			sqlSb.append(" and local_relpath not like '%.springBeans' ");
			sqlSb.append(" and local_relpath not like '%.settings/%' ");
			sqlSb.append(" and local_relpath not like '%.classpath' ");
			sqlSb.append(" and local_relpath not like '%.mymetadata' ");
			sqlSb.append(" and local_relpath not like '%.classpath' ");
			//sqlSb.append(" and local_relpath not like '%application%.xml' ");
			sqlSb.append(" and kind = 'file'");
			if(revisionBegin !=null){
				sqlSb.append(" and changed_revision > " + revisionBegin);
			}
			if(revisionEnd !=null){
				sqlSb.append(" and changed_revision <= " + revisionEnd);
			}
			
			System.out.println("svnSql:" + sqlSb.toString());
			
			//3.查询结果集
			stmt = conn.prepareStatement(sqlSb.toString());
			rs = stmt.executeQuery();
			
			//4.组装svn文件修改历史数据集合
			while(rs.next()){
				set.add(projectName+rs.getString(1));
			}
			return set;
		}catch (Exception e) {
			throw new RuntimeException("获取svn历史记录 异常",e);
		}finally {
			close(conn, stmt, rs);
		}
	}
	
	/**
	 * 获取指定工程的svn文件修改历史记录
	 * @param workspacePath 工作空间名称
	 * @param revisionStart 开始版本，但不包含该版本
	 * @param revisionEnd 结束版本，包含该版本
	 * @return
	 * @throws Exception
	 */
	public static Set<String> querySvnHistoryData(String workspacePath,Integer revisionBegin,Integer revisionEnd) throws Exception {
		//定义
		Set<String> set = new TreeSet<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			//1.获取连接
			String svnDBUrl = "jdbc:sqlite:" + workspacePath + "/.svn/wc.db";
			conn = getConnection(svnDBUrl);
			//2.构造查询sql 根据实际需求增加或删除过滤条件
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("select local_relpath,checksum from nodes where checksum is not null ");
			//sqlSb.append(" and local_relpath not like '%.jar' and local_relpath not like '%web.xml'");
			//sqlSb.append(" and local_relpath not like '%spring-servlet.xml' ");
			//sqlSb.append(" and local_relpath not like '%.properties' ");
			sqlSb.append(" and local_relpath not like '%.prefs' ");
			sqlSb.append(" and local_relpath not like '%.class' ");
			sqlSb.append(" and local_relpath not like '%.project' ");
			sqlSb.append(" and local_relpath not like '%.mymetadata' ");
			sqlSb.append(" and local_relpath not like '%MANIFEST.MF' ");
			sqlSb.append(" and local_relpath not like '%build.xml' ");
			sqlSb.append(" and local_relpath not like '%.springBeans' ");
			sqlSb.append(" and local_relpath not like '%.settings/%' ");
			sqlSb.append(" and local_relpath not like '%.classpath' ");
			sqlSb.append(" and local_relpath not like '%.mymetadata' ");
			sqlSb.append(" and local_relpath not like '%.classpath' ");
			//sqlSb.append(" and local_relpath not like '%application%.xml' ");
			sqlSb.append(" and kind = 'file'");
			if(revisionBegin !=null){
				sqlSb.append(" and changed_revision > " + revisionBegin);
			}
			if(revisionEnd !=null){
				sqlSb.append(" and changed_revision <= " + revisionEnd);
			}
			
			System.out.println("svnSql:" + sqlSb.toString());
			
			//3.查询结果集
			stmt = conn.prepareStatement(sqlSb.toString());
			rs = stmt.executeQuery();
			
			//4.组装svn文件修改历史数据集合
			while(rs.next()){
				set.add(rs.getString(1));
			}
			return set;
		}catch (Exception e) {
			throw new RuntimeException("获取svn历史记录 异常",e);
		}finally {
			close(conn, stmt, rs);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		String workspacePath = "D:/Development/Java/JavaProject/MyEclipse10/mydis_workspace/mydis";
		//Integer revision = 1678;
		Integer revision = 1668;
		Set<String> set = new TreeSet<String>();
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_bean", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_dao", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_service_common", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_service_logic", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_web_admin", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_web_ordercenter", revision, changedDate));
		//set.addAll(SVNHelper.querySvnHistoryData(workspacePath, "wfrcb_web_appserver", revision, changedDate));
		set.addAll(SVNHelper.querySvnHistoryData(workspacePath, 11,15));
		for(String s :set){
			System.out.println(s);
		}
	}
	

}
