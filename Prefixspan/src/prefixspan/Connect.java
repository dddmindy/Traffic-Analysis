package prefixspan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	private int oldnum;//��¼��һ�β�ѯʱ���еļ�¼��
	/*
	 * ��ȡ��һ�β�ѯʱ���ݿ��м�¼����Ŀ
	 */
	public int get_oldnum()
	{
		return oldnum;
	}
	/*
	 * ������һ�β�ѯʱ���еļ�¼��
	 */
	public void set_oldnum(int n)
	{
		oldnum=n;
	}
	/*
	 * ��ȡ��ǰ���ݿ��м�¼����Ŀ
	 */
	public int get_num()
	{
		int num=0;
		// ����������
        String driver = "com.mysql.jdbc.Driver";
        // URLָ��Ҫ���ʵ����ݿ���scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/jtsg?useSSL=false";
        // MySQL����ʱ���û���
        String user = "root"; 
        // MySQL����ʱ������
        String password = "";

        try { 
         // ������������
         Class.forName(driver);

         // �������ݿ�
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("");
         // statement����ִ��SQL���
         Statement statement = conn.createStatement();
         // Ҫִ�е�SQL���
         String sql = "select count(*) as num from jtsg";

         // �����
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
	 * ��ȡ���ݿ��������ļ�¼
	 */
	public ArrayList<Sequence> get_seqs(int num)
	{
		// ����������
        String driver = "com.mysql.jdbc.Driver";
        // URLָ��Ҫ���ʵ����ݿ���scutcs
        String url = "jdbc:mysql://127.0.0.1:3306/jtsg?useSSL=false";
        // MySQL����ʱ���û���
        String user = "root"; 
        // MySQL����ʱ������
        String password = "";
        ArrayList<Sequence> seqlist=new ArrayList<>();
        
        try { 
         // ������������
         Class.forName(driver);

         // �������ݿ�
         Connection conn = DriverManager.getConnection(url, user, password);

         if(!conn.isClosed()) 
          System.out.println("");
         // statement����ִ��SQL���
         Statement statement = conn.createStatement();
         // Ҫִ�е�SQL���
         String sql = "select * from jtsg where bh>"+String.valueOf(num);
         

         // �����
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