# Menu Management Software

This application is written in Kotlin and utilizes TornadoFX, a lightweight framework used to create UI's with the Kotlin language.

Let's get started!

## Import and Setup the Project

Import the project into your IDE. I used IntelliJ as my IDE, but if you are using a different IDE, I assume the same general set-up would still apply. The build.gradle file should have all the dependencies you need to get the app up and running. If you are prompted to Configure TornadoFX upon opening the project, click OK and it should automatically set the main class as a TornadoFX View. If this doesn't pop up, you will still be able to run the main class without setting this configuration though. 

Navigate to the MenuView.kt file, which is the "main" class, called "View" in TornadoFX. Directly to the left of the class declaration, you should see a small black and white icon. Click on it, and select 'Run'. If you don't see a small black and white icon to the left of the MenuView class, select Edit Configurations and add a new one with the following specifications: 

Click Add New Configuration -> select Application

Choose any name you'd like. 

Select the main class as "App" which is under menu.controller.App

Type -> select "Application"

Use classpath of module -> Menu_-_Order_Mgmt_main

Select the checkbox -> Single Instance Only 

You are now ready to run the app! 

## The UI 

Once you click Run, a GUI should pop up on your screen. It's a menu for a restaurant called "Fresh Food for Spoiled People".  You can select how many, if any, of the items you would like to order. Upon clicking "Submit Order", a modal will open that displays the order total. On this screen, you will see the option to add a tip to your order. After clicking "Add Tip", the updated order total will be displayed. 

## Error Handling 

I have included the functionality to not allow a user to enter a negative number, decimal, or a string.
In this case, a window will pop up that explains it must be a whole positive number. 

## Testing 

Tests are included for the methods in the MenuView class (in MenuViewTest). I used JFX Test Runner to boostrap TornadoFX for the tests. The annotation @TestInJfxThread allows me to run specific tests in the TornadoFX thread. 




