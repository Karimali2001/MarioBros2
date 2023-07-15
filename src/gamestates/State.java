/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//SUPER CLASE PARA TODOS NUESTROS ESTADOS DE JUEGO
package gamestates;

import main.Game;

/**
 *
 * @author karim
 */
public class State {
    
    //atributos
    protected Game game;
    
    //constructor
        public State(Game game) {
        this.game = game;
    }
    
    //set/get

    public Game getGame() {
        return game;
    }

        

    
}
