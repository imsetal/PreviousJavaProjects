package cn.yzw.device.vo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * 实验室设备类别
 * @author 杨子威
 */
public class Category implements Serializable, Comparator<Category> {
    private Integer id;
    private String title;
    private Integer level;
    private Integer seq;
    private Integer Pid;
    private List<Category> children;


    @Override
    public int compare(Category o1, Category o2) {
        return  o1.getSeq() - o2.getSeq();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getPid() {
        return Pid;
    }

    public void setPid(Integer pid) {
        Pid = pid;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", level=" + level +
                ", seq=" + seq +
                ", Pid=" + Pid +
                ", children=" + children +
                '}';
    }
}
