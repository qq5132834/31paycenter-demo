package chap3.dendrogram;

import com.alibaba.fastjson.JSON;
import io.swagger.models.auth.In;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DendrogramTree {

    private final static Integer marginY = 100; //行间距
    private final static Integer marginX = 150; //列间距
    private final static Integer fontSize = 28;

    /**
     * 生成树图写到磁盘
     * @param picType 图片类型JPG GIF JPEG PNG
     * @param file 图片文件
     * @param list 数据模型 [{name: '', children: [{name: '', children: [{}]}]}]
     * @return
     */
    public static boolean writeImage(String picType, File file, List<TreeNode> list ) {



        Integer treeDeep = 1; //树深
        calTreeNode(list, treeDeep, 1);
        System.out.println(JSON.toJSONString(list));

        List<TreeNode> leafNodeList = new ArrayList<TreeNode>();
        for (TreeNode treeNode : list) {
            getLeafNode(treeNode, leafNodeList);
        }
        System.out.println("叶子：" + JSON.toJSONString(leafNodeList));

        treeDeep = 1; //树深
        for (TreeNode leafNode : leafNodeList) {
            int deep = leafNode.getDeep();
            if(deep >= treeDeep){
                treeDeep = deep;
            }
        }
        System.out.println("树深：" + treeDeep);

        //计算每个叶子节点的(x,y)坐标

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= leafNodeList.size(); i++) {
            TreeNode leafNode = leafNodeList.get(i-1);
            int y = leafNode.getDeep() * marginY;
            int x = sb.toString().length() * fontSize + marginX * i;
            leafNode.setX(x);
            leafNode.setY(y);
            sb.append(leafNode.getName());
        }
        System.out.println("语法：" + sb.toString());
        int rectX = sb.toString().length() * fontSize + marginX * ( leafNodeList.size() + 2);
        int rectY = marginY * ( treeDeep + 2 );
        System.out.println("rectX:" + rectX + ",rectY:" + rectY);

        //
        BufferedImage bimg = new BufferedImage(rectX, rectY, BufferedImage.TYPE_INT_BGR);
        // 拿到画笔
        Graphics2D g = bimg.createGraphics();
        // 画一个图形系统默认是白色
        g.fillRect(1, 1, rectX - 2, rectY - 2);
        // 设置画笔颜色
        g.setColor(new Color(12, 123, 88));

        // 设置画笔画出的字体风格
        g.setFont(new Font("隶书", Font.ITALIC, fontSize));
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        //消除文字锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 写一个字符串



        //将叶子节点list转为map结构
        Map<String, TreeNode> map = new HashMap<String, TreeNode>();
        for (TreeNode leafNode : leafNodeList) {
            map.put(leafNode.getCode(), leafNode);
        }

        //将叶子节点的坐标写入树中
        for (TreeNode treeNode : list) {
            calLeafNodePosition(treeNode, map);
        }
        System.out.println("list：" + JSON.toJSONString(list));


        List<TreeNode> allNodeList = new ArrayList<TreeNode>();
        for (TreeNode treeNode : list) {
            getAllNode(treeNode, allNodeList);
        }
        System.out.println("allNodeList：" + JSON.toJSONString(allNodeList));
        for (int i = 0; i < allNodeList.size(); i++){
            for (TreeNode treeNode : list) {
                calParentNodePosition(treeNode);
            }
        }

        System.out.println("带坐标的树：" + JSON.toJSONString(list));
        //画图
        drawString(g, list);


//        return true;


//        int heightL2 = (imgHeight - fontSize) / list.size();
//        for (int i = 0; i < list.size(); i++) {
//            DendrogramDto dendrogramDto = list.get(i);
//            int height = heightL2 / 2;
//            String name = dendrogramDto.getName();
//            g.drawString(name, parentX + margin, height + heightL2 * i);
//            // TODO 这里可以写其他属性， y随属性个数增加而增加 fontSize * i
//            g.drawString("注册资本：" + (dendrogramDto.getRegCapital() == null?"-": dendrogramDto.getRegCapital()), parentX + margin, height + heightL2 * i + fontSize);
//            g.drawLine(parentX, parentY, parentX + margin, height + heightL2 * i);
//            if (dendrogramDto.getChildren() != null && !dendrogramDto.getChildren().isEmpty()) {
//                int myX = computeParentX(dendrogramDto.getName(), parentX + margin, fontSize);
//                drawChildrenTransverse(g, dendrogramDto.getChildren(), height + heightL2 * i, heightL2, heightL2 * i, fontSize, margin, myX);
//            }
//        }
//        // 释放画笔
        g.dispose();
        // 将画好的图片通过流形式写到硬盘上
        boolean val = false;
        try {
            val = ImageIO.write(bimg, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return val;
    }

    private static void drawString(Graphics2D g, List<TreeNode> treeNodeList){
        if(treeNodeList != null && treeNodeList.size() > 0){
            for (TreeNode treeNode : treeNodeList) {
                String name = treeNode.getName();
                int x = treeNode.getX();
                int y = treeNode.getY();
                g.drawString(name, x, y);
                if(treeNode.getChildren() != null && treeNode.getChildren().size() > 0){
                    for (TreeNode child : treeNode.getChildren()) {
                        g.drawLine(x, y, child.getX(), child.getY());
                    }
                    drawString(g, treeNode.getChildren());
                }
            }
        }
    }

//    /**
//     * 循环子树
//     * @param g Graphics2D
//     * @param children 子节点
//     * @param parentY 父节点的Y坐标
//     * @param parentHeight 父区域的高度
//     * @param startY 父区域起始Y坐标
//     * @param fontSize 字体大小
//     * @param margin 兄弟节点的间距
//     * @param parentX 父节点的X坐标
//     */
//    private static void drawChildrenTransverse(Graphics2D g, List<DendrogramDto> children, int parentY, int parentHeight, int startY, int fontSize, int margin, int parentX) {
//        int heightLn = parentHeight / children.size();
//        for (int i = 0; i < children.size(); i++) {
//            DendrogramDto dendrogramDto = children.get(i);
//            int y = heightLn / 2 + heightLn * i + startY;
//            int x = parentX + margin;
//            String name = dendrogramDto.getName();
//            g.drawString(name, x , y);
//            g.drawLine(parentX, parentY, x, y);
//            if (dendrogramDto.getChildren() != null && !dendrogramDto.getChildren().isEmpty()) {
//                int myX = computeParentX(dendrogramDto.getName(), x, fontSize);
//                int myStartY = heightLn * i + startY;
//                drawChildrenTransverse(g, dendrogramDto.getChildren(),y, heightLn, myStartY, fontSize, margin, myX);
//            }
//        }
//    }



    //写入父节点坐标
    private static void calParentNodePosition(TreeNode treeNode){
        if(treeNode.getX() != null && treeNode.getY() != null){
            return;
        }
        if(treeNode.getChildren() != null && treeNode.getChildren().size() > 0){
            boolean st = true;
            for (TreeNode node : treeNode.getChildren()) {
                if(node.getX() == null && node.getY() == null){
                    st = false;
                    calParentNodePosition(node);
                }
            }
            if (st) {
                //求取叶子节点的最大值与最小值
                Integer minX = Integer.MAX_VALUE;
                Integer maxX = Integer.MIN_VALUE;
                for (TreeNode node : treeNode.getChildren()) {
                    if (node.getX() >= maxX) {
                        maxX = node.getX();
                    }
                    if (node.getX() <= minX) {
                        minX = node.getX();
                    }
                }
                int x = (maxX + minX) / 2;
                int y = treeNode.getDeep() * marginY;
                treeNode.setX(x);
                treeNode.setY(y);
            }
        }
    }

    //写入叶子节点坐标
    private static void calLeafNodePosition(TreeNode treeNode, Map<String, TreeNode> map){
        TreeNode node = map.get(treeNode.getCode());
        if(node != null){
            treeNode.setX(node.getX());
            treeNode.setY(node.getY());
        }
        if(treeNode.getChildren() != null && treeNode.getChildren().size() > 0){
            for (TreeNode tn : treeNode.getChildren()) {
                calLeafNodePosition(tn, map);
            }
        }
    }

    private static void calTreeNode (List<TreeNode> tree, Integer treeDeep, int num){
        if(tree != null && tree.size() > 0){
            int deep = treeDeep;
            for (TreeNode treeNode : tree) {
                treeNode.setDeep(deep);
                treeNode.setCode(String.valueOf(num++));
            }

            for (TreeNode treeNode : tree) {
                if(treeNode.getChildren() != null && treeNode.getChildren().size() > 0){
                    treeDeep++;
                    calTreeNode(treeNode.getChildren(), treeDeep, num);
                }
            }
        }
    }

    //获取所有叶子节点
    private static void getLeafNode(TreeNode treeNode, List<TreeNode> leafNodes){
        if(treeNode.getChildren() == null || treeNode.getChildren().size() == 0){
            leafNodes.add(treeNode);
        }
        else {
            for (TreeNode node : treeNode.getChildren()) {
                getLeafNode(node, leafNodes);
            }
        }
    }

    //获取所有节点
    private static void getAllNode(TreeNode treeNode, List<TreeNode> parentNodes){
        if(treeNode.getChildren() != null && treeNode.getChildren().size() > 0){
            for (TreeNode node : treeNode.getChildren()) {
                getAllNode(node, parentNodes);
                parentNodes.add(node);
            }
        }
    }


    public static class TreeNode {

        private String code;
        private Integer deep;
        private Integer x;
        private Integer y;

        private String name;
        private List<TreeNode> children;

        public TreeNode(){}

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getDeep() {
            return deep;
        }

        public void setDeep(Integer deep) {
            this.deep = deep;
        }

        public TreeNode(String name){
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode> children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }


}