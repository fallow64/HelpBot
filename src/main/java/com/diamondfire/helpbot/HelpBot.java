package com.diamondfire.helpbot;

import com.diamondfire.helpbot.instance.InstanceHandler;

import javax.security.auth.login.LoginException;

public class HelpBot {

    public static final InstanceHandler instance = new InstanceHandler();

    public static void main(String[] args) throws LoginException, InterruptedException {
        instance.startup();
    }
}
