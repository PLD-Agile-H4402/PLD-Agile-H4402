/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.lyon.pld.agile.model;

/**
 *
 * @author scheah
 */
public class Section {
    private final String name;
    private final double length;
    private final Node destination;

    public Section(String name, double length, Node destination) {
        this.name = name;
        this.length = length;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public Node getDestination() {
        return destination;
    }
    
}
