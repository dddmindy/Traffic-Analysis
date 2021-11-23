package prefixspan;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;



public class MyFrame {
	private JTextArea ta;
	private JTextArea ta1;
	private JTextArea ta2;
	private JTextArea ta3;
	/*
	 * ������һ������
	 */
	public void create_frame()
	{
		 JFrame f=new JFrame("��������ģʽ�ĵ�·��ͨ�¹�Ԥ��ϵͳ");
	     f.setLocation(100,100);//����ʾ���ϱ�Ե100���أ�����ʾ�����Ե100����
	     f.setSize(300,100);            //���ô���Ĵ�СΪ200*100���ش�С
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////�û��������ڵĹرհ�ťʱ����ִ�еĲ���
	     JPanel panel = new JPanel(); 
	     panel.setSize(200,100);
	     panel.setLayout(null);
	     f.add(panel);
	     JButton b1=new JButton("mining");
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                method1();
	            }
	        });
	     b1.setBounds(0, 0, 90,70);
	     panel.add(b1);
	     JButton b2=new JButton("predict");
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                method2();
	            }
	        });
	     b2.setBounds(90, 0, 90,70);
	     panel.add(b2);
	     JButton b3=new JButton("introduction");
	     b3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                method3();
	            }
	        });
	     b3.setBounds(180, 0, 110,70);
	     panel.add(b3);
	     f.setVisible(true);   //���ô���ɼ�
		
	}
	/*
	 * mining���ܽ���
	 */
	private void method1(){ 
		 JFrame f=new JFrame("mining");
	     f.setLocation(200,200);//����ʾ���ϱ�Ե100���أ�����ʾ�����Ե100����
	     f.setSize(550,300);            //���ô���Ĵ�СΪ500*400���ش�С
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////�û��������ڵĹرհ�ťʱ����ִ�еĲ���
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,300);
	     panel.setLayout(null);
	     f.add(panel);
	     JLabel jl=new JLabel("��С֧�ֶ���ֵ����:");
	     jl.setBounds(10, 10, 130, 30);
	     panel.add(jl);
	     JTextField jf=new JTextField("0");//�ı�������������С��ֵ����
	     jf.setBounds(130, 10, 50, 30);
	     panel.add(jf);
	     JRadioButton rb = new JRadioButton("����ʾ���¹ʽ��������");  
	     rb.setBounds(200, 10, 180, 30);  
	     rb.setSelected(true);  
	     panel.add(rb);
	     JRadioButton rb1 = new JRadioButton("��������");  
	     rb1.setBounds(380, 10, 80, 30);  
	     rb1.setSelected(true);  
	     panel.add(rb1);
	     JButton b1=new JButton("go");//��ť�������ִ��
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	double mins=Double.parseDouble(jf.getText());
	            	boolean select=rb.isSelected();
	            	boolean select1=rb1.isSelected();
	                mining(mins,select,select1);
	            }
	        });
	     b1.setBounds(475, 10, 50, 30);
	     panel.add(b1);
	     ta=new JTextArea();//�ı�����������ʾ�ھ��Ľ��
	     JScrollPane jsp=new JScrollPane(ta);//�ı�����ӹ�����
	     jsp.setBounds(10, 50, 515, 200);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //���ô���ɼ�
	
		
	}
	/*
	 * predict���ܽ���
	 */
	private void method2(){ 
		 JFrame f=new JFrame("predict");
	     f.setLocation(200,200);//����ʾ���ϱ�Ե100���أ�����ʾ�����Ե100����
	     f.setSize(550,380);            //���ô���Ĵ�СΪ500*400���ش�С
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////�û��������ڵĹرհ�ťʱ����ִ�еĲ���
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,380);
	     panel.setLayout(null);
	     f.add(panel);
	     JLabel jl1=new JLabel("�¹�ʱ��:");
	     jl1.setBounds(10, 20, 100, 20);
	     panel.add(jl1);
	     JTextField jf1=new JTextField("");
	     jf1.setBounds(100, 20, 50, 20);
	     panel.add(jf1);
	     JLabel jl2=new JLabel("������ʩ����:");
	     jl2.setBounds(10, 60, 100, 20);
	     panel.add(jl2);
	     JTextField jf2=new JTextField("");
	     jf2.setBounds(100, 60, 50, 20);
	     panel.add(jf2);
	     JLabel jl3=new JLabel("·�����:");
	     jl3.setBounds(10, 100, 100, 20);
	     panel.add(jl3);
	     JTextField jf3=new JTextField("");
	     jf3.setBounds(100, 100, 50, 20);
	     panel.add(jf3);
	     JLabel jl4=new JLabel("��·����:");
	     jl4.setBounds(10, 140, 100, 20);
	     panel.add(jl4);
	     JTextField jf4=new JTextField("");
	     jf4.setBounds(100, 140, 50, 20);
	     panel.add(jf4);
	     JLabel jl5=new JLabel("·��·������:");
	     jl5.setBounds(10, 180, 100, 20);
	     panel.add(jl5);
	     JTextField jf5=new JTextField("");
	     jf5.setBounds(100, 180, 50, 20);
	     panel.add(jf5);
	     JLabel jl6=new JLabel("�����λ��:");
	     jl6.setBounds(10, 220, 100, 20);
	     panel.add(jl6);
	     JTextField jf6=new JTextField("");
	     jf6.setBounds(100, 220, 50, 20);
	     panel.add(jf6);
	     
	     
	     JButton b1=new JButton("go");//��ť�������ִ��
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ArrayList<String> items1=new ArrayList<>();  
	            	if(!jf1.getText().isEmpty()) items1.add(jf1.getText());
	            	if(!jf2.getText().isEmpty()) items1.add(jf2.getText());
	            	if(!jf3.getText().isEmpty()) items1.add(jf3.getText());
	            	if(!jf4.getText().isEmpty()) items1.add(jf4.getText());
	            	if(!jf5.getText().isEmpty()) items1.add(jf5.getText());
	            	if(!jf6.getText().isEmpty()) items1.add(jf6.getText());
	            	
	            	predict(items1);
	            	
	            }
	        });
	     b1.setBounds(10, 255, 135, 30);
	     panel.add(b1);
	     
	     JButton b2=new JButton("Ԥ��ģʽ");//��ť�������ִ��
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	method_warn();
	            }
	        });
	     b2.setBounds(10, 295, 135, 30);
	     panel.add(b2);
	     ta1=new JTextArea();//�ı�����������ʾ�ھ��Ľ��
	     JScrollPane jsp=new JScrollPane(ta1);//�ı�����ӹ�����
	     jsp.setBounds(160, 20, 515, 310);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //���ô���ɼ�
	
		
	}
	/*
	 * introduction���ܽ���
	 */
	private void method3(){ 
		 JFrame f=new JFrame("introduction");
	     f.setLocation(200,200);
	     f.setSize(550,250);           
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,250);
	     panel.setLayout(null);
	     f.add(panel);
	     JButton b1=new JButton("mining˵��");//��ť�������ִ��
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_m();
	            	
	            }
	        });
	     b1.setBounds(10, 20, 135, 30);
	     panel.add(b1);
	     JButton b2=new JButton("predict˵��");//��ť�������ִ��
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_p();
	            	
	            }
	        });
	     b2.setBounds(10, 60, 135, 30);
	     panel.add(b2);
	     JButton b3=new JButton("�����");//��ť�������ִ��
	     b3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_t();
	            	
	            }
	        });
	     b3.setBounds(10, 100, 135, 30);
	     panel.add(b3);
	     JButton b4=new JButton("Ƶ����P");//��ť�������ִ��
	     b4.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_P();
	            	
	            }
	        });
	     b4.setBounds(10, 140, 135, 30);
	     panel.add(b4);
	     JButton b5=new JButton("Ȩ�ر�");//��ť�������ִ��
	     b5.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_w();
	            	
	            }
	        });
	     b5.setBounds(10, 180, 135, 30);
	     panel.add(b5);
	     
	     
	     ta2=new JTextArea();//�ı�����������ʾ�ھ��Ľ��
	     JScrollPane jsp=new JScrollPane(ta2);//�ı�����ӹ�����
	     jsp.setBounds(160, 20, 360, 190);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //���ô���ɼ�
	
		
	}
	/*
	 * warn���ܽ��棬���Ԥ��ģʽ����
	 */
	private void method_warn()
	{
		JFrame f=new JFrame("warn");
	     f.setLocation(750,200);
	     f.setSize(300,380);           
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,250);
	     panel.setLayout(null);
	     f.add(panel);
	     JLabel jl=new JLabel("֧�ֶ���ֵ����:");
	     jl.setBounds(10, 10, 100, 20);
	     panel.add(jl);
	     JTextField jf=new JTextField("0");//�ı�������������С��ֵ����
	     jf.setBounds(110, 10, 30, 20);
	     panel.add(jf);
	     JLabel jl1=new JLabel("Ƶ������ֵ:");
	     jl1.setBounds(150, 10, 100, 20);
	     panel.add(jl1);
	     JTextField jf1=new JTextField("0");//�ı�������������С��ֵ����
	     jf1.setBounds(250, 10, 30, 20);
	     panel.add(jf1);
	     Connect conn=new Connect();
	     Timer timer = new Timer();
	     JButton b1=new JButton("��ʼ");//��ť�������ִ��
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	double mins=Double.parseDouble(jf.getText());
	            	double p_mins=Double.parseDouble(jf1.getText());
	            	String filePath = "F:\\jtsg1.txt";  
	    	        //��С֧�ֶ���ֵ��  
	    	        
	    		   PrefixSpanTool tool = new PrefixSpanTool(filePath, mins);  
	    		   tool.prefixSpanCalculate();
	            	ta3.append("-----------------------------��ʼ----------------------------\n");
	            	conn.set_oldnum(conn.get_num());
	            	timer.schedule(new TimerTask() {
	        	       public void run() {
	        	    	   ArrayList<Sequence> seqlist=new ArrayList<Sequence>();
	        	    	   seqlist=conn.get_seqs(conn.get_oldnum());
	        	    	   for(Sequence seq:seqlist)
	        	    	   {
	        	    		   ArrayList<ItemSet> itemSetList=seq.getItemSetList();
	        	    		   for(ItemSet itemSet:itemSetList)
	        	    		   {
	        	    			   ArrayList<String> items=itemSet.getItems();
	        	    			   ta3.append("���У�");
	        	    			   for(String str:items)
	        	    			   {
	        	    				   ta3.append(decoding(str)+",");
	        	    			   }
	        	    			   ta3.append("   Ԥ�⣺");
	        	    		   }
	        	    		   if(!is_safe(seq,"x",tool.get_pMap(),p_mins)||!is_safe(seq,"y",tool.get_pMap(),p_mins)||!is_safe(seq,"z",tool.get_pMap(),p_mins))
	        	    		   {
	        	    			   
	        	    			   ta3.append("Σ��,");
	        	    			   if(!is_safe(seq,"x",tool.get_pMap(),p_mins))
	        	    				   ta3.append("���ܷ���"+decoding("x")+"�¹ʡ�");
	        	    			   if(!is_safe(seq,"y",tool.get_pMap(),p_mins))
	        	    				   ta3.append("���ܷ���"+decoding("y")+"�¹ʡ�");
	        	    			   if(!is_safe(seq,"z",tool.get_pMap(),p_mins))
	        	    				   ta3.append("���ܷ���"+decoding("z")+"�¹ʡ�");
	        	    		   }
	        	    		   else
	        	    			   ta3.append("��ȫ");
	        	    		   ta3.append("\n");
	        	    	   }
	        	    	   conn.set_oldnum(conn.get_num());
	        	    	   
	        	       }
	        	     }, 1000, 10000);
	            	
	            }
	        });
	     b1.setBounds(10, 40, 130, 20);
	     panel.add(b1);
	     JButton b2=new JButton("����");//��ť�������ִ��
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	timer.cancel();
	            	 ta3.append("-----------------------------����----------------------------\n");
	            	
	            }
	        });
	     b2.setBounds(150, 40, 130, 20);
	     panel.add(b2);
	     ta3=new JTextArea();//�ı�����������ʾ�ھ��Ľ��
	     JScrollPane jsp=new JScrollPane(ta3);//�ı�����ӹ�����
	     jsp.setBounds(10, 65, 265, 270);
	     panel.add(jsp);
	     f.setVisible(true);   
	}
	
	/*
	 * �жϸ������Ƿ�����ﵽԤ��ģʽ�ľ���ֵ
	 */
	private boolean is_safe(Sequence seq,String x,HashMap<Sequence,Float> map,double p_mins)
	{
		boolean is_safe=true;
		ArrayList<String> tempitems=new ArrayList<>();
		tempitems.add(x);
		ItemSet itemSet=new ItemSet(tempitems);
		Sequence xseq=seq.copySequence();
		xseq.getItemSetList().add(itemSet);
		for(Map.Entry entry : map.entrySet())
		{
			Sequence tempseq=(Sequence) entry.getKey();
			
			float tempcount=(Float) entry.getValue();
			if(tempseq.is_same(xseq)&&tempcount>p_mins)
			{
				
				is_safe=false;
				
			}
		}
		return is_safe;
		
	}
	/*
	 * ִ��Ԥ�⹦��
	 */
	private void predict(ArrayList<String> items1)
	{
		String filePath = "F:\\jtsg1.txt";  
        //��С֧�ֶ���ֵ��  
          
		PrefixSpanTool tool = new PrefixSpanTool(filePath, 0);  
		tool.prefixSpanCalculate();
		predict_x(items1,tool,"t","x");
		predict_x(items1,tool,"t","y");
		predict_x(items1,tool,"t","z");
		predict_x(items1,tool,"u","x");
		predict_x(items1,tool,"u","y");
		predict_x(items1,tool,"u","z");
		predict_x(items1,tool,"v","x");
		predict_x(items1,tool,"v","y");
		predict_x(items1,tool,"v","z");
		predict_x(items1,tool,"w","x");
		predict_x(items1,tool,"w","y");
		predict_x(items1,tool,"w","z");
		
		
	}
	/*
	 * Ԥ���¹���̬Ϊt���¹ʽ��Ϊx�ķ���Ƶ����Ƶ����
	 */
	private void predict_x(ArrayList<String> items1,PrefixSpanTool tool,String t,String x)
	{
		ArrayList<String> tempitems=new ArrayList<>();
		tempitems.add(t);
		tempitems.add(x);
		Sequence seq=new Sequence();
		ArrayList<ItemSet> itemSetList=new ArrayList<>();
		ItemSet is1=new ItemSet(items1);
		ItemSet is2=new ItemSet(tempitems);
		itemSetList.add(is1);
		itemSetList.add(is2);
		Sequence xseq=new Sequence();
		xseq.setItemSetList(itemSetList);
		ta1.append("���У�");
		ta1.append("<");
		for (ItemSet itemSet : xseq.getItemSetList())
		{
			
			if (itemSet.getItems().size() > 1) {  
                ta1.append("(");
            }  

            for (String str : itemSet.getItems()) {  
                ta1.append(str + "");
            }  

            if (itemSet.getItems().size() > 1) {  
                ta1.append(")");
            }  
            
            
		}
		ta1.append(">   ");
		for(Map.Entry entry : tool.get_FrequencyMap().entrySet())
		{
			Sequence tempseq=(Sequence) entry.getKey();
			int tempcount=(Integer) entry.getValue();
			if(tempseq.is_same(xseq))
			{
				ta1.append("Ƶ����");
				ta1.append(Integer.toString(tempcount)+"  ");
				ta1.append("Ƶ���ȣ�");
				float tempcount1=tool.get_pMap().get(tempseq);
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//���췽�����ַ���ʽ�������С������2λ,����0����.
				String p=decimalFormat.format(tempcount1);//format ���ص����ַ���
				ta1.append(p);
				
			}
		}
		
		ta1.append("\n");
		
		
		
		
	}
	
	/*
	 * ����������С֧�ֶȵ�Ƶ������
	 */
	private void mining(double mins,boolean select,boolean select1)
	{
		
		 String filePath = "F:\\jtsg1.txt";  
	        //��С֧�ֶ���ֵ��  
	          
	     PrefixSpanTool tool = new PrefixSpanTool(filePath, mins);  
	     tool.prefixSpanCalculate(); 
	     
	    
	     if(!select&&!select1)
	    	 print1(tool.get_FrequencyMap(),tool.get_pMap());
	     else if(select&&!select1)
	    	 print2(tool.get_FrequencyMap(),tool.get_pMap());
	     else if(!select&&select1)
	    	 print3(tool.get_FrequencyMap(),tool.get_pMap());
	     else
	    	 print4(tool.get_FrequencyMap(),tool.get_pMap());
	     
	    	 
	    	 
	     
		
	}
	/*
	 * ����mining����
	 */
	public void intro_m()
	{
		ta2.setText("");
		ta2.append("1.������С֧�ֶ���ֵ����(���������ҪΪ0-1֮���ʵ��)��\n");
		ta2.append("2.ѡ���Ƿ�ֻѡ�����¹ʽ�������У�Ĭ��Ϊ�ǡ�\n");
		ta2.append("3.ѡ���Ƿ���ʾ�������У�Ĭ��Ϊ�ǡ�\n");
		ta2.append("4.�����go����ť���ھ�ɹ����������·��ı�����ʾ��\n");
		
	}
	/*
	 * ����predict����
	 */
	public void intro_p()
	{
		ta2.setText("");
		ta2.append("1.����������Ҫ��ѯ���еı��루���ݱ������д).\n");
		ta2.append("2.�����go����ť��Ԥ��ɹ����������Ҳ��ı�����ʾ��\n");
		ta2.append("Ԥ��ģʽ\n");
		ta2.append("1.����֧�ֶ���ֵ������Ƶ������ֵ.\n");
		ta2.append("2.�����Ӧ���ݿ⣬ÿ��һ��ʱ���ѯ���ݿ⣬Ԥ��������¼�Ľ��\n");
		
		
	}
	/*
	 * �����Ľ���
	 */
	public void intro_t()
	{
		ta2.setText("");
		ta2.append("�¹�ʱ��"+"          "+"0ʱ��6ʱ"+"       "+"a\n");
		ta2.append("�¹�ʱ��"+"          "+"6ʱ��12ʱ"+"     "+"b\n");
		ta2.append("�¹�ʱ��"+"          "+"12ʱ��18ʱ"+"   "+"c\n");
		ta2.append("�¹�ʱ��"+"          "+"18ʱ��24ʱ"+"   "+"d\n");
		ta2.append("������ʩ����"+"   "+"�޷���"+"           "+"e\n");
		ta2.append("������ʩ����"+"   "+"�̻���"+"           "+"f\n");
		ta2.append("������ʩ����"+"   "+"�л���"+"           "+"g\n");
		ta2.append("������ʩ����"+"   "+"�е���"+"           "+"h\n");
		ta2.append("·�����"+"           "+"����"+"               "+"i\n");
		ta2.append("·�����"+"           "+"��ʪ"+"               "+"j\n");
		ta2.append("��·����"+"           "+"ƽֱ"+"               "+"k\n");
		ta2.append("��·����"+"           "+"һ����"+"           "+"l\n");
		ta2.append("��·����"+"           "+"һ����"+"           "+"m\n");
		ta2.append("·��·������"+"   "+"��ͨ·��"+"       "+"n\n");
		ta2.append("·��·������"+"   "+"��֦�ֲ��"+"   "+"o\n");
		ta2.append("·��·������"+"   "+"��֦�ֲ��"+"   "+"p\n");
		ta2.append("�����λ��"+"       "+"��������"+"       "+"q\n");
		ta2.append("�����λ��"+"       "+"�ǻ�������"+"   "+"r\n");
		ta2.append("�����λ��"+"       "+"���ǻ�ϵ�"+"   "+"s\n");
		ta2.append("�¹���̬"+"         "+"��ײ�˶�����"+" "+"t\n");
		ta2.append("�¹���̬"+"           "+"��ײ����"+"      "+"u\n");
		ta2.append("�¹���̬"+"           "+"ײ�̶���"+"      "+"v\n");
		ta2.append("�¹���̬"+"         "+"��ײ��ֹ����"+" "+"w\n");
		ta2.append("�¹�����"+"           "+"����"+"               "+"x\n");
		ta2.append("�¹�����"+"           "+"����"+"               "+"y\n");
		ta2.append("�¹���̬"+"           "+"�Ʋ���ʧ"+"      "+"z\n");
		
		
	
		
		
		
	}
	/*
	 * ����ָ��p�Ľ���
	 */
	public void intro_P()
	{
		ta2.setText("");
		ta2.append("�����ı���������Ȩ�ص���������ģʽ��Ƶ���̶ȵ�ָ��P:\n");
		ta2.append("����ģʽ��Ƶ����������ÿһ����Ŀ����������������Ȩ�أ�Ȼ����͡�\n");
		ta2.append("���������Ե�Ȩ�ؼ�����Ϊ1.��\n");
		ta2.append("��������ָ����ڳ����е����ֻ���Ը�һЩ��\n");
		
		
	}
	/*
	 * Ȩ�ر�Ľ���
	 */
	public void intro_w()
	{
		ta2.setText(""); 
		ta2.append("��Ŀ����"+"          "+"Ȩ��\n");
		ta2.append("�¹�ʱ��"+"          "+"0.1\n");
		ta2.append("������ʩ����"+"  "+"0.1\n");
		ta2.append("·�����"+"          "+"0.1\n");
		ta2.append("��·����"+"          "+"0.1\n");
		ta2.append("·��·������"+"  "+"0.1\n");
		ta2.append("�����λ��"+"      "+"0.1\n");
		ta2.append("�¹���̬"+"          "+"0.2\n");
		ta2.append("�¹�����"+"          "+"0.2\n");
		
		
	}
	
   
	
	public void print1(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//�����������ģʽ����Ƶ����Ƶ����
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //��������
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //��������
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("����ģʽ�ھ�\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			ta.append("���У�");
			ta.append("<");
			for (ItemSet itemSet : tempseq.getItemSetList())
			{
				
				if (itemSet.getItems().size() > 1) {  
                    ta.append("(");
                }  

                for (String str : itemSet.getItems()) {  
                    ta.append(str + "");
                }  

                if (itemSet.getItems().size() > 1) {  
                    ta.append(")");
                }  
                
                
			}
			
			ta.append(">   ");
			ta.append("Ƶ����");
			int tempcount1=map.get(tempseq);
			ta.append(Integer.toString(tempcount1)+"  ");
			ta.append("Ƶ���ȣ�");
			
			DecimalFormat decimalFormat=new DecimalFormat("0.0000");//���췽�����ַ���ʽ�������С������2λ,����0����.
			String p=decimalFormat.format(tempcount);//format ���ص����ַ���
			ta.append(p);
			ta.append("\n");
		}
	}
	
	public void print2(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//����н�ͨ�¹ʽ������ģʽ����Ƶ����Ƶ����
	{
		
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //��������
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //��������
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("����ģʽ�ھ�\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			
			if(tempseq.strIsContained("x")||tempseq.strIsContained("y")||tempseq.strIsContained("z"))
			{
				ta.append("���У�");
				ta.append("<");
				for (ItemSet itemSet : tempseq.getItemSetList())
				{
					
					if (itemSet.getItems().size() > 1) {  
	                    ta.append("(");
	                }  

	                for (String str : itemSet.getItems()) {  
	                    ta.append(str + "");
	                }  

	                if (itemSet.getItems().size() > 1) {  
	                    ta.append(")");
	                }  
	                
	                
				}
				
				ta.append(">   ");
				ta.append("Ƶ����");
				int tempcount1=map.get(tempseq);
				ta.append(Integer.toString(tempcount1)+"  ");
				ta.append("Ƶ���ȣ�");
				
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//���췽�����ַ���ʽ�������С������2λ,����0����.
				String p=decimalFormat.format(tempcount);//format ���ص����ַ���
				ta.append(p);
				ta.append("\n");
			}
		}
	}
	
	public void print3(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//���������������ģʽ����Ƶ����Ƶ����
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //��������
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //��������
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("����ģʽ�ھ�\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			ta.append("���У�");
			ta.append("<");
			for (ItemSet itemSet : tempseq.getItemSetList())
			{
				
				if (itemSet.getItems().size() > 1) {  
                    ta.append("(");
                }  

                for (String str : itemSet.getItems()) {  
                    ta.append(decoding(str) + ",");
                }  

                if (itemSet.getItems().size() > 1) {  
                    ta.append(")");
                }  
                
                
			}
			
			ta.append(">   ");
			ta.append("Ƶ����");
			int tempcount1=map.get(tempseq);
			ta.append(Integer.toString(tempcount1)+"  ");
			ta.append("Ƶ���ȣ�");
			
			DecimalFormat decimalFormat=new DecimalFormat("0.0000");//���췽�����ַ���ʽ�������С������2λ,����0����.
			String p=decimalFormat.format(tempcount);//format ���ص����ַ���
			ta.append(p);
			ta.append("\n");
		}
	}
	
	public void print4(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//����н�ͨ�¹ʽ������������ģʽ����Ƶ����Ƶ����
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //��������
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //��������
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("����ģʽ�ھ�\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();

			if(tempseq.strIsContained("x")||tempseq.strIsContained("y")||tempseq.strIsContained("z"))
			{
				ta.append("���У�");
				ta.append("<");
				for (ItemSet itemSet : tempseq.getItemSetList())
				{
					
					if (itemSet.getItems().size() > 1) {  
	                    ta.append("(");
	                }  

	                for (String str : itemSet.getItems()) {  
	                    ta.append(decoding(str) + ",");
	                }  

	                if (itemSet.getItems().size() > 1) {  
	                    ta.append(")");
	                }  
	                
	                
				}
				
				ta.append(">   ");
				ta.append("Ƶ����");
				int tempcount1=map.get(tempseq);
				ta.append(Integer.toString(tempcount1)+"  ");
				ta.append("Ƶ���ȣ�");
				
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//���췽�����ַ���ʽ�������С������2λ,����0����.
				String p=decimalFormat.format(tempcount);//format ���ص����ַ���
				ta.append(p);
				ta.append("\n");
			}
		}
	}
	/*
	 * �õ������Ӧ��ʵ�ʺ���
	 */
	public String decoding(String s)
	{
		String s1=new String();
		switch(s)
		{
			case "a":s1="0ʱ��6ʱ";break;
			case "b":s1="6ʱ��12ʱ";break;
			case "c":s1="12ʱ��18ʱ";break;
			case "d":s1="18ʱ��24ʱ";break;
			case "e":s1="�޷���";break;
			case "f":s1="�̻���";break;
			case "g":s1="�л���";break;
			case "h":s1="�е���";break;
			case "i":s1="����";break;
			case "j":s1="��ʪ";break;
			case "k":s1="ƽֱ";break;
			case "l":s1="һ����";break;
			case "m":s1="һ����";break;
			case "n":s1="��ͨ·��";break;
			case "o":s1="��֦�ֲ��";break;
			case "p":s1="��֦�ֲ��";break;
			case "q":s1="��������";break;
			case "r":s1="�ǻ�������";break;
			case "s":s1="���ǻ�ϵ�";break;
			case "t":s1="��ײ�˶�����";break;
			case "u":s1="��ײ����";break;
			case "v":s1="ײ�̶���";break;
			case "w":s1="��ײ��ֹ����";break;
			case "x":s1="����";break;
			case "y":s1="����";break;
			case "z":s1="�Ʋ���ʧ";break;
			default:break;
		}
		return s1;
	}

}
