package cn.zh.Dome01.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 浅笑 on 2018/4/7.
 */
//权限表
public class privilege {
    private Integer id;
    private  String url;//权限导航的地址
    private String name;
    private Integer parent;
    private String icon;//小图标
     private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<privilege> getChildren() {
        return children;
    }

    public void setChildren(List<privilege> children) {
        this.children = children;
    }

    private List<privilege> children=new ArrayList<privilege>();

    @Override
    public String toString() {
        return "privilege{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
