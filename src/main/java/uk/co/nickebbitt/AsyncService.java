/*
 * $Header: $
 */

package uk.co.nickebbitt;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author Nick Ebbitt
 */
public class AsyncService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public void performAsyncComputation(DeferredResult<List<String>> result) {

        LOG.debug("Creating new Actor System...");

        ActorSystem system = ActorSystem.create("asyncTest");

        LOG.debug("Creating StringCollector to process request...");

        system.actorOf(Props.create(StringCollector.class), "stringCollector").tell(result, ActorRef.noSender());


    }

}
