package com.example.appdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appdatabase.dao.UserDao;
import com.example.appdatabase.database.AppDatabase;
import com.example.appdatabase.entity.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        User user1 = new User();
        user1.uid = 0;
        user1.firstName = "Juan";
        user1.lastName = "Rojas";

        User user2 = new User();
        user2.uid = 1;
        user2.firstName = "David";
        user2.lastName = "Delgado";

        User user3 = new User();
        user3.uid = 2;
        user3.firstName = "Daniel";
        user3.lastName = "Latorre";
        
        User user4 = new User();
        user4.uid = 3;
        user4.firstName = "Eduardo";
        user4.lastName = "Rico";


        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TextView textViewHello = findViewById(R.id.textUser);

                db.userDao().delete(user1);
                db.userDao().delete(user2);
                db.userDao().delete(user3);
                db.userDao().delete(user4);
                db.userDao().insertAll(user1, user2, user3, user4);

                List<User> users = db.userDao().getAll();
                StringBuilder usersText = new StringBuilder();
                for (User user : users) {
                    usersText.append("User: ").append(user.uid).append(" ").append(user.firstName).append(" ").append(user.lastName).append("\n");
                }

                TextView textViewUsers = findViewById(R.id.textUser);
                textViewUsers.setText(usersText.toString());

            }
        });

    }
}