package com.example.meal4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


public class SignUpActivity extends AppCompatActivity {

    private EditText FirstName, LastName, PhoneNumber, Address, SignUpEmail, SignUpPassword, SignUpCPassword;
    private Button SignUp;

    private FirebaseAuth firebaseAuth;
    private Firebase firebase;
   private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firebase = new Firebase("https://meal4u-69675.firebaseio.com/");


       progressDialog = new ProgressDialog(this);

        FirstName = (EditText) findViewById(R.id.firstName);
        LastName = (EditText) findViewById(R.id.lastName);
        PhoneNumber = (EditText) findViewById(R.id.mobileNumber);
        Address = (EditText) findViewById(R.id.address);
        SignUpEmail = (EditText) findViewById(R.id.signup_email);
        SignUpPassword = (EditText) findViewById(R.id.signup_password);
        SignUpCPassword = (EditText) findViewById(R.id.signupc_password);
        SignUp = (Button) findViewById(R.id.continue_button);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    private void signUp() {

        final String firstName = FirstName.getText().toString();
        final String lastName = LastName.getText().toString();
        final String Number = PhoneNumber.getText().toString();
        final String address = Address.getText().toString().trim();
        final String email = SignUpEmail.getText().toString().trim();
        String password = SignUpPassword.getText().toString().trim();
        String confirmPassword = SignUpCPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(Number) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)) {

            if(firstName.isEmpty())
            {
                FirstName.setError("First Name is required!");
                FirstName.requestFocus();
                return;
            }
            if(lastName.isEmpty())
            {
                LastName.setError("Last Name is required!");
                LastName.requestFocus();
                return;
            }
            if(address.isEmpty())
            {
                Address.setError("Address is required!");
                Address.requestFocus();
                return;
            }
            if(Number.isEmpty())
            {
                PhoneNumber.setError("Phone Number is required!");
                PhoneNumber.requestFocus();
                return;
            }
            if(!Patterns.PHONE.matcher(Number).matches())
            {
                PhoneNumber.setError("Enter valid number");
                PhoneNumber.requestFocus();
                return;
            }
            if(email.isEmpty())
            {
                SignUpEmail.setError("Email is required!");
                SignUpEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                SignUpEmail.setError("Enter valid EmailId");
                SignUpEmail.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                SignUpPassword.setError("Password is required!");
                SignUpPassword.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                SignUpPassword.setError("Length of Password should be 6");
                SignUpPassword.requestFocus();
                return;
            }

            if(!password.equals(confirmPassword))
            {
                SignUpCPassword.setError("Password should be Same");
                SignUpCPassword.requestFocus();
                return;
            }


          progressDialog.setMessage("Signing Up");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        Customer customer = new Customer();
                        customer.setFname(firstName);
                        customer.setLname(lastName);
                        customer.setAddress(address);
                        customer.setMobile(Number);
                        customer.setEmailId(email);

                        Firebase dbReference = firebase.child("Customer").push();

                        if(customer != null) {
                            dbReference.setValue(customer);

                            Toast.makeText(SignUpActivity.this,"User Created",Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Oppsss!!! UNSuccessfull",  Toast.LENGTH_SHORT).show();
                        }

                       progressDialog.dismiss();
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}