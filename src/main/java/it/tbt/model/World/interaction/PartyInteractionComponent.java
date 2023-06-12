package it.tbt.model.World.interaction;

import it.tbt.model.World.collision.CollisionDetector;
import it.tbt.model.World.collision.CollisionDetectorImpl;
import it.tbt.model.party.IParty;

public class PartyInteractionComponent implements InteractionComponent {

    private IParty party;
    private CollisionDetector collisionDetector = new CollisionDetectorImpl();

    public PartyInteractionComponent(final IParty party) {
        this.party = party;
    }

    /**
     *
     */
    @Override
    public void InteractLogic() {
        for(var x: party.getCurrentRoom().getEntities()) {
            if(collisionDetector.checkCollision(party, x)) {
                if(x instanceof Interactable) {
                    ((Interactable) x).OnInteraction(this.party);
                }
            }
        }
    }
}
