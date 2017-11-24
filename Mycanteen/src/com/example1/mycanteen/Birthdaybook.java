package com.example1.mycanteen;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Birthdaybook extends Activity {
  public Schoolcanteen.Canteen canteen;
  private Calendar cal;  
  private int year,month,day;
  private int yearbook=0,monthbook=0,daybook=0;
  TextView showtime,tip;
  Button button,buttonsignup;
  RadioGroup rg;
  EditText peoplenumber;
  private int lunchordinner;
  public int getLunchordinner() {
    return lunchordinner;
  }

  public void setLunchordinner(int lunchordinner) {
    this.lunchordinner = lunchordinner;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_birthdaybook);
    TextView canteenname=(TextView) findViewById(R.id.birthdaybooktextview);    
    Intent intent=getIntent();
    canteen=(Schoolcanteen.Canteen)intent.getSerializableExtra("canteen");
    canteenname.setText("����Ԥ��");
    getDate();
    tip=(TextView) findViewById(R.id.birthdaybooktextView5);
    //tip.setText("");
    showtime=(TextView) findViewById(R.id.birthdaybooktextView1);
    button=(Button) findViewById(R.id.birthdaybookbutton1);
    button.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
        OnDateSetListener listener=new OnDateSetListener() {          
          @Override  
          public void onDateSet(DatePicker arg0, int year, int month, int day) {  
            showtime.setText(year+"-"+(++month)+"-"+day); //��ѡ���������ʾ��TextView��,��Ϊ֮ǰ��ȡmonthֱ��ʹ�ã����Բ���Ҫ+1������ط���Ҫ��ʾ������+1
            yearbook=year;
            monthbook=month;
            daybook=day;
          }  
      };  
      DatePickerDialog dialog=new DatePickerDialog(Birthdaybook.this, 0,listener,year,month,day);//�����������Ϊ��ʾdialogʱĬ�ϵ����ڣ��·ݴ�0��ʼ��0-11��Ӧ1-12����  
      dialog.show();  
      }
  });
    rg = (RadioGroup) findViewById(R.id.birthdaybookGroup);
    setLunchordinner(1);
    choice();
    peoplenumber=(EditText) findViewById(R.id.birthdaybookeditText1);
    buttonsignup=(Button) findViewById(R.id.birthdaybookbutton2);
    buttonsignup.setOnClickListener(new View.OnClickListener() {   
      @Override
      public void onClick(View v) {
          if (validate()) {
            final Bundle data=new Bundle();
            data.putSerializable("canteen", canteen);
            Intent intent=new Intent(Birthdaybook.this,Birthdaybook.class);
            intent.putExtras(data);
            startActivity(intent);
            finish();
            /*if (registerPro()>0) {
              Intent intent=new Intent(WorkerRegister.this,MainActivity.class);
              startActivity(intent);
              finish();
            }
            else {
              text.setText("��ʾ��ע��ʧ��");
            }*/
          }            
      }
  });
  }
  
  
  
  private boolean validate() {
    if (yearbook==0) {
      tip.setText("��ʾ:��ѡ������");
      return false;
    }
    String pnstring=peoplenumber.getText().toString();
    if (pnstring.trim()== null || pnstring.trim().length() == 0) {
      tip.setText("��ʾ:����������");
      return false;
    }
    int pn=Integer.parseInt(pnstring);
    //String pwd=password.getText().toString().trim();
    if (pn>25) {
      tip.setText("��ʾ:��������");
      return false;
  }
    if (yearbook<year) {
      tip.setText("��ʾ:��ʱ��β���ԤԼ");
      return false;
    }
    else if (yearbook==year) {
      if (monthbook<month) {
        tip.setText("��ʾ:��ʱ��β���ԤԼ");
        return false;
      }
      else if (monthbook==month) {
        if (daybook<=day) {
          tip.setText("��ʾ:��ʱ��β���ԤԼ");
          return false;
        }
      }
    }
  return true;
  }
  
  private void getDate() {  
    cal=Calendar.getInstance();  
    year=cal.get(Calendar.YEAR);       //��ȡ������ʱ����    
    Log.i("TestLog","year"+year);  
    month=cal.get(Calendar.MONTH);   //��ȡ�����·��Ǵ�0��ʼ����  
    day=cal.get(Calendar.DAY_OF_MONTH);      
   }
  
  private void choice() {
    rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group,int checkedId) {
        switch (checkedId) {
        case R.id.birthdaybookradioButton1:
          setLunchordinner(1);
          break;
        case R.id.birthdaybookradioButton2:
          setLunchordinner(2);
          break;
        }
      }
    });
  }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.birthdaybook, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
