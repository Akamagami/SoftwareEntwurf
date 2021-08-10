package frontend.controller;

import frontend.GUIEvent;

public interface IGUIEventListener extends java.util.EventListener {

    void processGUIEvent(GUIEvent ge);
}
