package academiadecodigo.apiores.alpha.hi8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginController extends AppCompatActivity {

    private Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_registration);

        loginButton = (Button) findViewById(R.id.login);
        registerButton = (Button) findViewById(R.id.register);

        loginButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(LoginController.this, AccessController.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        registerButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(LoginController.this, ProfileController.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}