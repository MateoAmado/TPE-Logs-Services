package com.example.Logs.Controller;



import com.example.Logs.Model.Log;
import com.example.Logs.Service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Lista todos los Logs", description = "Muestra todos los logs existentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs encontrados exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró ningun log"),
    })
    @GetMapping
    public ResponseEntity<List<Log>> listarLogs(){
        List<Log> logs = logsService.obtenerLogs();
        if(logs==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @Operation(summary = "Crea un nuevo log", description = "Crea un nuevo log en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se guardó exitosamente el Log"),
           })
    @PostMapping
    public ResponseEntity<Log> guardarLog(@RequestBody Log log){
       logsService.guardar_log(log);
       return new ResponseEntity<>(log, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar un Log", description = "Elimina un log por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se eliminó exitosamente el log"),
           })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarLog(@PathVariable String id ){
        logsService.eliminar_log(id);
        return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }



}
