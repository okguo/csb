package com.csb.common.util;

import com.csb.common.base.BaseTree;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by Administrator on 2017/3/29.
 */
public class InitTree {
    /**
     * 初始化节点入口
     * @param specTableList 继承baseTree的对象集合
     * @param rootName 根目录名称
     * @return
     * @throws Exception
     */
    public String init(List specTableList,String rootName) throws Exception{
        List dataList = initList(specTableList);
        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        Node root = new Node();
        root.id = "0";
        root.text= rootName;
        root.parentId = "";
        nodeList.put(root.id, root);
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext();) {
            Map dataRecord = (Map) it.next();
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.text = (String) dataRecord.get("text");
            node.parentId = (String) dataRecord.get("parentId");
            nodeList.put(node.id, node);
        }
        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext();) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            if (node.parentId == null || node.parentId.equals("")) {
                root = node;
            } else {
                ((Node) nodeList.get(node.parentId)).addChild(node);
            }
        }
        // 输出无序的树形菜单的JSON字符串
        System.out.println(root.toString());
        return "["+root.toString()+"]";
    }
    public List initList(List  specTableList){
        List dataList = new ArrayList();
        HashMap dataRecord = new HashMap();
        for(int i = 0; i < specTableList.size(); i++) {
            BaseTree tree = (BaseTree)specTableList.get(i);
            dataRecord = new HashMap();
            dataRecord.put("id",tree.getId());
            dataRecord.put("text", tree.getText());
            String parentID = tree.getParentId();
            if (StringUtils.isEmpty(parentID)) {
                parentID = "0";
            }
            dataRecord.put("parentId",parentID);
            dataList.add(dataRecord);
        }
        return dataList;
    }
}

class  Node {
    public String id;
    public String text;
    public String parentId;

    /**
     * 子节点列表
     */
    private Children children = new Children();

    // 先序遍历，拼接JSON字符串
    public String toString() {
        String result = //"["
                "{"
                        + "\"text\":\"" + text + "\","
                        + "\"id\":\"" + id +"\"";
        if (children != null && children.getSize() != 0) {
            if (result.contains("nodes")) {
                result += ",";
            }else{
                result += ",\"nodes\":" + children.toString();
            }
        }
        return result + "}";
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加子节点
    public void addChild(Node node) {
        this.children.addChild(node);
    }
}

class  Children {
    private List list = new ArrayList();

    public int getSize(){
        return list.size();
    }
    public void addChild(Node node){
        list.add(node);
    }

    // 拼接子节点的JSON字符串
    public String toString() {
        String result = "[";
        for (Iterator it = list.iterator(); it.hasNext();) {
            result += ((Node) it.next()).toString();
            result += ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "]";
        return result;
    }

    // 子节点排序
    public void sortChildren() {
        // 对本层节点进行排序
        // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
        Collections.sort(list, new NodeIDComparator());
        // 对每个节点的下一层节点进行排序
        for (Iterator it = list.iterator(); it.hasNext();) {
            ((Node) it.next()).sortChildren();
        }
    }

    /**
     * 节点比较器
     */
    class NodeIDComparator implements Comparator {
        // 按照节点编号比较
        public int compare(Object o1, Object o2) {
            int j1 = Integer.parseInt(((Node)o1).id);
            int j2 = Integer.parseInt(((Node)o2).id);
            return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
        }
    }
}

class VO extends BaseTree {
}

