package com.diamondfire.helpbot.command.arguments.value;


import com.diamondfire.helpbot.util.JaroWinkler;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DefinedStringArg extends ValueArgument<String> {

    final String[] options;


    public DefinedStringArg(String[] options) {
        super(null, true);
        this.options = options;
    }

    public DefinedStringArg(String[] options, boolean required) {
        super(null, required);
        this.options = options;
    }

    public DefinedStringArg(String[] options, boolean required, String displayName) {
        super(displayName, required);
        this.options = options;
    }


    @Override
    public String getValue(String msg) {
        return getClosestOption(msg);
    }

    @Override
    protected boolean validateValue(String msg) {
        return getClosestOption(msg) != null;
    }

    @Override
    public String failMessage() {
        return "Invalid argument, please choose from the following: " + String.join(", ", options);
    }

    @Override
    public String getName() {
        if (super.getName() == null) {
            String parsedOptions;

            if (options.length > 6) {
                parsedOptions = String.join("/", Arrays.copyOfRange(options, 1, 6)) + "..." + String.format("*+%s more*", options.length - 6);
            } else {
                parsedOptions = String.join("/", options);
            }

            return parsedOptions;
        }
        return super.getName();
    }

    private String getClosestOption(String args) {
        //Generate a bunch of "favorable" actions.
        Map<String, Double> possibleChoices = new HashMap<>();
        for (String option : options) {
            possibleChoices.put(option, JaroWinkler.score(args, option));
        }

        //Get the most similar action possible.
        Map.Entry<String, Double> closestAction = possibleChoices.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .orElse(null);

        return closestAction.getValue() >= 0.85 ? closestAction.getKey() : null;
    }
}
