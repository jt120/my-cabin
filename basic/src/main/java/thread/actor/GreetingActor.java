package thread.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class GreetingActor extends UntypedActor {
  LoggingAdapter log = Logging.getLogger(getContext().system(), this);

  public void onReceive(Object message) throws Exception {
    if (message instanceof Greeting)
      log.info("Hello " + ((Greeting) message).who);
  }

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef greeter = system.actorOf(Props.create(GreetingActor.class), "greeter");
        greeter.tell(new Greeting("Charlie Parker"), ActorRef.noSender());
    }
}