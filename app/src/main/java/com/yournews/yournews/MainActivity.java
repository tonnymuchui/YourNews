package com.yournews.yournews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
//    //view objects
    @BindView(R.id.buttonDialog) Button buttonDialogs;
    @BindView(R.id.listViewArtists) ListView listViewDiaries;

    List<Diary> diaries = new ArrayList<>();
    DatabaseReference databaseDiaries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        databaseDiaries = FirebaseDatabase.getInstance().getReference("diaries");

    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseDiaries.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listViewDiaries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Diary diary = diaries.get(i);
                        showUpdateDeleteDialog(diary.getDiaryId(), diary.getUName());
                        return true;
                    }
                });
                buttonDialogs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       ShowAddDialog();
                    }
                });

                diaries.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Diary diary = postSnapshot.getValue(Diary.class);
                    diaries.add(diary);
                }

                DiaryList DiaryAdapter = new DiaryList(MainActivity.this, diaries);
                listViewDiaries.setAdapter(DiaryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean updateDiary(String id, String name, String genre,String date) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("diaries").child(id);

        Diary diary = new Diary(id, name, genre,date);
        dR.setValue(diary);
        Toast.makeText(getApplicationContext(), "Diary Updated", Toast.LENGTH_LONG).show();
        return true;
    }
    private void ShowAddDialog(){
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
       LayoutInflater inflater = getLayoutInflater();
       final View dialogView = inflater.inflate(R.layout.add_diary, null);
       mbuilder.setView(dialogView);

       final  EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextNamem);
        final EditText spinnerGenre = (EditText) dialogView.findViewById(R.id.spinnerGenresm);
        final EditText editTextDate = (EditText) dialogView.findViewById(R.id.editTextDatem);
        final Button buttonCreate = (Button) dialogView.findViewById(R.id.buttonAddArtistm);
        final AlertDialog d = mbuilder.create();
        d.show();
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String diaryDesc = spinnerGenre.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {

                    String id = databaseDiaries.push().getKey();
                    Diary diary = new Diary(id, name, diaryDesc,date);
                    databaseDiaries.child(id).setValue(diary);
                    editTextName.setText("");
                    spinnerGenre.setText("");
                    editTextDate.setText("");
                    d.dismiss();
                }
            }
        });
    }

    private void showUpdateDeleteDialog(final String diaryId, String diaryName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText spinnerGenre = (EditText) dialogView.findViewById(R.id.spinnerGenres);
        final EditText editTextDate = (EditText) dialogView.findViewById(R.id.editTextDate);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateArtist);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteArtist);

        dialogBuilder.setTitle(diaryName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenre.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateDiary(diaryId, name, genre,date);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(diaryId);
                b.dismiss();
            }
        });
    }
    private boolean deleteArtist(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("diaries").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Diary Deleted", Toast.LENGTH_LONG).show();

        return true;
    }
}