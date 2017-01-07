package network;

import java.io.Serializable;

public class Message implements Serializable {
    private int x;
    private int y;
    private MessageType type;
    private String text;
    private boolean defeat;
    private boolean isHit;

    public Message(int x, int y, MessageType type, String text, boolean defeat, boolean isHit) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.text = text;
        this.defeat = defeat;
        this.isHit = isHit;
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
