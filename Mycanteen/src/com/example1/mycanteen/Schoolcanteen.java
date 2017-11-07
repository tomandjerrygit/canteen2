package com.example1.mycanteen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schoolcanteen {
  public static class Canteen implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public Integer id;
    public String name;
    public String desc;
    public String notice;
    public Canteen(Integer id,String name,String desc,String notice) {
      this.id=id;
      this.name=name;
      this.desc=desc;
      this.notice=notice;
    }
    public String toString() {
      return name;
    }
    public Integer getId() {
      return id;
    }
    public void setId(Integer id) {
      this.id = id;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getDesc() {
      return desc;
    }
    public void setDesc(String desc) {
      this.desc = desc;
    }
    public String getNotice() {
      return notice;
    }
    public void setNotice(String notice) {
      this.notice = notice;
    }
  }
  public static List<Canteen> ITEMS=new ArrayList<Canteen>();
  public static Map<Integer,Canteen> ITEM_MAP=new HashMap<Integer,Canteen>();
  static {
    addItem(new Canteen(1,"ѧʿ","ѧʿ����","ѧʿ������ٹ���"));
    addItem(new Canteen(2,"ѧԷ","ѧԷ����","ѧʿ������ټ���Ӫҵ"));
    addItem(new Canteen(3,"�ڵ�","�ڵ�","�µ꿪ҵ"));
    addItem(new Canteen(4,"��ζի","�������","�Ƴ���ץ���ⷹ"));
    addItem(new Canteen(5,"����԰","����԰","�����Խ���"));
  }
  private static void addItem(Canteen canteen) {
    ITEMS.add(canteen);
    ITEM_MAP.put(canteen.id, canteen);
  }
}
