package com.tienda.services;


import com.tienda.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    // Se obtiene una lista de objetos Categoria que son los registros
    // de la tabla categoria.
    // Pueden ser todos o solo los activos...
    public List<Categoria> getCategorias(boolean activos);

    // Se obtiene una Categoria, a partir del id de un categoria
    public Categoria getCategoria(Categoria categoria);


    public void save(Categoria categoria);

    // Se elimina el categoria que tiene el id pasado por parametro
    public void delete(Categoria categoria);

}
