package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemplosqlite.bdd.BDHelper;

public class MainActivity extends AppCompatActivity {
    EditText txt_id, txt_nombre, txt_apellido, txt_direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id=findViewById(R.id.txt_id);
        txt_nombre=findViewById(R.id.txt_nombre);
        txt_apellido=findViewById(R.id.txt_apellido);
        txt_direccion=findViewById(R.id.txt_direccion);
    }

    public void registrar(View view){
        BDHelper admin = new BDHelper(this,"Registro.db",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = txt_id.getText().toString();
        String nombre = txt_nombre.getText().toString();
        String apellido = txt_apellido.getText().toString();
        String direccion = txt_direccion.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !direccion.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("usu_id",id);
            registro.put("usu_nombre",nombre);
            registro.put("usu_apellido",apellido);
            registro.put("usu_direccion",direccion);
            bd.insert("t_usuario", null,registro);
            Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "PORFAVOR INGRESAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarRegistro(View view) {
        BDHelper admin = new BDHelper(this, "Registro.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String id = txt_id.getText().toString();
        String tabla = "t_usuario";
        String campoID = "usu_id";
        String whereClause = campoID + " = ?";
        String[] whereArgs = { id };

        int registrosEliminados = bd.delete(tabla, whereClause, whereArgs);
        if (registrosEliminados > 0) {
            Toast.makeText(this, "Registro eliminado exitosamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontró el registro a eliminar", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }







}