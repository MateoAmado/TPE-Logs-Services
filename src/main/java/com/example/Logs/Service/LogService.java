package com.example.Logs.Service;

import com.example.Logs.Model.Log;
import com.example.Logs.Repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogService {

    @Autowired
    @Qualifier("mongoLogsRepository")
    private LogsRepository logsRepository;

    public void guardar_log(Log log) {
        log.setFecha(LocalDate.now());
        this.getLog_repository().save(log);
    }

    public void eliminar_log(String id) {
        this.getLog_repository().deleteById(id);
    }

    private LogsRepository getLog_repository() {
        return logsRepository;
    }

    public List<Log> obtenerLogs() {
       List<Log> logs = this.logsRepository.findAll();
        if(!logs.isEmpty()) {
            return logs;
        }
        else
            return null;
    }

}
