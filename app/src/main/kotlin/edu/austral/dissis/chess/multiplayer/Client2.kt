package edu.austral.dissis.chess.multiplayer

import clientserver.client.ClientController
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    Application.launch(Client2::class.java)
}

class Client2 : Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle
        val root = GameView(imageResolver)// Hay que usar algo de aca para abrir el server.
        ClientController(root)
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}