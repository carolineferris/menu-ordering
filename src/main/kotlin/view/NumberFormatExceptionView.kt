package view

import javafx.scene.text.FontWeight
import tornadofx.*

class NumberFormatExceptionView : Fragment("Whoops!") {
    override val root =
            vbox {
                minWidth = 390.0
                minHeight = 325.0
                form {
                    label("Only integers greater than 0 can be entered in the form") { style(append = true) { fontSize = 1.em; fontWeight = FontWeight.BOLD } }
                }
                button("OK") {
                    action {
                        modalStage?.close()
                    }
                }
            }
}