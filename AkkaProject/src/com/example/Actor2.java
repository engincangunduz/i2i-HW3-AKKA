package com.example;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class Actor2 extends AbstractActor {
    static public Props props() {
        return Props.create(Actor2.class, Actor2::new);
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
                    System.out.println("Actor2 received message: " + greet.message);
                    getSender().tell(new Greet("Hi from Actor2"), getSelf());
                })
                .build();
    }
}
