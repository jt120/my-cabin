package thread.actor;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * since 2015/2/10.
 */
public class ActorTest {

    static class Message {
        String url;

        Message(String url) {
            this.url = url;
        }
    }

    static class Result {
        String html;

        Result(String html) {
            this.html = html;
        }
    }

    static class UrlFetcher extends UntypedActor {
        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof Message) {
                Message work = (Message) message;
                //String result = WS.url(work.url).get();
                //getSender().tell(new Result(result), getSelf());
            } else {
                unhandled(message);
            }
            }
    }

    static class Querier extends UntypedActor {
        private String question;
        private List<String> engines;
        private AtomicReference<String> result;

        public Querier(String question, List<String> engines, AtomicReference<String> result) {
            this.question = question;
            this.engines = engines;
            this.result = result;
            }

        @Override
        public void onReceive(Object message) throws Exception {
            if (message instanceof Result) {
                result.compareAndSet(null, ((Result) message).html);
                getContext().stop(self());
            } else {
                for (String base : engines) {
                    String url = base + question;
                    ActorRef fetcher = this.getContext().actorOf(Props.create(UrlFetcher.class),
                            "fetcher-" + base.hashCode());
                    Message m = new Message(url);
                    fetcher.tell(m, self());
                }
            }
            }
    }

    private static String getFirstResultActors(final String question, final List<String> engines) {
        ActorSystem system = ActorSystem.create("Search");
        final AtomicReference<String> result = new AtomicReference<String>();

        final ActorRef q = system.actorOf(
                Props.create(new UntypedActorFactory() {
                                 @Override
                                 public Actor create() throws Exception {
                                     return new Querier(question, engines, result);
                                 }
                             }));

        q.tell(new Object(), ActorRef.noSender());

        while (result.get() == null) ;
        return result.get();
    }
}
