package com.example.Logs.Repository;

import com.example.Logs.Model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("mongoLogsRepository")
public interface LogsRepository extends MongoRepository<Log, String> {


}
