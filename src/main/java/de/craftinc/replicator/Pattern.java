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

import org.bukkit.Material;

public class Pattern {
    private static final Material[][][] repPattern = {{{Material.OBSIDIAN,Material.OBSIDIAN,null},{Material.MOSSY_COBBLESTONE,Material.OBSIDIAN,null},{Material.GLOWSTONE,Material.OBSIDIAN,null}},
                                        {{Material.GOLD_BLOCK,Material.DIAMOND_BLOCK,Material.OBSIDIAN},{Material.AIR,Material.JACK_O_LANTERN,Material.OBSIDIAN},{Material.MOSSY_COBBLESTONE,Material.OBSIDIAN,null}},
                                        {{Material.OBSIDIAN,Material.OBSIDIAN,null},{Material.MOSSY_COBBLESTONE,Material.OBSIDIAN,null},{Material.GLOWSTONE,Material.OBSIDIAN,null}}};
    //direction north (replicator direction)

    public static Material getCenter(){
        return repPattern[1][1][1];
    }

    public static Material[][][] getNorth(){
        return repPattern;
    }

    public static Material[][][] getSouth(){
        Material[][][] newPattern = new Material[3][3][3];
        for(int x=0;x<=2;x++){
            for(int y=0;y<=2;y++){
                newPattern[x][y][0] = repPattern[x][y][2];
                newPattern[x][y][1] = repPattern[x][y][1];
                newPattern[x][y][2] = repPattern[x][y][0];
            }
        }
        return newPattern;
    }

    public static Material[][][] getWest(){
        Material[][][] newPattern = new Material[3][3][3];
        for(int x=0;x<=2;x++){
            for(int y=0;y<=2;y++){
              for(int z=0;z<=2;z++){
                newPattern[x][y][z] = repPattern[z][y][x];
              }
            }
        }
        return newPattern;
    }

    public static Material[][][] getEast(){
        Material[][][] westPattern = getWest();
        Material[][][] newPattern = new Material[3][3][3];
        for(int y=0;y<=2;y++){
            for(int z=0;z<=2;z++){
                newPattern[0][y][z] = westPattern[2][y][z];
                newPattern[1][y][z] = westPattern[1][y][z];
                newPattern[2][y][z] = westPattern[0][y][z];
            }
        }
        return newPattern;
    }
}
