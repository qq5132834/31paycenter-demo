package chap3.dendrogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DendrogramTreeDemo {

    public static void main(String[] args) {

        List<DendrogramTree.TreeNode> list = new ArrayList<DendrogramTree.TreeNode>();
        DendrogramTree.TreeNode dto = new DendrogramTree.TreeNode("cal");

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

        List<DendrogramTree.TreeNode> dtoChildren = new ArrayList<DendrogramTree.TreeNode>();
        dtoChildren.add(a);
        dtoChildren.add(new DendrogramTree.TreeNode("*"));

        DendrogramTree.TreeNode d = new DendrogramTree.TreeNode("[c1 + d2]");
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
        dtoChildren.add(d);

        dto.setChildren(dtoChildren);
        list.add(dto);
        DendrogramTree.writeImage("JPG", new File("C:/Users/51328/Desktop/本书源代码/tree.jpg"), list);
    }

}
