package academiadecodigo.apiores.alpha.hi8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;


import java.util.ArrayList;
import java.util.List;

import academiadecodigo.apiores.alpha.hi8.userCards.ArrayUserCard;
import academiadecodigo.apiores.alpha.hi8.userCards.UserCard;

public class MainActivity extends AppCompatActivity {

    private UserCard[] userCard;
    private ArrayUserCard arrayAdapter;
    private int i;

    private FirebaseAuth auth;
    private String currentUserId;
    private DatabaseReference usersDatabase;
    private ListView listView;
    private List<UserCard> queuecard;

    private String sex;
    private String oppositeSex;


    private void populateArray() {
        UserCard sara = new UserCard("1", "");
        UserCard diogo = new UserCard("2", "");
        UserCard bernardo = new UserCard("3", "");
        UserCard paulo = new UserCard("4", "");
        UserCard xavier = new UserCard("5", "");
        UserCard soraia = new UserCard("6", "");
        UserCard jojo = new UserCard("8", "");
        queuecard.add(sara);
        queuecard.add(diogo);
        queuecard.add(bernardo);
        queuecard.add(paulo);
        queuecard.add(xavier);
        queuecard.add(soraia);
        queuecard.add(jojo);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        //sexChoice();

        queuecard = new ArrayList<UserCard>();
        populateArray();

        arrayAdapter = new ArrayUserCard(this, R.layout.item, queuecard);

        SwipeFlingAdapterView container = (SwipeFlingAdapterView) findViewById(R.id.frame);


        container.setAdapter(arrayAdapter);
        container.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                Log.d("LIST", "removed an object!");
                queuecard.remove(0);
                arrayAdapter.notifyDataSetChanged();

            }


            @Override
            public void onLeftCardExit(Object dataObject) {

                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        container.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void sexChoice() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        DatabaseReference databaseMale = FirebaseDatabase.getInstance().getReference().child("Users").child("Male");
        databaseMale.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.getKey().equals(user.getUid())) {
                    sex = "Male";
                    oppositeSex = "Female";
                    //getOppositeChoice();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        DatabaseReference databaseFemale = FirebaseDatabase.getInstance().getReference().child("Users").child("Female");
        databaseFemale.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.getKey().equals(user.getUid())) {
                    sex = "Female";
                    oppositeSex = "Male";
                    //getOppositeChoice();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void getOppositeChoice() {

        DatabaseReference databaseOpposite = FirebaseDatabase.getInstance().getReference().child("Users").child(oppositeSex);
        databaseOpposite.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.exists()) {

                    UserCard userCard = new UserCard(dataSnapshot.getKey(), dataSnapshot.child("name").getKey().toString());

                    queuecard.add(userCard);
                    arrayAdapter.notifyDataSetChanged();
               }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }


    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginController.class);
        startActivity(intent);
        finish();
        return;
    }
}