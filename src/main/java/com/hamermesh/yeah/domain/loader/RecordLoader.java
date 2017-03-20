package com.hamermesh.yeah.domain.loader;

import com.hamermesh.yeah.domain.Record;
import com.hamermesh.yeah.domain.User;
import com.hamermesh.yeah.repository.RecordRepository;
import com.hamermesh.yeah.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by SIMONTHEPIMON on 3/20/2017.
 */

@Component
public class RecordLoader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(RecordLoader.class);

    private UserRepository userRepository;

    public RecordLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void run(String... var1) throws Exception {
        User user = userRepository.findOneByLogin("admin").get();

        for(int i = 0; i<1000; i++) {
            Record record = new Record();
            record.setUser(user);
            record.setType(RecordType.values()[i % RecordType.values().length].name());
            logger.debug("Record Created: {}", recordRepository.save(record));
        }
    }

    private enum RecordType {
        DEBIT,
        CREDIT,
        CRIMINAL,
        VINYL,
        WORLD
    }
}
