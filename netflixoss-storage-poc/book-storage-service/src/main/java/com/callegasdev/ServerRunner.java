package com.callegasdev;

public class ServerRunner {
    public static void main(String[] args) {
        System.setProperty("java.awt.headless","true");
        System.setProperty("archaius.deployment.environment","dev");
        netflix.karyon.KaryonRunner.main(new String[]{KaryonJerseyServerApp.class.getCanonicalName()});
    }
}
