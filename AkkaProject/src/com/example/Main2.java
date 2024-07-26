package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class Main2 {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create("akka-system", ConfigFactory.parseFile(new File("/AkkaProject/resources/application2.conf")));

        try {
            ActorRef actor2 = system.actorOf(Actor2.props(), "actor2");

            System.out.println("Actor2 is ready and waiting for messages.");
            System.out.println("Press ENTER to exit the system");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }
}
