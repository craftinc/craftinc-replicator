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
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

@SuppressWarnings("UnusedDeclaration")
public class PlayerInteractEntityListener implements Listener
{
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteractEntity( PlayerInteractEntityEvent event )
    {
        // do nothing if entity type is not an item frame
        if ( event.getRightClicked().getType() != EntityType.ITEM_FRAME )
        {
            return;
        }

        // get the actual entity
        ItemFrame itemFrame = null;
        Integer entityId = event.getRightClicked().getEntityId();
        for ( Entity e : event.getPlayer().getWorld().getEntitiesByClass(ItemFrame.class) )
        {
            if ( e.getEntityId() == entityId )
            {
                itemFrame = (ItemFrame) e;
                break;
            }
        }
        //Replicator.mochaccino.sendMessage("ItemFrame found "+itemFrame.getEntityId());
        // if no item frame was found: return! (strange and should NEVER happen as long as the world is not upside down!)
        if ( itemFrame == null )
        {
            return;
        }

        // do nothing if item frame is empty
        if (itemFrame.getItem().getType().equals(Material.AIR))
        {
            return;
        }

        // find replicator centers which are suitable for this item frame
        //Replicator.mochaccino.sendMessage(itemFrame.getAttachedFace() + "");
        ArrayList<Replicator> replicators = Replicator.getUsableReplicators(event.getRightClicked().getLocation().getBlock().getRelative(itemFrame.getAttachedFace()).getLocation(),event.getPlayer().getName());

        // do nothing if no replicator centers have been found
        if ( replicators.isEmpty() )
        {
            //Replicator.mochaccino.sendMessage("No replicator found.");
            return;
        }

        // get the replicators from database for these centers
        for ( Replicator replicator : replicators )
        {

            // from here on we have a valid replicator and a valid item
            // that's why we cancel the event
            event.setCancelled(true);

            // and spawn the items at the center location of the replicator
            ItemStack stack = itemFrame.getItem();
            stack.setAmount(64);
            event.getPlayer().getWorld().dropItemNaturally(
                    replicator.getCenter().getBlock().getRelative(Replicator.getDirection(replicator.getCenter())).getLocation(),
                    stack);
        }
    }
}
