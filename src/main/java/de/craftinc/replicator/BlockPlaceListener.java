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

import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;

public class BlockPlaceListener implements Listener {

    public void onBlockPlaced(BlockPlaceEvent event){
        ArrayList<Location> replicators = Replicator.getReplicators(event.getBlockPlaced().getLocation());
        if(!replicators.isEmpty()){
            for(Location loc:replicators){
                Replicator rep = Replicator.getOrCreate(loc,event.getPlayer().getName());
                if(rep!=null){
                    event.getPlayer().sendMessage(Messages.newReplicator(rep));
                }
            }
        }
    }
}
