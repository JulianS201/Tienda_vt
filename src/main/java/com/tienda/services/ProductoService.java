package com.tienda.services;


import com.tienda.domain.Producto;
import java.util.List;

public interface ProductoService {
    // Se obtiene una lista de objetos Producto que son los registros
    // de la tabla producto.
    // Pueden ser todos o solo los activos...
    public List<Producto> getProductos(boolean activos);

    // Se obtiene una Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);


    public void save(Producto producto);

    // Se elimina el producto que tiene el id pasado por parametro
    public void delete(Producto producto);

}