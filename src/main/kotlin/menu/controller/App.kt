package menu.controller

import menu.MenuView
import tornadofx.App

class App: App() {
    override val primaryView = MenuView::class
}