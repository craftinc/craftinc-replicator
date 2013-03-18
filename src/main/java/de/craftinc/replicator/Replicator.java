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
import org.bukkit.block.BlockFace;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: starback
 * Date: 18.03.13
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public class Replicator {

    private ArrayList<String> owners;
    private ArrayList<String> users;

    private Location spawn;
    private Location pumpkin;

    public Replicator(String firstOwner, Location spawn, Location pumpkin) {
        this.owners = new ArrayList<String>();
        this.users = new ArrayList<String>();
        this.owners.add(firstOwner);
        this.spawn = spawn;
        this.pumpkin = pumpkin;
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public void addOwner(String owner) {
        this.users.add(owner);
    }

    public boolean rmUser(String user) {
        if(this.users.remove(user)) return true;
        else return false;
    }

    public boolean rmOwner(String owner) {
        if(this.owners.remove(owner)) return true;
        else return false;
    }

    private BlockFace getDirection() {

        if(this.pumpkin.getBlock().get)

        if(this.spawn.getBlock().getRelative(BlockFace.EAST).equals(this.pumpkin.getBlock())) return BlockFace.EAST;
        else if(this.spawn.getBlock().getRelative(BlockFace.SOUTH).equals(this.pumpkin.getBlock())) return BlockFace.SOUTH;
        else if(this.spawn.getBlock().getRelative(BlockFace.WEST).equals(this.pumpkin.getBlock())) return BlockFace.WEST;
        else if(this.spawn.getBlock().getRelative(BlockFace.NORTH).equals(this.pumpkin.getBlock())) return BlockFace.NORTH;
        else return null;
    }

    private boolean isValid(){

        return false;
    }

    private ArrayList<Location> getPumpkin(Location currentBlock){
        ArrayList<Location> pumpkins = new ArrayList<Location>();
        Location nextBlock;
        for(int x=-1;x<=1;x++){
            for(int y=-1;y<=1;y++){
                for(int z=-1;z<=1;z++){
                    nextBlock = currentBlock.getBlock().getRelative(x,y,z).getLocation();
                    if(nextBlock.getBlock().getType().equals(Material.JACK_O_LANTERN)) pumpkins.add(nextBlock);
                }
            }
        }
        return pumpkins;
    }


}
