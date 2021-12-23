import java.util.*;

/**
 * 哈夫曼树
 * 最优二叉树
 */
public class huffmanTree {
    public Node root;

    /**
     * 构造函数
     * @param nodes
     */
    public huffmanTree(List<Node> nodes){
        nodes =new ArrayList<>(nodes);
        sortList(nodes);
        while (nodes.size()>1){
            creatAndReplace(nodes);
        }
        if(nodes.size()==0)
            root=null;
        else
            root = nodes.get(0);
    }

    /**
     *
     * @param nodes
     */
    private static void creatAndReplace(List<Node> nodes) {
        Node left = nodes.get(0);
        Node right = nodes.get(1);
        Node parent = new Node(left.weight + right.weight,-1);
        parent.setLeftNode(left);
        parent.setRightNode(right);
        nodes.remove(0);
        nodes.remove(0);
        nodes.add(parent);
        sortList(nodes);
    }

    /**
     * 重载操作符
     * 比较用来排序的两个参数
     */
    public static   Comparator<Node> comparator = (o1, o2) -> {
        if(o1.weight>o2.weight) {
            return 1;
        }else
        if(o1.weight==o2.weight){
            return 0;
        }else {
            return -1;
        }
    };

    /**
     * 排序
     * @param nodes
     */
    private static void sortList(List<Node> nodes) {
        Collections.sort(nodes, comparator );
    }

    /**
     * 打印出对应的01值
     * @param root
     * @param string
     * @param map
     */
    public  void print(Node root, String string,HashMap map){
        if(root!=null) {
            if (root.getRightNode() == null & root.getLeftNode() == null) {
                //  System.out.println(root.character + "  对应的编码 " + string);
                map.put(root.character, string);
            }
            if (root.getLeftNode() != null) {
                print(root.getLeftNode(), string + "0", map);
            }
            if (root.getRightNode() != null) {
                print(root.getRightNode(), string + "1", map);
            }
        }
    }

}

