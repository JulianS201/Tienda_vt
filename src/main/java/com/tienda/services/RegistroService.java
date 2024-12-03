/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.services;

import com.tienda.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author segur
 */
public interface RegistroService {

    public Model crear(Model model, Usuario usuario) throws MessagingException;

    public Model activar(Model model, String username, String password);

    public void habilitar(Usuario usuario, MultipartFile imagenFile);

}
