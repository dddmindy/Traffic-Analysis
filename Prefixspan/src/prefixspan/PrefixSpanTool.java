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
    
	private String filePath;// ���������ļ���ַ    
    private double minSupportRate;// ��С֧�ֶ���ֵ����    
    private int minSupport;// ��С֧�ֶȣ�ͨ����������������ֵ��������    
    private ArrayList<Sequence> totalSeqs;// ԭʼ������    
    private ArrayList<Sequence> totalFrequentSeqs;// �ھ������������Ƶ��ģʽ    
    private ArrayList<String> singleItems;  // ����Ƶ���ĵ�һ����ڵݹ�ö��   
    private Integer tempcount;//������ʱ�洢����ģʽ��Ƶ��
    private HashMap<String,Integer> itemMap;//���ڴ洢��������Ӧ��Ƶ��
    private HashMap<Sequence,Integer> FrequencyMap;//�洢����ģʽ�����Ӧ��Ƶ��
    private HashMap<Sequence,Float> pMap;//�洢����ģʽ�����Ӧ�����Ŷ�
    
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
     * ���ļ��ж�ȡ���� 
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
                tempItemSet = new ItemSet(s); //����s���ַ���Ϊ���ʼ��Ϊ� 
                tempSeq.getItemSetList().add(tempItemSet);  
            }  
            totalSeqs.add(tempSeq);  
        }  
     
    }  
  
    /** 
     * ��������б����� 
     *  
     * @param seqList 
     *            ����������б� 
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
     * �Ƴ���ʼ�����в�������С֧�ֶ���ֵ�ĵ��� 
     */  
    private void removeInitSeqsItem() {  
        int count = 0;    
        singleItems = new ArrayList<>();  
  
        for (Sequence seq : totalSeqs) {  //�������������е������itemMap
            for (ItemSet itemSet : seq.getItemSetList()) {  
                for (String s : itemSet.getItems()) {  
                    if (!itemMap.containsKey(s)) {  
                        itemMap.put(s, 1);  
                    }  
                }  
            }  
        }  
  
        String key;  
        for (Map.Entry entry : itemMap.entrySet()) {  //�����������У��������key�����и���������itemMap
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
     * �ݹ�������������������ģʽ 
     *  
     * @param beforeSeq 
     *            ǰ׺���� 
     * @param afterSeqList 
     *            ��׺�����б� 
     */  
    private void recursiveSearchSeqs(Sequence beforeSeq,  
            ArrayList<Sequence> afterSeqList) {  
        ItemSet tempItemSet;  
        Sequence tempSeq2;  
        Sequence tempSeq;  
        ArrayList<Sequence> tempSeqList = new ArrayList<>();  
  
        for (String s : singleItems) {  
            // �ֳ�2����ʽ�ݹ飬��<a>Ϊ��ʼ���һ��ֱ�Ӽ�����������<a,a>,<a,b> <a,c>..  
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
  
            // �ڶ��ֵݹ�Ϊ��Ԫ�ص���ݼ������������aΪ��<(aa)>,<(ab)>,<(ac)>...  
            // a������������Ϊһ��ǰ׺���У���������ǵ���Ԫ�ػ����Ѿ��Ƕ�Ԫ�ص��  
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
     * �������������������������е�֧�ֶ��Ƿ񳬹���ֵ 
     *  
     * @param s 
     *            ����ƥ����� 
     * @param seqList 
     *            �Ƚ��������� 
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
     * ������������������е�֧�ֶ��Ƿ������ֵ 
     *  
     * @param itemSet 
     *            ���Ԫ��� 
     * @param seqList 
     *            �Ƚϵ������б� 
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
     * �õ�����ģʽ��Ƶ����p
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
     * �õ�����ģʽ��Ƶ����p
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
     * ����ģʽ�������� 
     */  
    public void prefixSpanCalculate() {  
        Sequence seq;  
        Sequence tempSeq;  
        ArrayList<Sequence> tempSeqList = new ArrayList<>();  
        ItemSet itemSet;  
        removeInitSeqsItem();  
  
        for (String s : singleItems) {  
            // ���ʼ��a,b,d��ʼ�ݹ�����Ѱ��Ƶ������ģʽ  
            seq = new Sequence();  
            itemSet = new ItemSet(s);  
            seq.getItemSetList().add(itemSet);  
  
            if (isLargerThanMinSupport(s, totalSeqs)) {  
                tempSeqList = new ArrayList<>();  
                for (Sequence s2 : totalSeqs) {  
                    // �жϵ�һ���Ƿ�������������У������Ž�����ȡ����  
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
     * ��ģʽ������Ƶ������ģʽ 
     */  
    private void printTotalFreSeqs() {  
        System.out.println("����ģʽ�ھ�����");  
          
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
  
                // ÿ5�����л�һ��  
                if (count == 5) {  
                    count = 0;  
                    System.out.println();  
                }  
            }  
  
        }  
    }  
  
}  