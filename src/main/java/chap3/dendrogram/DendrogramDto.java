package chap3.dendrogram;

import java.util.List;

public class DendrogramDto {

    private String regCapital;

    private String name;

    private List<DendrogramDto> children;

    public DendrogramDto(){}

    public DendrogramDto(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DendrogramDto> getChildren() {
        return children;
    }

    public void setChildren(List<DendrogramDto> children) {
        this.children = children;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }
}
