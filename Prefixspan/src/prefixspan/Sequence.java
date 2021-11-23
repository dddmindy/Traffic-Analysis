package prefixspan;

import java.util.ArrayList;

 
public class Sequence {  
    // �����ڵ��  
    private ArrayList<ItemSet> itemSetList;  
  
    public Sequence() {  
        this.itemSetList = new ArrayList<>();  
    }  
  
    public ArrayList<ItemSet> getItemSetList() {  
        return itemSetList;  
    }  
  
    public void setItemSetList(ArrayList<ItemSet> itemSetList) {  
        this.itemSetList = itemSetList;  
    }  
  
    /** 
     * �жϵ�һ���Ƿ�����ڴ�����
     *  
     * @param c 
     *            ���ж��� 
     * @return 
     */  
    public boolean strIsContained(String c) {  
        boolean isContained = false;  
  
        for (ItemSet itemSet : itemSetList) {  //������itemSetList�е�ÿ�����ֵ��itemSet
            isContained = false;  
  
            for (String s : itemSet.getItems()) {  //��itemSet��е�ÿ���ֵ��s��c�Ƚ�
                if (itemSet.getItems().contains("_")) {  
                    continue;  
                }  
  
                if (s.equals(c)) {  
                    isContained = true;  
                    break;  
                }  
            }  
  
            if (isContained) {  
                // ����Ѿ����������ˣ�ֱ������ѭ��  
                break;  
            }  
        }  
  
        return isContained;  
    }  
  
    /** 
     * �ж���Ƿ������������ 
     */  
    public boolean componentItemIsContain(ItemSet itemSet) {  
        boolean isContained = false;  
        ArrayList<String> tempItems;  
        String lastItem = itemSet.getLastValue();  
  
        for (int i = 0; i < this.itemSetList.size(); i++) {  
            tempItems = this.itemSetList.get(i).getItems();  
            // ��2��������ң���һ�ִ�_X���ҳ�x���������Ԫ�أ���Ϊ_ǰ׺�Ѿ�Ϊԭ����Ԫ��  
            if (tempItems.size() > 1 && tempItems.get(0).equals("_")  
                    && tempItems.contains(lastItem)) {  
                isContained = true;  
                break;  
            } else if (!tempItems.get(0).equals("_")) {  
                // ��û��_ǰ׺�����ʼѰ�ң��ڶ���Ϊ�Ӻ���ĺ�׺���ҳ�ֱ���ҳ������ַ�ΪabΪͬһ����  
                if (strArrayContains(tempItems, itemSet.getItems())) {  
                    isContained = true;  
                    break;  
                }  
            }  
  
            if (isContained) {  
                break;  
            }  
        }  
  
        return isContained;  
    }  
  
    /** 
     * ɾ�������� 
     *  
     * @param s 
     *            ��ɾ���� 
     */  
    public void deleteSingleItem(String s) {  
        ArrayList<String> tempItems;  
        ArrayList<String> deleteItems = new ArrayList<>();  
  
        for (ItemSet itemSet : this.itemSetList) {  
            tempItems = itemSet.getItems();  
            deleteItems = new ArrayList<>();  
  
            for (int i = 0; i < tempItems.size(); i++) {  
                if (tempItems.get(i).equals(s)) {  
                    deleteItems.add(tempItems.get(i));  
                }  
            }  
  
            tempItems.removeAll(deleteItems);  
        }  
    }  
  
    /** 
     * ��ȡ��s֮�����õ����� 
     *  
     * @param s 
     *            Ŀ����ȡ��s 
     */  
    public Sequence extractItem(String s) {  
        Sequence extractSeq = this.copySequence();  
        ItemSet itemSet;  
        ArrayList<String> items;  
        ArrayList<ItemSet> deleteItemSets = new ArrayList<>();  
        ArrayList<String> tempItems = new ArrayList<>();  
  
        for (int k = 0; k < extractSeq.itemSetList.size(); k++) {  
            itemSet = extractSeq.itemSetList.get(k);  
            items = itemSet.getItems();  
            if (items.size() == 1 && items.get(0).equals(s)) {  
                //����ҵ����ǵ������ȫ�Ƴ�������ѭ��  
                extractSeq.itemSetList.remove(k);  
                break;  
            } else if (items.size() > 1 && !items.get(0).equals("_")) {  
                //�ں����Ķ�Ԫ�������ж��Ƿ������Ԫ��  
                if (items.contains(s)) {  
                    //���������s�����Ԫ�ؼ��뵽��ʱ�ַ�������  
                    int index = items.indexOf(s);  
                    for (int j = index; j < items.size(); j++) {  
                        tempItems.add(items.get(j));  
                    }  
                    //����һλ��s����±��"_"  
                    tempItems.set(0, "_");  
                    if (tempItems.size() == 1) {  
                        // �����ƥ��Ϊ����ĩ�ˣ�ͬ���Ƴ�  
                        deleteItemSets.add(itemSet);  
                    } else {  
                        //���仯�����滻ԭ����  
                        extractSeq.itemSetList.set(k, new ItemSet(tempItems));  
                    }  
                    break;  
                } else {  
                    deleteItemSets.add(itemSet);  
                }  
            } else {  
                // ����������2��������ͳͳ�Ƴ�  
                deleteItemSets.add(itemSet);  
            }  
        }  
        extractSeq.itemSetList.removeAll(deleteItemSets);  
  
        return extractSeq;  
    }  
  
