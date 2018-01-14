package view

import javafx.scene.text.FontWeight
import menu.MenuView
import menu.roundToTwoDecimals
import tornadofx.*

class UpdatedOrder : Fragment("Thanks for your business!") {
    override val root =
            vbox {
                minWidth = 500.0
                minHeight = 500.0
                form {
                    label("Have a great day!") { style(append = true) { fontSize = 2.em; fontWeight = FontWeight.BOLD } }
                    label("Total with Tip: $${(MenuView.total.roundToTwoDecimals())}") { style(append = true) { fontSize = 2.em; fontWeight = FontWeight.BOLD } }
                }
                button("OK") {
                    action {
                        modalStage?.close()

                    }
                }
            }
}
