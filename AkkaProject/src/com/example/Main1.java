package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import com.typesafe.config.ConfigFactory;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Main1 {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("akka-system", ConfigFactory.parseFile(new File("/AkkaProject/resources/application1.conf")));

        try {
            Future<ActorRef> actor2Future = system.actorSelection("akka://akka-system@127.0.0.1:2551/user/actor2").resolveOne(Duration.create(5, "seconds"));
            CompletableFuture<ActorRef> actor2CompletableFuture = FutureConverters.toJava(actor2Future).toCompletableFuture();
            ActorRef actor2 = actor2CompletableFuture.get();

            ActorRef actor1 = system.actorOf(Actor1.props(actor2), "actor1");

            actor1.tell(new Actor1.Greet("Hi from Actor1"), ActorRef.noSender());

            System.out.println("Press ENTER to exit the system");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }
}
