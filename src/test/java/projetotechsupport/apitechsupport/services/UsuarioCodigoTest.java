package projetotechsupport.apitechsupport.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import projetotechsupport.apitechsupport.ApiTechsupportApplication;
import projetotechsupport.apitechsupport.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiTechsupportApplication.class)
public class UsuarioCodigoTest {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void teste() {
        String codigoDoUsuario = usuarioService.getCodigoDoUsuario();
    }

}
