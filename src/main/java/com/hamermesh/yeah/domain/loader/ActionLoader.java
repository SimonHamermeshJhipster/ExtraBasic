package com.hamermesh.yeah.domain.loader;

import com.hamermesh.yeah.domain.Action;
import com.hamermesh.yeah.repository.ActionRepository;
import com.hamermesh.yeah.repository.RecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by SIMONTHEPIMON on 3/20/2017.
 */

@Component
@Order(value = 1)
public class ActionLoader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(RecordLoader.class);

    private ActionRepository actionRepository;

    public ActionLoader(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void run(String... var1) throws Exception {
        recordRepository.findAll().forEach(record -> {
                        for (int i = 0; i < 3; i++) {
                            Action action = new Action();
                            action.setName(ActionName.values()[i % ActionName.values().length].name());
                            action.setRecord(record);
                            logger.debug("Action Created for Record {} : {}",
                                (record.getId() + ":" + record.getType()), actionRepository.save(action));
                        }
        });
    }

    private enum ActionName {
        TRANSFER,
        COMPOUND,
        ROUNDHOUSE,
        JACKSON,
        SPOOKY_AT_A_DISTANCE
    }
}
