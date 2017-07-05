package ua.com.codeasylum.menulibrary;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ua.com.codeasylum.menulibrary.fragmentsForTest.Fragment_1;
import ua.com.codeasylum.menulibrary.fragmentsForTest.Fragment_2;
import ua.com.codeasylum.menulibrary.fragmentsForTest.Fragment_3;
import ua.com.codeasylum.menulibrary.fragmentsForTest.Fragment_4;
import ua.com.codeasylum.menulibrary.fragmentsForTest.Fragment_5;

public class MainActivity extends AppCompatActivity {

    MenuLibraryClass menuLibraryClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuLibraryClass = (MenuLibraryClass) findViewById(R.id.menu);
        Fragment[] fragments = new Fragment[5];
        fragments[0] = new Fragment_1();
        fragments[1] = new Fragment_2();
        fragments[2] = new Fragment_3();
        fragments[3] = new Fragment_4();
        fragments[4] = new Fragment_5();
        menuLibraryClass.setFragmentsAndFragmentManager(fragments,getSupportFragmentManager());
    }
}
