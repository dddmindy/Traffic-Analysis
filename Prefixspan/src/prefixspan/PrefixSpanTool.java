package prefixspan;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;  
import java.util.Map;  
  
public class PrefixSpanTool {  
    
	private String filePath;// 测试数据文件地址    
    private double minSupportRate;// 最小支持度阈值比例    
    private int minSupport;// 最小支持度，通过序列总数乘以阈值比例计算    
    private ArrayList<Sequence> totalSeqs;// 原始序列组    
    private ArrayList<Sequence> totalFrequentSeqs;// 挖掘出的所有序列频繁模式    
    private ArrayList<String> singleItems;  // 所有频繁的单一项，用于递归枚举   
    private Integer tempcount;//用来临时存储序列模式的频数
    private HashMap<String,Integer> itemMap;//用于存储单项和其对应的频数
    private HashMap<Sequence,Integer> FrequencyMap;//存储序列模式和其对应的频数
    private HashMap<Sequence,Float> pMap;//存储序列模式和其对应的置信度
    
    public PrefixSpanTool(String filePath, double minSupportRate) {  
        this.filePath = filePath;  
        this.minSupportRate = minSupportRate;  
        this.itemMap=new HashMap<>();
        this.FrequencyMap=new HashMap<>();
        this.pMap=new HashMap<>();
        
        readDataFile();  
    }  
    public HashMap<String,Integer> get_itemMap()
    {
    	return itemMap;
    }
    public ArrayList<Sequence> get_totalFrequentSeqs()
    {
    	return totalFrequentSeqs;
    }
    
    public HashMap<Sequence,Integer> get_FrequencyMap()
    {
    	return FrequencyMap;
    }
    
    public HashMap<Sequence,Float> get_pMap()
    {
    	return pMap;
    }
    /** 
     * 从文件中读取数据 
     */  
    private void readDataFile() {  
        File file = new File(filePath);  
        ArrayList<String[]> dataArray = new ArrayList<String[]>();  
  
        try {  
            BufferedReader in = new BufferedReader(new FileReader(file));  
            String str;  
            String[] tempArray;  
            while ((str = in.readLine()) != null) {  
                tempArray = str.split(" ");  
                dataArray.add(tempArray);  
            }  
            in.close();  
        } catch (IOException e) {  
            e.getStackTrace();  
        }  
  
        minSupport = (int) Math.ceil(dataArray.size() * minSupportRate) ;
        if(minSupport==0) minSupport=1;
        totalSeqs = new ArrayList<>();  
        totalFrequentSeqs = new ArrayList<>();  
        Sequence tempSeq;  
        ItemSet tempItemSet;  
        for (String[] str : dataArray) {  
            tempSeq = new Sequence();  
            for (String s : str) {  
                tempItemSet = new ItemSet(s); //序列s按字符作为项初始化为项集 
                tempSeq.getItemSetList().add(tempItemSet);  
            }  
            totalSeqs.add(tempSeq);  
        }  
     
    }  
  
    /** 
     * 输出序列列表内容 
     *  
     * @param seqList 
     *            待输出序列列表 
     */  
    private void outputSequence(ArrayList<Sequence> seqList) {  
        for (Sequence seq : seqList) {  
            System.out.print("<");  
            for (ItemSet itemSet : seq.getItemSetList()) {  
                if (itemSet.getItems().size() > 1) {  
                    System.out.print("(");  
                }  
  
                for (String s : itemSet.getItems()) {  
                    System.out.print(s + "");  
                }  
  
                if (itemSet.getItems().size() > 1) {  
                    System.out.print(")");  
                }  
            }  
            System.out.println(">");  
        }  
    }  
  
    /** 
     * 移除初始序列中不满足最小支持度阈值的单项 
     */  
    private void removeInitSeqsItem() {  
        int count = 0;    
        singleItems = new ArrayList<>();  
  
        for (Sequence seq : totalSeqs) {  //遍历所有序列中的项，加入itemMap
            for (ItemSet itemSet : seq.getItemSetList()) {  
                for (String s : itemSet.getItems()) {  
                    if (!itemMap.containsKey(s)) {  
                        itemMap.put(s, 1);  
                    }  
                }  
            }  
        }  
  
        String key;  
        for (Map.Entry entry : itemMap.entrySet()) {  //遍历所有序列，计算包含key的序列个数，存入itemMap
            count = 0;  
            key = (String) entry.getKey();  
            for (Sequence seq : totalSeqs) {  
                if (seq.strIsContained(key)) {  
                    count++;  
                }  
            }  
  
            itemMap.put(key, count);  
  
        }  
  
        for (Map.Entry entry : itemMap.entrySet()) {  
            key = (String) entry.getKey();  
            count = (int) entry.getValue();  
  
            if (count >= minSupport) {  
                singleItems.add(key);  
            }  
        }  
  
        Collections.sort(singleItems);  
    }  
  
