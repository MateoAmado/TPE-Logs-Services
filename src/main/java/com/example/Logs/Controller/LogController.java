package com.example.Logs.Controller;



import com.example.Logs.Model.Log;
import com.example.Logs.Service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LogService logsService;

    @GetMapping
    public ResponseEntity<List<Log>> listarLogs(){
        List<Log> logs = logsService.obtenerLogs();
        if(logs==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo log", description = "Crea un nuevo log en el sistema")
    @PostMapping
    public ResponseEntity<Log> guardarLog(@RequestBody Log log){
       logsService.guardar_log(log);
       return new ResponseEntity<>(log, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarLog(@PathVariable String id ){
        logsService.eliminar_log(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }



}
