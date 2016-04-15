package sk.com.sharedpreference;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName,edtPassword;
    CheckBox savepreference_ckt;
    Button submit;
    LinearLayout mLinearLayout;
    SharedPreferences prefernce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = (LinearLayout) findViewById(R.id.main);
        edtUserName = (EditText) findViewById(R.id.user_name);
        edtPassword = (EditText) findViewById(R.id.password);
        savepreference_ckt = (CheckBox) findViewById(R.id.chk_save);
        submit = (Button) findViewById(R.id.submit);
        prefernce = getSharedPreferences("My Prefernce", MODE_PRIVATE);


        final SharedPreferences.Editor mEditor = prefernce.edit();


        edtUserName.setText(prefernce.getString("User Name", null));
        edtPassword.setText(prefernce.getString("Password", null));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserName.getText().toString().length() != 0 && edtPassword.getText().toString().length() != 0){
                    if (savepreference_ckt.isChecked()) {

                        mEditor.putString("User Name", edtUserName.getText().toString());
                        mEditor.putString("Password", edtPassword.getText().toString());
                        mEditor.commit();
                        Snackbar.make(mLinearLayout, "User NamePassword are saved", Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        mEditor.clear();
                        mEditor.commit();
                        Snackbar.make(mLinearLayout, "User NamePassword are not saved", Snackbar.LENGTH_SHORT).show();
                        edtUserName.setText("");
                        edtPassword.setText("");
                    }
                }
                else {
                    Snackbar.make(mLinearLayout, "Enter both field", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }
}
