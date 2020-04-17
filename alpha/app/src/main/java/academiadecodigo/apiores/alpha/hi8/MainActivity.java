package academiadecodigo.apiores.alpha.hi8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.HashSet;
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
    public static List<Integer> userIdSet = new ArrayList<>();

    public ArrayUserCard getArrayAdapter() {
        return arrayAdapter;
    }

    private void populateArray() {
        UserCard jojo = new UserCard("1", "jojo",1);
        UserCard beruno = new UserCard("2", "beruno",2);
        UserCard hugo = new UserCard("3", "hugo",3);
        UserCard miguel = new UserCard("4", "miguel",4);
        UserCard Evandro = new UserCard("6", "evandro",5);
        UserCard pedro = new UserCard("16", "pedro",6);
        UserCard fabio = new UserCard("7", "fabio",7);
        UserCard bernardo = new UserCard("2", "bernardo",8);
        UserCard andre = new UserCard("1", "andre",9);
        UserCard gustavo = new UserCard("14", "gustavo",10);
        UserCard francisco = new UserCard("8", "francisco",11);
        UserCard sara = new UserCard("9", "sara",12);
        UserCard diogo = new UserCard("5", "diogo",13);
        UserCard soraia = new UserCard("21", "soraia",14);
        UserCard julio = new UserCard("12", "julio",15);
        UserCard rita = new UserCard("17", "rita",16);
        UserCard ricardo = new UserCard("18", "ricardo",17);
        UserCard jaime = new UserCard("10", "jaime",18);
        UserCard ruben = new UserCard("19", "ruben",19);
        UserCard claudia = new UserCard("4", "claudia",20);
        UserCard xavier = new UserCard("22", "xavier",21);
        UserCard paulo = new UserCard("15", "paulo",22);
        UserCard last = new UserCard("15", "last",23);
        UserCard last1 = new UserCard("15", "last1",24);

        queuecard.add(jojo);
        queuecard.add(beruno);
        queuecard.add(hugo);
        queuecard.add(miguel);
        queuecard.add(Evandro);
        queuecard.add(pedro);
        queuecard.add(fabio);
        queuecard.add(bernardo);
        queuecard.add(andre);
        queuecard.add(gustavo);
        queuecard.add(francisco);
        queuecard.add(sara);
        queuecard.add(diogo);
        queuecard.add(soraia);
        queuecard.add(julio);
        queuecard.add(rita);
        queuecard.add(ricardo);
        queuecard.add(jaime);
        queuecard.add(ruben);
        queuecard.add(claudia);
        queuecard.add(xavier);
        queuecard.add(paulo);
        queuecard.add(last);
        queuecard.add(last1);


        for (int i = 0; i < 23; i++){
            userIdSet.add(i);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        queuecard = new ArrayList<UserCard>();
        populateArray();

        arrayAdapter = new ArrayUserCard(this, R.layout.item, queuecard);

        SwipeFlingAdapterView container = (SwipeFlingAdapterView) findViewById(R.id.frame);


        container.setAdapter(arrayAdapter);
        container.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {


                Log.d("LIST", "removed an object!");

                if (arrayAdapter.getNum() < 23) {
                    queuecard.remove(0);
                }


                arrayAdapter.notifyDataSetChanged();

            }


            @Override
            public void onLeftCardExit(Object dataObject) {

                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();

                arrayAdapter.setNum();



                //arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
                arrayAdapter.setNum();



                //arrayAdapter.notifyDataSetChanged();

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



    public void logout(View view) {
        auth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginController.class);
        startActivity(intent);
        finish();
        return;
    }
}