package com.tienda;

import com.tienda.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mycontroller {

    @GetMapping("/pagina2")
    public String getPagina2() {
        return "pagina2";  // 'pagina2' debe coincidir con el nombre del archivo en templates (sin .html)
    }
}