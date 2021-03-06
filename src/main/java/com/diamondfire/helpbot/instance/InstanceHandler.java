package com.diamondfire.helpbot.instance;

import com.diamondfire.helpbot.components.codedatabase.AutoRefreshDBTask;
import com.diamondfire.helpbot.components.codedatabase.changelog.CodeDifferenceHandler;
import com.diamondfire.helpbot.components.codedatabase.db.CodeDatabase;

import javax.security.auth.login.LoginException;
import java.util.stream.Collectors;

public class InstanceHandler {

    public void startup() throws LoginException, InterruptedException {
        BotInstance.start();
        CodeDatabase.initialize();
        CodeDifferenceHandler.refresh();
        AutoRefreshDBTask.initialize();
    }
}
