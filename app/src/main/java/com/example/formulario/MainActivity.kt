package com.example.formulario

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var profileImage: ImageView

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vistas
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        profileImage = findViewById(R.id.profileImage)

        //validacion de imagen
        findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            submitForm()
        }
    }

    //validacion y envio de formulario
    private fun submitForm() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        //validaciones
        if (name.isEmpty()) {

            etName.error = "Nombre requerido"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Correo inválido"
            return
        }

        if (phone.isEmpty() || phone.length != 10 || !phone.all { it.isDigit() }) {
            etPhone.error = "Número de teléfono inválido"
            return
        }

        //imagen cuando se envia el formualrio
        profileImage.setImageResource(R.drawable.perfil) // Imagen nueva desde los recursos

        //mensaje de éxito
        Toast.makeText(this, "Formulario enviado con éxito", Toast.LENGTH_SHORT).show()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
