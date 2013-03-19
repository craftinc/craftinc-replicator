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

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class Commands implements CommandExecutor
{
    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args )
    {
        // Check if command comes from a player.
        if ( !( sender instanceof Player ) )
        {
            sender.sendMessage(Messages.commandIssuedByNonPlayer);
            return true;
        }

        // commands
        if ( command.getName().equalsIgnoreCase("replicate") || command.getAliases().get(0).equalsIgnoreCase("repli") )
        {
            Player player = ( (Player) sender ).getPlayer();

            // help
            if ( args.length == 0 || ( args.length > 0 && args[0].equalsIgnoreCase("help") ) )
            {
                sender.sendMessage(Messages.helpGeneral(player));
                return true;
            }

            // checkversion
            if ( args.length > 0 && args[0].equalsIgnoreCase("checkversion") )
            {
                if ( !sender.hasPermission("craftinc.replicator.update") )
                {
                    sender.sendMessage(Messages.noPermissionCheckversion);
                    return false;
                }

                if ( UpdateHelper.newVersionAvailable() )
                {
                    sender.sendMessage(
                            Messages.updateMessage(UpdateHelper.cachedLatestVersion, UpdateHelper.getCurrentVersion()));
                    return true;
                }
                else
                {
                    sender.sendMessage(Messages.noUpdateAvailable);
                    return true;
                }
            }

            // info
            if ( args.length > 0 && args[0].equalsIgnoreCase("info") )
            {
                // looking at replicator
                if ( args.length == 1 )
                {
                    // get block where the player is looking at
                    Block potentialReplicatorBlock = player.getTargetBlock(null, 100);
                    Replicator.mochaccino.sendMessage("You are looking at "+potentialReplicatorBlock.getLocation().getBlockX()+","+potentialReplicatorBlock.getLocation().getBlockY()+","+potentialReplicatorBlock.getLocation().getBlockZ());
                    // get zero or more valid replicator centers
                    ArrayList<Replicator> replicators = Replicator.getReplicators(potentialReplicatorBlock.getLocation());

                    if ( replicators.size() == 0 )
                    {
                        sender.sendMessage(Messages.noReplicatorInSight);
                        return true;
                    }
                    sender.sendMessage(Messages.info(replicators));
                    return true;
                }
                // replicator specified as argument
                else if ( args.length == 2 )
                {
                    Replicator rep = Replicator.getByName(args[1], player.getName());
                    if ( rep == null )
                    {
                        sender.sendMessage(Messages.noReplicatorWithName(args[1]));
                        return true;
                    }
                    sender.sendMessage(
                            Messages.info(new ArrayList<Replicator>(Arrays.asList(new Replicator[] { rep }))));
                    return true;
                }
            }

            // list
            if ( args.length == 1 && args[0].equalsIgnoreCase("list") )
            {
                sender.sendMessage(
                        Messages.list(Replicator.getReplicatorsByOwner(player.getName()),
                                      Replicator.getReplicatorsByUser(player.getName())));
                return true;
            }

            // addowner, delowner, adduser, deluser
            if ( args.length > 1 && ( args[0].equalsIgnoreCase("addowner") || args[0].equalsIgnoreCase("delowner") ||
                                      args[0].equalsIgnoreCase("adduser") || args[0].equalsIgnoreCase("deluser") ) )
            {
                // looking at replicator
                if ( args.length == 2 )
                {
                    // get block where the player is looking at
                    Block potentialReplicatorBlock = player.getTargetBlock(null, 100);

                    // get zero or more valid replicator centers
                    ArrayList<Replicator> replicators = Replicator
                            .getOwnReplicators(potentialReplicatorBlock.getLocation(),player.getName());

                    // no replicator in sight
                    if ( replicators.isEmpty() )
                    {
                        sender.sendMessage(Messages.noReplicatorInSight);
                        return true;
                    }
                    for ( Replicator replicator : replicators )
                    {
                        if ( args[0].equalsIgnoreCase("addowner") )
                        {
                            replicator.addOwner(args[1],player.getName());
                        }
                        else if ( args[0].equalsIgnoreCase("delowner") )
                        {
                            replicator.rmOwner(args[1],player.getName());
                        }
                        else if ( args[0].equalsIgnoreCase("adduser") )
                        {
                            replicator.addUser(args[1],player.getName());
                        }
                        else if ( args[0].equalsIgnoreCase("deluser") )
                        {
                            replicator.rmUser(args[1],player.getName());
                        }
                        sender.sendMessage(Messages.addedOwner(args[1], replicator));
                    }
                    return true;
                }
                // replicator name specified as argument
                else if ( args.length == 3 )
                {
                    Replicator replicator = Replicator.getByName(args[2], player.getName());

                    if ( replicator == null )
                    {
                        sender.sendMessage(Messages.noReplicatorWithName(args[2]));
                        return true;
                    }

                    if ( args[0].equalsIgnoreCase("addowner") )
                    {
                        replicator.addOwner(args[1],player.getName());
                    }
                    else if ( args[0].equalsIgnoreCase("delowner") )
                    {
                        replicator.rmOwner(args[1],player.getName());
                    }
                    else if ( args[0].equalsIgnoreCase("adduser") )
                    {
                        replicator.addUser(args[1],player.getName());
                    }
                    else if ( args[0].equalsIgnoreCase("deluser") )
                    {
                        replicator.rmUser(args[1],player.getName());
                    }

                    sender.sendMessage(Messages.addedOwner(player.getName(), replicator));
                    return true;
                }
            }
        }


        // if some unknown argument has been given, show help message
        sender.sendMessage(Messages.helpGeneral(( (Player) sender ).getPlayer()));
        return true;
    }
}
