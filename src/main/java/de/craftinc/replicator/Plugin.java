/*  Craft Inc. Replicator
    Copyright (C) 2013  Paul Schulze, Maximilian Häckel, Moritz Kaltofen

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

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
    public static JavaPlugin instance;

    @Override
    public void onLoad()
    {
        ConfigurationSerialization.registerClass(Replicator.class);
    }

    @Override
    public void onDisable()
    {
    }

    @Override
    public void onEnable()
    {
        Plugin.instance = this;

        // load replicators
        Replicator.loadReplicators();

        // create listeners
        BlockPlaceListener blockPlaceListener = new BlockPlaceListener();
        PlayerInteractEntityListener playerInteractEntityListener = new PlayerInteractEntityListener();

        // commands
        Commands commandExecutor = new Commands();
        getCommand("replicator").setExecutor(commandExecutor);

        // register listeners
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(blockPlaceListener, this);
        pm.registerEvents(playerInteractEntityListener, this);
    }
}
