package it.tbt.model.world.interaction;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.world.collision.CollisionDetector;
import it.tbt.model.world.collision.CollisionDetectorImpl;
import it.tbt.model.party.IParty;

import java.util.LinkedList;
import java.util.List;

/**
 * Interaction component implementation for the IParty objects.
 */

public class PartyInteractionComponent implements InteractionComponent {

    private final IParty party;
    private final CollisionDetector collisionDetector = new CollisionDetectorImpl();

    /**
     * @param party the party object onto which this component is attached to.
     */
    @SuppressFBWarnings (
            value = { "EI2" },
            justification = "The Component needs to access the exact instance of the Party the game is using."
    )
    public PartyInteractionComponent(final IParty party) {
        this.party = party;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interactLogic() {
        List<Interactable> y = new LinkedList<>();
        for (var x: party.getCurrentRoom().getEntities()) {
            if (collisionDetector.checkCollision(party, x)) {
                if (x instanceof Interactable) {
                    y.add((Interactable) x);
                }
            }
        }
        if(y.size()>0) {
            y.stream().findFirst().get().onInteraction(this.party);
        }

    }
}
