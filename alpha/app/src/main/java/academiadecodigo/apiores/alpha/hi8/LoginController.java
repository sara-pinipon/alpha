package academiadecodigo.apiores.alpha.hi8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LoginController extends AppCompatActivity {

    private Button loginButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_login_registration);

        loginButton = (Button) findViewById(R.id.login);
        registerButton = (Button) findViewById(R.id.register);
        System.out.println("___________________aaaaaaa_________________________");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginController.this, AccessController.class);
                startActivity(intent);
                finish();
                return;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginController.this, NewClientController.class);
                startActivity(intent);
                finish();
                return;
            }
        });
    }
}