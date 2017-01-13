package network;

import java.io.Serializable;

public class Message implements Serializable {
    private int x;
    private int y;
    private MessageType type;
    private String text;
    private boolean defeat;
    private boolean isHit;

    private Message(int x, int y, MessageType type, String text, boolean defeat, boolean isHit) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.text = text;
        this.defeat = defeat;
        this.isHit = isHit;
    }

    public static Message getReadyMessage() {
        return new Message(0, 0, MessageType.READY, "", false, false);
    }

    public static Message getDefeatMessage() {
        return new Message(0, 0, MessageType.GAME_END, "You win", true, true);
    }

    public static Message getVictoryMessage() {
        return new Message(0, 0, MessageType.GAME_END, "You lose", false, false);
    }

    public static Message getHitMessage(int x, int y) {
        return new Message(x, y, MessageType.HIT, "", false, true);
    }

    public static Message getMissMessage(int x, int y) {
        return new Message(x, y, MessageType.HIT, "", false, false);
    }

    public static Message getAttackMessage(int x, int y) {
        return new Message(x, y, MessageType.ATTACK, "", false, false);
    }

    public static Message getInterruptMessage() {
        return new Message(0, 0, MessageType.INTERRUPT, "", false, false);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDefeat() {
        return defeat;
    }

    public void setDefeat(boolean defeat) {
        this.defeat = defeat;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }


}
