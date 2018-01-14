package view

import javafx.scene.control.ToggleGroup
import javafx.scene.text.FontWeight
import javafx.stage.StageStyle
import menu.MenuView
import menu.roundToTwoDecimals
import tornadofx.*

    class OrderIsSubmitted : Fragment("Thanks for your business!") {
        private val toggleGroup = ToggleGroup()
        override val root =
                vbox {
                    minWidth = 500.0
                    minHeight = 500.0
                    form {
                        label("Your order has been submitted!") { style(append = true) { fontSize = 2.em; fontWeight = FontWeight.BOLD } }
                        label("Total: $${MenuView.total.roundToTwoDecimals()}") { style(append = true) { fontSize = 2.em; fontWeight = FontWeight.BOLD } }
                    }
                    label("If you'd like, select an optional tip to be added to your total.") { style(append = true) { fontSize = 1.em; fontWeight = FontWeight.BOLD } }
                    radiobutton("10%", toggleGroup) { style(append = true) { fontSize = 1.em; fontWeight = FontWeight.BOLD } }
                    radiobutton("15%", toggleGroup) { style(append = true) { fontSize = 1.em; fontWeight = FontWeight.BOLD } }
                    radiobutton("20%", toggleGroup) { style(append = true) { fontSize = 1.em; fontWeight = FontWeight.BOLD } }
                    button("Add Tip") {
                        action {
                            val selection = toggleGroup.selectedToggleProperty().toString().split("'", "%")[1]
                            val tip = MenuView.total * ((selection.toDouble()) / 100)
                            MenuView.total = MenuView.total + tip
                            modalStage?.close()
                            find(UpdatedOrder::class).openModal(stageStyle = StageStyle.UTILITY)
                        }
                    }
                }
    }
