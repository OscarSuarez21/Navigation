package ec.edu.tecnologicoloja.navigation;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import ec.edu.tecnologicoloja.navigation.BaseDeDatos.Persona;
import ec.edu.tecnologicoloja.navigation.BaseDeDatos.PersonaLab;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ListAdapter listItemAdapter;
    private ArrayList<Persona> listaNombres=new ArrayList<>();
    private ListView listView;
    private PersonaLab mPersonaLab;
    private Persona mPersona;
    private TextView guardar;
    private Button bguardar,blimpiar;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mPersonaLab=new PersonaLab(this);

    listView=(ListView) findViewById(R.id.list);
    guardar=(TextView) findViewById(R.id.editTextTextPersonName);
    bguardar=(Button) findViewById(R.id.buttonGuardar);
    blimpiar=(Button) findViewById(R.id.buttonLimpiar);
        bguardar.setOnClickListener(this);
        blimpiar.setOnClickListener(this);

        getAllPersonas();
       // listItemAdapter = new ListAdapter(this, listaNombres);
        listView.setAdapter(listItemAdapter);

}

     // GUARDA EN LA BASE DE DATOS

    public void insertPersonas() {
        mPersona=new Persona();
        mPersona.setNombre(guardar.getText().toString());
        mPersonaLab.addPersona(mPersona);
        guardar.setText(" ");

    }
    // CONSULTA A LA BASE DE DATOS
    public void getAllPersonas(){
        listaNombres.clear();
        listaNombres.addAll(mPersonaLab.getPersonas());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        if (v==blimpiar){
            mPersonaLab.deleteAllPersona();
            listaNombres.clear();
            //listItemAdapter.notifyDataSetChan();
        }
        if (v==bguardar){
            insertPersonas();
            getAllPersonas();
           // listItemAdapter.notifyDataSetChanged();
        }



    }
}