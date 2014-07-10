/*
 * $Header: $
 */

package uk.co.datasys;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author Nick Ebbitt
 */
@RestController
@RequestMapping(value = "/api")
public class RestService {

    @RequestMapping(value = "/stringsSync", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getStringsSync() {
        return Arrays.asList("one", "two");
    }

    @RequestMapping(value = "/stringsAsync", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<List<String>> getStringsAsync() {

        DeferredResult<List<String>> result = new DeferredResult<>(5000);

        new AsyncService().performAsyncComputation(result);

        return result;
    }

}
