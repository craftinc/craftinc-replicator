/*  Craft Inc. Replicator
    Copyright (C) 2013  Paul Schulze, Maximilian Häckel

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.craftinc.replicator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages
{
    private static final String NEWLINE = "\n";

    private static final String pluginName = Plugin.instance.getDescription().getName();

    private static String makeCmd( Player player, String command, String explanation, String[] permissions, String... args )
    {
        StringBuilder sb = new StringBuilder();

        // command
        sb.append(ChatColor.YELLOW);
        sb.append(command);
        if ( args.length > 0 )
        {
            sb.append(" ");
            sb.append(ChatColor.BLUE);
            for ( int i = 0; i < args.length; i++ )
            {
                String s = args[i];
                sb.append(s);
                if ( i != args.length - 1 )
                {
                    sb.append(" ");
                }
            }
        }

        sb.append(ChatColor.YELLOW);
        sb.append(": ");
        sb.append(ChatColor.WHITE);
        sb.append(explanation);
        sb.append(NEWLINE);

        return sb.toString();
    }

    public static String helpGeneral(Player player) =
            ChatColor.GREEN + pluginName + " - Usage:" + NEWLINE +
            makeCmd("help", "shows this help", null) +
            makeCmd("adduser | deluser",
                    "Add or remove a player's right to use the replicator in front of you or the replicator given by \"id\".",
                    null, "<player>", "[id]") +
            makeCmd("addowner | delowner",
                    "Add or remove a player's right to use AND add or remove other users and owners to the replicator in front of you or the replicator given by \"id\".",
                    null, "<player>", "[id]") +
            makeCmd("checkversion", "Checks for a newer version.", new String[]{"craftinc.replicator.update"});

    public static String borderCreationSuccessful
            = ChatColor.YELLOW + "New border was set " +
              ChatColor.GREEN + "successfully" +
              ChatColor.YELLOW + "!";

    public static String commandIssuedByNonPlayer
            = ChatColor.RED + "Only a player can use " + pluginName + " commands!";

    public static String noPermissionUse =
            ChatColor.RED + "Sorry, you don't have permission to use this replicator. Ask .... TODO"; //TODO

    public static String noPermissionCheckversion =
            ChatColor.RED + "Sorry, you don't have permission to check for new versions.";

    public static String updateMessage( String newVersion, String curVersion )
    {
        return ChatColor.RED + pluginName + ": New version available!" + NEWLINE +
               ChatColor.YELLOW + "Current version: " + ChatColor.WHITE + curVersion + NEWLINE +
               ChatColor.YELLOW + "New version: " + ChatColor.WHITE + newVersion + NEWLINE +
               ChatColor.YELLOW + "Please visit:" + NEWLINE +
               ChatColor.AQUA + "http://dev.bukkit.org/server-mods/craftinc-replicator" + NEWLINE +
               ChatColor.YELLOW + "to get the latest version!";
    }

    public static String noUpdateAvailable =
            ChatColor.YELLOW + "No updates available.";
}
