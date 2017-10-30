package com.example1.mycanteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Workerlogin extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_workerlogin);
    final Button buttonup = (Button) findViewById(R.id.toRegister);
    final Button buttonup1 = (Button) findViewById(R.id.Login1);
    final EditText name = (EditText) findViewById(R.id.EnterName);
    final TextView text = (TextView) findViewById(R.id.info1);
    buttonup1.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {
            if (name.getText().toString() == null || name.getText().toString().length() == 0) {
                text.setText("��ʾ���û��������ڣ�");
            }
            else {
            //��ת����¼�ɹ�ҳ��
            Intent intent=new Intent(Workerlogin.this,LoginSuccess.class);
            startActivity(intent);
            }
        }
    });
    buttonup.setOnClickListener(new View.OnClickListener() {   
        @Override
        public void onClick(View v) {

            //��ת��ע��ҳ��
            Intent intent=new Intent(Workerlogin.this,WorkerRegister.class);
            startActivity(intent);
        }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    //getMenuInflater().inflate(R.menu.workerlogin, menu);
    getMenuInflater().inflate(R.menu.workerlogin, menu);
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
