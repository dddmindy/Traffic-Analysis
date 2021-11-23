package prefixspan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	private int oldnum;//记录上一次查询时表中的记录数
	/*
	 * 获取上一次查询时数据库中记录的数目
	 */
	public int get_oldnum()
	{
		return oldnum;
	}
	/*
	 * 设置上一次查询时表中的记录数
	 */
	public void set_oldnum(int n)
	{
		oldnum=n;
	}
	/*
	 * 获取当前数据库中记录的数目
	 */
	public int get_num()
	{
		int num=0;
		// 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/jtsg?useSSL=false";
        // MySQL配置时的用户名
        String user = "root"; 
        // MySQL配置时的密码
        String password = "";

        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("");
         // statement用来执行SQL语句
         Statement statement = conn.createStatement();
         // 要执行的SQL语句
         String sql = "select count(*) as num from jtsg";

         // 结果集
         ResultSet rs = statement.executeQuery(sql);
         
         while(rs.next()) {
        	 num=rs.getInt("num");
          
         }

         rs.close();
         conn.close();

        } catch(ClassNotFoundException e) {
        	System.out.println("Sorry,can`t find the Driver!"); 
        	e.printStackTrace();
        } catch(SQLException e) {
        	e.printStackTrace();
        } catch(Exception e) {
        	e.printStackTrace();
        } 
		return num;
		
	}
	/*
	 * 获取数据库中新增的记录
	 */
	public ArrayList<Sequence> get_seqs(int num)
	{
		// 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/jtsg?useSSL=false";
        // MySQL配置时的用户名
        String user = "root"; 
        // MySQL配置时的密码
        String password = "";
        ArrayList<Sequence> seqlist=new ArrayList<>();
        
        try { 
         // 加载驱动程序
         Class.forName(driver);

         // 连续数据库
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("");
         // statement用来执行SQL语句
         Statement statement = conn.createStatement();
         // 要执行的SQL语句
         String sql = "select * from jtsg where bh>"+String.valueOf(num);
         

         // 结果集
         ResultSet rs = statement.executeQuery(sql);
        
         while(rs.next()) {
        	 /*if(rs.getString("sgsj")!=null) System.out.print(rs.getString("sgsj"));
        	 if(rs.getString("fhsslx")!=null) System.out.print(rs.getString("fhsslx"));
        	 if(rs.getString("lbqk")!=null) System.out.print(rs.getString("lbqk"));
        	 if(rs.getString("dlxx")!=null) System.out.print(rs.getString("dlxx"));
        	 if(rs.getString("lkldlx")!=null) System.out.print(rs.getString("lkldlx"));
        	 if(rs.getString("zhdmwz")!=null) System.out.print(rs.getString("zhdmwz"));*/
        	 ArrayList<String> itemset=new ArrayList<>(); 
        	 if(rs.getString("sgsj")!=null) itemset.add(rs.getString("sgsj")); 
        	 if(rs.getString("fhsslx")!=null) itemset.add(rs.getString("fhsslx")); 
        	 if(rs.getString("lbqk")!=null) itemset.add(rs.getString("lbqk")); 
        	 if(rs.getString("dlxx")!=null) itemset.add(rs.getString("dlxx")); 
        	 if(rs.getString("lkldlx")!=null) itemset.add(rs.getString("lkldlx")); 
        	 if(rs.getString("zhdmwz")!=null) itemset.add(rs.getString("zhdmwz")); 
        	 ItemSet itemset1=new ItemSet(itemset);
        	 ArrayList<ItemSet> itemSetList=new ArrayList<ItemSet>();
        	 itemSetList.add(itemset1);
        	 Sequence seq=new Sequence();
        	 seq.setItemSetList(itemSetList);
        	 seqlist.add(seq);
          	 
         }

         rs.close();
         conn.close();

        } catch(ClassNotFoundException e) {
        	System.out.println("Sorry,can`t find the Driver!"); 
        	e.printStackTrace();
        } catch(SQLException e) {
        	e.printStackTrace();
        } catch(Exception e) {
        	e.printStackTrace();
        } 
        return seqlist;
	}
	

}