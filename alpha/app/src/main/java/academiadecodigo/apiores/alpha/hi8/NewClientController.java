package academiadecodigo.apiores.alpha.hi8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;
import java.util.Map;

public class NewClientController extends AppCompatActivity {

    private Button registerButton;
    private EditText userEmail, userPassword, userName;

    private RadioGroup gender;

    private FirebaseAuth authentication;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        authentication = FirebaseAuth.getInstance();

        //start fire base
        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null){
                    Intent intent = new Intent(NewClientController.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        registerButton = (Button) findViewById(R.id.register);
        userEmail = (EditText) findViewById(R.id.email);
        userPassword = (EditText) findViewById(R.id.password);
        userName = (EditText) findViewById(R.id.name);
        gender = (RadioGroup) findViewById(R.id.radioGroup);

        //set register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectId = gender.getCheckedRadioButtonId();

                final RadioButton radioButton = (RadioButton) findViewById(selectId);


                if(radioButton.getText() == null) {
                    return;
                }

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String name = userName.getText().toString();

                authentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener(NewClientController.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(NewClientController.this, "sign up error", Toast.LENGTH_SHORT).show();

                        } else {
                            String userId = authentication.getCurrentUser().getUid();
                            DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                            Map userInfo = new HashMap<>();

                            userInfo.put("name", name);
                            userInfo.put("sex", radioButton.getText().toString());
                            userInfo.put("profileImageUrl", "default");

                            currentUserDb.updateChildren(userInfo);

                        }
                    }
                });
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        authentication.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        authentication.removeAuthStateListener(firebaseAuthStateListener);
    }
}
