package menu

import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import javafx.stage.StageStyle
import menu.domain.Category
import menu.domain.MealOptions
import menu.domain.MenuItem
import tornadofx.*
import view.NumberFormatExceptionView
import view.OrderIsSubmitted
import java.math.BigDecimal

class MenuView : View("Menu") {
    var categories = arrayOf(
            Category("Soups", arrayOf(
                    MenuItem("Mushroom Chowder", 3.99, "An organic blend of creamy soup and shitake", TextField("")),
                    MenuItem("Tomato Bisque", 4.99, "Fresh tomato served with goldfish", TextField("")))),
            Category("Salads", arrayOf(
                    MenuItem("Summer Salmon Salad", 9.99, "Freshly grilled salmon on spiced arugula", TextField("")),
                    MenuItem("Kale Vinagrette", 10.59, "Crunchy Kale served with raspberries & lime", TextField("")))),
            Category("Mains", arrayOf(
                    MenuItem("Smoky BBQ Burger", 11.29, "Marinated for 12 hours, this burger is on fire!", TextField("")),
                    MenuItem("Creamy Lasagna", 9.79, "7 layers of fresh pasta, beef, cheese and marinara", TextField("")))))

    override val root = tabpane {
            tab("Lunch Menu", VBox()) {
                vbox {
                    label("Fresh Food for Spoiled People") { style(append = true) { fontSize = 2.em; fontWeight = FontWeight.BOLD } }
                    form {
                        categories.forEach { category ->
                            hbox {
                                fieldset(category.name) {
                                    style(append = true) { padding = box(1.em) }
                                    category.menuItems.forEach { menuItem ->
                                        field("${menuItem.name} - $${menuItem.price}")
                                        field("${menuItem.description}   QTY:") { menuItem.quantity = textfield() }
                                    }
                                }
                            }
                        }
                    }
                }

                button("Submit Order") {
                    action {
                        calculateTotal(categories)
                        if (!error) {
                            find(OrderIsSubmitted::class).openModal(stageStyle = StageStyle.UTILITY)
                        }
                    }
                }
            }
    }

    companion object {
        var total = 0.00
        var error = false

        internal fun calculateTotal(categories: Array<Category>) {
            total = 0.0

            categories.forEach { category ->
                category.menuItems.forEach { menuItem ->
                    if (!menuItem.quantity.text?.trim()?.length?.equals(0)!!) {
                        if (!validateNumeric(menuItem.quantity)) {
                            error = true
                            find(NumberFormatExceptionView::class).openModal(stageStyle = StageStyle.UTILITY)
                        } else {
                            error = false
                            total += menuItem.quantity.text?.toDouble()!! * menuItem.price
                        }
                    }
                }
            }
        }

        internal fun validateNumeric(textField: TextField?): Boolean {
            try {
                java.lang.Double.parseDouble(textField?.text.toString())
            } catch (numberFormatException: NumberFormatException) {
                return false
            }
            if (textField?.text.toString().contains(".")) {
                return false
            }
            if (textField?.text?.toInt()!! < 0) {
                return false
            }
            return true
        }
    }
}

fun Double.roundToTwoDecimals() =
        BigDecimal(this).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()