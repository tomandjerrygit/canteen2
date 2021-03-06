package com;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Dao {
  DbPool dbp=new DbPool();
  /*public List<Book> allbooks(int pageNumber,int pageSize,String s)
  {
    List<Book> booklist=new ArrayList<Book>();
    String sql="select * from book b,author a where b.authorid=a.authorid and a.name=\""+s+"\" limit ?,?";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, (pageNumber-1)*pageSize);
      ps.setInt(2, (pageSize));
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        Book book=new Book();
        //isbn!!!!!!!!!!!!!!!
        book.setIsbn(rs.getInt("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthorid(rs.getInt("authorid"));
        book.setPublisher(rs.getString("publisher"));
        book.setPublishdata(rs.getDate("publishdata"));
        book.setPrice(rs.getInt("price"));
        book.setName(rs.getString("name"));
        book.setAge(rs.getInt("age"));
        book.setCountry(rs.getString("country"));
        booklist.add(book);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return booklist;
  }
  public int getbooksamount(String s)
  {
    int i=0;
    String sql="select count(*) from book b,author a where b.authorid=a.authorid and a.name=\""+s+"\"";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        i=rs.getInt(1);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return i;
  }
  public Book findbook(String s)
  {
    Book thebook=new Book();
    String sql="select * from book b,author a where b.authorid=a.authorid and b.title=\""+s+"\"";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        thebook.setIsbn(rs.getInt("isbn"));
        thebook.setTitle(rs.getString("title"));
        thebook.setAuthorid(rs.getInt("authorid"));
        thebook.setPublisher(rs.getString("publisher"));
        thebook.setPublishdata(rs.getDate("publishdata"));
        thebook.setPrice(rs.getInt("price"));
        thebook.setName(rs.getString("name"));
        thebook.setAge(rs.getInt("age"));
        thebook.setCountry(rs.getString("country"));
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return thebook;
  }
  public int deletebook(int i)
  {
    String sql="delete from book where isbn=?";
    PreparedStatement ps=null;
    int iflag=0;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, i);
      iflag=ps.executeUpdate();
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return iflag;
  }
  public String findauthor(int i)
  {
    String name="";
    String sql="select name from book b,author a where b.authorid=a.authorid and b.isbn=?";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, i);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        name=rs.getString("name");
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return name;
  }
  public int updata(String title,String publisher,java.util.Date publishdata,int price,int isbn)
  {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    String str=sdf.format(publishdata);  
    String sql="update book set title=\""+title+"\",publisher=\""+publisher+"\",publishdata=\""+str
        +"\",price="+price+" where title=\""+title+"\"";
    PreparedStatement ps=null;
    int iflag=0;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      //ps.setString(1, );
      iflag=ps.executeUpdate();
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return iflag;
  }*/
  public DbPool getDbp() {
    return dbp;
  }
  public void setDbp(DbPool dbp) {
    this.dbp = dbp;
  }
  public int login(String id,String password)
  {
    int canteenid=0;
    String sql="select canteenid from workerlogin where id=\""+id+"\" and password=\""+password+"\"";
    System.out.println(sql);
    PreparedStatement ps=null;
    System.out.println(canteenid);
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        canteenid=rs.getInt("canteenid");
      }
      //ps.close();
      System.out.println(canteenid);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return canteenid;
  }
}
