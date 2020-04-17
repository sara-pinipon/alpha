package academiadecodigo.apiores.alpha.hi8.userCards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import java.util.HashSet;


import academiadecodigo.apiores.alpha.hi8.MainActivity;
import academiadecodigo.apiores.alpha.hi8.R;

public class ArrayUserCard extends ArrayAdapter<UserCard> {

    private HashSet<Integer> test;

    private Context context;
    private int num = 0;

    public ArrayUserCard(Context context, int resource, List<UserCard> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("ResourceType")
    public View getView(int position, View convertView, ViewGroup parentGroup) {

        UserCard userCard = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parentGroup, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.name);
        System.out.println(userName);
        //userName.setText("_");


        ImageView userImage = (ImageView) convertView.findViewById(R.id.image);


        userImage.setImageResource(R.mipmap.image + num);


        return convertView;
    }

    public void setNum() {
        this.num++;
    }
}
