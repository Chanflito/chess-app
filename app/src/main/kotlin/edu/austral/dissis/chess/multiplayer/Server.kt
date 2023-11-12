package edu.austral.dissis.chess.multiplayer

import clientserver.server.ServerController
import common.engine.DefaultGameEngine

import javafx.application.Application
import javafx.stage.Stage

fun main() {
    Application.launch(ServerApplication::class.java)
}

class ServerApplication : Application() {
    private val gameEngine = DefaultGameEngine()

    override fun start(primaryStage: Stage) {
        ServerController(gameEngine)
    }
}