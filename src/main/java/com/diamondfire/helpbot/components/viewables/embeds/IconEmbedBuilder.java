package com.diamondfire.helpbot.components.viewables.embeds;

import com.diamondfire.helpbot.components.codedatabase.db.datatypes.DisplayIconData;
import com.diamondfire.helpbot.components.codedatabase.db.datatypes.SimpleData;
import com.diamondfire.helpbot.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;

public class IconEmbedBuilder extends DataEmbedBuilder {

    @Override
    protected EmbedBuilder buildDataEmbed(SimpleData data) {

        EmbedBuilder builder = new EmbedBuilder();
        generateInfo(data, builder);

        return builder;


    }

    protected void generateInfo(SimpleData data, EmbedBuilder builder) {
        DisplayIconData icon = data.getItem();
        if (icon.getAdditionalInfo().size() != 0) {
            StringBuilder additionalInfo = new StringBuilder();

            // Contains arrays of strings, so loop through those.
            for (JsonElement current : icon.getAdditionalInfo()) {
                String mainAdditional = current.getAsJsonArray().get(0).getAsString();

                // Request combines stuff like "ite-rations" to "iterations".
                boolean requestCombine = mainAdditional.endsWith("-");

                mainAdditional = requestCombine ? mainAdditional.substring(0, mainAdditional.length() - 1) : (mainAdditional + " ");

                // Prints the first part of the additional section
                additionalInfo.append("\n> " + mainAdditional);

                // Generate the rest of the additional info (skip the first index)
                JsonArray array = current.getAsJsonArray();

                for (int i = 1; i < array.size(); i++) {
                    if (requestCombine) {
                        requestCombine = false;
                        additionalInfo.append(array.get(i).getAsString());
                        continue;
                    }
                    additionalInfo.append(" " + array.get(i).getAsString());

                }

            }

            builder.addField("Additional Info", MarkdownSanitizer.escape(additionalInfo.toString()), false);
        }

        if (icon.getWorksWith().length != 0) {
            builder.addField("Works With", StringUtil.listView(icon.getWorksWith(), ">", true), false);
        }

        if (icon.getExample().length != 0) {
            builder.addField("Examples", StringUtil.listView(icon.getExample(), "", true), false);
        }
        if (icon.isCancellable()) {
            builder.addField("\uD83D\uDEAB Cancellable", "", false);

        }
        if (icon.isCancelledAutomatically()) {
            builder.addField("\uD83D\uDEAB Cancels Automatically", "", false);

        }
        if (icon.mobsOnly()) {
            builder.addField("Mobs Only", "", false);
        }

    }


}
