package com.puzzle15;

public class MoveAction {
    public int fromXIndex;
    public int fromYIndex;
    public int toXIndex;
    public int toYIndex;
    public MoveAction(int fromX, int toX, int fromY, int toY, RandomGameActivity.LastDirection _direction){
        fromXIndex = fromX;
        toXIndex = toX;
        fromYIndex = fromY;
        toYIndex = toY;
        direction = _direction;
    }
    public RandomGameActivity.LastDirection direction;
}
