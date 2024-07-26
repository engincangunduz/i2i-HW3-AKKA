package com.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class Actor1 extends AbstractActor {
    private final ActorRef actor2;

    static public Props props(ActorRef actor2) {
        return Props.create(Actor1.class, () -> new Actor1(actor2));
    }

    public Actor1(ActorRef actor2) {
        this.actor2 = actor2;
    }

    static public class Greet {
        public final String message;

        public Greet(String message) {
            this.message = message;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Greet.class, greet -> {
                    System.out.println("Actor1 received message: " + greet.message);
                    actor2.tell(new Actor2.Greet("Hi from Actor1"), getSelf());
                })
                .build();
    }
}
