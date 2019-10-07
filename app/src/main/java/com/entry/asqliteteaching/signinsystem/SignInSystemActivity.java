package com.entry.asqliteteaching.signinsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.entry.asqliteteaching.R;
import com.entry.asqliteteaching.signinsystem.classes.Artical;
import com.entry.asqliteteaching.signinsystem.classes.User;
import com.entry.asqliteteaching.signinsystem.utils.SQDao;

import java.util.List;

public class SignInSystemActivity extends AppCompatActivity {

    private SQDao sqDao;
    private TextView textView;
    private Button load;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        sqDao = new SQDao(this);
        if (sqDao.isDataExists(SQDao.USER)){
            sqDao.iniTable(SQDao.USER);
        }
        if (sqDao.isDataExists(SQDao.ARTICLE)){
            sqDao.iniTable(SQDao.ARTICLE);
        }
//        sqDao.updataDatabase();

        iniViews();
    }

    private void iniViews(){
        save     = findViewById(R.id.save);
        load     = findViewById(R.id.load);
        textView = findViewById(R.id.show_text);

        save.setOnClickListener(myListener);
        load.setOnClickListener(myListener);
        textView.setOnClickListener(myListener);
    }

    private View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.save:
                    textView.setText(sqDao.iniTable(SQDao.ARTICLE) + "  " + sqDao.iniTable(SQDao.USER)+"");
                    break;
                case R.id.load:
//                    List<Artical> list = sqDao.getAllArticles();
//                    if (list == null)
//                        break;
//                    StringBuffer sb = new StringBuffer();
//                    for (Artical a : list){
//                        sb.append(a.toString());
//                    }
//                    textView.setText(sb.toString());


                    List<User> userlist = sqDao.getAllUsers();
                    if (userlist == null)
                        break;
                    StringBuffer usersb = new StringBuffer();
                    usersb.append(userlist.size()+"");
                    for (User user : userlist){
                        usersb.append(user.toString());
                    }
                    textView.setText(usersb.toString());
                    break;
                case R.id.show_text:

                default:

            }
        }
    };
}
