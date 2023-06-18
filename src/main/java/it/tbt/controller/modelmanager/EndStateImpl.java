package it.tbt.controller.modelmanager;

import it.tbt.controller.modelmanager.EndState;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

public class EndStateImpl implements EndState, StateTrigger {
    private String message;
    private StateObserver stateObserver;
    public EndStateImpl(String message){
        this.message=message;
    }

    @Override
    public String getMessage(){
        return message;
    }
    @Override
    public void setStateObserver (StateObserver stateObserver) {
        this.stateObserver=stateObserver;
    }

    @Override
    public void toMainMenu(){
        stateObserver.onMenu ();
    }
}
