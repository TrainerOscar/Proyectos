package com.example.login.models

import androidx.lifecycle.ViewModel
import com.example.login.models.Producto
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow




class ProductoViewModel:   ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos : StateFlow<List<Producto>> = _productos

    fun agregarProducto(id: Int, nombre: String, precio: Double, cantidad: Int) {
        val nuevoProducto = Producto(
            id = id,
            nombre = nombre,
            precio = precio,
            cantidad = cantidad
        )
        _productos.value = _productos.value + nuevoProducto
    }

    fun mostrarProductos() : List<Producto> {
        return _productos.value
    }


}