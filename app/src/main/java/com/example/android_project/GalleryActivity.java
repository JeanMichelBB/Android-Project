package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Surface;
import android.widget.ListView;

public class GalleryActivity extends AppCompatActivity {
    String filmList[]= {"The Shawshank Redemption", "The Godfather", "The Dark Knight", "The Godfather Part II", "12 Angry Men", "Schindler's List", "The Lord of the Rings: The Return of the King", "Pulp Fiction", "The Lord of the Rings: The Fellowship of the Ring", "Forrest Gump"};
    int filmImages[] = {R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault , R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault, R.drawable.posterdefault};

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        listView = findViewById(R.id.listView);
        CustomBaseAdapeter customBaseAdapeter = new CustomBaseAdapeter(getApplicationContext(), filmList, filmImages);
        listView.setAdapter(customBaseAdapeter);
    }

}