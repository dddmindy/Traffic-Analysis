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
	 * 建立第一个界面
	 */
	public void create_frame()
	{
		 JFrame f=new JFrame("基于序列模式的道路交通事故预测系统");
	     f.setLocation(100,100);//离显示屏上边缘100像素，里显示屏左边缘100像素
	     f.setSize(300,100);            //设置窗体的大小为200*100像素大小
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////用户单击窗口的关闭按钮时程序执行的操作
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
	     f.setVisible(true);   //设置窗体可见
		
	}
	/*
	 * mining功能界面
	 */
	private void method1(){ 
		 JFrame f=new JFrame("mining");
	     f.setLocation(200,200);//离显示屏上边缘100像素，里显示屏左边缘100像素
	     f.setSize(550,300);            //设置窗体的大小为500*400像素大小
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////用户单击窗口的关闭按钮时程序执行的操作
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,300);
	     panel.setLayout(null);
	     f.add(panel);
	     JLabel jl=new JLabel("最小支持度阈值比例:");
	     jl.setBounds(10, 10, 130, 30);
	     panel.add(jl);
	     JTextField jf=new JTextField("0");//文本区用于输入最小阈值比例
	     jf.setBounds(130, 10, 50, 30);
	     panel.add(jf);
	     JRadioButton rb = new JRadioButton("仅显示有事故结果的序列");  
	     rb.setBounds(200, 10, 180, 30);  
	     rb.setSelected(true);  
	     panel.add(rb);
	     JRadioButton rb1 = new JRadioButton("中文序列");  
	     rb1.setBounds(380, 10, 80, 30);  
	     rb1.setSelected(true);  
	     panel.add(rb1);
	     JButton b1=new JButton("go");//按钮，点击后执行
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
	     ta=new JTextArea();//文本区，用于显示挖掘后的结果
	     JScrollPane jsp=new JScrollPane(ta);//文本区添加滚动条
	     jsp.setBounds(10, 50, 515, 200);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //设置窗体可见
	
		
	}
	/*
	 * predict功能界面
	 */
	private void method2(){ 
		 JFrame f=new JFrame("predict");
	     f.setLocation(200,200);//离显示屏上边缘100像素，里显示屏左边缘100像素
	     f.setSize(550,380);            //设置窗体的大小为500*400像素大小
	     f.setDefaultCloseOperation(f.DISPOSE_ON_CLOSE);////用户单击窗口的关闭按钮时程序执行的操作
	     JPanel panel = new JPanel(); 
	     panel.setSize(550,380);
	     panel.setLayout(null);
	     f.add(panel);
	     JLabel jl1=new JLabel("事故时间:");
	     jl1.setBounds(10, 20, 100, 20);
	     panel.add(jl1);
	     JTextField jf1=new JTextField("");
	     jf1.setBounds(100, 20, 50, 20);
	     panel.add(jf1);
	     JLabel jl2=new JLabel("防护设施类型:");
	     jl2.setBounds(10, 60, 100, 20);
	     panel.add(jl2);
	     JTextField jf2=new JTextField("");
	     jf2.setBounds(100, 60, 50, 20);
	     panel.add(jf2);
	     JLabel jl3=new JLabel("路表情况:");
	     jl3.setBounds(10, 100, 100, 20);
	     panel.add(jl3);
	     JTextField jf3=new JTextField("");
	     jf3.setBounds(100, 100, 50, 20);
	     panel.add(jf3);
	     JLabel jl4=new JLabel("道路线形:");
	     jl4.setBounds(10, 140, 100, 20);
	     panel.add(jl4);
	     JTextField jf4=new JTextField("");
	     jf4.setBounds(100, 140, 50, 20);
	     panel.add(jf4);
	     JLabel jl5=new JLabel("路口路段类型:");
	     jl5.setBounds(10, 180, 100, 20);
	     panel.add(jl5);
	     JTextField jf5=new JTextField("");
	     jf5.setBounds(100, 180, 50, 20);
	     panel.add(jf5);
	     JLabel jl6=new JLabel("横断面位置:");
	     jl6.setBounds(10, 220, 100, 20);
	     panel.add(jl6);
	     JTextField jf6=new JTextField("");
	     jf6.setBounds(100, 220, 50, 20);
	     panel.add(jf6);
	     
	     
	     JButton b1=new JButton("go");//按钮，点击后执行
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
	     
	     JButton b2=new JButton("预警模式");//按钮，点击后执行
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	method_warn();
	            }
	        });
	     b2.setBounds(10, 295, 135, 30);
	     panel.add(b2);
	     ta1=new JTextArea();//文本区，用于显示挖掘后的结果
	     JScrollPane jsp=new JScrollPane(ta1);//文本区添加滚动条
	     jsp.setBounds(160, 20, 515, 310);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //设置窗体可见
	
		
	}
	/*
	 * introduction功能界面
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
	     JButton b1=new JButton("mining说明");//按钮，点击后执行
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_m();
	            	
	            }
	        });
	     b1.setBounds(10, 20, 135, 30);
	     panel.add(b1);
	     JButton b2=new JButton("predict说明");//按钮，点击后执行
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_p();
	            	
	            }
	        });
	     b2.setBounds(10, 60, 135, 30);
	     panel.add(b2);
	     JButton b3=new JButton("编码表");//按钮，点击后执行
	     b3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_t();
	            	
	            }
	        });
	     b3.setBounds(10, 100, 135, 30);
	     panel.add(b3);
	     JButton b4=new JButton("频繁度P");//按钮，点击后执行
	     b4.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_P();
	            	
	            }
	        });
	     b4.setBounds(10, 140, 135, 30);
	     panel.add(b4);
	     JButton b5=new JButton("权重表");//按钮，点击后执行
	     b5.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	intro_w();
	            	
	            }
	        });
	     b5.setBounds(10, 180, 135, 30);
	     panel.add(b5);
	     
	     
	     ta2=new JTextArea();//文本区，用于显示挖掘后的结果
	     JScrollPane jsp=new JScrollPane(ta2);//文本区添加滚动条
	     jsp.setBounds(160, 20, 360, 190);
	     panel.add(jsp);
	    
	     f.setVisible(true);   //设置窗体可见
	
		
	}
	/*
	 * warn功能界面，点击预警模式进入
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
	     JLabel jl=new JLabel("支持度阈值比例:");
	     jl.setBounds(10, 10, 100, 20);
	     panel.add(jl);
	     JTextField jf=new JTextField("0");//文本区用于输入最小阈值比例
	     jf.setBounds(110, 10, 30, 20);
	     panel.add(jf);
	     JLabel jl1=new JLabel("频繁度阈值:");
	     jl1.setBounds(150, 10, 100, 20);
	     panel.add(jl1);
	     JTextField jf1=new JTextField("0");//文本区用于输入最小阈值比例
	     jf1.setBounds(250, 10, 30, 20);
	     panel.add(jf1);
	     Connect conn=new Connect();
	     Timer timer = new Timer();
	     JButton b1=new JButton("开始");//按钮，点击后执行
	     b1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	double mins=Double.parseDouble(jf.getText());
	            	double p_mins=Double.parseDouble(jf1.getText());
	            	String filePath = "F:\\jtsg1.txt";  
	    	        //最小支持度阈值率  
	    	        
	    		   PrefixSpanTool tool = new PrefixSpanTool(filePath, mins);  
	    		   tool.prefixSpanCalculate();
	            	ta3.append("-----------------------------开始----------------------------\n");
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
	        	    			   ta3.append("序列：");
	        	    			   for(String str:items)
	        	    			   {
	        	    				   ta3.append(decoding(str)+",");
	        	    			   }
	        	    			   ta3.append("   预测：");
	        	    		   }
	        	    		   if(!is_safe(seq,"x",tool.get_pMap(),p_mins)||!is_safe(seq,"y",tool.get_pMap(),p_mins)||!is_safe(seq,"z",tool.get_pMap(),p_mins))
	        	    		   {
	        	    			   
	        	    			   ta3.append("危险,");
	        	    			   if(!is_safe(seq,"x",tool.get_pMap(),p_mins))
	        	    				   ta3.append("可能发生"+decoding("x")+"事故。");
	        	    			   if(!is_safe(seq,"y",tool.get_pMap(),p_mins))
	        	    				   ta3.append("可能发生"+decoding("y")+"事故。");
	        	    			   if(!is_safe(seq,"z",tool.get_pMap(),p_mins))
	        	    				   ta3.append("可能发生"+decoding("z")+"事故。");
	        	    		   }
	        	    		   else
	        	    			   ta3.append("安全");
	        	    		   ta3.append("\n");
	        	    	   }
	        	    	   conn.set_oldnum(conn.get_num());
	        	    	   
	        	       }
	        	     }, 1000, 10000);
	            	
	            }
	        });
	     b1.setBounds(10, 40, 130, 20);
	     panel.add(b1);
	     JButton b2=new JButton("结束");//按钮，点击后执行
	     b2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	timer.cancel();
	            	 ta3.append("-----------------------------结束----------------------------\n");
	            	
	            }
	        });
	     b2.setBounds(150, 40, 130, 20);
	     panel.add(b2);
	     ta3=new JTextArea();//文本区，用于显示挖掘后的结果
	     JScrollPane jsp=new JScrollPane(ta3);//文本区添加滚动条
	     jsp.setBounds(10, 65, 265, 270);
	     panel.add(jsp);
	     f.setVisible(true);   
	}
	
	/*
	 * 判断该序列是否满足达到预警模式的警告值
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
	 * 执行预测功能
	 */
	private void predict(ArrayList<String> items1)
	{
		String filePath = "F:\\jtsg1.txt";  
        //最小支持度阈值率  
          
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
	 * 预测事故形态为t，事故结果为x的发生频数和频繁度
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
		ta1.append("序列：");
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
				ta1.append("频数：");
				ta1.append(Integer.toString(tempcount)+"  ");
				ta1.append("频繁度：");
				float tempcount1=tool.get_pMap().get(tempseq);
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
				String p=decimalFormat.format(tempcount1);//format 返回的是字符串
				ta1.append(p);
				
			}
		}
		
		ta1.append("\n");
		
		
		
		
	}
	
	/*
	 * 查找满足最小支持度的频繁序列
	 */
	private void mining(double mins,boolean select,boolean select1)
	{
		
		 String filePath = "F:\\jtsg1.txt";  
	        //最小支持度阈值率  
	          
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
	 * 介绍mining功能
	 */
	public void intro_m()
	{
		ta2.setText("");
		ta2.append("1.输入最小支持度阈值比例(输入的数需要为0-1之间的实数)。\n");
		ta2.append("2.选择是否只选择有事故结果的序列，默认为是。\n");
		ta2.append("3.选择是否显示中文序列，默认为是。\n");
		ta2.append("4.点击“go”按钮，挖掘成功后序列在下方文本框显示。\n");
		
	}
	/*
	 * 介绍predict功能
	 */
	public void intro_p()
	{
		ta2.setText("");
		ta2.append("1.依次填入想要查询序列的编码（根据编码表填写).\n");
		ta2.append("2.点击“go”按钮，预测成功后序列在右侧文本框显示。\n");
		ta2.append("预警模式\n");
		ta2.append("1.输入支持度阈值比例和频繁度阈值.\n");
		ta2.append("2.监测相应数据库，每隔一定时间查询数据库，预测新增记录的结果\n");
		
		
	}
	/*
	 * 编码表的介绍
	 */
	public void intro_t()
	{
		ta2.setText("");
		ta2.append("事故时间"+"          "+"0时到6时"+"       "+"a\n");
		ta2.append("事故时间"+"          "+"6时到12时"+"     "+"b\n");
		ta2.append("事故时间"+"          "+"12时到18时"+"   "+"c\n");
		ta2.append("事故时间"+"          "+"18时到24时"+"   "+"d\n");
		ta2.append("防护设施类型"+"   "+"无防护"+"           "+"e\n");
		ta2.append("防护设施类型"+"   "+"绿化带"+"           "+"f\n");
		ta2.append("防护设施类型"+"   "+"有护栏"+"           "+"g\n");
		ta2.append("防护设施类型"+"   "+"行道树"+"           "+"h\n");
		ta2.append("路表情况"+"           "+"干燥"+"               "+"i\n");
		ta2.append("路表情况"+"           "+"潮湿"+"               "+"j\n");
		ta2.append("道路线形"+"           "+"平直"+"               "+"k\n");
		ta2.append("道路线形"+"           "+"一般弯"+"           "+"l\n");
		ta2.append("道路线形"+"           "+"一般弯"+"           "+"m\n");
		ta2.append("路口路段类型"+"   "+"普通路段"+"       "+"n\n");
		ta2.append("路口路段类型"+"   "+"三枝分叉口"+"   "+"o\n");
		ta2.append("路口路段类型"+"   "+"四枝分叉口"+"   "+"p\n");
		ta2.append("横断面位置"+"       "+"机动车道"+"       "+"q\n");
		ta2.append("横断面位置"+"       "+"非机动车道"+"   "+"r\n");
		ta2.append("横断面位置"+"       "+"机非混合道"+"   "+"s\n");
		ta2.append("事故形态"+"         "+"碰撞运动车辆"+" "+"t\n");
		ta2.append("事故形态"+"           "+"刮撞行人"+"      "+"u\n");
		ta2.append("事故形态"+"           "+"撞固定物"+"      "+"v\n");
		ta2.append("事故形态"+"         "+"碰撞静止车辆"+" "+"w\n");
		ta2.append("事故类型"+"           "+"死亡"+"               "+"x\n");
		ta2.append("事故类型"+"           "+"伤人"+"               "+"y\n");
		ta2.append("事故形态"+"           "+"财产损失"+"      "+"z\n");
		
		
	
		
		
		
	}
	/*
	 * 评价指标p的介绍
	 */
	public void intro_P()
	{
		ta2.setText("");
		ta2.append("总量的比例及属性权重的评价序列模式的频繁程度的指标P:\n");
		ta2.append("序列模式的频繁次数除以每一个项目的样本数量并乘以权重，然后求和。\n");
		ta2.append("（所有属性的权重加起来为1.）\n");
		ta2.append("（该评价指标对于长序列的评分会相对高一些）\n");
		
		
	}
	/*
	 * 权重表的介绍
	 */
	public void intro_w()
	{
		ta2.setText(""); 
		ta2.append("项目名称"+"          "+"权重\n");
		ta2.append("事故时间"+"          "+"0.1\n");
		ta2.append("防护设施类型"+"  "+"0.1\n");
		ta2.append("路表情况"+"          "+"0.1\n");
		ta2.append("道路线形"+"          "+"0.1\n");
		ta2.append("路口路段类型"+"  "+"0.1\n");
		ta2.append("横断面位置"+"      "+"0.1\n");
		ta2.append("事故形态"+"          "+"0.2\n");
		ta2.append("事故类型"+"          "+"0.2\n");
		
		
	}
	
   
	
	public void print1(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//输出所有序列模式及其频数和频繁度
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //降序排序
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //降序排序
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("序列模式挖掘\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			ta.append("序列：");
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
			ta.append("频数：");
			int tempcount1=map.get(tempseq);
			ta.append(Integer.toString(tempcount1)+"  ");
			ta.append("频繁度：");
			
			DecimalFormat decimalFormat=new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(tempcount);//format 返回的是字符串
			ta.append(p);
			ta.append("\n");
		}
	}
	
	public void print2(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//输出有交通事故结果序列模式及其频数和频繁度
	{
		
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //降序排序
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //降序排序
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("序列模式挖掘\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			
			if(tempseq.strIsContained("x")||tempseq.strIsContained("y")||tempseq.strIsContained("z"))
			{
				ta.append("序列：");
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
				ta.append("频数：");
				int tempcount1=map.get(tempseq);
				ta.append(Integer.toString(tempcount1)+"  ");
				ta.append("频繁度：");
				
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
				String p=decimalFormat.format(tempcount);//format 返回的是字符串
				ta.append(p);
				ta.append("\n");
			}
		}
	}
	
	public void print3(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//输出所有中文序列模式及其频数和频繁度
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //降序排序
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //降序排序
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("序列模式挖掘\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();
			ta.append("序列：");
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
			ta.append("频数：");
			int tempcount1=map.get(tempseq);
			ta.append(Integer.toString(tempcount1)+"  ");
			ta.append("频繁度：");
			
			DecimalFormat decimalFormat=new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
			String p=decimalFormat.format(tempcount);//format 返回的是字符串
			ta.append(p);
			ta.append("\n");
		}
	}
	
	public void print4(HashMap<Sequence,Integer> map,HashMap<Sequence,Float> map1)//输出有交通事故结果的中文序列模式及其频数和频繁度
	{
		ArrayList<Map.Entry<Sequence,Float>> list = new ArrayList<Map.Entry<Sequence,Float>>(map1.entrySet());
   	 	Collections.sort(list,new Comparator<Map.Entry<Sequence,Float>>() {
            //降序排序
        	public int compare(Map.Entry<Sequence, Float> item1, Map.Entry<Sequence, Float> item2){
                return item2.getValue().compareTo(item1.getValue());    //降序排序
            }
            
        });
		Sequence tempseq;
		float tempcount;
		ta.append("序列模式挖掘\n");
		for(Map.Entry<Sequence,Float> entry:list)
		{
			tempseq=(Sequence) entry.getKey();
			tempcount=(Float) entry.getValue();

			if(tempseq.strIsContained("x")||tempseq.strIsContained("y")||tempseq.strIsContained("z"))
			{
				ta.append("序列：");
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
				ta.append("频数：");
				int tempcount1=map.get(tempseq);
				ta.append(Integer.toString(tempcount1)+"  ");
				ta.append("频繁度：");
				
				DecimalFormat decimalFormat=new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
				String p=decimalFormat.format(tempcount);//format 返回的是字符串
				ta.append(p);
				ta.append("\n");
			}
		}
	}
	/*
	 * 得到编码对应的实际含义
	 */
	public String decoding(String s)
	{
		String s1=new String();
		switch(s)
		{
			case "a":s1="0时至6时";break;
			case "b":s1="6时至12时";break;
			case "c":s1="12时至18时";break;
			case "d":s1="18时至24时";break;
			case "e":s1="无防护";break;
			case "f":s1="绿化带";break;
			case "g":s1="有护栏";break;
			case "h":s1="行道树";break;
			case "i":s1="干燥";break;
			case "j":s1="潮湿";break;
			case "k":s1="平直";break;
			case "l":s1="一般弯";break;
			case "m":s1="一般坡";break;
			case "n":s1="普通路段";break;
			case "o":s1="三枝分叉口";break;
			case "p":s1="四枝分叉口";break;
			case "q":s1="机动车道";break;
			case "r":s1="非机动车道";break;
			case "s":s1="机非混合道";break;
			case "t":s1="碰撞运动车辆";break;
			case "u":s1="刮撞行人";break;
			case "v":s1="撞固定物";break;
			case "w":s1="碰撞静止车辆";break;
			case "x":s1="死亡";break;
			case "y":s1="伤人";break;
			case "z":s1="财产损失";break;
			default:break;
		}
		return s1;
	}

}
