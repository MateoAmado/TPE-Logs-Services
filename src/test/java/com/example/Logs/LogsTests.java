package com.example.Logs;

import com.example.Logs.Model.Log;
import com.example.Logs.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UsuarioLoginDTO;
import dto.UsuarioRegistroDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogsTests {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private RestTemplate restTemplate;

        private String AuthToken;

        private int idUsuario;

    @BeforeAll
    public void crearCuentaYAutenticar() {
        if (AuthToken == null) {
            try {
                // Crear nuevo usuario
                UsuarioRegistroDTO nuevoUsuario = new UsuarioRegistroDTO("NOCOMPILAAAAA", "gonzah@gmail.com", "ADMIN", "gonzalo", "hellers", 2, 69L, 165456789L);
                ObjectMapper objectMapper = new ObjectMapper();
                String registroJson = objectMapper.writeValueAsString(nuevoUsuario);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> requestEntity = new HttpEntity<>(registroJson, headers);
                String registroUrl = "http://localhost:8090/auth/registro";
                UsuarioDTO u = restTemplate.postForObject(registroUrl, requestEntity, UsuarioDTO.class);
                idUsuario = u.getId();

                // Autenticar usuario
                UsuarioLoginDTO loginUsuario = new UsuarioLoginDTO("gonzah@gmail.com", "NOCOMPILAAAAA");
                loginUsuario.setRol("ADMIN");
                String loginJson = objectMapper.writeValueAsString(loginUsuario);

                HttpHeaders loginHeaders = new HttpHeaders();
                loginHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> loginRequestEntity = new HttpEntity<>(loginJson, loginHeaders);
                String authUrl = "http://localhost:8090/auth/login";
                AuthToken = restTemplate.postForObject(authUrl, loginRequestEntity, String.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void CrearLogTest() throws Exception{
        System.out.println(AuthToken);
        Log log = new Log();
        log.setId("awsd");
        log.setDescripcion("Log De Prueba");
        ObjectMapper objectMapper = new ObjectMapper();
        String logJson = objectMapper.writeValueAsString(log);
        mockMvc.perform(MockMvcRequestBuilders.post("/logs")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthToken)
                .content(logJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void eliminarLogsTest(){
       try {
           mockMvc.perform(MockMvcRequestBuilders.delete("/logs/{id}", "awsd")
                           .header(HttpHeaders.AUTHORIZATION, "Bearer " + AuthToken)
                           .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isNoContent());
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       }
}
