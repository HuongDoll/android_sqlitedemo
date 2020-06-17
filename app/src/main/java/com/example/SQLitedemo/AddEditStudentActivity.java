package com.example.SQLitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditStudentActivity extends AppCompatActivity {

    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;

    private EditText textid;
    private EditText textname;
    private EditText textbirthday;
    private EditText textemail;
    private EditText textcountry;
    private Button buttonSave;
    private Button buttonCancel;

    private Student student;
    private boolean needRefresh;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_student);

        this.textid = (EditText)this.findViewById(R.id.edit_id);
        this.textname = (EditText)this.findViewById(R.id.edit_name);
        this.textbirthday = (EditText)this.findViewById(R.id.edit_birthday);
        this.textemail = (EditText)this.findViewById(R.id.edit_email);
        this.textcountry = (EditText)this.findViewById(R.id.edit_country);

        this.buttonSave = (Button)findViewById(R.id.button_save);
        this.buttonCancel = (Button)findViewById(R.id.button_cancel);

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonSaveClicked();
            }
        });

        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                buttonCancelClicked();
            }
        });

        Intent intent = this.getIntent();
        this.student = (Student) intent.getSerializableExtra("student");
        if(student == null)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            this.textid.setText(student.getId());
            this.textname.setText(student.getName());
            this.textbirthday.setText(student.getBirthday());
            this.textemail.setText(student.getEmail());
            this.textcountry.setText(student.getCountry());
        }
    }

    // User Click on the Save button.
    public void buttonSaveClicked()  {
        DatabaseHandler db = new DatabaseHandler(this);

        String id = this.textid.getText().toString();
        String name = this.textname.getText().toString();
        String birthday = this.textbirthday.getText().toString();
        String email = this.textemail.getText().toString();
        String country = this.textcountry.getText().toString();

        if(id.equals("") || name.equals("") || birthday.equals("") || email.equals("") || country.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter id, name, birthday, email & country", Toast.LENGTH_LONG).show();
            return;
        }

        if(mode == MODE_CREATE ) {
            this.student= new Student(id, name, birthday, email, country);
            db.addStudent(student);
        } else  {
            this.student.setId(id);
            this.student.setName(name);
            this.student.setBirthday(birthday);
            this.student.setEmail(email);
            this.student.setCountry(country);
            db.updateStudent(student);
        }

        this.needRefresh = true;

        // Back to MainActivity.
        this.onBackPressed();
    }

    // User Click on the Cancel button.
    public void buttonCancelClicked()  {
        // Do nothing, back MainActivity.
        this.onBackPressed();
    }

    // When completed this Activity,
    // Send feedback to the Activity called it.
    @Override
    public void finish() {

        // Create Intent
        Intent data = new Intent();

        // Request MainActivity refresh its ListView (or not).
        data.putExtra("needRefresh", needRefresh);

        // Set Result
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }
}