    /** 
     * 递归搜索满足条件的序列模式 
     *  
     * @param beforeSeq 
     *            前缀序列 
     * @param afterSeqList 
     *            后缀序列列表 
     */  
    private void recursiveSearchSeqs(Sequence beforeSeq,  
            ArrayList<Sequence> afterSeqList) {  
        ItemSet tempItemSet;  
        Sequence tempSeq2;  
        Sequence tempSeq;  
        ArrayList<Sequence> tempSeqList = new ArrayList<>();  
  
        for (String s : singleItems) {  
            // 分成2种形式递归，以<a>为起始项，第一种直接加入独立项集遍历<a,a>,<a,b> <a,c>..  
            if (isLargerThanMinSupport(s, afterSeqList)) {  
                tempSeq = beforeSeq.copySequence();  
                tempItemSet = new ItemSet(s);  
                tempSeq.getItemSetList().add(tempItemSet);  
                FrequencyMap.put(tempSeq, tempcount);
                totalFrequentSeqs.add(tempSeq);  
                tempSeqList = new ArrayList<>();  
                for (Sequence seq : afterSeqList) {  
                    if (seq.strIsContained(s)) {  
                        tempSeq2 = seq.extractItem(s);  
                        tempSeqList.add(tempSeq2);  
                    }  
                }  
  
                recursiveSearchSeqs(tempSeq, tempSeqList);  
            }  
  
            // 第二种递归为以元素的身份加入最后的项集内以a为例<(aa)>,<(ab)>,<(ac)>...  
            // a在这里可以理解为一个前缀序列，里面可能是单个元素或者已经是多元素的项集  
            tempSeq = beforeSeq.copySequence();  
            int size = tempSeq.getItemSetList().size();  
            tempItemSet = tempSeq.getItemSetList().get(size - 1);  
            tempItemSet.getItems().add(s);  
  
            if (isLargerThanMinSupport(tempItemSet, afterSeqList)) {  
                tempSeqList = new ArrayList<>();  
                for (Sequence seq : afterSeqList) {  
                    if (seq.componentItemIsContain(tempItemSet)) {  
                        tempSeq2 = seq.extractCompoentItem(tempItemSet  
                                .getItems());  
                        tempSeqList.add(tempSeq2);  
                    }  
                }  
                FrequencyMap.put(tempSeq, tempcount);
                totalFrequentSeqs.add(tempSeq);  

                recursiveSearchSeqs(tempSeq, tempSeqList);  
            }  
        }  
    }  
  
    /** 
     * 所传入的项组合在所给定序列中的支持度是否超过阈值 
     *  
     * @param s 
     *            所需匹配的项 
     * @param seqList 
     *            比较序列数据 
     * @return 
     */  
    private boolean isLargerThanMinSupport(String s, ArrayList<Sequence> seqList) {  
        boolean isLarge = false;  
        int count = 0;  
  
        for (Sequence seq : seqList) {  
            if (seq.strIsContained(s)) {  
                count++;  
            }  
        }  
  
        if (count >= minSupport) {  
            isLarge = true;  
            tempcount=count;
        }  
  
        return isLarge;  
    }  
  
