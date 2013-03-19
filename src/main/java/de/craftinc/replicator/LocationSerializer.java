/*  Craft Inc. Replicator
    Copyright (C) 2013  Paul Schulze, Maximilian Häckel, Moritz Kaltofen, Tobias Ottenweller

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package de.craftinc.replicator;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

public class LocationSerializer
{
    private static String worldKey = "world";
    private static String xKey     = "x";
    private static String yKey     = "y";
    private static String zKey     = "z";


    private static World getWorld( String name ) throws Exception
    {
        World world = Plugin.instance.getServer().getWorld(name);

        if ( world == null )
        {
            throw new Exception("World '" + name + "' does not exists anymore! Cannot get instance!");
        }

        return world;
    }


    public static Map<String, Object> serializeLocation( Location l )
    {
        if ( l == null )
        {
            return null;
        }

        Map<String, Object> serializedLocation = new HashMap<String, Object>();

        serializedLocation.put(worldKey, l.getWorld().getName());
        serializedLocation.put(xKey, l.getX());
        serializedLocation.put(yKey, l.getY());
        serializedLocation.put(zKey, l.getZ());

        return serializedLocation;
    }


    public static Location deserializeLocation( Map<String, Object> map ) throws Exception
    {
        if ( map == null )
        {
            return null;
        }

        World w = getWorld((String) map.get(worldKey));


        // verbose loading of coordinates (they might be Double or Integer)
        Object objX = map.get(xKey);
        Object objY = map.get(yKey);
        Object objZ = map.get(zKey);

        double x, y, z;

        if ( objX instanceof Integer )
            x = (double) (Integer) objX;
        else
            x = (Double) objX;

        if ( objY instanceof Integer )
            y = (double) (Integer) objY;
        else
            y = (Double) objY;

        if ( objZ instanceof Integer )
            z = (double) (Integer) objZ;
        else
            z = (Double) objZ;


        return new Location(w, x, y, z);
    }
}
