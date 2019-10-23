package me.ioiu.btjh;

public enum PlayAndPause {

PLAY("play",true),
PAUSE("pause",false);
private String Type;
private boolean aBoolean;
    PlayAndPause(String play, boolean b) {
        this.Type=play;
        this.aBoolean= b;

    }

    public String getType() {
        return Type;
    }

    public boolean isaBoolean() {
        return aBoolean;
    }
}
