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

import java.util.Arrays;
import java.util.HashSet;

// FIXME: unused at the moment because the HashSet<Byte> seems not to be working as it is expected here...
public class BlockUtil
{
    /**
     * List of blocks considered transparent. Not complete.
     */
    public static final HashSet<Byte> transparentBlocks = new HashSet<Byte>(Arrays.asList(new Byte[] {
            6, // Sapling
            8, // Flowing Water
            9, // Still Water
            20, // Glass
            30, // Cobweb
            37, // Dandelion
            38, // Rose
            39, // Brown Mushrooom
            40, // Red Mushroom
            44, // Slabs
            50, // Torch
            70, // Stone Pressure Plate
            72, // Wooden Pressure Plate
            75, // Redstone Torch (inactive)
            76, // Redstone Torch (active)
            78, // Snow
            79, // Ice
            102, // Window
            104, // Pumpkin Stem
            105, // Melon Stem
            126, // Wooden Slab
            (byte) 131, // Tripwire Hook
            (byte) 132, // Tripwire
            (byte) 147, // Weighted Pressure Plate (Light)
            (byte) 148, // Weighted Pressure Plate (Heavy)
            (byte) 149, // Redstone Comparator (inactive)
            (byte) 150, // Redstone Comparator (active)
            (byte) 151, // Daylight Sensor

    }));
}