    /** 
     * ��ȡ�����֮������� 
     *  
     * @param array 
     *            ������� 
     * @return 
     */  
    public Sequence extractCompoentItem(ArrayList<String> array) {  
        // �ҵ�Ŀ����Ƿ�����ֹͣ  
        boolean stopExtract = false;  
        Sequence seq = this.copySequence();  
        String lastItem = array.get(array.size() - 1);  
        ArrayList<String> tempItems;  
        ArrayList<ItemSet> deleteItems = new ArrayList<>();  
  
        for (int i = 0; i < seq.itemSetList.size(); i++) {  
            if (stopExtract) {  
                break;  
            }  
  
            tempItems = seq.itemSetList.get(i).getItems();  
            // ��2��������ң���һ�ִ�_X���ҳ�x���������Ԫ�أ���Ϊ_ǰ׺�Ѿ�Ϊԭ����Ԫ��  
            if (tempItems.size() > 1 && tempItems.get(0).equals("_")  
                    && tempItems.contains(lastItem)) {  
                if (tempItems.size() == 2) {  
                    seq.itemSetList.remove(i);  
                } else {  
                    // ��1��λ�ñ�Ϊ�±��"_"��
                	while(!tempItems.get(1).equals(lastItem))
                	{
                		tempItems.remove(1);
                	}
                	tempItems.set(1, "_");
                    tempItems.remove(0);  
                }  
                stopExtract = true;  
                break;  
            } else if (!tempItems.get(0).equals("_")) {  
                // ��û��_ǰ׺�����ʼѰ�ң��ڶ���Ϊ�Ӻ���ĺ�׺���ҳ�ֱ���ҳ������ַ�ΪabΪͬһ����  
                if (strArrayContains(tempItems, array)) {  
                    // ���������ҳ���һ�������ַ���λ�ã��Ѻ���Ĳ��ֽ�ȡ����  
                    int index = tempItems.indexOf(lastItem);  
                    ArrayList<String> array2 = new ArrayList<String>();  
  
                    for (int j = index; j < tempItems.size(); j++) {  
                        array2.add(tempItems.get(j));  
                    }  
                    array2.set(0, "_");  
  
                    if (array2.size() == 1) {  
                        //���������ĩβ��λ�ã����Ƴ������������滻  
                        deleteItems.add(seq.itemSetList.get(i));  
                    } else {  
                        seq.itemSetList.set(i, new ItemSet(array2));  
                    }  
                    stopExtract = true;  
                    break;  
                } else {  
                    deleteItems.add(seq.itemSetList.get(i));  
                }  
            } else {  
                // ��������Ǵ���_X��X���������һ��Ԫ�ص����  
                deleteItems.add(seq.itemSetList.get(i));  
            }  
        }  
          
        seq.itemSetList.removeAll(deleteItems);  
  
        return seq;  
    }  
    
    /*
     * �ж����������Ƿ���ͬ
     */
    public boolean is_same(Sequence seq1)
    {
    	boolean is_same=true;
    	if(seq1.itemSetList.size()!=this.itemSetList.size())
    	{
    		is_same=false;
    	}
    	else
    	{
    		for(int i=0;i<this.itemSetList.size();i++)
    		{
    			ItemSet itemSet=this.itemSetList.get(i);
    			ItemSet itemSet1=seq1.itemSetList.get(i);
    			if(itemSet.getItems().size()!=itemSet1.getItems().size())
    			{
    				is_same=false;
    			}
    			else
    			{
    				for(int j=0;j<itemSet.getItems().size();j++)
    				{
    					if(!itemSet.getItems().get(j).equals(itemSet1.getItems().get(j)))
    					{
    						is_same=false;
    					}
    				}
    			}
    			
    		}
    	}
    	return is_same;
    	
    }
    
    /** 
     * ���һ������ 
     *  
     * @return 
     */  
    public Sequence copySequence() {  
        Sequence copySeq = new Sequence();  
        ItemSet tempItemSet;  
        ArrayList<String> items;  
  
        for (ItemSet itemSet : this.itemSetList) {  
            items = (ArrayList<String>) itemSet.getItems().clone();  
            tempItemSet = new ItemSet(items);  
            copySeq.getItemSetList().add(tempItemSet);  
        }  
  
        return copySeq;  
    }  
  
    /** 
     * ��ȡ���������һ��������1��Ԫ�� 
     *  
     * @return 
     */  
    public String getLastItemSetValue() {  
        int size = this.getItemSetList().size();  
        ItemSet itemSet = this.getItemSetList().get(size - 1);  
        size = itemSet.getItems().size();  
  
        return itemSet.getItems().get(size - 1);  
    }  
  
    /** 
     * �ж�strList2�Ƿ���strList1�������� 
     *  
     * @param strList1 
     * @param strList2 
     * @return 
     */  
    public static boolean strArrayContains(ArrayList<String> strList1,  
            ArrayList<String> strList2) {  
        boolean isContained = true;  
        boolean isexist;
        for (int i = 0,j = 0; i < strList2.size(); i++) {  
            isexist = false;  
  
            for (; j < strList1.size(); j++) {  
                if (strList1.get(j).equals(strList2.get(i))) {  
                    isexist = true;  
                    j++;
                    break;  
                }  
            }  
  
            if (!isexist) {  
            	isContained=false;
                break;  
            }  
        }  
  
        return isContained;  
    }  
}  
