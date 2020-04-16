package academiadecodigo.apiores.alpha.hi8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccessController extends AppCompatActivity {

    private Button loginButton;
    private EditText emailButton, passwordButton;



    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                /*
                if (user != null){
                    Intent intent = new Intent(AccessController.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                */
            }
        };


        loginButton = (Button) findViewById(R.id.login);

        emailButton = (EditText) findViewById(R.id.email);
        passwordButton = (EditText) findViewById(R.id.password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailButton.getText().toString();
                final String password = passwordButton.getText().toString();



                if(!email.equals("admin") || !password.equals("admin")){
                    Toast.makeText(AccessController.this, "sign in error", Toast.LENGTH_SHORT).show();
                    System.out.println("-----------------------------ifffff_________________________________");
                    return;
                }

                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<aaaaaaaaaaaaaaaaaaa>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                Intent intent = new Intent(AccessController.this, MainActivity.class);
                startActivity(intent);
                System.out.println("-----<<<-<-<-<<-<--<-<-<-<---_>>_<->-<_>_>-<-<-->_>-<->__>->_");
                finish();
                return;


        }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListener);
    }
}