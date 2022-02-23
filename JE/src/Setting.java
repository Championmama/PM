package src;

import util.Position;

public class Setting {
    public static final boolean Loop = true;
    public static final int TickRate = 3;
    public static final Position StartPosition = new Position(5, 5);
    public static final boolean Ghost = false;
    public static final int Lives = 3;
    public static final int invTimer = 20; //in Ticks
    public static final int FaitnessTimer = 10;

    public static class Elements {
        public static final class GhostSpawns {
            public static final Position[] Spawn = {
                new Position(8, 10),
                new Position(10, 15),
                new Position(20, 8),
                new Position(5, 10)
            };

        }
        public static boolean E_Cherry = true;
        public static boolean E_Point = true;
        public static boolean E_inv = true;
        public static boolean E_Wall = true;
        public static boolean E_Door = true;


        public static class Points {
            public static final int Cherry = 100;
            public static final int Point = 10;
            public static final int inv = 100;
            public static final int empty = 0;
        }
    }

    public static class Animator {
        public static int CellWidth = 20;
        public static int CellHeight = 20;
        public static int inmargin = 0;
        public static int outmargin = 10;
    }
}
