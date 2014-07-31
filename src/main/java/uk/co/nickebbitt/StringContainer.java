/*
 * $Header: $
 */

package uk.co.nickebbitt;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 *
 * @author Nick Ebbitt
 */
public class StringContainer extends UntypedActor {

    private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

    private final String value;

    public StringContainer(String value) {
        this.value = value;
    }

    @Override
    public void onReceive(Object message) throws Exception {

        LOG.debug("message: {}", message);
        if (message == "get") {
            getSender().tell(value, getSelf());
        }

    }

}
