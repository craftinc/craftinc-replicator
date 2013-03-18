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

    private Location center;

    public Replicator(String firstOwner, Location spawn, Location center) {
        this.owners = new ArrayList<String>();
        this.users = new ArrayList<String>();
        this.owners.add(firstOwner);
        this.center = center;
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

    public static ArrayList<Location> getReplicators(Location currentBlock){
        ArrayList<Location> replicators = new ArrayList<Location>();
        ArrayList<Location> centers = getCenters(currentBlock);
        for(Location center:centers){
            if(isValid(center)){
                replicators.add(center);
            }
        }
        return replicators;
    }

    private static Material[][][] getPattern(Location center){
        if(center.getBlock().getRelative(BlockFace.NORTH).getType().equals(Material.AIR)) return Pattern.getNorth();
        if(center.getBlock().getRelative(BlockFace.SOUTH).getType().equals(Material.AIR)) return Pattern.getSouth();
        if(center.getBlock().getRelative(BlockFace.WEST).getType().equals(Material.AIR)) return Pattern.getWest();
        if(center.getBlock().getRelative(BlockFace.EAST).getType().equals(Material.AIR)) return Pattern.getEast();
        return null;
    }

    private static boolean isValid(Location center){
        Material[][][] pattern = getPattern(center);
        for(int x=0;x<=2;x++){
            for(int y=0;y<=2;y++){
                for(int z=0;z<=2;z++){
                    if((pattern[x][y][z]!=center.getBlock().getRelative(x-1,y-1,z-1).getType())&&((pattern[x][y][z]!=null))){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static ArrayList<Location> getCenters(Location currentBlock){
        ArrayList<Location> centers = new ArrayList<Location>();
        Location nextBlock;
        for(int x=-1;x<=1;x++){
            for(int y=-1;y<=1;y++){
                for(int z=-1;z<=1;z++){
                    nextBlock = currentBlock.getBlock().getRelative(x,y,z).getLocation();
                    if(nextBlock.getBlock().getType().equals(Pattern.getCenter())) centers.add(nextBlock);
                }
            }
        }
        return centers;
    }

}
