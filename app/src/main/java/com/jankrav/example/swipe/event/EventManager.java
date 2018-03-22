package com.jankrav.example.swipe.event;


import com.jankrav.example.swipe.view.VerticalPagerListener;

public class EventManager {
    private static final EventManager instance = new EventManager();
    public static EventManager getInstance(){
        return instance;
    }

    private VerticalPagerListener listener;
    private EventManager(){}

    public void register(VerticalPagerListener listener){
        if(listener != null){
            this.listener = listener;
        } else {
            throw new IllegalArgumentException("Vertical pager listener must not be null!");
        }
    }

    public void unregister(){
        listener = null;
    }

    public void post(PageChangedEvent event){
        listener.onLocationChanged(event);
    }
}
