/*
 * $Header: $
 */
package uk.co.nickebbitt;

import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.web.context.request.async.DeferredResult;
import scala.concurrent.duration.Duration;

/**
 *
 * @author Nick Ebbitt
 */
public class StringCollector extends UntypedActor {

    private final LoggingAdapter LOG = Logging.getLogger(getContext().system(), this);

    private DeferredResult<List<String>> result;
    private final List<String> collectedStrings = new ArrayList<>();

    @Override
    public void preStart() throws Exception {
        getContext().actorOf(Props.create(StringContainer.class, "one"), "one");
        getContext().actorOf(Props.create(StringContainer.class, "two"), "two");
        getContext().actorOf(Props.create(StringContainer.class, "three"), "three");
    }

    @Override
    public void onReceive(Object message) throws Exception {

        LOG.debug("message: {}", message);
        if (message instanceof DeferredResult) {

            result = (DeferredResult) message;

            getContext().actorSelection("*").tell("get", getSelf());

            getContext().setReceiveTimeout(Duration.create(2, TimeUnit.SECONDS));

        } else if (message instanceof String) {

            collectedStrings.add((String) message);

        } else if (message instanceof ReceiveTimeout) {

            result.setResult(collectedStrings);
            getContext().stop(getSelf());

        } else {
            unhandled(message);
        }

    }

}
