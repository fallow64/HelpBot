package com.diamondfire.helpbot.command.impl.other;

import com.diamondfire.helpbot.command.arguments.Argument;
import com.diamondfire.helpbot.command.arguments.NoArg;
import com.diamondfire.helpbot.command.impl.Command;
import com.diamondfire.helpbot.command.impl.CommandCategory;
import com.diamondfire.helpbot.command.permissions.Permission;
import com.diamondfire.helpbot.components.codedatabase.changelog.CodeDifferenceHandler;
import com.diamondfire.helpbot.components.codedatabase.db.CodeDatabase;
import com.diamondfire.helpbot.components.externalfile.ExternalFile;
import com.diamondfire.helpbot.events.CommandEvent;
import com.diamondfire.helpbot.util.StringUtil;
import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;
import java.util.LinkedHashMap;

public class InfoCommand extends Command {

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Get info on the current active code database";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.CODE_BLOCK;
    }

    @Override
    public Argument getArgument() {
        return new NoArg();
    }

    @Override
    public Permission getPermission() {
        return Permission.USER;
    }

    @Override
    public void run(CommandEvent event) {
        LinkedHashMap<String, Integer> dataStats = new LinkedHashMap<>();
        EmbedBuilder builder = new EmbedBuilder();

        dataStats.put("CodeBlocks", CodeDatabase.getCodeBlocks().size());
        dataStats.put("Actions", CodeDatabase.getActions().size());
        dataStats.put("Sounds", CodeDatabase.getSounds().size());
        dataStats.put("Particles", CodeDatabase.getParticles().size());
        dataStats.put("Potions", CodeDatabase.getPotions().size());
        dataStats.put("Game Value", CodeDatabase.getGameValues().size());
        dataStats.put("Legacy Actions", CodeDatabase.getDeprecatedActions().size());
        dataStats.put("Legacy Game Values", CodeDatabase.getDeprecatedGameValues().size());

        builder.addField("Current Database Stats:", String.format("```asciidoc\n%s```", StringUtil.asciidocStyle(dataStats)), true);
        builder.addField("What's New on Beta?", String.format("```%s```", StringUtil.fieldSafe(CodeDifferenceHandler.getDifferences())), true);
        builder.setFooter("Database Last Updated");
        builder.setDescription("The database is updated automatically every 24h.");
        builder.setTimestamp(Instant.ofEpochMilli(ExternalFile.DB.getFile().lastModified()));
        event.getChannel().sendMessage(builder.build()).queue();

    }
}
