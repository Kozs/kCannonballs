package org.parabot.Kozs.kCannonballs;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Npc;
import org.rev317.min.api.wrappers.SceneObject;
import org.rev317.min.api.wrappers.Tile;
 
import java.awt.*;
 
public class Anti implements Strategy
{
    private Npc antiRandom;
 
    private Area bobsIsland = new Area(new Tile(2511, 4765),
            new Tile(2511, 4790),
            new Tile(2542, 4790),
            new Tile(2542, 4765));
 
    public final static int[] RANDOMS = { 410, 1091, 3117, 3022, 3351, 409 };
 
    private final int BOB = 1091;
    private final int SANDWICH_LADY = 3117;
    private final int OLD_MAN = 410;
    private final int PORTAL = 8987;
 
    public static int randomCount;
 
    public static final Runnable RUNNABLE = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
 
    public boolean activate()
    {
        for (Npc n : Npcs.getNearest(RANDOMS))
        {
            if (n.getInteractingCharacter().equals(Players.getMyPlayer()) && n.distanceTo() < 3)
            {
                antiRandom = n;
                return true;
            }
        }
 
        return false;
    }
 
    public void execute()
    {
        Time.sleep(1000);
 
        if (RUNNABLE != null)
            RUNNABLE.run();
 
        System.out.println("There is a random nearby!");
 
        if (antiRandom != null)
        {
            if (antiRandom.getDef().getId() == BOB && bobsIsland.contains(Players.getMyPlayer().getLocation()))
            {
                for (SceneObject portal : SceneObjects.getNearest(PORTAL))
                {
                    if (portal != null && bobsIsland.contains(Players.getMyPlayer().getLocation()))
                    {
                        final SceneObject portalToInteract = portal;
 
                        portal.interact(0);
 
                        Time.sleep(new SleepCondition()
                        {
                            @Override
                            public boolean isValid()
                            {
                                return portalToInteract.getLocation().distanceTo() == 1;
                            }
                        }, 7500);
 
                        Time.sleep(1000);
                    }
                    else
                    {
                        break;
                    }
                }
 
                if (!bobsIsland.contains(Players.getMyPlayer().getLocation()))
                {
                    System.out.println("The Bob random has been completed");
                }
            }
            else if (antiRandom.getDef().getId() == SANDWICH_LADY || antiRandom.getDef().getId() == OLD_MAN)
            {
                String str;
 
                if (antiRandom.getDef().getId() == SANDWICH_LADY)
                    str = "Sandwich Lady";
                else
                    str = "Old man";
 
                antiRandom.interact(0);
 
                Time.sleep(1500);
 
                System.out.println("The " + str + " random has been completed.");
            }
            else
            {
                System.out.println("A suspicious genie random has been called on us.");
                System.out.println("The client was closed to protect your account.");
 
                System.exit(0);
            }
        }
 
        randomCount++;
    }
 
    public class Area {
        private Polygon p;
 
        /**
         * Initializes a PolygonArea with the tiles given
         *
         * @param tiles
         *            tiles to use in the area
         */
        public Area(Tile... tiles) {
            this.p = new Polygon();
            for (int i = 0; i < tiles.length; i++) {
                p.addPoint(tiles[i].getX(), tiles[i].getY());
            }
        }
 
        /**
         * Checks if a tile is in the area
         *
         * @param tile
         *            The tile to check
         * @return <b>true</b> if area does contain the tile, otherwise <b>false</b>
         */
        public boolean contains(Tile tile) {
            return this.contains(tile.getX(), tile.getY());
        }
 
        public boolean contains(int x, int y) {
            int i;
            int j;
            boolean result = false;
            for (i = 0, j = p.npoints - 1; i < p.npoints; j = i++) {
                if ((p.ypoints[i] > y - 1) != (p.ypoints[j] > y - 1)
                        && (x <= (p.xpoints[j] - p.xpoints[i]) * (y - p.ypoints[i])
                        / (p.ypoints[j] - p.ypoints[i]) + p.xpoints[i])) {
                    result = !result;
                }
            }
            return result;
        }
    }
}