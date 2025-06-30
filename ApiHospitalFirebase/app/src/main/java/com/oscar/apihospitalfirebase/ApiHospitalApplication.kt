    package com.oscar.apihospitalfirebase

    import android.app.Application
    import com.google.firebase.FirebaseApp
    import com.google.firebase.firestore.FirebaseFirestore
    import android.util.Log

    class ApiHospitalApplication : Application() {
        override fun onCreate() {
            super.onCreate()
            try {
                FirebaseApp.initializeApp(this)
                FirebaseFirestore.getInstance()
                Log.d("ApiHospitalApp", "Firebase inicializado correctamente.")
            } catch (e: Exception) {
                Log.e("ApiHospitalApp", "Error al inicializar Firebase", e)
            }
        }
    }