    /** 
     * 所传入的组合项集在序列中的支持度是否大于阈值 
     *  
     * @param itemSet 
     *            组合元素项集 
     * @param seqList 
     *            比较的序列列表 
     * @return 
     */  
    private boolean isLargerThanMinSupport(ItemSet itemSet,  
            ArrayList<Sequence> seqList) {  
        boolean isLarge = false;  
        int count = 0;  
  
        if (seqList == null) {  
            return false;  
        }  
  
        for (Sequence seq : seqList) {  
            if (seq.componentItemIsContain(itemSet)) {  
                count++;  
            }  
        }  
  
        if (count >= minSupport) {  
            isLarge = true;
            tempcount=count;
        }  
  
        return isLarge;  
    }  
    
    
    /**
     * 得到序列模式的频繁度p
     */
    private float get_weight(String str)
    {
    	float w=0;
    	switch(str)
		{
			case "a":w=(float) 0.1;break;
			case "b":w=(float) 0.1;break;
			case "c":w=(float) 0.1;break;
			case "d":w=(float) 0.1;break;
			case "e":w=(float) 0.1;break;
			case "f":w=(float) 0.1;break;
			case "g":w=(float) 0.1;break;
			case "h":w=(float) 0.1;break;
			case "i":w=(float) 0.1;break;
			case "j":w=(float) 0.1;break;
			case "k":w=(float) 0.1;break;
			case "l":w=(float) 0.1;break;
			case "m":w=(float) 0.1;break;
			case "n":w=(float) 0.1;break;
			case "o":w=(float) 0.1;break;
			case "p":w=(float) 0.1;break;
			case "q":w=(float) 0.1;break;
			case "r":w=(float) 0.1;break;
			case "s":w=(float) 0.1;break;
			case "t":w=(float) 0.2;break;
			case "u":w=(float) 0.2;break;
			case "v":w=(float) 0.2;break;
			case "w":w=(float) 0.2;break;
			case "x":w=(float) 0.2;break;
			case "y":w=(float) 0.2;break;
			case "z":w=(float) 0.2;break;
			default:break;
		}
    	return w;
    }
    
    /**
     * 得到序列模式的频繁度p
     */
    private void create_pMap()
    {
    	Sequence tempseq;
		int tempcount;
		float count;
		float icount;
		float p;
		float w;
    	for(Map.Entry entry : FrequencyMap.entrySet())
    	{
    		tempseq=(Sequence) entry.getKey();
			tempcount=(Integer) entry.getValue();
			count=(float) tempcount;
			p=0;
			for (ItemSet itemSet : tempseq.getItemSetList())
			{
				for (String str : itemSet.getItems())
				{
					w=get_weight(str);
					icount=(float) itemMap.get(str);
					p=p+count/icount*w;
				}
			}
			pMap.put(tempseq, p);
    	}
    	
    }
  
    /** 
     * 序列模式分析计算 
     */  
    public void prefixSpanCalculate() {  
        Sequence seq;  
        Sequence tempSeq;  
        ArrayList<Sequence> tempSeqList = new ArrayList<>();  
        ItemSet itemSet;  
        removeInitSeqsItem();  
  
        for (String s : singleItems) {  
            // 从最开始的a,b,d开始递归往下寻找频繁序列模式  
            seq = new Sequence();  
            itemSet = new ItemSet(s);  
            seq.getItemSetList().add(itemSet);  
  
            if (isLargerThanMinSupport(s, totalSeqs)) {  
                tempSeqList = new ArrayList<>();  
                for (Sequence s2 : totalSeqs) {  
                    // 判断单一项是否包含于在序列中，包含才进行提取操作  
                    if (s2.strIsContained(s)) {  
                        tempSeq = s2.extractItem(s);  
                        tempSeqList.add(tempSeq);  
                    }  
                }  
  
                totalFrequentSeqs.add(seq);  
                recursiveSearchSeqs(seq, tempSeqList);  
            }  
        }  
        create_pMap();
    }  
    
    
    
    /** 
     * 按模式类别输出频繁序列模式 
     */  
    private void printTotalFreSeqs() {  
        System.out.println("序列模式挖掘结果：");  
          
        ArrayList<Sequence> seqList;  
        HashMap<String, ArrayList<Sequence>> seqMap = new HashMap<>();  
        for (String s : singleItems) {  
            seqList = new ArrayList<>();  
            for (Sequence seq : totalFrequentSeqs) {  
                if (seq.getItemSetList().get(0).getItems().get(0).equals(s)) {  
                    seqList.add(seq);  
                }  
            }  
            seqMap.put(s, seqList);  
        }  
  
        int count = 0;  
        for (String s : singleItems) {  
            count = 0;  
            System.out.println();  
            System.out.println();  
  
            seqList = (ArrayList<Sequence>) seqMap.get(s);  
            for (Sequence tempSeq : seqList) {  
                count++;  
                System.out.print("<");  
                for (ItemSet itemSet : tempSeq.getItemSetList()) {  
                    if (itemSet.getItems().size() > 1) {  
                        System.out.print("(");  
                    }  
  
                    for (String str : itemSet.getItems()) {  
                        System.out.print(str + "");  
                    }  
  
                    if (itemSet.getItems().size() > 1) {  
                        System.out.print(")");  
                    }  
                }  
                System.out.print(">, ");  
  
                // 每5个序列换一行  
                if (count == 5) {  
                    count = 0;  
                    System.out.println();  
                }  
            }  
  
        }  
    }  
  
}  