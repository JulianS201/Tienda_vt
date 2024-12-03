/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.services.impl;

import com.tienda.domain.Usuario;
import com.tienda.services.CorreoService;
import com.tienda.services.FirebaseStorageService;
import com.tienda.services.RegistroService;
import com.tienda.services.UsuarioService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author segur
 */
@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Model crear(Model model, Usuario usuario) throws MessagingException {
        String titulo;
        String mensaje;

        if (!usuarioService
                .existeUsuarioPorUsernameOCorreo(
                        usuario.getUsername(), usuario.getCorreo())) {
            // No existe el usuario... se va a enviar un correo...
            usuario.setActivo(false);
            usuario.setPassword(demeClave());
            usuarioService.save(usuario, false);
            enviarCorreo(usuario);
            titulo = mensajes.getMessage("registro.activar", null, Locale.getDefault());
            mensaje = mensajes.getMessage("registro.mensaje.activacion.ok", null, Locale.getDefault());
            mensaje = String.format(mensaje, usuario.getCorreo());
        } else {
            // SI existe el usuario... se le notifica al usuario...
            titulo = mensajes.getMessage("registro.activar.error", null, Locale.getDefault());
            mensaje = mensajes.getMessage("registro.mensaje.usuario.o.correo", null, Locale.getDefault());
            mensaje = String.format(mensaje,
                    usuario.getUsername(),
                    usuario.getCorreo());
        }

        model.addAttribute("titulo", titulo);
        model.addAttribute("mensaje", mensaje);

        return model;

    }

    @Override
    public Model activar(Model model, String username, String password) {
        Usuario usuario=usuarioService.getUsuarioPorUsernameYPassword(username, password);

        if (usuario!=null) {
            // Si lo encontró...
            model.addAttribute("usuario", usuario);
        } else {
            // Si no lo encontró...
            model.addAttribute("titulo", "Error");
            model.addAttribute("mensaje", "Error activando usuario");
        }

        return model;

    }
        
   @Autowired
private FirebaseStorageService firebaseStorageService;
   
    @Override
    public void habilitar(Usuario usuario, MultipartFile imagenFile) {

    var encriptador = new BCryptPasswordEncoder();
    usuario.setPassword(encriptador.encode(usuario.getPassword()));

    if (!imagenFile.isEmpty()) {
        String ruta = firebaseStorageService
                .cargaImagen(imagenFile,
                             "usuarios/",
                             usuario.getIdUsuario());
        usuario.setRutaImagen(ruta);
    }
    usuarioService.save(usuario, true);
        
        
    }

    private String demeClave() {

        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += base.charAt((int) (Math.random() * base.length()));
        }
        return clave;
    }

    @Autowired
    private MessageSource mensajes;
    @Autowired
    private CorreoService correoService;

    private void enviarCorreo(Usuario usuario) throws MessagingException {
        String asunto = mensajes.getMessage("registro.mensaje.activacion", null, Locale.getDefault());
        String mensaje = mensajes.getMessage("registro.correo.activar", null, Locale.getDefault());
        mensaje = String.format(mensaje,
                usuario.getNombre(),
                usuario.getApellidos(),
                "localhost",
                usuario.getUsername(),
                usuario.getPassword());

        correoService.enviarCorreoHTML(usuario.getCorreo(), asunto, mensaje);
    }

}


