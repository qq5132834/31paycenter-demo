package chap3.dendrogram;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DendrogramDemo {

    public static void main(String[] args) {

        List<DendrogramDto> list = new ArrayList<DendrogramDto>();
        DendrogramDto dto = new DendrogramDto();
        dto.setName("cal");
        List<DendrogramDto> children = new ArrayList<DendrogramDto>();
        children.add(new DendrogramDto("a"));
        children.add(new DendrogramDto("+"));
        children.add(new DendrogramDto("b"));
        dto.setChildren(children);
        list.add(dto);
        Dendrogram.writeImage("JPG", new File("C:/Users/51328/Desktop/本书源代码/tree.jpg"), list, "root");
    }

}
