package menu.domain

import javafx.scene.control.TextField

data class MenuItem (
        val name: String,
        val price: Double,
        val description: String,
        var quantity: TextField
)