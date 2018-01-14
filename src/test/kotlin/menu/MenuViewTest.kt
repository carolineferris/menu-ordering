package menu

import de.saxsys.javafx.test.JfxRunner
import de.saxsys.javafx.test.TestInJfxThread
import javafx.application.Platform
import javafx.scene.control.TextField
import menu.domain.Category
import menu.domain.MenuItem
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JfxRunner::class)
class MenuViewTest {

    @Test
    fun `should return total price if TextFields are provided`() {
        val categories = arrayOf(
                Category("Soups", arrayOf(
                        MenuItem("Mushroom Chowder", 3.99, "An organic blend of creamy soup and shitake", TextField("2")),
                        MenuItem("Tomato Bisque", 4.99, "Fresh tomato served with goldfish", TextField("3")))),
                Category("Salads", arrayOf(
                        MenuItem("Summer Salmon Salad", 9.99, "Freshly grilled salmon on spiced arugula", TextField("4")),
                        MenuItem("Kale Vinagrette", 10.59, "Crunchy Kale served with raspberries & lime", TextField("")))),
                Category("Mains", arrayOf(
                        MenuItem("Smoky BBQ Burger", 11.29, "Marinated for 12 hours, this burger is on fire!", TextField("")),
                        MenuItem("Creamy Lasagna", 9.79, "7 layers of fresh pasta, beef, cheese and marinara", TextField("")))))

        MenuView.calculateTotal(categories)

        assertThat(MenuView.total.roundToTwoDecimals()).isEqualTo(62.91)
    }

    @Test
    fun `should return total price if one of every item is ordered`() {
        val categories = arrayOf(
                Category("Soups", arrayOf(
                        MenuItem("Mushroom Chowder", 3.99, "An organic blend of creamy soup and shitake", TextField("1")),
                        MenuItem("Tomato Bisque", 4.99, "Fresh tomato served with goldfish", TextField("1")))),
                Category("Salads", arrayOf(
                        MenuItem("Summer Salmon Salad", 9.99, "Freshly grilled salmon on spiced arugula", TextField("1")),
                        MenuItem("Kale Vinagrette", 10.59, "Crunchy Kale served with raspberries & lime", TextField("1")))),
                Category("Mains", arrayOf(
                        MenuItem("Smoky BBQ Burger", 11.29, "Marinated for 12 hours, this burger is on fire!", TextField("1")),
                        MenuItem("Creamy Lasagna", 9.79, "7 layers of fresh pasta, beef, cheese and marinara", TextField("1")))))

        var totalPrice = 0.0
        categories.forEach { it.menuItems.forEach { totalPrice += it.price } }
        MenuView.calculateTotal(categories)
        assertThat(MenuView.total).isEqualTo(totalPrice)
    }

    @Test
    fun `should return error if a string is entered`() {
        val input = TextField("abc")
        assertThat(MenuView.validateNumeric(input)).isEqualTo(false)
    }

    @Test
    fun `should return error if a negative number is entered`() {
        val input = TextField("-2")
        assertThat(MenuView.validateNumeric(input)).isEqualTo(false)
    }

    @Test
    fun `should return error if a decimal is entered`() {
        val input = TextField("1.4")
        assertThat(MenuView.validateNumeric(input)).isEqualTo(false)
    }

    @Test
    @TestInJfxThread
    @Throws(Exception::class)
    fun testWithFXThread() {
        Assert.assertTrue(Platform.isFxApplicationThread())
    }
}