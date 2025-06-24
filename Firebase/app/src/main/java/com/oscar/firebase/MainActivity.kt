package com.oscar.firebase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ActionCodeSettings
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    private lateinit var editText: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonBorrar: Button
    private lateinit var buttonVerDatos: Button
    private lateinit var recyclerDatos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {


        fun enviarEnlaceDeInicioSesion(email: String) {
            val actionCodeSettings = ActionCodeSettings.newBuilder()
                .setUrl("https://tudominio.firebaseapp.com") // usa la misma que en Firebase Console
                .setHandleCodeInApp(true)
                .setAndroidPackageName(
                    "com.oscar.firebase", true, "21"
                )
                .build()

            auth.sendSignInLinkToEmail(email, actionCodeSettings)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Enlace enviado a $email", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        auth = FirebaseAuth.getInstance()

// Verificar si ya está autenticado
        if (auth.currentUser == null) {
            enviarEnlaceDeInicioSesion("usuario@ejemplo.com") // puedes usar un EditText para pedirlo
        } else {
            Toast.makeText(this, "Bienvenido: ${auth.currentUser!!.email}", Toast.LENGTH_LONG).show()
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentData = intent?.data
        if (intentData != null && auth.isSignInWithEmailLink(intentData.toString())) {
            val email = "usuario@ejemplo.com" // Guarda esto en preferencias o recíbelo por EditText
            auth.signInWithEmailLink(email, intentData.toString())
                .addOnSuccessListener {
                    Toast.makeText(this, "Autenticado como ${auth.currentUser?.email}", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Falló el inicio de sesión", Toast.LENGTH_SHORT).show()
                }
        }


        editText = findViewById(R.id.editTextData)
        buttonGuardar = findViewById(R.id.buttonGuardar)
        buttonBorrar = findViewById(R.id.buttonBorrar)
        buttonVerDatos = findViewById(R.id.buttonVerDatos)
        recyclerDatos = findViewById(R.id.recyclerDatos)

        recyclerDatos.layoutManager = LinearLayoutManager(this)

        buttonGuardar.setOnClickListener {
            val texto = editText.text.toString()

            if (texto.isNotEmpty()) {
                guardarEnFirebase(texto)
            } else {
                Toast.makeText(this, "Por favor escribe algo", Toast.LENGTH_SHORT).show()
            }
        }

        buttonBorrar.setOnClickListener {
            val referencia = FirebaseDatabase.getInstance().getReference("datos")
            referencia.removeValue().addOnSuccessListener {
                Toast.makeText(this, "Todos los datos han sido eliminados", Toast.LENGTH_SHORT).show()
                recyclerDatos.adapter = null
            }.addOnFailureListener {
                Toast.makeText(this, "Error al borrar: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        buttonVerDatos.setOnClickListener {
            val referencia = FirebaseDatabase.getInstance().getReference("datos")
            referencia.get().addOnSuccessListener { snapshot ->
                val listaDatos = mutableListOf<String>()
                for (datoSnapshot in snapshot.children) {
                    val valor = datoSnapshot.getValue(String::class.java)
                    valor?.let { listaDatos.add(it) }
                }
                recyclerDatos.adapter = DataAdapter(listaDatos)
            }.addOnFailureListener {
                Toast.makeText(this, "Error al cargar datos: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun guardarEnFirebase(texto: String) {
        val database = FirebaseDatabase.getInstance()
        val uid = auth.currentUser?.uid ?: return
        val referencia = database.getReference("usuarios").child(uid).child("datos")
        val id = UUID.randomUUID().toString()

        referencia.child(id).setValue(texto).addOnSuccessListener {
            Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_SHORT).show()
            editText.text.clear()
        }.addOnFailureListener {
            Toast.makeText(this, "Error al guardar: ${it.message}", Toast.LENGTH_LONG).show()
        }
    }
}


