package chap3.dendrogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DendrogramTreeDemo {

    public static void main(String[] args) {

        List<DendrogramTree.TreeNode> dtoChildren = new ArrayList<DendrogramTree.TreeNode>();
        dtoChildren.add(createChildrenA());
        dtoChildren.add(new DendrogramTree.TreeNode("*"));
        dtoChildren.add(createChildrenB());
        dtoChildren.add(new DendrogramTree.TreeNode("%"));
        dtoChildren.add(createChildrenD());
        dtoChildren.add(new DendrogramTree.TreeNode("mod"));
        dtoChildren.add(createChildrenB());

        List<DendrogramTree.TreeNode> list = new ArrayList<DendrogramTree.TreeNode>();
        DendrogramTree.TreeNode dto = new DendrogramTree.TreeNode("语法");
        dto.setChildren(dtoChildren);
        list.add(dto);
        DendrogramTree.writeImage("JPG", new File("C:/Users/51328/Desktop/本书源代码/tree.jpg"), list);
    }

    private static DendrogramTree.TreeNode createChildrenA(){
        DendrogramTree.TreeNode a = new DendrogramTree.TreeNode("(a1 - a2)");
        List<DendrogramTree.TreeNode> aChildren = new ArrayList<DendrogramTree.TreeNode>();
        aChildren.add(new DendrogramTree.TreeNode("("));

        DendrogramTree.TreeNode c = new DendrogramTree.TreeNode("a1 - a2");
        List<DendrogramTree.TreeNode> cChildren = new ArrayList<DendrogramTree.TreeNode>();
        cChildren.add(new DendrogramTree.TreeNode("a1"));
        cChildren.add(new DendrogramTree.TreeNode("-"));
        cChildren.add(new DendrogramTree.TreeNode("a2"));
        c.setChildren(cChildren);
        aChildren.add(c);

        aChildren.add(new DendrogramTree.TreeNode(")"));
        a.setChildren(aChildren);
        return a;
    }

    private static DendrogramTree.TreeNode createChildrenD(){
        DendrogramTree.TreeNode d = new DendrogramTree.TreeNode("[number1 + number2]");
        List<DendrogramTree.TreeNode> dChildren = new ArrayList<DendrogramTree.TreeNode>();
        dChildren.add(new DendrogramTree.TreeNode("["));

        DendrogramTree.TreeNode e = new DendrogramTree.TreeNode("number1 + number2");
        List<DendrogramTree.TreeNode> eChildren = new ArrayList<DendrogramTree.TreeNode>();
        eChildren.add(new DendrogramTree.TreeNode("number1"));
        eChildren.add(new DendrogramTree.TreeNode("+"));
        eChildren.add(new DendrogramTree.TreeNode("number2"));
        e.setChildren(eChildren);
        dChildren.add(e);

        dChildren.add(new DendrogramTree.TreeNode("]"));
        d.setChildren(dChildren);
        return d;
    }

    private static DendrogramTree.TreeNode createChildrenB(){
        DendrogramTree.TreeNode d = new DendrogramTree.TreeNode("{str1 + str2}/abc");
        List<DendrogramTree.TreeNode> dChildren = new ArrayList<DendrogramTree.TreeNode>();
        dChildren.add(new DendrogramTree.TreeNode("{"));

        DendrogramTree.TreeNode e = new DendrogramTree.TreeNode("str1 + str2");
        List<DendrogramTree.TreeNode> eChildren = new ArrayList<DendrogramTree.TreeNode>();
        DendrogramTree.TreeNode str1 =new DendrogramTree.TreeNode("str1");
        str1.setChildren(new ArrayList<DendrogramTree.TreeNode>());
        str1.getChildren().add(new DendrogramTree.TreeNode("s"));
        str1.getChildren().add(new DendrogramTree.TreeNode("t"));
        str1.getChildren().add(new DendrogramTree.TreeNode("r"));
        str1.getChildren().add(new DendrogramTree.TreeNode("1"));
        eChildren.add(str1);

        eChildren.add(new DendrogramTree.TreeNode("+"));

        DendrogramTree.TreeNode str2 = new DendrogramTree.TreeNode("str2");
        str2.setChildren(new ArrayList<DendrogramTree.TreeNode>());
        str2.getChildren().add(new DendrogramTree.TreeNode("s"));
        str2.getChildren().add(new DendrogramTree.TreeNode("t"));
        str2.getChildren().add(new DendrogramTree.TreeNode("r"));
        str2.getChildren().add(new DendrogramTree.TreeNode("2"));
        eChildren.add(str2);
        e.setChildren(eChildren);
        dChildren.add(e);
        dChildren.add(new DendrogramTree.TreeNode("}"));
        dChildren.add(new DendrogramTree.TreeNode("/"));
        dChildren.add(new DendrogramTree.TreeNode("abc"));
        d.setChildren(dChildren);
        return d;
    }

}
