import greenfoot.Actor;

import java.util.List;

class MovableActor extends Actor {
    private final int moveDelta;
    private final Pathmap pathmap;

    private List<int[]> moveToPath;

    MovableActor(String pathmap, int moveDelta) {
        this.moveDelta = moveDelta;
        this.pathmap = new Pathmap(pathmap);
    }

    public void act() {
        move();
    }

    private void move() {
        if (moveToPath == null || moveToPath.size() == 0) {
            return;
        }

        int x = getX();
        int y = getY();

        int[] waypoint = moveToPath.get(0);

        int distanceX = Math.abs(x - waypoint[0]);
        int distanceY = Math.abs(y - waypoint[1]);

        double steps = Math.max(distanceX, distanceY) / moveDelta;

        int moveX = (int) Math.round(distanceX / steps);
        int moveY = (int) Math.round(distanceY / steps);

        if (distanceX <= moveDelta) {
            x = waypoint[0];
        } else {
            x += (x < waypoint[0]) ? moveX : -moveX;
        }

        if (distanceY <= moveDelta) {
            y = waypoint[1];
        } else {
            y += (y < waypoint[1]) ? moveY : -moveY;
        }

        if (x == waypoint[0] && y == waypoint[1]) {
            moveToPath.remove(0);

            if (moveToPath.size() == 0) {
                finishedMoveTo();
            }
        }

        setLocation(x, y);
    }

    void moveTo(int x, int y) {
        this.moveToPath = pathmap.findPath(getX(), getY(), x, y);
    }

    void finishedMoveTo() {

    }
}
